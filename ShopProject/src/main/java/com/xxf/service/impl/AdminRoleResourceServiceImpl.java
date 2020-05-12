package com.xxf.service.impl;

import com.xxf.dao.AdminResourcesMapper;
import com.xxf.dao.AdminRoleResourcesMapper;
import com.xxf.model.AdminRoleResources;
import com.xxf.model.AdminRoleResourcesExample;
import com.xxf.service.AdminRoleResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by 熊显付 on 2020/4/14.
 */
@Service("AdminRoleResourceService")
public class AdminRoleResourceServiceImpl implements AdminRoleResourceService {

    @Autowired
    private AdminRoleResourcesMapper mapper;

    @Autowired
    private AdminResourcesMapper adminResourcesMapper;

    @Override
    public List<AdminRoleResources> getRoleResourceByRoleId(Integer roleId) {
        AdminRoleResourcesExample example = new AdminRoleResourcesExample();
        AdminRoleResourcesExample.Criteria criteria = example.createCriteria();
        criteria.andRoleIdEqualTo( roleId );
        List<AdminRoleResources> list = this.mapper.selectByExample( example );
        return list;
    }

    /**
     * 事务控制
     * @param pres
     * @param roleId
     * @return
     */
    @Override
    @Transactional(rollbackFor = { Exception.class }, readOnly = false)
    public Boolean savePremission(String[] pres, Integer roleId) {
        Boolean flag = true;
        try{
            if(pres!=null && pres.length>0 && roleId!=null){
                //先删除该角色下的所有权限
                List<AdminRoleResources> resources = getRoleResourceByRoleId( roleId );
                if(resources!=null && resources.size()>0){
                    for (AdminRoleResources re : resources ) {
                        this.mapper.deleteByPrimaryKey( re.getId() );
                    }
                }
                //再循环添加
                for (String resourceId : pres) {
                    AdminRoleResources reso = new AdminRoleResources();
                    reso.setRoleId( roleId );
                    reso.setResourcesId( Integer.parseInt(  resourceId ) );
                    reso.setCreateTime( new Date(  ) );
                    reso.setUpdateTime( new Date(  ) );
                    this.mapper.insert( reso );
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            flag = false;
        }
        return flag;
    }
}
