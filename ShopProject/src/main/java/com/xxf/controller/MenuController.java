package com.xxf.controller;

import com.xxf.Utils.ShiroUtils;
import com.xxf.model.AdminInfo;
import com.xxf.model.AdminResources;
import com.xxf.service.AdminResouceService;
import com.xxf.service.AdminRoleResourceService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * Created by 熊显付 on 2020/4/10.
 */
@Controller
@RequestMapping("menu")
public class MenuController {

    @Autowired
    private AdminResouceService adminResouceService;

    @RequestMapping(value="index")
    public ModelAndView index(){
        ModelAndView model = new ModelAndView(  );
        List<AdminResources> list = this.adminResouceService.getList();
        model.addObject( "resourceList" ,list);
        model.setViewName( "/premission/resource");
        return model;
    }

    @RequestMapping("list")
    @ResponseBody
    public List<AdminResources> list(){
        List<AdminResources> list = this.adminResouceService.getList();
        return list;
    }

    @RequestMapping("save")
    @ResponseBody
    public String save(@RequestBody AdminResources resources){
        String resultMessage = "error";
        if(resources!=null){
            //判断有没有父id，没有默认为0（顶级菜单）
            if(resources.getPid()==null){
                resources.setPid( 0 );
            }
            //判断是新增还是修改
            if(resources.getId()!=null){
                resources.setUpdateTime( new Date(  ) );
                this.adminResouceService.update( resources );
                resultMessage = "success";
            }else{
                resources.setCreateTime( new Date(  ) );
                resources.setUpdateTime( new Date(  ) );
                this.adminResouceService.insert( resources );
                resultMessage = "success";
            }
        }
        return  resultMessage;
    }

    @ResponseBody
    @RequestMapping("findById")
    public AdminResources findById(int id){
        return this.adminResouceService.findById( id );
    }

    @RequestMapping("del")
    @ResponseBody
    public String delete(int id){
       this.adminResouceService.delete( id );
       return "success";
    }

    /**
     * 功能描述: 根据用户角色权限，查改用户的菜单（左侧菜单）
     * @Param: [session]
     * @Return: java.util.List<com.xxf.model.AdminResources>
     * @Author: Administrator
     * @Date: 2020/4/16 下午 03:12
     */
    @RequestMapping("selMenu")
    @ResponseBody
    public List<AdminResources> selMenu(HttpSession session){
        AdminInfo admin = ShiroUtils.getEntity();
        List<AdminResources> menuList = this.adminResouceService.selMenu(Integer.parseInt(admin.getRoleId()));
        return menuList;
    }


}
