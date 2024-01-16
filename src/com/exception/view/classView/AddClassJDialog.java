package com.exception.view.classView;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jdesktop.swingx.JXDatePicker;

import com.exception.bean.ClassBean;
import com.exception.service.ClassService;
import com.exception.serviceImpl.ClassServiceImpl;
import com.exception.util.DateUtil;
import com.exception.util.StrUtil;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JTextField;

/** 
* @author 作者 Your-Name: 
* @version 创建时间：2021年5月24日 上午11:13:26 
* 类说明 
* 新增班级和修改班级可以共用一个窗体，这里就写在一起
*/
public class AddClassJDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField className;
	private JTextField classTeacher;
	private JXDatePicker datePicker;
	private ClassBean classBean;

	/**
	 * Create the dialog.
	 */
	public AddClassJDialog(JFrame frame, boolean modal,ClassBean classBean) {
		
		super(frame,modal);
		this.classBean = classBean;
		if(classBean ==null)
			setTitle("正在新增班级");
		else
			setTitle("正在修改班级");
		setBounds(100, 100, 330, 250);
		getContentPane().setLayout(new BorderLayout());
		setLocationRelativeTo(frame);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("班级名称:");
			lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
			lblNewLabel.setBounds(43, 29, 70, 22);
			contentPanel.add(lblNewLabel);
		}
		
		className = new JTextField();
		className.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		className.setBounds(123, 30, 143, 21);
		contentPanel.add(className);
		className.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("带班老师:");
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		lblNewLabel.setBounds(43, 75, 70, 22);
		contentPanel.add(lblNewLabel);
		
		classTeacher = new JTextField();
		classTeacher.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		classTeacher.setColumns(10);
		classTeacher.setBounds(123, 77, 143, 21);
		contentPanel.add(classTeacher);
		
		JLabel lblNewLabel_1 = new JLabel("开班日期:");
		lblNewLabel_1.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(43, 122, 70, 22);
		contentPanel.add(lblNewLabel_1);
		
		datePicker = new JXDatePicker();
		datePicker.getEditor().setFont(new Font("微软雅黑", Font.PLAIN, 13));
		datePicker.setBounds(123, 123, 143, 21);
		contentPanel.add(datePicker);
		
		JButton btnNewButton = new JButton("添加");
		btnNewButton.setBounds(56, 177, 70, 23);
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					addOrEditClassButton(e);
			}
		});
		contentPanel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("重置");
		btnNewButton_1.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				resetButton(e);
			}
		});
		btnNewButton_1.setBounds(196, 177, 70, 23);
		contentPanel.add(btnNewButton_1);
		
		if(classBean != null) {
			className.setText(classBean.getF_name());
			classTeacher.setText(classBean.getF_teacher());
			datePicker.setDate(classBean.getF_time());
			btnNewButton.setText("修改");
		}
		
		this.setVisible(true);
	}

	//重置按钮动作
	protected void resetButton(ActionEvent e) {
		this.setVisible(false);
		if(classBean == null) {
			className.setText("");
			classTeacher.setText("");
			datePicker.getEditor().setText("");
		}else {
			className.setText(classBean.getF_name());
			classTeacher.setText(classBean.getF_teacher());
			datePicker.setDate(classBean.getF_time());
		}
		this.setVisible(true);
	}

	//修改班级按钮动作
	protected void addOrEditClassButton(ActionEvent e) {
		String classNameStr = this.className.getText();
		String classTeacherStr = this.classTeacher.getText();
		Date tempDate = this.datePicker.getDate();
		if(!StrUtil.notEmpty(classNameStr)||!StrUtil.notEmpty(classTeacherStr)||tempDate==null) {
			JOptionPane.showMessageDialog(this, "您填写的信息不完善，请重试!");
			return;
		}
		ClassService classService = new ClassServiceImpl();
		//新增班级
		if(classBean == null) {
			ClassBean clBean = new ClassBean(classNameStr, classTeacherStr, tempDate);
			JOptionPane.showMessageDialog(this, classService.addClass(clBean));
			resetButton(e);
			return;
		}
		//修改班级
		else {
			ClassBean clBean = new ClassBean(this.classBean.getPk_id(),classNameStr, classTeacherStr, tempDate);
			clBean = classService.editClass(clBean);
			if(clBean == null) {
				JOptionPane.showMessageDialog(this, "修改失败!班级名重复或其他原因");
				resetButton(e);
			}else {
				this.classBean = clBean;
				JOptionPane.showMessageDialog(this, "修改成功!");
				resetButton(e);
			}
			return;
		}
	}
	
	
	
}
