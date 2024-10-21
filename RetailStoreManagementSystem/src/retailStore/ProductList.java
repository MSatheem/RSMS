package retailStore;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductList {

	List<Product> productDetailList = new ArrayList<Product>();
	int numberOfProductRead = 0;

	public void productInfo() {
		numberOfProductRead = 0;
		productDetailList.clear();
		try {
			PreparedStatement pst = DataBaseConnection.con.prepareStatement("SELECT id FROM product");
			ResultSet rst = pst.executeQuery();
			while (rst.next()) {
				Product product = new Product().getProductInfo(rst.getInt(1));
				productDetailList.add(product);
				numberOfProductRead++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void productInfo(String searchKey) {
		numberOfProductRead = 0;
		productDetailList.clear();
		try {
			PreparedStatement pst = DataBaseConnection.con.prepareStatement("SELECT id FROM product WHERE name LIKE '%"+searchKey+"%'");
			ResultSet rst = pst.executeQuery();
			while (rst.next()) {
				Product product = new Product().getProductInfo(rst.getInt(1));
				productDetailList.add(product);
				numberOfProductRead++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Object[][] tableArray() {
		Object[][] objects = new Object[numberOfProductRead][3];
		for (int i = 0; i < numberOfProductRead; i++) {
			objects[i][0] = productDetailList.get(i).getId();
			objects[i][1] = productDetailList.get(i).getName();
			objects[i][2] = productDetailList.get(i).getSupplierId();
		}
		return objects;
	}
	
	public List<Product> productList() {
		productInfo();
		return productDetailList;
	}
	
	public List<Product> productList(String key) {
		productInfo(key);
		return productDetailList;
	}
}