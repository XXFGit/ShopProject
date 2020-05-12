package com.xxf.dao;

import com.xxf.model.AdminRoleResources;
import com.xxf.model.AdminRoleResourcesExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AdminRoleResourcesMapper {
    int countByExample(AdminRoleResourcesExample example);

    int deleteByExample(AdminRoleResourcesExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AdminRoleResources record);

    int insertSelective(AdminRoleResources record);

    List<AdminRoleResources> selectByExample(AdminRoleResourcesExample example);

    AdminRoleResources selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AdminRoleResources record, @Param("example") AdminRoleResourcesExample example);

    int updateByExample(@Param("record") AdminRoleResources record, @Param("example") AdminRoleResourcesExample example);

    int updateByPrimaryKeySelective(AdminRoleResources record);

    int updateByPrimaryKey(AdminRoleResources record);
}