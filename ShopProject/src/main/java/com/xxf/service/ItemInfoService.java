package com.xxf.service;

import com.github.pagehelper.PageInfo;
import com.xxf.Utils.SearchCondition;
import com.xxf.model.ItemInfo;

public interface ItemInfoService {

    PageInfo<ItemInfo> getList(SearchCondition con, int page, int size);

    Boolean insert(ItemInfo itemInfo);

    Boolean update(ItemInfo itemInfo);

    Boolean delete(String keyId);

    ItemInfo findById(String keyId);

}
