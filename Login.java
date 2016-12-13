import java.awt.*; 
import javax.swing.*; 
import java.awt.event.*;  

public class Login extends JFrame implements ActionListener 
{ 
    private JLabel user,password; 
    private JTextField text_user; //用户名文本行
    private JPasswordField text_password; //密码文本行
    private JButton button_ok,button_cancel; 
    private JComboBox typeBox; 
    JLabel jlabel; 
    public boolean isLogin=false; //判断登录是否成功
    public Login() { 
	  super("用户登录"); 
      this.setSize(240,150); 
      this.setLocation(550,200); 
      this.setBackground(Color.lightGray); 
      this.setResizable(false); 
      this.setDefaultCloseOperation(DISPOSE_ON_CLOSE); 
      this.getContentPane().setLayout(new FlowLayout()); 
      jlabel=new JLabel("    欢迎登录医院信息管理系统      ",JLabel.CENTER); 
      this.getContentPane().add(jlabel); 
      user=new JLabel("用户名："); 
      user.setFont(new Font("Dialog",0,12)); 
      this.getContentPane().add(user); 
      text_user = new JTextField(15); 
      text_user.setEditable(true);
      this.getContentPane().add(text_user); 
      password=new JLabel("密    码："); 
      password.setFont(new Font("Dialog",0,12)); 
      this.getContentPane().add(password); 
      text_password = new JPasswordField(15); 
      this.getContentPane().add(text_password); 
      String types[] = {"管理员","收费人员","医生","药师","院长"}; 
      typeBox = new JComboBox(types); 
      button_ok = new JButton("登录"); 
      button_ok.setFont(new Font("Dialog",0,12)); 
      this.getContentPane().add(new Label()); 
      this.getContentPane().add(typeBox); 
      this.getContentPane().add(button_ok);  
      button_ok.addActionListener(this); 
      button_cancel = new JButton("取消"); 
      button_cancel.setFont(new Font("Dialog",0,12)); 
      this.getContentPane().add(button_cancel); 
      button_cancel.addActionListener(this); 
      this.setVisible(true); 
    }
    public void actionPerformed(ActionEvent e)   //单击事件处理方法
    { 
      String username = text_user.getText(); 
      String password = new String(text_password.getPassword()); 
      if (e.getSource()==button_cancel) //单击退出按钮
      {this.setVisible(false); } 
      else 
      if (username.equals("")) 
      JOptionPane.showMessageDialog(this, "请输入用户名！"); 
      else if (password.equals("")) 
      JOptionPane.showMessageDialog(this, "密码不能为空，请输入密码！"); 
      else 
      	if (e.getSource()==button_ok) // 单击登录按钮
             { 
      		loginBean login=new loginBean(); 
      		isLogin=login.enter(username,password); 
      		int type = typeBox.getSelectedIndex(); 
      		if(isLogin) 
      		{ 
                 if(login.Flag==type){ 
                    new Menu(type); 
                 }else{ 
                  JOptionPane.showMessageDialog(this, "登录身份不正确!"); 
                  return;
                  } 
                { 
              	  text_user.setText(""); 
              	  text_password.setText(""); 
              	  } 
                this.setVisible(false); 
               } 
         } 
     }  
   public static void main(String args[]) throws Exception //测试方法
   {
	new Login(); 
   } 
}  


