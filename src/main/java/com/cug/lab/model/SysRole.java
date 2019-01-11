package com.cug.lab.model;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SysRole  implements Serializable {

    private static final long serialVersionUID = 1782953758109023915L;

    private Long id; //编号
    private String role; //角色标识 程序中判断使用,如"admin"
    private String description; //角色描述,UI界面显示使用
    private String resourceIds; //拥有的资源
    private Boolean available = Boolean.FALSE; //是否可用,如果不可用将不会添加给用户



    public SysRole() {
    }

    public SysRole(String role, String description, Boolean available) {
        this.role = role;
        this.description = description;
        this.available = available;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getResourceIds() {
        return resourceIds;
    }

    public void setResourceIds(String resourceIds) {
        this.resourceIds = resourceIds;
    }

    /* 通过roleids字符串设置resourceIdList */
    public List<Long> getResourceIdList() {
        ArrayList<Long> resourceIdList = new ArrayList<Long>();
        if(StringUtils.isEmpty(resourceIds)) {

        }else{
            String[] arr = resourceIds.split(",");
            if(arr != null || arr.length > 0){
                for (int i = 0 ; i < arr.length ; i++){
                    if(StringUtils.isEmpty(arr[i])){

                    }else {
                        resourceIdList.add(Long.parseLong(arr[i]));
                    }
                }
            }
        }
        return resourceIdList;
    }

    /* 通过resourceIdList设置roleids字符串 */
    public void setResourceIdList(ArrayList<Long> resourceIdList) {
        StringBuilder s = new StringBuilder();

        for(Long resourceId : resourceIdList) {
            if(StringUtils.isEmpty(String.valueOf(resourceId))) {
                continue;
            }
            s.append(resourceId);
            s.append(",");
        }
        this.resourceIds =  s.toString();
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SysRole role = (SysRole) o;

        if (id != null ? !id.equals(role.id) : role.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", role='" + role + '\'' +
                ", description='" + description + '\'' +
                ", resourceIds=" + resourceIds +
                ", available=" + available +
                '}';
    }
}

