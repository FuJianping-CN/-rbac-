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
		 * д��ģ������
		List<Auth> auths=new ArrayList();
		Auth root=new Auth();
		root.setName("�����ڵ�");
		Auth child=new Auth();
		child.setName("�ӽڵ�");
		root.getChildren().add(child);
		auths.add(root);
	    return auths;
	    */
		/*
		 * �ݹ鷽��:Ч�ʱȽϵ�
		Auth parent=new Auth();
		parent.setId(0);    //���㸸�ڵ�;
		queryChildAuths(parent);
		return parent.getChildren();
		*/
		
		//˫ѭ��,
		List<Auth> authList=new ArrayList<Auth>();
		
		List<Auth> auths=authService.queryAllAuths();
		System.out.println("auth=="+auths.size());
		for(Auth auth:auths){
			//ÿһ���ڵ㶼���������ӽڵ�
			Auth child=auth;
			//Ȼ��������ĸ��ڵ�,������û�и��ڵ��
			if(auth.getAuthParentRoot()==0){
				authList.add(auth);
			}else{
				for(Auth innerAuth:auths){
					if(child.getAuthParentRoot().equals(innerAuth.getAuthId())){
						//���ڵ�
						Auth parent=innerAuth;
						//��ϸ��ӽڵ�Ĺ�ϵ
						parent.getChildren().add(child);
						break;
					}
				}
			}
			
			
		}
		 return authList;  
		
	}
	
	//�ݹ��ѯȨ����Ϣ
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
		// ���뵱ǰҳ��,�Լ�ҳ�Ĵ�С;
		System.out.println("authcontroler-- index");
		PageHelper.startPage(pn, 5);
		List<Auth> auths = this.authService.queryAllAuths();
		
		System.out.println("auths=" + auths.size());
		// pageinfo��װ��ѯ��Ľ��,ֻ��Ҫ��pageInfo����ҳ��Ϳ�����
		// ��װ�˷�ҳ����Ϣ,6��ʾ������ʾ��ҳ��
		PageInfo page = new PageInfo(auths, 6);
		model.addAttribute("pageInfo", page);
		
		return "permission/index";
	}
	
	@RequestMapping("/toAddAuth")
	public String toAddAuth(Model model) {
		List<Auth> auths = this.authService.queryAllAuths();
		//��ȡ����Ȩ�����ƺ�id��Ϊ������Ȩ��ʱ�Թ�ѡ�������ڵ�
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
		//Ȩ����ӳɹ��󣬷���ֵΪ������Ȩ������authId
		int insertResult = this.authService.insertAuth(auth);
//		 ���Ȩ��ʱ������Ȩ��Ϊ����㣬�����Զ��������еĽ�ɫ�У���ɫ-Ȩ�ޱ�
		this.addRootAuthForRoles(auth);
		//������Ȩ�ޣ��Զ���Ӹ�����Ա��ɫ
		System.err.println("insertAuthForRole...before");
		int addResult = this.authService.insertAuthForRole(1, auth.getAuthId());
		System.err.println("insertAuthForRole...addResult="+addResult);
		AjaxResult result = new AjaxResult();
		if (insertResult > 0) {
			result.setSuccess(true);
			
			//�����ǰ�û��ǹ���Աadmin���򽫸�Ȩ����Ӹ�����Ա
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
	 * ���Ȩ��ʱ������Ȩ��Ϊ����㣬�����Զ��������еĽ�ɫ�У���ɫ-Ȩ�ޱ�
	 * @param auth �ӱ��ύ����������
	 * @param authId ִ�����ݿ����֮�󷵻ص�����������
	 */
	public void addRootAuthForRoles(Auth auth) {
		String authIsRoot = auth.getAuthIsRoot();
		if ("��".equals(authIsRoot)) {
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
		//��ȡ����Ȩ�����ƺ�id��Ϊ������Ȩ��ʱ�Թ�ѡ�������ڵ�
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
