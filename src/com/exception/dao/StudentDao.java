package com.exception.dao;

import java.util.ArrayList;

import com.exception.bean.ClassBean;
import com.exception.bean.RoomBean;
import com.exception.bean.StudentBean;

/** 
* @author 作者 Your-Name: 
* @version 创建时间：2021年5月24日 下午2:58:17 
* 类说明 
*/
public interface StudentDao {
	
	//根据班级id查找学生-----此方法已作废
//	public abstract ArrayList<StudentBean> selectStudentByClassId(ClassBean classBean);
	
	//根据房间id查找学生
	public abstract ArrayList<StudentBean> selectStudentByRoomId(RoomBean roomBean);
	
	//判断此班级内是否有学生
	public abstract boolean selectStudentByClassId(int classID);
	
	//查找全部学生
	public abstract ArrayList<StudentBean> selectAllStudent();
	
	//查找符合查询条件的学生
	public abstract ArrayList<StudentBean> selectSomeStudentByInfo(String sql);
	
	//新增学生
	public abstract boolean addStudentInfo(StudentBean stu);
	
	//学生换房
	public abstract boolean studentExchangeRoom(StudentBean stu,int oldId,int newId);
	
	//学生退房
	public abstract boolean deleteStudentInfo(StudentBean stu);
}
