package com.tan.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.tan.model.*;

public interface ActivityMapper {  
	  int addActivity(Activity activity);
	  List<Activity> getAllActivityLimit(@Param("offset") int offset, @Param("limit") int limit);  
	  List<Activity> getActivityByType(@Param("offset") int offset, @Param("limit") int limit, @Param("activityType") int activityType);  
	  
}  