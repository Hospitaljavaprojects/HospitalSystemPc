import java.sql.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.JOptionPane;
/*增加帐号基本信息*/
class add extends Panel implements ActionListener {
	Connection con;
	Statement sql;
	Panel pNorth, pCenter;
	JButton done, readd;
	Box box, box0, box1, box2, box3, box4;
	JTextField id, password, root;
	public add() {
		setLayout(new BorderLayout());
		Font font = new Font("华文中宋", Font.BOLD, 22);
		UIManager.put("Label.font", font);
		id = new JTextField(20);
		password = new JTextField(20);
		root = new JTextField(20);
		box0 = Box.createHorizontalBox();
		JLabel jl = new JLabel("            录入帐号基本信息            ");
		jl.setForeground(Color.red);
		jl.setFont(new Font("华文行楷", Font.BOLD, 34));
		box0.add(jl);
		box1 = Box.createHorizontalBox();
		box1.add(new JLabel("编号:"));
		box1.add(Box.createHorizontalStrut(8));
		box1.add(id);
		box2 = Box.createHorizontalBox();
		box2.add(new JLabel("密码:"));
		box2.add(Box.createHorizontalStrut(8));
		box2.add(password);
		box3 = Box.createHorizontalBox();
		box3.add(new JLabel("身份:"));
		box3.add(Box.createHorizontalStrut(8));
		box3.add(root);
		box4 = Box.createHorizontalBox();
		done = new JButton("录入");
		done.setFont(new Font("华文行楷", Font.BOLD, 22));
		done.setForeground(Color.black);
		done.setBackground(Color.white);
		done.addActionListener(this);
		box4.add(done);
		box4.add(Box.createHorizontalStrut(8));
		readd = new JButton("重置");
		readd.setFont(new Font("华文行楷", Font.BOLD, 22));
		readd.setForeground(Color.black);
		readd.setBackground(Color.white);
		readd.addActionListener(this);
		box4.add(readd);
		box = Box.createVerticalBox();
		box.add(box0);
		box.add(Box.createVerticalStrut(12));
		box.add(box1);
		box.add(Box.createVerticalStrut(12));
		box.add(box2);
		box.add(Box.createVerticalStrut(12));
		box.add(box3);
		box.add(Box.createVerticalStrut(12));
		box.add(box4);
		box.add(Box.createVerticalStrut(12));
		pCenter = new Panel();
		pCenter.add(box);
		add(pCenter, "Center");
		setSize(300, 250);
		setBackground(Color.cyan);
	}
	public void actionPerformed(ActionEvent e) {
		String id1,password1,root1;
		int out = 1;
		if (e.getSource() == done) {
			id1 = id.getText();
			if (id1.equals("")) {
				JOptionPane.showMessageDialog(this, "对不起,帐号不能为空!");
			}
			else {
				try {
					Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				} catch (java.lang.ClassNotFoundException ee) {
				}
				try {
					con=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=Hospital","sa","sa");
					sql = con.createStatement();
					ResultSet rs = null;
					rs = sql.executeQuery("select * from userInfo");
					while (rs.next()) {
						id1 = rs.getString("id");
						if (id1.equals(id.getText().trim())) {
							id.setText("");
							JOptionPane.showMessageDialog(this, "对不起,该帐号已存在!", "帐号管理",JOptionPane.ERROR_MESSAGE);
							out = 0;
							break;
						}
					}
					con.close();
					sql.close();
				} catch (SQLException r) {
				}
				try{
					Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				} catch (java.lang.ClassNotFoundException ee) {
				}
				try {
					con=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=Hospital","sa","sa");
					sql = con.createStatement();
					id1 = "'" + id.getText().trim() + "'";
					password1 ="'" + password.getText().trim()+ "'";
					root1 = "'" + root.getText().trim() + "'";
					String temp = "insert into userInfo values (" + id1 + ","+ password1+ "," + root1  + ")";
					sql.executeUpdate(temp);
					sql.close();
					con.close();
				} catch (SQLException err) {
					JOptionPane.showMessageDialog(this, "信息录入错误,请重新录入!", "帐号管理",JOptionPane.ERROR_MESSAGE);
					out = 0;
				}
				if (out == 1) {
					JOptionPane.showMessageDialog(this, "录入成功!", "帐号管理",JOptionPane.INFORMATION_MESSAGE);
					id.setText(" ");
					password.setText(" ");
					root.setText("");
				}
			}
		}
		if (e.getSource() == readd) {
			id.setText(" ");
			password.setText(" ");
			root.setText("");
		}
	}
}
class Query extends Panel implements ActionListener {
	Connection con;
	Statement sql;
	ButtonGroup bg;
	//JRadioButton male, female;
	JTextField id,password,root;
	JButton jb;
	Box box, box0, box1, box2;
	int flag = 0;
	Query() {
		Font font = new Font("华文中宋", Font.BOLD, 22);
		UIManager.put("Label.font", font);
		setLayout(new BorderLayout());
		jb = new JButton("查询");
		jb.setBackground(Color.white);
		jb.setFont(new Font("华文行楷", Font.BOLD, 22));
		jb.setForeground(Color.black);
		jb.addActionListener(this);
		id = new JTextField(26);
		password = new JTextField(22);
		root = new JTextField(22);
		/* 设置以下属性值不可编辑 */
		password.setEditable(false);
		root.setEditable(false);
		Panel p1 = new Panel();
		JLabel jl = new JLabel("   查询帐号基本信息   ");
		jl.setForeground(Color.red);
		jl.setFont(new Font("华文行楷", Font.BOLD, 34));
		p1.add(jl);
		add(p1, "North");
		box0 = Box.createHorizontalBox();
		box0.add(new JLabel("输入要查询的编号:"));
		box0.add(Box.createHorizontalStrut(8));
		box0.add(id);
		box0.add(Box.createHorizontalStrut(8));
		box0.add(jb);
		box1 = Box.createHorizontalBox();
		box1.add(new JLabel("编号:"));
		box1.add(Box.createHorizontalStrut(8));
		box1.add(id);
		box2 = Box.createHorizontalBox();
		box2.add(new JLabel("身份:"));
		box2.add(Box.createHorizontalStrut(8));
		box2.add(root);
		box = Box.createVerticalBox();
		box.add(box0);
		box.add(Box.createVerticalStrut(8));
		box.add(box1);
		box.add(Box.createVerticalStrut(8));
		box.add(box2);
		box.add(Box.createVerticalStrut(8));
		Panel pCenter = new Panel();
		pCenter.add(box);
		add(pCenter, "Center");
		setSize(300, 250);
		setBackground(Color.cyan);
	}
	public void actionPerformed(ActionEvent e) {
		int flag = 0;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException ee) {
		}
		try {
			con=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=Hospital","sa","sa");
			sql = con.createStatement();
			String id1,password1,root1;
			ResultSet rs = sql.executeQuery("select * from userInfo");
			while (rs.next()) {
				id1 = rs.getString("id");
				password1 = rs.getString("password");
				root1 = rs.getString("root");
				if (id1.equals(id.getText().trim())) {
					password.setText(password1);
					root.setText(root1);
					flag = 1;
					break;
				}
			}
			sql.close();
			con.close();
		} catch (SQLException ex) {
		}
		if (flag == 0) {
			JOptionPane.showMessageDialog(null, "没有该帐号相关信息!请重新输入！", "账号管理", JOptionPane.ERROR_MESSAGE);
			id.setText("");
			password.setText(" ");
			root.setText("");
		}
	}
}
class Update extends Panel implements ActionListener {
	Connection con;
	Statement sql;
	JButton b1, b2, b3;
	Box box, box0, box1, box2, box3;
	JTextField id,password,root;
	Update() {
		setLayout(new BorderLayout());
		Font font = new Font("华文中宋", Font.BOLD, 22);
		UIManager.put("Label.font", font);
		b1 = new JButton("修改");
		b1.setFont(new Font("华文行楷", Font.BOLD, 22));
		b1.setBackground(Color.white);
		b1.setForeground(Color.black);
		b2 = new JButton("录入修改");
		b2.setFont(new Font("华文行楷", Font.BOLD, 22));
		b2.setBackground(Color.white);
		b2.setForeground(Color.black);
		b3 = new JButton("重置");
		b3.setFont(new Font("华文行楷", Font.BOLD, 22));
		b3.setForeground(Color.black);
		b3.setBackground(Color.white);
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		id = new JTextField(18);
		password = new JTextField(20);
		root = new JTextField(20);
		Panel pNorth = new Panel();
		JLabel jl = new JLabel("  修改帐号基本信息  ");
		jl.setForeground(Color.red);
		jl.setFont(new Font("华文行楷", Font.BOLD, 34));
		pNorth.add(jl);
		add(pNorth, "North");
		box0 = Box.createHorizontalBox();
		box0.add(new JLabel("输入要修改信息的帐号:"));
		box0.add(Box.createHorizontalStrut(8));
		box0.add(id);
		box0.add(Box.createHorizontalStrut(8));
		box0.add(b1);
		box1 = Box.createHorizontalBox();
		box1.add(new JLabel("(新)密码:"));
		box1.add(Box.createHorizontalStrut(8));
		box1.add(password);
		box2 = Box.createHorizontalBox();
		box2.add(new JLabel("(新)身份:"));
		box2.add(Box.createHorizontalStrut(8));
		box2.add(root);
		box3= Box.createHorizontalBox();
		box3.add(b2);
		box3.add(Box.createHorizontalStrut(8));
		box3.add(b3);
		box = Box.createVerticalBox();
		box.add(box0);
		box.add(Box.createVerticalStrut(12));
		box.add(box1);
		box.add(Box.createVerticalStrut(12));
		box.add(box2);
		box.add(Box.createVerticalStrut(12));
		box.add(box3);
		box.add(Box.createVerticalStrut(12));
		Panel pCenter = new Panel();
		pCenter.add(box);
		add(pCenter, "Center");
		setSize(300, 250);
		setBackground(Color.cyan);
	}
	public void actionPerformed(ActionEvent e) {
		String id1,password1,root1;
		int flag = 0;
		if (e.getSource() == b1) {
			try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			} catch (ClassNotFoundException ce) {
				System.out.println("SQLException:" + ce.getMessage());
			}
			try {
				con=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=Hospital","sa","sa");
				sql = con.createStatement();
				ResultSet rs = sql.executeQuery("select * from userInfo ");
				while (rs.next()) {
					id1 = rs.getString("id");
					password1 = rs.getString("password");
					root1 = rs.getString("root");
					if (id1.equals(id.getText().trim())) {
						password.setText(password1);
						root.setText(root1);
						flag = 1;
						break;
					}
				}
				sql.close();
				rs.close();
				con.close();
			} catch (SQLException ee) {
			}
			if (flag == 0) {
				JOptionPane.showMessageDialog(null, "没有该帐号相关信息!请重新输入！","账号管理", JOptionPane.ERROR_MESSAGE);
				id.setText("");
				password.setText(" ");
				root.setText("");
			}
		}
		if (e.getSource() == b2) {
			id1 = "'" +id.getText().trim()+"'";
			password1 = "'" + password.getText().trim() + "'";
			root1 = "'" + root.getText().trim() + "'";
			try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			} catch (ClassNotFoundException ee) {
			}
			try {
				con=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=Hospital","sa","sa");
				sql = con.createStatement();
				String temp = "update userInfo set id= "+ id1 +","+ "password=" + password1 + "," + "root=" + root1  + "'";
				System.out.println(temp);
				sql.executeUpdate(temp);
				sql.close();
				con.close();
				JOptionPane.showMessageDialog(this, "修改成功!", "账号管理", JOptionPane.INFORMATION_MESSAGE);
			} catch (SQLException ex) {
				System.out.println(ex);
			}
		}
		if (e.getSource() == b3) {
			id.setText("");
			password.setText(" ");
			root.setText("");
		}
	}
}
class Delete extends Panel implements ActionListener {
	Connection con;
	Statement sql;
	JTextField id,password,root;
	JButton b, b1;
	Box box, box0, box1, box2,box3;
	ButtonGroup bg;
	Delete() {
		setLayout(new BorderLayout());
		Font font = new Font("华文中宋", Font.BOLD, 22);
		UIManager.put("Label.font", font);
		b1 = new JButton("确定删除");
		b1.setForeground(Color.black);
		b1.setFont(new Font("华文行楷", Font.BOLD, 22));
		b1.setBackground(Color.white);
		b1.addActionListener(this);
		b = new JButton("确定");
		b.setForeground(Color.black);
		b.setFont(new Font("华文行楷", Font.BOLD, 22));
		b.setBackground(Color.white);
		b.addActionListener(this);
		id = new JTextField(24);
		password = new JTextField(20);
		root= new JTextField(20);
		password.setEditable(false);
		root.setEditable(false);
		Panel pNorth = new Panel();
		JLabel jl = new JLabel("  删除帐号基本信息  ");
		jl.setForeground(Color.red);
		jl.setFont(new Font("华文行楷", Font.BOLD, 34));
		pNorth.add(jl);
		add(pNorth, "North");
		box0 = Box.createHorizontalBox();
		box0.add(new JLabel("输入要删除的编号:"));
		box0.add(Box.createHorizontalStrut(8));
		box0.add(id);
		box0.add(Box.createHorizontalStrut(8));
		box0.add(b);
		box1 = Box.createHorizontalBox();
		box1.add(new JLabel("密码:"));
		box1.add(Box.createHorizontalStrut(8));
		box1.add(password);
		box2 = Box.createHorizontalBox();
		box2.add(new JLabel("身份:"));
		box2.add(Box.createHorizontalStrut(8));
		box2.add(root);
		box3 = Box.createHorizontalBox();
		box3.add(b1);
		box = Box.createVerticalBox();
		box.add(box0);
		box.add(Box.createVerticalStrut(12));
		box.add(box1);
		box.add(Box.createVerticalStrut(12));
		box.add(box2);
		box.add(Box.createVerticalStrut(12));
		box.add(box3);
		Panel pCenter = new Panel();
		pCenter.add(box);
		add(pCenter, "Center");
		setSize(300, 250);
		setBackground(Color.cyan);
	}
	public void actionPerformed(ActionEvent e) {
		String id1,password1,root1;
		int flag = 0;
		if (e.getSource() == b) {
			try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			} catch (ClassNotFoundException ee) {
				System.out.println("SQLException:" + ee.getMessage());
			}
			try {
				con=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=Hospital","sa","sa");
				sql = con.createStatement();
				ResultSet rs = sql.executeQuery("select * from userInfo ");
				while (rs.next()) {
					id1 = rs.getString("id");
					password1 = rs.getString("password");
					root1 = rs.getString("root");
					if (id1.equals(id.getText().trim())) {
						password.setText(password1);
						root.setText(root1);
						flag = 1;
						break;
					}
				}
				sql.close();
				con.close();
			} catch (SQLException ex) {
				System.out.println("SQLException:" + ex.getMessage());
			}
			if (flag == 0) {
				JOptionPane.showMessageDialog(null, "这个帐号不存在,请重新输入!", "错误!", JOptionPane.YES_NO_OPTION);
				id.setText("");
				password.setText(" ");
				root.setText("");
			}
		}
		if (e.getSource() == b1) {
			int n = JOptionPane.showConfirmDialog(this, "确定要删除该帐号及全部信息吗?", "账号管理", JOptionPane.YES_NO_OPTION);
			if (n == JOptionPane.YES_OPTION) {
				try {
					Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				} catch (ClassNotFoundException ee) {
				}
				try {
					con=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=Hospital","sa","sa");
					sql = con.createStatement();
					id1 = "'" + id.getText().trim() + "'";
					String temp = "delete from userInfo where id=" + id1;
					sql.executeUpdate(temp);
					id.setText("");
					password.setText(" ");
					root.setText("");
					sql.close();
					con.close();
				} catch (SQLException ex) {
				}
			} else if (n == JOptionPane.NO_OPTION) {
			}
		}
	}
}
class adddrug extends Panel implements ActionListener {
	Connection con;
	Statement sql;
	Panel pNorth, pCenter;
	JButton done, readd;
	Box box, box0, box1, box2, box3, box4,box5,box6,box7;
	JTextField drugid, drugname,drugbrief,drugcom,drugprice,drugnum;
	public adddrug() {
		setLayout(new BorderLayout());
		Font font = new Font("华文中宋", Font.BOLD, 22);
		UIManager.put("Label.font", font);
		drugid = new JTextField(20);
		drugname = new JTextField(20);
		drugbrief = new JTextField(20);
		drugcom = new JTextField(20);
		drugprice = new JTextField(20);
		drugnum = new JTextField(20);
		box0 = Box.createHorizontalBox();
		JLabel jl = new JLabel("            录入药品基本信息            ");
		jl.setForeground(Color.red);
		jl.setFont(new Font("华文行楷", Font.BOLD, 34));
		box0.add(jl);
		box1 = Box.createHorizontalBox();
		box1.add(new JLabel("药品编号:"));
		box1.add(Box.createHorizontalStrut(8));
		box1.add(drugid);
		box2 = Box.createHorizontalBox();
		box2.add(new JLabel("药品名称:"));
		box2.add(Box.createHorizontalStrut(8));
		box2.add(drugname);
		box3 = Box.createHorizontalBox();
		box3.add(new JLabel("药品简称:"));
		box3.add(Box.createHorizontalStrut(8));
		box3.add(drugbrief);
		box4 = Box.createHorizontalBox();
		box4.add(new JLabel("药品单位:"));
		box4.add(Box.createHorizontalStrut(8));
		box4.add(drugcom);
		box5 = Box.createHorizontalBox();
		box5.add(new JLabel("药品价格:"));
		box5.add(Box.createHorizontalStrut(8));
		box5.add(drugprice);
		box6 = Box.createHorizontalBox();
		box6.add(new JLabel("药品库存:"));
		box6.add(Box.createHorizontalStrut(8));
		box6.add(drugnum);
		box7= Box.createHorizontalBox();
		done = new JButton("录入");
		done.setFont(new Font("华文行楷", Font.BOLD, 22));
		done.setForeground(Color.black);
		done.setBackground(Color.white);
		done.addActionListener(this);
		box7.add(done);
		box7.add(Box.createHorizontalStrut(8));
		readd = new JButton("重置");
		readd.setFont(new Font("华文行楷", Font.BOLD, 22));
		readd.setForeground(Color.black);
		readd.setBackground(Color.white);
		readd.addActionListener(this);
		box7.add(readd);
		box = Box.createVerticalBox();
		box.add(box0);
		box.add(Box.createVerticalStrut(12));
		box.add(box1);
		box.add(Box.createVerticalStrut(12));
		box.add(box2);
		box.add(Box.createVerticalStrut(12));
		box.add(box3);
		box.add(Box.createVerticalStrut(12));
		box.add(box4);
		box.add(Box.createVerticalStrut(12));
		box.add(box5);
		box.add(Box.createVerticalStrut(12));
		box.add(box6);
		box.add(Box.createVerticalStrut(12));
		box.add(box7);
		pCenter = new Panel();
		pCenter.add(box);
		add(pCenter, "Center");
		setBackground(Color.cyan);
	}
	public void actionPerformed(ActionEvent e) {
		String drugid1, drugname1,drugbrief1,drugcom1,drugprice1,drugnum1;
		int out = 1;
		if (e.getSource() == done) {
			drugid1 = drugid.getText();
			if (drugid1.equals("")) {
				JOptionPane.showMessageDialog(this, "对不起,药品编号不能为空!");
			}
			else {
				try {
					Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				} catch (java.lang.ClassNotFoundException ee) {
				}
				try {
					con=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=Hospital","sa","sa");
					sql = con.createStatement();
					ResultSet rs = null;
					rs = sql.executeQuery("select * from userInfo");
					while (rs.next()) {
						drugid1 = rs.getString("id");
						if (drugid1.equals(drugid.getText().trim())) {
							drugid.setText("");
							JOptionPane.showMessageDialog(this, "对不起,该药品已存在!", "帐号管理",JOptionPane.ERROR_MESSAGE);
							out = 0;
							break;
						}
					}
					con.close();
					sql.close();
				} catch (SQLException r) {
				}
				try{
					Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				} catch (java.lang.ClassNotFoundException ee) {
				}
				try {
					con=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=Hospital","sa","sa");
					sql = con.createStatement();
					drugid1 = "'" + drugid.getText().trim() + "'";
					drugname1 ="'" + drugname.getText().trim()+ "'";
					drugbrief1 = "'" + drugbrief.getText().trim() + "'";
					drugcom1 = "'" + drugcom.getText().trim() + "'";
					drugprice1 = "'" + drugprice.getText().trim() + "'";
					drugnum1 = "'" + drugnum.getText().trim() + "'";
					String temp = "insert into drug values (" + drugid1 + ","+ drugname1 + "," + drugbrief1 +"," + drugcom1 +"," + drugprice1 +"," + drugnum1 + ")";
					sql.executeUpdate(temp);
					sql.close();
					con.close();
				} catch (SQLException err) {
					JOptionPane.showMessageDialog(this, "信息录入错误,请重新录入!", "药品管理",JOptionPane.ERROR_MESSAGE);
					out = 0;
				}
				if (out == 1) {
					JOptionPane.showMessageDialog(this, "录入成功!", "药品管理",JOptionPane.INFORMATION_MESSAGE);
					drugid.setText(" ");
					drugname.setText(" ");
					drugbrief.setText("");
					drugcom.setText("");
					drugprice.setText("");
					drugnum.setText("");
				}
			}
		}
		if (e.getSource() == readd) {
			drugid.setText(" ");
			drugname.setText(" ");
			drugbrief.setText("");
			drugcom.setText("");
			drugprice.setText("");
			drugnum.setText("");
		}
	}
}
class exit extends JDialog implements ActionListener {
	JFrame mainFrame;
	JPanel panelS, panelN;
	JButton yes, no;
	JLabel lab;
	public exit(JFrame mainFrame) {
		// true 代表为有模式对话框
		this.mainFrame = mainFrame;
		panelN = new JPanel();
		panelS = new JPanel();
		lab = new JLabel("确认要退出帐号管理界面吗?");
		lab.setForeground(Color.RED);
		lab.setFont(new Font("华文行楷", Font.PLAIN, 22));
		panelN.add(lab);
		yes = new JButton("退出");
		yes.setForeground(Color.RED);
		yes.setFont(new Font("华文行楷", Font.PLAIN, 20));
		yes.addActionListener(this);
		no = new JButton("取消");
		no.setFont(new Font("华文行楷", Font.PLAIN, 20));
		no.setForeground(Color.RED);
		no.addActionListener(this);
		panelS.add(yes);
		panelS.add(no);
		getContentPane().add(panelS, BorderLayout.SOUTH);
		getContentPane().add(panelN, BorderLayout.NORTH);
		this.setBackground(Color.BLUE);
		this.setResizable(false); // 设置对话框为不可改变大小
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == yes) {
			System.exit(0);
		}
		if (e.getSource() == no) {
			this.setVisible(false);
		}
	}
}

