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
		// ���뵱ǰҳ��,�Լ�ҳ�Ĵ�С;
		System.out.println("rolecontroler-- index");
		PageHelper.startPage(pn, 5);
		List<Role> roles = this.roleService.queryRoleAll();
		System.out.println("roles=" + roles.size());
		// pageinfo��װ��ѯ��Ľ��,ֻ��Ҫ��pageInfo����ҳ��Ϳ�����
		// ��װ�˷�ҳ����Ϣ,6��ʾ������ʾ��ҳ��
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
		
		//����µĽ�ɫʱ����Ȩ�޸�����Զ���ӽ�ȥ���������ɫ���Ȩ��ʱ��ֻ��Ҫ��Ӹ�������Ȩ�޽��
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
	 * �������Ľ�ɫ������и����Ȩ�ޣ���ɫ-Ȩ�ޱ�
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

		// ��ȡ�ý�ɫ�ѱ���Ȩ��Ȩ�޺�δ����Ȩ��Ȩ��
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
