package se.smu;
//import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.DriverManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.awt.event.MouseAdapter;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.sql.Connection;
//import java.sql.DriverManager;
import java.sql.ResultSet;
//import java.sql.SQLException;
import java.sql.Statement;

public class FindPw extends JFrame implements MouseListener {

   public JPanel contentPane;
   public JTextField namefield, idfield;

   /**
    * Launch the application.
    */
   public static void main(String[] args) {
      EventQueue.invokeLater(new Runnable() {
         public void run() {
            try {
               FindPw frame = new FindPw();
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
   public FindPw() {

      setTitle("To Do List");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setBounds(100, 100, 473, 608);
      contentPane = new JPanel();
      contentPane.setBackground(Color.WHITE);
      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
      setContentPane(contentPane);
      contentPane.setLayout(null);

      JLabel lblNewLabel = new JLabel("To do List");
      lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
      lblNewLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 40));
      lblNewLabel.setBounds(10, 20, 455, 50);
      contentPane.add(lblNewLabel);
      JLabel lb2NewLabel = new JLabel("● 비밀번호 찾기");
      lb2NewLabel.setHorizontalAlignment(SwingConstants.LEFT);
      lb2NewLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
      lb2NewLabel.setBounds(30, 80, 455, 50);
      contentPane.add(lb2NewLabel);
      JLabel lb3NewLabel = new JLabel("● 이름");
      lb3NewLabel.setHorizontalAlignment(SwingConstants.LEFT);
      lb3NewLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
      lb3NewLabel.setBounds(80, 180, 455, 50);
      contentPane.add(lb3NewLabel);
      namefield = new JTextField();
      namefield.setText("이름을 입력하세요.");
      namefield.setBounds(150, 180, 170, 50);
      namefield.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
      contentPane.add(namefield);
      JLabel lb4NewLabel = new JLabel("● 아이디");
      lb4NewLabel.setHorizontalAlignment(SwingConstants.LEFT);
      lb4NewLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
      lb4NewLabel.setBounds(50, 230, 455, 50);
      contentPane.add(lb4NewLabel);
      namefield.addMouseListener(this);
      idfield = new JTextField();
      idfield.addMouseListener(this);
      idfield.setText("아이디를 입력하세요.");
      idfield.setBounds(150, 230, 170, 50);
      idfield.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
      contentPane.add(idfield);
      final JButton btn1 = new JButton("확인");
      btn1.setBounds(110, 330, 90, 50);
      btn1.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
      contentPane.add(btn1);
      JButton btn2 = new JButton("취소");
      btn2.setBounds(220, 330, 90, 50);
      btn2.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
      contentPane.add(btn2);
      btn2.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            setVisible(false);
            LoginFrame frame = new LoginFrame();
            frame.setVisible(true);
         }
      });

      btn1.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {

            try {
               Connection conn = null;
               Statement stmt = null;
               ResultSet rs = null;
               String sql = null;

               Class.forName("com.mysql.jdbc.Driver");
               // Open a connection
               conn = DriverManager.getConnection("jdbc:mysql://localhost/mydb", "root", "root");
               stmt = conn.createStatement();
               
   

               // 등록 버튼
               if (e.getSource().equals(btn1)) {
                  sql = "select pw from db where name='" + namefield.getText() + "' and id='" + idfield.getText()
                        + "';";

                  rs = stmt.executeQuery(sql);

                  // 이미 id 존재할 경우
                  if (rs.next() == true) {
                     JOptionPane.showMessageDialog(null, rs.getString(1));
                     setVisible(false);
                     LoginFrame frame = new LoginFrame();
                     frame.setVisible(true);
                  } else if ((namefield.getText().isEmpty()) == true || (idfield.getText().isEmpty()) == true) {
                     JOptionPane.showMessageDialog(null, "비어 있는 칸이 존재합니다.");
                  }

                  else {
                     JOptionPane.showMessageDialog(null, "등록되지 않은 사용자입니다.");
                  }
               }
            } catch (Exception ee) {
               System.out.println("문제있음");
               ee.printStackTrace();
            }
         }

      });
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

   @Override
   public void mouseClicked(MouseEvent e) {
      if (e.getSource().equals(namefield)) {
         namefield.setText("");
      } else if (e.getSource().equals(idfield)) {
         idfield.setText("");
      }
   }

}