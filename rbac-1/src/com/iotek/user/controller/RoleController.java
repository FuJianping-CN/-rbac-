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

@Controller
@RequestMapping(value = "role")
public class RoleController {

	@Autowired
	private RoleService roleService;

	@Autowired
	private AuthService authService;


	@RequestMapping("/index")
	public String index(@RequestParam(value = "pn", defaultValue = "1") Integer pn, Model model) {
		// 传入当前页码,以及页的大小;
		System.out.println("rolecontroler-- index");
		PageHelper.startPage(pn, 5);
		List<Role> roles = this.roleService.queryRoleAll();
		System.out.println("roles=" + roles.size());
		// pageinfo包装查询后的结果,只需要将pageInfo交给页面就可以了
		// 封装了分页的信息,6表示连续显示的页数
		PageInfo page = new PageInfo(roles, 6);
		model.addAttribute("pageInfo", page);
		return "role/index";
	}

	@ResponseBody
	@RequestMapping(value = "insertRole")
	public Object insertRole(Role role, HttpSession session) {
		User creator = (User) session.getAttribute("user");
		role.setCreator(creator.getUserId());
		role.setCreateDate(new Date());
		int insertResult = this.roleService.insertRole(role);
		
		//添加新的角色时，把权限根结点自动添加进去，后面给角色添加权限时，只需要添加各个功能权限结点
		this.addAuthsForRoleByRoleId(role);
		
		AjaxResult result = new AjaxResult();
		if (insertResult > 0) {
			result.setSuccess(true);
		} else {
			result.setSuccess(false);
		}
		return result;
	}
	
	/**
	 * 给新增的角色添加所有根结点权限：角色-权限表
	 * @param role
	 */
	public void addAuthsForRoleByRoleId(Role role) {
		Map<String, Object> roleAuthIdsMap = new HashMap<>();
		roleAuthIdsMap.put("roleId", role.getRoleId());
		List<Integer> rootAuthIds = new ArrayList<Integer>();
		List<Auth> rootAuths = this.authService.queryRootAuths();
		for (Auth auth : rootAuths) {
			rootAuthIds.add(auth.getAuthId());
		}
		roleAuthIdsMap.put("authIds", rootAuthIds);
		this.roleService.addAuthsForRoleByRoleId(roleAuthIdsMap);
	}

	@ResponseBody
	@RequestMapping(value = "deleteRoleById")
	public Object deleteRoleById(int roleId) {
		int deleteResult = this.roleService.deleteRoleById(roleId);
		AjaxResult result = new AjaxResult();
		if (deleteResult > 0) {
			result.setSuccess(true);
		} else {
			result.setSuccess(false);
		}
		return result;
	}

	@RequestMapping("/toUpdateRole")
	public String toUpdateRole(int roleId, Model model) {
		Role role = this.roleService.queryRoleById(roleId);
		model.addAttribute("role", role);
		return "role/updateRole";
	}

	@ResponseBody
	@RequestMapping(value = "updateRoleById")
	public Object updateRoleById(Role role, HttpSession session) {
		User updater = (User) session.getAttribute("user");
		role.setUpdater(updater.getUserId());
		role.setUpdateDate(new Date());
		int udpateResult = this.roleService.updateRoleById(role);
		AjaxResult result = new AjaxResult();
		if (udpateResult > 0) {
			result.setSuccess(true);
		} else {
			result.setSuccess(false);
		}
		return result;
	}

	@RequestMapping("/toAddAuthForRole")
	public String toAddAuthForRole(int roleId, Model model) {
		Role role = this.roleService.queryRoleById(roleId);
		model.addAttribute("role", role);

		// 获取该角色已被授权的权限和未被授权的权限
		List<Auth> assignedAuths = this.authService.queryAuthsByRoleId(roleId);

		List<Auth> unAssignedAuths = this.authService.queryUnAuthsByRoleId(roleId);

		System.err.println(
				"assignedAuths.size()=" + assignedAuths.size() + ", unAssignedAuths.size()=" + unAssignedAuths.size());

		model.addAttribute("assignedAuths", assignedAuths);
		model.addAttribute("unAssignedAuths", unAssignedAuths);

		return "role/addAuthForRole";
	}

	@ResponseBody
	@RequestMapping(value = "addAuthForRole")
	public Object addAuthForRole(Integer roleId, Integer[] unAssignedAuthIds) {
		System.out.println("roleId===========" + roleId);
		AjaxResult result = new AjaxResult();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("roleId", roleId);
			map.put("authIds", unAssignedAuthIds);
			this.roleService.addAuthsForRoleByRoleId(map);
			result.setSuccess(true);
		} catch (Exception e) {
			// TODO: handle exception
			result.setSuccess(false);
		}
		return result;
	}

	@ResponseBody
	@RequestMapping(value = "deleteAuthForRole")
	public Object deleteAuthForRole(Integer roleId, Integer[] assignedAuthIds) {
		System.out.println("roleId" + roleId);
		AjaxResult result = new AjaxResult();
		try {

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("roleId", roleId);
			map.put("authIds", assignedAuthIds);
			this.roleService.deleteAuthsForRoleByRoleId(map);
			result.setSuccess(true);
		} catch (Exception e) {
			// TODO: handle exception
			result.setSuccess(false);
		}
		return result;
	}
}
