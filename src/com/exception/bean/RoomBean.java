package com.exception.bean;

import java.util.ArrayList;
import java.util.Date;

/** 
* @author 作者 Your-Name: 
* @version 创建时间：2021年5月17日 下午2:55:36 
* 类说明 
*/
public class RoomBean {
	
	private int pk_id;
	private String f_address;
	private int f_maxnum;
	private int f_stucount;
	private int f_price;
	private String f_master;
	private String f_phone;
	//1代表正常，2代表损坏
	private int f_state;
	private String f_type;
	//1代表男，2代表女
	private int f_sextype;
	private String f_pay;
	private Date f_time;
	
	private ArrayList<StudentBean>studentBeans;
	
	//更新内部的student数据，不然是有一定人类逻辑问题的，这个和业务无关，可以有这一步操作，也可以没有
	//不过这样做的好处可以保证数据传递的完整性，提高程序扩展性，如果不考虑程序后续有变动，这个函数完全是多此一举
	public void setStudentBean(RoomBean roomBean) {
		for(StudentBean student:studentBeans) {
			student.setRoomBean(roomBean);
		}
	}
	
	public RoomBean() {
		
	}
	
	public RoomBean(int pk_id, String f_address) {
		super();
		this.pk_id = pk_id;
		this.f_address = f_address;
	}

	public RoomBean(String f_address, int f_maxnum, int f_stucount, int f_price, String f_master, String f_phone,
			int f_state, String f_type, int f_sextype, String f_pay, Date f_time) {
		super();
		this.f_address = f_address;
		this.f_maxnum = f_maxnum;
		this.f_stucount = f_stucount;
		this.f_price = f_price;
		this.f_master = f_master;
		this.f_phone = f_phone;
		this.f_state = f_state;
		this.f_type = f_type;
		this.f_sextype = f_sextype;
		this.f_pay = f_pay;
		this.f_time = f_time;
	}

	public RoomBean(String f_address, int f_maxnum, int f_stucount, int f_price, String f_master, String f_phone,
			int f_state, String f_type, int f_sextype, String f_pay, Date f_time, ArrayList<StudentBean> studentBeans) {
		super();
		this.f_address = f_address;
		this.f_maxnum = f_maxnum;
		this.f_stucount = f_stucount;
		this.f_price = f_price;
		this.f_master = f_master;
		this.f_phone = f_phone;
		this.f_state = f_state;
		this.f_type = f_type;
		this.f_sextype = f_sextype;
		this.f_pay = f_pay;
		this.f_time = f_time;
		this.studentBeans = studentBeans;
		
	}

	public RoomBean(int pk_id, String f_address, int f_maxnum, int f_stucount, int f_price, String f_master,
			String f_phone, int f_state, String f_type, int f_sextype, String f_pay, Date f_time) {
		super();
		this.pk_id = pk_id;
		this.f_address = f_address;
		this.f_maxnum = f_maxnum;
		this.f_stucount = f_stucount;
		this.f_price = f_price;
		this.f_master = f_master;
		this.f_phone = f_phone;
		this.f_state = f_state;
		this.f_type = f_type;
		this.f_sextype = f_sextype;
		this.f_pay = f_pay;
		this.f_time = f_time;
	}

	

	public RoomBean(int pk_id, String f_address, int f_maxnum, int f_stucount, int f_price, String f_master,
			String f_phone, int f_state, String f_type, int f_sextype, String f_pay, Date f_time,
			ArrayList<StudentBean> studentBeans) {
		super();
		this.pk_id = pk_id;
		this.f_address = f_address;
		this.f_maxnum = f_maxnum;
		this.f_stucount = f_stucount;
		this.f_price = f_price;
		this.f_master = f_master;
		this.f_phone = f_phone;
		this.f_state = f_state;
		this.f_type = f_type;
		this.f_sextype = f_sextype;
		this.f_pay = f_pay;
		this.f_time = f_time;
		this.studentBeans = studentBeans;
	}

	public ArrayList<StudentBean> getStudentBeans() {
		return studentBeans;
	}

	public void setStudentBeans(ArrayList<StudentBean> studentBeans) {
		this.studentBeans = studentBeans;
	}
		

	public int getPk_id() {
		return pk_id;
	}

	
	public void setPk_id(int pk_id) {
		this.pk_id = pk_id;
	}

	
	public String getF_address() {
		return f_address;
	}

	
	public void setF_address(String f_address) {
		this.f_address = f_address;
	}


	public int getF_maxnum() {
		return f_maxnum;
	}



	public void setF_maxnum(int f_maxnum) {
		this.f_maxnum = f_maxnum;
	}


	public int getF_stucount() {
		return f_stucount;
	}


	public void setF_stucount(int f_stucount) {
		this.f_stucount = f_stucount;
	}


	public int getF_price() {
		return f_price;
	}
	

	public void setF_price(int f_price) {
		this.f_price = f_price;
	}
	

	public String getF_master() {
		return f_master;
	}
	

	public void setF_master(String f_master) {
		this.f_master = f_master;
	}
	

	public int getF_state() {
		return f_state;
	}
	

	public void setF_state(int f_state) {
		this.f_state = f_state;
	}
	

	public String getF_type() {
		return f_type;
	}
	

	public void setF_type(String f_type) {
		this.f_type = f_type;
	}

	
	public int getF_sextype() {
		return f_sextype;
	}
	

	public void setF_sextype(int f_sextype) {
		this.f_sextype = f_sextype;
	}
	

	public String getF_pay() {
		return f_pay;
	}
	

	public void setF_pay(String f_pay) {
		this.f_pay = f_pay;
	}
	

	public Date getF_time() {
		return f_time;
	}
	

	public void setF_time(Date f_time) {
		this.f_time = f_time;
	}
	

	public String getF_phone() {
		return f_phone;
	}
	

	public void setF_phone(String f_phone) {
		this.f_phone = f_phone;
	}

	@Override
	public String toString() {
		return f_address;
	}
	
	
}
