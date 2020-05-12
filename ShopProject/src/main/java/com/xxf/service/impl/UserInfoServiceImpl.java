package com.xxf.service.impl;

import com.xxf.dao.UserInfoMapper;
import com.xxf.model.UserInfo;
import com.xxf.model.UserInfoExample;
import com.xxf.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoMapper mapper;

    @Override
    public List<UserInfo> findAll() {
        UserInfoExample example = new UserInfoExample();
        List<UserInfo> list = this.mapper.selectByExample(example);
        if(list!=null && list.size()>0){
            return list;
        }
        return null;
    }
}
