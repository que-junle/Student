package com.exception.service;

import java.util.ArrayList;

import com.exception.bean.RecordBean;
import com.exception.bean.RoomBean;

/** 
* @author 作者 Your-Name: 
* @version 创建时间：2021年5月31日 下午2:34:07 
* 类说明 
*/
public interface RecordService {

	//插入一条房间维护记录
	public abstract boolean addRoomRecord(RecordBean recordBean);
	
	//更新一条房间维护记录
	public abstract boolean handleRoomRecord(RecordBean recordBean);
	
	//查询所有的房间维护记录
	public abstract ArrayList<RecordBean> queryAllRecordByRoom(RoomBean roomBean);
}
