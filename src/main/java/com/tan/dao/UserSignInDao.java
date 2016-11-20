package com.tan.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

import com.tan.model.*;
import com.tan.service.UserService;
import com.mysql.jdbc.Driver;

@Component
public class UserSignInDao {
	
    @SuppressWarnings("static-access")  
    public int addUserSignIn(UserSignIn userSignIn)  
    {  
    	
    	 //使用MyBatis提供的Resources类加载mybatis的配置文件（它也加载关联的映射文件）
        //Reader reader = Resources.getResourceAsReader(resource); 
        //构建sqlSession的工厂
        //SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
    	    	
        SqlSession session = GetSqlSessionFactory.getInstance().getSqlSessionFactory().openSession(); 
        System.out.println("ceshi:sessionaaaaaaaaaaaaaaaaaaa "+session);
        UserSignInMapper userMapper = session.getMapper(UserSignInMapper.class);   
        System.out.println("ceshi222:userMapper "+userMapper);
        System.out.println("ceshi222:userMapper "+userSignIn.getPhonenumber());
        
        int user_id=userMapper.insertUserSignIn1(userSignIn);
        System.out.println("addUserSignIn222："+userSignIn.getId()); 
        session.commit();
        session.close();
        return userSignIn.getId();
        
        /*UserSignInMapper userMapper = session.getMapper(UserInfoMapper.class);
        User user1 = new User();
        user1.setName("xiaowei");
        user1.setEmail("xiaowei@111.com");
        user1.setPassword("456");
        userMapper.insertUser1(user1);*/
        
        
        
        /*System.out.println("addUserSignIn："+user_id);     */
           
        
        
       /* int row = cityMapper.insert(city);    //insrt不再返回主键，只返回响应行数，这点和ibatis不同了
        System.out.println("响应的行数："+row);
        //取得自增的标识列 ID的值
        System.out.println("新插入的数据的ID："+city.getId());*/
        
        
         // 一定要有的。
       // userMapper.findUserIdByPhonenumber(userSignIn.getPhonenumber());
        //session.close();

  	  //System.out.println("addUserSignIn："+user_id);
        //return 11;
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
	
	
	
	
	
	
	
    public UserSignIn getUserByphoneAndPwd(UserSignIn userSignIn)  
    {  
    	
    	 //使用MyBatis提供的Resources类加载mybatis的配置文件（它也加载关联的映射文件）
        //Reader reader = Resources.getResourceAsReader(resource); 
        //构建sqlSession的工厂
        //SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
    	    	
        SqlSession session = GetSqlSessionFactory.getInstance().getSqlSessionFactory().openSession(); 
        System.out.println("ceshi:session "+session);
        UserSignInMapper userMapper = session.getMapper(UserSignInMapper.class);   
        System.out.println("ceshi222:userMapper "+userMapper);
        System.out.println("ceshi222:userMapper333 "+userSignIn.getPhonenumber());
        
        UserSignIn users=userMapper.getUserByphoneAndPwd(userSignIn);
        if(users==null)
        {
        	System.out.println("weikong");
        }
        System.out.println("addUserSignIn222："+users.toString()); 
        session.commit();
        session.close();
        return users;
        
        
       /* int row = cityMapper.insert(city);    //insrt不再返回主键，只返回响应行数，这点和ibatis不同了
        System.out.println("响应的行数："+row);
        //取得自增的标识列 ID的值
        System.out.println("新插入的数据的ID："+city.getId());*/
         // 一定要有的。
       // userMapper.findUserIdByPhonenumber(userSignIn.getPhonenumber());
        //session.close();
        /*UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user1 = new User();
        user1.setName("xiaowei");
        user1.setEmail("xiaowei@111.com");
        user1.setPassword("456");
        userMapper.insertUser1(user1);
        sqlSession.commit(); */ // 一定要有的。
        
    }  
	
	
  /*  public List<UserSignIn> getAllUser()  
    {  
        SqlSession session = GetSqlSessionFactory.getInstance().getSqlSessionFactory().openSession(); 
      //  System.out.println("ceshi:session "+session);
        UserSignInMapper userMapper = session.getMapper(UserSignInMapper.class);   
      //  System.out.println("ceshi222:userMapper "+userMapper);
       // System.out.println("ceshi222:userMapper "+userSignIn.getPhonenumber());
        
        List<UserSignIn> userslist=userMapper.getAllUser();
      //  System.out.println("ceshi222:userMapper "+userslist.get(0).getPhonenumber());
        session.commit();
        session.close();
        return userslist;        
    }  
	*/
	
	
    public List<UserSignIn> getAllUser()  
    {  
        SqlSession session = GetSqlSessionFactory.getInstance().getSqlSessionFactory().openSession(); 
      //  System.out.println("ceshi:session "+session);
        UserSignInMapper userMapper = session.getMapper(UserSignInMapper.class);   
      //  System.out.println("ceshi222:userMapper "+userMapper);
       // System.out.println("ceshi222:userMapper "+userSignIn.getPhonenumber());
        
        List<UserSignIn> userslist=userMapper.getAllUser();
        System.out.println("ceshi222:getBirthdayStr "+userslist.get(0).getUserInfo().getBirthdayStr());
        session.commit();
        session.close();
        return userslist;        
    }  
	
	

    public List<UserSignIn> getAllUserLimit(int start,int end)  
    {  
    	 System.out.println("开始："+new Date());
        SqlSession session = GetSqlSessionFactory.getInstance().getSqlSessionFactory().openSession(); 
      //  System.out.println("ceshi:session "+session);
        UserSignInMapper userMapper = session.getMapper(UserSignInMapper.class);   
        
        List<UserSignIn> userslist=userMapper.getAllUserLimit(start,end);
        //System.out.println("ceshi222:getBirthdayStr "+userslist.get(0).getUserInfo().getBirthdayStr());
        session.commit();
        session.close();
        System.out.println("结束："+new Date());
        return userslist;        
    }  
	
    public List<UserSignIn> getUserInfo(int userId)  
    {  
        SqlSession session = GetSqlSessionFactory.getInstance().getSqlSessionFactory().openSession(); 
        UserSignInMapper userMapper = session.getMapper(UserSignInMapper.class);   
        
        List<UserSignIn> userslist=userMapper.getUserInfo(userId);
        //System.out.println("ceshi222:getBirthdayStr "+userslist.get(0).getUserInfo().getBirthdayStr());
        session.commit();
        session.close();
        System.out.println("结束："+new Date());
        return userslist;        
    }  
	
	
	
    

    public List<UserSignIn> getUserfriend(int start,int end,int userid)  
    {  
        SqlSession session = GetSqlSessionFactory.getInstance().getSqlSessionFactory().openSession(); 
      //  System.out.println("ceshi:session "+session);
        UserSignInMapper userMapper = session.getMapper(UserSignInMapper.class);   
        
        List<UserSignIn> userslist=userMapper.getUserfriend(start, end, userid);
        System.out.println("userslist 好友个数： "+userslist.size());
        
        for(int i=0;i<userslist.size();i++)
        {
        	UserSignIn userslist2=userslist.get(i);
        	System.out.println("获取好友昵称： "+userslist2.getUserInfo().getNickname());
        }
        
        session.commit();
        session.close();
        return userslist;        
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
