package com.xxf.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xxf.Utils.SearchCondition;
import com.xxf.dao.ItemInfoMapper;
import com.xxf.model.ItemInfo;
import com.xxf.model.ItemInfoExample;
import com.xxf.service.ItemInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("itemInfoService")
public class ItemInfoServicempl implements ItemInfoService {

    @Autowired
    private ItemInfoMapper mapper;

    @Override
    public PageInfo<ItemInfo> getList(SearchCondition con, int page, int size) {
        PageHelper.startPage(page,size);
        ItemInfoExample example = new ItemInfoExample();
        List<ItemInfo> list = this.mapper.selectByExample(example);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    @Override
    public Boolean insert(ItemInfo itemInfo) {
        Boolean flag = false;
        if(this.mapper.insert(itemInfo)>0){
            flag = true;
        }
        return flag;
    }

    @Override
    public Boolean update(ItemInfo itemInfo) {
        Boolean flag = false;
        if(this.mapper.updateByPrimaryKey(itemInfo)>0){
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
    public ItemInfo findById(String keyId) {
        return this.mapper.selectByPrimaryKey(keyId);
    }
}
