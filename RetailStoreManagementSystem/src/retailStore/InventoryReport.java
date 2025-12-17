package retailStore;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InventoryReport {
	Product[] product;
	int yearSelected, monthSelected;
	
	public InventoryReport() {
	}
	

	private int numberOfProducts() {
		product = new Product().getAllProducts();
		return product.length;
	}
	
	public Object[][] currentInventory() {
		Object[][] report = new Object[numberOfProducts()][5];
		for(int i = 0; i<numberOfProducts(); i++) {
			report[i][0] = product[i].getId();
			report[i][1] = product[i].getName();
			report[i][2] = storeCount(product[i].getId());
			report[i][3] = shelfCount(product[i].getId());
			report[i][4] = storeCount(product[i].getId()) + shelfCount(product[i].getId()) ;
		}
		return report;
	}
	
	private  int storeCount(int productId) {
		try {
			PreparedStatement pst = DataBaseConnection.con.prepareStatement("SELECT SUM(quantityInStock) FROM inbound_product WHERE productId = ?");
			pst.setInt(1, productId);
			ResultSet rst = pst.executeQuery();
			if(rst.next()) {
				return rst.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	private int shelfCount(int productId) { //number of products in the shelf
		try {
			PreparedStatement pst = DataBaseConnection.con.prepareStatement("SELECT SUM(quantityInShelf) FROM shelf_product WHERE productId = ?");
			pst.setInt(1, productId);
			ResultSet rst = pst.executeQuery();
			if(rst.next()) {
				return rst.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
		
	
	
	
}