class fram extends Frame implements ActionListener {
	MenuBar menubar;
	Menu menu_select;
	MenuItem item_userInfo_add, item_userInfo_search, item_userInfo_update,
			item_userInfo_delete,item_userInfo_adddrug, item_exit;
	add luru;
	Query chaxun;
	Update xiugai;
	Delete shanchu;
	adddrug jiayao;
	Box box, box1, box2;
	fram() {
		luru = new add();
		chaxun = new Query();
		xiugai = new Update();
		shanchu = new Delete();
		jiayao = new adddrug();
		menubar = new MenuBar();
		menu_select = new Menu("菜单选项");
		menu_select.setFont(new Font("华文行楷", Font.BOLD, 18));
		item_userInfo_add = new MenuItem("录入帐号基本信息");
		item_userInfo_add.addActionListener(this);
		item_userInfo_update = new MenuItem("修改帐号基本信息");
		item_userInfo_update.addActionListener(this);
		item_userInfo_search = new MenuItem("查询帐号基本信息");
		item_userInfo_search.addActionListener(this);
		item_userInfo_delete = new MenuItem("删除帐号基本信息");
		item_userInfo_delete.addActionListener(this);
		item_userInfo_adddrug = new MenuItem("录入药品基本信息");
		item_userInfo_adddrug.addActionListener(this);
		item_exit = new MenuItem("退出");
		item_exit.addActionListener(this);
		menu_select.add(item_userInfo_search);
		menu_select.add(item_userInfo_add);
		menu_select.add(item_userInfo_update);
		menu_select.add(item_userInfo_delete);
		menu_select.add(item_userInfo_adddrug);
		menu_select.add(item_exit);
		menubar.add(menu_select);
		setMenuBar(menubar);
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == item_userInfo_add) {
			removeAll();
			add(luru, "Center");
			validate();
		}
		if (e.getSource() == item_userInfo_search) {
			removeAll();
			add(chaxun, "Center");
			validate();
		}
		if (e.getSource() == item_userInfo_update) {
			removeAll();
			add(xiugai, "Center");
			validate();
		}
		if (e.getSource() == item_userInfo_delete) {
			removeAll();
			add(shanchu, "Center");
			validate();
		}
		if (e.getSource() == item_userInfo_adddrug) {
			removeAll();
			add(jiayao, "Center");
			validate();
		}
		if (e.getSource() == item_exit) {
			JFrame f4 = new JFrame();
			exit exit1 = new exit(f4);
			exit1.setBounds(500, 240, 300, 120);
			exit1.setVisible(true);
		}
	}
}
public class Administor {
	public static void main(String[] args) {
		Connection con=null;
		try {
			con=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=Hospital","sa","sa");
			Statement stmt = con.createStatement();
			stmt.close();
			con.close();
		} catch (SQLException exe) {
		}
	    Frame fra = new fram();
		fra.setBounds(300, 150, 800, 400);
		fra.setVisible(true);
		// 窗口事件处理 
		fra.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
}

