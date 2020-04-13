package com.laoji.business.controller;

import com.github.pagehelper.PageInfo;
import com.laoji.business.BusinessException;
import com.laoji.business.BusinessStatus;
import com.laoji.commons.dto.ResponseResult;
import com.laoji.provider.api.PmsProductService;
import com.laoji.provider.domain.PmsProduct;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product/h5")
public class ProductH5Controller {
    @Reference
    PmsProductService pmsProductService;

    @GetMapping("/getList")
    public ResponseResult<PageInfo> getList(Integer pageSize, Integer pageNum){
        if(pageSize==null||pageNum==null){
            pageSize=1;
            pageNum=10;
        }

        PageInfo<PmsProduct> pageInfo = pmsProductService.getAll(pageSize, pageNum);
        if(pageInfo==null){
            throw new BusinessException(BusinessStatus.DATA_NOT_FOUNT);
        }
        return new ResponseResult<PageInfo>(ResponseResult.OK,"查询成功",pageInfo);
    }
}
