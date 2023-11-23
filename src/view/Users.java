package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.DAO;
import model.Customer;
import model.User;

import javax.swing.JPasswordField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Users extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField UserName;
	private JScrollPane jScrollPane_User_List;
	private JTable UserTable;
	private DefaultTableModel model;
	private JTextField PasswordTb;
	Connection Con = null;
	PreparedStatement pst = null;
	ResultSet Rs = null;
	Statement St = null;
	private ArrayList<User> list;
	private JButton btnSave;
	private JButton btnEdit;
	private JButton btnDelete;
	private JButton btnRefresh;
	int Key = 0;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Users frame = new Users();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Users() {
		this.init();
		list = new DAO().getListUser();
		model = new DefaultTableModel() {
			public boolean isCellEditable(int row, int column) {
				return false;
			};
		};
		model = (DefaultTableModel)this.UserTable.getModel();
		this.model.setColumnIdentifiers(new Object[] {"Id","Name","Password"});
		this.showTable();
		btnSave.addActionListener(this);
		btnEdit.addActionListener(this);
		btnDelete.addActionListener(this);
		btnRefresh.addActionListener(this);
	}
	
	public void showTable() {
		for (User s : list) {
            model.addRow(new Object[]{s.getId(),s.getName(),s.getPassword()});
		}
	}
	
	public void Clear() {
		UserName.setText("");
		PasswordTb.setText("");
	}


	public void init() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1500, 761);
		this.setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(100, 149, 237));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(179, 16, 1297, 704);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblMange_Users = new JLabel("Manage Users");
		lblMange_Users.setBounds(10, 10, 148, 32);
		lblMange_Users.setForeground(new Color(30, 144, 255));
		lblMange_Users.setFont(new Font("Tahoma", Font.BOLD, 20));
		panel.add(lblMange_Users);
		
		JLabel lblName = new JLabel("Name");
		lblName.setForeground(new Color(0, 0, 0));
		lblName.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblName.setBounds(332, 83, 77, 32);
		panel.add(lblName);
		
		UserName = new JTextField();
		UserName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		UserName.setColumns(10);
		UserName.setBounds(332, 125, 208, 38);
		panel.add(UserName);
		
		JLabel lblPass = new JLabel("Password");
		lblPass.setForeground(Color.BLACK);
		lblPass.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblPass.setBounds(710, 83, 103, 32);
		panel.add(lblPass);
		
		JLabel lblUsers_List = new JLabel("Users List");
		lblUsers_List.setForeground(new Color(0, 0, 0));
		lblUsers_List.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblUsers_List.setBounds(567, 247, 148, 32);
		panel.add(lblUsers_List);
		
		btnSave = new JButton("Save");
		btnSave.setForeground(new Color(30, 144, 255));
		btnSave.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnSave.setBackground(Color.WHITE);
		btnSave.setBounds(348, 184, 111, 44);
		panel.add(btnSave);
		
		btnEdit = new JButton("Edit");
		btnEdit.setForeground(new Color(30, 144, 255));
		btnEdit.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnEdit.setBackground(Color.WHITE);
		btnEdit.setBounds(495, 184, 111, 44);
		panel.add(btnEdit);
		
		btnDelete = new JButton("Delete");
		btnDelete.setForeground(new Color(30, 144, 255));
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnDelete.setBackground(Color.WHITE);
		btnDelete.setBounds(648, 184, 111, 44);
		panel.add(btnDelete);
		
		JLabel lblPets = new JLabel("Pets");
		lblPets.setForeground(new Color(255, 255, 255));
		lblPets.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblPets.setBounds(11, 186, 148, 32);
		contentPane.add(lblPets);
		
		JLabel lbldog = new JLabel("");
		lbldog.setBounds(48, 16, 72, 85);
		lbldog.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(Pets.class.getResource("dog.png"))));
		contentPane.add(lbldog);
		
		JLabel lblUsers = new JLabel("Users");
		lblUsers.setForeground(Color.WHITE);
		lblUsers.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblUsers.setBounds(11, 276, 148, 32);
		contentPane.add(lblUsers);
		
		JLabel lblCustomers = new JLabel("Customers");
		lblCustomers.setForeground(Color.WHITE);
		lblCustomers.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblCustomers.setBounds(11, 354, 148, 32);
		contentPane.add(lblCustomers);
		
		JLabel lblBilling = new JLabel("Billing");
		lblBilling.setForeground(Color.WHITE);
		lblBilling.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblBilling.setBounds(11, 430, 148, 32);
		contentPane.add(lblBilling);
		
		JLabel lblLogout = new JLabel("Logout");
		lblLogout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new Login().setVisible(true);
				Users.this.dispose();
			}
		});
		lblLogout.setForeground(Color.WHITE);
		lblLogout.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblLogout.setBounds(11, 541, 148, 32);
		contentPane.add(lblLogout);
		
		jScrollPane_User_List = new JScrollPane();
		jScrollPane_User_List.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel model = (DefaultTableModel) UserTable.getModel();
				int Myindex = UserTable.getSelectedRow();
				Key = Integer.valueOf(model.getValueAt(Myindex, 0).toString());
				UserName.setText(model.getValueAt(Myindex, 1).toString());
				PasswordTb.setText(model.getValueAt(Myindex, 2).toString());
				
			}
		});
		UserTable = new JTable();
		UserTable.setFont(new Font("Tahoma", Font.PLAIN, 16));
		this.UserTable.setModel(new DefaultTableModel(new Object[0][], new String[0]));
		this.jScrollPane_User_List.setViewportView(this.UserTable);
		jScrollPane_User_List.setBounds(113, 307, 1081, 355);
		
		panel.add(jScrollPane_User_List);
		
		PasswordTb = new JPasswordField();
		PasswordTb.setBounds(710, 125, 208, 38);
		panel.add(PasswordTb);
		
		btnRefresh = new JButton("Refresh");
		btnRefresh.setForeground(new Color(30, 144, 255));
		btnRefresh.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnRefresh.setBackground(Color.WHITE);
		btnRefresh.setBounds(807, 184, 111, 44);
		panel.add(btnRefresh);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnSave)) {
			SaveBtnClick();
		} else if(e.getSource().equals(btnRefresh)) {
			RefreshClick();
		} else if(e.getSource().equals(btnEdit)) {
			EditClick();
		} else if(e.getSource().equals(btnDelete)) {
			DeleteClick();
		}
	}
	
	private void DeleteClick() {
		try {
			int row = UserTable.getSelectedRow();
			if(row==-1) {
				JOptionPane.showMessageDialog(Users.this, "Vui lòng chọn người dùng!","Lỗi!", JOptionPane.ERROR_MESSAGE, null);
			} else {
				int confirm = JOptionPane.showConfirmDialog(Users.this, "Bạn có chắc chắn muốn xóa không?");
				
				if(confirm == JOptionPane.YES_OPTION) {
					int id = Integer.valueOf(String.valueOf(UserTable.getValueAt(row, 0)));
					new DAO().deleteUser(id);
					
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void EditClick() {
		if(UserName.getText().isEmpty()|| PasswordTb.getText().isEmpty()) {
			JOptionPane.showMessageDialog(rootPane, "Missing Information");
		} else {
			try {
				Con = DriverManager.getConnection("jdbc:sqlserver://LAPTOP-40OFFHMU\\SQLEXPRESS:1433;"
						+ "user=sa;password=123;databaseName=PetShopDb;encrypt=false");
				PreparedStatement Save = Con.prepareStatement("update UF_ChooseAUser(?) set UName=?, UPass=?");
				Save.setInt(1, Key);
				Save.setString(2, UserName.getText());
				Save.setString(3, PasswordTb.getText());
				int row = Save.executeUpdate();
				JOptionPane.showMessageDialog(rootPane, "User Edited");
				Con.close();
				Clear();
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(rootPane, e2);
			}
		}
	}

	private void RefreshClick() {
		model.setRowCount(0);
		list = new DAO().getListUser();
		showTable();
	}

	Statement St1 = null;
	ResultSet Rs1 = null;
	private int ItemId;

	private void SaveBtnClick() {
		if(UserName.getText().isEmpty()|| PasswordTb.getText().isEmpty()) {
			JOptionPane.showMessageDialog(rootPane, "Missing Information");
		} else {
			try {
				Con = DriverManager.getConnection("jdbc:sqlserver://LAPTOP-40OFFHMU\\SQLEXPRESS:1433;"
						+ "user=sa;password=123;databaseName=PetShopDb;encrypt=false");
				PreparedStatement Save = Con.prepareStatement("insert into UF_SelectAllUser() values(?,?)");
				Save.setString(1, UserName.getText());
				Save.setString(2, PasswordTb.getText());
				int row = Save.executeUpdate();
				JOptionPane.showMessageDialog(rootPane, "User Added");
				Con.close();
				Clear();
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(rootPane, e2);
			}
		}
	}
}
