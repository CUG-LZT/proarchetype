package com.cug.lab.serviceImpl;

import com.cug.lab.dao.SysResourceMapper;
import com.cug.lab.model.SysResource;
import com.cug.lab.service.ResourceService;
import org.apache.shiro.authz.permission.WildcardPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-2-14
 * <p>Version: 1.0
 */
@Service

public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private SysResourceMapper resourceDao;

    @Override
    public SysResource createResource(SysResource resource) {
        return resourceDao.createResource(resource);
    }

    @Override
    public SysResource updateResource(SysResource resource) {
        return resourceDao.updateResource(resource);
    }

    @Override
    public void deleteResource(Long resourceId) {
        resourceDao.deleteResource(resourceId);
    }

    @Override
    public SysResource findOne(Long resourceId) {
        return resourceDao.findOne(resourceId);
    }

    @Override
    public List<SysResource> findAll() {
        return resourceDao.findAll();
    }

    @Override
    public Set<String> findPermissions(Set<Long> resourceIds) {
        Set<String> permissions = new HashSet<String>();
        for(Long resourceId : resourceIds) {
            SysResource resource = findOne(resourceId);
            if(resource != null && !StringUtils.isEmpty(resource.getPermission())) {
                permissions.add(resource.getPermission());
            }
        }
        return permissions;
    }

    /* 查询所有的资源权限 */
    @Override
    public Map<String , List<SysResource>> findMenus(Set<String> permissions) {
        Map<String , List<SysResource>> map = new HashMap<String , List<SysResource>>();
        List<SysResource> allResources = findAll();
        List<SysResource> menus = new ArrayList<SysResource>();
        List<SysResource> pages = new ArrayList<SysResource>();
        for(SysResource resource : allResources) {
            if(resource.isRootNode()) {
                continue;
            }
            if(resource.getType().equals(SysResource.ResourceType.button)) {
                continue;
            }
            if(resource.getType().equals(SysResource.ResourceType.page)) {
                if(!hasPermission(permissions, resource)) {
                    continue;
                }
                pages.add(resource);
            }
            if(resource.getType().equals(SysResource.ResourceType.menu)){
                if(!hasPermission(permissions, resource)) {
                    continue;
                }
                menus.add(resource);
            }

        }
        System.out.println(pages);
        map.put("menus",menus);
        map.put("pages",pages);
        return map;
    }

    //传过来的permissions就是用户获得的所有的
    private boolean hasPermission(Set<String> permissions, SysResource resource) {
        //为空返回真
        if(StringUtils.isEmpty(resource.getPermission())) {
            return true;
        }
        for(String permission : permissions) {
            WildcardPermission p1 = new WildcardPermission(permission);
            WildcardPermission p2 = new WildcardPermission(resource.getPermission());
            if(p1.implies(p2) || p2.implies(p1)) {
                return true;
            }
        }
        return false;
    }
}
