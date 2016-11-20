package com.tan.model;

public class Users {

	//实体类的属性和表的字段名称一一对应
    private int id;
    private String username;
    private String pwd;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return username;
    }

    public void setName(String name) {
        this.username = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setAge(String pwd) {
        this.pwd = pwd;
    }

    @Override
    public String toString() {
        return "[id=" + id + ", username=" + username + ", pwd=" + pwd + "]";
    }
	
}
