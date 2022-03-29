package com.mblog.service;

import com.mblog.domain.ResponseResult;
import com.mblog.domain.entity.User;

public interface BlogLoginService {
    ResponseResult login(User user);

    ResponseResult logout();
}
