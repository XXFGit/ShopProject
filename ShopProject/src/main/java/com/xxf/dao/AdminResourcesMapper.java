package com.xxf.dao;

import com.xxf.model.AdminResources;
import com.xxf.model.AdminResourcesExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AdminResourcesMapper {
    int countByExample(AdminResourcesExample example);

    int deleteByExample(AdminResourcesExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AdminResources record);

    int insertSelective(AdminResources record);

    List<AdminResources> selectByExample(AdminResourcesExample example);

    AdminResources selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AdminResources record, @Param("example") AdminResourcesExample example);

    int updateByExample(@Param("record") AdminResources record, @Param("example") AdminResourcesExample example);

    int updateByPrimaryKeySelective(AdminResources record);

    int updateByPrimaryKey(AdminResources record);
}