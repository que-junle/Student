package com.exception.serviceImpl;

import java.util.ArrayList;

import com.exception.bean.RoomBean;
import com.exception.dao.RoomDao;
import com.exception.daoImpl.RoomDaoImpl;
import com.exception.service.RoomService;
import com.exception.util.StrUtil;

/** 
* @author 作者 Your-Name: 
* @version 创建时间：2021年5月24日 下午11:25:22 
* 类说明 
*/
public class RoomServiceImpl implements RoomService{

	RoomDao roomDao = new RoomDaoImpl();
	
	@Override
	public ArrayList<RoomBean> queryAllRoom() {
		return roomDao.selectAllRoom();
	}
	
	//根据查询条件凭借对应的sql语句
	@Override
	public ArrayList<RoomBean> querySomeRoomByInfo(String address, int status, int roomable, int sexType) {
		StringBuffer buffer = new StringBuffer("select pk_id,f_address,f_maxnum,f_stucount,f_price,f_master,f_phone,f_state,f_type,f_sextype,f_pay,f_time from t_room where ");
		String sqlStrs[] = new String[5];
		int i = 0;
		if(StrUtil.notEmpty(address)) {
			sqlStrs[i++] = "f_address like '%" + address + "%'";
		}
		if(status != 0) {
			sqlStrs[i++] = "f_state = " + status;
		}
		if(roomable != 0) {
			sqlStrs[i++] = "f_stucount < f_maxnum ";
		}
		if(sexType != 0) {
			sqlStrs[i++]="f_sextype = " + sexType;
		}
		if(i == 0) {
			return roomDao.selectAllRoom();
		}
		buffer = StrUtil.splicingStrs(i, buffer, sqlStrs);
		return roomDao.selectSomeRoomByInfo(buffer.toString());
	}

	//返回1代表成功 返回0代表失败，返回2代表房间重复
	@Override
	public int addRoom(RoomBean roomBean) {
		if(roomDao.roomAddressIsExist(roomBean.getF_address())) {
			return 2;
		}
		if(roomDao.insertRoom(roomBean)) {
			return 1;
		}
		return 0;
	}

	@Override
	public boolean deleteRoom(RoomBean roomBean) {
		if(roomBean.getF_stucount()>0) {
			return false;
		}
		return roomDao.deleteRoom(roomBean);
	}

	@Override
	public ArrayList<RoomBean> queryAllRoomble(int f_sextype) {
		String sql = "select pk_id,f_address,f_maxnum,f_stucount,f_price,f_master,f_phone,f_state,f_type,f_sextype,f_pay,f_time from t_room where f_stucount < f_maxnum and f_sextype = " + f_sextype;
		return roomDao.selectSomeRoomByInfo(sql);
	}

}
