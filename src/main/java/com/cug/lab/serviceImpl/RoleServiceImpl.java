package com.cug.lab.serviceImpl;

import com.cug.lab.dao.SysRoleMapper;
import com.cug.lab.model.SysRole;
import com.cug.lab.service.ResourceService;
import com.cug.lab.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;



@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private SysRoleMapper roleDao;
    @Autowired
    private ResourceService resourceService;

    public SysRole createRole(SysRole role) {
        return roleDao.createRole(role);
    }

    public SysRole updateRole(SysRole role) {
        return roleDao.updateRole(role);
    }

    public void deleteRole(Long roleId) {
        roleDao.deleteRole(roleId);
    }

    @Override
    public SysRole findOne(Long roleId) {
        return roleDao.findOne(roleId);
    }

    @Override
    public List<SysRole> findListByName(Set<String> set) {
        System.out.println("roleservice -> " + set);
        return roleDao.findListByName(set);
    }

    @Override
    public List<SysRole> findAll() {
        return roleDao.findAll();
    }

    /*通过在用户的信息中拿到的roleids 来通过id循环查询到所有的role信息*/
    @Override
    public Set<String> findRoles(Long... roleIds) {
        Set<String> roles = new HashSet<String>();
        for(Long roleId : roleIds) {
            SysRole role = findOne(roleId);
            if(role != null) {
                roles.add(role.getRole());
            }
        }
        return roles;
    }

    /* 通过在用户的信息中拿到的roleids 来通过id循环查询到所有的role信息 并将所有的资源id传给资源服务 */
    @Override
    public Set<String> findPermissions(Long[] roleIds) {
        Set<Long> resourceIds = new HashSet<Long>();
        for(Long roleId : roleIds) {
            SysRole role = findOne(roleId);
            if(role != null) {
                resourceIds.addAll(role.getResourceIdList());
            }
        }
        return resourceService.findPermissions(resourceIds);
    }
}
