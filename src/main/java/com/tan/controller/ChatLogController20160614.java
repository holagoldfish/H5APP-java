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

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value="/chatlog14/{sender}/{recevied}")
public class ChatLogController20160614 {
	
private ChatLogDao chatLogDao=new ChatLogDao();

//与某个客户端的连接会话，需要通过它来给客户端发送数据
private Session session;
 
private static List<Users> peers =new ArrayList<Users>();
private static List<Users> peers2 = new ArrayList<Users>();
private static CopyOnWriteArraySet<ChatLogController20160614> webSocketSet = new CopyOnWriteArraySet<ChatLogController20160614>();


private static List<LoginAndSession> LoginAndSessionList = new ArrayList<LoginAndSession>();

int loginUserid;
int chatUserid;

/**
 * 连接建立成功调用的方法
 * @param session  可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
 */
@OnOpen
public void onOpen(@PathParam(value="sender") String login_Userid,@PathParam(value="recevied") String chat_Userid,Session session){
	System.out.println("有新连接加入！当前在线人数为");
	System.out.println("loginUserid: " + login_Userid);
	System.out.println("chatUserid: " + chat_Userid);
	
	//loginUsersIdList.add(login_Userid);
	LoginAndSession userandSession=new LoginAndSession();
	userandSession.setLogin_Userid(login_Userid);
	userandSession.setChat_Userid(chat_Userid);
	userandSession.setSession(session);
	
	LoginAndSessionList.add(userandSession);
	
	//登录者用户成 接收未读信息
	loginUserid=Integer.parseInt(login_Userid);
	chatUserid=Integer.parseInt(chat_Userid);
	
    this.session = session;
    webSocketSet.add(this);     //加入set中
   // addOnlineCount();           //在线数加1
   // System.out.println("有新连接加入！当前在线人数为" + getOnlineCount());
    
    
    
    //查找给登录用户发的未读信息
    getUnReadChatLog(session,chatUserid, loginUserid,"否");
    /*List<ChatLog> chatLogList=chatLogDao.getUnReadChatLog(chatUserid, loginUserid,"否");
    System.out.println("chatLogList 长度bbbbbbbbbbbbbbhaha "+chatLogList.size());
    
    try {    	
    	for(int i=0;i<chatLogList.size();i++)
    	{
    		ChatLog c=chatLogList.get(i);
    		//Thread.sleep(5000);
			session.getBasicRemote().sendText(c.getSendcontent());
			 System.out.println("neirong: "+c.getSendcontent());
    	}   
		
	} catch (Exception e) {
		e.printStackTrace();
	}*/
}


@OnMessage
public void onMessage(String message, Session session) 
    	throws IOException, InterruptedException {
		
		System.out.println("4444:6666 " + message);
		System.out.println("session ceshi: " + session.toString());
		
		/*
		int loginUserid;
		int chatUserid;
		*/
		
		if(LoginAndSessionList.size()>0)
		{
			/*for(int i=0;i<LoginAndSessionList.size();i++)
			{
				LoginAndSession loginandsession=LoginAndSessionList.get(i);
				if(session==loginandsession.getSession())
				{
					loginandsession.getChat_Userid();
				}
			}*/
			
			System.out.println("loginUserid " + loginUserid);
			System.out.println("chatUserid: " + chatUserid);
			
			int n=0; //判断聊天客户是否在线，不在线发送不在线信息
			
			for(int i=0;i<LoginAndSessionList.size();i++)
			{
				LoginAndSession loginandsession=LoginAndSessionList.get(i);
				if(chatUserid==Integer.parseInt(loginandsession.getLogin_Userid()))
				{   
					loginandsession.getSession().getBasicRemote().sendText(message);
					
					ChatLog chatLog=new ChatLog();
					chatLog.setSenderid(loginUserid);
					chatLog.setReceiverid(chatUserid);
					chatLog.setIsRead("是");
					chatLog.setSendtime(new Date());
					chatLog.setSendcontent(message);
					chatLog.setRemark("");
					chatLog.setStatus(0);
					chatLogDao.sender(chatLog);
					n++;
					break;
				}
			}
			
			if(n==0)
			{
				ChatLog chatLog=new ChatLog();
				chatLog.setSenderid(loginUserid);
				chatLog.setReceiverid(chatUserid);
				chatLog.setIsRead("否");
				chatLog.setSendtime(new Date());
				chatLog.setSendcontent(message);
				chatLog.setRemark("");
				chatLog.setStatus(0);
				chatLogDao.sender(chatLog);
			}
			
		}
		
		//session.getBasicRemote().sendText(message.toString());	
		
	  //List<ChatLog> chatLogList=chatLogDao.getUnReadChatLog(chatUserid, loginUserid,"否");
	  //System.out.println("chatLogList 长度bbbbbbbbbbbbbbhaha "+chatLogList.size());
		
		//getUnReadChatLog(session,chatUserid, loginUserid,"否");	
		
}


/**
 * 查询未读信息
 * @param session
 * @param chatUserid
 * @param loginUserid
 * @param Readstatus
 */
public void getUnReadChatLog(Session session,int chatUserid, int loginUserid,String Readstatus)
{

	List<ChatLog> setReadStatusList=new ArrayList<ChatLog>();
    //查找给登录用户发的未读信息
    List<ChatLog> chatLogList=chatLogDao.getUnReadChatLog(chatUserid, loginUserid,Readstatus);
    System.out.println("chatLogList 长度bbbbbbbbbbbbbbhaha "+chatLogList.size());
    
    try {    	
    	 /*JSONObject jsonObj = new JSONObject();
   	     jsonObj.put("chatLogList",chatLogList);
   	   System.out.println("jsonObj.toString() "+jsonObj.toString());
	   session.getBasicRemote().sendObject(jsonObj);*/		
		//session.getBasicRemote().sendText("ni hao a啊啊啊啊");    	
    	/*int sentMessages = 0;
		while(sentMessages < 3){
			Thread.sleep(5000);
			session.getBasicRemote().
				sendText("This is an intermediate server message. Count: " 
					+ sentMessages);
			sentMessages++;
		}		*/
    	
    	if(chatLogList.size()>0)
    	{
	    	for(int i=0;i<chatLogList.size();i++)
	    	{
	    		ChatLog c=chatLogList.get(i);
	    		//Thread.sleep(5000);
				session.getBasicRemote().sendText(c.getSendcontent());
				System.out.println("chatlogid: "+c.getChatlogId()+" neirong: "+c.getSendcontent());
				c.setIsRead("是");
				setReadStatusList.add(c);
				//updateChatLog
	    	}       	
    	    chatLogDao.updateChatLog(setReadStatusList);   
    	}
		
	} catch (Exception e) {
		e.printStackTrace();
	}
}



/**
 * 连接关闭调用的方法
 */
@OnClose
public void onClose(){
	System.out.println("关闭");
    webSocketSet.remove(this);  //从set中删除
   // subOnlineCount();           //在线数减1    
    //System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());
}
 
@OnError
public void onError(Throwable throwable) {
   // System.out.println(throwable.getMessage());
}

	
	
}
