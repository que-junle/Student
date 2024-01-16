package com.exception.view.roomView;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.exception.bean.ClassBean;
import com.exception.bean.RoomBean;
import com.exception.bean.StudentBean;
import com.exception.service.RoomService;
import com.exception.serviceImpl.RoomServiceImpl;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Color;

/** 
* @author 作者 Your-Name: 
* @version 创建时间：2021年5月24日 下午6:50:44 
* 类说明 
*/
public class RoomJPanel extends JPanel {


	private JFrame fatherFrame;
	private DefaultTableModel dtm = null;
	private JTable table;
	private JTextField roomAddress;
	private JComboBox roomStatus;
	private JComboBox roomType;
	private JComboBox roomable;
	private RoomService roomService = new RoomServiceImpl();
	
	private ArrayList<RoomBean> arrayList;
	
	public RoomJPanel(JFrame jFrame) {
		
		this.fatherFrame = jFrame;
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
				"\u623F\u95F4\u5730\u5740", "\u53EF\u5BB9\u7EB3\u4EBA\u6570", "\u5DF2\u4F4F\u4EBA\u6570", "\u623F\u79DF", "\u623F\u95F4\u72B6\u6001", "\u623F\u4E1C", "\u623F\u4E1C\u7535\u8BDD", "\u623F\u95F4\u7C7B\u578B"
			}
		) {
			 public boolean isCellEditable(int row, int column) {
		           return false;
		      };
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(0).setMinWidth(100);
		scrollPane.setViewportView(table);
		this.dtm = (DefaultTableModel) table.getModel();
		
		JLabel text1 = new JLabel("房间地址");
		text1.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		text1.setBounds(149, 432, 67, 24);
		add(text1);
		
		roomAddress = new JTextField();
		roomAddress.setBounds(212, 435, 103, 20);
		add(roomAddress);
		roomAddress.setColumns(10);
		
		JLabel text2 = new JLabel("可住房间");
		text2.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		text2.setBounds(149, 473, 67, 24);
		add(text2);
		
		String strs[] = {"不限","可住房间"};
		roomable = new JComboBox(strs);
		roomable.setBounds(212, 476, 103, 21);
		add(roomable);
		
		JLabel text3 = new JLabel("房间状态");
		text3.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		text3.setBounds(342, 432, 67, 24);
		add(text3);
		
		String strs1[] = {"不限","正常","设备损坏"};
		roomStatus = new JComboBox(strs1);
		roomStatus.setBounds(407, 435, 103, 21);
		add(roomStatus);
		
		JLabel text4 = new JLabel("房间类型");
		text4.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		text4.setBounds(342, 473, 67, 24);
		add(text4);
		
		String strs2[] = {"不限","男生寝室","女生寝室"};
		roomType = new JComboBox(strs2);
		roomType.setBounds(407, 476, 103, 21);
		add(roomType);
		
		JButton btnNewButton = new JButton("查询");
		btnNewButton.setBounds(588, 435, 67, 23);
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				querySomeRoom(e);
			}
		});
		add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("查看房间信息");
		btnNewButton_1.setBounds(588, 476, 118, 23);
		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				roomInfoButton(e);
			}
		});
		add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("查看房间设施");
		btnNewButton_1_1.setBounds(716, 476, 118, 23);
		btnNewButton_1_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				roomRecordButton(e);
			}
		});
		add(btnNewButton_1_1);
		
		JButton btnNewButton_2 = new JButton("删除");
		btnNewButton_2.setBounds(679, 435, 67, 23);
		btnNewButton_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				deleteButton(e);
			}
		});
		add(btnNewButton_2);
		
		JButton btnNewButton_2_1 = new JButton("重置");
		btnNewButton_2_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				resetButton();
			}
		});
		btnNewButton_2_1.setBounds(767, 435, 67, 23);
		add(btnNewButton_2_1);
		
		JLabel lblNewLabel = new JLabel("(近似)");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 13));
		lblNewLabel.setBounds(107, 436, 49, 21);
		add(lblNewLabel);
	}

	protected void roomRecordButton(ActionEvent e) {
		int index = this.table.getSelectedRow();
		if(index == -1) {
			JOptionPane.showMessageDialog(this, "您未选择任何房间");
			return;
		}
		RoomBean bean = arrayList.get(index);
		if(bean==null)
			return;
		new RoomRecordJDialog(this.fatherFrame,true,bean);
		queryAllRoom();
	}

	protected void roomInfoButton(ActionEvent e) {
		int index = this.table.getSelectedRow();
		if(index==-1) {
			JOptionPane.showMessageDialog(this, "您未选择任何房间");
			return;
		}
		RoomBean bean = arrayList.get(index);
		if(bean==null)
			return;
		new RoomInfoJDialog(this.fatherFrame,true,bean);
		queryAllRoom();
	}

	protected void deleteButton(ActionEvent e) {
		if(this.table.getSelectedRow() == -1) {
			JOptionPane.showMessageDialog(this, "您未选择任何房间");
			return;
		}
		if(JOptionPane.showConfirmDialog(this, "是否要删除此房间?","正在删除房间...",JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {
			int index = this.table.getSelectedRow();
			RoomBean bean = arrayList.get(index);
			if(bean==null)
				return;
			if(roomService.deleteRoom(bean)) {
				JOptionPane.showMessageDialog(this, "删除房间成功！");
				resetButton();
			}else {
				JOptionPane.showMessageDialog(this, "房间内还有学生居住,无法删除");
			}
		}
	}

	public void resetButton() {
		this.roomAddress.setText("");
		this.roomable.setSelectedIndex(0);
		this.roomStatus.setSelectedIndex(0);
		this.roomType.setSelectedIndex(0);
		queryAllRoom();
	}

	protected void querySomeRoom(ActionEvent e) {
		String roomAddressStr = this.roomAddress.getText();
		int roomStatusInt = this.roomStatus.getSelectedIndex();
		int roomableInt = this.roomable.getSelectedIndex();
		int roomTypeInt = this.roomType.getSelectedIndex();
		arrayList = roomService.querySomeRoomByInfo(roomAddressStr, roomStatusInt, roomableInt, roomTypeInt);
		dtm.setRowCount(0);
		for(RoomBean roomBean : arrayList) {
			Vector v = new Vector();
			v.add(roomBean.getF_address());
			v.add(roomBean.getF_maxnum());
			v.add(roomBean.getF_stucount());
			v.add(roomBean.getF_price()+"/月");
			v.add(roomBean.getF_state() ==1 ? "正常" : "损坏");
			v.add(roomBean.getF_master());
			v.add(roomBean.getF_phone());
			v.add(roomBean.getF_sextype() == 1 ? "男生宿舍":"女生宿舍");
			this.dtm.addRow(v);
		}
	}

	protected void queryAllRoom() {
		arrayList = roomService.queryAllRoom();
		dtm.setRowCount(0);
		for(RoomBean roomBean : arrayList) {
			Vector v = new Vector();
			v.add(roomBean.getF_address());
			v.add(roomBean.getF_maxnum());
			v.add(roomBean.getF_stucount());
			v.add(roomBean.getF_price()+"/月");
			v.add(roomBean.getF_state() ==1 ? "正常" : "损坏");
			v.add(roomBean.getF_master());
			v.add(roomBean.getF_phone());
			v.add(roomBean.getF_sextype() ==1?"男生宿舍":"女生宿舍");
			this.dtm.addRow(v);
		}
	}
}
