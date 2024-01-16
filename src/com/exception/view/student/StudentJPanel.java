package com.exception.view.student;

import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

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
import com.exception.view.roomView.RoomInfoJDialog;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFrame;

/** 
* @author 作者 Your-Name: 
* @version 创建时间：2021年6月2日 上午10:50:03 
* 类说明 
*/
public class StudentJPanel extends JPanel {
	
	private JFrame fatherFrame;
	private JTable table;
	private DefaultTableModel dtm = null;
	private JTextField stuName;
	private JComboBox stuClassComb;
	private JComboBox stuRoomComb;
	private ClassService classService = new ClassServiceImpl();
	private RoomService roomService = new RoomServiceImpl();
	private StudentService studentService = new StudentServiceImpl();
	
	
	private ArrayList<ClassBean> classList;
	private ArrayList<RoomBean> roomList;
	private ArrayList<StudentBean> stuList;

	/**
	 * Create the panel.
	 */
	public StudentJPanel(JFrame frame) {
		
		fatherFrame = frame;
		setBackground(SystemColor.control);
		setBounds(0, 0, 877, 511);
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 857, 412);
		add(scrollPane);
		
		table = new JTable();
		table.setShowVerticalLines(false);
		table.setFont(new Font("等线", Font.BOLD, 14));
		//设置用户不可拖动
		table.getTableHeader().setReorderingAllowed(false);
		//设置表格高度
		table.setRowHeight(35);
		//设置表格居中显示
		DefaultTableCellRenderer r =new DefaultTableCellRenderer();
		r.setHorizontalAlignment(JLabel.CENTER);
		table.setDefaultRenderer(Object.class, r);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u5B66\u751F\u59D3\u540D", "\u6027\u522B", "\u73ED\u7EA7", "\u6240\u5728\u623F\u95F4", "\u8054\u7CFB\u7535\u8BDD"
			}
		){
			 public boolean isCellEditable(int row, int column) {
		           return false;
		      };
		});
		table.getColumnModel().getColumn(3).setPreferredWidth(150);
		table.getColumnModel().getColumn(3).setMinWidth(150);
		table.getColumnModel().getColumn(4).setPreferredWidth(150);
		table.getColumnModel().getColumn(4).setMinWidth(150);
		scrollPane.setViewportView(table);
		dtm = (DefaultTableModel) table.getModel();
		
		JLabel lblNewLabel = new JLabel("学生姓名");
		lblNewLabel.setFont(new Font("等线", Font.PLAIN, 14));
		lblNewLabel.setBounds(40, 432, 68, 23);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("班       级");
		lblNewLabel_1.setFont(new Font("等线", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(40, 472, 68, 23);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("所在房间");
		lblNewLabel_1_1.setFont(new Font("等线", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(237, 433, 68, 23);
		add(lblNewLabel_1_1);
		
		JButton btnNewButton_1 = new JButton("学生换房");
		btnNewButton_1.setBounds(646, 433, 91, 21);
		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				exchangeRoomButton(e);
			}
		});
		add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("学生退房");
		btnNewButton_1_1.setBounds(747, 433, 91, 21);
		btnNewButton_1_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				stuDeleteButton(e);
			}
		});
		add(btnNewButton_1_1);
		
		JButton btnNewButton_1_1_1 = new JButton("查看学生详细信息");
		btnNewButton_1_1_1.setBounds(646, 473, 192, 21);
		btnNewButton_1_1_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				stuInfoButton(e);
			}
		});
		add(btnNewButton_1_1_1);
		
		JButton btnNewButton_2 = new JButton("查询");
		btnNewButton_2.setBounds(555, 433, 81, 21);
		btnNewButton_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				queryButton(e);
			}
		});
		add(btnNewButton_2);
		
		JButton btnNewButton_2_1 = new JButton("刷新");
		btnNewButton_2_1.setBounds(555, 473, 81, 21);
		btnNewButton_2_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				resetButton();
			}
		});
		add(btnNewButton_2_1);
		
		stuName = new JTextField();
		stuName.setBounds(107, 433, 99, 21);
		add(stuName);
		stuName.setColumns(10);
		
		
		stuClassComb = new JComboBox();
		stuClassComb.setBounds(107, 473, 99, 21);
		add(stuClassComb);
		
		stuRoomComb = new JComboBox();
		stuRoomComb.setBounds(305, 433, 99, 21);
		add(stuRoomComb);
	}

	protected void stuInfoButton(ActionEvent e) {
		int index = this.table.getSelectedRow();
		if(index==-1) {
			JOptionPane.showMessageDialog(this, "未选择学生信息");
			return;
		}
		StudentBean bean = this.stuList.get(index);
		if(bean==null)
			return;
		new StuInfoJDialog(this.fatherFrame, true, bean);
	}

	protected void stuDeleteButton(ActionEvent e) {
		int index = this.table.getSelectedRow();
		if(index==-1) {
			JOptionPane.showMessageDialog(this, "未选择学生信息");
			return;
		}
		StudentBean bean = this.stuList.get(index);
		if(bean==null)
			return;
		if(JOptionPane.showConfirmDialog(this, "确定已退房?","正在办理学生退房...",JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {
			if(studentService.deleteStudent(bean)) {
				JOptionPane.showMessageDialog(this, "学生退房成功!");
				queryAllStudent();
				return;
			}else {
				JOptionPane.showMessageDialog(this, "学生退房失败!");
				return;
			}
		}
	}

	protected void exchangeRoomButton(ActionEvent e) {
		int index = this.table.getSelectedRow();
		if(index==-1) {
			JOptionPane.showMessageDialog(this, "未选择学生信息");
			return;
		}
		StudentBean bean = this.stuList.get(index);
		if(bean==null)
			return;
		new StuExchangeRoomJDialog(this.fatherFrame,true,bean);
		queryAllStudent();
	}

	protected void queryButton(ActionEvent e) {
		String studentName = this.stuName.getText();
		ClassBean studentClass = (ClassBean) this.stuClassComb.getSelectedItem();
		RoomBean studentRoom = (RoomBean) this.stuRoomComb.getSelectedItem();
		if(!StrUtil.notEmpty(studentName)&&studentClass.getPk_id()==-1&&studentRoom.getPk_id()==-1) {
			queryAllStudent();
			return;
		}
		StudentBean studentBean = new StudentBean();
		studentBean.setF_name(studentName);
		studentBean.setClassBean(studentClass);
		studentBean.setRoomBean(studentRoom);
		dtm.setRowCount(0);
		stuList = studentService.querySomeStudentByInfo(studentBean);
		for(StudentBean stu : stuList) {
			Vector v = new Vector();
			v.add(stu.getF_name());
			v.add(stu.getF_sex() == 1 ?"男":"女");
			v.add(stu.getClassBean().getF_name());
			v.add(stu.getRoomBean().getF_address());
			v.add(stu.getF_phone());
			dtm.addRow(v);
		}
	}

	public void resetButton() {
		this.stuName.setText("");
		initComboBox();
		queryAllStudent();
	}
	
	private void queryAllStudent() {
		dtm.setRowCount(0);
		stuList = studentService.queryAllStudent();
		for(StudentBean stu : stuList) {
			Vector v = new Vector();
			v.add(stu.getF_name());
			v.add(stu.getF_sex()==1?"男":"女");
			v.add(stu.getClassBean().getF_name());
			v.add(stu.getRoomBean().getF_address());
			v.add(stu.getF_phone());
			dtm.addRow(v);
		}
	}

	private void initComboBox() {
		classList = classService.queryAllClass();
		roomList = roomService.queryAllRoom();
		ClassBean classBeans [] = new ClassBean[classList.size()+1];
		RoomBean roomBeans [] = new RoomBean[roomList.size()+1];
		classBeans[0] = new ClassBean(-1,"全匹配");
		roomBeans[0] = new RoomBean(-1,"全匹配");
		for(int i = 0;i < classList.size();i++) {
			classBeans[i+1] = classList.get(i);
		}
		for(int i = 0;i < roomList.size();i++) {
			roomBeans[i+1] = roomList.get(i);
		}
		this.stuClassComb.setModel(new DefaultComboBoxModel<ClassBean>(classBeans));
		this.stuRoomComb.setModel(new DefaultComboBoxModel<RoomBean>(roomBeans));
		this.stuClassComb.setSelectedIndex(0);
		this.stuRoomComb.setSelectedIndex(0);
	}
	
}
