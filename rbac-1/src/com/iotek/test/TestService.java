package com.iotek.test;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.iotek.user.po.User;
import com.iotek.user.service.UserService;

public class TestService {
	@Test
	public void testQueryAllUser(){
		System.out.println("testQueryAllUser...");
		ApplicationContext context=new ClassPathXmlApplicationContext("spring/spring-context.xml");
		UserService userService=(UserService)context.getBean("userService");
		List<User> users=userService.queryAllUser();
		System.out.println("-------------------------------------");
		System.out.println("users.size()="+users.size());
		System.out.println("users==="+users);
	}
}
