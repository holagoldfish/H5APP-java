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
@RequestMapping("/chatlog.do")
public class ChatLogController1 {
	
private ChatLogDao chatLogDao;

	
@RequestMapping(params = "method=sender")	
public void sender(Users user,HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
		  
	  System.out.println("chatlog 发送信息aaaaaaaaaa");	  
	  request.setCharacterEncoding("utf-8");  
	  
	  int senderid = Integer.parseInt(request.getParameter("senderid"));
	  int receiverid = Integer.parseInt(request.getParameter("receiverid")); 
	  String sendcontent = URLDecoder.decode(request.getParameter("sendcontent"),"UTF-8");		  

	  //SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
	  //System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
	  
	  ChatLog chatlog=new ChatLog();
	  chatlog.setSenderid(senderid);
	  chatlog.setReceiverid(receiverid);
	  chatlog.setSendcontent(sendcontent);
	  chatlog.setIsRead("否");
	  chatlog.setSendtime(new Date());
	  chatlog.setStatus(0);	  
	  
	  chatLogDao=new ChatLogDao();
	  int chatLogId=chatLogDao.sender(chatlog);
		  
		try {
			//response.getWriter().print(jsonObj.toString());
			response.getWriter().print("ok");
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
   Date date = null;
   try {
    date = format.parse(str);
   } catch (Exception e) {
    e.printStackTrace();
   }
   return date;
}

	

	
}
