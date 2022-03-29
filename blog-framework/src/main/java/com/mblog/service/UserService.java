package com.mblog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mblog.domain.ResponseResult;
import com.mblog.domain.entity.User;


/**
 * 用户表(User)表服务接口
 *
 * @author makejava
 * @since 2022-03-05 14:23:13
 */
public interface UserService extends IService<User> {

    ResponseResult userInfo();

    ResponseResult updateUserInfo(User user);

    ResponseResult register(User user);
}
