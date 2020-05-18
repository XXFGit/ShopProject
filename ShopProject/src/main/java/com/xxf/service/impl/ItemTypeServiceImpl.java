package com.xxf.service.impl;

import com.github.pagehelper.PageInfo;
import com.xxf.dao.ItemTypeMapper;
import com.xxf.model.ItemType;
import com.xxf.model.ItemTypeExample;
import com.xxf.service.ItemTypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("itemTypeService")
public class ItemTypeServiceImpl implements ItemTypeService {

    @Resource
    private ItemTypeMapper mapper;

    @Override
    public PageInfo<ItemType> getList() {
        ItemTypeExample example = new ItemTypeExample();
        List<ItemType> list = this.mapper.selectByExample(example);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    @Override
    public Boolean insert(ItemType itemType) {
        Boolean flag = false;
        if(this.mapper.insert(itemType)>0){
            flag = true;
        }
        return flag;
    }

    @Override
    public Boolean update(ItemType itemType) {
        Boolean flag = false;
        if(this.mapper.updateByPrimaryKey(itemType)>0){
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
    public ItemType findById(String keyId) {
        return this.mapper.selectByPrimaryKey(keyId);
    }
}
