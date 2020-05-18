package com.xxf.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xxf.Utils.UUIDUtils;
import com.xxf.model.ItemType;
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
import java.util.Map;

@Controller
@RequestMapping("/type")
public class ItemTypeController {

    @Autowired
    private ItemTypeService itemTypeService;

    @RequestMapping("/index")
    public ModelAndView index(
            @RequestParam(required = true, defaultValue = "1") Integer pageNum,
            @RequestParam(required = true, defaultValue = "10") Integer pageSize){
        ModelAndView model = new ModelAndView();
        model.setViewName("/item/item_type");
        PageHelper.startPage(pageNum,pageSize);
        PageInfo<ItemType> pageInfo = this.itemTypeService.getList();
        model.addObject("typeList",pageInfo.getList());
        model.addObject("pageInfo",pageInfo);
        return model;
    }

    @RequestMapping("save")
    @ResponseBody
    public String save(@RequestBody ItemType type){
        String resultMessage = "error";
        if(type!=null){
            //判断有没有父id，没有默认为0（顶级菜单）
            if(!StringUtils.isNotBlank(type.getParentId())){
                type.setParentId( "0" );
                type.setParentName("根节点");
            }
            if(StringUtils.isNotBlank(type.getId())){
                type.setCreateTime(new Date());
                type.setUpdateTime(new Date());
                this.itemTypeService.update(type);
                resultMessage = "success";
            }else{
                type.setId(UUIDUtils.getUUid());
                type.setSortOrder(0);
                type.setCreateTime(new Date());
                type.setUpdateTime(new Date());
                this.itemTypeService.insert(type);
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
            map.put("type",this.itemTypeService.findById(id));
        }
       // map.put("roleList",this.itemTypeService.getList());
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "del.do")
    public String delete(String keyId){
        if(StringUtils.isNotBlank(keyId)){
            this.itemTypeService.delete(keyId);
        }
        return "success";
    }


}
