package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.Category;
import model.Customer;
import model.Pet;
import model.User;

public class DAO {
private Connection conn;
	
	public DAO() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection("jdbc:sqlserver://LAPTOP-40OFFHMU\\SQLEXPRESS:1433;"
					+ "user=sa;password=123;databaseName=PetShopDb;encrypt=false");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public ArrayList<Customer> getListCustomer() {
		ArrayList<Customer> list = new ArrayList<>();
		
		String sql = "SELECT * FROM UF_SelectAllCustomer()";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Customer c = new Customer();
				c.setCusId(rs.getInt("CustId"));
				c.setCustName(rs.getString("CustName"));
				c.setCustAdd(rs.getString("CustAdd"));
				c.setCustPhone(rs.getInt("CustPhone"));
				
				list.add(c);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		try {
			if (conn!=null) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return list;
	}
	
	public void xoaCustomer(int id) {
		
		try {
			String sql = "delete UF_deleteACust(?) from CustomerTbl";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			int rs = ps.executeUpdate();
	
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		try {
			if (conn!=null) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	//--------------------------User--------------------------------------------
	
	public ArrayList<User> getListUser() {
		ArrayList<User> list = new ArrayList<>();
		
		String sql = "SELECT * FROM UF_SelectAllUser()";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				User c = new User();
				c.setId(rs.getInt("UId"));
				c.setName(rs.getString("UName"));
				c.setPassword(rs.getString("UPass"));
				
				list.add(c);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		try {
			if (conn!=null) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return list;
	}
	
	public void deleteUser(int id) {
		
		try {
			String sql = "delete UF_ChooseAUser(?) from UserTbl";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			int rs = ps.executeUpdate();
	
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		try {
			if (conn!=null) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	//------------------------------Category-------------------------------------------
	
	public ArrayList<Category> getListCategory() {
		ArrayList<Category> list = new ArrayList<>();
		
		String sql = "SELECT * FROM UF_SelectAllCat()";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Category c = new Category();
				c.setId(rs.getInt("CatId"));
				c.setName(rs.getString("CatDes"));
				list.add(c);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		try {
			if (conn!=null) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return list;
	}
	
	public void deleteCategory(int id) {
		
		try {
			String sql = "delete UF_ChooseACat(?) from CategoryTbl";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			int rs = ps.executeUpdate();
	
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		try {
			if (conn!=null) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	//--------------------Pet----------------------------------------
	
	public ArrayList<Pet> getListPet() {
		ArrayList<Pet> list = new ArrayList<>();
		
		String sql = "SELECT * FROM UF_SelectAllPet()";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Pet c = new Pet();
				c.setPId(rs.getInt("PId"));
				c.setPName(rs.getString("PName"));
				c.setPCat(rs.getInt("PCat"));
				c.setPqty(rs.getInt("Pqty"));
				c.setPprice(rs.getInt("Pprice"));
				list.add(c);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		try {
			if (conn!=null) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return list;
	}
	
	public void deletePet(int id) {
		
		try {
			String sql = "delete UF_ChooseAPet(?) from PetTbl";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			int rs = ps.executeUpdate();
	
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		try {
			if (conn!=null) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	//----------------------Bill-----------------------------------------
	

}
