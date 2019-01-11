package com.cug.lab.dao;

import com.cug.lab.model.SysUser;

import java.util.List;

public interface SysUserMapper {
    public SysUser createUser(SysUser user);
    public SysUser updateUser(SysUser user);
    public void deleteUser(Long userId);

    SysUser findOne(Long userId);

    List<SysUser> findAll();

    SysUser findByUsername(String username);
}
