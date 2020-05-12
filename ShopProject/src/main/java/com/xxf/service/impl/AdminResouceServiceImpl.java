package com.xxf.service.impl;

import com.xxf.dao.AdminResourcesMapper;
import com.xxf.dao.AdminRoleResourceMapper;
import com.xxf.dao.AdminRoleResourcesMapper;
import com.xxf.model.AdminResources;
import com.xxf.model.AdminResourcesExample;
import com.xxf.model.AdminRoleResources;
import com.xxf.model.AdminRoleResourcesExample;
import com.xxf.service.AdminResouceService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by 熊显付 on 2020/4/10.
 */
@Service("adminResouceService")
public class AdminResouceServiceImpl implements AdminResouceService {

    @Autowired
    private AdminResourcesMapper mapper;
    @Autowired
    private AdminRoleResourcesMapper mapper1;  //中间表
    @Autowired
    private AdminRoleResourceMapper mapper2;  //链表查询

    @Override
    public List<AdminResources> getList() {
        AdminResourcesExample example = new AdminResourcesExample();
        List<AdminResources> list = this.mapper.selectByExample(example);
        if(list!=null && list.size()>0){
            return list;
        }
        return null;
    }

    @Override
    public Boolean insert(AdminResources adminInfo) {
        Boolean flag = false;
        if(this.mapper.insert(adminInfo)>0){
            flag = true;
        }
        return flag;
    }

    @Override
    public Boolean update(AdminResources adminInfo) {
        Boolean flag = false;
        if(this.mapper.updateByPrimaryKey(adminInfo)>0){
            flag = true;
        }
        return flag;
    }

    @Override
    public Boolean delete(int keyId) {
        Boolean flag = false;
        if(this.mapper.deleteByPrimaryKey(keyId)>0){
            flag = true;
        }
        return flag;
    }

    @Override
    public AdminResources findById(int keyId) {
        return this.mapper.selectByPrimaryKey(keyId);
    }


    /**
     * 功能描述: 根据roleId组装改角色下的菜单。
     * @Param: [roleId]
     * @Return: java.util.List<com.xxf.model.AdminResources>
     * @Author: Administrator
     * @Date: 2020/4/15 下午 03:00
     */
    @Override
    public List<AdminResources> selMenu(Integer roleId) {
        List<AdminResources> results = new ArrayList<>();
        AdminRoleResourcesExample example = new AdminRoleResourcesExample();
        example.createCriteria().andRoleIdEqualTo(roleId);
        //根据roleId查出中间表的数据
        List<AdminRoleResources> list = this.mapper1.selectByExample(example);
        if (list != null && list.size() > 0) {
            //连表查询
            /**
             * SELECT m.id ,m.name,m.icon,m.url,m.pid
             * FROM admin_role_resources r LEFT JOIN admin_resources m ON r.resources_id = m.id
             * WHERE r.role_id = 1 and type = 1 order by m.sort desc
             */
            List<AdminResources> menus = mapper2.getMenus(roleId);
            //循环
            for (int i = 0; i < menus.size(); i++) {
                //判断是不是顶级菜单
                if (menus.get(i).getPid() == 0 && !menus.get(i).getType().equals("2")) {  //除去按钮
                    AdminResources menu = new AdminResources();
                    menu.setId(menus.get(i).getId());
                    menu.setName(menus.get(i).getName());
                    menu.setIcon(menus.get(i).getIcon());
                    menu.setUrl(menus.get(i).getUrl());
                    //menus2 ： 子菜单list，
                    List<AdminResources> menus2 = new ArrayList<>();
                    for (int j = 0; j < menus.size(); j++) {
                        ////再次循环,找到父id等于当前的menuId时，将其add进子菜单中。
                        //menus.get(j),menus.get(i)  关键判断
                        if (menus.get(j).getPid() == menus.get(i).getId()) {
                            AdminResources menu2 = new AdminResources();
                            menu2.setId(menus.get(j).getId());
                            menu2.setName(menus.get(j).getName());
                            menu2.setIcon(menus.get(j).getIcon());
                            menu2.setUrl(menus.get(j).getUrl());
                            menus2.add(menu2);
                        }
                    }
                    //子菜单设置值
                    menu.setChildren(menus2);
                    results.add(menu);
                }
            }
        }

        return results;
    }

    @Override
    public Set<String> selpremission(Integer roleId) {
        List<AdminResources> menus = mapper2.getMenus(roleId);
        Set<String> set = new HashSet<>();
        if(menus!=null && menus.size()>0){
            for (int i = 0; i <menus.size() ; i++) {
                if(StringUtils.isNotBlank(menus.get(i).getPermission())){
                    set.add(menus.get(i).getPermission());
                }
            }
        }
        return set;
    }


}
