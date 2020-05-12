package com.xxf.controller;

import com.xxf.Utils.UUIDUtils;
import com.xxf.model.AdminInfo;
import com.xxf.service.AdminService;
import com.xxf.service.RoleService;
import com.xxf.shiro.UserRealm;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private RoleService roleService;

    /**
     * 功能描述: 列表
     * @Param: []
     * @Return: org.springframework.web.servlet.ModelAndView
     * @Author: Administrator
     * @Date: 2020/4/10 上午 09:48
     */
    @RequestMapping("/index")
    public ModelAndView index(){
        ModelAndView model = new ModelAndView();
        model.setViewName("/premission/admin");
        List<AdminInfo> list = this.adminService.getList();
        model.addObject("adminList",list);
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
            map.put("admin",this.adminService.findById(id));
        }
        map.put("roleList",this.roleService.getList());
        return map;
    }

    /**
     * 功能描述: 保存（新增保存，修改保存）
     * @Param: [adminInfo]
     * @Return: java.lang.String
     * @Author: Administrator
     * @Date: 2020/4/10 下午 04:35
     */
    @RequestMapping(value = "save.do")
    @ResponseBody
    public String save(@RequestBody AdminInfo adminInfo){
        String resultMessage = "error";
        if(adminInfo.getId()!=null && !adminInfo.getId().equals("")){
            adminInfo.setUpdateTime(new Date());
            this.adminService.update(adminInfo);
            resultMessage = "success";
        }else{
            adminInfo.setId(UUIDUtils.getUUid());
            adminInfo.setPassword("123456");
            adminInfo.setCreateTime(new Date());
            this.adminService.insert(adminInfo);
            resultMessage = "success";
        }
        return resultMessage;
    }

    @ResponseBody
    @RequestMapping(value = "del.do")
    public String delete(String keyId){
        if(StringUtils.isNotBlank(keyId)){
            this.adminService.delete(keyId);
        }
        return "success";
    }



}
