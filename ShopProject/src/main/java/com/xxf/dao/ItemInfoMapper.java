package com.xxf.dao;

import com.xxf.model.ItemInfo;
import com.xxf.model.ItemInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ItemInfoMapper {
    int countByExample(ItemInfoExample example);

    int deleteByExample(ItemInfoExample example);

    int deleteByPrimaryKey(String id);

    int insert(ItemInfo record);

    int insertSelective(ItemInfo record);

    List<ItemInfo> selectByExampleWithBLOBs(ItemInfoExample example);

    List<ItemInfo> selectByExample(ItemInfoExample example);

    ItemInfo selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ItemInfo record, @Param("example") ItemInfoExample example);

    int updateByExampleWithBLOBs(@Param("record") ItemInfo record, @Param("example") ItemInfoExample example);

    int updateByExample(@Param("record") ItemInfo record, @Param("example") ItemInfoExample example);

    int updateByPrimaryKeySelective(ItemInfo record);

    int updateByPrimaryKeyWithBLOBs(ItemInfo record);

    int updateByPrimaryKey(ItemInfo record);
}