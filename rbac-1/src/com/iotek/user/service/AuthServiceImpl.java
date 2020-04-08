package com.iotek.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iotek.user.dao.AuthDao;
import com.iotek.user.po.Auth;
import com.iotek.user.po.User;
@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
	private AuthDao authDao;
	public List<Auth> queryChildAuths(Integer id) {
		// TODO Auto-generated method stub
		return authDao.queryChildAuths(id);
	}
	public List<Auth> queryAllAuths() {
		// TODO Auto-generated method stub
		return  authDao.queryAllAuths();
	}
	public List<Auth> queryAuthByUser(User u) {
		// TODO Auto-generated method stub
		return authDao.queryAuthByUser(u);
	}
	@Override
	public int insertAuth(Auth auth) {
		// TODO Auto-generated method stub
		return this.authDao.insertAuth(auth);
	}
	@Override
	public int deleteAuthById(int authId) {
		// TODO Auto-generated method stub
		return this.authDao.deleteAuthById(authId);
	}
	@Override
	public Auth queryAuthById(int authId) {
		// TODO Auto-generated method stub
		return this.authDao.queryAuthById(authId);
	}
	@Override
	public int updateAuthById(Auth auth) {
		// TODO Auto-generated method stub
		return this.authDao.updateAuthById(auth);
	}
	@Override
	public List<Auth> queryAuthsByRoleId(Integer roleId) {
		// TODO Auto-generated method stub
		return this.authDao.queryAuthsByRoleId(roleId);
	}
	@Override
	public List<Auth> queryUnAuthsByRoleId(Integer roleId) {
		// TODO Auto-generated method stub
		return this.authDao.queryUnAuthsByRoleId(roleId);
	}
	@Override
	public int insertAuthForRole(Integer roleId, Integer authId) {
		// TODO Auto-generated method stub
		return this.authDao.insertAuthForRole(roleId, authId);
	}
	@Override
	public List<Auth> queryRootAuths() {
		// TODO Auto-generated method stub
		return this.authDao.queryRootAuths();
	}

}
