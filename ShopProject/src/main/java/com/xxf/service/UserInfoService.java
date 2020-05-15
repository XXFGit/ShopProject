package com.xxf.service;

import com.github.pagehelper.PageInfo;
import com.xxf.model.UserInfo;


public interface UserInfoService {

    PageInfo<UserInfo> findAll();

}
