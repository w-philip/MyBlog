package com.mblog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mblog.domain.ResponseResult;
import com.mblog.domain.entity.Link;


/**
 * 友链(Link)表服务接口
 *
 * @author makejava
 * @since 2022-03-03 13:31:09
 */
public interface LinkService extends IService<Link> {

    ResponseResult getAllLink();
}
