package retailStore;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Product {
	private int id;
	private String name;
	private int supplierId;
	private int quantityInStock=0;
	
	Connection con;
	
	//constructors
	public Product(int id) {
		this.id = id;
	}
	
	public Product(int id, String name, int supplierId) {
		this.id = id;
		this.name = name;
		this.supplierId = supplierId;
	}
	
	public Product(String name, int supplierId) {
		this.id = nextId();
		this.name = name;
		this.supplierId = supplierId;
	}

	
	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", supplierId=" + supplierId + ", quantityInStock="
				+ quantityInStock + "]";
	}

	//getters
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public int getQuantityInStock() {
		return quantityInStock;
	}
	public int getSupplierId() {
		return supplierId;
	}
	
	//setters
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setQuantityInStock(int quantityInStock) {
		this.quantityInStock = quantityInStock;
	}
	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}
	
	//saving product to database
	public void saveProduct() {
		connectToDatabase();
		PreparedStatement pst;
		
		try {
			pst = con.prepareStatement("INSERT INTO product(id,name, supplierId, inStock) VALUES(?,?,?,?)");
			pst.setInt(1, this.id);
			pst.setString(2, this.name);
			pst.setInt(3, this.supplierId);
			pst.setInt(4, this.quantityInStock);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		closeConnection();
	}
	
	public Product getProductInfo(int id) {
		connectToDatabase();
		PreparedStatement pst;
		ResultSet rst;
		Product product = new Product(id);
		
		try {
			pst = con.prepareStatement("SELECT name,supplierId,inStock FROM product WHERE id = ?");
			rst = pst.executeQuery();
			if(rst.next()) {
				product.name = rst.getString(1);
				product.supplierId = rst.getInt(2);
				product.quantityInStock = rst.getInt(3);
				return product;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;	
	}
	
	public boolean isProductSaved() {
		connectToDatabase();
		PreparedStatement pst;
		ResultSet rst;
		try {
			pst = con.prepareStatement("SELECT * FROM CUSTOMER WHERE id = ?");
			pst.setInt(1, id);
			rst = pst.executeQuery();
			if(rst.next()) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	//retrieving next id that will be allocated
	private int nextId() {
		connectToDatabase();
		PreparedStatement pst;
		ResultSet rst;
		
		try {
			pst = con.prepareStatement("SELECT MAX(id) from product");
			rst = pst.executeQuery();
			if(rst.next()) {
				return rst.getInt(1)+1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		closeConnection();
		return -1;
	}
	
	
	//establishing connection to database
	private void connectToDatabase() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/rsms","root","");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//closing connection to database
	private void closeConnection() {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
