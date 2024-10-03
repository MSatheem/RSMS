package retailStore;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Product {
	private int id;
	private String name;
	private int supplierId;

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

	//getters
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
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
	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}
	
	//saving product to database
	public void saveProduct() {
		PreparedStatement pst;
		
		try {
			pst = DataBaseConnection.con.prepareStatement("INSERT INTO product(id,name, supplierId) VALUES(?,?,?)");
			pst.setInt(1, this.id);
			pst.setString(2, this.name);
			pst.setInt(3, this.supplierId);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Product getProductInfo(int id) {
		PreparedStatement pst;
		ResultSet rst;
		Product product = new Product(id);
		
		try {
			pst = DataBaseConnection.con.prepareStatement("SELECT name,supplierId FROM product WHERE id = ?");
			pst.setInt(1, id);
			rst = pst.executeQuery();
			if(rst.next()) {
				product.name = rst.getString(1);
				product.supplierId = rst.getInt(2);
				return product;
			} else { //empty result
				return null;
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
			pst = DataBaseConnection.con.prepareStatement("SELECT id,name,supplierId FROM product");
			rst = pst.executeQuery();
			int i=0;
			while(rst.next()) {
				int id = rst.getInt(1);
				String name = rst.getString(2);
				int supplierId = rst.getInt(3);
				product[i] = new Product(id,name,supplierId);
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
			pst = DataBaseConnection.con.prepareStatement("SELECT id,name,supplierId FROM product WHERE supplierId = ?");
			pst.setInt(1, supplierIdIn);
			rst = pst.executeQuery();
			int i=0;
			while(rst.next()) {
				int id = rst.getInt(1);
				String name = rst.getString(2);
				int supplierId = rst.getInt(3);
				product[i] = new Product(id,name,supplierId);
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
		return "Product [id=" + id + ", name=" + name + ", supplierId=" + supplierId + "]";
	}

	// 2d object Array for table population
	public Object[][] populateProductTable() {
		Product product[] = getAllProducts();
		Object objects[][] = new Object[getProductCount()][4];
		for (int i = 0; i < getProductCount(); i++) {
			objects[i][0] = product[i].id;
			objects[i][1] = product[i].name;
			objects[i][2] = product[i].supplierId;
		}
		return objects;
	}	
	
	// getting number of product saved
	public int getProductCount() {
		return DataBaseConnection.getCount("product","id");
	}
	
	public int getProductCount(int supplierId) {
		PreparedStatement pst;
		ResultSet rst;

		try {
			pst = DataBaseConnection.con.prepareStatement("SELECT COUNT(id) FROM product WHERE supplierId = ?");
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
		PreparedStatement pst;
		ResultSet rst;
		try {
			pst = DataBaseConnection.con.prepareStatement("SELECT * FROM product WHERE id = ?");
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
		PreparedStatement pst;
		ResultSet rst;
		
		try {
			pst = DataBaseConnection.con.prepareStatement("SELECT MAX(id) from product");
			rst = pst.executeQuery();
			if(rst.next()) {
				return rst.getInt(1)+1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return -1;
	}
	
	public void update() {
		PreparedStatement pst;
		
		try {
			pst = DataBaseConnection.con.prepareStatement("UPDATE product SET name = ?, supplierId = ? WHERE id = ? ");
			pst.setString(1, getName());
			pst.setInt(2, getSupplierId());
			pst.setInt(3, getId());
			pst.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
