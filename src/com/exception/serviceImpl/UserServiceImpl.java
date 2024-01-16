package com.exception.serviceImpl;

import com.exception.bean.UserBean;
import com.exception.dao.UserDao;
import com.exception.daoImpl.UserDaoImpl;
import com.exception.service.UserService;

/** 
* @author 作者 Your-Name: 
* @version 创建时间：2021年5月17日 下午6:41:19 
* 类说明 
*/
public class UserServiceImpl implements UserService{

	@Override
	public UserBean findUserByInfo(String username, String password) {
		UserDao userDao = new UserDaoImpl();
		return userDao.selectUserByInfo(new UserBean(username, password));
	}

}
