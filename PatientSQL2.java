package cn.edu.usst.moon;

import java.sql.*;

public class PatientSQL2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection con=null;
		Statement stmt=null;
		String strTemp= "";
		try{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			System.out.println("�������سɹ���");
		}
		catch(ClassNotFoundException e){
			System.out.println("��������ʧ�ܣ�");
		}
		try{
			con=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=patient");
			stmt=con.createStatement();
			System.out.println("���ݿ����ӳɹ���");
			
		}catch(SQLException ee){
			System.out.println("���ݿ�����ʧ�ܣ�");
		}
		

	}

}
