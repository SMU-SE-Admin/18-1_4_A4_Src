package se.smu;

import java.awt.Color;

import javax.swing.*;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.sql.PreparedStatement;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class SeeSubject extends JFrame implements MouseListener {

	public JPanel contentPane;

	public JTable table, table1;
	JScrollPane scroll;
	public JButton button_2;
	String colNames[] = { "과목명", "교수명", "요일", "시간", "년도", "학기" };
	DefaultTableModel SubjectTable = new DefaultTableModel(colNames, 0);
	String colNames2[] = { "항목", "마감기한", "완료여부", "실제마감일", "진행률", "중요도" };
	DefaultTableModel TodoTable = new DefaultTableModel(colNames2, 0);
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	String sql = null;
	PreparedStatement pstmt = null;

	Connection conn2 = null;
	Statement stmt2 = null;
	ResultSet rs2 = null;
	String sql2 = null;
	PreparedStatement pstmt2 = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SeeSubject frame = new SeeSubject();
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
	public SeeSubject() {
		setTitle("To Do List");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 525, 615);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNewButton = new JButton("할 일 전체보기");
		btnNewButton.setBounds(22, 12, 148, 29);
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.setBackground(new Color(224, 255, 255));
		btnNewButton.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		contentPane.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				SeeTodo frame = new SeeTodo();
				frame.setVisible(true);

			}
		});

		JButton button = new JButton("등록");
		button.setBounds(249, 12, 72, 29);
		button.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		contentPane.add(button);

		JButton button_1 = new JButton("수정");
		button_1.setBounds(335, 12, 72, 29);
		button_1.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		contentPane.add(button_1);
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				ModSubject frame = new ModSubject();
				frame.setVisible(true);

			}
		});

		button_2 = new JButton("삭제");
		button_2.setBounds(421, 12, 72, 29);
		button_2.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		contentPane.add(button_2);

		table1 = new JTable(SubjectTable);

		JScrollPane scrollPane = new JScrollPane(table1);
		scrollPane.setBounds(24, 53, 469, 184);
		contentPane.add(scrollPane);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				AddLecture frame = new AddLecture();
				frame.setVisible(true);
			}
		});

		try {

			Class.forName("com.mysql.jdbc.Driver");
			// Open a connection
			conn = DriverManager.getConnection("jdbc:mysql://localhost/mydb", "root", "root");
			stmt = conn.createStatement();

			sql = "select Subjectname,Professor,day,time,year,semester from Subject ;";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			// 중복체크
			while (rs.next()) {
				SubjectTable.addRow(new Object[] { rs.getString("Subjectname"), rs.getString(2), rs.getString(3),
						rs.getString(4), rs.getString(5), rs.getString(6) });

			}
		} catch (Exception ee) {
			System.out.println("문제있음");
			ee.printStackTrace();
		}

		table = new JTable(TodoTable);
		table.addMouseListener(this);

		scroll = new JScrollPane(table);
		scroll.setBounds(22, 304, 471, 213);
		contentPane.add(scroll);

		try {

			Class.forName("com.mysql.jdbc.Driver");
			// Open a connection
			conn2 = DriverManager.getConnection("jdbc:mysql://localhost/mydb", "root", "root");
			stmt2 = conn2.createStatement();
			System.out.println("테이블을 생성했습니다.");

			sql2 = "select Todoname,Deadline,finish,realdeadline,progressrate,importance from Todo ;";
			pstmt2 = conn2.prepareStatement(sql2);
			rs2 = pstmt2.executeQuery();

			// 이미 id 존재할 경우
			while (rs2.next()) {
				
				TodoTable.addRow(new Object[] { rs2.getString(1), rs2.getString(2), rs2.getString(3), rs2.getString(4),
						rs2.getString(5), rs2.getString(6) });

			}
		} catch (Exception ee) {
			System.out.println("문제있음");
			ee.printStackTrace();
		}

		JPanel panel = new JPanel();
		panel.setBounds(22, 241, 471, 51);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("To do list");
		lblNewLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		lblNewLabel.setBounds(14, 12, 139, 27);
		panel.add(lblNewLabel);

		JButton btnNewButton_1 = new JButton("+");
		btnNewButton_1.setBounds(371, 6, 41, 39);
		panel.add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				AddTodo frame;
				try {
					frame = new AddTodo();
					frame.setVisible(true);
				} catch (ParseException e1) {

					e1.printStackTrace();
				}

			}
		});

		JButton button_3 = new JButton("-");
		button_3.setBounds(416, 6, 41, 39);
		panel.add(button_3);

	}

	@Override
	public void mouseClicked(MouseEvent e) {

		Object value_1 = null;
		int row_1 = -1;

		//
		if (e.getSource().equals(table)) {
			row_1 = table.getSelectedRow();
			value_1 = table.getValueAt(row_1, 1);
		}
		// db연결
		try {

			Class.forName("com.mysql.jdbc.Driver");
			// Open a connection
			conn = DriverManager.getConnection("jdbc:mysql://localhost/mydb", "root", "root");
			stmt = conn.createStatement();
			System.out.println("테이블을 생성했습니다.");

			// 등록 버튼
			if (e.getSource().equals(button_2)) {

				if (value_1 == null)
					JOptionPane.showMessageDialog(null, "삭제할 과목이 선택되지 않았습니다.");
				else {
					sql = "select Todoname,deadline,finish,realdeadline,progressrate,importance from Todo where Subjectname='"
							+ value_1 + "';";

					rs = stmt.executeQuery(sql);
					table1.remove(row_1);

				}
			}
		} catch (Exception ee) {
			System.out.println("문제있음");
			ee.printStackTrace();
		}
	}
	// TODO Auto-generated method stub

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}