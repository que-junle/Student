package com.exception.daoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.exception.bean.RecordBean;
import com.exception.bean.RoomBean;
import com.exception.dao.RoomDao;
import com.exception.util.DateUtil;
import com.exception.util.DbUtil;

/** 
* @author 作者 Your-Name: 
* @version 创建时间：2021年5月25日 下午8:49:04 
* 类说明 
*/
public class RoomDaoImpl extends BaseDao implements RoomDao {

	private ArrayList<RoomBean>arrayList = new ArrayList<>();
	
	@Override
	public ArrayList<RoomBean> selectAllRoom() {
		arrayList.clear();
		String sql = "select pk_id,f_address,f_maxnum,f_stucount,f_price,f_master,f_phone,f_state,f_type,f_sextype,f_pay,f_time from t_room";
		RoomBean roomBean = null;
		this.con = DbUtil.getConnection();
		try {
			this.preparedStatement = this.con.prepareStatement(sql);
			ResultSet executeQuery = this.preparedStatement.executeQuery();
			while(executeQuery.next()) {
				roomBean = new RoomBean(executeQuery.getInt(1),executeQuery.getString(2),executeQuery.getInt(3),executeQuery.getInt(4),executeQuery.getInt(5),executeQuery.getString(6),executeQuery.getString(7),executeQuery.getInt(8),executeQuery.getString(9),executeQuery.getInt(10),executeQuery.getString(11),executeQuery.getDate(12));
				roomBean.setStudentBeans(new StudentDaoImpl().selectStudentByRoomId(roomBean));
				//注意此处绝不能和上一行进行颠倒，先赋值在更新
				//更新内部的student数据，不然是有一定人类逻辑问题的，这个和业务无关，可以有这一步操作，也可以没有
				//不过这样做的好处可以保证数据传递的完整性，提高程序扩展性，如果不考虑程序后续有变动，这个函数完全是多此一举
				roomBean.setStudentBean(roomBean);
				arrayList.add(roomBean);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			this.closeAll();
		}
		return arrayList;
	}

	@Override
	public ArrayList<RoomBean> selectSomeRoomByInfo(String sql) {
		arrayList.clear();
		this.con = DbUtil.getConnection();
		try {
			this.preparedStatement = this.con.prepareStatement(sql);
			ResultSet executeQuery = this.preparedStatement.executeQuery();
			RoomBean roomBean  = null;
			while(executeQuery.next()) {
				roomBean = new RoomBean(executeQuery.getInt(1),executeQuery.getString(2),executeQuery.getInt(3),executeQuery.getInt(4),executeQuery.getInt(5),executeQuery.getString(6),executeQuery.getString(7),executeQuery.getInt(8),executeQuery.getString(9),executeQuery.getInt(10),executeQuery.getString(11),executeQuery.getDate(12));
				roomBean.setStudentBeans(new StudentDaoImpl().selectStudentByRoomId(roomBean));
				//注意此处绝不能和上一行进行颠倒，先赋值在更新
				//更新内部的student数据，不然是有一定人类逻辑问题的，这个和业务无关，可以有这一步操作，也可以没有
				//不过这样做的好处可以保证数据传递的完整性，提高程序扩展性，如果不考虑程序后续有变动，这个函数完全是多此一举
				roomBean.setStudentBean(roomBean);
				arrayList.add(roomBean);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			this.closeAll();
		}
		return arrayList;
	}

	@Override
	public boolean roomAddressIsExist(String roomAddress) {
		boolean flag = false;
		String sql = "select pk_id from t_room where f_address = ?";
		this.con = DbUtil.getConnection();
		try {
			this.preparedStatement = this.con.prepareStatement(sql);
			this.preparedStatement.setString(1, roomAddress);
			ResultSet executeQuery = this.preparedStatement.executeQuery();
			if(executeQuery.next()) {
				flag = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			this.closeAll();
		}
		return flag;
	}

	@Override
	public boolean insertRoom(RoomBean roomBean) {
		boolean flag = false;
		String sql = "insert into t_room values(NULL,?,?,?,?,?,?,?,?,?,?,?)";
		this.con = DbUtil.getConnection();
		try {
			this.preparedStatement = this.con.prepareStatement(sql);
			this.preparedStatement.setString(1,roomBean.getF_address());
			this.preparedStatement.setInt(2, roomBean.getF_maxnum());
			this.preparedStatement.setInt(3, roomBean.getF_stucount());
			this.preparedStatement.setInt(4, roomBean.getF_price());
			this.preparedStatement.setString(5, roomBean.getF_master());
			this.preparedStatement.setString(6, roomBean.getF_phone());
			this.preparedStatement.setInt(7, roomBean.getF_state());
			this.preparedStatement.setString(8, roomBean.getF_type());
			this.preparedStatement.setInt(9, roomBean.getF_sextype());
			this.preparedStatement.setString(10, roomBean.getF_pay());
			this.preparedStatement.setString(11, DateUtil.DateToSqlDate(roomBean.getF_time()));
			if(this.preparedStatement.executeUpdate() > 0) {
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			this.closeAll();
		}
		return flag;
	}

	@Override
	public boolean deleteRoom(RoomBean roomBean) {
		boolean flag = false;
		String sql = "delete from t_room where pk_id = ? or f_address = ?";
		this.con = DbUtil.getConnection();
		try {
			this.preparedStatement = this.con.prepareStatement(sql);
			this.preparedStatement.setInt(1, roomBean.getPk_id());
			this.preparedStatement.setString(2, roomBean.getF_address());
			if(this.preparedStatement.executeUpdate() >0 ) {
				flag = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			this.closeAll();
		}
		return flag;
	}

}
