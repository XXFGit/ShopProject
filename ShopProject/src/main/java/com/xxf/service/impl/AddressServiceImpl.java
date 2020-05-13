package com.xxf.service.impl;

import com.github.pagehelper.PageInfo;
import com.xxf.dao.AddressInfoMapper;
import com.xxf.model.AddressInfo;
import com.xxf.model.AddressInfoExample;
import com.xxf.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("addressService")
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressInfoMapper mapper;

    @Override
    public PageInfo<AddressInfo> getList() {
        AddressInfoExample example = new AddressInfoExample();
        List<AddressInfo> list = this.mapper.selectByExample(example);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    @Override
    public Boolean insert(AddressInfo addressInfo) {
        Boolean flag = false;
        if(this.mapper.insert(addressInfo)>0){
            flag = true;
        }
        return flag;
    }

    @Override
    public Boolean update(AddressInfo addressInfo) {
        Boolean flag = false;
        if(this.mapper.updateByPrimaryKey(addressInfo)>0){
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
    public AddressInfo findById(String keyId) {
        return this.mapper.selectByPrimaryKey(keyId);
    }

    @Override
    public AddressInfo findByUserId(String keyId) {
        AddressInfoExample example = new AddressInfoExample();
        example.createCriteria().andUserIdEqualTo(keyId);
        List<AddressInfo> list = this.mapper.selectByExample(example);
        if(list!=null && list.size()>0){
            return list.get(0);
        }
        return null;
    }
}
