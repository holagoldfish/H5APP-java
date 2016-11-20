package com.tan.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.tan.model.*;

public interface UserSignInMapper {  
	  UserSignIn findById(int id);
	  int findUserIdByPhonenumber(String phonenumber);
	  int insertUserSignIn1(UserSignIn userSignIn);
	  int insertUserSignIn2(UserSignIn userSignIn); 
	  int insertUserInfo1(UserInfo userInfo);
	  UserSignIn getUserByphoneAndPwd(UserSignIn userSignIn);
	  List<UserSignIn> getAllUser();
	  List<UserSignIn> getAllUserLimit(@Param("offset") int offset, @Param("limit") int limit); 
	  List<UserSignIn> getUserfriend(@Param("offset") int offset, @Param("limit") int limit,@Param("userid") int userid);
	  List<UserSignIn> getUserInfo(@Param("userid") int userid); 
}  