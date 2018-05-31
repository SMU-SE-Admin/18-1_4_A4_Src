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
	int row, row2;
	String value_1, value_2;
	private JTable table, table1;
	JScrollPane scroll;
	public JButton button_2, button_3;
	String colNames[] = { "�����", "������", "����", "�ð�", "�⵵", "�б�" };
	DefaultTableModel SubjectTable = new DefaultTableModel(colNames, 0);
	String colNames2[] = { "�׸�", "��������", "�ϷῩ��", "����������", "�����", "�߿䵵" };
	DefaultTableModel TodoTable = new DefaultTableModel(colNames2, 0);
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

		JButton btnNewButton = new JButton("�� �� ��ü����");
		btnNewButton.setBounds(22, 12, 148, 29);
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.setBackground(new Color(224, 255, 255));
		btnNewButton.setFont(new Font("���� ���", Font.PLAIN, 15));
		contentPane.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				SeeTodo frame = new SeeTodo();
				frame.setVisible(true);

			}
		});

		JButton button = new JButton("���");
		button.setBounds(249, 12, 72, 29);
		button.setFont(new Font("���� ���", Font.PLAIN, 15));
		contentPane.add(button);

		JButton button_1 = new JButton("����");
		button_1.setBounds(335, 12, 72, 29);
		button_1.setFont(new Font("���� ���", Font.PLAIN, 15));
		contentPane.add(button_1);
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				ModSubject frame = new ModSubject();
				frame.setVisible(true);

			}
		});

		button_2 = new JButton("����");
		button_2.setBounds(421, 12, 72, 29);
		button_2.setFont(new Font("���� ���", Font.PLAIN, 15));
		contentPane.add(button_2);

		table = new JTable(TodoTable);

		table1 = new JTable(SubjectTable);
		table.addMouseListener(this);
		JScrollPane scrollPane = new JScrollPane(table1);
		scrollPane.setBounds(24, 53, 469, 184);
		contentPane.add(scrollPane);
		table1.addMouseListener(this);

		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.jdbc.Driver");
					// Open a connection
					conn = DriverManager.getConnection("jdbc:mysql://localhost/mydb", "root", "root");
					stmt = conn.createStatement();

					if (value_1 != null) {
						sql = "delete from Subject where Subjectname='" + value_1 + "';";
						stmt.executeUpdate(sql);
						JOptionPane.showMessageDialog(null, "�����Ǿ����ϴ�.");
						SubjectTable.removeRow(row);
						value_1 = null;

					} else {
						JOptionPane.showMessageDialog(null, "������ ������ ���õ��� �ʾҽ��ϴ�.");
						System.out.print(value_1);

					}

				} catch (Exception ee) {
					System.out.println("��������");
					ee.printStackTrace();
				}

			}

		});
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				AddLecture frame = new AddLecture();
				frame.setVisible(true);
			}
		});
		Connection conn = null;

		ResultSet rs = null;
		String sql = null;
		PreparedStatement pstmt = null;
		try {

			Class.forName("com.mysql.jdbc.Driver");
			// Open a connection
			conn = DriverManager.getConnection("jdbc:mysql://localhost/mydb", "root", "root");
			stmt = conn.createStatement();

			System.out.println("���̺��� �����߽��ϴ�.");

			sql = "select Subjectname,Professor,day,time,year,semester from Subject ;";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			// �̹� id ������ ���
			while (rs.next()) {

				SubjectTable.addRow(new Object[] { rs.getString("Subjectname"), rs.getString(2), rs.getString(3),
						rs.getString(4), rs.getString(5), rs.getString(6) });

			}
		} catch (Exception ee) {
			System.out.println("��������");
			ee.printStackTrace();
		}

		scroll = new JScrollPane(table);
		scroll.setBounds(22, 304, 471, 213);
		contentPane.add(scroll);

		JPanel panel = new JPanel();
		panel.setBounds(22, 241, 471, 51);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("To do list");
		lblNewLabel.setFont(new Font("���� ���", Font.PLAIN, 20));
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

		button_3 = new JButton("-");
		button_3.setBounds(416, 6, 41, 39);

		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection conn = null;
				Statement stmt = null;

				String sql = null;

				try {
					Class.forName("com.mysql.jdbc.Driver");
					// Open a connection
					conn = DriverManager.getConnection("jdbc:mysql://localhost/mydb", "root", "root");
					stmt = conn.createStatement();
					System.out.println("���̺��� �����߽��ϴ�.");
					if (value_2 != null) {
						sql = "delete from Todo where Todoname='" + value_2 + "';";
						stmt.executeUpdate(sql);
						JOptionPane.showMessageDialog(null, "�����Ǿ����ϴ�.");
						TodoTable.removeRow(row2);
						value_2 = null;

					} else {
						JOptionPane.showMessageDialog(null, "������ �׸��� ���õ��� �ʾҽ��ϴ�.");
						System.out.print(value_2);

					}

				} catch (Exception ee) {
					System.out.println("��������");
					ee.printStackTrace();
				}

			}

		});

		panel.add(button_3);
		JButton btnNewButton_2 = new JButton("����");
		btnNewButton_2.setBounds(310, 6, 60, 39);
		panel.add(btnNewButton_2);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				ModTodo frame;
				try {
					frame = new ModTodo();
					frame.setVisible(true);
				} catch (ParseException e1) {

					e1.printStackTrace();
				}

			}
		});

	}

	public void mouseClicked(MouseEvent e) {
		if (e.getSource().equals(table1)) {
			row = table1.getSelectedRow();
			value_1 = (String) table1.getValueAt(row, 0);
		} else {

			row2 = table.getSelectedRow();
			value_2 = (String) table.getValueAt(row2, 0);
		}
		Connection conn = null;

		ResultSet rs = null;
		String sql = null;
		PreparedStatement pstmt = null;
		try {

			Class.forName("com.mysql.jdbc.Driver");
			// Open a connection
			conn = DriverManager.getConnection("jdbc:mysql://localhost/mydb", "root", "root");
			stmt = conn.createStatement();

			sql = "select Todoname,deadline,finish,realdeadline,progressrate,importance from Todo where Subjectname='"
					+ value_1 + "' ;";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			TodoTable.setNumRows(0);

			// �̹� id ������ ���
			while (rs.next()) {

				TodoTable.addRow(new Object[] { rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6) });

			}

		} catch (Exception ee) {
			System.out.println("��������");
			ee.printStackTrace();
		}

	}

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