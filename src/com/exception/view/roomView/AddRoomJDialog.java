package com.exception.view.roomView;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jdesktop.swingx.JXDatePicker;

import com.exception.bean.RoomBean;
import com.exception.service.RoomService;
import com.exception.serviceImpl.RoomServiceImpl;
import com.exception.util.StrUtil;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JTextField;
import javax.swing.JComboBox;

/** 
* @author 作者 Your-Name: 
* @version 创建时间：2021年5月28日 下午2:27:42 
* 类说明 
*/
public class AddRoomJDialog extends JDialog {
	
	private JTextField addressText;
	private JTextField maxNumText;
	private JTextField roomPriceText;
	private JTextField masterText;
	private JComboBox roomStatus;
	private JTextField roomTypeText;
	private JComboBox roomType;
	private JTextField roomPayText;
	private JTextField roomPhone;
	private JXDatePicker datePicker;


	
	 public AddRoomJDialog(JFrame frame, boolean modal) {
		 
		super(frame,modal);
		setTitle("添加房间");
		setBounds(100, 100, 520, 360);
		getContentPane().setLayout(null);
		
		setLocationRelativeTo(frame);
		
		JLabel lblNewLabel = new JLabel("房间地址");
		lblNewLabel.setFont(new Font("宋体", Font.BOLD, 14));
		lblNewLabel.setBounds(27, 41, 70, 22);
		getContentPane().add(lblNewLabel);
		
		addressText = new JTextField();
		addressText.setBounds(116, 42, 120, 21);
		getContentPane().add(addressText);
		addressText.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("可容纳人数");
		lblNewLabel_1.setFont(new Font("宋体", Font.BOLD, 14));
		lblNewLabel_1.setBounds(27, 92, 85, 22);
		getContentPane().add(lblNewLabel_1);
		
		maxNumText = new JTextField();
		maxNumText.setColumns(10);
		maxNumText.setBounds(116, 92, 120, 21);
		getContentPane().add(maxNumText);
		
		JLabel lblNewLabel_1_1 = new JLabel("房    租");
		lblNewLabel_1_1.setFont(new Font("宋体", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(27, 140, 85, 22);
		getContentPane().add(lblNewLabel_1_1);
		
		roomPriceText = new JTextField();
		roomPriceText.setColumns(10);
		roomPriceText.setBounds(116, 140, 120, 21);
		getContentPane().add(roomPriceText);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("房    东");
		lblNewLabel_1_1_1.setFont(new Font("宋体", Font.BOLD, 14));
		lblNewLabel_1_1_1.setBounds(27, 188, 85, 22);
		getContentPane().add(lblNewLabel_1_1_1);
		
		masterText = new JTextField();
		masterText.setColumns(10);
		masterText.setBounds(116, 188, 120, 21);
		getContentPane().add(masterText);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("房间状态");
		lblNewLabel_1_1_1_1.setFont(new Font("宋体", Font.BOLD, 14));
		lblNewLabel_1_1_1_1.setBounds(28, 236, 85, 22);
		getContentPane().add(lblNewLabel_1_1_1_1);
		
		String strs[] = {"正常","设备损坏"};
		roomStatus = new JComboBox(strs);
		roomStatus.setBounds(117, 236, 119, 21);
		getContentPane().add(roomStatus);
		
		JLabel lblNewLabel_2 = new JLabel("房间户型");
		lblNewLabel_2.setFont(new Font("宋体", Font.BOLD, 14));
		lblNewLabel_2.setBounds(284, 41, 70, 22);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("房间类型");
		lblNewLabel_2_1.setFont(new Font("宋体", Font.BOLD, 14));
		lblNewLabel_2_1.setBounds(283, 91, 70, 22);
		getContentPane().add(lblNewLabel_2_1);
		
		String strs1[]= {"男生宿舍","女生宿舍"};
		roomType = new JComboBox(strs1);
		roomType.setBounds(354, 91, 119, 21);
		getContentPane().add(roomType);
		
		roomTypeText = new JTextField();
		roomTypeText.setColumns(10);
		roomTypeText.setBounds(355, 42, 120, 21);
		getContentPane().add(roomTypeText);
		
		JLabel lblNewLabel_2_2 = new JLabel("支付方式");
		lblNewLabel_2_2.setFont(new Font("宋体", Font.BOLD, 14));
		lblNewLabel_2_2.setBounds(283, 139, 70, 22);
		getContentPane().add(lblNewLabel_2_2);
		
		roomPayText = new JTextField();
		roomPayText.setColumns(10);
		roomPayText.setBounds(354, 139, 120, 21);
		getContentPane().add(roomPayText);
		
		JLabel lblNewLabel_2_2_1 = new JLabel("房东电话");
		lblNewLabel_2_2_1.setFont(new Font("宋体", Font.BOLD, 14));
		lblNewLabel_2_2_1.setBounds(283, 186, 70, 22);
		getContentPane().add(lblNewLabel_2_2_1);
		
		roomPhone = new JTextField();
		roomPhone.setColumns(10);
		roomPhone.setBounds(354, 185, 120, 21);
		getContentPane().add(roomPhone);
		
		JLabel lblNewLabel_2_2_1_1 = new JLabel("租房日期");
		lblNewLabel_2_2_1_1.setFont(new Font("宋体", Font.BOLD, 14));
		lblNewLabel_2_2_1_1.setBounds(283, 236, 70, 22);
		getContentPane().add(lblNewLabel_2_2_1_1);
		
		datePicker = new JXDatePicker();
		datePicker.getEditor().setFont(new Font("微软雅黑", Font.PLAIN, 13));
		datePicker.setBounds(354, 236, 120, 21);
		getContentPane().add(datePicker);
		
		JButton btnNewButton = new JButton("添加房间");
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addRoomButton(e);
			}
		});
		btnNewButton.setBounds(292, 293, 97, 23);
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("重置内容");
		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				resetButton(e);
			}
		});
		btnNewButton_1.setBounds(399, 293, 97, 23);
		getContentPane().add(btnNewButton_1);
		
		setVisible(true);
	}

	protected void resetButton(ActionEvent e) {
		this.addressText.setText("");
		this.maxNumText.setText("");
		this.roomPriceText.setText("");
		this.masterText.setText("");
		this.roomStatus.setSelectedIndex(0);
		this.roomType.setSelectedIndex(0);
		this.roomTypeText.setText("");
		this.roomPayText.setText("");
		this.roomPhone.setText("");
		this.datePicker.getEditor().setText("");
	}

	protected void addRoomButton(ActionEvent e) {
		String [] strs = new String[7];
		strs[0] = this.addressText.getText();
		strs[1] = this.maxNumText.getText();
		strs[2] = this.roomPriceText.getText();
		strs[3] = this.masterText.getText();
		int roomStatus = ((String)this.roomStatus.getSelectedItem()).equals("正常") ? 1:2;
		int roomType = ((String)this.roomType.getSelectedItem()).equals("男生宿舍") ? 1:2;
		strs[4] = this.roomTypeText.getText();
		strs[5] = this.roomPayText.getText();
		strs[6] = this.roomPhone.getText();
		Date tempDate = this.datePicker.getDate();
		for(int i = 0; i < strs.length; i++) {
			if(!StrUtil.notEmpty(strs[i])) {
				JOptionPane.showMessageDialog(this, "信息不完整，请重试!");
				return;
			}
		}
		if(tempDate == null) {
			JOptionPane.showMessageDialog(this, "信息不完整，请重试!");
			return;
		}
		String numb ="[0-9]{1,6}";
		if(!strs[1].matches(numb)||!strs[2].matches(numb)) {
			JOptionPane.showMessageDialog(this, "数据格式有误，请重试!");
			return;
		}
		RoomBean roomBean = new RoomBean(strs[0],Integer.valueOf(strs[1]),0,Integer.valueOf(strs[2]),strs[3],strs[6],roomStatus,strs[4],roomType,strs[5],tempDate);
		RoomService roomService = new RoomServiceImpl();
		int addRoom = roomService.addRoom(roomBean);
		if(addRoom == 1) {
			if(roomBean.getF_state()==2) {
				if(JOptionPane.showConfirmDialog(this, "请先补充损坏记录,否则无法添加损坏房间","提示",JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {
					JOptionPane.showMessageDialog(this, "添加成功");
					resetButton(e);
					return;
				}
				if(roomService.deleteRoom(roomBean)) {
					JOptionPane.showMessageDialog(this, "添加失败");
					return;
				}
			}else {
				JOptionPane.showMessageDialog(this, "添加成功");
				resetButton(e);
				return;
			}
		}else if(addRoom == 0) {
			JOptionPane.showMessageDialog(this, "添加失败");
		}else {
			JOptionPane.showMessageDialog(this, "房间重复，添加失败");
		}
		
	}
}
