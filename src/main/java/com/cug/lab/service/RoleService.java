package com.cug.lab.service;

import com.cug.lab.model.SysRole;
import java.util.List;
import java.util.Set;

/**

* @Description:
* @Author:         lzt
* @CreateDate:     2019/1/11
* @Version:        1.0
*/
public interface RoleService {


    public SysRole createRole(SysRole role);
    public SysRole updateRole(SysRole role);
    public void deleteRole(Long roleId);

    public SysRole findOne(Long roleId);
    public List<SysRole> findListByName(Set<String> set);
    public List<SysRole> findAll();

    /**
     * 根据角色编号得到角色标识符列表
     * @param roleIds
     * @return
     */
    Set<String> findRoles(Long... roleIds);

    /**
     * 根据角色编号得到权限字符串列表
     * @param roleIds
     * @return
     */
    Set<String> findPermissions(Long[] roleIds);
}
