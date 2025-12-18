package retailStore;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class SalesReport {
	
	LinkedList<SalesNode> salesList = new LinkedList<SalesNode>();
	private double totalSales = 0.00;
	private int productSold = 0;
	private int productsReturned = 0;
	private double profitEarned=0.00;
	
	public SalesReport() {
		retrieveAllInvoice();
	}
	
	public SalesReport(int month, int year) {
		retrieveAllInvoice(month, year);
	}
	
	
	private void retrieveAllInvoice() {
		PreparedStatement pst;
		ResultSet rst;
		
		try {
			pst = DataBaseConnection.con.prepareStatement("SELECT number from invoice");
			rst = pst.executeQuery();
			while(rst.next()) {
				SalesNode node = new SalesNode(rst.getInt(1));
				totalSales = totalSales + node.saleValue();
				productSold = productSold + node.numberOfProducts();
				productsReturned = productsReturned + node.getNumberOfProductReturned();
				profitEarned = profitEarned + node.getProfit();
				salesList.add(node);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void retrieveAllInvoice(int month, int year) {
		PreparedStatement pst;
		ResultSet rst;
		
		try {
			pst = DataBaseConnection.con.prepareStatement("SELECT number from invoice WHERE YEAR(date) = ? AND MONTH(date) = ?");
			pst.setInt(1, year);
			pst.setInt(2, month);
			rst = pst.executeQuery();
			while(rst.next()) {
				SalesNode node = new SalesNode(rst.getInt(1));
				totalSales = totalSales + node.saleValue();
				productSold = productSold + node.numberOfProducts();
				productsReturned = productsReturned + node.getNumberOfProductReturned();
				profitEarned = profitEarned + node.getProfit();
				salesList.add(node);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	public double getTotalSales() {
		return totalSales;
	}
	
	public int getProductSold() {
		return productSold;
	}

	public int getProductsReturned() {
		return productsReturned;
	}
	public double getProfit() {
		return profitEarned;
	}
}
