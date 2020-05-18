package com.xxf.service;

import com.github.pagehelper.PageInfo;
import com.xxf.model.ItemType;

public interface ItemTypeService {

    PageInfo<ItemType> getList();

    Boolean insert(ItemType itemType);

    Boolean update(ItemType itemType);

    Boolean delete(String keyId);

    ItemType findById(String keyId);
}
