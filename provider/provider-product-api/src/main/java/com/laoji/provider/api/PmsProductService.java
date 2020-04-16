package com.laoji.provider.api;

import com.github.pagehelper.PageInfo;
import com.laoji.provider.domain.PmsProduct;

import java.util.List;

/**
*  商品服务接口
*  @author: laoji
*  @date:2020/4/16 10:57
*/
public interface PmsProductService {

    /**
     * 获取所有商品
     * @return List<PmsProduct> {@Link PmsProduct}
     */
    public List<PmsProduct> getAll();

    /**
     * 根据分页获取数据
     * @param pageSize 页数
     * @param pageNum  一页多少数据
     * @return PageInfo<PmsProduct>
     */
    public PageInfo<PmsProduct> getAll(Integer pageSize, Integer pageNum);
}
