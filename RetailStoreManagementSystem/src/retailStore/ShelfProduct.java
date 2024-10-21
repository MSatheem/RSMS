package retailStore;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ShelfProduct extends InboundProduct{
	private int inboundLogNo;
	private double salePrice;
	private double salePercentage = 0.00;
	private int batchNo;
	private int quantityMovedToShelf;
	private int quantityInShelf;
	
	public int getQuantityMovedToShelf() {
		return quantityMovedToShelf;
	}
	public void setQuantityMovedToShelf(int quantityInShelf) {
		this.quantityMovedToShelf = quantityInShelf;
	}
	public ShelfProduct() {
		super();
	}
	public ShelfProduct(int id) {
		super(id);
	}
	public int getInboundLogNo() {
		return inboundLogNo;
	}
	public void setInboundLogNo(int inoundLogNo) {
		this.inboundLogNo = inoundLogNo;
	}
	public double getSalePrice() {
		return salePrice;
	}
	public void setSalePrice(double salePrice) {
		this.salePrice = salePrice;
	}
	public double getSalePercentage() {
		return salePercentage;
	}
	public void setSalePercentage(double salePercentage) {
		this.salePercentage = salePercentage;
	}
	public int getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(int batchNo) {
		this.batchNo = batchNo;
	}
	public int getQuantityInShelf() {
		return quantityInShelf;
	}
	public void setQuantityInShelf(int quantityInShelf) {
		this.quantityInShelf = quantityInShelf;
	}
	
	public boolean updateQuantity() {
		PreparedStatement pst;
		try {
			int quantityInStockRead = getQuantityInStock();
			quantityInStockRead = quantityInStockRead - quantityMovedToShelf;
			pst = DataBaseConnection.con.prepareStatement("UPDATE inbound_product SET quantityInStock = ? WHERE logNo = ? AND productId = ? AND batchNo = ?");
			pst.setInt(1, quantityInStockRead);
			pst.setInt(2, this.inboundLogNo);
			pst.setInt(3, this.getId());
			pst.setInt(4, this.batchNo);
			pst.execute();
			return true; //successful update
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return false;
	}	
	
	private int getQuantityInStock() {
		int quantityInStock = 0;;
		PreparedStatement pst;
		ResultSet rst;
		try {
			pst = DataBaseConnection.con.prepareStatement("SELECT quantityInStock FROM inbound_product WHERE logNo = ? AND productId = ? AND batchNo = ?");
			pst.setInt(1, this.inboundLogNo);
			pst.setInt(2, this.getId());
			pst.setInt(3, this.batchNo);
			
			rst = pst.executeQuery();
			while(rst.next()) {
				quantityInStock = rst.getInt(1);
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return quantityInStock;
	}
}
