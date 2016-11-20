package com.tan.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
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
@RequestMapping("/activity.do")
public class ActivityController {
	
private ActivityDao activityDao=new ActivityDao();

@RequestMapping(params = "method=testhy")	
public void testhy(Users user,HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{		  
	  System.out.println("testhy");	
	}

	
@RequestMapping(params = "method=addActivity")	
public void addActivity(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
	  
	  System.out.println("ActivityController");
	 
	  request.setCharacterEncoding("utf-8");  
	  int publisherUserId =Integer.parseInt(request.getParameter("publisherUserId"));
	 /* int deposit =Integer.parseInt(request.getParameter("deposit"));  //押金
	  int totalAmount =Integer.parseInt(request.getParameter("totalAmount"));  //活动金额
     */	 
	  int deposit =0;  //押金
	  int totalAmount =0;  //活动金额
	  int activityStatus=0;
	  
	  String theme =URLDecoder.decode(request.getParameter("theme"),"UTF-8");  //活动金额
	  String content =URLDecoder.decode(request.getParameter("content"),"UTF-8");  //活动金额
	  int activityType =Integer.parseInt(request.getParameter("activityType"));  //活动类型 1自主 2赞助
	  activityStatus=Integer.parseInt(request.getParameter("activityStatus"));  //活动状态 0 进行中
	 // Date timeStart =StrToDate(request.getParameter("timeStart").toString());  //活动金额
	 // Date timeEnd =StrToDate(request.getParameter("timeEnd").toString());  //活动金额
	  
	  String timeStartstr=request.getParameter("timeStart");
	  String timeEndstr=request.getParameter("timeEnd");
	  
	
	  Activity activity=new Activity();
	  activity.setPublisherUserId(publisherUserId);
	  activity.setTimeStart(StrToDate(timeStartstr));
	  activity.setTimeEnd(StrToDate(timeEndstr));
	  
	  activity.setDeposit(deposit);
	  activity.setTotalAmount(totalAmount);
	  activity.setTheme(theme);
	  activity.setContent(content);
	  activity.setActivityType(activityType);
	  activity.setActivityStatus(activityStatus);	  
	  
	  int activityid=activityDao.addActivity(activity);
	  try {
		  System.out.println("activityid "+activityid);
			response.getWriter().print(activityid); //返回好友id
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}


@RequestMapping(params = "method=getAllActivityLimit")	
public void getAllActivityLimit(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
	  
	  System.out.println("getAllActivityLimit");
	 
	  request.setCharacterEncoding("utf-8");  
	  int start =Integer.parseInt(request.getParameter("start"));
	  int end =Integer.parseInt(request.getParameter("end"));  
	  int activityType =Integer.parseInt(request.getParameter("activityType"));  
	  
	/*  int publisherUserId =Integer.parseInt(request.getParameter("publisherUserId"));
	  int deposit =Integer.parseInt(request.getParameter("deposit"));  //押金
	  int totalAmount =Integer.parseInt(request.getParameter("totalAmount"));  //活动金额
	  String theme =request.getParameter("theme");  //活动金额
	  String content =request.getParameter("content");  //活动金额
	  int activityType =Integer.parseInt(request.getParameter("activityType"));  //活动类型
	  int activityStatus =Integer.parseInt(request.getParameter("activityStatus"));  //活动状态
	    
	  Activity activity=new Activity();
	  activity.setPublisherUserId(publisherUserId);
	  activity.setTimeStart(new Date());
	  activity.setTimeEnd(new Date());
	  
	  activity.setDeposit(deposit);
	  activity.setTotalAmount(totalAmount);
	  activity.setTheme(theme);
	  activity.setContent(content);
	  activity.setActivityType(activityType);
	  activity.setActivityStatus(activityStatus);*/
	  
	  List<Activity> activityList=activityDao.getAllActivityLimit(start, end);
	  try {
		    response.setContentType("text/html;charset=GBK");
		     JSONObject jsonObj = new JSONObject();
			 jsonObj.put("activityList",activityList);		
			  System.out.println(jsonObj.toString());
			 response.getWriter().print(jsonObj.toString()); //返回好友id
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  }





@RequestMapping(params = "method=getActivityByType")	
public void getActivityByType(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
	  
	  System.out.println("getActivityByType");
	 
	  request.setCharacterEncoding("utf-8");  
	  int start =Integer.parseInt(request.getParameter("start"));
	  int end =Integer.parseInt(request.getParameter("end"));  
	  int activityType =Integer.parseInt(request.getParameter("activityType"));  
	  
	/*  int publisherUserId =Integer.parseInt(request.getParameter("publisherUserId"));
	  int deposit =Integer.parseInt(request.getParameter("deposit"));  //押金
	  int totalAmount =Integer.parseInt(request.getParameter("totalAmount"));  //活动金额
	  String theme =request.getParameter("theme");  //活动金额
	  String content =request.getParameter("content");  //活动金额
	  int activityType =Integer.parseInt(request.getParameter("activityType"));  //活动类型
	  int activityStatus =Integer.parseInt(request.getParameter("activityStatus"));  //活动状态
	    
	  Activity activity=new Activity();
	  activity.setPublisherUserId(publisherUserId);
	  activity.setTimeStart(new Date());
	  activity.setTimeEnd(new Date());
	  
	  activity.setDeposit(deposit);
	  activity.setTotalAmount(totalAmount);
	  activity.setTheme(theme);
	  activity.setContent(content);
	  activity.setActivityType(activityType);
	  activity.setActivityStatus(activityStatus);*/
	  
	  List<Activity> activityList=activityDao.getActivityByType(start, end,activityType);
	  try {
		    response.setContentType("text/html;charset=GBK");
		    response.setHeader("Access-Control-Allow-Origin", "*");
		     JSONObject jsonObj = new JSONObject();
			 jsonObj.put("activityList",activityList);	
			 System.out.println(jsonObj.toString());
			 response.getWriter().print(jsonObj.toString()); //返回好友id
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  }



/**
* 字符串转换成日期
* @param str
* @return date
*/
public static Date StrToDate(String str) {
  
   /*SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");*/
   SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
   Date date = new Date();
   try {
    date = format.parse(str);
   } catch (Exception e) {
    e.printStackTrace();
   }
   return date;
}

	
}
