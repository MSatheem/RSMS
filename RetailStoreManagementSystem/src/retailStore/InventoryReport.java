package retailStore;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InventoryReport {
	Product[] product;
	int yearSelected, monthSelected;
	
	public InventoryReport() {
	}
	
	public InventoryReport(int yearSelected, int monthSelected) {
		this.yearSelected = yearSelected;
		this.monthSelected = monthSelected;
	}

	private int numberOfProducts() {
		product = new Product().getAllProducts();
		return product.length;
	}
	
	public Object[][] genreatereport() {
		Object[][] report = new Object[numberOfProducts()][8];
		for(int i = 0; i<numberOfProducts(); i++) {
			report[i][0] = product[i].getId();
			report[i][1] = product[i].getName();
			report[i][2] = storeCount(product[i].getId());
			report[i][3] = shelfCount(product[i].getId());
			report[i][4] = storeCount(product[i].getId()) + shelfCount(product[i].getId()) ;
			report[i][5] = soldCount(product[i].getId());
			report[i][6] = returnCount(product[i].getId());
			report[i][7] = inboundCount(product[i].getId());
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
	
	private int soldCount(int productId) {
		int count = 0;
		try {
			PreparedStatement pst = DataBaseConnection.con.prepareStatement("SELECT SUM(quantity) FROM invoice_product WHERE productId = ? AND billNumber IN (SELECT number FROM invoice WHERE YEAR(date) = ? AND MONTH(date) = ?)");
			pst.setInt(1, productId);
			pst.setInt(2, yearSelected);
			pst.setInt(3, monthSelected);
			ResultSet rst = pst.executeQuery();
			while(rst.next()) {
				count += rst.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
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
	
	private int returnCount(int productId) {
		try {
			PreparedStatement pst = DataBaseConnection.con.prepareStatement("SELECT SUM(quantity) FROM return_product WHERE productId = ? AND YEAR(date) = ? AND MONTH(date) = ?");
			pst.setInt(1, productId);
			pst.setInt(2, yearSelected);
			pst.setInt(3, monthSelected);
			ResultSet rst = pst.executeQuery();
			if(rst.next()) {
				return rst.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	private int inboundCount(int productId) {
		try {
			PreparedStatement pst = DataBaseConnection.con.prepareStatement("SELECT SUM(quantityRecieved) FROM inbound_product WHERE productId = ? AND logNo IN (SELECT logNo FROM inbound WHERE YEAR(date) = ? AND MONTH(date) = ?)");
			pst.setInt(1, productId);
			pst.setInt(2, yearSelected);
			pst.setInt(3, monthSelected);
			ResultSet rst = pst.executeQuery();
			if(rst.next()) {
				return rst.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
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
	
	
}