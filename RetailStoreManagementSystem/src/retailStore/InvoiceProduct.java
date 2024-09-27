package retailStore;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InvoiceProduct extends ShelfProduct{
	private int quantity;
	private double price;
	private double totalPrice;
	private double discount = 0;
	private int inboundLogNo;
	private int batchNo;
	private int logNo;
	
	public InvoiceProduct(int productId) {
		Product product = new Product().getProductInfo(productId);
		this.setName(product.getName());
		this.setId(product.getId());
		product = null;
	}
	
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
		this.totalPrice = price * quantity;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	public int getInboundLogNo() {
		return inboundLogNo;
	}
	public void setInboundLogNo(int inboundLogNo) {
		this.inboundLogNo = inboundLogNo;
	}
	public int getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(int batchNo) {
		this.batchNo = batchNo;
	}
	
	public void updateQuantityInShelf() {
		PreparedStatement pst;
		int UpdatequantityInShelf = this.getQuantityInShelf() - this.quantity;
		try {
			pst = DataBaseConnection.con.prepareStatement("UPDATE shelf_product SET quantityInShelf = ? WHERE logNo = ? AND productId = ? AND batchNo= ? AND inboundLogNo = ? ");
			pst.setInt(1, UpdatequantityInShelf);
			pst.setInt(2, logNo);
			pst.setInt(3, getId());
			pst.setInt(4, batchNo);
			pst.setInt(5, inboundLogNo);
			pst.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getLogNo() {
		return logNo;
	}

	public void setLogNo(int logNo) {
		this.logNo = logNo;
	}
}
