package com.tan.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

import com.tan.model.Book;
import com.tan.model.Users;
import com.mysql.jdbc.Driver;

@Component
public class CopyOfUserDao {
	
	Users user;

	//ģ����ݿ����
	/*public void add(Users user){
		System.out.print("Add");
	}
	public void update(Users user){
		System.out.print("Update");
	}*/
	
	
    public List<Users> getAllUsers()  
    {  
        List<Users> users = new ArrayList<Users>();  
        SqlSession session = GetSqlSessionFactory.getInstance().getSqlSessionFactory().openSession();  
        
        System.out.println("session: "+session);      
        
        try  
        {  
            // users=session.selectList("user_list_page", new User(),new  
            // RowBounds(offset,pageSize));//未测试过  
            // 注解方式查询  
           // users = session.getMapper(UserService.class).getUsersByPage(offset, pageSize);  
        	 UserMapper userMapper=session.getMapper(UserMapper.class);
        	 
        	 if(userMapper==null)
             {
             	 System.out.println("userMapper: 是空的");
             }
             else
             {
             	 System.out.println("userMapper: 不是空的");
             }
        	 
        	
        	 users=userMapper.getAllUsers();
        	 System.out.println("userMapper aaaaa:"+users);
        	
        } catch (Exception e)  
        {  
            e.printStackTrace();  
        } finally  
        {  
            session.close();  
        }  
        return users;  
    }  
    
    
    /*
     * 
     * 
     @SuppressWarnings("static-access")  
    public List<Users> getAllUsers()  
    {  
        List<Users> users = new ArrayList<Users>();  
        SqlSession session = GetSqlSessionFactory.getInstance().getSqlSessionFactory().openSession();  
        try  
        {  
            // users=session.selectList("user_list_page", new User(),new  
            // RowBounds(offset,pageSize));//未测试过  
            // 注解方式查询  
           // users = session.getMapper(UserService.class).getUsersByPage(offset, pageSize);  
        	 UserMapper userMapper=session.getMapper(UserMapper.class);
        	 users=userMapper.getAllUsers();
        	 System.out.println(users);
        	
        } catch (Exception e)  
        {  
            e.printStackTrace();  
        } finally  
        {  
            session.close();  
        }  
        return users;  
    }  
    
    @SuppressWarnings("static-access")  
    public void add(String id, String username, String password)  
    {  
        SqlSession session = GetSqlSessionFactory.getInstance().getSqlSessionFactory().openSession();  
        try  
        {  
            session.getMapper(UserService.class).add(id, username, password);  
            session.commit();// 提交事务  
        } catch (Exception e)  
        {  
            e.printStackTrace();  
        } finally  
        {  
            session.close();  
        }  
    }  
  
    @SuppressWarnings("static-access")  
    public void delete(String id)  
    {  
        SqlSession session = GetSqlSessionFactory.getInstance().getSqlSessionFactory().openSession();  
        try  
        {  
            session.getMapper(UserService.class).delete(id);  
            session.commit();// 提交事务  
        } catch (Exception e)  
        {  
            e.printStackTrace();  
        } finally  
        {  
            session.close();  
        }  
    }  
  
    @SuppressWarnings("static-access")  
    public int update(String username, String password, String id)  
    {  
        int count = 0;  
        SqlSession session = GetSqlSessionFactory.getInstance().getSqlSessionFactory().openSession();  
        try  
        {  
            // Map<String, Object> map=new HashMap<String, Object>();  
            // map.put("username", user.getUsername());  
            // map.put("password", user.getPassword());  
            // session.update("updateUser", map);  
            count = session.getMapper(UserService.class).update(username, password, id);  
            session.commit();// 提交事务  
        } catch (Exception e)  
        {  
            count = 0;  
            e.printStackTrace();  
        } finally  
        {  
            session.close();  
        }  
        return count;  
    } 
    */
    
    
}
