package com.xxf.service;

import com.xxf.model.Role;

import java.util.List;

public interface RoleService {

    List<Role> getList();

    Boolean insert(Role adminInfo);

    Boolean update(Role adminInfo);

    Boolean delete(String keyId);

    Role findById(String keyId);

}
