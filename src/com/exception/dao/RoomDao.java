package com.exception.dao;

import java.util.ArrayList;

import com.exception.bean.RoomBean;

/** 
* @author 作者 Your-Name: 
* @version 创建时间：2021年5月25日 下午8:46:45 
* 类说明 
*/
public interface RoomDao {
	
	//查询所有房间
	public abstract ArrayList<RoomBean> selectAllRoom();
	
	//查询符合某种条件的房间
	public abstract ArrayList<RoomBean> selectSomeRoomByInfo(String sql);
	
	//判断房间是否存在
	public abstract boolean roomAddressIsExist(String roomAddress);
	
	//新增房间
	public abstract boolean insertRoom(RoomBean roomBean);
	
	//删除房间
	public abstract boolean deleteRoom(RoomBean roomBean);
}
