package dao;

import java.awt.Panel;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

public class KsManager extends AbstractTableModel {
	private Basedao base;
	private ResultSet rs;
	private Vector rows,colu;
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return this.colu.size();
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return this.rows.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return ((Vector)this.rows.get(rowIndex)).get(columnIndex);
	}

	public String getColu(int column)
	{
		return (String)this.colu.get(column);
	}
	
	public void search(String sql, String [] param)
	{
		colu= new Vector();
		colu.add("科室号");
		colu.add("科室名");
		rows= new Vector();
		try{
			base = new Basedao();
			rs= base.result(sql, param);
			while(rs.next())
			{
				Vector row= new Vector();
				row.add(rs.getString(1));
				row.add(rs.getString(2));
				rows.add(row);
			}
		}
		catch(Exception e)
		{
		e.printStackTrace();
		}
		finally
		{
			base.closeAll(null, null, null);
		}
	}

	public void addAmount(String sql ,String [] param)
	{
		colu= new Vector();
		colu.add("科室名");
		colu.add("挂号量");
		rows= new Vector();
		try{
			base = new Basedao();
			rs= base.result(sql, param);
			while(rs.next())
			{
				Vector row= new Vector();
				row.add(rs.getString(1));
				row.add(rs.getString(2));
				rows.add(row);
			}
		}
		catch(Exception e)
		{
		e.printStackTrace();
		}
		finally
		{
			base.closeAll(null, null, null);
		}
	}
}
