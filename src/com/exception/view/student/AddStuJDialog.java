package com.exception.view.student;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jdesktop.swingx.JXDatePicker;

import com.exception.bean.ClassBean;
import com.exception.bean.RoomBean;
import com.exception.bean.StudentBean;
import com.exception.service.ClassService;
import com.exception.service.RoomService;
import com.exception.service.StudentService;
import com.exception.serviceImpl.ClassServiceImpl;
import com.exception.serviceImpl.RoomServiceImpl;
import com.exception.serviceImpl.StudentServiceImpl;
import com.exception.util.StrUtil;
import com.lovo.netCRM.component.LovoFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;

/** 
* @author 作者 Your-Name: 
* @version 创建时间：2021年6月3日 上午9:56:09 
* 类说明 
*/
public class AddStuJDialog extends JDialog {
	
	private LovoFileChooser headPic;
	private JTextField stuName;
	private JXDatePicker datePicker;
	private JTextField stuPhone;
	private JRadioButton rdbtn1;
	private JRadioButton rdbtn0;
	private JComboBox classComb;
	private JComboBox roomComb;
	private RoomService roomService = new RoomServiceImpl();
	private ClassService classService = new ClassServiceImpl();
	private StudentService stuService = new StudentServiceImpl();
	private ArrayList<ClassBean> classList;
	private ArrayList<RoomBean> roomList;
	
	public AddStuJDialog(JFrame frame,boolean flag) {
		super(frame,flag);
		setTitle("正在办理学生入住");
		setBounds(100, 100, 480, 380);
		getContentPane().setLayout(null);
		setLocationRelativeTo(frame);
		
		JLabel lblNewLabel = new JLabel("姓  名");
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		lblNewLabel.setBounds(37, 38, 41, 15);
		getContentPane().add(lblNewLabel);
		
		stuName = new JTextField();
		stuName.setBounds(88, 36, 124, 21);
		getContentPane().add(stuName);
		stuName.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("性  别");
		lblNewLabel_1.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(37, 110, 41, 15);
		getContentPane().add(lblNewLabel_1);
		
		rdbtn1 = new JRadioButton("男");
		rdbtn1.setBounds(95, 107, 41, 23);
		rdbtn1.setSelected(true);
		getContentPane().add(rdbtn1);
		
		rdbtn0 = new JRadioButton("女");
		rdbtn0.setBounds(165, 107, 41, 23);
		getContentPane().add(rdbtn0);
		
		// 创建一个按钮组
		ButtonGroup btnGroup = new ButtonGroup();
		// 添加单选按钮到按钮组
		btnGroup.add(rdbtn1);
		btnGroup.add(rdbtn0);
		rdbtn1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				roomListChange();
			}
		});
		rdbtn0.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				roomListChange();
			}
		});
		
		
		JLabel lblNewLabel_1_1 = new JLabel("班  级");
		lblNewLabel_1_1.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(37, 180, 41, 15);
		getContentPane().add(lblNewLabel_1_1);
		
		classComb = new JComboBox();
		classComb.setBounds(90, 177, 119, 21);
		getContentPane().add(classComb);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("房  间");
		lblNewLabel_1_1_1.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		lblNewLabel_1_1_1.setBounds(37, 240, 41, 15);
		getContentPane().add(lblNewLabel_1_1_1);
		
		roomComb = new JComboBox();
		roomComb.setBounds(90, 237, 119, 21);
		getContentPane().add(roomComb);
		
		this.headPic = new LovoFileChooser(this);
		this.headPic.setBounds(300, 20, 100, 130);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("联系电话");
		lblNewLabel_1_1_1_1.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		lblNewLabel_1_1_1_1.setBounds(255, 180, 67, 15);
		getContentPane().add(lblNewLabel_1_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("入住时间");
		lblNewLabel_1_1_1_1_1.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		lblNewLabel_1_1_1_1_1.setBounds(255, 240, 67, 15);
		getContentPane().add(lblNewLabel_1_1_1_1_1);
		
		datePicker = new JXDatePicker();
		datePicker.getEditor().setFont(new Font("微软雅黑", Font.PLAIN, 13));
		datePicker.setBounds(322, 237, 109, 21);
		getContentPane().add(datePicker);
		
		stuPhone = new JTextField();
		stuPhone.setBounds(322, 177, 109, 21);
		getContentPane().add(stuPhone);
		stuPhone.setColumns(10);
		
		JButton btnNewButton = new JButton("学生入住");
		btnNewButton.setBounds(109, 299, 97, 23);
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addStudentButton(e);
			}
		});
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("取消");
		btnNewButton_1.setBounds(255, 299, 97, 23);
		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AddStuJDialog.this.dispose();
			}
		});
		getContentPane().add(btnNewButton_1);
		
		initialJDialog();
	}
	
	protected void addStudentButton(ActionEvent e) {
		String stuName = this.stuName.getText();
		int gender = this.rdbtn1.isSelected() == true ? 1:2;
		String photo = this.headPic.getFilePath();
		String phone = this.stuPhone.getText();
		Date date  = this.datePicker.getDate();
		ClassBean classBean = (ClassBean) this.classComb.getSelectedItem();
		RoomBean roomBean = (RoomBean) this.roomComb.getSelectedItem();
		if(!StrUtil.notEmpty(stuName)||!StrUtil.notEmpty(photo)||!StrUtil.notEmpty(phone)||date == null||classBean == null||roomBean == null) {
			JOptionPane.showMessageDialog(this, "请完整填写信息");
			return;
		}
		
		boolean addStudentBool = stuService.addStudent(new StudentBean(stuName, gender, photo, phone, date, roomBean, classBean));
		if(addStudentBool == true) {
			JOptionPane.showMessageDialog(this, "学生入住成功");
			initialJDialog();
			return;
		}else {
			JOptionPane.showMessageDialog(this, "添加失败");
			return;
		}
	}

	public void initialJDialog() {
		
		this.stuName.setText("");
		this.stuPhone.setText("");
		this.datePicker.getEditor().setText("");
		this.rdbtn1.setSelected(true);
		
		classList = classService.queryAllClass();
		ClassBean [] classBeans = new ClassBean[classList.size()];
		for(int i = 0;i < classList.size();i++) {
			classBeans[i] = classList.get(i);
		}
		
		this.classComb.setModel(new DefaultComboBoxModel<ClassBean>(classBeans));
		if(this.classComb.getSelectedItem()!=null) {
			this.classComb.setSelectedIndex(0);
		}
		
		roomListChange();
		this.setVisible(true);
	}
	
	private void roomListChange() {
		roomList = roomService.queryAllRoomble(this.rdbtn1.isSelected() == true ? 1:2);
		RoomBean [] roomBeans = new RoomBean[roomList.size()];
		for(int i = 0;i < roomList.size();i++) {
			roomBeans[i] = roomList.get(i);
		}
		this.roomComb.setModel(new DefaultComboBoxModel<RoomBean>(roomBeans));
		if(this.roomComb.getSelectedItem()!=null) {
			this.roomComb.setSelectedIndex(0);
		}
	}
	
	
}
