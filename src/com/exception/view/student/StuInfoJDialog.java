package com.exception.view.student;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.exception.bean.StudentBean;
import com.exception.util.DateUtil;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

/** 
* @author 作者 Your-Name: 
* @version 创建时间：2021年6月5日 下午5:14:53 
* 类说明 
*/
public class StuInfoJDialog extends JDialog {
	
	public StuInfoJDialog(JFrame frame,boolean flag,StudentBean stuBean) {
		
		super(frame,flag);
		setBounds(100, 100, 459, 320);
		setLocationRelativeTo(frame);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("姓名");
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		lblNewLabel.setBounds(41, 29, 65, 22);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("性别");
		lblNewLabel_1.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(41, 83, 65, 22);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("班级");
		lblNewLabel_1_1.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(41, 136, 65, 22);
		getContentPane().add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("房间");
		lblNewLabel_1_1_1.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		lblNewLabel_1_1_1.setBounds(41, 190, 65, 22);
		getContentPane().add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("联系电话");
		lblNewLabel_1_1_1_1.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		lblNewLabel_1_1_1_1.setBounds(234, 136, 65, 22);
		getContentPane().add(lblNewLabel_1_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("入住时间");
		lblNewLabel_1_1_1_1_1.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		lblNewLabel_1_1_1_1_1.setBounds(234, 190, 65, 22);
		getContentPane().add(lblNewLabel_1_1_1_1_1);
		
		ImageIcon image = new ImageIcon(stuBean.getF_photo());
		image.setImage(image.getImage().getScaledInstance(81,116,Image.SCALE_DEFAULT));
		JLabel stuImage = new JLabel(image);
		stuImage.setBounds(271, 10, 81, 116);
		getContentPane().add(stuImage);
		
		JLabel stuName = new JLabel(stuBean.getF_name());
		stuName.setForeground(Color.RED);
		stuName.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		stuName.setBounds(94, 33, 105, 15);
		getContentPane().add(stuName);
		
		JLabel stuGender = new JLabel(stuBean.getF_sex() == 1?"男":"女");
		stuGender.setForeground(Color.RED);
		stuGender.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		stuGender.setBounds(94, 87, 105, 15);
		getContentPane().add(stuGender);
		
		JLabel stuClass = new JLabel(stuBean.getClassBean().getF_name());
		stuClass.setForeground(Color.RED);
		stuClass.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		stuClass.setBounds(94, 140, 105, 15);
		getContentPane().add(stuClass);
		
		JLabel stuRoom = new JLabel(stuBean.getRoomBean().getF_address());
		stuRoom.setForeground(Color.RED);
		stuRoom.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		stuRoom.setBounds(94, 194, 105, 15);
		getContentPane().add(stuRoom);
		
		JLabel stuPhone = new JLabel(stuBean.getF_phone());
		stuPhone.setForeground(Color.RED);
		stuPhone.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		stuPhone.setBounds(312, 140, 105, 15);
		getContentPane().add(stuPhone);
		
		JLabel stuTime = new JLabel(DateUtil.DateToSqlDate(stuBean.getF_time()));
		stuTime.setForeground(Color.RED);
		stuTime.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		stuTime.setBounds(312, 194, 105, 15);
		getContentPane().add(stuTime);
		
		JButton btnNewButton = new JButton("确定");
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				StuInfoJDialog.this.dispose();
			}
		});
		btnNewButton.setBounds(165, 239, 97, 23);
		getContentPane().add(btnNewButton);
		
		setVisible(true);
		
	}
}
