package com.xxf.controller;

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
    public ModelAndView index(){
        ModelAndView model = new ModelAndView();
        List<UserInfo> userList = this.userInfoService.findAll();
        model.setViewName("user/user");
        model.addObject("userList",userList);
        return model;
    }



}
