package dao;

import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.table.AbstractTableModel;

public class DeskManager extends AbstractTableModel {
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
	
}
