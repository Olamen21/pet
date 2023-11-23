package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AdminLogin extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JPasswordField PasswordTb;
	private JButton LoginBtn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminLogin frame = new AdminLogin();
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
	public AdminLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 593, 308);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setLocationRelativeTo(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(100, 149, 237));
		panel.setBounds(0, 0, 579, 73);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblName_1_1 = new JLabel("Admin Login");
		lblName_1_1.setForeground(new Color(255, 255, 255));
		lblName_1_1.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblName_1_1.setBounds(202, 24, 172, 39);
		panel.add(lblName_1_1);
		
		JLabel lblName_1 = new JLabel("Heaven Care Pet Shop");
		lblName_1.setForeground(new Color(30, 144, 255));
		lblName_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblName_1.setBounds(168, 83, 237, 32);
		contentPane.add(lblName_1);
		
		JLabel lblName = new JLabel("Password");
		lblName.setForeground(new Color(30, 144, 255));
		lblName.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblName.setBounds(53, 141, 104, 32);
		contentPane.add(lblName);
		
		PasswordTb = new JPasswordField();
		PasswordTb.setFont(new Font("Tahoma", Font.PLAIN, 16));
		PasswordTb.setBounds(168, 138, 237, 32);
		contentPane.add(PasswordTb);
		
		LoginBtn = new JButton("Login");
		LoginBtn.setForeground(new Color(30, 144, 255));
		LoginBtn.setFont(new Font("Tahoma", Font.BOLD, 16));
		LoginBtn.setBackground(Color.WHITE);
		LoginBtn.setBounds(238, 193, 97, 32);
		contentPane.add(LoginBtn);
		
		JLabel lblBack = new JLabel("Back");
		lblBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new Login().setVisible(true);
				AdminLogin.this.dispose();
			}
		});
		lblBack.setForeground(new Color(30, 144, 255));
		lblBack.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblBack.setBounds(266, 235, 57, 32);
		contentPane.add(lblBack);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(LoginBtn)) {
			LoginClick();
		} 
	}
	
	private void LoginClick() {
		if(PasswordTb.getText().isEmpty()) {
			JOptionPane.showMessageDialog(AdminLogin.this, "Enter Admin Password!!!");
		} else {
			new Users().setVisible(true);
			this.dispose();
		}
	}
}
