package com.xxf.service;

import com.xxf.model.AdminInfo;

import java.util.List;
import java.util.Set;

public interface AdminService {

    List<AdminInfo> getList();

    Boolean insert(AdminInfo adminInfo);

    Boolean update(AdminInfo adminInfo);

    Boolean delete(String keyId);

    AdminInfo findById(String keyId);

    AdminInfo checkPwdAndUserName(AdminInfo adminInfo);

    AdminInfo findByName(String adminName);

}
