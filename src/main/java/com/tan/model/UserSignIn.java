package com.tan.model;

public class UserSignIn {

	//实体类的属性和表的字段名称一一对应
    private int id;
    private String phonenumber;
    private String pwd;
    private int isLogin;
    
    private UserInfo userInfo;

    

	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumer(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
    
    public int getIsLogin() {
        return id;
    }

    public void setIsLogin(int isLogin) {
        this.id = isLogin;
    }
    
    public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

    @Override
    public String toString() {
        return "[id=" + id + ", phonenumer=" + phonenumber + ", pwd=" + pwd + ", isLogin=" + isLogin + ", userInfo=" + userInfo + "]";
    }
	
}
