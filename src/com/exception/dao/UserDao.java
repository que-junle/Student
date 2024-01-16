package com.exception.dao;

import com.exception.bean.UserBean;

/** 
* @author 作者 Your-Name: 
* @version 创建时间：2021年5月17日 下午6:42:38 
* 类说明 
*/
public interface UserDao {
	
	UserBean selectUserByInfo(UserBean userBean);
	
}
