package se.smu;

import javax.swing.*;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class SeeTodo extends JFrame {

	private JPanel contentPane;
	private JTable table;
	JScrollPane scroll;

	String colNames2[] = { "�׸�", "��������", "�ϷῩ��", "����������", "�����", "�߿䵵" };
	DefaultTableModel AllTodoTable = new DefaultTableModel(colNames2, 0);

	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	String sql = null;
	PreparedStatement pstmt = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SeeTodo frame = new SeeTodo();
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

	public SeeTodo() {
		setTitle("To Do List");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 476, 511);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		table = new JTable(AllTodoTable);
		table.setAutoCreateRowSorter(true);
		scroll = new JScrollPane(table);
		scroll.setBounds(22, 125, 422, 327);

		contentPane.add(scroll);
		try {
			Connection conn = null;

			ResultSet rs = null;
			String sql = null;
			Class.forName("com.mysql.jdbc.Driver");
			// Open a connection
			conn = DriverManager.getConnection("jdbc:mysql://localhost/mydb", "root", "root");
			stmt = conn.createStatement();
			/*
			 * //���̺� ���� ���̸� �ּ� Ǯ�� sql =
			 * "create table Subject(Subjectname varchar(20), Professor varchar(20), day varchar(20) ,time varchar(20), year varchar(20),semester varchar(20), primary key(Subjectname));"
			 * ; stmt.executeUpdate(sql);
			 */
			System.out.println("���̺��� �����߽��ϴ�.");

			sql = "select Todoname,deadline,finish,realdeadline,progressrate,importance  from Todo ;";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			// �̹� id ������ ���
			while (rs.next()) {
				// Object data[] =
				// {rs.getString("Subjectname"),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)};
				AllTodoTable.addRow(new Object[] { rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6) });

			}
		} catch (Exception ee) {
			System.out.println("��������");
			ee.printStackTrace();
		}

		JPanel panel = new JPanel();
		panel.setBounds(22, 62, 422, 51);
		panel.setBackground(new Color(128, 128, 128));
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("To do list");
		lblNewLabel.setFont(new Font("���� ���", Font.PLAIN, 20));
		lblNewLabel.setBounds(14, 12, 139, 27);
		panel.add(lblNewLabel);

		JButton button_3 = new JButton("Ȩ");
		button_3.setBounds(367, 12, 50, 27);
		panel.add(button_3);
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				SeeSubject frame = new SeeSubject();
				frame.setVisible(true);

			}
		});

		JButton hidebutton = new JButton("�����");
		hidebutton.setBounds(339, 23, 105, 27);
		contentPane.add(hidebutton);
		hidebutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (hidebutton.getText().equals("�����")) {
					try {
						Connection conn = null;

						ResultSet rs = null;
						String sql = null;
						Class.forName("com.mysql.jdbc.Driver");
						// Open a connection
						conn = DriverManager.getConnection("jdbc:mysql://localhost/mydb", "root", "root");
						stmt = conn.createStatement();
						/*
						 * //���̺� ���� ���̸� �ּ� Ǯ�� sql =
						 * "create table Subject(Subjectname varchar(20), Professor varchar(20), day varchar(20) ,time varchar(20), year varchar(20),semester varchar(20), primary key(Subjectname));"
						 * ; stmt.executeUpdate(sql);
						 */
						System.out.println("���̺��� �����߽��ϴ�.");

						sql = "select Todoname,deadline,finish,realdeadline,progressrate,importance  from Todo where finish='X' ;";
						pstmt = conn.prepareStatement(sql);
						rs = pstmt.executeQuery();
						AllTodoTable.setNumRows(0);

						// �̹� id ������ ���
						while (rs.next()) {
							// Object data[] =
							// {rs.getString("Subjectname"),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)};
							AllTodoTable.addRow(new Object[] { rs.getString(1), rs.getString(2), rs.getString(3),
									rs.getString(4), rs.getString(5), rs.getString(6) });

						}

					} catch (Exception ee) {
						System.out.println("��������");
						ee.printStackTrace();
					}

					hidebutton.setText("���̱�");

				} else if (hidebutton.getText().equals("���̱�")) {
					try {
						Connection conn = null;

						ResultSet rs = null;
						String sql = null;
						Class.forName("com.mysql.jdbc.Driver");
						// Open a connection
						conn = DriverManager.getConnection("jdbc:mysql://localhost/mydb", "root", "root");
						stmt = conn.createStatement();
						/*
						 * //���̺� ���� ���̸� �ּ� Ǯ�� sql =
						 * "create table Subject(Subjectname varchar(20), Professor varchar(20), day varchar(20) ,time varchar(20), year varchar(20),semester varchar(20), primary key(Subjectname));"
						 * ; stmt.executeUpdate(sql);
						 */
						System.out.println("���̺��� �����߽��ϴ�.");

						sql = "select Todoname,deadline,finish,realdeadline,progressrate,importance  from Todo  ;";
						pstmt = conn.prepareStatement(sql);
						rs = pstmt.executeQuery();
						AllTodoTable.setNumRows(0);

						// �̹� id ������ ���
						while (rs.next()) {
							// Object data[] =
							// {rs.getString("Subjectname"),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)};
							AllTodoTable.addRow(new Object[] { rs.getString(1), rs.getString(2), rs.getString(3),
									rs.getString(4), rs.getString(5), rs.getString(6) });

						}

					} catch (Exception ee) {
						System.out.println("��������");
						ee.printStackTrace();
					}
					hidebutton.setText("�����");
				}

			}

		});

	}
}