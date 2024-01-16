package com.exception.serviceImpl;

import java.util.ArrayList;

import com.exception.bean.RecordBean;
import com.exception.bean.RoomBean;
import com.exception.dao.RecordDao;
import com.exception.daoImpl.RecordDaoImpl;
import com.exception.service.RecordService;

/** 
* @author 作者 Your-Name: 
* @version 创建时间：2021年5月31日 下午2:35:06 
* 类说明 
*/
public class RecordServiceImpl implements RecordService{

	RecordDao recordDao = new RecordDaoImpl();
	
	@Override
	public boolean addRoomRecord(RecordBean recordBean) {
		return recordDao.insertRecord(recordBean);
	}

	@Override
	public boolean handleRoomRecord(RecordBean recordBean) {
		return recordDao.updateRecord(recordBean);
	}

	@Override
	public ArrayList<RecordBean> queryAllRecordByRoom(RoomBean roomBean) {
		return recordDao.selectRecordByRoomId(roomBean.getPk_id());
	}

}
