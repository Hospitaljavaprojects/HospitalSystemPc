package cn.edu.usst.moon;

import java.sql.*;

public class PatientSQL4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 Connection con=null;
		  Statement stmt=null;
		  ResultSet rs = null;
		  String strTemp = "";
		
		//������������  
		try{
			    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			    }
		catch(ClassNotFoundException e){}
		
		//�������ݿ�����
		try{
			     con=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=Hospital","sa","sa");
			     stmt=con.createStatement();
		}catch(SQLException ee){}
		
		//������
		strTemp = "create table patient(id varchar(20) ,name varchar(20),gender varchar(5),address varchar(50),"
				+"phone varchar(50),office varchar(10),doctor varchar(10))";
		try {
			stmt.executeUpdate(strTemp);
		} catch (SQLException e) {
			e.printStackTrace();
		}		
       
		//�������
		strTemp = "insert into patient values('ZhangSan','����','��','̫ԭ��ӭ�������79��','13803511208','�ǿ�','�����',500)";
		try {
			stmt.executeUpdate(strTemp);
		} catch (SQLException e) {
			e.printStackTrace();
		}		
       
		//��ȡ���������
		strTemp = "select * from patient";		
		try {
			rs = stmt.executeQuery(strTemp);
			while(rs.next())
			{
				System.out.println(rs.getString("id"));
				System.out.println(rs.getString("name"));
				System.out.println(rs.getString("gender"));
				System.out.println(rs.getString("address"));
				System.out.println(rs.getString("phone"));
				System.out.println(rs.getString("office"));
				System.out.println(rs.getString("doctor"));
				System.out.println(rs.getString("ispay"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		
		//�ͷ���Դ
       try { 
			rs.close();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



	}

}
