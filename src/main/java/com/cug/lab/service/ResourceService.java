package com.cug.lab.service;

import com.cug.lab.model.SysResource;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */
public interface ResourceService {


    public SysResource createResource(SysResource resource);
    public SysResource updateResource(SysResource resource);
    public void deleteResource(Long resourceId);

    SysResource findOne(Long resourceId);
    List<SysResource> findAll();

    /**
     * 得到资源对应的权限字符串
     * @param resourceIds
     * @return
     */
    Set<String> findPermissions(Set<Long> resourceIds);

    /**
     * 根据用户权限得到菜单
     * @param permissions
     * @return
     */
    Map<String , List<SysResource>> findMenus(Set<String> permissions);
}
