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
			System.out.println("驱动加载成功！");
		}
		catch(ClassNotFoundException e){
			System.out.println("驱动加载失败！");
		}
		try{
			con=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=patient");
			stmt=con.createStatement();
			System.out.println("数据库连接成功！");
			
		}catch(SQLException ee){
			System.out.println("数据库连接失败！");
		}
		

	}

}
