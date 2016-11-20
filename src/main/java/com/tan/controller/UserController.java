package com.tan.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tan.model.Users;
import com.tan.service.UserService;

@Controller
@RequestMapping("/user.do")
public class UserController {
	
	private UserService userService;
	
@RequestMapping(params = "method=add")	
public void add(Users user,HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
	  
	  //request.setCharacterEncoding("utf-8");  
	  String headimg = request.getParameter("headimg");
	  String nickname = URLDecoder.decode(request.getParameter("nickname"),"UTF-8");
	  String sex = URLDecoder.decode(request.getParameter("sex"),"UTF-8");
	  String singleOrDouble = request.getParameter("singleOrDouble");
	  String birthday = request.getParameter("birthday");
	  String city = URLDecoder.decode(request.getParameter("city"),"UTF-8");
	  String job = URLDecoder.decode(request.getParameter("job"),"UTF-8");
	  String phonenumber = request.getParameter("phonenumber");
	  String pwd = URLDecoder.decode(request.getParameter("pwd"),"UTF-8");
	  
	 // String name = URLDecoder.decode(nickname,"UTF-8");
	 // System.out.println("祈求不是乱码44："+URLDecoder.decode(name,"UTF-8"));	
	 // System.out.println("祈求不是乱码554："+name);
	  
	 // System.out.println(headimg);
	  System.out.println(nickname);
	  System.out.println(sex);
	  System.out.println(singleOrDouble);
	  System.out.println(birthday);
	  System.out.println(city);
	  System.out.println(job);
	  System.out.println(phonenumber);
	  System.out.println(pwd);
	  
	  
		/*System.out.println("连接");			
		System.out.println("获取用户名方式一：username:"+user.getName());
		String username = request.getParameter("username");
		System.out.println("获取用户名方式二：username:"+username);
		String pwd = request.getParameter("pwd");
		System.out.println("获取密码方式二：username:"+pwd);
		String f1 = request.getParameter("headimg");
		System.out.println("获取密码方式二：headimg:"+f1);*/
		/*System.out.println("author:"+book.getAuthor());		
		bookService.add(book);*/
	/*	System.out.println("mmmm: ");
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("username", "张三吖");
		jsonObj.put("password", "123456");	*/
		//URLDecoder.decode(jsonObj.toString(),"UTF-8");
		
		try {
			//response.getWriter().print(jsonObj.toString());
			response.getWriter().print("ok");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
	/*
	@RequestMapping(params = "method=add")
	public void add(Users user,HttpServletRequest request, HttpServletResponse response){
		
		System.out.println("dfsssb你好啊");			
		System.out.println("bookname:"+user.getName());
		
		String callback = request.getParameter("name");
		System.out.println("bookname2:"+user.getName());
		
		System.out.println("author:"+book.getAuthor());		
		bookService.add(book);
		
		
		System.out.println("mmmm: ");
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("username", "张三吖");
		jsonObj.put("password", "123456");	
		
	
		
		//URLDecoder.decode(jsonObj.toString(),"UTF-8");
		
		try {
			response.getWriter().print(jsonObj.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	*/
	
	
	@RequestMapping(params = "method=getAllUsers")
	public void getAllUsers(HttpServletRequest request, HttpServletResponse response){
		
		if(userService==null)
		{
			userService=new UserService();
		}
		
		String callback = request.getParameter("name");
		System.out.println("username22222222222:"+callback);
		

		System.out.println("getAllUsers  fdsf");	
		
		
		List<Users> allusers=userService.getAllUsers();
		
		
		JSONObject jsonObj = new JSONObject();
		//jsonObj.put("username", "张三吖");
		//jsonObj.put("password", "123456");	
		
		
		//jsonObj.putAll();
		

		System.out.println("mmmmfffff: "+allusers.toString());
		response.setContentType("text/html;charset=GBK");
		
		try {
			response.getWriter().print(allusers);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	/*	for (int i=0;i<allusers.size();i++)
		{
			jsonObj.put("username", allusers.get(0));
		}
		*/	
			
		
		/*try {
			response.getWriter().print(jsonObj.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		
		
		
	}
	
	
	
/*	@RequestMapping(params = "method=update")
	public String update(Users user) {
		userService.update(user);
		return "success";
	}
	
	public UserService getUserService() {
		return userService;
	}
	
	
	@Resource
	public void setUserService(UserService userService) {
		this.userService = userService;
	}*/
	
	
	
}
