package com.iotek.user.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.iotek.user.po.AjaxResult;
import com.iotek.user.po.Auth;
import com.iotek.user.po.Role;
import com.iotek.user.po.User;
import com.iotek.user.service.AuthService;
import com.iotek.user.service.RoleService;
import com.iotek.user.service.UserService;

@Controller
@RequestMapping(value = "user")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private AuthService authService;

	@RequestMapping(value = "main")
	public String main() {
		return "welcomePage";
	}

	@ResponseBody
	@RequestMapping(value = "doAjaxLogin")
	public Object doAjaxLogin(@RequestParam(value = "pn", defaultValue = "1") Integer pn,
			User user, HttpSession session) {
		System.out.println("doAjaxLogin:" + user);
		AjaxResult result = new AjaxResult(); // ajax返回的对象
		User u = userService.login(user);
		System.out.println("u = " + u);
		if (u != null && "1".equals(u.getUserState())) {
			session.setAttribute("user", u);
			//获取用户的权限
			Auth root=null;
			List<Auth> auths=authService.queryAuthByUser(u); //传用户ID
			System.err.println("doAjaxLogin auths.size() ="+auths.size());
			System.err.println("auths+login ajax====="+auths);
			Map<Integer,Auth> authMap=new HashMap<Integer,Auth>();
			
			for(Auth auth:auths){
//				authMap.put(auth.getId(), auth);
				authMap.put(auth.getAuthId(), auth);
//				System.err.println("            "+auth.toString());
			}
			
			for(Auth auth:auths){
				Auth child=auth;
//				System.out.println("child.getAuthParentRoot()="+child.getAuthParentRoot());
				if(child.getAuthParentRoot()==0){
					root=auth;
				}else{
					Auth parent=authMap.get(child.getAuthParentRoot());
					parent.getChildren().add(child);
				}
			}
			System.err.println("doAjaxLogin -- root="+root);
			session.setAttribute("rootAuth", root);
			result.setSuccess(true);
			
			//将用户数据显示到表格中
			// 传入当前页码,以及页的大小;
			PageHelper.startPage(pn, 5);
			List<User> users = userService.queryAllUser();
//			System.out.println("users=" + users.size());
			// pageinfo包装查询后的结果,只需要将pageInfo交给页面就可以了
			// 封装了分页的信息,6表示连续显示的页数
			PageInfo page = new PageInfo(users, 6);
			session.setAttribute("pageInfo", page);
			
		} else {
			result.setSuccess(false);
		}
		return result;
	}

	/**
	 * 用户首页
	 */
	@RequestMapping(value = "index")
	public String index(
			@RequestParam(value = "pn", defaultValue = "1") Integer pn,
			Model model) {
		// 传入当前页码,以及页的大小;
		PageHelper.startPage(pn, 5);
		List<User> users = userService.queryAllUser();
		System.out.println("users=" + users.size());
		// pageinfo包装查询后的结果,只需要将pageInfo交给页面就可以了
		// 封装了分页的信息,6表示连续显示的页数
		PageInfo page = new PageInfo(users, 6);
		model.addAttribute("pageInfo", page);
		return "user/index";

	}

	@RequestMapping("/assign")
	public String assign(Integer id, Model model) {
		User user = userService.queryUserById(id);
		System.out.println("user=="+user+"*((((((((((((((((ssss");
		model.addAttribute("user", user);
		
		List<Role> roles = roleService.queryRoleAll();
		// 获取分配的角色与未分配的角色
		List<Role> assingedRoles = new ArrayList();
		List<Role> unassingedRoles = new ArrayList();

		List<Integer> roleids = userService.queryRoleidsByUserid(id);
		for (Role role : roles) {
			if (roleids.contains(role.getRoleId())) {
				assingedRoles.add(role);
			} else {
				unassingedRoles.add(role);
			}
		}
		System.out.println("assingedRoles=" + assingedRoles.size());
		System.out.println("unassingedRoles=" + unassingedRoles.size());
		model.addAttribute("assingedRoles", assingedRoles);
		model.addAttribute("unassingedRoles", unassingedRoles);
		return "user/assign";
	}

	/**
	 * 为用户分配角色
	 * 一次性可分配若干个角色
	 */
	@ResponseBody
	@RequestMapping(value = "doAssign")
	public Object doAssign(Integer userid, Integer[] unassignroleids) {
//		System.out.println("userId==========="+userid+"%%%%%%%%%%%");
		System.err.println("doAssign unassignroleids="+unassignroleids.toString());
		//
		List<Integer> unAssignRoleIdsInt = new ArrayList<>();
		for (Integer roleId : unassignroleids) {
			unAssignRoleIdsInt.add(roleId);
		}
		//通过若干个角色的id获取这些角色的全部不重复的权限id
//		List<Integer> authIds = this.roleService.queryAuthsByRoleIds(unAssignRoleIdsInt);
//		Map<String, Object> authIdsMap = new HashMap<String, Object>();
//		authIdsMap.put("userid", userid);
//		authIdsMap.put("authIds", authIds);
		//将权限Id添加到用户权限表中
//		this.userService.insertUserAuths(authIdsMap);
		
		AjaxResult result = new AjaxResult();
		try {

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userid", userid);
			map.put("roleids", unassignroleids);
			userService.insertUserRoles(map);
			result.setSuccess(true);
		} catch (Exception e) {
			// TODO: handle exception
			result.setSuccess(false);
		}
        return result;
	}
	
	/**
	 * 取消用户的角色
	 * @param userid
	 * @param assingroleids
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "dounAssign")
	public Object dounAssign(Integer userid, Integer[] assingroleids) {
		System.out.println("dounAssign assingroleids"+assingroleids.toString());
		AjaxResult result = new AjaxResult();
		try {
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userid", userid);
			map.put("roleids", assingroleids);
			userService.deleteUserRole(map);
			result.setSuccess(true);
		} catch (Exception e) {
			// TODO: handle exception
			result.setSuccess(false);
		}
        return result;
	}
	
	@RequestMapping("/toAssignAuthPage")
	public String toAssignAuthPage(Integer userId, Model model) {
		return "";
	}
	
	@ResponseBody
	@RequestMapping("/assingAuth")
	public Object assingAuth(Integer userId) {
		AjaxResult ajaxResult = new AjaxResult();
		return ajaxResult;
	}
	
	@RequestMapping("/toAddUser")
	public String toAddUser() {
		return "user/addUser";
	}
	
	
	@RequestMapping(value = "addUser")
	public String addUser(User user, HttpSession session) {
		System.out.println("addUser...");
		User creator = (User) session.getAttribute("user");
		user.setCreator(creator.getUserId());
		user.setCreateDate(new Date());
		System.out.println("addUser: user = " + user.toString());
		int userId = this.userService.insertUser(user);
		System.err.println("---------------------------------------------");
		System.err.println("----------------addUser  userId = " + user.getUserId());
		return "redirect:index";
	}
	
	@ResponseBody
	@RequestMapping(value = "deleteUserById")
	public Object deleteUserById(String userId) {
		System.out.println("deleteUserById userId = " + userId);
		int deleteResult = this.userService.deleteUserById(Integer.valueOf(userId));
		System.out.println("deleteUserById deleteResult = " + deleteResult);
		AjaxResult result = new AjaxResult();
		if (deleteResult > 0) {
			result.setSuccess(true);
		} else {
			result.setSuccess(false);
		}
		return result;
	}
	
	
	@RequestMapping("/toEditUser")
	public String toEditUser(Integer userId, Model model) {
		User user = userService.queryUserById(userId);
		System.out.println("user=="+user);
		model.addAttribute("user", user);
		return "user/editUser";
	}
	
	@ResponseBody
	@RequestMapping("/editUser")
	public Object editUser(User user, HttpSession session) {
		User updater = (User) session.getAttribute("user");
		user.setUpdater(updater.getUserId());
		user.setUpdateDate(new Date());
		System.out.println("editUser user = " + user.toString());
		int updateResult = this.userService.updateUserById(user);
		System.out.println("editUser updateResult = " + updateResult);
		AjaxResult result = new AjaxResult();
		if (updateResult > 0) {
			result.setSuccess(true);
		} else {
			result.setSuccess(false);
		}
		return result;
	}

}

/**
 * 用户权限问题：
 * 1.给用户添加角色，用户角色表、用户权限表，要把该角色的权限全部赋给该用户
 * 	角色可能不同，但是有相同的权限，相同的权限只能存储一个
 * 2.给用户删除角色，要删除该角色对应的全部权限，但是不能删除该用户的角色的相同的角色
 * 3.给用户授权限，要判断该权限是否存在，
 */
