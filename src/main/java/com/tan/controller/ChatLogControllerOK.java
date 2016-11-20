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

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/chatlog3/{sender}/{recevied}")
public class ChatLogControllerOK {
	
private ChatLogDao chatLogDao;

//与某个客户端的连接会话，需要通过它来给客户端发送数据
private Session session;
 
private static List<Users> peers =new ArrayList<Users>();
private static List<Users> peers2 = new ArrayList<Users>();
private static CopyOnWriteArraySet<ChatLogControllerOK> webSocketSet = new CopyOnWriteArraySet<ChatLogControllerOK>();


/**
 * 连接建立成功调用的方法
 * @param session  可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
 */
@OnOpen
public void onOpen(@PathParam(value="sender") String sender,@PathParam(value="recevied") String recevied,Session session){
	System.out.println("有新连接加入！当前在线人数为");
	System.out.println("sender: " + sender);
	System.out.println("recevied: " + recevied);
    this.session = session;
    webSocketSet.add(this);     //加入set中
   // addOnlineCount();           //在线数加1
   // System.out.println("有新连接加入！当前在线人数为" + getOnlineCount());
    
    try {
		session.getBasicRemote().sendText("ni hao a啊啊啊啊");
		System.out.println("晕死 ");
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}


@OnMessage
public void onMessage(String message, Session session) 
    	throws IOException, InterruptedException {
		
		System.out.println("4444:6666 " + message);
		System.out.println("session ceshi: " + session.toString());
		session.getBasicRemote().sendText(" 给 发送信息：");	
		
		
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
