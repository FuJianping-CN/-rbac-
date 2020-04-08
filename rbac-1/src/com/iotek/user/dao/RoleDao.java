package com.iotek.user.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.iotek.user.po.Role;

public interface RoleDao {
	List<Role> queryRoleAll();
	int insertRole(Role role);
	int deleteRoleById(Integer roleId);
	Role queryRoleById(Integer roleId);
	int updateRoleById(Role role);
	
	void addAuthsForRoleByRoleId(Map<String, Object> map);
	void addRootAuthForRoles(Map<String, Object> map);
	void deleteAuthsForRoleByRoleId(Map<String, Object> map);
	List<Integer> queryAuthsByRoleIds(@Param("roleIds")List<Integer> roleIds);
}
