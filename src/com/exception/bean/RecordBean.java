package com.exception.bean;

import java.util.Date;

/** 
* @author 作者 Your-Name: 
* @version 创建时间：2021年5月17日 下午2:55:53 
* 类说明 
*/
public class RecordBean {
	
	private int pk_id;
	
	private Date f_time;
	
	private String f_info;
	//是否处理 1代表以处理，0代表未处理
	private int f_isok;
	
	private int fk_roomid;

	
	public RecordBean() {
		
	}
	
	public RecordBean(Date f_time, String f_info, int f_isok, int fk_roomid) {
		this.f_time = f_time;
		this.f_info = f_info;
		this.f_isok = f_isok;
		this.fk_roomid = fk_roomid;
	}


	public RecordBean(int pk_id, Date f_time, String f_info, int f_isok, int fk_roomid) {
		this.pk_id = pk_id;
		this.f_time = f_time;
		this.f_info = f_info;
		this.f_isok = f_isok;
		this.fk_roomid = fk_roomid;
	}

	public int getPk_id() {
		return pk_id;
	}

	public void setPk_id(int pk_id) {
		this.pk_id = pk_id;
	}

	public Date getF_time() {
		return f_time;
	}

	public void setF_time(Date f_time) {
		this.f_time = f_time;
	}

	public String getF_info() {
		return f_info;
	}

	public void setF_info(String f_info) {
		this.f_info = f_info;
	}

	public int getF_isok() {
		return f_isok;
	}

	public void setF_isok(int f_isok) {
		this.f_isok = f_isok;
	}

	public int getFk_roomid() {
		return fk_roomid;
	}

	public void setFk_roomid(int fk_roomid) {
		this.fk_roomid = fk_roomid;
	}
	
}
