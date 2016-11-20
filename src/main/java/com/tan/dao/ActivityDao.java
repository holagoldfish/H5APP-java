package com.tan.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

import com.tan.model.*;
import com.tan.service.UserService;
import com.mysql.jdbc.Driver;

@Component
public class ActivityDao {
	
    @SuppressWarnings("static-access")  
    public int addActivity(Activity activity)  
    {      	    	
        SqlSession session = GetSqlSessionFactory.getInstance().getSqlSessionFactory().openSession(); 
        ActivityMapper activityMapper = session.getMapper(ActivityMapper.class);   
        System.out.println("ceshi222:userMapper "+activityMapper);
        
        int activityId=activityMapper.addActivity(activity);
        System.out.println("addUserSignIn222："+activity.getId()); 
        session.commit();
        session.close();
        return activity.getId();        
    }  
	
    @SuppressWarnings("static-access") 
    public List<Activity> getAllActivityLimit(int start,int end)  
    {  
        SqlSession session = GetSqlSessionFactory.getInstance().getSqlSessionFactory().openSession(); 
        ActivityMapper activityMapper = session.getMapper(ActivityMapper.class);   
        
        List<Activity> userslist=activityMapper.getAllActivityLimit(start,end);
        //System.out.println("ceshi222:getBirthdayStr "+userslist.get(0).getUserInfo().getBirthdayStr());
        session.commit();
        session.close();
        return userslist;        
    }  
	
    @SuppressWarnings("static-access") 
    public List<Activity> getActivityByType(int start,int end,int activityType)  
    {  
        SqlSession session = GetSqlSessionFactory.getInstance().getSqlSessionFactory().openSession(); 
        ActivityMapper activityMapper = session.getMapper(ActivityMapper.class);   
        
        List<Activity> userslist=activityMapper.getActivityByType(start,end,activityType);
        //System.out.println("ceshi222:getBirthdayStr "+userslist.get(0).getUserInfo().getBirthdayStr());
        session.commit();
        session.close();
        return userslist;        
    }  
    
    
}
