package com.exception.daoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.exception.bean.UserBean;
import com.exception.dao.UserDao;
import com.exception.util.DbUtil;

/** 
* @author 作者 Your-Name: 
* @version 创建时间：2021年5月17日 下午6:43:36 
* 类说明 
*/
public class UserDaoImpl extends BaseDao implements UserDao{

	@Override
	public UserBean selectUserByInfo(UserBean userBean) {
		String sql = "select pk_userid,f_username,f_password from t_user where f_username = ? and f_password = ?";
		this.con = DbUtil.getConnection();
		UserBean resultUser = null;
		try {
			this.preparedStatement = this.con.prepareStatement(sql);
			this.preparedStatement.setString(1, userBean.getF_username());
			this.preparedStatement.setString(2, userBean.getF_password());
			ResultSet executeQuery = this.preparedStatement.executeQuery();
			if(executeQuery.next()) {
				resultUser = new UserBean(executeQuery.getInt(1),executeQuery.getString(2),executeQuery.getString(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			this.closeAll();
		}
		return resultUser;
	}

}
