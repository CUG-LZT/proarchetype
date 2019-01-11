package com.cug.lab.credentials;

import com.cug.lab.model.SysUser;
import com.cug.lab.service.UserService;
import com.cug.lab.utils.PasswordHelper;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.atomic.AtomicInteger;

/**

* @Description:    自定义密码验证器
* @Author:         lzt
* @CreateDate:     2019/1/11
* @Version:        1.0
*/
public class RetryLimitHashedCredentialsMatcher extends HashedCredentialsMatcher {

    @Autowired
    private PasswordHelper passwordHelper;

    @Autowired
    private UserService userService;

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        String username = new String(usernamePasswordToken.getUsername());
        String password = new String(usernamePasswordToken.getPassword());
        String dbPassword = (String) info.getCredentials();
        //根据用户名获取到的密码
        SysUser user = userService.findByUsername(username);
        user.setPassword(password);
        passwordHelper.decryptPassword(user);
        return this.equals(user.getPassword(), dbPassword);

    }
}
