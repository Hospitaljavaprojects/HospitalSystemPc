package dao;
	import java.awt.Panel;
	import java.sql.ResultSet;
	import java.util.Vector;

	import javax.swing.JScrollPane;
	import javax.swing.JTable;
	import javax.swing.table.AbstractTableModel;

	public class DocManager extends AbstractTableModel {
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
			colu.add("工号");
			colu.add("姓名");
			colu.add("密码");//如何不显示
			colu.add("所属科室");

			rows= new Vector();
			try{
				base = new Basedao();
				rs= base.result(sql, param);
				while(rs.next())
				{
					Vector row= new Vector();
					row.add(rs.getString(1));
					row.add(rs.getString(2));
					row.add(rs.getString(3));
					row.add(rs.getString(4));
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

		public boolean update(String sql ,String [] param)//增加，删除，修改
		{
			base = new  Basedao();
			return  base.executeSQL(sql, param);		
		}
	}