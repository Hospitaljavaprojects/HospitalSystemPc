import java.awt.*;  
import java.awt.event.*; 
import javax.swing.*; 
import java.sql.*; 
public class Menu extends JFrame implements ActionListener 
{
   Dimension faceSize = new Dimension(450, 550); 
   JPanel  contentPane; 
   JButton buttonAdmi; 
   JButton buttonInvalid; 
   JButton buttonDoctor; 
   JButton buttonPharmacist; 
   JButton buttonDean; 
   JButton buttonExit; 
   int type;
  public Menu(int type) { 
   this.type=type; 
   this.setDefaultCloseOperation(EXIT_ON_CLOSE); 
   this.setSize(800,500); 
   this.setTitle("ҽԺ����ϵͳ"); 
   this.setLocation(400,100); 
   buttonAdmi=new JButton("����Ա"); 
   buttonInvalid=new JButton("�շ���Ա"); 
   buttonDoctor=new JButton("ҽ��"); 
   buttonPharmacist=new JButton("ҩʦ"); 
   buttonDean=new JButton("Ժ��"); 
   buttonExit=new JButton("�˳�ϵͳ"); 
   JPanel panel1=new JPanel(); 
   panel1.add(buttonAdmi); 
   JPanel panel2=new JPanel(); 
   panel2.add(buttonInvalid); 
   JPanel panel3=new JPanel(); 
   panel3.add(buttonDoctor); 
   JPanel panel4=new JPanel(); 
   panel4.add(buttonPharmacist); 
   JPanel panel5=new JPanel(); 
   panel5.add(buttonDean);  
   JPanel panel6=new JPanel(); 
   panel6.add(buttonExit);  
   ImageIcon firstpage=new ImageIcon("abc.PNG"); 
   this.getContentPane().setLayout(new BorderLayout()); 
   JPanel panel = new JPanel(); 
   panel.add(panel1); 
   panel.add(panel2); 
   panel.add(panel3); 
   panel.add(panel4); 
   panel.add(panel5);  
   panel.add(panel6);  
   this.getContentPane().add(panel,BorderLayout.NORTH);  
   this.getContentPane().add(new JLabel(firstpage),BorderLayout.CENTER); 
   buttonAdmi.addActionListener(this); 
   buttonInvalid.addActionListener(this); 
   buttonDoctor.addActionListener(this);  
   buttonPharmacist.addActionListener(this); 
   buttonDean.addActionListener(this); 
   buttonExit.addActionListener(this); 
   //�رճ���ʱ�Ĳ���
   this.addWindowListener(  new WindowAdapter() 
                            { 
	                        public void windowClosing(WindowEvent e){  
	                        	System.exit(0); 
	                        	}
     }); 
      this.setVisible(true); 
  }  
    public void actionPerformed(ActionEvent e) 
{  
    	if(e.getSource()==buttonAdmi) 
        {  
    		new Adminisrtator(null,type); 
        }
         if(e.getSource()==buttonInvalid) 
        {  
        	 new Invalid(null,type);
        }
       if(e.getSource()==buttonDoctor)
       { 
    	   new Doctor(null,type); 
       } 
       if(e.getSource()==buttonPharmacist)
       { 
    	   new Pharmacist(null,type); 
       }
       if(e.getSource()==buttonDean)
       { 
    	   new Dean(null,type); 
       }
       if(e.getSource()==buttonExit) 
       { 
    	   JOptionPane.showConfirmDialog(null, "��ȷ���˳�ϵͳ��","��Ϣ",JOptionPane.YES_NO_OPTION); 
    	   System.exit(0); 
       } 
   } 
   public static void main(String []args) 
   { 
	   new Menu(1); 
   }
}  
