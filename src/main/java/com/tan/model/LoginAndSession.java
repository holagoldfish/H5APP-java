package com.tan.model;


import javax.websocket.Session;
public class LoginAndSession {

	public String getLogin_Userid() {
		return login_Userid;
	}
	public void setLogin_Userid(String login_Userid) {
		this.login_Userid = login_Userid;
	}
	public String getChat_Userid() {
		return chat_Userid;
	}
	public void setChat_Userid(String chat_Userid) {
		this.chat_Userid = chat_Userid;
	}
	public Session getSession() {
		return session;
	}
	public void setSession(Session session) {
		this.session = session;
	}
	private String login_Userid;
	private String chat_Userid;
	private Session session;
	
}
