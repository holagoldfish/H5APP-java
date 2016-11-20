package com.tan.dao;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class GetSqlSessionFactory  
{  
    private static SqlSessionFactory sqlSessionFactory = null;        
    private static GetSqlSessionFactory getSqlSessionFactory = null;  
  
    private GetSqlSessionFactory()  
    {  
        String rs = "mybatis.xml";  
        Reader reader = null;  
        try  
        {  
            reader = Resources.getResourceAsReader(rs);  
            
        } catch (IOException e)  
        {  
            e.printStackTrace();  
        }  
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);  
        // 注解方式查询时需要注册mapper  
        //sqlSessionFactory.getConfiguration().addMapper(UserService.class);  
    }  
  
    public static GetSqlSessionFactory getInstance()  
    {  
        if (getSqlSessionFactory == null)  
            getSqlSessionFactory = new GetSqlSessionFactory();  
        return getSqlSessionFactory;  
    }  
  
    public static SqlSessionFactory getSqlSessionFactory()  
    {  
        return sqlSessionFactory;  
    }  
  
}  