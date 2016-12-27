package cn.edu.usst.moon;


import java.awt.*;

import java.awt.event.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class PatientManagement extends JFrame implements ActionListener{
	JMenuBar bar=null;
	JMenu menu1,menu2,menu3,menu4,menu5;
	JMenuItem item1,item2,item3,item4,item5;
	PatientAdd zengjia;
	PatientQuery chaxun;
	PatientUpdate gengxin;
	PatientDelete shanchu;
	
	PatientManagement(){
		super("医院挂号管理系统");
		zengjia=new PatientAdd();
		chaxun=new PatientQuery();
		gengxin=new PatientUpdate();
		shanchu=new PatientDelete();
		bar=new JMenuBar();
		menu1=new JMenu("录入病人信息");
		menu2=new JMenu("查询病人信息");
		menu3=new JMenu("更新病人信息");
		menu4=new JMenu("删除病人信息");
		menu5=new JMenu("退出系统");
		item1=new JMenuItem("录入");
		item2=new JMenuItem("查询");
		item3=new JMenuItem("更新");
		item4=new JMenuItem("删除");
		item5=new JMenuItem("退出");
		menu1.add(item1);
		menu2.add(item2);
		menu3.add(item3);
		menu4.add(item4);
		menu5.add(item5);
		bar.add(menu1);
		bar.add(menu2);
		bar.add(menu3);
		bar.add(menu4);
		bar.add(menu5);
		setJMenuBar(bar);
		item1.addActionListener(this);
		item2.addActionListener(this);
		item3.addActionListener(this);
		item4.addActionListener(this);
		item5.addActionListener(this);
		JLabel label=new JLabel("医院挂号管理系统",JLabel.CENTER);
		String s=" ";
		Font f=new Font(s,Font.BOLD,22);
		label.setFont(f);
		label.setBackground(Color.green);
		label.setForeground(Color.BLUE);
		add(label,"Center");
		setVisible(true);
		setSize(350,300);
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==item1){
			this.getContentPane().removeAll();
			this.getContentPane().add(zengjia,"Center");
			this.getContentPane().repaint();
			this.getContentPane().validate();
		}
		if(e.getSource()==item2){
			this.getContentPane().removeAll();
			this.getContentPane().add(chaxun,"Center");
			this.getContentPane().repaint();
			this.getContentPane().validate();
		}
		if(e.getSource()==item3){
			this.getContentPane().removeAll();
			this.getContentPane().add(gengxin,"Center");
			this.getContentPane().repaint();
			this.getContentPane().validate();
		}
		if(e.getSource()==item4){
			this.getContentPane().removeAll();
			this.getContentPane().add(shanchu,"Center");
			this.getContentPane().repaint();
			this.getContentPane().validate();
		}
		if(e.getSource()==item5){
			System.exit(0);
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PatientManagement paM=new PatientManagement();
		paM.setVisible(true);
		
		paM.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
		 

	}

}
