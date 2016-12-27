import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Druger extends JFrame{
// 定义组件
    private JScrollPane scp;
    private JTableHeader jth;
    private JTable tab;
    private JButton btnShow,btndeal;
// 构造方法
   public Druger(){
// 窗体的相关属性的定义
   super("药师");
   this.setSize(300, 250);
   this.setLayout(null);
   this.setBackground(new Color(102,204,255));
   this.setBounds(300, 150, 800, 400);;
// 创建组件
   this.scp = new JScrollPane();
   this.scp.setBounds(150,50,500,250);
   this.btnShow = new JButton("显示处方单");
   this.btnShow.setBounds(250,10,100,30);
   this.btndeal= new JButton("开药");
   this.btndeal.setBounds(450,10,100,30);
// 给按钮注册监听
   this.btnShow.addActionListener(new ActionListener(){
  public void actionPerformed(ActionEvent ae){
     btnShow_ActionPerformed(ae);
   }
  });
   this.btndeal.addActionListener(new ActionListener(){
	   public void actionPerformed(ActionEvent ae){
	      btndeal_ActionPerformed(ae);
	    }
	   });
// 将组件加入到窗体中
   add(this.scp);
   add(this.btnShow);
   add(this.btndeal);
// 显示窗体
   this.setVisible(true);
}
// 点击按钮时的事件处理
   public void btnShow_ActionPerformed(ActionEvent ae){
   	// 以下是连接数据源和显示数据的具体处理方法
   	Connection con=null;

   // 获得连接
   	try {
   		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
   	} catch (ClassNotFoundException e) {
   		// TODO Auto-generated catch block
   		e.printStackTrace();
   	}
       try {
   		con=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=Hospital","sa","sa");
   	} catch (SQLException e) {
   		// TODO Auto-generated catch block
   		e.printStackTrace();
   	}
   // 建立查询条件
       String sql = "select * from  recipe_drug"; 
       PreparedStatement pstm = null;
   	try {
   		pstm = con.prepareStatement(sql);
   	} catch (SQLException e) {
   		// TODO Auto-generated catch block
   		e.printStackTrace();
   	}
   // 执行查询
       ResultSet rs = null;
   	try {
   		rs = pstm.executeQuery();
   	} catch (SQLException e) {
   		// TODO Auto-generated catch block
   		e.printStackTrace();
   	}
   // 计算有多少条记录
       int c = 0;
       try {
   		while(rs.next()){
   		c++;
   		}
   	} catch (SQLException e) {
   		// TODO Auto-generated catch block
   		e.printStackTrace();
   	}
       try {
   		rs = pstm.executeQuery();
   	} catch (SQLException e) {
   		// TODO Auto-generated catch block
   		e.printStackTrace();
   	}
   // 将查询获得的记录数据，转换成适合生成JTable的数据形式
       Object[][] info = new Object[c][6];
       c = 0;
       try {
   		while(rs.next()){
   		info[c][0] = Integer.valueOf( rs.getInt("pid"));
   		info[c][1] = Integer.valueOf( rs.getInt("doc"));
   		info[c][2] = rs.getString("drugbrief");
   		info[c][3] = Integer.valueOf( rs.getInt("count") );
   		info[c][4] = rs.getString("tottalprice");
   		info[c][5] = rs.getString("ispay");
   		c++;
   		}
   	} catch (SQLException e) {
   		// TODO Auto-generated catch block
   		e.printStackTrace();
   	}
   // 定义表头
       String[] title = {"病人编号","医生编号","药品简称","数量","总价","是否付费"};
   // 创建JTable
       this.tab = new JTable(info,title);
   // 显示表头
       this.jth = this.tab.getTableHeader();
   // 将JTable加入到带滚动条的面板中
       this.scp.getViewport().add(tab); 
   }
public void btndeal_ActionPerformed(ActionEvent ae){

	Connection con=null;
// 获得连接
	try {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    try {
		con=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;DatabaseName=Hospital","sa","sa");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
// 建立查询条件
    String sql = "select * from drug"; 
    PreparedStatement pstm = null;
	try {
		pstm = con.prepareStatement(sql);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
// 执行查询
    ResultSet rs = null;
	try {
		rs = pstm.executeQuery();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
// 计算有多少条记录
    int c = 0;
    try {
		while(rs.next()){
		c++;
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    try {
		rs = pstm.executeQuery();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
// 将查询获得的记录数据，转换成适合生成JTable的数据形式
    Object[][] info = new Object[c][6];
    c = 0;
    try {
		while(rs.next()){
		info[c][0] = rs.getString("drugid");
		info[c][1] = rs.getString("drugname");
		info[c][2] = rs.getString("drugbrief");
		info[c][3] = rs.getString("drugcom" );
		info[c][4] = rs.getString("drugprice");
		info[c][5] = rs.getString("drugnum");
		c++;
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
// 定义表头
    String[] title = {"药品编号","药品名称","药品简称","药品单位","药品价格","药品库存"};
// 创建JTable
    this.tab = new JTable(info,title);
// 显示表头
    this.jth = this.tab.getTableHeader();
// 将JTable加入到带滚动条的面板中
    this.scp.getViewport().add(tab); 
 Statement st=null;
  try {
	st=con.createStatement();
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
  String sql1="select * from drug";
  ResultSet rs1=null;
  try {
	rs1=st.executeQuery(sql1);
} catch (SQLException e1) {
	// TODO Auto-generated catch block
	e1.printStackTrace();
}
  int drugnum1 = 0;
  try {
	while(rs1.next()){
		drugnum1 = Integer.valueOf(rs1.getInt("drugnum"));
    }
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
String sql2="select count(count) as count from recipe_drug join drug on drug.drugbrief=recipe_drug.drugbrief";
  ResultSet rs2=null;
  try {
	rs2=st.executeQuery(sql2);
} catch (SQLException e1) {
	// TODO Auto-generated catch block
	e1.printStackTrace();
}
  int count1 = 0;
  try {
	while(rs2.next()){
		count1 = Integer.valueOf(rs2.getInt("count"));
	  }
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
  String sql3="update drug set drugnum="+(drugnum1-count1)+"where exists(select drugbrief from recipe_drug where drug.drugbrief=recipe_drug.drugbrief)";
  try {
	st.executeUpdate(sql3);
} catch (SQLException e1) {
	// TODO Auto-generated catch block
	e1.printStackTrace();
}
  try {
	st.close();
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

public static void main(String[] args){
new Druger();
}
}
