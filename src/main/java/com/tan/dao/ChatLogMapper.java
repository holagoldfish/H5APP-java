package com.tan.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.tan.model.*;

public interface ChatLogMapper{   
	  int sender(ChatLog chatLog);
	  List<ChatLog> getUnReadChatLog(@Param("sendUserid") int sendUserid,@Param("receiverUserid") int receiverUserid,@Param("readStatus") String readStatus);
	  void updateChatLog(@Param("chatLogList") List<ChatLog> chatLogList);
	  int senderChatLogList(@Param("chatLogList") List<ChatLog> chatLogList);
}  