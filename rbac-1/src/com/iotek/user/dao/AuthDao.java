package com.iotek.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.iotek.user.po.Auth;
import com.iotek.user.po.Role;
import com.iotek.user.po.User;

public interface AuthDao {
	 //���ݸ��ڵ�������ӽڵ��ѯ����
    public List<Auth> queryChildAuths(Integer id);

	public List<Auth> queryAllAuths();

	public List<Auth> queryAuthByUser(User u);
	
	int insertAuth(Auth auth);
	int deleteAuthById(int authId);
	Auth queryAuthById(int authId);
	int updateAuthById(Auth auth);
	
	List<Auth> queryAuthsByRoleId(Integer roleId);
	List<Auth> queryUnAuthsByRoleId(Integer roleId);
	List<Auth> queryRootAuths();
	int insertAuthForRole(@Param("roleId")Integer roleId, @Param("authId")Integer authId);
}
