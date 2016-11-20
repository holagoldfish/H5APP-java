package com.tan.model;

import java.util.Date;

public class ChatLog {
	
	 private int chatlogId;
	 public int getChatlogId() {
		return chatlogId;
	}
	public void setChatlogId(int chatlogId) {
		this.chatlogId = chatlogId;
	}
	public int getSenderid() {
		return senderid;
	}
	public void setSenderid(int senderid) {
		this.senderid = senderid;
	}
	public int getReceiverid() {
		return receiverid;
	}
	public void setReceiverid(int receiverid) {
		this.receiverid = receiverid;
	}
	public Date getSendtime() {
		return sendtime;
	}
	public void setSendtime(Date sendtime) {
		this.sendtime = sendtime;
	}
	public String getSendtimeStr() {
		return sendtimeStr;
	}
	public void setSendtimeStr(String sendtimeStr) {
		this.sendtimeStr = sendtimeStr;
	}
	public String getSendcontent() {
		return sendcontent;
	}
	public void setSendcontent(String sendcontent) {
		this.sendcontent = sendcontent;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getIsRead() {
		return isRead;
	}
	public void setIsRead(String isRead) {
		this.isRead = isRead;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	private int senderid;
	 private int receiverid;
	 private Date sendtime;	 
	 private String sendtimeStr;	
	 private String sendcontent;
	 private String remark;
	 private String isRead;
	 private int status;

}
