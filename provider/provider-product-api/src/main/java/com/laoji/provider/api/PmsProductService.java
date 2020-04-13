package com.laoji.provider.api;

import com.github.pagehelper.PageInfo;
import com.laoji.provider.domain.PmsProduct;

import java.util.List;

public interface PmsProductService {

    public List<PmsProduct> getAll();
    public PageInfo<PmsProduct> getAll(Integer pageSize, Integer pageNum);
}
