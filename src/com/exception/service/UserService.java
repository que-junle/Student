package com.exception.service;

import com.exception.bean.UserBean;

/** 
* @author 作者 Your-Name: 
* @version 创建时间：2021年5月17日 下午6:36:34 
* 类说明 
*/
public interface UserService {
	
	//管理员用户登录程序
	UserBean findUserByInfo(String username,String password);
	
	
	
}
