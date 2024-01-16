package com.exception.bean;

/** 
* @author 作者 Your-Name: 
* @version 创建时间：2021年5月17日 下午2:55:16 
* 类说明 
*/
public class UserBean {
	
	private int pk_id;
	
	private String f_username;
	
	private String f_password;
	
	public UserBean() {
		
	}
	
	public UserBean(String f_username,String f_password) {
		this.f_username = f_username;
		this.f_password = f_password;
	}

	public UserBean(int pk_id, String f_username, String f_password) {
		super();
		this.pk_id = pk_id;
		this.f_username = f_username;
		this.f_password = f_password;
	}

	public int getPk_id() {
		return pk_id;
	}

	public void setPk_id(int pk_id) {
		this.pk_id = pk_id;
	}

	public String getF_username() {
		return f_username;
	}

	public void setF_username(String f_username) {
		this.f_username = f_username;
	}

	public String getF_password() {
		return f_password;
	}

	public void setF_password(String f_password) {
		this.f_password = f_password;
	}
}
