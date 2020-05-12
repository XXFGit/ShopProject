package com.xxf.dao;

import com.xxf.model.AdminInfo;
import com.xxf.model.AdminInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AdminInfoMapper {
    int countByExample(AdminInfoExample example);

    int deleteByExample(AdminInfoExample example);

    int deleteByPrimaryKey(String id);

    int insert(AdminInfo record);

    int insertSelective(AdminInfo record);

    List<AdminInfo> selectByExample(AdminInfoExample example);

    AdminInfo selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") AdminInfo record, @Param("example") AdminInfoExample example);

    int updateByExample(@Param("record") AdminInfo record, @Param("example") AdminInfoExample example);

    int updateByPrimaryKeySelective(AdminInfo record);

    int updateByPrimaryKey(AdminInfo record);
}