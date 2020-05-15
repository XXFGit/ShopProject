package com.xxf.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xxf.model.Role;
import com.xxf.model.UserInfo;
import com.xxf.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping("/index")
    public ModelAndView index(@RequestParam(required = true, defaultValue = "1") Integer pageNum,
                              @RequestParam(required = true, defaultValue = "10") Integer pageSize){
        ModelAndView model = new ModelAndView();
        PageHelper.startPage(pageNum,pageSize);
        PageInfo<UserInfo> pageInfo  = this.userInfoService.findAll();
        model.setViewName("user/user");
        model.addObject("userList",pageInfo.getList());
        model.addObject("pageInfo",pageInfo);
        return model;
    }



}
