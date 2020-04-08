package com.iotek.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.iotek.user.po.User;

public class LoginInterceptor implements HandlerInterceptor {
    
	/**
	 * 在完成视图渲染后,执行此方法
	 */
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}
    /**
     * 在控制器执行完毕后,执行的逻辑操作
     */
	
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub

	}
	/**
	 * 在控制器执行之前完成业务逻辑操作
	 * 方法的返回值决定逻辑是否继续执行,true 表示继续执行,false 表示相反
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
