package com.tan.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.tan.dao.ChatLogDao;
import com.tan.model.ChatLog;
import com.tan.model.LoginAndSession;

import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value="/chatlog/{sender}/{recevied}")
public class ChatLogController {
	
private ChatLogDao chatLogDao=new ChatLogDao();

//与某个客户端的连接会话，需要通过它来给客户端发送数据
private Session session;
private static CopyOnWriteArraySet<ChatLogController> webSocketSet = new CopyOnWriteArraySet<ChatLogController>();
private static List<LoginAndSession> LoginAndSessionList = new ArrayList<LoginAndSession>();

//登录时给聊友发送信息，聊友不在线，为防止一条条发送到服务器，占用服务器资源，者累计一定数量后批量给后台发送信息
private static List<ChatLog> loginUserToUnloginChatlists=new ArrayList<ChatLog>(); 

private static LoginAndSession userandSession;
int loginUserid;
int chatUserid;

/**
 * 连接建立成功调用的方法
 * @param session  可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
 */
@OnOpen
public void onOpen(@PathParam(value="sender") String login_Userid,@PathParam(value="recevied") String chat_Userid,Session session){
	
	System.out.println("loginUserid: " + login_Userid);
	System.out.println("chatUserid: " + chat_Userid);
	System.out.println("session: "+session);
	
	//登录用户信息
	userandSession=new LoginAndSession();
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
    
    //查找给登录用户发的未读信息
    getUnReadChatLog(session,chatUserid, loginUserid,"否");
}




@OnMessage
public void onMessage(String message, Session session) 
    	throws IOException, InterruptedException {
		
		System.out.println("客户端 "+session.toString()+" 发送的信息： " + message);
		System.out.println("LoginAndSessionList.size() 大小 "+LoginAndSessionList.size());
		
		System.out.println("loginUserid " + loginUserid);
		System.out.println("chatUserid: " + chatUserid);
		
		for(int i=0;i<LoginAndSessionList.size();i++)
		{
			LoginAndSession loginandsession=LoginAndSessionList.get(i);
			System.out.println("打印 登录用户: " + loginandsession.getLogin_Userid());
			System.out.println("打印 聊友: " + loginandsession.getChat_Userid());
			System.out.println("打印 session: " + loginandsession.getSession().toString());
		}
	
		System.out.println("全部打印LoginAndSessionList");
					
		int n=0; //判断聊天客户是否在线，在线发送及时嘻嘻			
		for(int i=0;i<LoginAndSessionList.size();i++)
		{
			LoginAndSession loginandsession=LoginAndSessionList.get(i);
			System.out.println("loginandsession 登录用户: " + loginandsession.getLogin_Userid());
			System.out.println("loginandsession 聊友: " + loginandsession.getChat_Userid());
			System.out.println("loginandsession session: " + loginandsession.getSession().toString());
			
			if(chatUserid==Integer.parseInt(loginandsession.getLogin_Userid()) && loginUserid==Integer.parseInt(loginandsession.getChat_Userid()))
			{   
				System.out.println("  在线发送及时消息： ");
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
			}
		}
		
		if(n==0)
		{
			System.out.println("  发送不在线消息 ");
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
		
		
		

		/*if(LoginAndSessionList.size()==1 && LoginAndSessionList.contains(userandSession))
		{
			System.out.println(" 在线登录用户只有一个 " );			
			//List<ChatLog> chatlists=new ArrayList<ChatLog>();		//TODO待优化批量 批量发送未读信息，防止服务器过多	 
			ChatLog chatLog=new ChatLog();
			chatLog.setSenderid(loginUserid);
			chatLog.setReceiverid(chatUserid);
			chatLog.setIsRead("否");
			chatLog.setSendtime(new Date());
			chatLog.setSendcontent(message);
			chatLog.setRemark("");
			chatLog.setStatus(0);
			chatLogDao.sender(chatLog);
			//loginUserToUnloginChatlists.add(chatLog);			   //TODO待优化批量 批量发送未读信息，防止服务器过多			
		}
		else if(LoginAndSessionList.size()>1)
		{			
			System.out.println("loginUserid " + loginUserid);
			System.out.println("chatUserid: " + chatUserid);
						
			int n=0; //判断聊天客户是否在线，在线发送及时嘻嘻			
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
		}*/
		
}



/*
@OnMessage
public void onMessage(String message, Session session) 
    	throws IOException, InterruptedException {
		
		System.out.println("客户端 "+session.toString()+" 发送的信息： " + message);
				
		if(LoginAndSessionList.size()>1)
		{			
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
		else if(LoginAndSessionList.size()==1 && LoginAndSessionList.contains(userandSession))
		{
			System.out.println(" 在线登录用户只有一个 " );
			
			//List<ChatLog> chatlists=new ArrayList<ChatLog>();			
			ChatLog chatLog=new ChatLog();
			chatLog.setSenderid(loginUserid);
			chatLog.setReceiverid(chatUserid);
			chatLog.setIsRead("否");
			chatLog.setSendtime(new Date());
			chatLog.setSendcontent(message);
			chatLog.setRemark("");
			chatLog.setStatus(0);
			loginUserToUnloginChatlists.add(chatLog);			
			
		}
}
*/

/**
 * 查询未读信息
 * @param session
 * @param chatUserid
 * @param loginUserid
 * @param Readstatus
 */
public void getUnReadChatLog(Session session,int chatUserid, int loginUserid,String Readstatus)
{
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
    	
    	
        //查找给登录用户发的未读信息
        List<ChatLog> chatLogList=chatLogDao.getUnReadChatLog(chatUserid, loginUserid,Readstatus);
        System.out.println("chatLogList 长度"+chatLogList.size());        
    	
    	if(chatLogList.size()>0)
    	{
    		List<ChatLog> setReadStatusList=new ArrayList<ChatLog>();
	    	for(int i=0;i<chatLogList.size();i++)
	    	{
	    		ChatLog c=chatLogList.get(i);
				session.getBasicRemote().sendText(c.getSendcontent());
				System.out.println("chatlogid: "+c.getChatlogId()+" neirong: "+c.getSendcontent());
				c.setIsRead("是");
				setReadStatusList.add(c);
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
	List<LoginAndSession> closeLoginAndSessionList =LoginAndSessionList;
	LoginAndSession closeloginandsession = null;
	for(int i=0;i<closeLoginAndSessionList.size();i++)
	{
		LoginAndSession loginandsession=closeLoginAndSessionList.get(i);
		System.out.println("关闭的 登录用户: " + loginandsession.getLogin_Userid());
		System.out.println("关闭的 聊友: " + loginandsession.getChat_Userid());
		System.out.println("关闭的 session: " + loginandsession.getSession().toString());
		if(this.session.equals(loginandsession.getSession()))
		{
			closeloginandsession=loginandsession;
		}
	}
	if(closeloginandsession!=null)
	{
		LoginAndSessionList.remove(closeloginandsession);
	}	
    webSocketSet.remove(this);  //从set中删除
    /*if(userandSession!=null)
    {*/
       // LoginAndSessionList.remove(userandSession);
       //System.out.println("LoginAndSessionList 移除"+userandSession+" "+LoginAndSessionList.size());
    //}
}
 
@OnError
public void onError(Throwable throwable) {
    System.out.println("server端 websocket 报错："+session.toString()+" 报错内容： "+throwable.getMessage());
}

	
	
}
