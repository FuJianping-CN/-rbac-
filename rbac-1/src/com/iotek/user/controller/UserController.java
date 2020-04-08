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
		AjaxResult result = new AjaxResult(); // ajax���صĶ���
		User u = userService.login(user);
		System.out.println("u = " + u);
		if (u != null && "1".equals(u.getUserState())) {
			session.setAttribute("user", u);
			//��ȡ�û���Ȩ��
			Auth root=null;
			List<Auth> auths=authService.queryAuthByUser(u); //���û�ID
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
			
			//���û�������ʾ�������
			// ���뵱ǰҳ��,�Լ�ҳ�Ĵ�С;
			PageHelper.startPage(pn, 5);
			List<User> users = userService.queryAllUser();
//			System.out.println("users=" + users.size());
			// pageinfo��װ��ѯ��Ľ��,ֻ��Ҫ��pageInfo����ҳ��Ϳ�����
			// ��װ�˷�ҳ����Ϣ,6��ʾ������ʾ��ҳ��
			PageInfo page = new PageInfo(users, 6);
			session.setAttribute("pageInfo", page);
			
		} else {
			result.setSuccess(false);
		}
		return result;
	}

	/**
	 * �û���ҳ
	 */
	@RequestMapping(value = "index")
	public String index(
			@RequestParam(value = "pn", defaultValue = "1") Integer pn,
			Model model) {
		// ���뵱ǰҳ��,�Լ�ҳ�Ĵ�С;
		PageHelper.startPage(pn, 5);
		List<User> users = userService.queryAllUser();
		System.out.println("users=" + users.size());
		// pageinfo��װ��ѯ��Ľ��,ֻ��Ҫ��pageInfo����ҳ��Ϳ�����
		// ��װ�˷�ҳ����Ϣ,6��ʾ������ʾ��ҳ��
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
		// ��ȡ����Ľ�ɫ��δ����Ľ�ɫ
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
	 * Ϊ�û������ɫ
	 * һ���Կɷ������ɸ���ɫ
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
		//ͨ�����ɸ���ɫ��id��ȡ��Щ��ɫ��ȫ�����ظ���Ȩ��id
//		List<Integer> authIds = this.roleService.queryAuthsByRoleIds(unAssignRoleIdsInt);
//		Map<String, Object> authIdsMap = new HashMap<String, Object>();
//		authIdsMap.put("userid", userid);
//		authIdsMap.put("authIds", authIds);
		//��Ȩ��Id��ӵ��û�Ȩ�ޱ���
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
	 * ȡ���û��Ľ�ɫ
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
 * �û�Ȩ�����⣺
 * 1.���û���ӽ�ɫ���û���ɫ���û�Ȩ�ޱ�Ҫ�Ѹý�ɫ��Ȩ��ȫ���������û�
 * 	��ɫ���ܲ�ͬ����������ͬ��Ȩ�ޣ���ͬ��Ȩ��ֻ�ܴ洢һ��
 * 2.���û�ɾ����ɫ��Ҫɾ���ý�ɫ��Ӧ��ȫ��Ȩ�ޣ����ǲ���ɾ�����û��Ľ�ɫ����ͬ�Ľ�ɫ
 * 3.���û���Ȩ�ޣ�Ҫ�жϸ�Ȩ���Ƿ���ڣ�
 */
