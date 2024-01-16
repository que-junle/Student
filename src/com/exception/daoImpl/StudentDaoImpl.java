package com.exception.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.exception.bean.ClassBean;
import com.exception.bean.RoomBean;
import com.exception.bean.StudentBean;
import com.exception.dao.StudentDao;
import com.exception.util.DateUtil;
import com.exception.util.DbUtil;

/** 
* @author 作者 Your-Name: 
* @version 创建时间：2021年5月24日 下午3:00:36 
* 类说明 
*/
public class StudentDaoImpl extends BaseDao implements StudentDao{

	private ArrayList<StudentBean> arrayList = new ArrayList<StudentBean>();
	
	//根据班级id查找学生，这个方法其实没什么用，食之无味，弃之可惜，业务中没有这样的要求，并且如果有的话，这个代码还需要继续修改
	//	@Override
	//	public ArrayList<StudentBean> selectStudentByClassId(ClassBean classBean) {
	//		ArrayList<StudentBean> arrayList = new ArrayList<>();
	//		StudentBean studentBean;
	//		ClassBean claBean;
	//		RoomBean roomBean;
	//		this.con = DbUtil.getConnection();
	//		String sql = "SELECT t_student.pk_id,t_student.f_name,t_student.f_sex,t_student.f_photo,t_student.f_phone,t_student.f_time,t_class.pk_id,t_class.f_name,t_class.f_teacher,t_class.f_time,t_room.pk_id,t_room.f_address,t_room.f_maxNum,t_room.f_stucount,t_room.f_price,t_room.f_master,t_room.f_state,t_room.f_type,t_room.f_sextype,t_room.f_pay,t_room.f_time FROM t_class LEFT JOIN t_student ON t_student.fk_classid = ? AND t_student.fk_classid = t_class.pk_id JOIN t_room ON t_student.fk_roomid = t_room.pk_id";
	//		try {
	//			this.preparedStatement = this.con.prepareStatement(sql);
	//			this.preparedStatement.setInt(1, classBean.getPk_id());
	//			ResultSet executeQuery = this.preparedStatement.executeQuery();
	//			while(executeQuery.next()) {
	//				claBean = new ClassBean(executeQuery.getInt(7),executeQuery.getString(8),executeQuery.getString(9),executeQuery.getTime(10));
	//				roomBean = new RoomBean(executeQuery.getInt(11), executeQuery.getString(12), executeQuery.getInt(13), executeQuery.getInt(14), executeQuery.getInt(15), executeQuery.getString(16), executeQuery.getInt(17), executeQuery.getString(18), executeQuery.getInt(19), executeQuery.getString(20), executeQuery.getDate(21));
	//				studentBean = new StudentBean(executeQuery.getInt(1),executeQuery.getString(2),executeQuery.getInt(3),executeQuery.getString(4),executeQuery.getString(5),executeQuery.getDate(6),roomBean,claBean);
	//				arrayList.add(studentBean);
	//			}
	//		} catch (SQLException e) {
	//			// TODO Auto-generated catch block
	//			e.printStackTrace();
	//		}finally {
	//			this.closeAll();
	//		}
	//		return arrayList;
	//	}

	
	//判断此班级内是否有学生
	@Override
	public boolean selectStudentByClassId(int classID) {
		boolean flag = false;
		String sql = "select t_student.pk_id FROM t_student LEFT JOIN t_class ON t_student.fk_classid = t_class.pk_id AND t_class.pk_id = ?";
		this.con = DbUtil.getConnection();
		try {
			this.preparedStatement = this.con.prepareStatement(sql);
			this.preparedStatement.setInt(1, classID);
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
	public ArrayList<StudentBean> selectStudentByRoomId(RoomBean roomBean) {
		arrayList.clear();
		StudentBean studentBean;
		ClassBean claBean;
		this.con = DbUtil.getConnection();
		String sql = "SELECT t_student.pk_id,t_student.f_name,t_student.f_sex,t_student.f_photo,t_student.f_phone,t_student.f_time,t_class.pk_id,t_class.f_name,t_class.f_teacher,t_class.f_time FROM t_student JOIN t_room ON t_student.fk_roomid = ? AND t_student.fk_roomid = t_room.pk_id JOIN t_class ON t_student.fk_classid = t_class.pk_id";
		try {
			this.preparedStatement = this.con.prepareStatement(sql);
			this.preparedStatement.setInt(1, roomBean.getPk_id());
			ResultSet executeQuery = this.preparedStatement.executeQuery();
			while(executeQuery.next()) {
				claBean = new ClassBean(executeQuery.getInt(7),executeQuery.getString(8),executeQuery.getString(9),executeQuery.getTime(10));
				studentBean = new StudentBean(executeQuery.getInt(1),executeQuery.getString(2),executeQuery.getInt(3),executeQuery.getString(4),executeQuery.getString(5),executeQuery.getDate(6),roomBean,claBean);
				arrayList.add(studentBean);
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
	public ArrayList<StudentBean> selectAllStudent() {
		arrayList.clear();
		String sql = "select t_student.pk_id,t_student.f_name,t_student.f_sex,t_student.f_photo,t_student.f_phone,t_student.f_time,t_class.pk_id,t_class.f_name,t_class.f_teacher,t_class.f_time,t_room.pk_id,t_room.f_address,t_room.f_maxnum,t_room.f_stucount,t_room.f_price,t_room.f_master,t_room.f_phone,t_room.f_state,t_room.f_type,t_room.f_sextype,t_room.f_pay,t_room.f_time FROM t_student LEFT JOIN t_class ON t_student.fk_classid = t_class.pk_id JOIN t_room ON t_student.fk_roomid = t_room.pk_id ";
		this.con = DbUtil.getConnection();
		StudentBean studentBean = null;
		ClassBean classBean = null;
		RoomBean roomBean = null;
		try {
			this.preparedStatement = this.con.prepareStatement(sql);
			ResultSet executeQuery = this.preparedStatement.executeQuery();
			while(executeQuery.next()) {
				classBean = new ClassBean(executeQuery.getInt(7),executeQuery.getString(8),executeQuery.getString(9),executeQuery.getDate(10));
				roomBean = new RoomBean(executeQuery.getInt(11),executeQuery.getString(12),executeQuery.getInt(13),executeQuery.getInt(14),executeQuery.getInt(15),executeQuery.getString(16),executeQuery.getString(17),executeQuery.getInt(18),executeQuery.getString(19),executeQuery.getInt(20),executeQuery.getString(21),executeQuery.getDate(22));
				studentBean  = new StudentBean(executeQuery.getInt(1),executeQuery.getString(2),executeQuery.getInt(3),executeQuery.getString(4),executeQuery.getString(5),executeQuery.getDate(6),roomBean,classBean);
				arrayList.add(studentBean);
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
	public ArrayList<StudentBean> selectSomeStudentByInfo(String sql) {
		arrayList.clear();
		this.con = DbUtil.getConnection();
		StudentBean studentBean = null;
		ClassBean classBean = null;
		RoomBean roomBean = null;
		try {
			this.preparedStatement = this.con.prepareStatement(sql);
			ResultSet executeQuery = this.preparedStatement.executeQuery();
			while(executeQuery.next()) {
				classBean = new ClassBean(executeQuery.getInt(7),executeQuery.getString(8),executeQuery.getString(9),executeQuery.getDate(10));
				roomBean = new RoomBean(executeQuery.getInt(11),executeQuery.getString(12),executeQuery.getInt(13),executeQuery.getInt(14),executeQuery.getInt(15),executeQuery.getString(16),executeQuery.getString(17),executeQuery.getInt(18),executeQuery.getString(19),executeQuery.getInt(20),executeQuery.getString(21),executeQuery.getDate(22));
				studentBean  = new StudentBean(executeQuery.getInt(1),executeQuery.getString(2),executeQuery.getInt(3),executeQuery.getString(4),executeQuery.getString(5),executeQuery.getDate(6),roomBean,classBean);
				arrayList.add(studentBean);
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
	public boolean addStudentInfo(StudentBean stu) {
		boolean flag = false;
		this.con = DbUtil.getConnection();
		String sql = "insert into t_student values(NULL,?,?,?,?,?,?,?)";
		try {
			this.preparedStatement = this.con.prepareStatement(sql);
			this.preparedStatement.setString(1,stu.getF_name());
			this.preparedStatement.setInt(2, stu.getF_sex());
			this.preparedStatement.setString(3, stu.getF_photo());
			this.preparedStatement.setString(4, stu.getF_phone());
			this.preparedStatement.setString(5, DateUtil.DateToSqlDate(stu.getF_time()));
			this.preparedStatement.setInt(6, stu.getClassBean().getPk_id());
			this.preparedStatement.setInt(7,stu.getRoomBean().getPk_id());
			if(this.preparedStatement.executeUpdate() > 0) {
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

	/*
		UPDATE t_room SET t_room.f_stucount = t_room.f_stucount + 1 WHERE  t_room.pk_id = ?
		UPDATE t_room SET t_room.f_stucount = t_room.f_stucount - 1 WHERE t_room.pk_id = ?
		UPDATE t_student SET t_student.fk_roomid = ? WHERE t_student.pk_id = ?
	 */
	@Override
	public boolean studentExchangeRoom(StudentBean stu, int oldId, int newId) {
		boolean flag = false;
		String sql1 = "UPDATE t_room SET t_room.f_stucount = t_room.f_stucount + 1 WHERE  t_room.pk_id = ?";
		String sql2 = "UPDATE t_room SET t_room.f_stucount = t_room.f_stucount - 1 WHERE t_room.pk_id = ?";
		String sql3 = "UPDATE t_student SET t_student.fk_roomid = ? WHERE t_student.pk_id = ?";
		this.con = DbUtil.getConnection();
		try {
			con.setAutoCommit(false);
			this.preparedStatement = this.con.prepareStatement(sql1);
			this.preparedStatement.setInt(1, newId);
			if(this.preparedStatement.executeUpdate() > 0) {
				this.preparedStatement = this.con.prepareStatement(sql2);
				this.preparedStatement.setInt(1, oldId);
				if(this.preparedStatement.executeUpdate() > 0) {
					this.preparedStatement = this.con.prepareStatement(sql3);
					this.preparedStatement.setInt(1, newId);
					this.preparedStatement.setInt(2, stu.getPk_id());
					
					if(this.preparedStatement.executeUpdate() > 0) {
						flag = true;
						con.commit();
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
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
	public boolean deleteStudentInfo(StudentBean stu) {
		boolean flag = false;
		String sql = "delete from t_student where pk_id = ?";
		this.con = DbUtil.getConnection();
		try {
			this.preparedStatement = this.con.prepareStatement(sql);
			this.preparedStatement.setInt(1, stu.getPk_id());
			if(this.preparedStatement.executeUpdate() > 0) {
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
