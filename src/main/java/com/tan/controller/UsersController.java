package com.tan.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tan.dao.UserInfoDao;
import com.tan.dao.UserSignInDao;
import com.tan.model.UserInfo;
import com.tan.model.UserSignIn;
import com.tan.model.Users;
import com.tan.service.UserService;

@Controller
@RequestMapping("/users.do")
public class UsersController {
	
private UserService userService;
private UserSignInDao userSignInDao;
private UserInfoDao userInfoDao;

@RequestMapping(params = "method=testhy")	
public void testhy(Users user,HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
		System.out.println("java后端测试联通ok");			
	}

	
@RequestMapping(params = "method=add")	
public void add(Users user,HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
	System.out.println("连数据库测试iiiiiiii哦哦哦哦哦哦哦bbbbbbbbbbbbbbbbbbbbbbbbbb");
	  request.setCharacterEncoding("utf-8");  
	  String headimg = request.getParameter("headimg");
	  String nickname = URLDecoder.decode(request.getParameter("nickname"),"UTF-8");
	  String sex = URLDecoder.decode(request.getParameter("sex"),"UTF-8");
	  String singleOrDouble = URLDecoder.decode(request.getParameter("singleOrDouble"),"UTF-8");
	  String birthday = request.getParameter("birthday");
	  String city = URLDecoder.decode(request.getParameter("city"),"UTF-8");
	  String job = URLDecoder.decode(request.getParameter("job"),"UTF-8");
	  String phonenumber = request.getParameter("phonenumber");
	  String pwd = URLDecoder.decode(request.getParameter("pwd"),"UTF-8");
	  
	  
	  UserSignIn userSignIn=new UserSignIn();
	  userSignIn.setPhonenumer(phonenumber);
	  userSignIn.setPwd(pwd);
	  userSignIn.setIsLogin(1); //1表示登录 0未登录	 	  
	  
	  userSignInDao=new UserSignInDao();
	  int user_id=userSignInDao.addUserSignIn(userSignIn);
		  
	  UserInfo userInfo=new UserInfo();
	  userInfo.setHeading(headimg);
	  userInfo.setNickname(nickname);
	  userInfo.setSex(sex);
	  userInfo.setSingleOrdouble(singleOrDouble);
	  userInfo.setBirthday(StrToDate(birthday));
	  userInfo.setCity(city);
	  userInfo.setJob(job);
	  userInfo.setUser_id(user_id);
	  
	  userInfoDao=new UserInfoDao();
	  int userinfoid=userInfoDao.addUserInfo(userInfo); 
	  
	 // String name = URLDecoder.decode(nickname,"UTF-8");
	 // System.out.println("祈求不是乱码44："+URLDecoder.decode(name,"UTF-8"));	
	
	  
		try {
			response.getWriter().print(user_id+""); //返回注册时 用户id
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


@RequestMapping(params = "method=login")	
public void login(Users user,HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
		  
	  System.out.println("连数据库测试login UsersController login");	
	  request.setCharacterEncoding("utf-8");  
	  String phonenumber = request.getParameter("phonenumber");
	  String pwd = URLDecoder.decode(request.getParameter("pwd"),"UTF-8");
	  
	  UserSignIn userSignIn=new UserSignIn();
	  userSignIn.setPhonenumer(phonenumber);
	  userSignIn.setPwd(pwd);
	  //userSignIn.setIsLogin(1); //1表示登录 0未登录	
	  
  
	  userSignInDao=new UserSignInDao();
	  UserSignIn userSignIn2=userSignInDao.getUserByphoneAndPwd(userSignIn);
	  
	  
	  response.setContentType("text/html;charset=GBK");
	  JSONObject jsonObj = new JSONObject();
	  //jsonObj.put("phonenumber", userSignIn2.getPhonenumber());
	  jsonObj.put("userSignIn2",userSignIn2);
	  	  
		/*jsonObj.put("username", "张三吖");
		jsonObj.put("password", "123456");	*/  
	  
	  /*	 
	  UserInfo userInfo=new UserInfo();
	  userInfo.setHeading(headimg);
	  userInfo.setNickname(nickname);
	  userInfo.setSex(sex);
	  userInfo.setSingleOrdouble(singleDouble);
	  userInfo.setBirthday(StrToDate(birthday));
	  userInfo.setCity(city);
	  userInfo.setJob(job);
	  userInfo.setUser_id(user_id);
	 
	  System.out.println("获取"+userInfo.getHeading());  
	  
	  userInfoDao=new UserInfoDao();
	  int userinfoid=userInfoDao.addUserInfo(userInfo);
	  System.out.println("userinfoid "+userinfoid);	*/  
		
		try {
			response.setHeader("Access-Control-Allow-Origin", "");
			response.setHeader("Access-Control-Allow-Headers", "X-Requested-With");
			response.setHeader("Cache-Control", "no-cache");
			  System.out.println("获取"+jsonObj.toString());  
			response.getWriter().print(jsonObj.toString());
			//response.getWriter().print("ok");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}



@RequestMapping(params = "method=allUsers")	
public void allUsers(Users user,HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
		  
	
	  request.setCharacterEncoding("utf-8");  
	  userSignInDao=new UserSignInDao();
	  List<UserSignIn> userslist=userSignInDao.getAllUser();
	  
	  response.setContentType("text/html;charset=GBK");
	  JSONObject jsonObj = new JSONObject();
	  jsonObj.put("userslist",userslist);	  
		
		try {
			 System.out.println("获取"+jsonObj.toString());  
			response.getWriter().print(jsonObj.toString());
			//response.getWriter().print("ok");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}




//【是否需要】
/*@RequestMapping(params = "method=getAllUserLimit")	
public void getAllUserLimit(Users user,HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{

	 System.out.println("UsersController.java getAllUserLimit");  
	  request.setCharacterEncoding("utf-8");  
	  int start = Integer.parseInt(request.getParameter("start"));
	  int end = Integer.parseInt(request.getParameter("end"));
	
	  userSignInDao=new UserSignInDao();
	  List<UserSignIn> userslist=userSignInDao.getAllUserLimit(start, end);
	  
	  response.setContentType("text/html;charset=GBK");
	  JSONObject jsonObj = new JSONObject();
	  jsonObj.put("userslist",userslist);	  
		
		try {
			
			// System.out.println("获取"+jsonObj.toString());  
			response.getWriter().print(jsonObj.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}*/


@RequestMapping(params = "method=getAllUserLimit")	
public void getAllUserLimit(Users user,HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{

	 System.out.println("UsersController.java getAllUserLimit");  
	  request.setCharacterEncoding("utf-8");  
	  int start = Integer.parseInt(request.getParameter("start"));
	  int end = Integer.parseInt(request.getParameter("end"));
	
	  userSignInDao=new UserSignInDao();
	  List<UserSignIn> userslist=userSignInDao.getAllUserLimit(start, end);
	  
	  response.setContentType("text/html;charset=GBK");
	 /* JSONObject jsonObj = new JSONObject();
	  jsonObj.put("userslist",userslist);	*/
	  
	//  JSONObject jsonObject = JSONObject.fromObject(jsonObj); 
	 // System.out.println(jsonObject);//输出	  
	  //JSONArray jsonArray = JSONArray.fromObject(userslist);	  
	   
		try {
			// ok1
			response.setHeader("Access-Control-Allow-Origin", "");
			response.setHeader("Access-Control-Allow-Headers", "X-Requested-With");
			response.setHeader("Cache-Control", "no-cache");
			
			  JSONArray jsonArray = JSONArray.fromObject(userslist);
			  System.out.println(jsonArray);
			  response.getWriter().print(jsonArray.toString());
			
			//ok2			
			/*【需要解决】
			  String jsonp=request.getParameter("callback");  
		     System.out.println("jsonp "+jsonp); 
			 response.setHeader("Access-Control-Allow-Origin", "");
			response.setHeader("Access-Control-Allow-Headers", "X-Requested-With");
			response.setHeader("Cache-Control", "no-cache");
			JSONArray jsonArray = JSONArray.fromObject(userslist);
			System.out.println("获取"+jsonArray.toString());  
			response.getWriter().print(jsonp+"("+jsonArray.toString()+")");*/
			
			/* 【需要解决】
			 JSONObject jsonObj = new JSONObject();
			  jsonObj.put("userslist",userslist);
			JSONObject jsonObject = JSONObject.fromObject(jsonObj); 			
			String jsonp=request.getParameter("callback");  
		     System.out.println("jsonp "+jsonp); 		     
			 response.setHeader("Access-Control-Allow-Origin", "");
			response.setHeader("Access-Control-Allow-Headers", "X-Requested-With");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(jsonp+"("+jsonObject.toString()+")");*/
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


@RequestMapping(params = "method=getUserInfo")	
public void getUserInfo(Users user,HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{

	  request.setCharacterEncoding("utf-8");  
	  int userId = Integer.parseInt(request.getParameter("userId"));
	
	  userSignInDao=new UserSignInDao();
	  List<UserSignIn> userslist=userSignInDao.getUserInfo(userId);
	  
	  response.setContentType("text/html;charset=GBK");  
	   
		try {
			// ok1
			response.setHeader("Access-Control-Allow-Origin", "");
			response.setHeader("Access-Control-Allow-Headers", "X-Requested-With");
			response.setHeader("Cache-Control", "no-cache");
			
			  JSONArray jsonArray = JSONArray.fromObject(userslist);
			  System.out.println(jsonArray);
			  response.getWriter().print(jsonArray.toString());
			
			//ok2			
			/*【需要解决】
			  String jsonp=request.getParameter("callback");  
		     System.out.println("jsonp "+jsonp); 
			 response.setHeader("Access-Control-Allow-Origin", "");
			response.setHeader("Access-Control-Allow-Headers", "X-Requested-With");
			response.setHeader("Cache-Control", "no-cache");
			JSONArray jsonArray = JSONArray.fromObject(userslist);
			System.out.println("获取"+jsonArray.toString());  
			response.getWriter().print(jsonp+"("+jsonArray.toString()+")");*/
			
			/* 【需要解决】
			 JSONObject jsonObj = new JSONObject();
			  jsonObj.put("userslist",userslist);
			JSONObject jsonObject = JSONObject.fromObject(jsonObj); 			
			String jsonp=request.getParameter("callback");  
		     System.out.println("jsonp "+jsonp); 		     
			 response.setHeader("Access-Control-Allow-Origin", "");
			response.setHeader("Access-Control-Allow-Headers", "X-Requested-With");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().print(jsonp+"("+jsonObject.toString()+")");*/
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}



@RequestMapping(params = "method=getUserfriend")	
public void getUserfriend(Users user,HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{

	 System.out.println("getUserfriend");  
	  request.setCharacterEncoding("utf-8");  
	  int start = Integer.parseInt(request.getParameter("start"));
	  int end = Integer.parseInt(request.getParameter("end"));
	  int userid = Integer.parseInt(request.getParameter("userid"));
	  
	 // UserSignIn userSignIn=new UserSignIn();
	 // userSignIn.setPhonenumer(phonenumber);
	  //userSignIn.setPwd(pwd);
	  //userSignIn.setIsLogin(1); //1琛ㄧず鐧诲綍 0鏈櫥褰�
	  
	  System.out.println("===============userid "+userid);  
	  
	  userSignInDao=new UserSignInDao();
	  List<UserSignIn> userslist=userSignInDao.getUserfriend(start, end, userid);
	  
	  response.setContentType("text/html;charset=GBK");
	  JSONObject jsonObj = new JSONObject();
	  jsonObj.put("userslist",userslist);	  
		
		try {
			
			//System.out.println("获取"+jsonObj.toString());  
			response.getWriter().print(jsonObj.toString());
			//response.getWriter().print("ok");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


/*
@RequestMapping(params = "method=allUsers")	
public void allUsers(Users user,HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
		  
	  System.out.println("连数据库测试login");	
	  request.setCharacterEncoding("utf-8");  
	  //String phonenumber = request.getParameter("phonenumber");
	 // String pwd = URLDecoder.decode(request.getParameter("pwd"),"UTF-8");
	  
	 // UserSignIn userSignIn=new UserSignIn();
	 // userSignIn.setPhonenumer(phonenumber);
	  //userSignIn.setPwd(pwd);
	  //userSignIn.setIsLogin(1); //1表示登录 0未登录	
	  userSignInDao=new UserSignInDao();
	  List<UserSignIn> userslist=userSignInDao.getAllUser();

		JSONObject jsonObj = new JSONObject();
		jsonObj.put("userslist", userslist);
	  
	  
	  //System.out.println("userslist长度："+userslist.size());
	  
	  
	//  List<UserSignIn> newuserslist=new ArrayList<UserSignIn>();
	  
	  JsonConfig jsonConfig = new JsonConfig();
      jsonConfig.registerJsonValueProcessor(Date.class , new JsonDateValueProcessor());
      System.out.println(JSONArray.fromObject(userslist, jsonConfig));
      
      
     // JSONObject jsonObj = new JSONObject();
     // System.out.println("=======:" + jsonObj.fromObject(userslist, jsonConfig).toString());
     //  jo = JSONArray.fromObject(userslist, jsonConfig);
     // jsonObj.put("userslist",jsonObj.fromObject(userslist, jsonConfig));
     // System.out.println("=======:" + jsonObj.toString());
	  
	  List<UserSignIn> newlis=new  ArrayList<UserSignIn>();
	  
	  for (int i=0;i<userslist.size();i++)
	  {
		  JSONObject jsonObj = new JSONObject();
		  JsonConfig jsonConfig = new JsonConfig();
		  jsonConfig.registerJsonValueProcessor(Date.class,new JsonDateValueProcessor("yyyy-MM-dd"));
		   jsonObj.fromObject(userslist.get(i), jsonConfig);
		   
		   
		   
		   String list = request.getParameter("json");
		   JSONArray data = JSONArray.fromObject(list);
		   for(int i=0;i<data.size();i++){
		        JSONObject jobj =  (JSONObject) data.get(i);
		        String name = jobj.get("name");
		   }
		   
	  }
	  
	 
	  	  
	  
	  JsonConfig jsonConfig = new JsonConfig();
	// 设置javabean中日期转换时的格式
	jsonConfig.registerJsonValueProcessor(Date.class,new JsonDateValueProcessor("yyyy-MM-dd HH:mm:ss"));
	// 获取json数组
	JSONArray jsonArray = JSONArray.fromObject(list, jsonConfig);
	System.out.println(jsonArray.toString());
	  
	  response.setContentType("text/html;charset=GBK");
	 // System.out.println("userslist.tostring："+userslist.toString());	  
	 
	  JSONObject mynew=jsonObj.fromObject(userslist, jsonConfig);
	  jsonObj.put("userslist",mynew);	  
	  
	  
	  ArrayList list = new ArrayList();
	  Users user = new Users();
	  user.setDate(new Date());
	  list.add(user);
	 // JsonConfig jsonConfig = new JsonConfig();
	  // 设置javabean中日期转换时的格式
	  //jsonConfig.registerJsonValueProcessor(Date.class,new JsonDateValueProcessor("yyyy-MM-dd HH:mm:ss"));
	 // jsonConfig.registerJsonValueProcessor(Date.class,new JsonDateValueProcessor("yyyy-MM-dd"));
	  // 获取json数组
	 // JSONArray jsonArray = JSONArray.fromObject(userslist, jsonConfig);
	 // System.out.println(jsonArray.toString());
	  
		
		try {
			//response.getWriter().print(jsonArray.toString());
			
			// System.out.println(mynew.toString());
			//response.getWriter().print(mynew.toString());
			
			System.out.println(userslist.toString());
			response.getWriter().print(userslist.toString());
			//response.getWriter().print("ok");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
*/



/**
* 字符串转换成日期
* @param str
* @return date
*/
public static Date StrToDate(String str) {
  
   /*SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");*/
   SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
   Date date = null;
   try {
    date = format.parse(str);
   } catch (Exception e) {
    e.printStackTrace();
   }
   return date;
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
		
		
	}
	
	
}
