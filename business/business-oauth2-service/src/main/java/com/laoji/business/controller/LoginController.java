package com.laoji.business.controller;

import com.google.common.collect.Maps;
import com.laoji.business.BusinessException;
import com.laoji.business.BusinessStatus;
import com.laoji.business.feign.ProfileFeign;
import com.laoji.cloud.api.MessageService;
import com.laoji.cloud.dto.UmsAdminLoginLogDTO;
import com.laoji.commons.dto.IpInfo;
import com.laoji.commons.dto.LoginInfo;
import com.laoji.commons.dto.LoginParams;
import com.laoji.commons.dto.ResponseResult;
import com.laoji.commons.utils.MapperUtils;
import com.laoji.commons.utils.OkHttpClientUtil;
import com.laoji.commons.utils.UserAgentUtils;
import com.laoji.provider.api.UmsAdminService;
import com.laoji.provider.domain.UmsAdmin;
import okhttp3.Response;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Enumeration;
import java.util.Map;
import java.util.Objects;

/**
*  登录 Controller
*  @author: laoji
*  @date:2020/4/5 16:07
*/
@RestController
@RequestMapping(value="/user")
public class LoginController {
    private static final String URL_OAUTH_TOKEN="http://localhost:9001/oauth/token";

    @Value("${base.oauth2.grant_type}")
    public String grantType;
    @Value("${base.oauth2.client_id}")
    public String clientId;
    @Value("${base.oauth2.client_secret}")
    public String clientSecret;

    @Resource(name = "userDetailsService")
    public UserDetailsService userDetailsService;
    @Resource
    public BCryptPasswordEncoder passwordEncoder;
    @Resource
    public TokenStore tokenStore;
    @Resource
    private ProfileFeign profileFeign;
    @Reference(version = "1.0.0")
    private UmsAdminService umsAdminService;
    @Reference
    private MessageService messageService;

    @PostMapping(value = "/login")
    public ResponseResult<Map<String,Object>> login(@RequestBody LoginParams loginParams,HttpServletRequest request){
        Map<String,Object> result= Maps.newHashMap();
        // 验证密码是否正确
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginParams.getUsername());
        if (userDetails == null || !passwordEncoder.matches(loginParams.getPassword(), userDetails.getPassword())) {
            throw new BusinessException(BusinessStatus.ADMIN_PASSWORD);
        }

        Map<String,String> paramsMap= Maps.newHashMap();
        paramsMap.put("username",loginParams.getUsername());
        paramsMap.put("password",loginParams.getPassword());
        paramsMap.put("grant_type",grantType);
        paramsMap.put("client_id",clientId);
        paramsMap.put("client_secret",clientSecret);
        Response response = OkHttpClientUtil.getInstance().postData(URL_OAUTH_TOKEN, paramsMap);
        try {
            Map<String, Object>  jsonMap= MapperUtils.json2map(Objects.requireNonNull(response.body()).string());
            String token = String.valueOf(jsonMap.get("access_token"));
            result.put("token",token);
        } catch (Exception e) {
            e.printStackTrace();
        }
        sendLoginMessage(loginParams.getUsername(), request);
        return new ResponseResult<Map<String,Object>>(20000,HttpStatus.OK.toString(),result);
    }

    @GetMapping(value = "/info")
    public ResponseResult<LoginInfo> getInfo() throws Exception {
        // 获取认证信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // 获取个人信息
        System.out.println(authentication.getName());
        String jsonString = profileFeign.info(authentication.getName());
        UmsAdmin umsAdmin = MapperUtils.json2pojoByTree(jsonString, "data", UmsAdmin.class);

        if(umsAdmin==null){
            //说明触发熔断
            throw new BusinessException(BusinessStatus.BREAKING);
        }
        // 封装并返回结果
        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setName(umsAdmin.getUsername());
        loginInfo.setAvatar(umsAdmin.getIcon());
        return new ResponseResult<LoginInfo>(ResponseResult.OK,"获取登录信息成功",loginInfo);
    }
    /**
     * 注销
     *
     * @return {@link ResponseResult}
     */
    @PostMapping(value = "/logout")
    public ResponseResult<Void> logout(HttpServletRequest request) {
        // 获取 token
        Enumeration<String> headers = request.getHeaders("authorization");
        String tokenStr = headers.nextElement();
        String[] strings = tokenStr.split(" ");
        // 删除 token 以注销strings
        OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(strings[1]);
        tokenStore.removeAccessToken(oAuth2AccessToken);
        return new ResponseResult<Void>(ResponseResult.OK, "用户已注销");
    }

    private boolean sendLoginMessage(String username,HttpServletRequest request){
        UmsAdmin umsAdmin = umsAdminService.get(username);
        String ip = UserAgentUtils.getIpAddr(request);
        IpInfo ipInfo = UserAgentUtils.getIpInfo(ip);
        String region = ipInfo.getRegion();
        String city = ipInfo.getCity();
        String browser = UserAgentUtils.getBrowser(request).getName();
        UmsAdminLoginLogDTO dto=new UmsAdminLoginLogDTO();
        dto.setAdminId(umsAdmin.getId());
        dto.setCreateTime(new Date());
        dto.setIp(ip);
        dto.setAddress(region+":"+city);
        dto.setUserAgent(browser);

        return messageService.sendAdminLoginLog(dto);
    }
}
