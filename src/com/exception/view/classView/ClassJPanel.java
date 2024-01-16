package com.exception.view.classView;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;

import org.jdesktop.swingx.JXDatePicker;

import com.exception.bean.ClassBean;
import com.exception.bean.RecordBean;
import com.exception.service.ClassService;
import com.exception.serviceImpl.ClassServiceImpl;
import com.exception.util.StrUtil;

import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/** 
* @author 作者 Your-Name: 
* @version 创建时间：2021年5月24日 上午9:13:43 
* 类说明 
*/
public class ClassJPanel extends JPanel {
	
	private JFrame fatherFrame;
	private JTable table;
	private JTextField classNumb;
	private JTextField className;
	private JTextField classTeacher;
	
	private JXDatePicker datepick = new JXDatePicker();
	
	private DefaultTableModel dtm = null;
	private ClassService classService = new ClassServiceImpl();
	
	public ClassJPanel(JFrame jFrame) {
		this.fatherFrame = jFrame;
		setBackground(SystemColor.control);
		setBounds(0, 0, 877, 511);
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 857, 412);
		add(scrollPane);
		
		table = new JTable();
		//table.setShowVerticalLines(true);
		table.setFont(new Font("等线", Font.BOLD, 14));
		//设置用户不可拖动
		table.getTableHeader().setReorderingAllowed(false);
		//设置表格高度
		table.setRowHeight(30);
		//设置表格居中显示
		DefaultTableCellRenderer r =new DefaultTableCellRenderer();
		
