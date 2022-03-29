package com.mblog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mblog.domain.ResponseResult;
import com.mblog.domain.entity.Category;


/**
 * 分类表(Category)表服务接口
 *
 * @author makejava
 * @since 2022-03-02 15:12:28
 */
public interface CategoryService extends IService<Category> {

    ResponseResult getCategoryList();
}
