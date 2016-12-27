package cn.edu.usst.moon;

import java.sql.*;

public class Charging {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection con=null;
		Statement stmt=null;
		ResultSet rs=null;
		 String strTemp = "";
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			con=DriverManager.getConnection("jdbc:sqlserver://localhost:1433; DatabaseName=Hospital","sa","sa");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			stmt=con.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		strTemp="use Hospital  insert into total select  totalprice=recipe.drugnum*drug.drugprice+searchitem.sprice  from recipe join drug on recipe.drugname=drug.drugname join searchitem on recipe.item=searchitem.sname group by recipe.drugnum,drug.drugprice,searchitem.sprice ";
		try {
			stmt.executeUpdate(strTemp);
		} catch (SQLException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		strTemp = "select price from total";
		
			try {
				 rs=stmt.executeQuery(strTemp);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		System.out.println("应收取总费用为：");
		float sum=0;
		try {
			while(rs.next())
			{
				
				
				sum=sum+rs.getFloat("price");
			}
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		System.out.println(sum);
		try {
			rs.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
