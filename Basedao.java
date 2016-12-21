package dao;
import java.sql.*;
public class Basedao {
public final static String DRIVER ="com.microsoft.sqlserver.jdbc.SQLServerDriver";
public final static String URL="jdbc:sqlserver://localhost:1433; DatabaseName=Hospital";
public final static String DBNAME="sa";
public final static String DBPWD="sql9410..";
public Connection getcon() throws ClassNotFoundException,SQLException
	{
		Class.forName(DRIVER);
		Connection con=DriverManager.getConnection(URL, DBNAME, DBPWD);	
		return con;
	}
public ResultSet result(String sql,String [] param)
	{
		Connection con= null;
		PreparedStatement ps=null;
		ResultSet rs = null;
		try {
			con =getcon();
			ps=con.prepareStatement(sql);
			for(int i=0;i<param.length;i++)
			{
				ps.setString(i+1, param[i]);
			}
			rs=ps.executeQuery();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
		return rs;
		}//finally
	}
public void closeAll(Connection con,PreparedStatement ps,ResultSet rs)
	{
		if(rs!=null)
		{
			try{
			rs.close();
			}
			catch(SQLException e){
				e.printStackTrace();
			}
		}//rs
		if(ps!=null)
		{
			try{
				ps.close();
				}
				catch(SQLException e){
					e.printStackTrace();
				}
		}//ps
		if(con!=null)
		{
			try{
				con.close();
				}
				catch(SQLException e){
					e.printStackTrace();
				}
		}//con
	}
public boolean executeSQL(String sql,String [] param){
	Connection con=null;
	PreparedStatement ps=null;
	ResultSet rs=null;
	boolean bln=true;
	int flag=0;
	try {
		con=getcon();
		ps=con.prepareStatement(sql);
		for(int i=0;i<param.length;i++)
		{
			ps.setString(i+1, param[i]);
		}
		flag=ps.executeUpdate();
		if(flag!=1)
			bln=false;
		else 
			bln=true;
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	finally{
		closeAll(con,ps,rs);
	}
	return bln;
}
}
