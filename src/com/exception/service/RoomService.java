package com.exception.service;

import java.util.ArrayList;

import com.exception.bean.RoomBean;

/** 
* @author 作者 Your-Name: 
* @version 创建时间：2021年5月24日 下午11:22:50 
* 类说明 
*/
public interface RoomService {
	
	//查找全部房间
	public abstract ArrayList<RoomBean> queryAllRoom();
	
	public abstract ArrayList<RoomBean> queryAllRoomble(int f_sextype);
	
	//查找符合某些条件的房间
	public abstract ArrayList<RoomBean> querySomeRoomByInfo(String address,int status,int roomable,int roomType);
	
	//新增房间
	//返回1代表成功 返回0代表失败，返回2代表房间重复
	public abstract int addRoom(RoomBean roomBean);
	
	//删除房间
	public abstract boolean deleteRoom(RoomBean roomBean);
	
	
	
}
