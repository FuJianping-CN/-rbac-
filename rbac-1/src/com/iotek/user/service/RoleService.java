package com.iotek.user.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.iotek.user.po.Role;

public interface RoleService {

	List<Role> queryRoleAll();
	int insertRole(Role role);
	int deleteRoleById(int roleId);
	Role queryRoleById(int roleId);
	int updateRoleById(Role role);
	
	void addAuthsForRoleByRoleId(Map<String, Object> map);
	void addRootAuthForRoles(Map<String, Object> map);
	void deleteAuthsForRoleByRoleId(Map<String, Object> map);
	List<Integer> queryAuthsByRoleIds(@Param("roleIds")List<Integer> roleIds);
}
