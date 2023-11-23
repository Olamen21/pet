package view;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Login extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField UNameTb;
	private JPasswordField PasswordTb;
	private JButton LoginBtn;
	Connection Con = null;
	PreparedStatement pst = null;
	ResultSet Rs = null;
	Statement St = null;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		this.init();
		LoginBtn.addActionListener(this);
	}

	public void init() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 565, 369);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setLocationRelativeTo(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(100, 149, 237));
		panel.setBounds(0, 0, 220, 332);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lbldog = new JLabel("");
		lbldog.setBounds(68, 10, 72, 85);
		lbldog.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(Login.class.getResource("dog.png"))));
		panel.add(lbldog);
		
		JLabel lblName_2_1 = new JLabel("Best Place");
		lblName_2_1.setForeground(new Color(255, 255, 255));
		lblName_2_1.setFont(new Font("Tahoma", Font.ITALIC, 16));
		lblName_2_1.setBounds(69, 122, 98, 32);
		panel.add(lblName_2_1);
		
		JLabel lblName_2_1_1 = new JLabel("Best Service");
		lblName_2_1_1.setForeground(Color.WHITE);
		lblName_2_1_1.setFont(new Font("Tahoma", Font.ITALIC, 16));
		lblName_2_1_1.setBounds(60, 177, 127, 32);
		panel.add(lblName_2_1_1);
		
		JLabel lblName_2_1_1_1 = new JLabel("Best Price");
		lblName_2_1_1_1.setForeground(Color.WHITE);
		lblName_2_1_1_1.setFont(new Font("Tahoma", Font.ITALIC, 16));
		lblName_2_1_1_1.setBounds(68, 233, 127, 32);
		panel.add(lblName_2_1_1_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(219, 0, 332, 332);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblName = new JLabel("Password");
		lblName.setForeground(new Color(30, 144, 255));
		lblName.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblName.setBounds(27, 212, 237, 32);
		panel_1.add(lblName);
		
		JLabel lblName_1 = new JLabel("Heaven Care Pet Shop");
		lblName_1.setForeground(new Color(30, 144, 255));
		lblName_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblName_1.setBounds(57, 21, 237, 32);
		panel_1.add(lblName_1);
		
		JLabel lblName_2 = new JLabel("UserName");
		lblName_2.setForeground(new Color(30, 144, 255));
		lblName_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblName_2.setBounds(27, 144, 237, 32);
		panel_1.add(lblName_2);
		
		UNameTb = new JTextField();
		UNameTb.setFont(new Font("Tahoma", Font.PLAIN, 16));
		UNameTb.setBounds(27, 186, 267, 26);
		panel_1.add(UNameTb);
		UNameTb.setColumns(10);
		
		PasswordTb = new JPasswordField();
		PasswordTb.setFont(new Font("Tahoma", Font.PLAIN, 16));
		PasswordTb.setBounds(27, 254, 267, 26);
		panel_1.add(PasswordTb);
		
		LoginBtn = new JButton("Login");
		LoginBtn.setBackground(new Color(255, 255, 255));
		LoginBtn.setForeground(new Color(30, 144, 255));
		LoginBtn.setFont(new Font("Tahoma", Font.BOLD, 16));
		LoginBtn.setBounds(127, 290, 97, 32);
		panel_1.add(LoginBtn);
		
		JLabel lblprofile = new JLabel("");
		lblprofile.setBounds(127, 73, 72, 72);
		lblprofile.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(Login.class.getResource("Profile-icon.png"))));
		panel_1.add(lblprofile);
		
		JLabel lblNewLabel = new JLabel("Admin");
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new AdminLogin().setVisible(true);
				Login.this.dispose();
			}
		});
		lblNewLabel.setForeground(new Color(30, 144, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(234, 296, 60, 26);
		panel_1.add(lblNewLabel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(LoginBtn)) {
			LoginClick();
		} 
	}

	private void LoginClick() {
		if(UNameTb.getText().isEmpty()||PasswordTb.getText().isEmpty()) {
			JOptionPane.showMessageDialog(Login.this, "Enter Both UserName and Password!!!");
		} else {
			String Query = "select * from UserTbl where Uname='"+UNameTb.getText()+"' and UPass='"+PasswordTb.getText()+"'";
			try {
				Con = DriverManager.getConnection("jdbc:sqlserver://LAPTOP-40OFFHMU\\SQLEXPRESS:1433;"
						+ "user=sa;password=123;databaseName=PetShopDb;encrypt=false");
				St = Con.createStatement();
				Rs = St.executeQuery(Query);
				if(Rs.next()) {
					new Pets().setVisible(true);
					this.dispose();
				} else {
					JOptionPane.showMessageDialog(Login.this, "Wrong User Name or Password");
					UNameTb.setText("");
					PasswordTb.setText("");
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
}
