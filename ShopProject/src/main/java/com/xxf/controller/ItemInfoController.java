package com.xxf.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xxf.Utils.SearchCondition;
import com.xxf.Utils.UUIDUtils;
import com.xxf.model.ItemInfo;
import com.xxf.model.ItemType;
import com.xxf.service.ItemInfoService;
import com.xxf.service.ItemTypeService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("item")
public class ItemInfoController {

    @Autowired
    private ItemInfoService itemInfoService;
    @Autowired
    private ItemTypeService itemTypeService;

    private SearchCondition con;

    @RequestMapping("index")
    @ResponseBody
    public ModelAndView index( @RequestParam(required = true, defaultValue = "1") Integer pageNum,
                               @RequestParam(required = true, defaultValue = "10") Integer pageSize){
        ModelAndView model = new ModelAndView();
        model.setViewName("/item/item");
        PageHelper.startPage(pageNum,pageSize);
        //分页查询
        PageInfo<ItemInfo> pageInfo = this.itemInfoService.getList(con,pageNum,pageSize);
        model.addObject("itemList",pageInfo.getList());
        model.addObject("pageInfo",pageInfo);
        return model;
    }

    @RequestMapping("toAddOrEditItem")
    public ModelAndView toAdd(String id){
        ModelAndView model = new ModelAndView();
        model.setViewName("/item/addOrEditItem");
        PageInfo<ItemType> pageInfo = this.itemTypeService.getList();
        model.addObject("typeList",pageInfo.getList());
        model.addObject("id",id);
        return model;
    }


    @RequestMapping("save.do")
    @ResponseBody
    public  String saveItem(@RequestBody ItemInfo item){
        String resultMessage = "error";
        if(item!=null){
            if(StringUtils.isNotBlank(item.getId())){
                item.setUpdateTime(new Date());
                this.itemInfoService.update(item);
                resultMessage = "success";
            }else{
                item.setId(UUIDUtils.getUUid());
                item.setUpdateTime(new Date());
                item.setCreateTime(new Date());
                item.setViewCount(0);
                item.setBuyCount(0);
                this.itemInfoService.insert(item);
                resultMessage = "success";
            }
        }
        return resultMessage;
    }


    /**
     * 功能描述: 根据ID查找
     * @Param: []
     * @Return: com.xxf.model.AdminInfo
     * @Author: Administrator
     * @Date: 2020/4/10 上午 10:34
     */
    @RequestMapping(value = "findById.do")
    @ResponseBody
    public Map<String,Object> findById(String id){
        Map<String,Object> map = new HashMap<>();
        if(id!=null && !id.equals("")){
            map.put("item",this.itemInfoService.findById(id));
        }
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "del.do")
    public String delete(String keyId){
        if(StringUtils.isNotBlank(keyId)){
            this.itemInfoService.delete(keyId);
        }
        return "success";
    }
}
