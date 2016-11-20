package com.tan.model;

import java.util.Date;

public class UserInfo {

	//实体类的属性和表的字段名称一一对应
    private int id;
    private int user_id;
    private String nickname;
    private String sex;
    private String singleOrdouble;
    private Date birthday;
    private String hometown;
    private String city;
    private String job;
    private String heading;
    private String birthdayStr;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
    
    
    
    public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getSingleOrdouble() {
		return singleOrdouble;
	}

	public void setSingleOrdouble(String singleOrdouble) {
		this.singleOrdouble = singleOrdouble;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	public String getBirthdayStr() {
		return birthdayStr;
	}

	public void setBirthdayStr(String birthdayStr) {
		this.birthdayStr = birthdayStr;
	}

	public String getHometown() {
		return hometown;
	}

	public void setHometown(String hometown) {
		this.hometown = hometown;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getHeading() {
		return heading;
	}

	public void setHeading(String heading) {
		this.heading = heading;
	}

	
    @Override
    public String toString() {
        return "[id=" + id + ", user_id=" + user_id + ", nickname=" + nickname + ", sex=" + sex + ", singleOrdouble=" + singleOrdouble + ", birthday=" + birthday + ", hometown=" + hometown + ", city=" + city + ", job=" + job + ", heading=" + heading + "]";
    }
	
}
