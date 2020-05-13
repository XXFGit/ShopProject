package com.xxf.service.impl;

import com.github.pagehelper.PageInfo;
import com.xxf.dao.RoleMapper;
import com.xxf.model.Role;
import com.xxf.model.RoleExample;
import com.xxf.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("roleService")
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper mapper;

    @Override
    public PageInfo<Role> getList() {
        RoleExample example = new RoleExample();
        List<Role> list = this.mapper.selectByExample(example);
        if(list!=null && list.size()>0){
            PageInfo pageInfo = new PageInfo(list);
            return pageInfo;
        }
        return null;
    }

    @Override
    public Boolean insert(Role role) {
        Boolean flag = false;
        if(this.mapper.insert(role)>0){
            flag = true;
        }
        return flag;
    }

    @Override
    public Boolean update(Role role) {
        Boolean flag = false;
        if(this.mapper.updateByPrimaryKey(role)>0){
            flag = true;
        }
        return flag;
    }

    @Override
    public Boolean delete(String keyId) {
        Boolean flag = false;
        if(this.mapper.deleteByPrimaryKey(Integer.parseInt(keyId))>0){
            flag = true;
        }
        return flag;
    }

    @Override
    public Role findById(String keyId) {
        return this.mapper.selectByPrimaryKey(Integer.parseInt(keyId));
    }

}
