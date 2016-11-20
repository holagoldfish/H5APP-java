package com.tan.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.tan.model.Users;

public interface CopyOfUserMapper {  
    @Insert("insert into yj_users(username, pwd) values(#{username}, #{pwd})")  
    public int add(Users user);  
      
    @Delete("delete from yj_users where id = #{id}")  
    public int deleteById(int id);  
      
    @Update("update yj_users set name = #{username}, age = #{pwd} where id = #{id}")  
    public int update(Users user);  
      
    @Select("select * from yj_users where id = #{id}")  
    public Users getUserById(int id);  
      
    @Select("select * from mysqlhy1.yj_users")  
    public List<Users> getAllUsers();  
}  