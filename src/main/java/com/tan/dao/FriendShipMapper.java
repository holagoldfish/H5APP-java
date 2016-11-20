package com.tan.dao;

import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.tan.model.*;

public interface FriendShipMapper{   
	public int addfriendship(FriendShip friendShip);
	public FriendShip getfriendship(FriendShip friendShip);
	public List<FriendShip> getfriendshipList(FriendShip friendShip);
	public List<FriendShip> getmyfriendshipList(FriendShip friendShip);
	
}  