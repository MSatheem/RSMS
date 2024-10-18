package retailStore;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ShelfList {
	Object[][] obj;
	
	private void shelfInfo(String searchKey) {
		ProductList productList = new ProductList();
		List<Product> product = productList.productList(searchKey);
		obj = new Object[product.size()][3];
		for(int i=0; i<product.size(); i++) {
			obj[i][0] = product.get(i).getId();
			obj[i][1] = product.get(i).getName();
			try {
				PreparedStatement pst = DataBaseConnection.con.prepareStatement("SELECT SUM(quantityInShelf) FROM shelf_product WHERE productId = ?");
				pst.setInt(1, product.get(i).getId());
				ResultSet rst = pst.executeQuery();
				while (rst.next()) {
					obj[i][2] = rst.getInt(1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
	}
	
	private void shelfInfo() {
		ProductList productList = new ProductList();
		List<Product> product = productList.productList();
		obj = new Object[product.size()][3];
		for(int i=0; i<product.size(); i++) {
			obj[i][0] = product.get(i).getId();
			obj[i][1] = product.get(i).getName();
			try {
				PreparedStatement pst = DataBaseConnection.con.prepareStatement("SELECT SUM(quantityInShelf) FROM shelf_product WHERE productId = ?");
				pst.setInt(1, product.get(i).getId());
				ResultSet rst = pst.executeQuery();
				while (rst.next()) {
					obj[i][2] = rst.getInt(1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
	}
	
	public Object[][] tableArray(String searchKey) {
		if(searchKey == null) {
			shelfInfo();
		} else {
			shelfInfo(searchKey);
		}
		return obj;
	}
}
