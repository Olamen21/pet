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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.DAO;
import model.Pet;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Billing extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JScrollPane jScrollPane_Pro;
	private JTable ProductTable;
	private JTable BillTable;
	private DefaultTableModel model;
	private DefaultTableModel model1;
	private JTextField PriceTb;
	private JTextField PnameTbl;
	private JTextField QuantityTb;
	private JScrollPane jScrollPane_Cus_Bill;
	private ArrayList<Pet> list;
	int Key = 0;
	int Stock = 0;
	int n = 1;
	int row = 0;
	Connection Con = null;
	PreparedStatement pst = null;
	ResultSet Rs = null;
	Statement St = null;
	private JComboBox CustomerCb;
	private JComboBox UserCb;
	private JButton btnReset;
	private JButton btnAddtoBill;
	private JButton btnPrint;
	private JButton btnSave;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Billing frame = new Billing();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Billing() {
		this.init();
		list = new DAO().getListPet();
		model = new DefaultTableModel() {
			public boolean isCellEditable(int row, int column) {
				return false;
			};
		};
		model = (DefaultTableModel)this.ProductTable.getModel();
		this.model.setColumnIdentifiers(new Object[] {"Id","Name","Category","Quantity", "Price"});
		model1 = new DefaultTableModel() {
			public boolean isCellEditable(int row, int column) {
				return false;
			};
		};
		model1 = (DefaultTableModel)this.BillTable.getModel();
		this.model1.setColumnIdentifiers(new Object[] {"Id","Product","Price","Quantity","Total"});
		this.showTable();
		GetCustomer();
		GetUser();
		btnAddtoBill.addActionListener(this);
		btnPrint.addActionListener(this);
		btnReset.addActionListener(this);
		btnSave.addActionListener(this);
	}
	
	

	private void showTable() {
		for (Pet s : list) {
            model.addRow(new Object[]{s.getPId(),s.getPName(),s.getPCat(),s.getPqty(),s.getPprice()});
		}
		
	
	}
	
	private void GetCustomer() {
		try {
			Con = DriverManager.getConnection("jdbc:sqlserver://LAPTOP-40OFFHMU\\SQLEXPRESS:1433;"
					+ "user=sa;password=123;databaseName=PetShopDb;encrypt=false");
			Statement st = Con.createStatement();
			Rs = st.executeQuery("Select * from CustomerTbl");
			while(Rs.next()) {
				int CatId = Rs.getInt("CustId");
				CustomerCb.addItem(CatId);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private void GetUser() {
		try {
			Con = DriverManager.getConnection("jdbc:sqlserver://LAPTOP-40OFFHMU\\SQLEXPRESS:1433;"
					+ "user=sa;password=123;databaseName=PetShopDb;encrypt=false");
			Statement st = Con.createStatement();
			Rs = st.executeQuery("Select * from UserTbl");
			while(Rs.next()) {
				int UId = Rs.getInt("UId");
				UserCb.addItem(UId);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
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
		
		JLabel lbl_Bill = new JLabel("Billing");
		lbl_Bill.setBounds(10, 10, 148, 32);
		lbl_Bill.setForeground(new Color(30, 144, 255));
		lbl_Bill.setFont(new Font("Tahoma", Font.BOLD, 20));
		panel.add(lbl_Bill);
		
		JLabel lbl_User_id = new JLabel("User ID");
		lbl_User_id.setForeground(new Color(0, 0, 0));
		lbl_User_id.setFont(new Font("Tahoma", Font.BOLD, 18));
		lbl_User_id.setBounds(33, 52, 77, 32);
		panel.add(lbl_User_id);
		
		JLabel lblCustomer = new JLabel("Customer");
		lblCustomer.setForeground(Color.BLACK);
		lblCustomer.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblCustomer.setBounds(251, 52, 103, 32);
		panel.add(lblCustomer);
		
		JLabel lblProduct = new JLabel("Product");
		lblProduct.setForeground(Color.BLACK);
		lblProduct.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblProduct.setBounds(707, 52, 103, 32);
		panel.add(lblProduct);
		
		PnameTbl = new JTextField();
		PnameTbl.setFont(new Font("Tahoma", Font.PLAIN, 16));
		PnameTbl.setColumns(10);
		PnameTbl.setBounds(707, 94, 208, 38);
		panel.add(PnameTbl);
		
		JLabel lblPrice = new JLabel("Price");
		lblPrice.setForeground(Color.BLACK);
		lblPrice.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblPrice.setBounds(947, 52, 103, 32);
		panel.add(lblPrice);
		
		PriceTb = new JTextField();
		PriceTb.setFont(new Font("Tahoma", Font.PLAIN, 16));
		PriceTb.setColumns(10);
		PriceTb.setBounds(947, 94, 208, 38);
		panel.add(PriceTb);
		
		JLabel lblPro_List = new JLabel("Products List");
		lblPro_List.setForeground(new Color(0, 0, 0));
		lblPro_List.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblPro_List.setBounds(189, 252, 148, 32);
		panel.add(lblPro_List);
		
		btnReset = new JButton("Reset");
		btnReset.setForeground(new Color(30, 144, 255));
		btnReset.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnReset.setBackground(Color.WHITE);
		btnReset.setBounds(189, 167, 111, 44);
		panel.add(btnReset);
		
		btnAddtoBill = new JButton("Add to Bill");
		btnAddtoBill.setForeground(new Color(30, 144, 255));
		btnAddtoBill.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnAddtoBill.setBackground(Color.WHITE);
		btnAddtoBill.setBounds(979, 167, 159, 44);
		panel.add(btnAddtoBill);
		
		btnPrint = new JButton("Print");
		btnPrint.setForeground(new Color(30, 144, 255));
		btnPrint.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnPrint.setBackground(Color.WHITE);
		btnPrint.setBounds(1028, 650, 111, 44);
		panel.add(btnPrint);
		
		JLabel lblPets = new JLabel("Pets");
		lblPets.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new Pets().setVisible(true);
				Billing.this.dispose();
			}
		});
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
		lblCustomers.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new Customers().setVisible(true);
				Billing.this.dispose();
			}
		});
		lblCustomers.setForeground(Color.WHITE);
		lblCustomers.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblCustomers.setBounds(11, 354, 148, 32);
		contentPane.add(lblCustomers);
		
		JLabel lblBilling = new JLabel("Billing");
		lblBilling.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new Billing().setVisible(true);
				Billing.this.dispose();
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
				Billing.this.dispose();
			}
		});
		lblLogout.setForeground(Color.WHITE);
		lblLogout.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblLogout.setBounds(11, 541, 148, 32);
		contentPane.add(lblLogout);
		
		jScrollPane_Pro = new JScrollPane();
		ProductTable = new JTable();
		ProductTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel model = (DefaultTableModel) ProductTable.getModel();
				int Myindex = ProductTable.getSelectedRow();
				Key = Integer.valueOf(model.getValueAt(Myindex, 0).toString());
				PnameTbl.setText(model.getValueAt(Myindex, 1).toString());
				Stock = Integer.valueOf(model.getValueAt(Myindex, 3).toString());
				PriceTb.setText(model.getValueAt(Myindex, 4).toString());
			}
		});
		ProductTable.setFont(new Font("Tahoma", Font.PLAIN, 16));
		this.ProductTable.setModel(new DefaultTableModel(new Object[0][], new String[0]));
		this.jScrollPane_Pro.setViewportView(this.ProductTable);
		jScrollPane_Pro.setBounds(33, 310, 468, 317);
		
		panel.add(jScrollPane_Pro);
		
		CustomerCb = new JComboBox();
		CustomerCb.setBounds(251, 94, 208, 38);
		panel.add(CustomerCb);
		
		UserCb = new JComboBox();
		UserCb.setBounds(33, 94, 208, 38);
		panel.add(UserCb);
		
		btnSave = new JButton("Save");
		btnSave.setForeground(new Color(30, 144, 255));
		btnSave.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnSave.setBackground(Color.WHITE);
		btnSave.setBounds(836, 650, 111, 44);
		panel.add(btnSave);
		
		QuantityTb = new JTextField();
		QuantityTb.setFont(new Font("Tahoma", Font.PLAIN, 16));
		QuantityTb.setColumns(10);
		QuantityTb.setBounds(707, 173, 208, 38);
		panel.add(QuantityTb);
		
		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setForeground(Color.BLACK);
		lblQuantity.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblQuantity.setBounds(707, 131, 103, 32);
		panel.add(lblQuantity);
		
		jScrollPane_Cus_Bill = new JScrollPane();
		BillTable = new JTable();
		BillTable.setFont(new Font("Tahoma", Font.PLAIN, 16));
		this.BillTable.setModel(new DefaultTableModel(new Object[0][], new String[0]));
		this.jScrollPane_Cus_Bill.setViewportView(this.BillTable);
		jScrollPane_Cus_Bill.setBounds(732, 320, 468, 307);
		panel.add(jScrollPane_Cus_Bill);
		
		JLabel lblCustomer_Bill = new JLabel("Customer's Bill");
		lblCustomer_Bill.setForeground(new Color(30, 144, 255));
		lblCustomer_Bill.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblCustomer_Bill.setBounds(872, 252, 198, 32);
		panel.add(lblCustomer_Bill);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnSave)) {
			SaveBtnClick();
		} else if(e.getSource().equals(btnAddtoBill)) {
			AddtoBillClick();
		} else if(e.getSource().equals(btnReset)) {
			ResetClick();
		} else if(e.getSource().equals(btnPrint)) {
			PrintClick();
		}
	}

	private void PrintClick() {
		// TODO Auto-generated method stub
		
	}

	private void ResetClick() {
		// TODO Auto-generated method stub
		
	}

	int a = 1;
	int r = 0;
	int col;
	private void AddtoBillClick() {
		if(PnameTbl.getText().isEmpty() || QuantityTb.getText().isEmpty()|| PriceTb.getText().isEmpty()) {
			
		} else {
			BillTable.setValueAt(a, r, col+1);
			BillTable.setValueAt(PnameTbl.getText(), r, col+1);
			a++;
			r++;
		}
		
		
	}

	private void SaveBtnClick() {
		// TODO Auto-generated method stub
		
	}
}
