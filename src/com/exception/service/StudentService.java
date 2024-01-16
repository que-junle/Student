package com.exception.service;

import java.util.ArrayList;

import com.exception.bean.ClassBean;
import com.exception.bean.StudentBean;

/** 
* @author 作者 Your-Name: 
* @version 创建时间：2021年5月24日 下午2:55:48 
* 类说明 
*/
public interface StudentService {
	
	//查询所有学生
	public abstract ArrayList<StudentBean> queryAllStudent();
	
	//查询符合条件的学生
	public abstract ArrayList<StudentBean> querySomeStudentByInfo(StudentBean stu);
	
	//学生入住
	public abstract boolean addStudent(StudentBean stu);
	
	//学生换房
	public abstract boolean exchangeRoom(StudentBean stu,int oldId,int newId);

	//学生退房
	public abstract boolean deleteStudent(StudentBean stu);
}
