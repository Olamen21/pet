package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Customers extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField CustNameTb;
	private JTextField AddressTb;
	private JScrollPane Jscroll;
	private JTable CustomerTable;
	private DefaultTableModel model;
	private JTextField PhoneTb;
	Connection Con = null;
	PreparedStatement pst = null;
	ResultSet Rs = null;
	Statement St = null;
	private ArrayList<Customer> list;
	int Key = 0;
	private JButton SaveBtn;
	private JButton EditBtn;
	private JButton DeleteBtn;
	private JButton btnRefresh;
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Customers frame = new Customers();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public Customers() {
		this.init();
		list = new DAO().getListCustomer();
		model = new DefaultTableModel() {
			public boolean isCellEditable(int row, int column) {
				return false;
			};
		};
		model = (DefaultTableModel)this.CustomerTable.getModel();
		this.model.setColumnIdentifiers(new Object[] {"Id","Name","Address","Phone"});
		this.showTable();
		SaveBtn.addActionListener(this);
		EditBtn.addActionListener(this);
		DeleteBtn.addActionListener(this);
		btnRefresh.addActionListener(this);
	}
	
	public void showTable() {
		for (Customer s : list) {
            model.addRow(new Object[]{s.getCusId(), s.getCustName(), s.getCustAdd(), s.getCustPhone()});
		}
	}
	
	public void Clear() {
		CustNameTb.setText("");
		AddressTb.setText("");
		PhoneTb.setText("");
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
		
		JLabel lblMange_Customers = new JLabel("Manage Customers");
		lblMange_Customers.setBounds(10, 10, 246, 32);
		lblMange_Customers.setForeground(new Color(30, 144, 255));
		lblMange_Customers.setFont(new Font("Tahoma", Font.BOLD, 20));
		panel.add(lblMange_Customers);
		
		JLabel lblName = new JLabel("Name");
		lblName.setForeground(new Color(0, 0, 0));
		lblName.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblName.setBounds(332, 83, 77, 32);
		panel.add(lblName);
		
		CustNameTb = new JTextField();
		CustNameTb.setFont(new Font("Tahoma", Font.PLAIN, 16));
		CustNameTb.setBounds(332, 125, 208, 38);
		panel.add(CustNameTb);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setForeground(Color.BLACK);
		lblAddress.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblAddress.setBounds(626, 83, 103, 32);
		panel.add(lblAddress);
		
		AddressTb = new JTextField();
		AddressTb.setFont(new Font("Tahoma", Font.PLAIN, 16));
		AddressTb.setBounds(626, 125, 208, 38);
		panel.add(AddressTb);
		
		JLabel lblCus_List = new JLabel("Customers List");
		lblCus_List.setForeground(new Color(0, 0, 0));
		lblCus_List.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblCus_List.setBounds(567, 247, 238, 32);
		panel.add(lblCus_List);
		
		SaveBtn = new JButton("Save");
		SaveBtn.setForeground(new Color(30, 144, 255));
		SaveBtn.setFont(new Font("Tahoma", Font.BOLD, 16));
		SaveBtn.setBackground(Color.WHITE);
		SaveBtn.setBounds(403, 184, 111, 44);
		panel.add(SaveBtn);
		
		EditBtn = new JButton("Edit");
		EditBtn.setForeground(new Color(30, 144, 255));
		EditBtn.setFont(new Font("Tahoma", Font.BOLD, 16));
		EditBtn.setBackground(Color.WHITE);
		EditBtn.setBounds(567, 184, 111, 44);
		panel.add(EditBtn);
		
		DeleteBtn = new JButton("Delete");
		DeleteBtn.setForeground(new Color(30, 144, 255));
		DeleteBtn.setFont(new Font("Tahoma", Font.BOLD, 16));
		DeleteBtn.setBackground(Color.WHITE);
		DeleteBtn.setBounds(755, 184, 111, 44);
		panel.add(DeleteBtn);
		
		JLabel lblPets = new JLabel("Pets");
		lblPets.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new Pets().setVisible(true);
				Customers.this.dispose();
			}
		});
		lblPets.setForeground(new Color(255, 255, 255));
		lblPets.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblPets.setBounds(11, 186, 148, 32);
		contentPane.add(lblPets);
		
		JLabel lbldog = new JLabel("");
		lbldog.setBounds(48, 16, 72, 85);
		lbldog.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(Customers.class.getResource("dog.png"))));
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
		lblBilling.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new Billing().setVisible(true);
				Customers.this.dispose();
			}
		});
		lblBilling.setForeground(Color.WHITE);
		lblBilling.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblBilling.setBounds(11, 430, 148, 32);
		contentPane.add(lblBilling);
		
		JLabel lblLogout = new JLabel("Logout");
		lblLogout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new Login().setVisible(true);
				Customers.this.dispose();
				
			}
		});
		lblLogout.setForeground(Color.WHITE);
		lblLogout.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblLogout.setBounds(11, 541, 148, 32);
		contentPane.add(lblLogout);
		
		Jscroll = new JScrollPane();
		CustomerTable = new JTable();
		CustomerTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel model = (DefaultTableModel) CustomerTable.getModel();
				int Myindex = CustomerTable.getSelectedRow();
				Key = Integer.valueOf(model.getValueAt(Myindex, 0).toString());
				CustNameTb.setText(model.getValueAt(Myindex, 1).toString());
				AddressTb.setText(model.getValueAt(Myindex, 2).toString());
				PhoneTb.setText(model.getValueAt(Myindex, 3).toString());
			}
		});
		CustomerTable.setFont(new Font("Tahoma", Font.PLAIN, 16));
		this.CustomerTable.setModel(new DefaultTableModel(new Object[0][], new String[0]));
		this.Jscroll.setViewportView(this.CustomerTable);
		Jscroll.setBounds(113, 307, 1081, 355);
		
		panel.add(Jscroll);
		
		JLabel lblPhone = new JLabel("Phone");
		lblPhone.setForeground(Color.BLACK);
		lblPhone.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblPhone.setBounds(899, 83, 103, 32);
		panel.add(lblPhone);
		
		PhoneTb = new JTextField();
		PhoneTb.setFont(new Font("Tahoma", Font.PLAIN, 16));
		PhoneTb.setBounds(899, 125, 208, 38);
		panel.add(PhoneTb);
		
		btnRefresh = new JButton("Refresh");
		btnRefresh.setForeground(new Color(30, 144, 255));
		btnRefresh.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnRefresh.setBackground(Color.WHITE);
		btnRefresh.setBounds(944, 184, 111, 44);
		panel.add(btnRefresh);
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(SaveBtn)) {
			SaveBtnClick();
		} else if(e.getSource().equals(EditBtn)) {
			EditBtnClick();
		} else if(e.getSource().equals(DeleteBtn)) {
			DeleteBtnClick();
		} else if(e.getSource().equals(btnRefresh)) {
			RefreshBtnClick();
		}
	}


	public void RefreshBtnClick() {
		model.setRowCount(0);
		list = new DAO().getListCustomer();
		showTable();
	}


	public void DeleteBtnClick() {
		try {
			int row = CustomerTable.getSelectedRow();
			if(row==-1) {
				JOptionPane.showMessageDialog(Customers.this, "Vui lòng chọn khách hàng!","Lỗi!", JOptionPane.ERROR_MESSAGE, null);
			} else {
				int confirm = JOptionPane.showConfirmDialog(Customers.this, "Bạn có chắc chắn muốn xóa không?");
				
				if(confirm == JOptionPane.YES_OPTION) {
					int id = Integer.valueOf(String.valueOf(CustomerTable.getValueAt(row, 0)));
					new DAO().xoaCustomer(id);
					
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public void EditBtnClick() {
		if(CustNameTb.getText().isEmpty()|| AddressTb.getText().isEmpty()|| PhoneTb.getText().isEmpty()) {
			JOptionPane.showMessageDialog(rootPane, "Missing Information");
		} else {
			try {
				Con = DriverManager.getConnection("jdbc:sqlserver://LAPTOP-40OFFHMU\\SQLEXPRESS:1433;"
						+ "user=sa;password=123;databaseName=PetShopDb;encrypt=false");
				PreparedStatement Save = Con.prepareStatement("update UF_deleteACust(?) set CustName=?, CustAdd=?,CustPhone=?");
				Save.setInt(1, Key);
				Save.setString(2, CustNameTb.getText());
				Save.setString(3, AddressTb.getText());
				Save.setString(4, PhoneTb.getText());
				int row = Save.executeUpdate();
				JOptionPane.showMessageDialog(rootPane, "Customer Updated");
				Con.close();
				Clear();
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(rootPane, e2);
			}
		}
		
	}


	Statement St1 = null;
	ResultSet Rs1 = null;
	private int ItemId;
	private void CountIt() {
		try {
			Con = DriverManager.getConnection("jdbc:sqlserver://LAPTOP-40OFFHMU\\SQLEXPRESS:1433;"
					+ "user=sa;password=123;databaseName=PetShopDb;encrypt=false");
			St1 = Con.createStatement();
			Rs1 = St1.executeQuery("select dbo.UF_MaxId() from CustomerTbl");
			Rs1.next();
			ItemId = Rs1.getInt(1)+1;
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void SaveBtnClick() {
		if(CustNameTb.getText().isEmpty()|| AddressTb.getText().isEmpty()|| PhoneTb.getText().isEmpty()) {
			JOptionPane.showMessageDialog(rootPane, "Missing Information");
		} else {
			try {
				CountIt();
				Con = DriverManager.getConnection("jdbc:sqlserver://LAPTOP-40OFFHMU\\SQLEXPRESS:1433;"
						+ "user=sa;password=123;databaseName=PetShopDb;encrypt=false");
				PreparedStatement Save = Con.prepareStatement("insert into UF_SelectAllCustomer() values(?,?,?,?)");
				Save.setInt(1, ItemId);
				Save.setString(2, CustNameTb.getText());
				Save.setString(3, AddressTb.getText());
				Save.setString(4, PhoneTb.getText());
				int row = Save.executeUpdate();
				JOptionPane.showMessageDialog(rootPane, "Customer Added");
				Con.close();
				Clear();
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(rootPane, e2);
			}
		}
	}
}
