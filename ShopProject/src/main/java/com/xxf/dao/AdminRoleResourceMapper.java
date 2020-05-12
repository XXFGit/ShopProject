package com.xxf.dao;

import com.xxf.model.AdminResources;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface AdminRoleResourceMapper {

    @Select("SELECT m.id,m.name,m.icon,m.url,m.pid,m.permission,m.type FROM admin_role_resources r LEFT JOIN admin_resources m ON r.resources_id = m.id WHERE r.role_id = #{0} order by m.id asc ")
    List<AdminResources> getMenus(Integer roleId);

}
