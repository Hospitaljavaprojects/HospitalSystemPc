package cn.edu.usst.moon;

import java.sql.*;

public class PatientSQL1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection con=null;
		Statement stmt=null;
		String strTemp="";
		try{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			System.out.println("驱动加载成功！");
		}
		catch(ClassNotFoundException e){
			System.out.println("驱动加载失败！");
		}

	}

}
