package se.smu;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;
import java.util.*;

@SuppressWarnings("serial")
public class ModTodo extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_2;
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	String sql = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {

				try {
					AddTodo frame = new AddTodo();
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
	}

	public static String[] calendar(String args[]) throws ParseException {
		DateFormat transFormat = new SimpleDateFormat("yyyy.MM.dd");

		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c2.add(Calendar.MONTH, +2);

		Date d1 = new Date();
		Date d2 = c2.getTime();

		c1.setTime(d1);
		c2.setTime(d2);

		String[] array;
		array = new String[31];

		int i = 0;
		while (c1.compareTo(c2) != 1) {

			String to = transFormat.format(c1.getTime());
			array[i] = to;

			c1.add(Calendar.DATE, 1);
			i++;
			if (i == 31)
				break;
		}

		return array;
	}

	public ModTodo() throws ParseException {
		setTitle("To Do List");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 534);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(65, 61, 305, 378);
		contentPane.add(panel);
		panel.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(14, 63, 258, 25);
		panel.add(panel_1);
		panel_1.setLayout(null);

		String[] day;
		day = new String[31];
		String[] today = calendar(day);

		JLabel label = new JLabel("항목");
		label.setBounds(14, 12, 88, 39);
		label.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		panel.add(label);

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(0, 0, 146, 24);
		panel_1.add(textField_2);

		JLabel subject = new JLabel("과목명");
		subject.setBounds(14, 100, 73, 27);
		subject.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		panel.add(subject);

		textField = new JTextField();
		textField.setBounds(126, 105, 146, 24);
		panel.add(textField);
		textField.setColumns(10);

		JLabel deadline = new JLabel("마감기한");
		deadline.setBounds(14, 139, 88, 27);
		deadline.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		panel.add(deadline);

		final JComboBox<Object> deadlinecomboBox = new JComboBox<Object>(today);
		deadlinecomboBox.setBounds(126, 144, 146, 24);
		panel.add(deadlinecomboBox);

		JLabel realdeadline = new JLabel("실제마감일");
		realdeadline.setBounds(14, 178, 100, 27);
		realdeadline.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		panel.add(realdeadline);

		final JComboBox<Object> realdeadlinecomboBox = new JComboBox<Object>(today);
		realdeadlinecomboBox.setBounds(126, 183, 146, 24);
		panel.add(realdeadlinecomboBox);

		JLabel label_2 = new JLabel("중요도");
		label_2.setBounds(14, 218, 88, 27);
		label_2.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		panel.add(label_2);

		final JComboBox<Object> importancecomboBox = new JComboBox<Object>();
		importancecomboBox.setModel(new DefaultComboBoxModel<Object>(new String[] { "1", "2", "3", "4", "5" }));
		importancecomboBox.setBounds(126, 223, 146, 24);
		panel.add(importancecomboBox);

		JLabel lblNewLabel_3 = new JLabel("완료여부");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(41, 257, 91, 24);
		panel.add(lblNewLabel_3);

		final JComboBox<Object> completecomboBox = new JComboBox<Object>();
		completecomboBox.setModel(new DefaultComboBoxModel<Object>(new String[] { "X", "O" }));
		completecomboBox.setBounds(41, 293, 91, 24);
		panel.add(completecomboBox);

		JLabel percentlabel = new JLabel("진행률");
		percentlabel.setHorizontalAlignment(SwingConstants.CENTER);
		percentlabel.setBounds(167, 259, 91, 24);
		panel.add(percentlabel);

		final JComboBox<Object> percentcomboBox = new JComboBox<Object>();
		percentcomboBox.setModel(new DefaultComboBoxModel<Object>(new String[] { "0%", "25%", "50%", "75%", "100%" }));
		percentcomboBox.setBounds(167, 293, 91, 24);
		panel.add(percentcomboBox);

		final JButton okbutton = new JButton("저장");
		okbutton.setBounds(41, 339, 91, 27);
		okbutton.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		panel.add(okbutton);

		okbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (textField.getText().equals(""))
					JOptionPane.showMessageDialog(null, "과목명을 기입해주세요");
				else {
					try {
						Class.forName("com.mysql.jdbc.Driver");
						// Open a connection
						conn = DriverManager.getConnection("jdbc:mysql://localhost/mydb", "root", "root");
						stmt = conn.createStatement();

						if (e.getSource().equals(okbutton)) {
							sql = "select * from Todo where Subjectname ='" + textField.getText() + "';";

							rs = stmt.executeQuery(sql);

							if ((textField.getText().isEmpty()) == true || (textField_2.getText().isEmpty()) == true) {
								JOptionPane.showMessageDialog(null, "비어있는 칸이 존재합니다.");
							} else {

								sql = "Update Todo Set Todoname='" + textField_2.getText() + "',Subjectname='"
										+ textField.getText() + "',deadline='" + deadlinecomboBox.getSelectedItem()
										+ "',readldeadline='" + realdeadlinecomboBox.getSelectedItem()
										+ "',importance='" + importancecomboBox.getSelectedItem() + "',finish='"
										+ completecomboBox.getSelectedItem() + "',progressrate='"
										+ percentcomboBox.getSelectedItem() + "';";

								stmt.executeUpdate(sql);
								JOptionPane.showMessageDialog(null, "할일 수정이 완료 되었습니다.");
								setVisible(false);
								SeeSubject frame = new SeeSubject();
								frame.setVisible(true);
							}
						}
					} catch (Exception ee) {
						System.out.println("문제있음");
						ee.printStackTrace();
					}
				}
			}

		});

		JButton cancelButton = new JButton("취소");
		cancelButton.setBounds(167, 339, 91, 27);
		cancelButton.setFont(new Font("맑은 고딕", Font.PLAIN, 20));

		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				SeeSubject frame = new SeeSubject();
				frame.setVisible(true);

			}
		});
		panel.add(cancelButton);

	}
}