package se.smu;

import javax.swing.*;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class SeeTodo extends JFrame {

	private JPanel contentPane;
	private JTable table;
	JScrollPane scroll;

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

		table = new JTable();
		table.setRowHeight(40);
		table.setModel(new DefaultTableModel(new Object[][] { { null, null, null, null, null },
				{ null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null },
				{ null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null },
				{ null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null },
				{ null, null, null, null, null }, { null, null, null, null, null }, },
				new String[] { "�׸�", "��������", "�ϷῩ��", "����������", "�߿䵵" }));
		scroll = new JScrollPane(table);
		scroll.setBounds(22, 125, 422, 327);

		contentPane.add(scroll);

		JPanel panel = new JPanel();
		panel.setBounds(22, 62, 422, 51);
		panel.setBackground(new Color(128, 128, 128));
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("To do list");
		lblNewLabel.setFont(new Font("���� ���", Font.PLAIN, 20));
		lblNewLabel.setBounds(14, 12, 139, 27);
		panel.add(lblNewLabel);

		JButton button_3 = new JButton("-");
		button_3.setBounds(367, 12, 41, 27);
		panel.add(button_3);
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				SeeSubject frame = new SeeSubject();
				frame.setVisible(true);

			}
		});

		JComboBox<Object> comboBox = new JComboBox<Object>();
		comboBox.setModel(new DefaultComboBoxModel<Object>(new String[] { "�׸�", "��������", "�ϷῩ��", "����������", "�߿䵵" }));
		comboBox.setBounds(251, 12, 106, 27);
		panel.add(comboBox);

		final JButton hidebutton = new JButton("�����");
		hidebutton.setBounds(339, 23, 105, 27);
		contentPane.add(hidebutton);
		hidebutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (hidebutton.getText().equals("�����"))
					hidebutton.setText("���̱�");

				else
					hidebutton.setText("�����");

			}
		});

	}
}