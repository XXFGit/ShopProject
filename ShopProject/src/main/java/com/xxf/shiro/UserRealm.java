package com.xxf.shiro;

import com.xxf.model.AdminInfo;
import com.xxf.service.AdminResouceService;
import com.xxf.service.AdminService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class UserRealm  extends AuthorizingRealm {

    @Autowired
    private AdminService adminService;
    @Autowired
    private AdminResouceService adminResouceService;

    /**
     * 功能描述: 授权
     * @Param: [principalCollection]
     * @Return: org.apache.shiro.authz.AuthorizationInfo
     * @Author: Administrator
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        AdminInfo adminInfo = (AdminInfo) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        //用户
        AdminInfo info = this.adminService.findByName(adminInfo.getUserName());
        if(info!=null){
            //根据用户获取用户的角色
            Set<String> sroles = new HashSet<>();
            sroles.add(String.valueOf(info.getRoleId()));
            //赋值角色给simpleAuthorizationInfo
            simpleAuthorizationInfo.setRoles(sroles);
        }
        //根据用户查询用户的权限
        Set<String> spermit = this.adminResouceService.selpremission(Integer.parseInt(info.getRoleId()));
        System.out.println(spermit.toString()+"--------------------------------");
        //授权
        simpleAuthorizationInfo.setStringPermissions(spermit);
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("身份校验--执行了goGetAuthenticationInfo...");
        //拿到用户名
        String username = (String) token.getPrincipal();
        //根据用户名数据库中找到admin
        AdminInfo user = adminService.findByName(username);
        if (user == null) {
            throw new UnknownAccountException(); //没有找到账号
        }
        //交给AuthenticationRealm使用CredentialsMatcher进行密码匹配
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user, //这里要传user，否则后面想拿到登录的用户信息，拿不到而报错
                user.getPassword(), //密码
                getName() //realm name
        );

        return authenticationInfo;
    }
}
