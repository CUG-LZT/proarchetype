package com.cug.lab.model;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SysUser implements Serializable {

	private static final long serialVersionUID = 6085691637946845003L;

	private Long id; //编号
	private Long organizationId; //所属公司
	private String username; //用户名
	private String password; //密码
	private String salt; //加密密码的盐
	private String roleIds; //拥有的角色列表
	private Boolean locked = Boolean.FALSE;

	/* 构造函数 */
	public SysUser() {
	}

	public SysUser(String username, String password) {
		this.username = username;
		this.password = password;
	}



	/* get set */
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	/* get set */
	public Long getOrganizationId() {
		return organizationId;
	}
	/* get set */
	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public String getUsername() {
		return username;
	}
	/* get set */
	public void setUsername(String username) {
		this.username = username;
	}
	/* get set */
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	/* get set */
	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	/*get CredentialsSalt*/
	public String getCredentialsSalt() {
		return username + salt;
	}

	/* get set */
	public String getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}

	/* 通过roleids字符串获取到list */
	public List<Long> getRoleIdList() {
		List<Long> roleIdList = new ArrayList<Long>();
		if(roleIds == null) {

		}else {
			String[] arr = roleIds.split(",");

			if(arr != null || arr.length > 0){
				for (int i = 0 ; i < arr.length ; i++){
					if(StringUtils.isEmpty(arr[i])){

					}else{
						roleIdList.add(Long.parseLong(arr[i]));
					}
				}
			}
		}
		return roleIdList;
	}

	/* 通过list设置roleids字符串 */
	public void setRoleIdList(List<Long> roleIdList) {
		StringBuilder s = new StringBuilder();
		for(Long roleId : roleIdList) {
			if(StringUtils.isEmpty(String.valueOf(roleId))) {
				continue;
			}
			s.append(roleId);
			s.append(",");
		}
		this.roleIds =  s.toString();
	}


	public Boolean getLocked() {
		return locked;
	}

	public void setLocked(Boolean locked) {
		this.locked = locked;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		SysUser user = (SysUser) o;

		if (id != null ? !id.equals(user.id) : user.id != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		return id != null ? id.hashCode() : 0;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", organizationId=" + organizationId +
				", username='" + username + '\'' +
				", password='" + password + '\'' +
				", salt='" + salt + '\'' +
				", roleIds=" + roleIds +
				", locked=" + locked +
				'}';
	}
}

