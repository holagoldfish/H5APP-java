package com.tan.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

import com.tan.model.*;
import com.tan.service.UserService;
import com.mysql.jdbc.Driver;

@Component
public class ChatLogDao {
	
    @SuppressWarnings("static-access")  
    public int sender(ChatLog chatLog)  
    {      	   	    	
        SqlSession session = GetSqlSessionFactory.getInstance().getSqlSessionFactory().openSession(); 
        System.out.println("ceshi:session sender"+session);
        ChatLogMapper chatLogMapper = session.getMapper(ChatLogMapper.class);  
        int chatLogId=chatLogMapper.sender(chatLog);
        
        System.out.println("chatLogId "+chatLogId);
        session.commit();
        session.close();
        return chatLogId;        
    }  
    
    @SuppressWarnings("static-access")  
    public void senderChatLogList(List<ChatLog> chatLogList)  
    {      
    	 SqlSession session = GetSqlSessionFactory.getInstance().getSqlSessionFactory().openSession(); 
         System.out.println("ceshi:session "+session);
         ChatLogMapper chatLogMapper = session.getMapper(ChatLogMapper.class);  
         int id=chatLogMapper.senderChatLogList(chatLogList);
         System.out.println("senderChatLogList: "+id);
	     session.commit();
	     session.close();
    }  
    
    @SuppressWarnings("static-access")  
    public List<ChatLog> getUnReadChatLog(int senderUserid,int receiverUserid,String readStatus)  
    {      	   	    	
        SqlSession session = GetSqlSessionFactory.getInstance().getSqlSessionFactory().openSession(); 
        System.out.println("ceshi:session getUnReadChatLog "+session);
        System.out.println("getUnReadChatLog:senderUserid "+senderUserid);
        System.out.println("getUnReadChatLog:receiverUserid "+receiverUserid);
        System.out.println("getUnReadChatLog:readStatus "+readStatus);
        ChatLogMapper chatLogMapper = session.getMapper(ChatLogMapper.class);  
        
        
        List<ChatLog> chatLogList =chatLogMapper.getUnReadChatLog(senderUserid,receiverUserid,readStatus);
        //System.out.println("getSendcontent "+chatLogList.get(0).getSendcontent());
        session.commit();
        session.close();
        return chatLogList;        
    }  
    
    @SuppressWarnings("static-access")  
    public void senderList(List<ChatLog> chatLogList)  
    {      
    	 SqlSession session = GetSqlSessionFactory.getInstance().getSqlSessionFactory().openSession(); 
         System.out.println("ceshi:session "+session);
         ChatLogMapper chatLogMapper = session.getMapper(ChatLogMapper.class);  
        // int chatLogId=chatLogMapper.sender(chatLog);
        
	        for(int i=0;i<chatLogList.size();i++)
	        {
	        	ChatLog chatLog=chatLogList.get(i);
	        	int chatLogId=chatLogMapper.sender(chatLog);
	        }
	        session.commit();
	        session.close();
    }  
    
    @SuppressWarnings("static-access")  
    public void updateChatLog(List<ChatLog> chatLogList)  
    {      
    	 SqlSession session = GetSqlSessionFactory.getInstance().getSqlSessionFactory().openSession(); 
         System.out.println("ceshi:session "+session);
         ChatLogMapper chatLogMapper = session.getMapper(ChatLogMapper.class);  
         chatLogMapper.updateChatLog(chatLogList);
	     session.commit();
	     session.close();
    }  
    
    
    
}
