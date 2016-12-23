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
	Box box, box0, box1, box2, box3;
	JTextField id, root;
	public add() {
		setLayout(new BorderLayout());
		Font font = new Font("华文中宋", Font.BOLD, 22);
		UIManager.put("Label.font", font);
		id = new JTextField(20);
		root = new JTextField(20);
		box0 = Box.createHorizontalBox();
		JLabel jl = new JLabel("            录入帐号信息            ");
		jl.setForeground(Color.red);
		jl.setFont(new Font("华文行楷", Font.BOLD, 34));
		box0.add(jl);
		box1 = Box.createHorizontalBox();
		box1.add(new JLabel("编号:"));
		box1.add(Box.createHorizontalStrut(8));
		box1.add(id);
		box2 = Box.createHorizontalBox();
		box2.add(new JLabel("身份:"));
		box2.add(Box.createHorizontalStrut(8));
		box2.add(root);
		box3 = Box.createHorizontalBox();
		done = new JButton("录入");
		done.setFont(new Font("华文行楷", Font.BOLD, 22));
		done.setForeground(Color.black);
		done.setBackground(Color.white);
		done.addActionListener(this);
		box3.add(done);
		box3.add(Box.createHorizontalStrut(8));
		readd = new JButton("重置");
		readd.setFont(new Font("华文行楷", Font.BOLD, 22));
		readd.setForeground(Color.black);
		readd.setBackground(Color.white);
		readd.addActionListener(this);
		box3.add(readd);
		box = Box.createVerticalBox();
		box.add(box0);
		box.add(Box.createVerticalStrut(12));
		box.add(box1);
		box.add(Box.createVerticalStrut(12));
		box.add(box2);
		box.add(Box.createVerticalStrut(12));
		box.add(box3);
		box.add(Box.createVerticalStrut(12));
		pCenter = new Panel();
		pCenter.add(box);
		add(pCenter, "Center");
		setSize(300, 250);
		setBackground(new Color(102,204,255));
	}
	public void actionPerformed(ActionEvent e) {
		String id1,root1;
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
					rs = sql.executeQuery("select id,root from userInfo");
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
					root1 = "'" + root.getText().trim() + "'";
					String temp = "insert into userInfo values (" + id1 + ","+ 0000+ "," + root1  + ")";
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
					root.setText(" ");
				}
			}
		}
		if (e.getSource() == readd) {
			id.setText(" ");
			root.setText("");
		}
	}
}
class Query extends Panel implements ActionListener {
	Connection con;
	Statement sql;
	ButtonGroup bg;
	JTextField id,root;
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
		root = new JTextField(22);
		/* 设置以下属性值不可编辑 */
		root.setEditable(false);
		Panel p1 = new Panel();
		JLabel jl = new JLabel("   查询帐号信息   ");
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
		setBackground(new Color(102,204,255));
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
			String id1,root1;
			ResultSet rs = sql.executeQuery("select id,root from userInfo");
			while (rs.next()) {
				id1 = rs.getString("id");
				root1 = rs.getString("root");
				if (id1.equals(id.getText().trim())) {
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
		JLabel jl = new JLabel("  修改帐号信息  ");
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
		box1.add(new JLabel("(新)身份:"));
		box1.add(Box.createHorizontalStrut(8));
		box1.add(root);
		box2= Box.createHorizontalBox();
		box2.add(b2);
		box2.add(Box.createHorizontalStrut(8));
		box2.add(b3);
		box = Box.createVerticalBox();
		box.add(box0);
		box.add(Box.createVerticalStrut(12));
		box.add(box1);
		box.add(Box.createVerticalStrut(12));
		box.add(box2);
		box.add(Box.createVerticalStrut(12));
		Panel pCenter = new Panel();
		pCenter.add(box);
		add(pCenter, "Center");
		setSize(300, 250);
		setBackground(new Color(102,204,255));
	}
	public void actionPerformed(ActionEvent e) {
		String id1,root1;
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
				ResultSet rs = sql.executeQuery("select id,root from userInfo ");
				while (rs.next()) {
					id1 = rs.getString("id");
					root1 = rs.getString("root");
					if (id1.equals(id.getText().trim())) {
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
				root.setText("");
			}
		}
		if (e.getSource() == b2) {
			id1 = "'" +id.getText().trim()+"'";
			root1 = "'" + root.getText().trim() + "'";
			try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			} catch (ClassNotFoundException ee) {
			}
			try {
				con=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=Hospital","sa","sa");
				sql = con.createStatement();
				String temp = "update userInfo set root=" + root1 +"where id="+id1;
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
			root.setText("");
		}
	}
}
class Delete extends Panel implements ActionListener {
	Connection con;
	Statement sql;
	JTextField id,password,root;
	JButton b, b1;
	Box box, box0, box1, box2;
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
		JLabel jl = new JLabel("  删除帐号信息  ");
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
		box1.add(new JLabel("身份:"));
		box1.add(Box.createHorizontalStrut(8));
		box1.add(root);
		box2 = Box.createHorizontalBox();
		box2.add(b1);
		box = Box.createVerticalBox();
		box.add(box0);
		box.add(Box.createVerticalStrut(12));
		box.add(box1);
		box.add(Box.createVerticalStrut(12));
		box.add(box2);
		Panel pCenter = new Panel();
		pCenter.add(box);
		add(pCenter, "Center");
		setSize(300, 250);
		setBackground(new Color(102,204,255));
	}
	public void actionPerformed(ActionEvent e) {
		String id1,root1;
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
				ResultSet rs = sql.executeQuery("select id,root from userInfo ");
				while (rs.next()) {
					id1 = rs.getString("id");
					root1 = rs.getString("root");
					if (id1.equals(id.getText().trim())) {
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
				root.setText("");
			}
		}
		if (e.getSource() == b1) {
			int n = JOptionPane.showConfirmDialog(this, "确定要删除该帐号的全部信息吗?", "账号管理", JOptionPane.YES_NO_OPTION);
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
					id.setText(" ");
					root.setText(" ");
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
		JLabel jl = new JLabel("            录入药品信息            ");
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
		setBackground(new Color(102,204,255));
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
					rs = sql.executeQuery("select * from drug");
					while (rs.next()) {
						drugid1 = rs.getString("drugid");
						if (drugid1.equals(drugid.getText().trim())) {
							drugid.setText("");
							drugname.setText("");
							JOptionPane.showMessageDialog(this, "对不起,该药品已存在!", "药品管理",JOptionPane.ERROR_MESSAGE);
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
class Updatedrug extends Panel implements ActionListener {
	Connection con;
	Statement sql;
	JButton b1, b2, b3;
	Box box, box0, box1, box2, box3,box4,box5,box6;
	JTextField drugid, drugname,drugbrief,drugcom,drugprice,drugnum;
	Updatedrug() {
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
		drugid = new JTextField(18);
		drugname = new JTextField(20);
		drugbrief= new JTextField(20);
		drugcom = new JTextField(20);
		drugprice = new JTextField(20);
		drugnum = new JTextField(20);
		Panel pNorth = new Panel();
		JLabel jl = new JLabel("  修改药品信息  ");
		jl.setForeground(Color.red);
		jl.setFont(new Font("华文行楷", Font.BOLD, 34));
		pNorth.add(jl);
		add(pNorth, "North");
		box0 = Box.createHorizontalBox();
		box0.add(new JLabel("输入要修改的药品编号:"));
		box0.add(Box.createHorizontalStrut(8));
		box0.add(drugid);
		box0.add(Box.createHorizontalStrut(8));
		box0.add(b1);
		box1 = Box.createHorizontalBox();
		box1.add(new JLabel("(新)药品名称:"));
		box1.add(Box.createHorizontalStrut(8));
		box1.add(drugname);
		box2 = Box.createHorizontalBox();
		box2.add(new JLabel("(新)药品简称:"));
		box2.add(Box.createHorizontalStrut(8));
		box2.add(drugbrief);
		box3 = Box.createHorizontalBox();
		box3.add(new JLabel("(新)药品单位:"));
		box3.add(Box.createHorizontalStrut(8));
		box3.add(drugcom);
		box4 = Box.createHorizontalBox();
		box4.add(new JLabel("(新)药品价格:"));
		box4.add(Box.createHorizontalStrut(8));
		box4.add(drugprice);
		box5 = Box.createHorizontalBox();
		box5.add(new JLabel("(新)药品库存:"));
		box5.add(Box.createHorizontalStrut(8));
		box5.add(drugnum);
		box6= Box.createHorizontalBox();
		box6.add(b2);
		box6.add(Box.createHorizontalStrut(8));
		box6.add(b3);
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
		Panel pCenter = new Panel();
		pCenter.add(box);
		add(pCenter, "Center");
		setSize(300, 250);
		setBackground(new Color(102,204,255));
	}
	public void actionPerformed(ActionEvent e) {
		String drugid1, drugname1,drugbrief1,drugcom1,drugprice1,drugnum1;
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
				ResultSet rs = sql.executeQuery("select * from drug ");
				while (rs.next()) {
					drugid1 = rs.getString("drugid");
					drugname1 = rs.getString("drugname");
					drugbrief1 = rs.getString("drugbrief");
					drugcom1 = rs.getString("drugcom");
					drugprice1 = rs.getString("drugprice");
					drugnum1 = rs.getString("drugnum");
					if (drugid1.equals(drugid.getText().trim())) {
						drugname.setText(drugname1);
						drugbrief.setText(drugbrief1);
						drugcom.setText(drugcom1);
						drugprice.setText(drugprice1);
						drugnum.setText(drugnum1);
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
				JOptionPane.showMessageDialog(null, "没有该相药品的相关信息!请重新输入！","药品管理", JOptionPane.ERROR_MESSAGE);
				drugid.setText("");
				drugname.setText(" ");
				drugbrief.setText(" ");
				drugcom.setText(" ");
				drugprice.setText(" ");
				drugnum.setText(" ");
			}
		}
		if (e.getSource() == b2) {
			drugid1 = "'" +drugid.getText().trim()+"'";
			drugname1 = "'" + drugname.getText().trim() + "'";
			drugbrief1 = "'" + drugbrief.getText().trim() + "'";
			drugcom1= "'" + drugcom.getText().trim() + "'";
			drugprice1= "'" + drugprice.getText().trim() + "'";
			drugnum1= "'" + drugnum.getText().trim() + "'";
			try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			} catch (ClassNotFoundException ee) {
			}
			try {
				con=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=Hospital","sa","sa");
				sql = con.createStatement();
				String temp = "update drug set drugname=" + drugname1 + "," +"drugbrief=" +drugbrief1+","+"drugcom=" +drugcom1+","+"drugprice="+drugprice1+","+"drugnum=" +drugnum1+"where drugid="+drugid1;
				sql.executeUpdate(temp);
				sql.close();
				con.close();
				JOptionPane.showMessageDialog(this, "修改成功!", "药品管理", JOptionPane.INFORMATION_MESSAGE);
			} catch (SQLException ex) {
				System.out.println(ex);
			}
		}
		if (e.getSource() == b3) {
			drugid.setText("");
			drugname.setText(" ");
			drugbrief.setText(" ");
			drugcom.setText(" ");
			drugprice.setText(" ");
			drugnum.setText(" ");
		}
	}
}
class Querydrug extends Panel implements ActionListener {
	Connection con;
	Statement sql;
	ButtonGroup bg;
	JTextField drugid,drugname,drugbrief,drugcom,drugprice,drugnum;
	JButton jb;
	Box box, box0, box1, box2,box3,box4,box5,box6;
	int flag = 0;
	Querydrug() {
		Font font = new Font("华文中宋", Font.BOLD, 22);
		UIManager.put("Label.font", font);
		setLayout(new BorderLayout());
		jb = new JButton("查询");
		jb.setBackground(Color.white);
		jb.setFont(new Font("华文行楷", Font.BOLD, 22));
		jb.setForeground(Color.black);
		jb.addActionListener(this);
		drugid = new JTextField(26);
		drugname= new JTextField(22);
		drugbrief = new JTextField(20);
		drugcom = new JTextField(20);
		drugprice = new JTextField(20);
		drugnum = new JTextField(20);
		/* 设置以下属性值不可编辑 */
		drugname.setEditable(false);
		drugbrief.setEditable(false);
		drugcom.setEditable(false);
		drugprice.setEditable(false);
		drugnum.setEditable(false);
		Panel p1 = new Panel();
		JLabel jl = new JLabel("   查询药品信息   ");
		jl.setForeground(Color.red);
		jl.setFont(new Font("华文行楷", Font.BOLD, 34));
		p1.add(jl);
		add(p1, "North");
		box0 = Box.createHorizontalBox();
		box0.add(new JLabel("输入要查询的药品编号:"));
		box0.add(Box.createHorizontalStrut(8));
		box0.add(drugid);
		box0.add(Box.createHorizontalStrut(8));
		box0.add(jb);
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
		box = Box.createVerticalBox();
		box.add(box0);
		box.add(Box.createVerticalStrut(8));
		box.add(box1);
		box.add(Box.createVerticalStrut(8));
		box.add(box2);
		box.add(Box.createVerticalStrut(8));
		box.add(box3);
		box.add(Box.createVerticalStrut(8));
		box.add(box4);
		box.add(Box.createVerticalStrut(8));
		box.add(box5);
		box.add(Box.createVerticalStrut(8));
		box.add(box6);
		box.add(Box.createVerticalStrut(8));
		Panel pCenter = new Panel();
		pCenter.add(box);
		add(pCenter, "Center");
		setSize(300, 250);
		setBackground(new Color(102,204,255));
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
			String drugid1, drugname1,drugbrief1,drugcom1,drugprice1,drugnum1;
			ResultSet rs = sql.executeQuery("select * from drug");
			while (rs.next()) {
				drugid1 = rs.getString("drugid");
				drugname1 = rs.getString("drugname");
				drugbrief1 = rs.getString("drugbrief");
				drugcom1 = rs.getString("drugcom");
				drugprice1 = rs.getString("drugprice");
				drugnum1 = rs.getString("drugnum");
				if (drugid1.equals(drugid.getText().trim())) {
					drugname.setText(drugname1);
					drugbrief.setText(drugbrief1);
					drugcom.setText(drugcom1);
					drugprice.setText(drugprice1);
					drugnum.setText(drugnum1);
					flag = 1;
					break;
				}
			}
			sql.close();
			con.close();
		} catch (SQLException ex) {
		}
		if (flag == 0) {
			JOptionPane.showMessageDialog(null, "没有该药品的相关信息!请重新输入！", "药品管理", JOptionPane.ERROR_MESSAGE);
			drugid.setText("");
			drugname.setText(" ");
			drugbrief.setText(" ");
			drugcom.setText(" ");
			drugprice.setText(" ");
			drugnum.setText(" ");
		}
	}
}
class Deletedrug extends Panel implements ActionListener {
	Connection con;
	Statement sql;
	JTextField drugid, drugname,drugbrief,drugcom,drugprice,drugnum;
	JButton b, b1;
	Box box, box0, box1, box2,box3,box4,box5,box6;
	ButtonGroup bg;
	Deletedrug() {
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
		drugid = new JTextField(24);
		drugname = new JTextField(20);
		drugbrief= new JTextField(20);
		drugcom = new JTextField(20);
		drugprice= new JTextField(20);
		drugnum= new JTextField(20);
		drugname.setEditable(false);
		drugbrief.setEditable(false);
		drugcom.setEditable(false);
		drugprice.setEditable(false);
		drugnum.setEditable(false);
		Panel pNorth = new Panel();
		JLabel jl = new JLabel("  删除药品信息  ");
		jl.setForeground(Color.red);
		jl.setFont(new Font("华文行楷", Font.BOLD, 34));
		pNorth.add(jl);
		add(pNorth, "North");
		box0 = Box.createHorizontalBox();
		box0.add(new JLabel("输入要删除的药品编号:"));
		box0.add(Box.createHorizontalStrut(8));
		box0.add(drugid);
		box0.add(Box.createHorizontalStrut(8));
		box0.add(b);
		box1 = Box.createHorizontalBox();
		box1.add(new JLabel("药品名称:"));
		box1.add(Box.createHorizontalStrut(8));
		box1.add(drugname);
		box2 = Box.createHorizontalBox();
		box2.add(new JLabel("药品简称:"));
		box2.add(Box.createHorizontalStrut(8));
		box2.add(drugbrief);
		box3 = Box.createHorizontalBox();
		box3.add(new JLabel("药品单位:"));
		box3.add(Box.createHorizontalStrut(8));
		box3.add(drugcom);
		box4 = Box.createHorizontalBox();
		box4.add(new JLabel("药品价格:"));
		box4.add(Box.createHorizontalStrut(8));
		box4.add(drugprice);
		box5 = Box.createHorizontalBox();
		box5.add(new JLabel("药品库存:"));
		box5.add(Box.createHorizontalStrut(8));
		box5.add(drugnum);
		box6 = Box.createHorizontalBox();
		box6.add(b1);
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
		Panel pCenter = new Panel();
		pCenter.add(box);
		add(pCenter, "Center");
		setSize(300, 250);
		setBackground(new Color(102,204,255));
	}
	public void actionPerformed(ActionEvent e) {
		String drugid1, drugname1,drugbrief1,drugcom1,drugprice1,drugnum1;
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
				ResultSet rs = sql.executeQuery("select * from drug ");
				while (rs.next()) {
					drugid1= rs.getString("drugid");
					drugname1 = rs.getString("drugname");
					drugbrief1 = rs.getString("drugbrief");
					drugcom1 = rs.getString("drugcom");
					drugprice1 = rs.getString("drugprice");
					drugnum1 = rs.getString("drugnum");
					if (drugid1.equals(drugid.getText().trim())) {
						drugname.setText(drugname1);
						drugbrief.setText(drugbrief1);
						drugcom.setText(drugcom1);
						drugprice.setText(drugprice1);
						drugnum.setText(drugnum1);
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
				JOptionPane.showMessageDialog(null, "这个药品不存在,请重新输入!", "错误!", JOptionPane.YES_NO_OPTION);
				drugid.setText("");
				drugname.setText(" ");
				drugbrief.setText(" ");
				drugcom.setText(" ");
				drugprice.setText(" ");
				drugnum.setText(" ");
			}
		}
		if (e.getSource() == b1) {
			int n = JOptionPane.showConfirmDialog(this, "确定要删除该药品的全部信息吗?", "药品管理", JOptionPane.YES_NO_OPTION);
			if (n == JOptionPane.YES_OPTION) {
				try {
					Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				} catch (ClassNotFoundException ee) {
				}
				try {
					con=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=Hospital","sa","sa");
					sql = con.createStatement();
					drugid1 = "'" + drugid.getText().trim() + "'";
					String temp = "delete from drug where drugid=" + drugid1;
					sql.executeUpdate(temp);
					drugid.setText("");
					drugname.setText(" ");
					drugbrief.setText(" ");
					drugcom.setText(" ");
					drugprice.setText(" ");
					drugnum.setText(" ");
					sql.close();
					con.close();
				} catch (SQLException ex) {
				}
			} else if (n == JOptionPane.NO_OPTION) {
			}
		}
	}
}
class adddept extends Panel implements ActionListener {
	Connection con;
	Statement sql;
	Panel pNorth, pCenter;
	JButton done, readd;
	Box box, box0, box1, box2, box3;
	JTextField kid, kname;
	public adddept() {
		setLayout(new BorderLayout());
		Font font = new Font("华文中宋", Font.BOLD, 22);
		UIManager.put("Label.font", font);
		kid = new JTextField(20);
		kname = new JTextField(20);	
		box0 = Box.createHorizontalBox();
		JLabel jl = new JLabel("            录入科室信息            ");
		jl.setForeground(Color.red);
		jl.setFont(new Font("华文行楷", Font.BOLD, 34));
		box0.add(jl);
		box1 = Box.createHorizontalBox();
		box1.add(new JLabel("科室编号:"));
		box1.add(Box.createHorizontalStrut(8));
		box1.add(kid);
		box2 = Box.createHorizontalBox();
		box2.add(new JLabel("科室名称:"));
		box2.add(Box.createHorizontalStrut(8));
		box2.add(kname);
		box3 = Box.createHorizontalBox();
		done = new JButton("录入");
		done.setFont(new Font("华文行楷", Font.BOLD, 22));
		done.setForeground(Color.black);
		done.setBackground(Color.white);
		done.addActionListener(this);
		box3.add(done);
		box3.add(Box.createHorizontalStrut(8));
		readd = new JButton("重置");
		readd.setFont(new Font("华文行楷", Font.BOLD, 22));
		readd.setForeground(Color.black);
		readd.setBackground(Color.white);
		readd.addActionListener(this);
		box3.add(readd);
		box = Box.createVerticalBox();
		box.add(box0);
		box.add(Box.createVerticalStrut(12));
		box.add(box1);
		box.add(Box.createVerticalStrut(12));
		box.add(box2);
		box.add(Box.createVerticalStrut(12));
		box.add(box3);
		box.add(Box.createVerticalStrut(12));
		pCenter = new Panel();
		pCenter.add(box);
		add(pCenter, "Center");
		setSize(300, 250);
		setBackground(new Color(102,204,255));
	}
	public void actionPerformed(ActionEvent e) {
		String kid1,kname1;
		int out = 1;
		if (e.getSource() == done) {
			kid1 = kid.getText();
			if (kid1.equals("")) {
				JOptionPane.showMessageDialog(this, "对不起,科室编号不能为空!");
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
					rs = sql.executeQuery("select * from keshi");
					while (rs.next()) {
						kid1 = rs.getString("kid");
						if (kid1.equals(kid.getText().trim())) {
							kid.setText("");
							JOptionPane.showMessageDialog(this, "对不起,该科室已存在!", "科室管理",JOptionPane.ERROR_MESSAGE);
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
					kid1 = "'" + kid.getText().trim() + "'";
					kname1 ="'" + kname.getText().trim()+ "'";
					String temp = "insert into keshi values (" + kid1 + ","+ kname1  + ")";
					sql.executeUpdate(temp);
					sql.close();
					con.close();
				} catch (SQLException err) {
					JOptionPane.showMessageDialog(this, "信息录入错误,请重新录入!", "科室管理",JOptionPane.ERROR_MESSAGE);
					out = 0;
				}
				if (out == 1) {
					JOptionPane.showMessageDialog(this, "录入成功!", "科室管理",JOptionPane.INFORMATION_MESSAGE);
					kid.setText(" ");
					kname.setText(" ");
				}
			}
		}
		if (e.getSource() == readd) {
			kid.setText(" ");
			kname.setText(" ");
		}
	}
}
class Querydept extends Panel implements ActionListener {
	Connection con;
	Statement sql;
	ButtonGroup bg;
	JTextField kid,kname;
	JButton jb;
	Box box, box0, box1, box2;
	int flag = 0;
	Querydept() {
		Font font = new Font("华文中宋", Font.BOLD, 22);
		UIManager.put("Label.font", font);
		setLayout(new BorderLayout());
		jb = new JButton("查询");
		jb.setBackground(Color.white);
		jb.setFont(new Font("华文行楷", Font.BOLD, 22));
		jb.setForeground(Color.black);
		jb.addActionListener(this);
		kid = new JTextField(26);
		kname = new JTextField(22);
		/* 设置以下属性值不可编辑 */
		kname.setEditable(false);
		Panel p1 = new Panel();
		JLabel jl = new JLabel("   查询科室信息   ");
		jl.setForeground(Color.red);
		jl.setFont(new Font("华文行楷", Font.BOLD, 34));
		p1.add(jl);
		add(p1, "North");
		box0 = Box.createHorizontalBox();
		box0.add(new JLabel("输入要查询的科室编号:"));
		box0.add(Box.createHorizontalStrut(8));
		box0.add(kid);
		box0.add(Box.createHorizontalStrut(8));
		box0.add(jb);
		box1 = Box.createHorizontalBox();
		box1.add(new JLabel("科室编号:"));
		box1.add(Box.createHorizontalStrut(8));
		box1.add(kid);
		box2 = Box.createHorizontalBox();
		box2.add(new JLabel("科室名称:"));
		box2.add(Box.createHorizontalStrut(8));
		box2.add(kname);
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
		setBackground(new Color(102,204,255));
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
			String kid1,kname1;
			ResultSet rs = sql.executeQuery("select * from keshi");
			while (rs.next()) {
				kid1 = rs.getString("kid");
				kname1 = rs.getString("kname");
				if (kid1.equals(kid.getText().trim())) {
					kname.setText(kname1);
					flag = 1;
					break;
				}
			}
			sql.close();
			con.close();
		} catch (SQLException ex) {
		}
		if (flag == 0) {
			JOptionPane.showMessageDialog(null, "没有该科室相关信息!请重新输入！", "科室管理", JOptionPane.ERROR_MESSAGE);
			kid.setText("");
			kname.setText(" ");
		}
	}
}
class Updatedept extends Panel implements ActionListener {
	Connection con;
	Statement sql;
	JButton b1, b2, b3;
	Box box, box0, box1, box2;
	JTextField kid,kname;
	Updatedept() {
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
		kid = new JTextField(18);
		kname = new JTextField(20);
		Panel pNorth = new Panel();
		JLabel jl = new JLabel("  修改科室信息  ");
		jl.setForeground(Color.red);
		jl.setFont(new Font("华文行楷", Font.BOLD, 34));
		pNorth.add(jl);
		add(pNorth, "North");
		box0 = Box.createHorizontalBox();
		box0.add(new JLabel("输入要修改信息的科室编号:"));
		box0.add(Box.createHorizontalStrut(8));
		box0.add(kid);
		box0.add(Box.createHorizontalStrut(8));
		box0.add(b1);
		box1 = Box.createHorizontalBox();
		box1.add(new JLabel("(新)科室名称:"));
		box1.add(Box.createHorizontalStrut(8));
		box1.add(kname);
		box2= Box.createHorizontalBox();
		box2.add(b2);
		box2.add(Box.createHorizontalStrut(8));
		box2.add(b3);
		box = Box.createVerticalBox();
		box.add(box0);
		box.add(Box.createVerticalStrut(12));
		box.add(box1);
		box.add(Box.createVerticalStrut(12));
		box.add(box2);
		box.add(Box.createVerticalStrut(12));
		Panel pCenter = new Panel();
		pCenter.add(box);
		add(pCenter, "Center");
		setSize(300, 250);
		setBackground(new Color(102,204,255));
	}
	public void actionPerformed(ActionEvent e) {
		String kid1,kname1;
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
				ResultSet rs = sql.executeQuery("select * from keshi ");
				while (rs.next()) {
					kid1 = rs.getString("kid");
					kname1 = rs.getString("kname");
					if (kid1.equals(kid.getText().trim())) {
						kname.setText(kname1);
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
				JOptionPane.showMessageDialog(null, "没有该科室相关信息!请重新输入！","科室管理", JOptionPane.ERROR_MESSAGE);
				kid.setText("");
				kname.setText(" ");
			}
		}
		if (e.getSource() == b2) {
			kid1 = "'" +kid.getText().trim()+"'";
			kname1 = "'" + kname.getText().trim() + "'";
			try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			} catch (ClassNotFoundException ee) {
			}
			try {
				con=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=Hospital","sa","sa");
				sql = con.createStatement();
				String temp = "update keshi set kname=" + kname1+"where kid="+kid1;
				sql.executeUpdate(temp);
				sql.close();
				con.close();
				JOptionPane.showMessageDialog(this, "修改成功!", "科室管理", JOptionPane.INFORMATION_MESSAGE);
			} catch (SQLException ex) {
				System.out.println(ex);
			}
		}
		if (e.getSource() == b3) {
			kid.setText("");
			kname.setText(" ");
		}
	}
}
class Deletedept extends Panel implements ActionListener {
	Connection con;
	Statement sql;
	JTextField kid,kname;
	JButton b, b1;
	Box box, box0, box1, box2;
	ButtonGroup bg;
	Deletedept() {
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
		kid = new JTextField(24);
		kname= new JTextField(20);
		kname.setEditable(false);
		Panel pNorth = new Panel();
		JLabel jl = new JLabel("  删除科室信息  ");
		jl.setForeground(Color.red);
		jl.setFont(new Font("华文行楷", Font.BOLD, 34));
		pNorth.add(jl);
		add(pNorth, "North");
		box0 = Box.createHorizontalBox();
		box0.add(new JLabel("输入要删除的科室编号:"));
		box0.add(Box.createHorizontalStrut(8));
		box0.add(kid);
		box0.add(Box.createHorizontalStrut(8));
		box0.add(b);
		box1 = Box.createHorizontalBox();
		box1.add(new JLabel("科室名称:"));
		box1.add(Box.createHorizontalStrut(8));
		box1.add(kname);
		box2 = Box.createHorizontalBox();
		box2.add(b1);
		box = Box.createVerticalBox();
		box.add(box0);
		box.add(Box.createVerticalStrut(12));
		box.add(box1);
		box.add(Box.createVerticalStrut(12));
		box.add(box2);
		box.add(Box.createVerticalStrut(12));
		Panel pCenter = new Panel();
		pCenter.add(box);
		add(pCenter, "Center");
		setSize(300, 250);
		setBackground(new Color(102,204,255));
	}
	public void actionPerformed(ActionEvent e) {
		String kid1,kname1;
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
				ResultSet rs = sql.executeQuery("select * from keshi ");
				while (rs.next()) {
					kid1 = rs.getString("kid");
					kname1 = rs.getString("kname");
					if (kid1.equals(kid.getText().trim())) {
						kname.setText(kname1);
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
				JOptionPane.showMessageDialog(null, "这个科室不存在,请重新输入!", "错误!", JOptionPane.YES_NO_OPTION);
				kid.setText("");
				kname.setText(" ");
			}
		}
		if (e.getSource() == b1) {
			int n = JOptionPane.showConfirmDialog(this, "确定要删除该科室的全部信息吗?", "科室管理", JOptionPane.YES_NO_OPTION);
			if (n == JOptionPane.YES_OPTION) {
				try {
					Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				} catch (ClassNotFoundException ee) {
				}
				try {
					con=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=Hospital","sa","sa");
					sql = con.createStatement();
					kid1 = "'" + kid.getText().trim() + "'";
					String temp = "delete from keshi where kid=" + kid1;
					sql.executeUpdate(temp);
					kid.setText("");
					kname.setText(" ");
					sql.close();
					con.close();
				} catch (SQLException ex) {
				}
			} else if (n == JOptionPane.NO_OPTION) {
			}
		}
	}
}
class additem extends Panel implements ActionListener {
	Connection con;
	Statement sql;
	Panel pNorth, pCenter;
	JButton done, readd;
	Box box, box0, box1, box2, box3, box4,box5;
	JTextField sid, sname,sbrief,sprice;
	public additem() {
		setLayout(new BorderLayout());
		Font font = new Font("华文中宋", Font.BOLD, 22);
		UIManager.put("Label.font", font);
		sid = new JTextField(20);
		sname = new JTextField(20);
		sbrief = new JTextField(20);
		sprice = new JTextField(20);
		box0 = Box.createHorizontalBox();
		JLabel jl = new JLabel("           录入检查项目信息            ");
		jl.setForeground(Color.red);
		jl.setFont(new Font("华文行楷", Font.BOLD, 34));
		box0.add(jl);
		box1 = Box.createHorizontalBox();
		box1.add(new JLabel("项目编号:"));
		box1.add(Box.createHorizontalStrut(8));
		box1.add(sid);
		box2 = Box.createHorizontalBox();
		box2.add(new JLabel("项目名称:"));
		box2.add(Box.createHorizontalStrut(8));
		box2.add(sname);
		box3 = Box.createHorizontalBox();
		box3.add(new JLabel("项目简称:"));
		box3.add(Box.createHorizontalStrut(8));
		box3.add(sbrief);
		box4 = Box.createHorizontalBox();
		box4.add(new JLabel("项目价格:"));
		box4.add(Box.createHorizontalStrut(8));
		box4.add(sprice);
		box5= Box.createHorizontalBox();
		done = new JButton("录入");
		done.setFont(new Font("华文行楷", Font.BOLD, 22));
		done.setForeground(Color.black);
		done.setBackground(Color.white);
		done.addActionListener(this);
		box5.add(done);
		box5.add(Box.createHorizontalStrut(8));
		readd = new JButton("重置");
		readd.setFont(new Font("华文行楷", Font.BOLD, 22));
		readd.setForeground(Color.black);
		readd.setBackground(Color.white);
		readd.addActionListener(this);
		box5.add(readd);
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
		pCenter = new Panel();
		pCenter.add(box);
		add(pCenter, "Center");
		setBackground(new Color(102,204,255));
	}
	public void actionPerformed(ActionEvent e) {
		String sid1, sname1,sbrief1,sprice1;
		int out = 1;
		if (e.getSource() == done) {
			sid1 = sid.getText();
			if (sid1.equals("")) {
				JOptionPane.showMessageDialog(this, "对不起,项目编号不能为空!");
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
					rs = sql.executeQuery("select * from searchitem");
					while (rs.next()) {
						sid1 = rs.getString("sid");
						if (sid1.equals(sid.getText().trim())) {
							sid.setText("");
							JOptionPane.showMessageDialog(this, "对不起,该检查项目已存在!", "检查项目管理",JOptionPane.ERROR_MESSAGE);
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
					sid1 = "'" + sid.getText().trim() + "'";
					sname1 ="'" + sname.getText().trim()+ "'";
					sbrief1 = "'" + sbrief.getText().trim() + "'";
					sprice1 = "'" + sprice.getText().trim() + "'";
					String temp = "insert into searchitem values (" + sid1 + ","+ sname1 + "," + sbrief1 +","  + sprice1 +")";
					sql.executeUpdate(temp);
					sql.close();
					con.close();
				} catch (SQLException err) {
					JOptionPane.showMessageDialog(this, "信息录入错误,请重新录入!", "检查项目管理",JOptionPane.ERROR_MESSAGE);
					out = 0;
				}
				if (out == 1) {
					JOptionPane.showMessageDialog(this, "录入成功!", "检查项目管理",JOptionPane.INFORMATION_MESSAGE);
					sid.setText(" ");
					sname.setText(" ");
					sbrief.setText("");
					sprice.setText("");
				}
			}
		}
		if (e.getSource() == readd) {
			sid.setText(" ");
			sname.setText(" ");
			sbrief.setText("");
			sprice.setText("");
		}
	}
}
class Updateitem extends Panel implements ActionListener {
	Connection con;
	Statement sql;
	JButton b1, b2, b3;
	Box box, box0, box1, box2, box3,box4,box5;
	JTextField sid,sname,sbrief,sprice;
	Updateitem() {
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
		sid = new JTextField(18);
		sname = new JTextField(20);
		sbrief= new JTextField(20);
		sprice = new JTextField(20);
		Panel pNorth = new Panel();
		JLabel jl = new JLabel("  修改检查项目信息  ");
		jl.setForeground(Color.red);
		jl.setFont(new Font("华文行楷", Font.BOLD, 34));
		pNorth.add(jl);
		add(pNorth, "North");
		box0 = Box.createHorizontalBox();
		box0.add(new JLabel("输入要修改的项目编号:"));
		box0.add(Box.createHorizontalStrut(8));
		box0.add(sid);
		box0.add(Box.createHorizontalStrut(8));
		box0.add(b1);
		box1 = Box.createHorizontalBox();
		box1.add(new JLabel("(新)项目名称:"));
		box1.add(Box.createHorizontalStrut(8));
		box1.add(sname);
		box2 = Box.createHorizontalBox();
		box2.add(new JLabel("(新)项目简称:"));
		box2.add(Box.createHorizontalStrut(8));
		box2.add(sbrief);
		box3 = Box.createHorizontalBox();
		box3.add(new JLabel("(新)项目价格:"));
		box3.add(Box.createHorizontalStrut(8));
		box3.add(sprice);
		box4= Box.createHorizontalBox();
		box4.add(b2);
		box4.add(Box.createHorizontalStrut(8));
		box4.add(b3);
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
		Panel pCenter = new Panel();
		pCenter.add(box);
		add(pCenter, "Center");
		setSize(300, 250);
		setBackground(new Color(102,204,255));
	}
	public void actionPerformed(ActionEvent e) {
		String sid1, sname1,sbrief1,sprice1;
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
				ResultSet rs = sql.executeQuery("select * from searchitem ");
				while (rs.next()) {
					sid1 = rs.getString("sid");
					sname1 = rs.getString("sname");
					sbrief1 = rs.getString("sbrief");
					sprice1 = rs.getString("sprice");
					if (sid1.equals(sid.getText().trim())) {
						sname.setText(sname1);
						sbrief.setText(sbrief1);
						sprice.setText(sprice1);
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
				JOptionPane.showMessageDialog(null, "没有该检查项目的相关信息!请重新输入！","检查项目管理", JOptionPane.ERROR_MESSAGE);
				sid.setText("");
				sname.setText(" ");
				sbrief.setText(" ");
				sprice.setText(" ");
			}
		}
		if (e.getSource() == b2) {
			sid1 = "'" +sid.getText().trim()+"'";
			sname1 = "'" + sname.getText().trim() + "'";
			sbrief1 = "'" + sbrief.getText().trim() + "'";
			sprice1= "'" + sprice.getText().trim() + "'";
			try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			} catch (ClassNotFoundException ee) {
			}
			try {
				con=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=Hospital","sa","sa");
				sql = con.createStatement();
				String temp = "update searchitem set sname=" + sname1 + "," +"sbrief=" +sbrief1+","+"sprice="+sprice1+"where sid="+ sid1;
				sql.executeUpdate(temp);
				sql.close();
				con.close();
				JOptionPane.showMessageDialog(this, "修改成功!", "检查项目管理", JOptionPane.INFORMATION_MESSAGE);
			} catch (SQLException ex) {
				System.out.println(ex);
			}
		}
		if (e.getSource() == b3) {
			sid.setText("");
			sname.setText(" ");
			sbrief.setText(" ");
			sprice.setText(" ");
		}
	}
}
class Queryitem extends Panel implements ActionListener {
	Connection con;
	Statement sql;
	ButtonGroup bg;
	JTextField sid,sname,sbrief,sprice;
	JButton jb;
	Box box, box0, box1, box2,box3,box4;
	int flag = 0;
	Queryitem() {
		Font font = new Font("华文中宋", Font.BOLD, 22);
		UIManager.put("Label.font", font);
		setLayout(new BorderLayout());
		jb = new JButton("查询");
		jb.setBackground(Color.white);
		jb.setFont(new Font("华文行楷", Font.BOLD, 22));
		jb.setForeground(Color.black);
		jb.addActionListener(this);
		sid = new JTextField(26);
		sname= new JTextField(22);
		sbrief = new JTextField(20);
		sprice = new JTextField(20);
		/* 设置以下属性值不可编辑 */
		sname.setEditable(false);
		sbrief.setEditable(false);
		sprice.setEditable(false);
		Panel p1 = new Panel();
		JLabel jl = new JLabel("   查询检查项目信息   ");
		jl.setForeground(Color.red);
		jl.setFont(new Font("华文行楷", Font.BOLD, 34));
		p1.add(jl);
		add(p1, "North");
		box0 = Box.createHorizontalBox();
		box0.add(new JLabel("输入要查询的项目编号:"));
		box0.add(Box.createHorizontalStrut(8));
		box0.add(sid);
		box0.add(Box.createHorizontalStrut(8));
		box0.add(jb);
		box1 = Box.createHorizontalBox();
		box1.add(new JLabel("项目编号:"));
		box1.add(Box.createHorizontalStrut(8));
		box1.add(sid);
		box2 = Box.createHorizontalBox();
		box2.add(new JLabel("项目名称:"));
		box2.add(Box.createHorizontalStrut(8));
		box2.add(sname);
		box3 = Box.createHorizontalBox();
		box3.add(new JLabel("项目简称:"));
		box3.add(Box.createHorizontalStrut(8));
		box3.add(sbrief);
		box4 = Box.createHorizontalBox();
		box4.add(new JLabel("项目价格:"));
		box4.add(Box.createHorizontalStrut(8));
		box4.add(sprice);
		box = Box.createVerticalBox();
		box.add(box0);
		box.add(Box.createVerticalStrut(8));
		box.add(box1);
		box.add(Box.createVerticalStrut(8));
		box.add(box2);
		box.add(Box.createVerticalStrut(8));
		box.add(box3);
		box.add(Box.createVerticalStrut(8));
		box.add(box4);
		box.add(Box.createVerticalStrut(8));
		Panel pCenter = new Panel();
		pCenter.add(box);
		add(pCenter, "Center");
		setSize(300, 250);
		setBackground(new Color(102,204,255));
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
			String sid1, sname1,sbrief1,sprice1;
			ResultSet rs = sql.executeQuery("select * from searchitem");
			while (rs.next()) {
				sid1 = rs.getString("sid");
				sname1 = rs.getString("sname");
				sbrief1 = rs.getString("sbrief");
				sprice1 = rs.getString("sprice");
				if (sid1.equals(sid.getText().trim())) {
					sname.setText(sname1);
					sbrief.setText(sbrief1);
					sprice.setText(sprice1);
					flag = 1;
					break;
				}
			}
			sql.close();
			con.close();
		} catch (SQLException ex) {
		}
		if (flag == 0) {
			JOptionPane.showMessageDialog(null, "没有该检查项目的相关信息!请重新输入！", "检查项目管理", JOptionPane.ERROR_MESSAGE);
			sid.setText("");
			sname.setText(" ");
			sbrief.setText(" ");
			sprice.setText(" ");
		}
	}
}
class Deleteitem extends Panel implements ActionListener {
	Connection con;
	Statement sql;
	JTextField sid, sname,sbrief,sprice;
	JButton b, b1;
	Box box, box0, box1, box2,box3,box4,box5,box6;
	ButtonGroup bg;
	Deleteitem() {
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
		sid = new JTextField(24);
		sname = new JTextField(20);
		sbrief= new JTextField(20);
		sprice= new JTextField(20);
		sname.setEditable(false);
		sbrief.setEditable(false);
		sprice.setEditable(false);
		Panel pNorth = new Panel();
		JLabel jl = new JLabel("  删除检查项目信息  ");
		jl.setForeground(Color.red);
		jl.setFont(new Font("华文行楷", Font.BOLD, 34));
		pNorth.add(jl);
		add(pNorth, "North");
		box0 = Box.createHorizontalBox();
		box0.add(new JLabel("输入要删除的项目编号:"));
		box0.add(Box.createHorizontalStrut(8));
		box0.add(sid);
		box0.add(Box.createHorizontalStrut(8));
		box0.add(b);
		box1 = Box.createHorizontalBox();
		box1.add(new JLabel("项目名称:"));
		box1.add(Box.createHorizontalStrut(8));
		box1.add(sname);
		box2 = Box.createHorizontalBox();
		box2.add(new JLabel("项目简称:"));
		box2.add(Box.createHorizontalStrut(8));
		box2.add(sbrief);
		box3 = Box.createHorizontalBox();
		box3.add(new JLabel("项目价格:"));
		box3.add(Box.createHorizontalStrut(8));
		box3.add(sprice);
		box4 = Box.createHorizontalBox();
		box4.add(b1);
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
		Panel pCenter = new Panel();
		pCenter.add(box);
		add(pCenter, "Center");
		setSize(300, 250);
		setBackground(new Color(102,204,255));
	}
	public void actionPerformed(ActionEvent e) {
		String sid1, sname1,sbrief1,sprice1;
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
				ResultSet rs = sql.executeQuery("select * from searchitem");
				while (rs.next()) {
					sid1= rs.getString("sid");
					sname1 = rs.getString("sname");
					sbrief1 = rs.getString("sbrief");
					sprice1 = rs.getString("sprice");
					if (sid1.equals(sid.getText().trim())) {
						sname.setText(sname1);
						sbrief.setText(sbrief1);
						sprice.setText(sprice1);
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
				JOptionPane.showMessageDialog(null, "这个检查项目不存在,请重新输入!", "错误!", JOptionPane.YES_NO_OPTION);
				sid.setText("");
				sname.setText(" ");
				sbrief.setText(" ");
				sprice.setText(" ");
			}
		}
		if (e.getSource() == b1) {
			int n = JOptionPane.showConfirmDialog(this, "确定要删除该检查项目的全部信息吗?", "检查项目管理", JOptionPane.YES_NO_OPTION);
			if (n == JOptionPane.YES_OPTION) {
				try {
					Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				} catch (ClassNotFoundException ee) {
				}
				try {
					con=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=Hospital","sa","sa");
					sql = con.createStatement();
					sid1 = "'" + sid.getText().trim() + "'";
					String temp = "delete from searchitem where sid=" + sid1;
					sql.executeUpdate(temp);
					sid.setText("");
					sname.setText(" ");
					sbrief.setText(" ");
					sprice.setText(" ");
					sql.close();
					con.close();
				} catch (SQLException ex) {
				}
			} else if (n == JOptionPane.NO_OPTION) {
			}
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
		lab = new JLabel("确认要退出管理员界面吗?");
		lab.setForeground(Color.black);
		lab.setFont(new Font("华文行楷", Font.PLAIN, 22));
		panelN.add(lab);
		yes = new JButton("退出");
		yes.setForeground(Color.black);
		yes.setFont(new Font("华文行楷", Font.PLAIN, 20));
		yes.addActionListener(this);
		no = new JButton("取消");
		no.setFont(new Font("华文行楷", Font.PLAIN, 20));
		no.setForeground(Color.black);
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
	Menu menu_select1,menu_select2,menu_select3,menu_select4;
	MenuItem item_userInfo_add, item_userInfo_query, item_userInfo_update,item_userInfo_delete,
	         item_drug_update,item_drug_add,item_drug_query,item_drug_delete, 
	         item_dept_update,item_dept_add,item_dept_query,item_dept_delete, 
	         item_search_update,item_search_add,item_search_query,item_search_delete, 
	         item_exit;
	add luru;
	Query chaxun;
	Update xiugai;
	Delete shanchu;
	adddrug jiayao;
	Updatedrug gaiyao;
	Querydrug chayao;
	Deletedrug shanyao;
	adddept jiakeshi;
	Updatedept gaikeshi;
	Querydept chakeshi;
	Deletedept shankeshi;
	additem jiaxiangmu;
	Updateitem gaixiangmu;
	Queryitem chaxiangmu;
	Deleteitem shanxiangmu;
	Box box, box1, box2;
	fram() {
		luru = new add();
		chaxun = new Query();
		xiugai = new Update();
		shanchu = new Delete();
		jiayao = new adddrug();
		chayao = new Querydrug();
		gaiyao = new Updatedrug();
		shanyao = new Deletedrug();
		jiakeshi = new adddept();
		chakeshi  = new Querydept();
		gaikeshi  = new Updatedept();
		shankeshi = new Deletedept();
		jiaxiangmu = new additem();
		chaxiangmu  = new Queryitem();
		gaixiangmu  = new Updateitem();
		shanxiangmu = new Deleteitem();
		menubar = new MenuBar();
		menu_select1 = new Menu("用户管理");
		menu_select2 = new Menu("药品管理");
		menu_select3 = new Menu("科室管理");
		menu_select4 = new Menu("检查项目管理");
		menu_select1.setFont(new Font("华文行楷", Font.BOLD, 18));
		menu_select2.setFont(new Font("华文行楷", Font.BOLD, 18));
		menu_select3.setFont(new Font("华文行楷", Font.BOLD, 18));
		menu_select4.setFont(new Font("华文行楷", Font.BOLD, 18));
		item_userInfo_add = new MenuItem("录入帐号信息");
		item_userInfo_add.addActionListener(this);
		item_userInfo_update = new MenuItem("修改帐号信息");
		item_userInfo_update.addActionListener(this);
		item_userInfo_query = new MenuItem("查询帐号信息");
		item_userInfo_query.addActionListener(this);
		item_userInfo_delete = new MenuItem("删除帐号信息");
		item_userInfo_delete.addActionListener(this);
		item_drug_add = new MenuItem("录入药品信息");
		item_drug_add.addActionListener(this);
		item_drug_update = new MenuItem("修改药品信息");
		item_drug_update.addActionListener(this);
		item_drug_query = new MenuItem("查询药品信息");
		item_drug_query.addActionListener(this);
		item_drug_delete= new MenuItem("删除药品信息");
		item_drug_delete.addActionListener(this);
		item_dept_add = new MenuItem("录入科室信息");
		item_dept_add.addActionListener(this);
		item_dept_update = new MenuItem("修改科室信息");
		item_dept_update.addActionListener(this);
		item_dept_query = new MenuItem("查询科室信息");
		item_dept_query.addActionListener(this);
		item_dept_delete= new MenuItem("删除科室信息");
		item_dept_delete.addActionListener(this);
		item_search_add= new MenuItem("录入检查项目信息");
		item_search_add.addActionListener(this);
		item_search_update = new MenuItem("修改检查项目信息");
		item_search_update.addActionListener(this);
		item_search_query = new MenuItem("查询检查项目信息");
		item_search_query.addActionListener(this);
		item_search_delete = new MenuItem("删除检查项目信息");
		item_search_delete.addActionListener(this);
		item_exit = new MenuItem("退出");
		item_exit.addActionListener(this);
		menu_select1.add(item_userInfo_query);
		menu_select1.add(item_userInfo_add);
		menu_select1.add(item_userInfo_update);
		menu_select1.add(item_userInfo_delete);
		menu_select2.add(item_drug_query);
		menu_select2.add(item_drug_add);
		menu_select2.add(item_drug_update);
		menu_select2.add(item_drug_delete);
		menu_select3.add(item_dept_query);
		menu_select3.add(item_dept_add);
		menu_select3.add(item_dept_update);
		menu_select3.add(item_dept_delete);
		menu_select4.add(item_search_query);
		menu_select4.add(item_search_add);
		menu_select4.add(item_search_update);
		menu_select4.add(item_search_delete);
		menu_select1.add(item_exit);
		menu_select2.add(item_exit);
		menu_select3.add(item_exit);
		menu_select4.add(item_exit);
		menubar.add(menu_select1);
		menubar.add(menu_select2);
		menubar.add(menu_select3);
		menubar.add(menu_select4);
		setMenuBar(menubar);
	
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == item_userInfo_add) {
			removeAll();
			add(luru, "Center");
			validate();
		}
		if (e.getSource() == item_userInfo_query) {
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
		if (e.getSource() == item_drug_add) {
			removeAll();
			add(jiayao, "Center");
			validate();
		}
		if (e.getSource() == item_drug_query) {
			removeAll();
			add(chayao, "Center");
			validate();
		}
		if (e.getSource() == item_drug_update) {
			removeAll();
			add(gaiyao, "Center");
			validate();
		}
		if (e.getSource() == item_drug_delete) {
			removeAll();
			add(shanyao, "Center");
			validate();
		}
		if (e.getSource() == item_dept_add) {
			removeAll();
			add(jiakeshi, "Center");
			validate();
		}
		if (e.getSource() == item_dept_query) {
			removeAll();
			add(chakeshi, "Center");
			validate();
		}
		if (e.getSource() == item_dept_update) {
			removeAll();
			add(gaikeshi, "Center");
			validate();
		}
		if (e.getSource() == item_dept_delete) {
			removeAll();
			add(shankeshi, "Center");
			validate();
		}
		if (e.getSource() == item_search_add) {
			removeAll();
			add(jiaxiangmu, "Center");
			validate();
		}
		if (e.getSource() == item_search_query) {
			removeAll();
			add(chaxiangmu, "Center");
			validate();
		}
		if (e.getSource() == item_search_update) {
			removeAll();
			add(gaixiangmu, "Center");
			validate();
		}
		if (e.getSource() == item_search_delete) {
			removeAll();
			add(shanxiangmu, "Center");
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

