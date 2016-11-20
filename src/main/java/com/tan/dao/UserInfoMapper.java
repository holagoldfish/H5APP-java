package com.tan.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.tan.model.*;

public interface UserInfoMapper {  
	  UserInfo findById(int id);
	  void insertUserInfo1(UserInfo userInfo);
	 /* void insertUserInfo2(UserInfo userInfo); */
}  