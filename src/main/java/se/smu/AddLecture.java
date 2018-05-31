package se.smu;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;

import java.awt.Color;

@SuppressWarnings("serial")
public class AddLecture extends JFrame {

   private JPanel contentPane;
   private JTextField textField;
   private JTextField textField_1;

   /**
    * Launch the application.
    */
   public static void main(String[] args) {
      EventQueue.invokeLater(new Runnable() {
         public void run() {
            try {
               AddLecture frame = new AddLecture();
               frame.setVisible(true);
            } catch (Exception e) {
               e.printStackTrace();
            }
         }
      });
   }

   /**
    * Create the frame.
    */
   public AddLecture() {
      setTitle("To Do List");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setBounds(100, 100, 450, 534);
      contentPane = new JPanel();
      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
      setContentPane(contentPane);
      contentPane.setLayout(null);
      
      JPanel panel = new JPanel();
      panel.setBackground(Color.WHITE);
      panel.setBounds(65, 61, 305, 340);
      contentPane.add(panel);
      panel.setLayout(null);
      
      JLabel lblNewLabel = new JLabel("������");
      lblNewLabel.setFont(new Font("���� ���", Font.PLAIN, 20));
      lblNewLabel.setBounds(14, 12, 88, 39);
      panel.add(lblNewLabel);
      
      JLabel lblNewLabel_1 = new JLabel("�����");
      lblNewLabel_1.setFont(new Font("���� ���", Font.PLAIN, 20));
      lblNewLabel_1.setBounds(14, 100, 73, 27);
      panel.add(lblNewLabel_1);
      
      JLabel label = new JLabel("������");
      label.setFont(new Font("���� ���", Font.PLAIN, 20));
      label.setBounds(14, 139, 73, 27);
      panel.add(label);
      
      JLabel label_1 = new JLabel("����/�ð�");
      label_1.setFont(new Font("���� ���", Font.PLAIN, 20));
      label_1.setBounds(14, 178, 88, 27);
      panel.add(label_1);
      
      JLabel label_2 = new JLabel("�⵵/�б�");
      label_2.setFont(new Font("���� ���", Font.PLAIN, 20));
      label_2.setBounds(14, 218, 88, 27);
      panel.add(label_2);
      
      final JComboBox<Object> comboBox_2 = new JComboBox<Object>();
      comboBox_2.setModel(new DefaultComboBoxModel<Object>(new String[]{"��","ȭ","��","��","��","��","��"}));
      comboBox_2.setBounds(116, 183, 65, 24);
      panel.add(comboBox_2);
      
      JButton btnNewButton = new JButton("���");
      btnNewButton.setFont(new Font("���� ���", Font.PLAIN, 20));
      btnNewButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent arg0) {
            setVisible(false);
            SeeSubject frame = new SeeSubject();
            frame.setVisible(true);
            
            
         }
      });
      btnNewButton.setBounds(172, 275, 91, 27);
      panel.add(btnNewButton);
      
      final JButton button = new JButton("Ȯ��");
      button.setFont(new Font("���� ���", Font.PLAIN, 20));
      button.setBounds(54, 275, 91, 27);
      panel.add(button);
  
      textField = new JTextField();
      textField.setBounds(118, 105, 154, 24);
      panel.add(textField);
      textField.setColumns(10);
      
      textField_1 = new JTextField();
      textField_1.setColumns(10);
      textField_1.setBounds(118, 144, 154, 24);
      panel.add(textField_1);
      
      
      final JComboBox<Object> comboBox = new JComboBox<Object>();
      comboBox.setModel(new DefaultComboBoxModel<Object>(new String[]{"9:00","10:00","11:00","12:00","13:00","14:00","15:00","16:00","17:00","18:00","19:00",}));
      
      comboBox.setBounds(207, 183, 65, 24);
      panel.add(comboBox);
      
      final JComboBox<Object> comboBox_1 = new JComboBox<Object>();
      comboBox_1.setModel(new DefaultComboBoxModel<Object>(new String[]{"2018","2019","2020","2021"}));
      comboBox_1.setBounds(116, 223, 65, 24);
      panel.add(comboBox_1);
      
      final JComboBox<Object> comboBox_3 = new JComboBox<Object>();
      comboBox_3.setModel(new DefaultComboBoxModel<Object>(new String[]{"1�б�","2�б�"}));
      comboBox_3.setBounds(207, 223, 65, 24);
      panel.add(comboBox_3);
      button.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
      Connection conn = null;
      Statement stmt = null;
      ResultSet rs = null;
      String sql = null;
      try {
          
    	     Class.forName("com.mysql.jdbc.Driver");
    	      // Open a connection
    	      conn = DriverManager.getConnection("jdbc:mysql://localhost/mydb", "root", "root");
    	      stmt = conn.createStatement();
    	      //���̺� ����
    	      sql = "create table Subject(Subjectname varchar(20), Professor varchar(20), day varchar(20) ,time varchar(20), year varchar(20),semester varchar(20), primary key(Subjectname));";
    	      stmt.executeUpdate(sql);
    	      System.out.println("���̺��� �����߽��ϴ�.");
    	      
    	      //��� ��ư
    	      if(e.getSource().equals(button)) {
    	         sql = "select * from Subject where Subjectname ='" + textField.getText() + "';";
    	         
    	         rs = stmt.executeQuery(sql);
    	         
    	         //�̹� id ������ ���
    	         if(rs.next() == true) {
    	            JOptionPane.showMessageDialog(null,"�̹� ����� �����Դϴ�.");
    	         }else if ((textField.getText().isEmpty()) == true || (textField_1.getText().isEmpty()) == true){
    	            JOptionPane.showMessageDialog(null,"����ִ� ĭ�� �����մϴ�.");  
    	         }else {
    	            
    	            sql = "insert into Subject values('" + textField.getText() + "','" + textField_1.getText() + "','" 
    	            + comboBox_2.getSelectedItem() + "','" + comboBox.getSelectedItem() + "','"
    	            +comboBox_1.getSelectedItem()+ "','" +comboBox_3.getSelectedItem()+"');";
    	            
    	            stmt.executeUpdate(sql);
    	            JOptionPane.showMessageDialog(null,"������ ��ϵǾ����ϴ�.");
    	            
    	            setVisible(false);
    	              SeeSubject frame= new SeeSubject();
    	              frame.setVisible(true);
    	            	         
    	            	      }
    	      }
      }  
    	            
    	 
    	      
    	         
    	      
    	    catch (Exception ee) {
    	      System.out.println("��������");
    	      ee.printStackTrace();
    	   }
          }
      
      });
   }
}

      
     
      
   
   
   

