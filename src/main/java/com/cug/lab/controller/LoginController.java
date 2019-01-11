package com.cug.lab.controller;


import com.cug.lab.Constants;
import com.cug.lab.model.SysResource;
import com.cug.lab.model.SysRole;
import com.cug.lab.model.SysUser;
import com.cug.lab.service.ResourceService;
import com.cug.lab.service.RoleService;
import com.cug.lab.service.UserService;
import com.cug.lab.web.bind.CurrentUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;


@Controller
public class LoginController {


    @Resource
    private  UserService userService;

    @Resource
    private  RoleService roleService;

    @Resource
    private ResourceService resourceService;

    @RequestMapping("/login")
    public String Login(HttpServletRequest servletRequest, Model model, SysUser user){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        String error = null;
        if(StringUtils.isEmpty(user.getUsername())){
            return "login";
        }
        try {
            subject.login(token);
        } catch (UnknownAccountException e) {
            error = "用户名错误！";
        } catch (IncorrectCredentialsException e) {
            error = "密码错误！";
        } catch (AuthenticationException e) {
            //其他错误，比如锁定，如果想单独处理请单独catch处理
            error = "发生了错误，请重试！";
        }
        if(error != null) {//出错了，返回登录页面
            model.addAttribute("error", error);
            return "login";
        } else {//登录成功
            servletRequest.setAttribute(Constants.CURRENT_USER, userService.findByUsername(user.getUsername()));
            Set<String> set = userService.findRoles(user.getUsername());
            System.out.println(set);
            List<SysRole> roleList = roleService.findListByName(set);
            Set<String> permissions = userService.findPermissions(user.getUsername());
            List<SysResource> menus = resourceService.findMenus(permissions);
            model.addAttribute("roles", roleList);
            model.addAttribute("menus", menus);
            return "homepage";
        }
    }




    @RequestMapping("/unauthorized")
    public static String unauthorized(){
        return "unauthorized";
    }



}
