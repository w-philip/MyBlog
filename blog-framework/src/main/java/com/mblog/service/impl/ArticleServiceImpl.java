package com.mblog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mblog.constants.SystemConstants;
import com.mblog.domain.ResponseResult;
import com.mblog.domain.entity.Article;
import com.mblog.domain.entity.Category;
import com.mblog.domain.vo.ArticleDetailVo;
import com.mblog.domain.vo.ArticleListVo;
import com.mblog.domain.vo.HotArticleVo;
import com.mblog.domain.vo.PageVo;
import com.mblog.mapper.ArticleMapper;
import com.mblog.service.ArticleService;
import com.mblog.service.CategoryService;
import com.mblog.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Autowired
    private CategoryService categoryService;

    @Override
    public ResponseResult hotArticelList() {
        LambdaQueryWrapper<Article> wrapper = new LambdaQueryWrapper();
        wrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        wrapper.orderByDesc(Article::getViewCount);
        Page<Article> page = new Page<>(1,10);
        page(page,wrapper);
        List<Article> articles = page.getRecords();
//        List<HotArticleVo> vo = new ArrayList<>();
//        for (Article article : articles) {
//            HotArticleVo hotArticleVo = new HotArticleVo();
//            BeanUtils.copyProperties(article,hotArticleVo);
//            vo.add(hotArticleVo);
//        }
        List<HotArticleVo> hotArticleVos = BeanCopyUtils.copyBeanList(articles, HotArticleVo.class);
        ResponseResult result = ResponseResult.okResult(hotArticleVos);
        return result;
    }

    @Override
    public ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId) {
        //????????????
        LambdaQueryWrapper<Article> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        //?????????categoryId???????????????????????????
        lambdaQueryWrapper.eq(Objects.nonNull(categoryId)&&categoryId>0,Article::getCategoryId,categoryId);
        //????????????????????????
        lambdaQueryWrapper.eq(Article::getStatus,0);
        //???isTop????????????
        lambdaQueryWrapper.orderByDesc(Article::getIsTop);
        //????????????
        Page<Article> page = new Page<>(pageNum,pageSize);
        page(page,lambdaQueryWrapper);

        List<Article> articles = page.getRecords();
        //??????categoryName
        articles.stream()
                .map(article -> article.setCategoryName(categoryService.getById(article.getCategoryId()).getName()))
                .collect(Collectors.toList());

//        for (Article article : articles) {
//            Category category = categoryService.getById(article.getCategoryId());
//            article.setCategoryName(category.getName());
//        }

        //??????????????????
        List<ArticleListVo> articleListVos = BeanCopyUtils.copyBeanList(page.getRecords(), ArticleListVo.class);

        PageVo pageVo = new PageVo(articleListVos,page.getTotal());

        return ResponseResult.okResult(pageVo);
    }

    @Override
    public ResponseResult getArticleDetail(Long id) {
        //??????id????????????
        Article article = getById(id);
        //?????????VO
        ArticleDetailVo articleDetailVo = BeanCopyUtils.copyBean(article, ArticleDetailVo.class);
        //????????????id???????????????
        Category category = categoryService.getById(articleDetailVo.getCategoryId());
        if(category!=null){
            articleDetailVo.setCategoryName(category.getName());
        }
        //??????????????????
        return ResponseResult.okResult(articleDetailVo);
    }
}
