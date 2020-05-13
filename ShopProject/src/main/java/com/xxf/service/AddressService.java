package com.xxf.service;

import com.github.pagehelper.PageInfo;
import com.xxf.model.AddressInfo;


public interface AddressService {

    PageInfo<AddressInfo> getList();

    Boolean insert(AddressInfo addressInfo);

    Boolean update(AddressInfo addressInfo);

    Boolean delete(String keyId);

    AddressInfo findById(String keyId);

    AddressInfo findByUserId(String keyId);


}
