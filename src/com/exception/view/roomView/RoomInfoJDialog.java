package com.exception.view.roomView;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.exception.bean.RoomBean;
import com.exception.bean.StudentBean;
import com.exception.util.DateUtil;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.Color;

/** 
* @author 作者 Your-Name: 
* @version 创建时间：2021年5月31日 上午11:37:22 
* 类说明 
*/
public class RoomInfoJDialog extends JDialog {
	
	private JTable table;
	private DefaultTableModel dtm;
	
	public RoomInfoJDialog(JFrame frame, boolean modal,RoomBean roomBean) {
		super(frame,modal);
		setTitle("房间信息");
		
		setBounds(100, 100, 480, 500);
		getContentPane().setLayout(null);
		
		setLocationRelativeTo(frame);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(36, 256, 402, 160);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		//table.setShowVerticalLines(true);
		table.setFont(new Font("等线", Font.BOLD, 14));
		//设置用户不可拖动
		table.getTableHeader().setReorderingAllowed(false);
		//设置表格高度
		table.setRowHeight(20);
		//设置表格居中显示
		DefaultTableCellRenderer r =new DefaultTableCellRenderer();
		r.setHorizontalAlignment(JLabel.CENTER);
		table.setDefaultRenderer(Object.class, r);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u5B66\u751F\u59D3\u540D", "\u6240\u5728\u73ED\u7EA7", "\u8054\u7CFB\u7535\u8BDD"
			}
		) {
			 public boolean isCellEditable(int row, int column) {
		           return false;
		     };
		});
		table.getColumnModel().getColumn(2).setPreferredWidth(150);
		table.getColumnModel().getColumn(2).setMinWidth(150);
		scrollPane.setViewportView(table);
		dtm = (DefaultTableModel) table.getModel();
		
		for(StudentBean studentBean : roomBean.getStudentBeans()) {
			Vector v = new Vector();
			v.add(studentBean.getF_name());
			v.add(studentBean.getClassBean().getF_name());
			v.add(studentBean.getF_phone());
			this.dtm.addRow(v);
		}
		
		
		
		JLabel lblNewLabel = new JLabel("房间地址");
		lblNewLabel.setFont(new Font("等线", Font.PLAIN, 13));
		lblNewLabel.setBounds(36, 10, 66, 19);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("可容纳人数");
		lblNewLabel_1.setFont(new Font("等线", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(36, 50, 66, 19);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("房    租");
		lblNewLabel_1_1.setFont(new Font("等线", Font.PLAIN, 13));
		lblNewLabel_1_1.setBounds(36, 90, 66, 19);
		getContentPane().add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("房    东");
		lblNewLabel_1_1_1.setFont(new Font("等线", Font.PLAIN, 13));
		lblNewLabel_1_1_1.setBounds(36, 130, 66, 19);
		getContentPane().add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("房间状态");
		lblNewLabel_1_1_1_1.setFont(new Font("等线", Font.PLAIN, 13));
		lblNewLabel_1_1_1_1.setBounds(36, 170, 66, 19);
		getContentPane().add(lblNewLabel_1_1_1_1);
		
		JLabel lblNewLabel_2 = new JLabel("房间户型");
		lblNewLabel_2.setFont(new Font("等线", Font.PLAIN, 13));
		lblNewLabel_2.setBounds(244, 10, 66, 19);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("已住人数");
		lblNewLabel_2_1.setFont(new Font("等线", Font.PLAIN, 13));
		lblNewLabel_2_1.setBounds(244, 50, 66, 19);
		getContentPane().add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("支付方式");
		lblNewLabel_2_1_1.setFont(new Font("等线", Font.PLAIN, 13));
		lblNewLabel_2_1_1.setBounds(244, 90, 66, 19);
		getContentPane().add(lblNewLabel_2_1_1);
		
		JLabel lblNewLabel_2_1_1_1 = new JLabel("房东电话");
		lblNewLabel_2_1_1_1.setFont(new Font("等线", Font.PLAIN, 13));
		lblNewLabel_2_1_1_1.setBounds(244, 130, 66, 19);
		getContentPane().add(lblNewLabel_2_1_1_1);
		
		JLabel lblNewLabel_2_1_1_1_1 = new JLabel("房间类型");
		lblNewLabel_2_1_1_1_1.setFont(new Font("等线", Font.PLAIN, 13));
		lblNewLabel_2_1_1_1_1.setBounds(244, 170, 66, 19);
		getContentPane().add(lblNewLabel_2_1_1_1_1);
		
		JLabel lblNewLabel_3 = new JLabel("租房日期");
		lblNewLabel_3.setBounds(36, 210, 58, 15);
		getContentPane().add(lblNewLabel_3);
		
		JButton btnNewButton = new JButton("确定");
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RoomInfoJDialog.this.dispose();
			}
		});
		btnNewButton.setBounds(181, 430, 97, 23);
		getContentPane().add(btnNewButton);
		
		JLabel roomAddress = new JLabel(roomBean.getF_address());
		roomAddress.setForeground(Color.RED);
		roomAddress.setFont(new Font("等线", Font.PLAIN, 13));
		roomAddress.setBounds(112, 10, 109, 19);
		getContentPane().add(roomAddress);
		
		JLabel roomMaxNum = new JLabel(roomBean.getF_maxnum()+" 人");
		roomMaxNum.setForeground(Color.RED);
		roomMaxNum.setFont(new Font("等线", Font.PLAIN, 13));
		roomMaxNum.setBounds(112, 50, 109, 19);
		getContentPane().add(roomMaxNum);
		
		JLabel roomPrice = new JLabel(roomBean.getF_price()+" / 月");
		roomPrice.setForeground(Color.RED);
		roomPrice.setFont(new Font("等线", Font.PLAIN, 13));
		roomPrice.setBounds(112, 90, 109, 19);
		getContentPane().add(roomPrice);
		
		JLabel roomMaster = new JLabel(roomBean.getF_master());
		roomMaster.setForeground(Color.RED);
		roomMaster.setFont(new Font("等线", Font.PLAIN, 13));
		roomMaster.setBounds(112, 130, 109, 19);
		getContentPane().add(roomMaster);
		
		JLabel roomStatus = new JLabel(roomBean.getF_state() == 1 ? "正常":"设备损坏");
		roomStatus.setForeground(Color.RED);
		roomStatus.setFont(new Font("等线", Font.PLAIN, 13));
		roomStatus.setBounds(112, 170, 109, 19);
		getContentPane().add(roomStatus);
		
		JLabel roomTime = new JLabel(DateUtil.DateToSqlDate(roomBean.getF_time()));
		roomTime.setForeground(Color.RED);
		roomTime.setFont(new Font("等线", Font.PLAIN, 13));
		roomTime.setBounds(112, 210, 109, 19);
		getContentPane().add(roomTime);
		
		JLabel roomType = new JLabel(roomBean.getF_type());
		roomType.setForeground(Color.RED);
		roomType.setFont(new Font("等线", Font.PLAIN, 13));
		roomType.setBounds(320, 10, 109, 19);
		getContentPane().add(roomType);
		
		JLabel roomStuCount = new JLabel(roomBean.getF_stucount()+" 人");
		roomStuCount.setForeground(Color.RED);
		roomStuCount.setFont(new Font("等线", Font.PLAIN, 13));
		roomStuCount.setBounds(320, 50, 109, 19);
		getContentPane().add(roomStuCount);
		
		JLabel roomPay = new JLabel(roomBean.getF_pay());
		roomPay.setForeground(Color.RED);
		roomPay.setFont(new Font("等线", Font.PLAIN, 13));
		roomPay.setBounds(320, 90, 109, 19);
		getContentPane().add(roomPay);
		
		JLabel roomPhone = new JLabel(roomBean.getF_phone());
		roomPhone.setForeground(Color.RED);
		roomPhone.setFont(new Font("等线", Font.PLAIN, 13));
		roomPhone.setBounds(320, 130, 109, 19);
		getContentPane().add(roomPhone);
		
		JLabel roomSexType = new JLabel(roomBean.getF_sextype() == 1 ? "男生宿舍":"女生宿舍");
		roomSexType.setForeground(Color.RED);
		roomSexType.setFont(new Font("等线", Font.PLAIN, 13));
		roomSexType.setBounds(320, 170, 109, 19);
		getContentPane().add(roomSexType);
		
		setVisible(true);
	}
}
