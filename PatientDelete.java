package cn.edu.usst.moon;
import java.sql.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.JOptionPane;


@SuppressWarnings("serial")
public class PatientDelete  extends JPanel implements ActionListener{
	Connection con;
	Statement sql;
	JTextField t1, t2, t3, t4, t5, t6,t7;
	JButton b;
	Box baseBox, bv1, bv2;
	
	PatientDelete(){
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e) {
		}
		try {
			con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=patient");
			sql = con.createStatement();
		} catch (SQLException ee) {
		}
		setLayout(new BorderLayout());
		b = new JButton("ɾ��");
		b.setBackground(Color.cyan);
		b.addActionListener(this);
		t1 = new JTextField(8);
		t1.addActionListener(this);
		t2 = new JTextField(16);
		t3 = new JTextField(16);
		t4 = new JTextField(16);
		t5 = new JTextField(16);
		t6 = new JTextField(16);
		t7 = new JTextField(16);
		t2.setEditable(false);
		t3.setEditable(false);
		t4.setEditable(false);
		t5.setEditable(false);
		t6.setEditable(false);
		t7.setEditable(false);
		JPanel p1 = new JPanel(), p2 = new JPanel();
		p1.add(new JLabel("����Ҫɾ�����˵��û���"));
		p1.add(t1);
		p1.add(b);
		bv1 = Box.createVerticalBox();
		bv1.add(new JLabel("����"));
		bv1.add(Box.createVerticalStrut(8));
		bv1.add(new JLabel("�Ա�"));
		bv1.add(Box.createVerticalStrut(8));
		bv1.add(new JLabel("��ַ"));
		bv1.add(Box.createVerticalStrut(8));
		bv1.add(new JLabel("�绰"));
		bv1.add(Box.createVerticalStrut(8));
		bv1.add(new JLabel("����"));
		bv1.add(Box.createVerticalStrut(8));
		bv1.add(new JLabel("ҽ��"));
		bv1.add(Box.createVerticalStrut(8));
		bv2 = Box.createVerticalBox();
		bv2.add(t2);
		bv2.add(Box.createVerticalStrut(8));
		bv2.add(t3);
		bv2.add(Box.createVerticalStrut(8));
		bv2.add(t4);
		bv2.add(Box.createVerticalStrut(8));
		bv2.add(t5);
		bv2.add(Box.createVerticalStrut(8));
		bv2.add(t6);
		bv2.add(Box.createVerticalStrut(8));
		bv2.add(t7);
		bv2.add(Box.createVerticalStrut(8));
		baseBox = Box.createHorizontalBox();
		baseBox.add(bv1);
		baseBox.add(Box.createHorizontalStrut(10));
		baseBox.add(bv2);
		p2.add(baseBox);
		add(p1, "North");
		add(p2, "Center");
		setSize(350,300);
		setBackground(Color.LIGHT_GRAY);
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == t1) {
			try {
				delete();
			} catch (SQLException ee) {
			}
		} else if (e.getSource() == b) {
			
			try {
				delete();
			} catch (SQLException ee) {
			}
			
			int n = JOptionPane.showConfirmDialog(this, "ȷ��Ҫɾ���ò��˵�ȫ����Ϣ��?",
					"ȷ��", JOptionPane.YES_NO_OPTION);
			if (n == JOptionPane.YES_OPTION) {
				try {
					String s1 = "'" + t1.getText().trim() + "'";
					String temp = "DELETE FROM student WHERE id=" + s1;
					con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=patient");
					sql.executeUpdate(temp);
					JOptionPane.showMessageDialog(this, "ɾ���ɹ�!", "��ʾ�Ի���",
							JOptionPane.INFORMATION_MESSAGE);
					con.close();
				} catch (SQLException ee) {
				}
			} else if (n == JOptionPane.NO_OPTION) {
			}
		}
	}

	public void delete() throws SQLException {
		String id, name, gender, address, phone, office,doctor;
		con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=patient");
		id = t1.getText().trim();
		ResultSet rs = sql.executeQuery("SELECT * FROM patient where id='"
				+ id + "'");
		if (rs.next()) {
			// id=rs.getString("id");
			name = rs.getString("name");
			gender = rs.getString("gender");
			address = rs.getString("address");
			phone = rs.getString("phone");
			office = rs.getString("office");
			doctor = rs.getString("dotor");
			// if(id.equals(t1.getText().trim())){
			t2.setText(name);
			t3.setText(gender);
			t4.setText(address);
			t5.setText(phone);
			t6.setText(office);
			t7.setText(doctor);
			// break;
			// }
		} else {
			JOptionPane.showMessageDialog(this, "�ò���δ��¼��!", "��ʾ�Ի���",
					JOptionPane.INFORMATION_MESSAGE);
		}

		con.close();
	}

}
