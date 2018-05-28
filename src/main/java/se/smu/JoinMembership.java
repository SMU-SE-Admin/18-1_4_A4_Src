package se.smu;
import java.util.*;
//import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
//import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseAdapter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.awt.event.ActionEvent;
//import javax.swing.JTable;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
//import javax.swing.border.TitledBorder;
import java.awt.Font;
import javax.swing.SwingConstants;
//import javax.swing.AbstractAction;
//import javax.swing.Action;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

class Membership{
         String id;
         String password;
         public Membership(String id,String password) {
               
               this.id=id;
               this.password=password;
         }
}
public class JoinMembership extends JFrame implements MouseListener {

   public JPanel contentPane;
   public JTextField txtName, txtId, txtPw;
   public JButton btnNewButton;
   public JButton btnNewButton_1;
   
   
   

   HashMap<String,Membership> map = new HashMap<String,Membership>();
   
   public static void main(String[] args) {
      EventQueue.invokeLater(new Runnable() {
         public void run() {
            try {
               JoinMembership frame = new JoinMembership();
               frame.setVisible(true);
            } catch (Exception e) {
               e.printStackTrace();
            }
         }
      });
   }

   public JoinMembership() {
      setTitle("A4's To Do List");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setBounds(100, 100, 487, 651);
      contentPane = new JPanel();
      contentPane.setBackground(Color.WHITE);
      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
      setContentPane(contentPane);
      contentPane.setLayout(null);
      
      txtId = new JTextField();
      txtId.addMouseListener(this);
      txtId.setText("");
      txtId.setBounds(201, 339, 173, 24);
      contentPane.add(txtId);
      txtId.setColumns(10);
      
      JLabel lblNewLabel = new JLabel("회원가입");
      lblNewLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 40));
      lblNewLabel.setBounds(150, 113, 173, 41);
      contentPane.add(lblNewLabel);
      
      txtName = new JTextField();
      txtName.addMouseListener(this);
      txtName.setText("");
      txtName.setBounds(201, 287, 173, 24);
      contentPane.add(txtName);
      txtName.setColumns(10);
      
      txtPw = new JPasswordField();
      txtPw.addMouseListener(this);
      txtPw.setHorizontalAlignment(SwingConstants.LEFT);
      txtPw.setText("");
      txtPw.setBounds(201, 387, 173, 24);
      txtPw.selectAll();  
      contentPane.add(txtPw);
      txtPw.setColumns(10);
      
      JLabel lblNewLabel_1 = new JLabel("ID");
      lblNewLabel_1.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
      lblNewLabel_1.setBounds(128, 340, 15, 18);
      contentPane.add(lblNewLabel_1);
      
      JLabel lblNewLabel_2 = new JLabel("이름");
      lblNewLabel_2.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
      lblNewLabel_2.setBounds(119, 288, 35, 18);
      contentPane.add(lblNewLabel_2);
      
      JLabel lblNewLabel_3 = new JLabel("PW");
      lblNewLabel_3.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
      lblNewLabel_3.setBounds(119, 388, 24, 18);
      contentPane.add(lblNewLabel_3);
      
      btnNewButton = new JButton("등록");
      btnNewButton.addMouseListener(this);
      btnNewButton.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
      btnNewButton.setBounds(117, 484, 105, 27);
      contentPane.add(btnNewButton);
      
      btnNewButton_1 = new JButton("취소");
      btnNewButton_1.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            setVisible(false);
            LoginFrame frame= new LoginFrame();
            frame.setVisible(true);
         
         }
      });
      btnNewButton_1.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
      btnNewButton_1.setBounds(269, 484, 105, 27);
      contentPane.add(btnNewButton_1);
      
   }
   Connection conn = null;
   Statement stmt = null;
   ResultSet rs = null;
   String sql = null;
   
   @Override
   public void mouseClicked(MouseEvent e) {
	// TextField 클릭시 예시지워주기
	   if (e.getSource().equals(txtName)) {
		   txtName.setText("");
		   } else if (e.getSource().equals(txtId)){
				txtId.setText("");
			} else if (e.getSource().equals(txtPw)) {
				txtPw.setText("");
			}
	// db연결   
   try {
	   
 	 Class.forName("com.mysql.jdbc.Driver");
      // Open a connection
      conn = DriverManager.getConnection("jdbc:mysql://localhost/mydb", "root", "root");
      stmt = conn.createStatement();
      //테이블 생성 전이면 주석 풀기
      sql = "create table DB(name varchar(20), id varchar(20) primary key, pw varchar(20) not null);";
      stmt.executeUpdate(sql);
      System.out.println("테이블을 생성했습니다.");
      
      //등록 버튼
      if(e.getSource().equals(btnNewButton)) {
    	  sql = "select * from DB where id ='" + txtId.getText() + "';";
    	  
    	  rs = stmt.executeQuery(sql);
    	  
    	  //이미 id 존재할 경우
    	  if(rs.next() == true) {
    		  JOptionPane.showMessageDialog(null,"이미 존재하는 아이디 입니다.");
    	  }else if ((txtName.getText().isEmpty()) == true || (txtId.getText().isEmpty()) == true || (txtPw.getText().isEmpty()) == true) {
    		  JOptionPane.showMessageDialog(null,"비어있는 칸이 존재합니다.");  
    	  }else {
    		  
    		  sql = "insert into DB values('" + txtName.getText() + "','" + txtId.getText() + "','" + txtPw.getText() + "');";
    		  
    		  stmt.executeUpdate(sql);
    		  JOptionPane.showMessageDialog(null,"회원 가입이 완료되었습니다.");
    		  setVisible(false);
              LoginFrame frame= new LoginFrame();
              frame.setVisible(true);
    	  }
      }
   } catch (Exception ee) {
		System.out.println("문제있음");
		ee.printStackTrace();
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

   
    	  
