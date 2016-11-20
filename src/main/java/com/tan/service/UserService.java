package com.tan.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

import com.tan.dao.GetSqlSessionFactory;
import com.tan.dao.UserDao;
import com.tan.dao.UserMapper;
import com.tan.model.Users;

@Component
public class UserService {

	private UserDao userDao;
	private Users user;

	public UserDao getUserDao() {
		return userDao;
	}

	/*@Resource
	public void setBookDao(UserDao userDao) {
		this.userDao = userDao;
	}*/
	
	
	/*public void add(Users user){
		userDao.add(user);
	}
	
	public void update(Users user){
		userDao.update(user);
	}*/
	
	
	/*public void get(){
		userDao.get();
	}*/
	
	
	  public List<Users> getAllUsers()  
	    {  
	        if(userDao==null)
	        {
	        	System.out.println(" zai userservice 页面里");
	        	userDao=new UserDao();
	        }
	        return userDao.getAllUsers();  
	    }

	public void add(String id, String username, String password) {
		// TODO Auto-generated method stub
		
	}  
    
	
}
