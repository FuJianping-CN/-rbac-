package com.iotek.user.service;

import java.util.List;

import com.iotek.user.po.Auth;
import com.iotek.user.po.User;

public interface AuthService {
	 //根据父节点把所有子节点查询出来
     public List<Auth> queryChildAuths(Integer id);

	public List<Auth> queryAllAuths();

	public List<Auth> queryAuthByUser(User u);
	
	public int insertAuth(Auth auth);
	public int deleteAuthById(int authId);
	public Auth queryAuthById(int authId);
	public int updateAuthById(Auth auth);
	
	List<Auth> queryAuthsByRoleId(Integer roleId);
	List<Auth> queryUnAuthsByRoleId(Integer roleId);
	List<Auth> queryRootAuths();
	int insertAuthForRole(Integer roleId, Integer authId);
}
