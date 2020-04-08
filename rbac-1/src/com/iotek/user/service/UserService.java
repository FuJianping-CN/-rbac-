package com.iotek.user.service;

import java.util.List;
import java.util.Map;

import com.iotek.user.po.Role;
import com.iotek.user.po.User;

public interface UserService {
	public List<User> queryAllUser();
    public User login(User user);
	public User queryUserById(Integer id);
	public List<Integer> queryRoleidsByUserid(Integer id);
	public void insertUserRoles(Map<String, Object> map);
	public void deleteUserRole(Map<String, Object> map);
	public void insertUserAuths(Map<String, Object> map);
	public void deleteUserAuths(Map<String, Object> map);
	public Integer insertUser(User user);
	public int deleteUserById(int userId);
	public int updateUserById(User user);
}
