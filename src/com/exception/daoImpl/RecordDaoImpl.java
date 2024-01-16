package com.exception.daoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.exception.bean.RecordBean;
import com.exception.dao.RecordDao;
import com.exception.util.DateUtil;
import com.exception.util.DbUtil;

/** 
* @author 作者 Your-Name: 
* @version 创建时间：2021年5月31日 下午12:53:02 
* 类说明 
*/
public class RecordDaoImpl extends BaseDao implements RecordDao{

	private ArrayList<RecordBean>arrayList = new ArrayList<RecordBean>();
	
	@Override
	public ArrayList<RecordBean> selectRecordByRoomId(int roomID) {
		arrayList.clear();
		RecordBean bean = null;
		this.con = DbUtil.getConnection();
		String sql = "select pk_id,f_time,f_info,f_isok,fk_roomid from t_record where fk_roomid = ?";
		try {
			this.preparedStatement = this.con.prepareStatement(sql);
			this.preparedStatement.setInt(1, roomID);
			ResultSet executeQuery = this.preparedStatement.executeQuery();
			while(executeQuery.next()) {
				bean = new RecordBean(executeQuery.getInt(1),executeQuery.getDate(2),executeQuery.getString(3),executeQuery.getInt(4),executeQuery.getInt(5));
				arrayList.add(bean);
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
	public boolean insertRecord(RecordBean recordBean) {
		boolean flag = false;
		this.con = DbUtil.getConnection();
		String sql = "insert into t_record values(NULL,?,?,?,?)";
		String sql2 = "update t_room set f_state = 2 where pk_id = ?";
		try {
			con.setAutoCommit(false);
			this.preparedStatement = this.con.prepareStatement(sql);
			this.preparedStatement.setString(1, DateUtil.DateToSqlDate(recordBean.getF_time()));
			this.preparedStatement.setString(2, recordBean.getF_info());
			this.preparedStatement.setInt(3, recordBean.getF_isok());
			this.preparedStatement.setInt(4, recordBean.getFk_roomid());
			if(this.preparedStatement.executeUpdate() > 0) {
				this.preparedStatement = this.con.prepareStatement(sql2);
				this.preparedStatement.setInt(1, recordBean.getFk_roomid());
				if(this.preparedStatement.executeUpdate()>0) {
					flag = true;
					con.commit();
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}finally {
			try {
				con.setAutoCommit(true);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.closeAll();
		}
		return flag;
	}

	@Override
	public boolean updateRecord(RecordBean recordBean) {
		boolean flag = false;
		this.con = DbUtil.getConnection();
		String sqlStr1  = "update t_record set f_isok = 1 where pk_id = ?";
		String sqlStr2 = "select pk_id from t_record where fk_roomid = ? and f_isok = 0";
		String sqlStr3 = "update t_room set f_state = 1 where pk_id = ?";
		try {
			this.con.setAutoCommit(false);
			this.preparedStatement = this.con.prepareStatement(sqlStr1);
			this.preparedStatement.setInt(1, recordBean.getPk_id());
			if(this.preparedStatement.executeUpdate() > 0) {
				this.preparedStatement = this.con.prepareStatement(sqlStr2);
				this.preparedStatement.setInt(1, recordBean.getFk_roomid());
				ResultSet executeQuery = this.preparedStatement.executeQuery();
				if(executeQuery.next() == false) {
					this.preparedStatement = this.con.prepareStatement(sqlStr3);
					this.preparedStatement.setInt(1, recordBean.getFk_roomid());
					int n = this.preparedStatement.executeUpdate();
				}
				flag = true;
				con.commit();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}finally {
			try {
				this.con.setAutoCommit(true);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.closeAll();
		}
		return flag;
	}

}
