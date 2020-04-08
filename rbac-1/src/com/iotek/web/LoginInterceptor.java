package com.iotek.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.iotek.user.po.User;

public class LoginInterceptor implements HandlerInterceptor {
    
	/**
	 * �������ͼ��Ⱦ��,ִ�д˷���
	 */
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}
    /**
     * �ڿ�����ִ����Ϻ�,ִ�е��߼�����
     */
	
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub

	}
	/**
	 * �ڿ�����ִ��֮ǰ���ҵ���߼�����
	 * �����ķ���ֵ�����߼��Ƿ����ִ��,true ��ʾ����ִ��,false ��ʾ�෴
	 */
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object arg2) throws Exception {
		// TODO Auto-generated method stub
		System.err.println("LoginInterceptor..preHandle. what...");
		HttpSession session=request.getSession();
		User user=(User)session.getAttribute("user");
		System.err.println("LoginInterceptor--user=" + user);
		if(user==null){
			String path=session.getServletContext().getContextPath();
			response.sendRedirect(path+"/login");
			return false;
		}
		return true;
	}

}
