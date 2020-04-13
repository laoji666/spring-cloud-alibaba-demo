package com.laoji.provider.tests;

import com.github.pagehelper.PageInfo;
import com.laoji.provider.api.PmsProductService;
import com.laoji.provider.domain.PmsProduct;
import org.apache.dubbo.config.annotation.Reference;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
@Rollback
public class PmsProductServiceTests {
    @Reference
    PmsProductService pmsProductService;

    @Test
    public void selectAll(){
        List<PmsProduct> list = pmsProductService.getAll();
        list.forEach(l ->{
            System.out.println(l);
        });
    }
    @Test
    public void selectAll2(){
        PageInfo<PmsProduct> pageInfo = pmsProductService.getAll(1, 10);
        pageInfo.getList().forEach(l ->{
            System.out.println(l);
        });
    }
}
