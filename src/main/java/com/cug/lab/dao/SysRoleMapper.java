package com.cug.lab.dao;

import com.cug.lab.model.SysRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

public interface SysRoleMapper {

    public SysRole createRole(SysRole role);
    public SysRole updateRole(SysRole role);
    public void deleteRole(Long roleId);

    public SysRole findOne(Long roleId);
    public List<SysRole> findListByName(@Param("roles") Set<String> set);
    public List<SysRole> findAll();
}
