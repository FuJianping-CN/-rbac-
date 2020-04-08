package com.iotek.user.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iotek.user.dao.UserDao;
import com.iotek.user.po.User;
@Service(value="userService")
public class UserServiceImpl implements UserService {
	@Autowired
    private UserDao userDao;
  
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public List<User> queryAllUser() {
		// TODO Auto-generated method stub
		System.out.println("xxxxxxxxxxxxxxxxxxx"+userDao);
		return userDao.queryAllUser();
	}

	public User login(User user) {
		// TODO Auto-generated method stub
		return userDao.login(user);
	}

	public User queryUserById(Integer id) {
		// TODO Auto-generated method stub
		return userDao.queryUserById(id);
	}

	public List<Integer> queryRoleidsByUserid(Integer id) {
		// TODO Auto-generated method stub
		return userDao.queryRoleidsByUserid(id);
	}

	public void insertUserRoles(Map<String, Object> map) {
		// TODO Auto-generated method stub
		userDao.insertUserRoles(map);
	}

	public void deleteUserRole(Map<String, Object> map) {
		// TODO Auto-generated method stub
		userDao.deleteUserRole(map);
		
	}

	@Override
	public Integer insertUser(User user) {
		// TODO Auto-generated method stub
		return this.userDao.insertUser(user);
	}

	@Override
	public int deleteUserById(int userId) {
		// TODO Auto-generated method stub
		return this.userDao.deleteUserById(userId);
	}

	@Override
	public int updateUserById(User user) {
		// TODO Auto-generated method stub
		return this.userDao.updateUserById(user);
	}

	@Override
	public void insertUserAuths(Map<String, Object> map) {
		// TODO Auto-generated method stub
		this.userDao.insertUserAuths(map);
	}

	@Override
	public void deleteUserAuths(Map<String, Object> map) {
		// TODO Auto-generated method stub
		this.userDao.deleteUserAuths(map);
	}

}
