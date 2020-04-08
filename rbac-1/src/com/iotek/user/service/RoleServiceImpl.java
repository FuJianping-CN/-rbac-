package com.iotek.user.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iotek.user.dao.RoleDao;
import com.iotek.user.po.Auth;
import com.iotek.user.po.Role;

@Service
public class RoleServiceImpl implements RoleService {
     @Autowired
    private RoleDao roleDao;
    
	public List<Role> queryRoleAll() {
		// TODO Auto-generated method stub
		return roleDao.queryRoleAll();
	}

	@Override
	public int insertRole(Role role) {
		// TODO Auto-generated method stub
		return this.roleDao.insertRole(role);
	}

	@Override
	public int deleteRoleById(int roleId) {
		// TODO Auto-generated method stub
		return this.roleDao.deleteRoleById(roleId);
	}

	@Override
	public Role queryRoleById(int roleId) {
		// TODO Auto-generated method stub
		return this.roleDao.queryRoleById(roleId);
	}

	@Override
	public int updateRoleById(Role role) {
		// TODO Auto-generated method stub
		return this.roleDao.updateRoleById(role);
	}

	@Override
	public void addAuthsForRoleByRoleId(Map<String, Object> map) {
		// TODO Auto-generated method stub
		this.roleDao.addAuthsForRoleByRoleId(map);
	}

	@Override
	public void deleteAuthsForRoleByRoleId(Map<String, Object> map) {
		// TODO Auto-generated method stub
		this.roleDao.deleteAuthsForRoleByRoleId(map);
	}

	@Override
	public List<Integer> queryAuthsByRoleIds(List<Integer> roleIds) {
		// TODO Auto-generated method stub
		return this.roleDao.queryAuthsByRoleIds(roleIds);
	}

	@Override
	public void addRootAuthForRoles(Map<String, Object> map) {
		// TODO Auto-generated method stub
		this.roleDao.addRootAuthForRoles(map);
	}


}
