package com.exception.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.exception.bean.UserBean;
import com.exception.service.UserService;
import com.exception.serviceImpl.UserServiceImpl;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.SystemColor;

/** 
* @author 作者 Your-Name: 
* @version 创建时间：2021年5月17日 下午6:04:06 
* 类说明 
*/
public class LoginFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginFrame() {
		setTitle("登录程序");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 350, 200);
		setResizable(false);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.control);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("用户名:");
		lblNewLabel.setIcon(new ImageIcon(LoginFrame.class.getResource("/image/用户名.png")));
		lblNewLabel.setFont(new Font("微软雅黑", Font.BOLD, 15));
		lblNewLabel.setBounds(57, 31, 76, 26);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("密   码:");
		lblNewLabel_1.setIcon(new ImageIcon(LoginFrame.class.getResource("/image/密码.png")));
		lblNewLabel_1.setFont(new Font("微软雅黑", Font.BOLD, 15));
		lblNewLabel_1.setBounds(57, 73, 88, 26);
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setFont(new Font("微软雅黑", Font.BOLD, 12));
		textField.setBounds(144, 34, 123, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("微软雅黑", Font.BOLD, 12));
		passwordField.setBounds(144, 78, 123, 21);
		contentPane.add(passwordField);
		
		JButton btnNewButton = new JButton("登录");
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				confirmButton(e);
			}
		});
		btnNewButton.setBackground(new Color(211, 211, 211));
		btnNewButton.setBounds(194, 133, 74, 23);
		contentPane.add(btnNewButton);
	}

	protected void confirmButton(ActionEvent e) {
		String username = this.textField.getText();
		String password = this.passwordField.getText();
		
		UserService userService = new UserServiceImpl();
		UserBean userBean = userService.findUserByInfo(username, password);
		if(userBean == null) {
			JOptionPane.showMessageDialog(this, "用户名或密码错误,请重试!");
			return;
		}
		IndexFrame indexFrame = new IndexFrame(userBean);
		this.dispose();
		
	}
}
