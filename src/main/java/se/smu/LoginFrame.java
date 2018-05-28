package se.smu;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;
//import javax.swing.AbstractAction;
//import javax.swing.Action;


public class LoginFrame extends JFrame implements MouseListener {

   public static JPanel mainframe;
   public JTextField textField;
   public JTextField idfield;
   public JTextField passwordfield;
   public JButton okbutton;
   int result = -1;
   
   String id = null;
   String pw = null;
   Connection conn = null;
   Statement stmt = null;
   ResultSet rs = null;
   String sql = null;
   
   
   public static void main(String[] args) {
      EventQueue.invokeLater(new Runnable() {
         public void run() {
            try {
               LoginFrame  frame = new LoginFrame();
               frame.setVisible(true);
            } catch (Exception e) {
               e.printStackTrace();
            }
         }
      });
   }

   public LoginFrame() {
	    
      setTitle("A4's To Do List");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setBounds(100, 100, 466, 571);
      mainframe = new JPanel();
      mainframe.setBorder(new EmptyBorder(5, 5, 5, 5));
      setContentPane(mainframe);
      mainframe.setBackground(Color.WHITE);
      mainframe.setLayout(null);
      
      JLabel lblNewLabel = new JLabel("To do list");
      mainframe.add(lblNewLabel);
      
      
      idfield = new JTextField();
      idfield.setText("ID�� �Է����ּ���.");
      idfield.addMouseListener(this);
      idfield.setFont(new Font("���� ���", Font.PLAIN, 12));
      idfield.setBounds(159, 231, 151, 37);
      mainframe.add(idfield);
      idfield.setColumns(10);
      
      passwordfield = new JPasswordField();
      passwordfield.setText("PW�� �Է����ּ���.");
      passwordfield.addMouseListener(this);
      passwordfield.setFont(new Font("���� ���", Font.PLAIN, 12));
      passwordfield.setColumns(10);
      passwordfield.setBounds(159, 293, 151, 37);
      mainframe.add(passwordfield);
    
      
      JTextArea textArea = new JTextArea();
      textArea.setBounds(103, 283, 4, 24);
      mainframe.add(textArea);
      
      JLabel Idlabel = new JLabel("�� ID ");
      Idlabel.setFont(new Font("���� ���", Font.PLAIN, 18));
      Idlabel.setBounds(75, 231, 57, 31);
      mainframe.add(Idlabel);
      
      JLabel Pwdlabel = new JLabel("�� PW ");
      Pwdlabel.setFont(new Font("���� ���", Font.PLAIN, 18));
      Pwdlabel.setBounds(75, 303, 72, 24);
      mainframe.add(Pwdlabel);
      
      JLabel lblToDoList = new JLabel("To do list");
      lblToDoList.setFont(new Font("���� ���", Font.PLAIN, 26));
      lblToDoList.setBounds(173, 90, 151, 47);
      mainframe.add(lblToDoList);
      
      JLabel label = new JLabel("�α���");
      label.setFont(new Font("���� ���", Font.PLAIN, 20));
      label.setBounds(199, 188, 79, 33);
      mainframe.add(label);
      
      JButton okbutton = new JButton("Ȯ��");
      okbutton.setFont(new Font("���� ���", Font.PLAIN, 14));
      okbutton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
             try {
          	   Class.forName("com.mysql.jdbc.Driver");
          	   // Open a connection
          	   conn = DriverManager.getConnection("jdbc:mysql://localhost/mydb", "root", "root");
          	   System.out.println("����Ǿ����ϴ�.");

          	   stmt = conn.createStatement();
          	   id = idfield.getText();
          	   pw = passwordfield.getText();
          	   sql = "select * from db where id ='" + id + "';";
          	   rs = stmt.executeQuery(sql);
          	   //���̵� ��ϵ��� ���� ���
          	   if(rs.next() == false || (id.isEmpty()) == true) {
          		   JOptionPane.showMessageDialog(null,"��ϵ��� ���� ���̵� �Դϴ�.");
          		   }else {
          			   sql = "select * from (select * from db where id='" + id + "') as T";
          			   rs = stmt.executeQuery(sql); //���� ���� pw�� ������ Ȯ��
          			   while(rs.next() == true) {
          				   if(rs.getString(3).equals(pw))
          					   result = 0; //����
          				   else
          					   result = -1; //�ٸ�
          	    		  }
          			   {
          	    		   if(result == 0) {
          	    			   setVisible(false);
          	    			   AddLecture2 frame = new AddLecture2();
          	    			   frame.setVisible(true); 
          	    		   }
          	    		   else
          	    			 JOptionPane.showMessageDialog(null,"�߸��� ��й�ȣ �Դϴ�.");
          			   }
          	    		  
          	    		  
          	    	  }
          	   }
              catch (Exception ee) {
          		System.out.println("��������");
          		ee.printStackTrace();
          	}
             
             }
      });
   
      okbutton.setBounds(103, 359, 111, 37);
      mainframe.add(okbutton);
      
      JButton joinbutton = new JButton("ȸ������");
      joinbutton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
        	 
                  setVisible(false);
                  JoinMembership frame = new JoinMembership();
                  frame.setVisible(true);
       
            }
      });
      joinbutton.setFont(new Font("���� ���", Font.PLAIN, 14));
      joinbutton.setBounds(238, 359, 111, 37);
      mainframe.add(joinbutton);
      
      JButton findID= new JButton("ID ã��");
      findID.setFont(new Font("���� ���", Font.PLAIN, 12));
      findID.setBounds(120, 422, 79, 23);
      mainframe.add(findID);
      findID.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
              
              FindId frame = new FindId();
              frame.setVisible(true);
   
        }
  });
      
      JButton findPW = new JButton("PW ã��");
      findPW.setFont(new Font("���� ���", Font.PLAIN, 12));
      findPW.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
        	 FindPw frame =new FindPw();
        	 frame.setVisible(true);
         }
      });
      findPW.setBounds(248, 422, 92, 23);
      mainframe.add(findPW);
       
   }

      
   
   
  
   public void mouseClicked(MouseEvent e) {
		// TextField Ŭ���� ���������ֱ�
		   if (e.getSource().equals(idfield)) {
			   idfield.setText("");
			   } else if (e.getSource().equals(passwordfield)){
				   passwordfield.setText("");
			   }
   }	         
		       

   

   @Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
}

	   


