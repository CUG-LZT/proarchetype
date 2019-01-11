package com.cug.lab.controller;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    //@RequiresRoles("admin")
    @RequestMapping("/admin")
    public static String admin(){
        //   SecurityUtils.getSubject().checkRole("admin");
        return "admin";
    }

    //@RequiresRoles("user")
    @RequestMapping("/user")
    public static String user(){
        //  SecurityUtils.getSubject().checkPermission("user:view");
        return "user";
    }
}
