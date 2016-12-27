package cn.edu.usst.moon;

import java.sql.*;


public class PatientSQL3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection con=null;
		Statement stmt=null;
		String strTemp= "";
		try{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		}catch(ClassNotFoundException e){
			
		}
		try{
			con=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=Hospital","sa","sa");
			stmt=con.createStatement();
		}catch(SQLException ee){
			
		}
		
		strTemp="CREATE TABLE patient(id varchar(20) PRIMARY KEY,name varchar(20),gender varchar(5),address varchar(50),"
				+"phone varchar(50),office varchar(10),doctor varchar(10)";
		try{
			stmt.executeUpdate(strTemp);
			System.out.println("数据库表建立成功！");
		}catch(SQLException e){
			e.printStackTrace();
		}

	}

}
