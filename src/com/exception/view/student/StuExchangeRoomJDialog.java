package com.exception.view.student;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.exception.bean.RoomBean;
import com.exception.bean.StudentBean;
import com.exception.service.RoomService;
import com.exception.service.StudentService;
import com.exception.serviceImpl.RoomServiceImpl;
import com.exception.serviceImpl.StudentServiceImpl;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JComboBox;
import java.awt.Color;

/** 
* @author 作者 Your-Name: 
* @version 创建时间：2021年6月4日 下午11:44:18 
* 类说明 
*/
public class StuExchangeRoomJDialog extends JDialog {

	private StudentBean stuBean;
	private JLabel stuName;
	private JLabel stuClass;
	private JLabel stuGender;
	private JLabel stuOldRoom;
	private JComboBox stuNewRoomComb;
	private ArrayList<RoomBean> roomList = new ArrayList<RoomBean>();
	private RoomService roomService = new RoomServiceImpl();
	private StudentService stuService = new StudentServiceImpl();
	
	public StuExchangeRoomJDialog(JFrame frame,boolean flag,StudentBean stuBean) {
		super(frame,flag);
		this.stuBean = stuBean;
		setBounds(100, 100, 520, 290);
		getContentPane().setLayout(null);
		setLocationRelativeTo(frame);
		
		JLabel lblNewLabel = new JLabel("姓名");
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		lblNewLabel.setBounds(48, 29, 47, 29);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("班级");
		lblNewLabel_1.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(48, 86, 47, 29);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("性别");
		lblNewLabel_1_1.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		lblNewLabel_1_1.setBounds(291, 29, 47, 29);
		getContentPane().add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("原房间");
		lblNewLabel_1_1_1.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		lblNewLabel_1_1_1.setBounds(291, 86, 59, 29);
		getContentPane().add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("可住房间");
		lblNewLabel_1_1_1_1.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		lblNewLabel_1_1_1_1.setBounds(48, 144, 77, 29);
		getContentPane().add(lblNewLabel_1_1_1_1);
		
		roomList = roomService.queryAllRoomble(stuBean.getF_sex());
		RoomBean room1[] = new RoomBean[roomList.size()];
		RoomBean room2[];
		if(roomList.size() == 0) {
			room2 = new RoomBean[1];
			room2[0] = new RoomBean(-1,"暂无新房间");
		}else {
			int j = 0;
			for(int i = 0; i < roomList.size();i++) {
				if(roomList.get(i).getPk_id() != stuBean.getRoomBean().getPk_id())
					room1[j++] = roomList.get(i);
			}
			room2 = new RoomBean[j];
			for(int i = 0; i < j; i++)
				room2[i] = room1[i];
		}
		stuNewRoomComb = new JComboBox(new DefaultComboBoxModel<RoomBean>(room2));
		stuNewRoomComb.setBounds(119, 149, 116, 21);
		getContentPane().add(stuNewRoomComb);
		
		stuName = new JLabel(stuBean.getF_name());
		stuName.setForeground(Color.RED);
		stuName.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		stuName.setBounds(119, 38, 116, 15);
		getContentPane().add(stuName);
		
		stuClass = new JLabel(stuBean.getClassBean().getF_name());
		stuClass.setForeground(Color.RED);
		stuClass.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		stuClass.setBounds(119, 92, 116, 15);
		getContentPane().add(stuClass);
		
		stuGender = new JLabel(stuBean.getF_sex() == 1 ?"男":"女");
		stuGender.setForeground(Color.RED);
		stuGender.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		stuGender.setBounds(367, 37, 77, 15);
		getContentPane().add(stuGender);
		
		stuOldRoom = new JLabel(stuBean.getRoomBean().getF_address());
		stuOldRoom.setForeground(Color.RED);
		stuOldRoom.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		stuOldRoom.setBounds(367, 93, 129, 15);
		getContentPane().add(stuOldRoom);
		
		JButton btnNewButton = new JButton("确定");
		btnNewButton.setBounds(145, 207, 77, 23);
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				confirmButton(e);
			}
		});
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("取消");
		btnNewButton_1.setBounds(287, 207, 77, 23);
		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				StuExchangeRoomJDialog.this.dispose();
			}
		});
		getContentPane().add(btnNewButton_1);
		
		setVisible(true);
	}

	protected void confirmButton(ActionEvent e) {
		RoomBean newRoomBean = (RoomBean) this.stuNewRoomComb.getSelectedItem();
		if(newRoomBean.getPk_id() == -1) {
			JOptionPane.showMessageDialog(this, "暂无房间可换");
			return;
		}
		if(stuService.exchangeRoom(this.stuBean, this.stuBean.getRoomBean().getPk_id(), newRoomBean.getPk_id())) {
			JOptionPane.showMessageDialog(this, "换房成功!");
		}else {
			JOptionPane.showMessageDialog(this, "换房失败!");
		}
	}
}
