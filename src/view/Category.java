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
import model.User;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Category extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField CatNameTbl;
	private JScrollPane jScrollPane_Cate_List;
	private JTable CategoriesTable;
	private DefaultTableModel model;
	Connection Con = null;
	PreparedStatement pst = null;
	ResultSet Rs = null;
	Statement St = null;
	int Key = 0;
	private ArrayList<model.Category> list;
	private JButton btnSave;
	private JButton btnEdit;
	private JButton btnDelete;
	private JButton btnRefresh;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Category frame = new Category();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public Category() {
		this.init();
		list = new DAO().getListCategory();
		model = new DefaultTableModel() {
			public boolean isCellEditable(int row, int column) {
				return false;
			};
		};
		model = (DefaultTableModel)this.CategoriesTable.getModel();
		this.model.setColumnIdentifiers(new Object[] {"Id","Name"});
		this.showTable();
		btnSave.addActionListener(this);
		btnDelete.addActionListener(this);
		btnEdit.addActionListener(this);
		btnRefresh.addActionListener(this);
	}
	
	public void showTable() {
		for (model.Category s : list) {
            model.addRow(new Object[]{s.getId(),s.getName()});
		}
	}
	
	public void Clear() {
		CatNameTbl.setText("");
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
		
		JLabel lblMange_Categories = new JLabel("Manage Categories");
		lblMange_Categories.setBounds(10, 10, 246, 32);
		lblMange_Categories.setForeground(new Color(30, 144, 255));
		lblMange_Categories.setFont(new Font("Tahoma", Font.BOLD, 20));
		panel.add(lblMange_Categories);
		
		JLabel lblName = new JLabel("Name");
		lblName.setForeground(new Color(0, 0, 0));
		lblName.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblName.setBounds(576, 79, 77, 32);
		panel.add(lblName);
		
		CatNameTbl = new JTextField();
		CatNameTbl.setFont(new Font("Tahoma", Font.PLAIN, 16));
		CatNameTbl.setBounds(513, 121, 208, 38);
		panel.add(CatNameTbl);
		
		JLabel lblCategories_List = new JLabel("Categories List");
		lblCategories_List.setForeground(new Color(0, 0, 0));
		lblCategories_List.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblCategories_List.setBounds(567, 247, 238, 32);
		panel.add(lblCategories_List);
		
		btnSave = new JButton("Save");
		btnSave.setForeground(new Color(30, 144, 255));
		btnSave.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnSave.setBackground(Color.WHITE);
		btnSave.setBounds(319, 184, 111, 44);
		panel.add(btnSave);
		
		btnEdit = new JButton("Edit");
		btnEdit.setForeground(new Color(30, 144, 255));
		btnEdit.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnEdit.setBackground(Color.WHITE);
		btnEdit.setBounds(480, 184, 111, 44);
		panel.add(btnEdit);
		
		btnDelete = new JButton("Delete");
		btnDelete.setForeground(new Color(30, 144, 255));
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnDelete.setBackground(Color.WHITE);
		btnDelete.setBounds(639, 184, 111, 44);
		panel.add(btnDelete);
		
		JLabel lblPets = new JLabel("Pets");
		lblPets.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new Pets().setVisible(true);
				Category.this.dispose();
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
		lblLogout.setForeground(Color.WHITE);
		lblLogout.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblLogout.setBounds(11, 541, 148, 32);
		contentPane.add(lblLogout);
		
		jScrollPane_Cate_List = new JScrollPane();
		jScrollPane_Cate_List.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel model = (DefaultTableModel) CategoriesTable.getModel();
				int Myindex = CategoriesTable.getSelectedRow();
				Key = Integer.valueOf(model.getValueAt(Myindex, 0).toString());
				CatNameTbl.setText(model.getValueAt(Myindex, 1).toString());
				
			}
		});
		CategoriesTable = new JTable();
		CategoriesTable.setFont(new Font("Tahoma", Font.PLAIN, 16));
		this.CategoriesTable.setModel(new DefaultTableModel(new Object[0][], new String[0]));
		this.jScrollPane_Cate_List.setViewportView(this.CategoriesTable);
		jScrollPane_Cate_List.setBounds(113, 307, 1081, 355);
		
		panel.add(jScrollPane_Cate_List);
		
		btnRefresh = new JButton("Refresh");
		btnRefresh.setForeground(new Color(30, 144, 255));
		btnRefresh.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnRefresh.setBackground(Color.WHITE);
		btnRefresh.setBounds(806, 184, 111, 44);
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
			int row = CategoriesTable.getSelectedRow();
			if(row==-1) {
				JOptionPane.showMessageDialog(Category.this, "Vui lòng chọn khách hàng!","Lỗi!", JOptionPane.ERROR_MESSAGE, null);
			} else {
				int confirm = JOptionPane.showConfirmDialog(Category.this, "Bạn có chắc chắn muốn xóa không?");
				
				if(confirm == JOptionPane.YES_OPTION) {
					int id = Integer.valueOf(String.valueOf(CategoriesTable.getValueAt(row, 0)));
					new DAO().deleteCategory(id);
					
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	private void EditClick() {
		if(CatNameTbl.getText().isEmpty()) {
			JOptionPane.showMessageDialog(rootPane, "Missing Information");
		} else {
			try {
				Con = DriverManager.getConnection("jdbc:sqlserver://LAPTOP-40OFFHMU\\SQLEXPRESS:1433;"
						+ "user=sa;password=123;databaseName=PetShopDb;encrypt=false");
				PreparedStatement Save = Con.prepareStatement("update UF_ChooseACat(?) set CatDes=?");
				Save.setInt(1, Key);
				Save.setString(2, CatNameTbl.getText());
				int row = Save.executeUpdate();
				JOptionPane.showMessageDialog(rootPane, "Category Edited");
				Con.close();
				Clear();
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(rootPane, e2);
			}
		}
	}


	private void RefreshClick() {
		model.setRowCount(0);
		list = new DAO().getListCategory();
		showTable();
	}


	Statement St1 = null;
	ResultSet Rs1 = null;
	private int ItemId;
	private void CountIt() {
		try {
			Con = DriverManager.getConnection("jdbc:sqlserver://LAPTOP-40OFFHMU\\SQLEXPRESS:1433;"
					+ "user=sa;password=123;databaseName=PetShopDb;encrypt=false");
			St1 = Con.createStatement();
			Rs1 = St1.executeQuery("select dbo.UF_MaxCatId() from CategoryTbl");
			Rs1.next();
			ItemId = Rs1.getInt(1)+1;
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	private void SaveBtnClick() {
		if(CatNameTbl.getText().isEmpty()) {
			JOptionPane.showMessageDialog(rootPane, "Missing Information");
		} else {
			try {
				CountIt();
				Con = DriverManager.getConnection("jdbc:sqlserver://LAPTOP-40OFFHMU\\SQLEXPRESS:1433;"
						+ "user=sa;password=123;databaseName=PetShopDb;encrypt=false");
				PreparedStatement Save = Con.prepareStatement("insert into UF_SelectAllCat() values(?,?)");
				Save.setInt(1, ItemId);
				Save.setString(2, CatNameTbl.getText());
				int row = Save.executeUpdate();
				JOptionPane.showMessageDialog(rootPane, "Category Added");
				Con.close();
				Clear();
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(rootPane, e2);
			}
		}
	}

}
