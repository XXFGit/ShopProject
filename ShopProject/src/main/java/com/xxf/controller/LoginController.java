package com.xxf.controller;

import com.xxf.Utils.ShiroUtils;
import com.xxf.model.AdminInfo;
import com.xxf.service.AdminService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * 功能描述:  后台登陆
 * @Param:
 * @Return:
 * @Author: Administrator
 * @Date: 2020/4/9 下午 05:57
 */
@Controller
public class LoginController {

    @Autowired
    private AdminService adminService;

    /**
     * 功能描述: 执行登陆验证
     * @Param: [adminInfo]
     * @Return: java.lang.String
     * @Author: Administrator
     * @Date: 2020/4/10 上午 09:37
     */
    @RequestMapping("/dologin")
    @ResponseBody
    public String login(@RequestBody AdminInfo adminInfo, HttpSession session){
        String resuleMesage = "error";
        if (adminInfo != null ) {
            //初始化
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(adminInfo.getUserName(), adminInfo.getPassword());
            try {
                //登录，即身份校验，由通过Spring注入的UserRealm会自动校验输入的用户名和密码在数据库中是否有对应的值
                subject.login(token);
                resuleMesage = "success";
                return resuleMesage;
                //return "redirect:index.do";
            }catch (Exception e){
                e.printStackTrace();
                resuleMesage = "未知错误，错误信息：" + e.getMessage();
            }
        } else {
            resuleMesage = "请输入用户名和密码";
        }
        return resuleMesage;
    }

    @RequestMapping(value="/loginOut")
    public String loginOut(){
        ShiroUtils.logout();
        return "redirect:/login.do";
    }

}
