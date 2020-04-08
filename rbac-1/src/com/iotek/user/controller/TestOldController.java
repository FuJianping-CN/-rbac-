package com.iotek.user.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iotek.user.po.User;
import com.iotek.user.service.UserService;

@Controller
public class TestOldController {
	@Autowired
    private UserService userService;
    @RequestMapping(value="login")
    public String login(){
    	return "login";
    }
    
    @ResponseBody
    @RequestMapping(value="json")
    public Object json(){
    	Map map=new HashMap();
    	map.put("username", "liayin");
    	return map;
    }
    
    
    @RequestMapping(value="queryAllUser")
    public String queryAllUser(){
    	System.out.println("*****");
    	List<User> users=userService.queryAllUser();
    	System.out.println("*****"+users.size());
    	return "login";
    }
}
