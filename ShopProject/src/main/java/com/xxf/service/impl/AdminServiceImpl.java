package com.xxf.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xxf.dao.AdminInfoMapper;
import com.xxf.model.AdminInfo;
import com.xxf.model.AdminInfoExample;
import com.xxf.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;

@Service("adminService")
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminInfoMapper mapper;

    @Override
    public PageInfo<AdminInfo> getList() {
        AdminInfoExample example = new AdminInfoExample();
        List<AdminInfo> list = this.mapper.selectByExample(example);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    @Override
    public Boolean insert(AdminInfo adminInfo) {
        Boolean flag = false;
        if(this.mapper.insert(adminInfo)>0){
            flag = true;
        }
        return flag;
    }

    @Override
    public Boolean update(AdminInfo adminInfo) {
        Boolean flag = false;
        if(this.mapper.updateByPrimaryKey(adminInfo)>0){
            flag = true;
        }
        return flag;
    }

    @Override
    public Boolean delete(String keyId) {
        Boolean flag = false;
        if(this.mapper.deleteByPrimaryKey(keyId)>0){
            flag = true;
        }
        return flag;
    }

    @Override
    public AdminInfo findById(String keyId) {
        return this.mapper.selectByPrimaryKey(keyId);
    }


    @Override
    public AdminInfo checkPwdAndUserName(AdminInfo adminInfo) {
        if(adminInfo!=null){
            AdminInfoExample example = new AdminInfoExample();
            example.createCriteria().andUserNameEqualTo(adminInfo.getUserName())
                    .andPasswordEqualTo(adminInfo.getPassword());
            List<AdminInfo> list = this.mapper.selectByExample(example);
            if(list!=null && list.size()>0){
                return list.get(0);
            }
        }
        return null;
    }

    @Override
    public AdminInfo findByName(String adminName) {
        AdminInfoExample example = new AdminInfoExample();
        example.createCriteria().andUserNameEqualTo(adminName);
        List<AdminInfo> list = this.mapper.selectByExample(example);
        if(list!=null && list.size()>0){
            return list.get(0);
        }
        return null;
    }
}
