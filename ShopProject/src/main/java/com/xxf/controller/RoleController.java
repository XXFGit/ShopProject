package com.xxf.controller;

import com.xxf.Utils.ResultUtil;
import com.xxf.Utils.UUIDUtils;
import com.xxf.model.AdminRoleResources;
import com.xxf.model.Role;
import com.xxf.service.AdminRoleResourceService;
import com.xxf.service.RoleService;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 功能描述: 角色管理
 * @Param:
 * @Return:
 * @Author: Administrator
 * @Date: 2020/4/9 下午 03:00
 */
@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;
    @Autowired
    private AdminRoleResourceService adminRoleResourceService;

    /**
     * 功能描述: 角色管理页面
     * @Param: []
     * @Return: java.lang.String
     * @Author: Administrator
     * @Date: 2020/4/9 下午 03:00
     */
    @RequestMapping("/index")
    public ModelAndView index(){
        ModelAndView model = new ModelAndView();
        model.setViewName("/premission/role");
        List<Role> list = this.roleService.getList();
        model.addObject("roleList",list);
        return model;
    }

    /**
     * 功能描述: 根据ID查找
     * @Param: []
     * @Return:
     * @Author: Administrator
     * @Date: 2020/4/10 上午 10:34
     */
    @RequestMapping(value = "findById.do")
    @ResponseBody
    public Map<String,Object> findById(String id){
        Map<String,Object> map = new HashMap<>();
        if(id!=null && !id.equals("")){
            map.put("role",this.roleService.findById(id));
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
    @RequiresPermissions("role:save")
    @ResponseBody
    public ResultUtil save(@RequestBody Role role){
        ResultUtil resultUtil=new ResultUtil();
        try{
            Boolean falg = SecurityUtils.getSubject().isPermitted("role:save");
            if(role.getId()!=null && !role.getId().equals("")){
                role.setUpdateTime(new Date());
                this.roleService.update(role);
                resultUtil.setCode(200);
            }else{
                role.setCreateTime(new Date());
                role.setUpdateTime( new Date(  ) );
                this.roleService.insert(role);
                resultUtil.setCode(200);
            }
        }catch (Exception e){
            resultUtil.setCode(500);
            e.printStackTrace();
        }

        return resultUtil;
    }

    /**
     * 功能描述: 删除角色
     * @Param: [keyId]
     * @Return: java.lang.String
     * @Author: Administrator
     * @Date: 2020/4/10 下午 05:45
     */
    @ResponseBody
    @RequestMapping(value = "del.do")
    public String delete(String keyId){
        if(StringUtils.isNotBlank(keyId)){
            this.roleService.delete(keyId);
        }
        return "success";
    }

    @ResponseBody
    @RequestMapping("/getResourceByRoleId")
    public List<AdminRoleResources> getResourceByRoleId(Integer roleId){
        List<AdminRoleResources> list = this.adminRoleResourceService.getRoleResourceByRoleId( roleId );
        return list;
    }

    @RequestMapping("/savePremission")
    @ResponseBody
    public String savePremission(String pres,String roleId){
        String resultMessage = "error";
        if(this.adminRoleResourceService.savePremission( pres.split( "," ),Integer.parseInt( roleId ) )){
            resultMessage = "success";
        }
        return resultMessage;

    }


}

