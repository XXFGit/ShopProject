package com.xxf.service;

import com.xxf.model.AdminResources;

import java.util.List;
import java.util.Set;

/**
 * Created by 熊显付 on 2020/4/10.
 */
public interface AdminResouceService {

    List<AdminResources> getList();

    Boolean insert(AdminResources adminResources);

    Boolean update(AdminResources adminResources);

    Boolean delete(int keyId);

    AdminResources findById(int keyId);

    List<AdminResources> selMenu(Integer roleId);

    Set<String> selpremission(Integer roleId);

}
