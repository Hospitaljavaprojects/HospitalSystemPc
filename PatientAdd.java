package cn.edu.usst.moon;

import java.sql.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.JOptionPane;
import javax.swing.border.*;


public class PatientAdd extends JPanel implements ActionListener{
	
	Connection con;
	  Statement sql;
	  JButton b1,b2;
	  JTextField tf1,tf2,tf3,tf4,tf5,tf6,tf7;
	  Box baseBox,bv1,bv2;
	  PatientAdd(){
		  try{
			    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			    }
			   catch(ClassNotFoundException e){}
			    try{
			     con=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=Hospital","sa","sa");
			     sql=con.createStatement();
			    }
			    catch(SQLException ee){}
			   setLayout(new BorderLayout());
			   JPanel p1=new JPanel();
			   JPanel p2=new JPanel();
			   tf1=new JTextField(16);
			   tf2=new JTextField(16);
			   tf3=new JTextField(16);
			   tf4=new JTextField(16);
			   tf5=new JTextField(16);
			   tf6=new JTextField(16);
			   tf7=new JTextField(16);
			   b1=new JButton("录入");  
			   b2=new JButton("重置");
			   b1.addActionListener(this);
			   b2.addActionListener(this);
			   p1.add(b1);
			   p1.add(b2);
			   bv1=Box.createVerticalBox();
			   bv1.add(new JLabel("用户名"));
			   bv1.add(Box.createVerticalStrut(8));
			   bv1.add(new JLabel("姓名"));
			   bv1.add(Box.createVerticalStrut(8));
			   bv1.add(new JLabel("性别"));
			   bv1.add(Box.createVerticalStrut(8));
			   bv1.add(new JLabel("地址"));
			   bv1.add(Box.createVerticalStrut(8));
			   bv1.add(new JLabel("电话"));
			   bv1.add(Box.createVerticalStrut(8));
			   bv1.add(new JLabel("科室"));
			   bv1.add(Box.createVerticalStrut(8));
			   bv1.add(new JLabel("医生"));
			   bv1.add(Box.createVerticalStrut(8));
			   bv2=Box.createVerticalBox();
			   bv2.add(tf1);
			   bv2.add(Box.createVerticalStrut(8));
			   bv2.add(tf2);
			   bv2.add(Box.createVerticalStrut(8));
			   bv2.add(tf3);
			   bv2.add(Box.createVerticalStrut(8));
			   bv2.add(tf4);
			   bv2.add(Box.createVerticalStrut(8));
			   bv2.add(tf5);
			   bv2.add(Box.createVerticalStrut(8));
			   bv2.add(tf6);
			   bv2.add(Box.createVerticalStrut(8));
			   bv2.add(tf7);
			   bv2.add(Box.createVerticalStrut(8));
			   baseBox=Box.createHorizontalBox();
			   baseBox.add(bv1);
			   baseBox.add(Box.createHorizontalStrut(10));
			   baseBox.add(bv2);
			   p2.add(baseBox);
			   add(p1,"South");
			   add(p2,"Center");
			   setSize(350,300);
			   setBackground(Color.pink);
	  }
	  public void actionPerformed(ActionEvent e){
		   if(e.getSource()==b1){
		    try{ insert();}
		    catch(SQLException ee){}
		    JOptionPane.showMessageDialog(this,"数据已入库!","提示对话框",JOptionPane.INFORMATION_MESSAGE);
		   }
		   else if(e.getSource()==b2){
		       tf1.setText(" ");
		       tf2.setText(" ");
		       tf3.setText(" ");
		       tf4.setText(" ");
		       tf5.setText(" ");
		       tf6.setText(" ");
		       tf7.setText(" ");
		   }
		  }
		  public void insert() throws SQLException{
		   String s1="'"+tf1.getText().trim()+"'";
		   String s2="'"+tf2.getText().trim()+"'";
		   String s3="'"+tf3.getText().trim()+"'";
		   String s4="'"+tf4.getText().trim()+"'";
		   String s5="'"+tf5.getText().trim()+"'";
		   String s6="'"+tf6.getText().trim()+"'";
		   String s7="'"+tf7.getText().trim()+"'";
		   String temp="INSERT INTO patient VALUES ("+s1+","+s2+","+s3+","+s4+","+s5+","+s6+","+s7+")";
		    con=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=Hospital","sa","sa");
		    sql.executeQuery(temp);
		   con.close();
		  }

}
