package com.exception.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/** 
* @author 作者 Your-Name: 
* @version 创建时间：2021年5月17日 下午6:38:18 
* 类说明 
*/
public abstract class BaseDao {
	
	protected Connection con = null;
	
	protected PreparedStatement preparedStatement = null;
	
	protected void closeAll() {
		try {
			if(this.con != null) {
				this.con.close();
			}
			if(this.preparedStatement != null) {
				this.preparedStatement.close();
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
