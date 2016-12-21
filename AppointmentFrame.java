package com.user;
import java.awt.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.awt.event.ActionEvent;
import dao.*;
public class AppointmentFrame extends JFrame implements ActionListener {
	private int pid;
	private String panme;
	private String psex;
	private int page;
	private String ptel;
	private String pdesk;
	private String pdate;
	private String ispay;
	
	private JFrame frame;
	private JLabel lblNewLabel;
	private JTextField txtname;
	private JTextField txtage;
	private JTextField txtphone;
	private JTextField txtdate;
	private JTextField txtsex;
	private JTextField txtdesk;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AppointmentFrame window = new AppointmentFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AppointmentFrame() {
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel p0 = new JPanel();
		p0.setBackground(new Color(135, 206, 235));
		frame.getContentPane().add(p0);
		p0.setLayout(null);
		
		JLabel label_4 = new JLabel("\u95E8\u8BCA\u9884\u7EA6");
		label_4.setFont(new Font("华文宋体", Font.BOLD, 25));
		label_4.setBounds(53, 78, 126, 72);
		p0.add(label_4);
		
		JPanel p1 = new JPanel();
		frame.getContentPane().add(p1);
		p1.setBackground(new Color(135, 206, 235));
		p1.setLayout(null);
		
		lblNewLabel = new JLabel("\u59D3\u540D");
		lblNewLabel.setBounds(41, 45, 44, 15);
		p1.add(lblNewLabel);
		
		txtname = new JTextField();
		txtname.setBounds(90, 42, 66, 21);
		p1.add(txtname);
		txtname.setColumns(10);
		
		JLabel label = new JLabel("\u6027\u522B");
		label.setBounds(41, 73, 44, 15);
		p1.add(label);
		
		JLabel label_1 = new JLabel("\u5E74\u9F84");
		label_1.setBounds(41, 98, 45, 15);
		p1.add(label_1);
		
		txtage = new JTextField();
		txtage.setBounds(90, 99, 66, 21);
		p1.add(txtage);
		txtage.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("\u8054\u7CFB\u65B9\u5F0F");
		lblNewLabel_1.setBounds(19, 129, 66, 15);
		p1.add(lblNewLabel_1);
		
		txtphone = new JTextField();
		txtphone.setBounds(90, 126, 66, 21);
		p1.add(txtphone);
		txtphone.setColumns(10);
		
		JLabel label_2 = new JLabel("\u9884\u7EA6\u65F6\u95F4");
		label_2.setBounds(19, 157, 66, 15);
		p1.add(label_2);
		
		txtdate = new JTextField();
		txtdate.setBounds(90, 154, 66, 21);
		p1.add(txtdate);
		txtdate.setColumns(10);
		
		JLabel label_3 = new JLabel("\u9884\u7EA6\u79D1\u5BA4");
		label_3.setBounds(19, 182, 66, 15);
		p1.add(label_3);
		
		JButton btnyes = new JButton("\u9884\u7EA6");
		btnyes.setBounds(29, 213, 72, 23);
		btnyes.addActionListener(this);
		p1.add(btnyes);
		
		JButton btnCancel = new JButton("\u53D6\u6D88");
		btnCancel.setBounds(113, 213, 77, 23);
		btnCancel.addActionListener(this);
		p1.add(btnCancel);
		
		JLabel label_5 = new JLabel("    \u586B\u5199\u9884\u7EA6\u4FE1\u606F");
		label_5.setBounds(31, 10, 159, 25);
		label_5.setFont(new Font("宋体", Font.PLAIN, 15));
		p1.add(label_5);
		
		txtsex = new JTextField();
		txtsex.setBounds(90, 70, 66, 21);
		p1.add(txtsex);
		txtsex.setColumns(10);
		
		txtdesk = new JTextField();
		txtdesk.setBounds(90, 179, 66, 21);
		p1.add(txtdesk);
		txtdesk.setColumns(10);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String cmd=e.getActionCommand();//只有给按钮加监听才会有监听事件
		if(cmd.equals("预约")){
			if(txtname.getText().length()==0
					||txtage.getText().length()==0
					||txtphone.getText().length()==0
					||txtdate.getText().length()==0)
				{
					 JOptionPane.showMessageDialog(null, "信息都不能为空!",
						      "警告", JOptionPane.WARNING_MESSAGE);
				}
				else
				{
					String sql="insert into patient(pname,psex,page,ptel,pdate,pdesk,ispay) values(?,?,?,?,?,?,?)";
					String[] param=new String[]{txtname.getText(),txtsex.getText(),txtage.getText(),txtphone.getText(),txtdate.getText(),txtdesk.getText(),"0"};
					Basedao base=new Basedao();
					base.executeSQL(sql,param);
					
				}
			
			
		}
		if(cmd.equals("取消"))
		{
			txtname.setText(null);
			txtsex.setText(null);//
			txtage.setText(null);
			txtphone.setText(null);
			txtdate.setText(null);
			txtdesk.setText(null);//
		}
	}
}
