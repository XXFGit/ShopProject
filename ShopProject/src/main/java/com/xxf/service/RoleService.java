package com.xxf.service;

import com.github.pagehelper.PageInfo;
import com.xxf.model.Role;

import java.util.List;

public interface RoleService {

    PageInfo<Role> getList();

    Boolean insert(Role adminInfo);

    Boolean update(Role adminInfo);

    Boolean delete(String keyId);

    Role findById(String keyId);

}
