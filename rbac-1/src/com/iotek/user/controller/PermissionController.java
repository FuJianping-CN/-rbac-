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
@RequestMapping(value="permission")
public class PermissionController {
	@Autowired
	private AuthService authService;
	
	@Autowired
	private RoleService roleService;
	
	
//	@RequestMapping(value="index")
//     public String index(){
//		System.out.println("xxxxxxxxxxxxxxxxxxxx");
//    	 return "permission/index";
//     }
	@ResponseBody
	@RequestMapping(value="loadData")
	public Object loadData(){
		System.out.println("loadData...");
		/*
		 * 写死模拟数据
		List<Auth> auths=new ArrayList();
		Auth root=new Auth();
		root.setName("顶级节点");
		Auth child=new Auth();
		child.setName("子节点");
		root.getChildren().add(child);
		auths.add(root);
	    return auths;
	    */
		/*
		 * 递归方法:效率比较低
		Auth parent=new Auth();
		parent.setId(0);    //顶层父节点;
		queryChildAuths(parent);
		return parent.getChildren();
		*/
		
		//双循环,
		List<Auth> authList=new ArrayList<Auth>();
		
		List<Auth> auths=authService.queryAllAuths();
		System.out.println("auth=="+auths.size());
		for(Auth auth:auths){
			//每一个节点都当作它的子节点
			Auth child=auth;
			//然后查找它的父节点,等于是没有父节点的
			if(auth.getAuthParentRoot()==0){
				authList.add(auth);
			}else{
				for(Auth innerAuth:auths){
					if(child.getAuthParentRoot().equals(innerAuth.getAuthId())){
						//父节点
						Auth parent=innerAuth;
						//组合父子节点的关系
						parent.getChildren().add(child);
						break;
					}
				}
			}
			
			
		}
		 return authList;  
		
	}
	
	//递归查询权限信息
	public void queryChildAuths(Auth parent){
		List<Auth> childAuths=authService.queryChildAuths(parent.getAuthId());
		for(Auth auth:childAuths){
			queryChildAuths(auth);
		}
		parent.setChildren(childAuths);
	}
	
	
	@RequestMapping("/index")
	public String index(
			@RequestParam(value = "pn", defaultValue = "1") Integer pn,
			Model model) {
		// 传入当前页码,以及页的大小;
		System.out.println("authcontroler-- index");
		PageHelper.startPage(pn, 5);
		List<Auth> auths = this.authService.queryAllAuths();
		
		System.out.println("auths=" + auths.size());
		// pageinfo包装查询后的结果,只需要将pageInfo交给页面就可以了
		// 封装了分页的信息,6表示连续显示的页数
		PageInfo page = new PageInfo(auths, 6);
		model.addAttribute("pageInfo", page);
		
		return "permission/index";
	}
	
	@RequestMapping("/toAddAuth")
	public String toAddAuth(Model model) {
		List<Auth> auths = this.authService.queryAllAuths();
		//获取所有权限名称和id，为了增加权限时以供选择所属节点
		List<Auth> auths2 = new ArrayList<>();
		for (Auth auth : auths) {
			Auth auth2 = new Auth();
			auth2.setAuthId(auth.getAuthId());
			auth2.setAuthName(auth.getAuthName());
			auths2.add(auth2);
		}
		for (Auth auth : auths2) {
			System.err.print("--" + auth.getAuthName());
		}
		model.addAttribute("authIdNams", auths2);
		return "permission/addAuth";
	}
	
	@ResponseBody
	@RequestMapping(value = "insertAuth")
	public Object insertAuth(Auth auth, HttpSession session) {
		User creator = (User) session.getAttribute("user");
		auth.setCreator(creator.getUserId());
		auth.setCreateDate(new Date());
		//权限添加成功后，返回值为自增的权限主键authId
		int insertResult = this.authService.insertAuth(auth);
//		 添加权限时，若该权限为根结点，则将其自动加入所有的角色中：角色-权限表
		this.addRootAuthForRoles(auth);
		//新增的权限，自动添加给管理员角色
		System.err.println("insertAuthForRole...before");
		int addResult = this.authService.insertAuthForRole(1, auth.getAuthId());
		System.err.println("insertAuthForRole...addResult="+addResult);
		AjaxResult result = new AjaxResult();
		if (insertResult > 0) {
			result.setSuccess(true);
			
			//如果当前用户是管理员admin，则将该权限添加给管理员
//			if ("admin".equals(creator.getUserName())) {
//				System.err.println("insertAuthForRoleResult before...insertResult = " + insertResult);
//				int insertAuthForRoleResult = this.authService.insertAuthForRole(1, insertResult);
//				System.err.println("insertAuthForRoleResult = " + insertAuthForRoleResult);
//			}
			
		} else {
			result.setSuccess(false);
		}
		return result;
	}
	
	/**
	 * 添加权限时，若该权限为根结点，则将其自动加入所有的角色中：角色-权限表
	 * @param auth 从表单提交过来的数据
	 * @param authId 执行数据库操作之后返回的自增的主键
	 */
	public void addRootAuthForRoles(Auth auth) {
		String authIsRoot = auth.getAuthIsRoot();
		if ("是".equals(authIsRoot)) {
			Map<String, Object> roleAuthsMap = new HashMap<String, Object>();
			roleAuthsMap.put("authId", auth.getAuthId());
			List<Integer> roleIds = new ArrayList<>();
			List<Role> roles = this.roleService.queryRoleAll();
			for (Role role : roles) {
				roleIds.add(role.getRoleId());
			}
			roleAuthsMap.put("roleIds", roleIds);
			this.roleService.addRootAuthForRoles(roleAuthsMap);
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "deleteAuthById")
	public Object deleteAuthById(int authId) {
		int deleteResult = this.authService.deleteAuthById(authId);
		AjaxResult result = new AjaxResult();
		if (deleteResult > 0) {
			result.setSuccess(true);
		} else {
			result.setSuccess(false);
		}
		return result;
	}
	
	@RequestMapping("/toEditAuth")
	public String toEditAuth(int authId, Model model) {
		List<Auth> auths = this.authService.queryAllAuths();
		//获取所有权限名称和id，为了增加权限时以供选择所属节点
		List<Auth> auths2 = new ArrayList<>();
		for (Auth auth : auths) {
			Auth auth2 = new Auth();
			auth2.setAuthId(auth.getAuthId());
			auth2.setAuthName(auth.getAuthName());
			auths2.add(auth2);
		}
		for (Auth auth : auths2) {
			System.err.print("--" + auth.getAuthName());
		}
		model.addAttribute("authIdNames", auths2);
		
		Auth auth = this.authService.queryAuthById(authId);
		model.addAttribute("auth", auth);
		return "permission/updateAuth";
	}
	
	@ResponseBody
	@RequestMapping(value = "editAuth")
	public Object editAuth(Auth auth, HttpSession session) {
		User creator = (User) session.getAttribute("user");
		auth.setUpdater(creator.getUserId());
		auth.setUpdateDate(new Date());
		int updateResult = this.authService.updateAuthById(auth);
		System.err.println("editAuth updateResult = " + updateResult);
		AjaxResult result = new AjaxResult();
		if (updateResult > 0) {
			result.setSuccess(true);
		} else {
			result.setSuccess(false);
		}
		return result;
	}
}
