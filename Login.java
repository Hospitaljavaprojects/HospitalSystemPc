import java.awt.*; 
import javax.swing.*; 
import java.awt.event.*;  

public class Login extends JFrame implements ActionListener 
{ 
    private JLabel user,password; 
    private JTextField text_user; //�û����ı���
    private JPasswordField text_password; //�����ı���
    private JButton button_ok,button_cancel; 
    private JComboBox typeBox; 
    JLabel jlabel; 
    public boolean isLogin=false; //�жϵ�¼�Ƿ�ɹ�
    public Login() { 
	  super("�û���¼"); 
      this.setSize(240,150); 
      this.setLocation(550,200); 
      this.setBackground(Color.lightGray); 
      this.setResizable(false); 
      this.setDefaultCloseOperation(DISPOSE_ON_CLOSE); 
      this.getContentPane().setLayout(new FlowLayout()); 
      jlabel=new JLabel("    ��ӭ��¼ҽԺ��Ϣ����ϵͳ      ",JLabel.CENTER); 
      this.getContentPane().add(jlabel); 
      user=new JLabel("�û�����"); 
      user.setFont(new Font("Dialog",0,12)); 
      this.getContentPane().add(user); 
      text_user = new JTextField(15); 
      text_user.setEditable(true);
      this.getContentPane().add(text_user); 
      password=new JLabel("��    �룺"); 
      password.setFont(new Font("Dialog",0,12)); 
      this.getContentPane().add(password); 
      text_password = new JPasswordField(15); 
      this.getContentPane().add(text_password); 
      String types[] = {"����Ա","�շ���Ա","ҽ��","ҩʦ","Ժ��"}; 
      typeBox = new JComboBox(types); 
      button_ok = new JButton("��¼"); 
      button_ok.setFont(new Font("Dialog",0,12)); 
      this.getContentPane().add(new Label()); 
      this.getContentPane().add(typeBox); 
      this.getContentPane().add(button_ok);  
      button_ok.addActionListener(this); 
      button_cancel = new JButton("ȡ��"); 
      button_cancel.setFont(new Font("Dialog",0,12)); 
      this.getContentPane().add(button_cancel); 
      button_cancel.addActionListener(this); 
      this.setVisible(true); 
    }
    public void actionPerformed(ActionEvent e)   //�����¼�������
    { 
      String username = text_user.getText(); 
      String password = new String(text_password.getPassword()); 
      if (e.getSource()==button_cancel) //�����˳���ť
      {this.setVisible(false); } 
      else 
      if (username.equals("")) 
      JOptionPane.showMessageDialog(this, "�������û�����"); 
      else if (password.equals("")) 
      JOptionPane.showMessageDialog(this, "���벻��Ϊ�գ����������룡"); 
      else 
      	if (e.getSource()==button_ok) // ������¼��ť
             { 
      		loginBean login=new loginBean(); 
      		isLogin=login.enter(username,password); 
      		int type = typeBox.getSelectedIndex(); 
      		if(isLogin) 
      		{ 
                 if(login.Flag==type){ 
                    new Menu(type); 
                 }else{ 
                  JOptionPane.showMessageDialog(this, "��¼��ݲ���ȷ!"); 
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
   public static void main(String args[]) throws Exception //���Է���
   {
	new Login(); 
   } 
}  


