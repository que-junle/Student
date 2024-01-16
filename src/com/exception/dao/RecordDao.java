package com.exception.dao;

import java.util.ArrayList;

import com.exception.bean.RecordBean;

/** 
* @author 作者 Your-Name: 
* @version 创建时间：2021年5月31日 下午12:48:08 
* 类说明 
*/
public interface RecordDao {
	
	//根据房间id查找对应的维修记录
	public abstract ArrayList<RecordBean> selectRecordByRoomId(int roomID);
	
	//插入一条维修记录
	public abstract boolean insertRecord(RecordBean recordBean);
	
	//更新一条维修记录
	public abstract boolean updateRecord(RecordBean recordBean);
}
