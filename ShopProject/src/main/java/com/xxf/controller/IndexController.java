package com.xxf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {


    @RequestMapping("/index")
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequestMapping("/form_elements")
    public ModelAndView form_elements(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("system_css/form_elements");
        modelAndView.addObject("active","form_elements");
        modelAndView.addObject("activeTitle","user");
        return modelAndView;
    }

    @RequestMapping("/table_basic")
    public ModelAndView table_basic(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("system_css/table_basic");
        modelAndView.addObject("active","table_basic");
        modelAndView.addObject("activeTitle","table_basic");
        return modelAndView;
    }

    @RequestMapping("/icon")
    public ModelAndView icon(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("system_css/icon");
        modelAndView.addObject("active","icon");
        modelAndView.addObject("activeTitle","xt_css");
        return modelAndView;
    }

    @RequestMapping("/button")
    public ModelAndView button(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("system_css/button");
        modelAndView.addObject("active","button");
        modelAndView.addObject("activeTitle","xt_css");
        return modelAndView;
    }

    @RequestMapping("/calendar")
    public ModelAndView calendar(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("system_css/calendar");
        modelAndView.addObject("active","calendar");
        modelAndView.addObject("activeTitle","calendar");
        return modelAndView;
    }

    @RequestMapping("/gallery")
    public ModelAndView gallery(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("system_css/gallery");
        modelAndView.addObject("active","gallery");
        modelAndView.addObject("activeTitle","gallery");
        return modelAndView;
    }

    @RequestMapping("/page_blank")
    public ModelAndView page_blank(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("system_css/page_blank");
        modelAndView.addObject("active","page_blank");
        modelAndView.addObject("activeTitle","page_blank");
        return modelAndView;
    }

    @RequestMapping("/extra_404_error")
    public ModelAndView extra_404_error(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("extra_404_error");
        modelAndView.addObject("active","extra_404_error");
        modelAndView.addObject("activeTitle","extra_404_error");
        return modelAndView;
    }

    @RequestMapping("/login")
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @RequestMapping("/list")
    public ModelAndView list(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        modelAndView.addObject("message", "这是我搭建的springmvc项目");
        return modelAndView;
    }





}
