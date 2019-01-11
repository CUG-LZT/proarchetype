package com.cug.lab.filter;

import com.cug.lab.Constants;
import com.cug.lab.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**

* @Description:    自定义用户过滤器，可具体实现其功能
 *                 登录之后的每次跳转 ， 都会把登录的用户信息写在session中
* @Author:         lzt
* @CreateDate:     2019/1/11
* @Version:        1.0
*/
public class SysUserFilter extends PathMatchingFilter {

    @Autowired
    private UserService userService;

    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        request.setAttribute(Constants.CURRENT_USER, userService.findByUsername(username));
        return true;
    }
}
