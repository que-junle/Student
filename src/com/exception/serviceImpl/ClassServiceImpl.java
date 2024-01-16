package com.exception.serviceImpl;

import java.util.ArrayList;
import java.util.Date;

import com.exception.bean.ClassBean;
import com.exception.bean.StudentBean;
import com.exception.dao.ClassDao;
import com.exception.dao.StudentDao;
import com.exception.daoImpl.ClassDaoImpl;
import com.exception.daoImpl.StudentDaoImpl;
import com.exception.service.ClassService;
import com.exception.service.StudentService;
import com.exception.util.DateUtil;
import com.exception.util.StrUtil;

/** 
* @author 作者 Your-Name: 
* @version 创建时间：2021年5月24日 上午11:51:44 
* 类说明 
*/
public class ClassServiceImpl implements ClassService{

	private ClassDao classDao = new ClassDaoImpl();
	private StudentDao studentDao = new StudentDaoImpl();
	
	@Override
	public String addClass(ClassBean classBean) {
		if(classDao.classNameIsExist(classBean.getF_name()) != 0)
			return "添加失败！班级名重复";
		if(classDao.insertClass(classBean)) 
			return "添加成功！";
		else 
			return "添加失败！";
	}


	@Override
	public ClassBean editClass(ClassBean classBean) {
		int classId = classDao.classNameIsExist(classBean.getF_name());
		if(classId == classBean.getPk_id() || classId == 0)
			if(classDao.editClass(classBean))
				return classBean;
			else
				return null;
		else
			return null;
	}

	@Override
	public ArrayList<ClassBean> queryAllClass() {
		return classDao.selectAllClass();
	}
	/*
	 *  返回值为1代表删除成功
	 *  返回值为0代表删除失败
	 *  返回值为-1代表无法删除
	 */
	@Override
	public boolean deleteClass(ClassBean classBean) {
		boolean flag = studentDao.selectStudentByClassId(classBean.getPk_id());
		if(flag) 
			return false;
		else
			return classDao.deleteClass(classBean);
	}


	/*
	 * 因为我们涉及到了多个条件的查询，组合的方式有很多，为了方便起间，我们使用字符串拼接的方式，将需要的条件进行拼接，不需要的条件过滤即可.
	 */
	@Override
	public ArrayList<ClassBean> querySomeClassByInfo(String classNumb, String className, String classTeacher,Date date) {
		StringBuffer stringBuffer = new StringBuffer("select pk_id,f_name,f_teacher,f_time from t_class where ");
		//这里参数最多是四个，我们为了安全起间，给他开辟五个空间
		String [] sqlStrs = new String [5];
		int i = 0;
		if(StrUtil.notEmpty(classNumb)) {
			classNumb = "pk_id = " + classNumb;
			sqlStrs[i++] = classNumb;
		}
		if(StrUtil.notEmpty(className)) {
			className = "f_name like '%"+className+"%'";
			sqlStrs[i++] = className;
		}
		if(StrUtil.notEmpty(classTeacher)) {
			classTeacher = "f_teacher like '%" + classTeacher + "%'";
			sqlStrs[i++] = classTeacher;
		}
		if(date != null) {
			String dateStr = "f_time = '" + DateUtil.DateToSqlDate(date)+"'";
			sqlStrs[i++] = dateStr;
		}
		//进行拼接
		stringBuffer = StrUtil.splicingStrs(i, stringBuffer, sqlStrs);
		return classDao.selectSomeClassByInfo(stringBuffer.toString());
	}
}
