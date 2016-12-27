import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Druger extends JFrame{
// �������
    private JScrollPane scp;
    private JTableHeader jth;
    private JTable tab;
    private JButton btnShow,btndeal;
// ���췽��
   public Druger(){
// �����������ԵĶ���
   super("ҩʦ");
   this.setSize(300, 250);
   this.setLayout(null);
   this.setBackground(new Color(102,204,255));
   this.setBounds(300, 150, 800, 400);;
// �������
   this.scp = new JScrollPane();
   this.scp.setBounds(150,50,500,250);
   this.btnShow = new JButton("��ʾ������");
   this.btnShow.setBounds(250,10,100,30);
   this.btndeal= new JButton("��ҩ");
   this.btndeal.setBounds(450,10,100,30);
// ����ťע�����
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
// ��������뵽������
   add(this.scp);
   add(this.btnShow);
   add(this.btndeal);
// ��ʾ����
   this.setVisible(true);
}
// �����ťʱ���¼�����
   public void btnShow_ActionPerformed(ActionEvent ae){
   	// ��������������Դ����ʾ���ݵľ��崦����
   	Connection con=null;

   // �������
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
   // ������ѯ����
       String sql = "select * from  recipe_drug"; 
       PreparedStatement pstm = null;
   	try {
   		pstm = con.prepareStatement(sql);
   	} catch (SQLException e) {
   		// TODO Auto-generated catch block
   		e.printStackTrace();
   	}
   // ִ�в�ѯ
       ResultSet rs = null;
   	try {
   		rs = pstm.executeQuery();
   	} catch (SQLException e) {
   		// TODO Auto-generated catch block
   		e.printStackTrace();
   	}
   // �����ж�������¼
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
   // ����ѯ��õļ�¼���ݣ�ת�����ʺ�����JTable��������ʽ
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
   // �����ͷ
       String[] title = {"���˱��","ҽ�����","ҩƷ���","����","�ܼ�","�Ƿ񸶷�"};
   // ����JTable
       this.tab = new JTable(info,title);
   // ��ʾ��ͷ
       this.jth = this.tab.getTableHeader();
   // ��JTable���뵽���������������
       this.scp.getViewport().add(tab); 
   }
public void btndeal_ActionPerformed(ActionEvent ae){

	Connection con=null;
// �������
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
// ������ѯ����
    String sql = "select * from drug"; 
    PreparedStatement pstm = null;
	try {
		pstm = con.prepareStatement(sql);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
// ִ�в�ѯ
    ResultSet rs = null;
	try {
		rs = pstm.executeQuery();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
// �����ж�������¼
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
// ����ѯ��õļ�¼���ݣ�ת�����ʺ�����JTable��������ʽ
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
// �����ͷ
    String[] title = {"ҩƷ���","ҩƷ����","ҩƷ���","ҩƷ��λ","ҩƷ�۸�","ҩƷ���"};
// ����JTable
    this.tab = new JTable(info,title);
// ��ʾ��ͷ
    this.jth = this.tab.getTableHeader();
// ��JTable���뵽���������������
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
