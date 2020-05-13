package com.xxf.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xxf.model.AddressInfo;
import com.xxf.service.AddressService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    /**
     * 功能描述: 列表
     * @Param: []
     * @Return: org.springframework.web.servlet.ModelAndView
     * @Author: Administrator
     * @Date: 2020/4/10 上午 09:48
     */
    @RequestMapping("/index")
    public ModelAndView index(
            @RequestParam(required = true, defaultValue = "1") Integer pageNum,
            @RequestParam(required = true, defaultValue = "1") Integer pageSize){
        ModelAndView model = new ModelAndView();
        model.setViewName("user/user_address");
        PageHelper.startPage(pageNum,pageSize);
        PageInfo<AddressInfo> pageInfo = this.addressService.getList();
        model.addObject("addressList",pageInfo.getList());
        model.addObject("pageInfo",pageInfo);
        return model;
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
            map.put("address",this.addressService.findById(id));
        }
        map.put("addressList",this.addressService.getList());
        return map;
    }


    @ResponseBody
    @RequestMapping(value = "del.do")
    public String delete(String keyId){
        if(StringUtils.isNotBlank(keyId)){
            this.addressService.delete(keyId);
        }
        return "success";
    }

}
