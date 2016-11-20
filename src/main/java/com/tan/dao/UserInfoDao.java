package com.tan.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;
import com.tan.model.*;
import com.tan.service.UserService;
import com.mysql.jdbc.Driver;

@Component
public class UserInfoDao {
	
    @SuppressWarnings("static-access")  
    public int addUserInfo(UserInfo userInfo)  
    {  
    	
    	 //使用MyBatis提供的Resources类加载mybatis的配置文件（它也加载关联的映射文件）
        //Reader reader = Resources.getResourceAsReader(resource); 
        //构建sqlSession的工厂
        //SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
    	
    	System.out.println("userSignIn");
    	    	
        SqlSession session = GetSqlSessionFactory.getInstance().getSqlSessionFactory().openSession(); 
       /* UserInfoMapper userInfoMapper = session.getMapper(UserInfoMapper.class);   */
        /*System.out.println("ceshi222:userMapper "+userMapper);
        System.out.println("ceshi222:userMapper "+userInfo.getPhonenumber());*/
      /*  userInfoMapper.insertUserInfo1(userInfo);*/
        
        UserSignInMapper userSignInMapper = session.getMapper(UserSignInMapper.class);   
        userSignInMapper.insertUserInfo1(userInfo);
        
        session.commit();  // 一定要有的。
        session.close();
        
        return userInfo.getId();
        
       // System.out.println(userSignIn);
        
        
        /*UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user1 = new User();
        user1.setName("xiaowei");
        user1.setEmail("xiaowei@111.com");
        user1.setPassword("456");
        userMapper.insertUser1(user1);
        sqlSession.commit(); */ // 一定要有的。
        
        /*SqlSessionFactory sessionFactory;
        sessionFactory = new SqlSessionFactoryBuilder()
          .build(Resources.getResourceAsReader("mybatis-config.xml"));
        SqlSession sqlSession = sessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user2 = new User();
        user2.setName("xiaohu");
        user2.setEmail("xiaohu@111.com");
        user2.setPassword("456");
        userMapper.insertUser2(user2);
        sqlSession.commit();
        System.out.println(user2);*/
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        /**
         * 映射sql的标识字符串，
         * me.gacl.mapping.userMapper是userMapper.xml文件中mapper标签的namespace属性的值，
         * getUser是select标签的id属性值，通过select标签的id属性值就可以找到要执行的SQL
         */
       /* String statement = "me.gacl.mapping.userMapper.getUser";//映射sql的标识字符串
        //执行查询返回一个唯一user对象的sql
        User user = session.selectOne(statement, 1);
        System.out.println(user);
        
        
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
        }  */
    }  
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

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
