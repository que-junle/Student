package com.exception.view.roomView;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.swingx.JXDatePicker;

import com.exception.bean.RecordBean;
import com.exception.bean.RoomBean;
import com.exception.service.RecordService;
import com.exception.serviceImpl.RecordServiceImpl;
import com.exception.util.DateUtil;
import com.exception.util.StrUtil;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import javax.swing.JEditorPane;
import javax.swing.JFrame;

/** 
* @author 作者 Your-Name: 
* @version 创建时间：2021年5月31日 下午2:07:09 
* 类说明 
*/
public class RoomRecordJDialog extends JDialog {
	
	private JFrame fatherFrame;
	private RoomBean thisRoomBean;
	
	private JTable table;
	private DefaultTableModel dtm = null;
	
	private JXDatePicker datePicker;
	
	private JEditorPane recordInfo;
	
	private RecordService recordService = new RecordServiceImpl();
	
	private ArrayList<RecordBean> arrayList;

	public RoomRecordJDialog(JFrame frame, boolean modal,RoomBean bean) {
		super(frame,modal);
		fatherFrame = frame;
		this.thisRoomBean = bean;
		
		setTitle("房间设施维护:" + bean.getF_address());
		setBounds(100, 100, 504, 420);
		setLocationRelativeTo(frame);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 474, 176);
		getContentPane().add(scrollPane);
		
		table = new JTable();
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
				"\u635F\u574F\u63CF\u8FF0", "\u635F\u574F\u65E5\u671F", "\u662F\u5426\u89E3\u51B3"
			}
		) {
			 public boolean isCellEditable(int row, int column) {
		           return false;
		     };
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(150);
		table.getColumnModel().getColumn(0).setMinWidth(150);
		scrollPane.setViewportView(table);
		dtm = (DefaultTableModel) table.getModel();
		
		
		JLabel lblNewLabel = new JLabel("损坏日期");
		lblNewLabel.setFont(new Font("等线", Font.PLAIN, 15));
		lblNewLabel.setBounds(67, 200, 72, 17);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("损坏描述");
		lblNewLabel_1.setFont(new Font("等线", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(67, 244, 72, 17);
		getContentPane().add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("添加设施损坏记录");
		btnNewButton.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				addRecordButton(e);
			}
		});
		btnNewButton.setBounds(22, 350, 143, 23);
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("处理已损坏设施");
		btnNewButton_1.setBounds(199, 350, 122, 23);
		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				handleRecordButton(e);
			}
		});
		getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("退出");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				RoomRecordJDialog.this.dispose();
			}
		});
		btnNewButton_1_1.setBounds(385, 350, 78, 23);
		getContentPane().add(btnNewButton_1_1);
		
		recordInfo = new JEditorPane();
		recordInfo.setFont(new Font("等线", Font.BOLD, 15));
		recordInfo.setBounds(140, 244, 212, 75);
		getContentPane().add(recordInfo);
		
		datePicker = new JXDatePicker();
		datePicker.getEditor().setFont(new Font("微软雅黑", Font.PLAIN, 13));
		datePicker.setBounds(140, 200, 143, 21);
		getContentPane().add(datePicker);
		queryRoomRecord();
		
		setVisible(true);
	}

	protected void handleRecordButton(ActionEvent e) {
		if(JOptionPane.showConfirmDialog(this, "房间设施真的维修好了吗?","正在处理设施记录...",JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {
			int index = this.table.getSelectedRow();
			RecordBean rBean = this.arrayList.get(index);
			if(recordService.handleRoomRecord(rBean)) {
				JOptionPane.showMessageDialog(this, "已更新记录");
				queryRoomRecord();
			}else {
				JOptionPane.showMessageDialog(this, "更新失败");
			}
		}
	}

	protected void addRecordButton(ActionEvent e) {
		Date date = this.datePicker.getDate();
		String text = this.recordInfo.getText();
		if(date==null ||!StrUtil.notEmpty(text)) {
			JOptionPane.showMessageDialog(this, "请填写详细信息!");
			return;
		}
		RecordBean recordBean = new RecordBean(date,text,0,thisRoomBean.getPk_id());
		if(recordService.addRoomRecord(recordBean)) {
			JOptionPane.showMessageDialog(fatherFrame, "添加成功！");
			this.datePicker.getEditor().setText("");
			this.recordInfo.setText("");
			queryRoomRecord();
		}else {
			JOptionPane.showMessageDialog(fatherFrame, "添加失败！");
		}
	}
	
	public void queryRoomRecord() {
		arrayList = recordService.queryAllRecordByRoom(thisRoomBean);
		dtm.setRowCount(0);
		for(RecordBean recordBean : arrayList) {
			Vector v = new Vector();
			v.add(recordBean.getF_info());
			v.add(DateUtil.DateToSqlDate(recordBean.getF_time()));
			v.add(recordBean.getF_isok() == 1 ? "已解决" : "未解决");
			this.dtm.addRow(v);
		}
	}
	
}
