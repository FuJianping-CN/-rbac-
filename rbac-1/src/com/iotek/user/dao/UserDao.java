package com.iotek.user.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.iotek.user.po.User;
@Repository
public interface UserDao {
      public List<User> queryAllUser();
      public User login(User user);
  	  public User queryUserById(Integer id);
  	  @Select("SELECT roleid FROM tb_user_role WHERE userid=#{userid}")
   	  public List<Integer> queryRoleidsByUserid(Integer id);
  	
  	  public void insertUserRoles(Map<String, Object> map);
  	  public void deleteUserRole(Map<String, Object> map);
  	  public void insertUserAuths(Map<String, Object> map);
	  public void deleteUserAuths(Map<String, Object> map);
  	  
  	  public Integer insertUser(User user);
  	  public int deleteUserById(int userId);
  	  public int updateUserById(User user);
}
