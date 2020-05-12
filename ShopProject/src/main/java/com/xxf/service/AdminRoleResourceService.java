package com.xxf.service;

import com.xxf.model.AdminRoleResources;

import java.util.List;

/**
 * Created by 熊显付 on 2020/4/14.
 */
public interface AdminRoleResourceService {

    List<AdminRoleResources> getRoleResourceByRoleId(Integer roleId);

    Boolean savePremission(String[] pres, Integer roleId);

}
