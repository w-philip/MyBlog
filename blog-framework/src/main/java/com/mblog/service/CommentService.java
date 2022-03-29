package com.mblog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mblog.domain.ResponseResult;
import com.mblog.domain.entity.Comment;


/**
 * 评论表(Comment)表服务接口
 *
 * @author makejava
 * @since 2022-03-04 20:15:11
 */
public interface CommentService extends IService<Comment> {

    ResponseResult commentList(String commentType, Long articleId, Integer pageNum, Integer pageSize);

    ResponseResult addComment(Comment comment);
}
