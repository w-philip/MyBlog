package com.mblog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mblog.domain.ResponseResult;
import com.mblog.domain.entity.Article;

public interface ArticleService extends IService<Article> {
    ResponseResult hotArticelList();

    ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId);

    ResponseResult getArticleDetail(Long id);
}