		r.setHorizontalAlignment(JLabel.CENTER);
		table.setDefaultRenderer(Object.class, r);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u73ED\u7EA7\u7F16\u53F7", "\u73ED\u7EA7\u540D\u79F0", "\u4EE3\u8BFE\u8001\u5E08", "\u5F00\u73ED\u65F6\u95F4"
			}
		) {
			 public boolean isCellEditable(int row, int column) {
		           return false;
		      };
		});
		scrollPane.setViewportView(table);
		this.dtm = (DefaultTableModel) table.getModel();
		
		JLabel lblNewLabel = new JLabel("班级编号:");
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		lblNewLabel.setBounds(81, 432, 69, 25);
		add(lblNewLabel);
		
		classNumb = new JTextField();
		classNumb.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		classNumb.setBounds(149, 435, 101, 20);
		add(classNumb);
		classNumb.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("班级名称:");
		lblNewLabel_1.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(82, 473, 69, 25);
		add(lblNewLabel_1);
		
		className = new JTextField();
		className.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		className.setColumns(10);
		className.setBounds(149, 476, 102, 20);
		add(className);
		
		JLabel lblNewLabel_2 = new JLabel("(精确)");
		lblNewLabel_2.setForeground(Color.RED);
		lblNewLabel_2.setBounds(40, 433, 49, 22);
		add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("(近似)");
		lblNewLabel_2_1.setForeground(Color.RED);
		lblNewLabel_2_1.setBounds(40, 474, 49, 22);
		add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_3 = new JLabel("带班老师:");
		lblNewLabel_3.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(319, 431, 69, 25);
		add(lblNewLabel_3);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("(近似)");
		lblNewLabel_2_1_1.setForeground(Color.RED);
		lblNewLabel_2_1_1.setBounds(281, 433, 49, 22);
		add(lblNewLabel_2_1_1);
		
		classTeacher = new JTextField();
		classTeacher.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		classTeacher.setColumns(10);
		classTeacher.setBounds(386, 435, 88, 20);
		add(classTeacher);
		
		datepick.getEditor().setFont(new Font("微软雅黑", Font.PLAIN, 13));
		datepick.setBounds(386, 475, 140, 21);
		add(datepick);
		
		JButton btnNewButton = new JButton("修改");
		btnNewButton.setBounds(639, 476, 88, 23);
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clickEditButton(e);
				
			}
		});
		add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("删除");
		btnNewButton_1.setBounds(760, 434, 88, 23);
		btnNewButton_1.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				clickDeleteButton(e);
			}
		});
		add(btnNewButton_1);
		
		JLabel lblNewLabel_3_1 = new JLabel("开班日期:");
		lblNewLabel_3_1.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		lblNewLabel_3_1.setBounds(319, 473, 69, 25);
		add(lblNewLabel_3_1);
		
		JButton btnNewButton_2 = new JButton("查询");
		btnNewButton_2.setBounds(639, 434, 88, 23);
		btnNewButton_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clickQueryButton(e);
			}
		});
		add(btnNewButton_2);
		
		JButton btnNewButton_1_1 = new JButton("重置");
		btnNewButton_1_1.setBounds(760, 476, 88, 23);
		btnNewButton_1_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				resetButton();
			}
		});
		add(btnNewButton_1_1);
	}

	//修改按钮动作属性
	protected void clickEditButton(ActionEvent e) {
		if(this.table.getSelectedRow() == -1) {
			JOptionPane.showMessageDialog(this, "您未选择任何班级");
			return;
		}
		int classNumb = (int) dtm.getValueAt(this.table.getSelectedRow(),0);
		String className = (String)dtm.getValueAt(this.table.getSelectedRow(), 1);
		String classTeacher = (String)dtm.getValueAt(this.table.getSelectedRow(), 2);
		Date date = (Date)dtm.getValueAt(this.table.getSelectedRow(), 3);
		ClassBean classBean = new ClassBean(classNumb,className,classTeacher,date);
		new AddClassJDialog(fatherFrame,true,classBean);
	}
	
	//删除按钮动作属性
	protected void clickDeleteButton(ActionEvent e) {
		if(this.table.getSelectedRow() == -1) {
			JOptionPane.showMessageDialog(this, "您未选择任何班级");
			return;
		}
		if(JOptionPane.showConfirmDialog(this, "是否要删除此班级?","正在删除班级...",JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {
			int classNumb = (int) dtm.getValueAt(this.table.getSelectedRow(),0);
			String className = (String)dtm.getValueAt(this.table.getSelectedRow(), 1);
			String classTeacher = (String)dtm.getValueAt(this.table.getSelectedRow(), 2);
			Date date = (Date)dtm.getValueAt(this.table.getSelectedRow(), 3);
			ClassBean classBean = new ClassBean(classNumb,className,classTeacher,date);
			boolean flag = classService.deleteClass(classBean);
			if(!flag)
				JOptionPane.showMessageDialog(this, "此班级内有学生,无法删除");
			else
				JOptionPane.showMessageDialog(this, "删除成功");
			resetButton();
		}
	}

	//重置按钮动作,因为是卡片布局,没办法设置关闭和开启,我们把他的权限开大,在外部调用时自动进行刷新
	public void resetButton() {
		this.className.setText("");
		this.classNumb.setText("");
		this.classTeacher.setText("");
		this.datepick.getEditor().setText("");
		queryAllClass();
	}

	//查找全部班级
	protected void queryAllClass() {
		this.dtm.setRowCount(0);
		List<ClassBean> classList = classService.queryAllClass();
		for(ClassBean w : classList) {
			Vector v = new Vector();
			v.add(w.getPk_id());
			v.add(w.getF_name());
			v.add(w.getF_teacher());
			v.add(w.getF_time());
			this.dtm.addRow(v);
		}
	}
	
	//查找按钮动作
	protected void clickQueryButton(ActionEvent e) {
		String classNumbStr = this.classNumb.getText();
		String classNameStr = this.className.getText();
		String classTeacherStr = this.classTeacher.getText();
		Date classDate = this.datepick.getDate();
		//如果没有填入任何有效信息点击查询的话
		if(!StrUtil.notEmpty(classNumbStr)&&!StrUtil.notEmpty(classNameStr)&&!StrUtil.notEmpty(classTeacherStr)&& classDate==null) {
			return;
		}
		String numb ="[0-9]{1,6}";
		if(StrUtil.notEmpty(classNumbStr)) {
			if(!classNumbStr.matches(numb)) {
				JOptionPane.showMessageDialog(this, "您输入的班级编号有误!");
				return;
			}
		}
		List<ClassBean> querySomeClassByInfo = classService.querySomeClassByInfo(classNumbStr, classNameStr, classTeacherStr, classDate);
		this.dtm.setRowCount(0);
		for(ClassBean classBean : querySomeClassByInfo) {
			Vector v = new Vector();
			v.add(classBean.getPk_id());
			v.add(classBean.getF_name());
			v.add(classBean.getF_teacher());
			v.add(classBean.getF_time());
			this.dtm.addRow(v);
		}
	}
}
