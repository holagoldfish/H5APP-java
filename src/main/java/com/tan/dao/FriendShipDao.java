package com.tan.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

import com.tan.model.*;
import com.tan.service.UserService;
import com.mysql.jdbc.Driver;

@Component
public class FriendShipDao {
	
    @SuppressWarnings("static-access")  
    public int add(FriendShip friendShip)  
    {      	
    	 //使用MyBatis提供的Resources类加载mybatis的配置文件（它也加载关联的映射文件）
        //Reader reader = Resources.getResourceAsReader(resource); 
        //构建sqlSession的工厂
        //SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
    	
        SqlSession session = GetSqlSessionFactory.getInstance().getSqlSessionFactory().openSession();        
        FriendShipMapper friendShipMapper = session.getMapper(FriendShipMapper.class);           
        int id=friendShipMapper.addfriendship(friendShip); 
        session.commit();  // 一定要有的。
        session.close();
        return friendShip.getId();  
    }  
    
    
    @SuppressWarnings("static-access")  
    public FriendShip getfriendship(FriendShip friendShip)  
    {         	
        SqlSession session = GetSqlSessionFactory.getInstance().getSqlSessionFactory().openSession();        
        FriendShipMapper friendShipMapper = session.getMapper(FriendShipMapper.class);           
        FriendShip friendShip1=friendShipMapper.getfriendship(friendShip); 
        session.commit();  // 一定要有的。
        session.close();
        return friendShip1;
    }  
    
    @SuppressWarnings("static-access")  
    public List<FriendShip> getfriendshipList(FriendShip friendShip)  
    {         	
        SqlSession session = GetSqlSessionFactory.getInstance().getSqlSessionFactory().openSession();        
        FriendShipMapper friendShipMapper = session.getMapper(FriendShipMapper.class);           
        List<FriendShip> friendShip1=friendShipMapper.getfriendshipList(friendShip); 
        session.commit();  // 一定要有的。
        session.close();
        return friendShip1;
    }  
    
    @SuppressWarnings("static-access")  
    public List<FriendShip> getmyfriendshipList(FriendShip friendShip)  
    {         	
        SqlSession session = GetSqlSessionFactory.getInstance().getSqlSessionFactory().openSession();        
        FriendShipMapper friendShipMapper = session.getMapper(FriendShipMapper.class);           
        List<FriendShip> friendShip1=friendShipMapper.getfriendshipList(friendShip); 
        session.commit();  // 一定要有的。
        session.close();
        return friendShip1;
    } 
    
}
