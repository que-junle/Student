package com.exception.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.exception.bean.UserBean;
import com.exception.view.classView.AddClassJDialog;
import com.exception.view.classView.ClassJPanel;
import com.exception.view.roomView.AddRoomJDialog;
import com.exception.view.roomView.RoomJPanel;
import com.exception.view.student.AddStuJDialog;
import com.exception.view.student.StudentJPanel;

import javax.swing.JMenuBar;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.awt.Color;
import java.awt.Container;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import java.awt.Panel;
import javax.swing.JLabel;
import javax.swing.JDesktopPane;

/** 
* @author 作者 Your-Name: 
* @version 创建时间：2021年5月17日 下午6:33:14 
* 类说明 
*/
public class IndexFrame extends JFrame {

	private Container contentPane;
	private UserBean userBean;
	
	private CardLayout card;
	private StudentJPanel studentFrame = new StudentJPanel(this);
	private ClassJPanel classFrame = new ClassJPanel(this);
	private RoomJPanel roomFrame = new RoomJPanel(this);
	
	public IndexFrame(UserBean userBean) {
		
		setTitle("宿舍管理系统欢迎您: "+userBean.getF_username());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 580);
		setResizable(false);
		setLocationRelativeTo(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(SystemColor.control);
		menuBar.setForeground(new Color(255, 255, 255));
		menuBar.setFont(new Font("等线", Font.PLAIN, 20));
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu(" 个人信息");
		mnNewMenu.setIcon(new ImageIcon(IndexFrame.class.getResource("/image/个人信息.png")));
		mnNewMenu.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		mnNewMenu.setBackground(new Color(0, 255, 255));
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("修改密码");
		mntmNewMenuItem.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		mntmNewMenuItem.setBackground(new Color(255, 255, 255));
		mntmNewMenuItem.setHorizontalAlignment(SwingConstants.CENTER);
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("退出登录");
		mntmNewMenuItem_1.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		mntmNewMenuItem_1.setBackground(new Color(255, 255, 255));
		mntmNewMenuItem_1.setHorizontalAlignment(SwingConstants.CENTER);
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JMenu mnNewMenu_1 = new JMenu(" 学生管理");
		mnNewMenu_1.setIcon(new ImageIcon(IndexFrame.class.getResource("/image/学生.png")));
		mnNewMenu_1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
		mnNewMenu_1.setBackground(new Color(255, 255, 255));
		mnNewMenu_1.setHorizontalAlignment(SwingConstants.CENTER);
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("学生入住");
		mntmNewMenuItem_2.setHorizontalAlignment(SwingConstants.CENTER);
		mntmNewMenuItem_2.setIcon(new ImageIcon(IndexFrame.class.getResource("/image/新增.png")));
		mntmNewMenuItem_2.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clickAddStudent(e);
			}
		});
		mntmNewMenuItem_2.setBackground(new Color(255, 255, 255));
		mnNewMenu_1.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem(" 学生列表");
		mntmNewMenuItem_3.setIcon(new ImageIcon(IndexFrame.class.getResource("/image/列表.png")));
		mntmNewMenuItem_3.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		mntmNewMenuItem_3.setBackground(new Color(255, 255, 255));
		mntmNewMenuItem_3.setHorizontalAlignment(SwingConstants.CENTER);
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clickStudentInfo(e);
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_3);
		
		JMenu mnNewMenu_1_1 = new JMenu(" 班级管理");
		mnNewMenu_1_1.setIcon(new ImageIcon(IndexFrame.class.getResource("/image/班级.png")));
		mnNewMenu_1_1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
		mnNewMenu_1_1.setBackground(new Color(255, 255, 255));
		mnNewMenu_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		menuBar.add(mnNewMenu_1_1);
		
		JMenuItem mntmNewMenuItem_2_1 = new JMenuItem("添加班级");
		mntmNewMenuItem_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		mntmNewMenuItem_2_1.setIcon(new ImageIcon(IndexFrame.class.getResource("/image/新增.png")));
		mntmNewMenuItem_2_1.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		mntmNewMenuItem_2_1.setBackground(new Color(255, 255, 255));
		mntmNewMenuItem_2_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clickAddClass(e);
			}
		});
		mnNewMenu_1_1.add(mntmNewMenuItem_2_1);
		
		JMenuItem mntmNewMenuItem_3_1 = new JMenuItem(" 班级信息");
		mntmNewMenuItem_3_1.setIcon(new ImageIcon(IndexFrame.class.getResource("/image/列表.png")));
		mntmNewMenuItem_3_1.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		mntmNewMenuItem_3_1.setBackground(new Color(255, 255, 255));
		mntmNewMenuItem_3_1.setHorizontalAlignment(SwingConstants.CENTER);
		mntmNewMenuItem_3_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				clickClassList(e);
			}
		});
		mnNewMenu_1_1.add(mntmNewMenuItem_3_1);
		
		JMenu mnNewMenu_1_1_1 = new JMenu(" 房间管理");
		mnNewMenu_1_1_1.setIcon(new ImageIcon(IndexFrame.class.getResource("/image/房间.png")));
		mnNewMenu_1_1_1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
		mnNewMenu_1_1_1.setBackground(new Color(255, 255, 255));
		mnNewMenu_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		menuBar.add(mnNewMenu_1_1_1);
		
		JMenuItem mntmNewMenuItem_2_1_1 = new JMenuItem(" 新增房间");
		mntmNewMenuItem_2_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		mntmNewMenuItem_2_1_1.setIcon(new ImageIcon(IndexFrame.class.getResource("/image/新增.png")));
		mntmNewMenuItem_2_1_1.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		mntmNewMenuItem_2_1_1.setBackground(new Color(255, 255, 255));
		mntmNewMenuItem_2_1_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clickAddRoom(e);
			}
		});
		mnNewMenu_1_1_1.add(mntmNewMenuItem_2_1_1);
		
		JMenuItem mntmNewMenuItem_3_1_1 = new JMenuItem(" 房间信息");
		mntmNewMenuItem_3_1_1.setIcon(new ImageIcon(IndexFrame.class.getResource("/image/列表.png")));
		mntmNewMenuItem_3_1_1.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		mntmNewMenuItem_3_1_1.setBackground(new Color(255, 255, 255));
		mntmNewMenuItem_3_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		mntmNewMenuItem_3_1_1.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				clickRoomList(e);
			}
		});
		mnNewMenu_1_1_1.add(mntmNewMenuItem_3_1_1);
		
		this.contentPane = getContentPane();
		this.contentPane.setLayout(new CardLayout());
		this.contentPane.setBackground(Color.WHITE);
		this.card = (CardLayout)this.contentPane.getLayout();
		
		ImageIcon image = new ImageIcon(IndexFrame.class.getResource("/image/bg.jpg"));
		image.setImage(image.getImage().getScaledInstance(900,580,Image.SCALE_DEFAULT));
		JLabel lblNewLabel = new JLabel(image);
		getContentPane().add(lblNewLabel, "name_319353912967000");
		

		setVisible(true);
	}
	

	


	protected void clickAddStudent(ActionEvent e) {
		new AddStuJDialog(this,true);
	}


	protected void clickAddRoom(ActionEvent e) {
		new AddRoomJDialog(this,true);
	}


	protected void clickAddClass(ActionEvent e) {
		new AddClassJDialog(this, true,null);
	}
	
	
	//点击学生列表
	private boolean flagStudentListFrame = false;
	protected void clickStudentInfo(ActionEvent e) {
		if(flagStudentListFrame ==false) {
			this.contentPane.add("studentFrame",studentFrame);
		}
		card.show(this.contentPane,"studentFrame");
		studentFrame.setVisible(true);
		studentFrame.resetButton();
		flagStudentListFrame = true;
		this.setVisible(true);
	}
	//点击班级列表
	private boolean flagClassListFrame = false;
	protected void clickClassList(ActionEvent e) {
		if(flagClassListFrame == false) {
			this.contentPane.add("classFrame",classFrame);
		}
		card.show(this.contentPane, "classFrame");
		classFrame.setVisible(true);
		classFrame.resetButton();
		flagClassListFrame = true;
		//最好是整体刷新一下，不然不好看
		this.setVisible(true);
	}
	//点击房间列表
	private boolean flagRoomListFrame = false;
	protected void clickRoomList(ActionEvent e) {
		if(flagRoomListFrame == false) {
			this.contentPane.add("roomFrame",roomFrame);
		}
		card.show(this.contentPane, "roomFrame");
		roomFrame.setVisible(true);
		roomFrame.resetButton();
		flagRoomListFrame = true;
		this.setVisible(true);
	}
	
}
