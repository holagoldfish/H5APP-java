package com.tan.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tan.dao.*;
import com.tan.model.*;
import com.tan.service.UserService;

@Controller
@RequestMapping("/friendship.do")
public class FriendShipController {
	
private FriendShipDao friendShipDao=new FriendShipDao();

@RequestMapping(params = "method=testhy")	
public void testhy(Users user,HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
		  
	  System.out.println("testhy");	
	}

	
@RequestMapping(params = "method=add")	
public void add(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
	  
	  System.out.println("FriendShipController");
	 
	  request.setCharacterEncoding("utf-8");  
	  int loginuserid =Integer.parseInt(request.getParameter("loginuserid"));
	  int friendid =Integer.parseInt(request.getParameter("friendid"));
	  
	  FriendShip friendShip=new FriendShip();	  
	  friendShip.setUserid(loginuserid);
	  friendShip.setFriendid(friendid); 
	  friendShip.setGroupid(0);  
	  int id=friendShipDao.add(friendShip);
	  try {
			response.getWriter().print(id); //返回好友id
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}

@RequestMapping(params = "method=getfriend")	
public void getfriendByuserid(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
	  
	  request.setCharacterEncoding("utf-8");  
	  int loginuserid =Integer.parseInt(request.getParameter("loginuserid"));
	  int friendid =Integer.parseInt(request.getParameter("friendid"));
	  
	  System.out.println("FriendShipController getfriend");
	  
	  FriendShip friendShip=new FriendShip();	  
	  friendShip.setUserid(loginuserid);
	  friendShip.setFriendid(friendid); 
	  FriendShip friendShip1=friendShipDao.getfriendship(friendShip);
	  try {
		    if(friendShip1!=null)
		    {
			  response.getWriter().print(friendShip1.getFriendid()); //返回好友id
		    }
		    else
		    {
		    	response.getWriter().print(0); //返回好友id
		    }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}

@RequestMapping(params = "method=getfriendList")	
public void getfriendListByuserid(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
	  
	 // request.setCharacterEncoding("utf-8");  
	  int loginuserid =Integer.parseInt(request.getParameter("loginuserid"));
	  int friendid =Integer.parseInt(request.getParameter("friendid"));
	  
	  System.out.println("FriendShipController getfriendList");
	  
	  FriendShip friendShip=new FriendShip();	  
	  friendShip.setUserid(loginuserid);
	  friendShip.setFriendid(friendid); 
	  List<FriendShip> friendShip1=friendShipDao.getfriendshipList(friendShip);

	  System.out.println("friendShip1.size() "+friendShip1.size());
	  try {
		    String tabText="关注Ta";
		    if(friendShip1.size()>0)
		    {
		  	  tabText="已关注"; 
		    }		    
		    //JSONObject jsonObj = new JSONObject();
			  //jsonObj.put("friendShip1",friendShip1.get(0));
		     //response.getWriter().print(jsonObj.toString());			 
		     response.setContentType("text/html;charset=GBK");
			 response.getWriter().print(tabText);			 
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace(); 
		}
}


@RequestMapping(params = "method=getmyfriendList")	
public void getmyfriendList(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
	  
	 
	  int loginuserid =Integer.parseInt(request.getParameter("loginuserid"));
	  FriendShip friendShip=new FriendShip();	  
	  friendShip.setUserid(loginuserid);
	  List<FriendShip> friendShip1=friendShipDao.getmyfriendshipList(friendShip);

	  try {
		    String tabText="关注Ta";
		    if(friendShip1.size()>0)
		    {
		  	  tabText="已关注"; 
		    }		 
		     response.setContentType("text/html;charset=GBK");
		     JSONObject jsonObj = new JSONObject();
			 jsonObj.put("friendShip1",friendShip1);		 
		    
			 response.getWriter().print(tabText);			 
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace(); 
		}
}





	
}
