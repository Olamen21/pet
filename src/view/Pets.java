package view;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.DAO;
import model.Pet;
import model.User;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JComboBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Pets extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField PetNameTb;
	private JTextField QuantityTb;
	private JTextField PriceTb;
	private JScrollPane jScrollPane_Pro_List;
	private JTable PetsTable;
	private DefaultTableModel model;
	private JButton btnRefresh;
	private ArrayList<Pet> list;
	Connection Con = null;
	PreparedStatement pst = null;
	ResultSet Rs = null;
	Statement St = null;
	private JButton btnSave;
	private JButton btnEdit;
	private JButton btnDelete;
	private JComboBox CatCb;
	int Key = 0;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Pets frame = new Pets();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Pets() {
		this.init();
		list = new DAO().getListPet();
		model = new DefaultTableModel() {
			public boolean isCellEditable(int row, int column) {
				return false;
			};
		};
		model = (DefaultTableModel)this.PetsTable.getModel();
		this.model.setColumnIdentifiers(new Object[] {"Id","Name","Category","Quantity", "Price"});
		getCategories();
		this.showTable();

		btnSave.addActionListener(this);
		btnDelete.addActionListener(this);
		btnEdit.addActionListener(this);
		btnRefresh.addActionListener(this);
	}

	public void showTable() {
		for (Pet s : list) {
            model.addRow(new Object[]{s.getPId(),s.getPName(),s.getPCat(),s.getPqty(),s.getPprice()});
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
		
		JLabel lblMange_Pets = new JLabel("Manage Pets");
		lblMange_Pets.setBounds(10, 10, 148, 32);
		lblMange_Pets.setForeground(new Color(30, 144, 255));
		lblMange_Pets.setFont(new Font("Tahoma", Font.BOLD, 20));
		panel.add(lblMange_Pets);
		
		JLabel lblName = new JLabel("Name");
		lblName.setForeground(new Color(0, 0, 0));
		lblName.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblName.setBounds(142, 83, 77, 32);
		panel.add(lblName);
		
		PetNameTb = new JTextField();
		PetNameTb.setFont(new Font("Tahoma", Font.PLAIN, 16));
		PetNameTb.setColumns(10);
		PetNameTb.setBounds(142, 125, 208, 38);
		panel.add(PetNameTb);
		
		JLabel lblCategory = new JLabel("Category");
		lblCategory.setForeground(Color.BLACK);
		lblCategory.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblCategory.setBounds(412, 83, 103, 32);
		panel.add(lblCategory);
		
		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setForeground(Color.BLACK);
		lblQuantity.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblQuantity.setBounds(710, 83, 103, 32);
		panel.add(lblQuantity);
		
		QuantityTb = new JTextField();
		QuantityTb.setFont(new Font("Tahoma", Font.PLAIN, 16));
		QuantityTb.setColumns(10);
		QuantityTb.setBounds(710, 125, 208, 38);
		panel.add(QuantityTb);
		
		JLabel lblPrice = new JLabel("Price");
		lblPrice.setForeground(Color.BLACK);
		lblPrice.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblPrice.setBounds(992, 83, 103, 32);
		panel.add(lblPrice);
		
		PriceTb = new JTextField();
		PriceTb.setFont(new Font("Tahoma", Font.PLAIN, 16));
		PriceTb.setColumns(10);
		PriceTb.setBounds(992, 125, 208, 38);
		panel.add(PriceTb);
		
		JLabel lblProducts_List = new JLabel("Products List");
		lblProducts_List.setForeground(new Color(0, 0, 0));
		lblProducts_List.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblProducts_List.setBounds(567, 247, 148, 32);
		panel.add(lblProducts_List);
		
		btnSave = new JButton("Save");
		btnSave.setForeground(new Color(30, 144, 255));
		btnSave.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnSave.setBackground(Color.WHITE);
		btnSave.setBounds(294, 184, 111, 44);
		panel.add(btnSave);
		
		btnEdit = new JButton("Edit");
		btnEdit.setForeground(new Color(30, 144, 255));
		btnEdit.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnEdit.setBackground(Color.WHITE);
		btnEdit.setBounds(478, 184, 111, 44);
		panel.add(btnEdit);
		
		btnDelete = new JButton("Delete");
		btnDelete.setForeground(new Color(30, 144, 255));
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnDelete.setBackground(Color.WHITE);
		btnDelete.setBounds(656, 184, 111, 44);
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
		lblCustomers.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new Customers().setVisible(true);
				Pets.this.dispose();
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
				Pets.this.dispose();
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
				Pets.this.dispose();
			}
		});
		lblLogout.setForeground(Color.WHITE);
		lblLogout.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblLogout.setBounds(11, 541, 148, 32);
		contentPane.add(lblLogout);
		
		jScrollPane_Pro_List = new JScrollPane();
		PetsTable = new JTable();
		PetsTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel model = (DefaultTableModel) PetsTable.getModel();
				int Myindex = PetsTable.getSelectedRow();
				Key = Integer.valueOf(model.getValueAt(Myindex, 0).toString());
				PetNameTb.setText(model.getValueAt(Myindex, 1).toString());
				CatCb.setSelectedItem(model.getValueAt(Myindex, 2).toString());
				QuantityTb.setText(model.getValueAt(Myindex, 3).toString());
				PriceTb.setText(model.getValueAt(Myindex, 4).toString());
			}
		});
		PetsTable.setFont(new Font("Tahoma", Font.PLAIN, 16));
		this.PetsTable.setModel(new DefaultTableModel(new Object[0][], new String[0]));
		this.jScrollPane_Pro_List.setViewportView(this.PetsTable);
		jScrollPane_Pro_List.setBounds(10, 307, 1277, 355);
		
		panel.add(jScrollPane_Pro_List);
		
		CatCb = new JComboBox();
		CatCb.setBounds(412, 125, 208, 38);
		panel.add(CatCb);
		
		btnRefresh = new JButton("Refresh");
		btnRefresh.setForeground(new Color(30, 144, 255));
		btnRefresh.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnRefresh.setBackground(Color.WHITE);
		btnRefresh.setBounds(848, 184, 111, 44);
		panel.add(btnRefresh);
		
	}
	
	private void getCategories() {
		try {
			Con = DriverManager.getConnection("jdbc:sqlserver://LAPTOP-40OFFHMU\\SQLEXPRESS:1433;"
					+ "user=sa;password=123;databaseName=PetShopDb;encrypt=false");
			Statement st = Con.createStatement();
			Rs = st.executeQuery("Select * from CategoryTbl");
			while(Rs.next()) {
				int CatId = Rs.getInt("CatId");
				CatCb.addItem(CatId);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	private void Clear() {
		PetNameTb.setText("");
		QuantityTb.setText("");
		PriceTb.setText("");
		CatCb.setSelectedIndex(-1);
		
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
			int row = PetsTable.getSelectedRow();
			if(row==-1) {
				JOptionPane.showMessageDialog(Pets.this, "Vui lòng chọn thú cưng!","Lỗi!", JOptionPane.ERROR_MESSAGE, null);
			} else {
				int confirm = JOptionPane.showConfirmDialog(Pets.this, "Bạn có chắc chắn muốn xóa không?");
				
				if(confirm == JOptionPane.YES_OPTION) {
					int id = Integer.valueOf(String.valueOf(PetsTable.getValueAt(row, 0)));
					new DAO().deletePet(id);
					Clear();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void EditClick() {
		if(PetNameTb.getText().isEmpty()|| QuantityTb.getText().isEmpty()|| PriceTb.getText().isEmpty() || CatCb.getSelectedIndex()==-1) {
			JOptionPane.showMessageDialog(rootPane, "Missing Information");
		} else {
			try {
				Con = DriverManager.getConnection("jdbc:sqlserver://LAPTOP-40OFFHMU\\SQLEXPRESS:1433;"
						+ "user=sa;password=123;databaseName=PetShopDb;encrypt=false");
				PreparedStatement Save = Con.prepareStatement("update UF_ChooseAPet(?) set PName=?, PCat=?,Pqty=?, Pprice=?");
				Save.setInt(1, Key);
				Save.setString(2, PetNameTb.getText());
				Save.setString(3, CatCb.getSelectedItem().toString());
				Save.setString(4, QuantityTb.getText());
				Save.setString(5, PriceTb.getText());
				int row = Save.executeUpdate();
				JOptionPane.showMessageDialog(rootPane, "Pet Updated");
				Con.close();
				Clear();
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(rootPane, e2);
			}
		}
	}

	private void RefreshClick() {
		model.setRowCount(0);
		list = new DAO().getListPet();
		showTable();
	}
	
	Statement St1 = null;
	ResultSet Rs1 = null;
	

	private void SaveBtnClick() {
		if(PetNameTb.getText().isEmpty()|| QuantityTb.getText().isEmpty()|| CatCb.getSelectedIndex()==-1||PriceTb.getText().isEmpty()) {
			JOptionPane.showMessageDialog(rootPane, "Missing Information");
		} else {
			try {
				Con = DriverManager.getConnection("jdbc:sqlserver://LAPTOP-40OFFHMU\\SQLEXPRESS:1433;"
						+ "user=sa;password=123;databaseName=PetShopDb;encrypt=false");
				PreparedStatement Save = Con.prepareStatement("insert into UF_SelectAllPet() values(?,?,?,?)");
				Save.setString(1, PetNameTb.getText());
				Save.setInt(2, Integer.valueOf(CatCb.getSelectedItem().toString()));
				Save.setInt(3, Integer.valueOf(QuantityTb.getText()) );
				Save.setInt(4, Integer.valueOf(PriceTb.getText()) );
				int row = Save.executeUpdate();
				JOptionPane.showMessageDialog(rootPane, "Product Added");
				Con.close();
				Clear();
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(rootPane, e2);
			}
		}
	}
}
