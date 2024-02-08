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
	private int inStock=0;
	
	Connection con;
	//constructors
	public Product() {}
	
	
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
	
	

	public Product(int id, String name, int supplierId, int quantityInStock) {
		this.id = id;
		this.name = name;
		this.supplierId = supplierId;
		this.inStock = quantityInStock;
	}

	//getters
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public int getInStock() {
		return inStock;
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
	public void setInStock(int inStock) {
		this.inStock = inStock;
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
			pst.setInt(4, this.inStock);
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
			pst.setInt(1, id);
			rst = pst.executeQuery();
			if(rst.next()) {
				product.name = rst.getString(1);
				product.supplierId = rst.getInt(2);
				product.inStock = rst.getInt(3);
				return product;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;	
	}
	
	public Product[] getAllProducts() {
		Product product[] = new Product[getProductCount()];
		PreparedStatement pst;
		ResultSet rst;
		
		try {
			pst = con.prepareStatement("SELECT id,name,supplierId,inStock FROM product");
			rst = pst.executeQuery();
			int i=0;
			while(rst.next()) {
				int id = rst.getInt(1);
				String name = rst.getString(2);
				int supplierId = rst.getInt(3);
				int inStock = rst.getInt(4);
				product[i] = new Product(id,name,supplierId,inStock);
				i++;
			}
			return product;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//getting details of products which  are supplied by particular supplier
	public Product[] getAllProducts(int supplierIdIn) {
		Product product[] = new Product[getProductCount(supplierIdIn)];
		PreparedStatement pst;
		ResultSet rst;
		
		try {
			pst = con.prepareStatement("SELECT id,name,supplierId,inStock FROM product WHERE supplierId = ?");
			pst.setInt(1, supplierIdIn);
			rst = pst.executeQuery();
			int i=0;
			while(rst.next()) {
				int id = rst.getInt(1);
				String name = rst.getString(2);
				int supplierId = rst.getInt(3);
				int inStock = rst.getInt(4);
				product[i] = new Product(id,name,supplierId,inStock);
				System.out.println(product[i].toString());
				i++;
			}
			return product;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", supplierId=" + supplierId + ", quantityInStock="
				+ inStock + "]";
	}

	// 2d object Array for table population
	public Object[][] populateProductTable() {
		Product product[] = getAllProducts();
		Object objects[][] = new Object[getProductCount()][3];
		for (int i = 0; i < getProductCount(); i++) {
			objects[i][0] = product[i].id;
			objects[i][1] = product[i].name;
			objects[i][2] = product[i].supplierId;
			objects[i][3] = product[i].inStock;
		}
		return objects;
	}	
	
	// getting number of product saved
	public int getProductCount() {
		connectToDatabase();
		PreparedStatement pst;
		ResultSet rst;

		try {
			pst = con.prepareStatement("SELECT COUNT(id) FROM product");
			rst = pst.executeQuery();

			if (rst.next()) {
				return rst.getInt(1);
			} else { // table empty
				return 0;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int getProductCount(int supplierId) {
		connectToDatabase();
		PreparedStatement pst;
		ResultSet rst;

		try {
			pst = con.prepareStatement("SELECT COUNT(id) FROM product WHERE id = ?");
			pst.setInt(1, supplierId);
			rst = pst.executeQuery();

			if (rst.next()) {
				return rst.getInt(1);
			} else { // table empty
				return 0;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public boolean isSaved() {
		connectToDatabase();
		PreparedStatement pst;
		ResultSet rst;
		try {
			pst = con.prepareStatement("SELECT * FROM product WHERE id = ?");
			pst.setInt(1, this.id);
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
	protected void connectToDatabase() {
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
