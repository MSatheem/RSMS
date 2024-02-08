package retailStore;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InboundProduct extends Product{

	private double purchasePrice;
	private double salePrice;
	private int batchNo;
	private Date mfgDate;
	private Date expDate;
	private int quantityIn;
	public InboundProduct() {
	}
	
	public InboundProduct(int id) {
		Product product = new Product().getProductInfo(id);
		this.setInStock(product.getInStock());
		this.setId(id);
		this.setName(product.getName());
	}
	
	//getters
	public double getPurchasePrice() {
		return purchasePrice;
	}
	public double getSalePrice() {
		return salePrice;
	}
	public int getBatchNo() {
		return batchNo;
	}
	public Date getMfgDate() {
		return mfgDate;
	}
	public Date getExpDate() {
		return expDate;
	}
	public int getQuantityIn() {
		return quantityIn;
	}
	
	//setters
	public void setPurchasePrice(double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}
	public void setSalePrice(double salePrice) {
		this.salePrice = salePrice;
	}
	public void setBatchNo(int batchNo) {
		this.batchNo = batchNo;
	}
	public void setMfgDate(Date mfgDate) {
		this.mfgDate = mfgDate;
	}
	public void setExpDate(Date expDate) {
		this.expDate = expDate;
	}
	public void setQuantityIn(int quantityIn) {
		this.quantityIn = quantityIn;
	}

	public void updateQuantity() {
		connectToDatabase();
		PreparedStatement pst;
		try {
			pst = con.prepareStatement("UPDATE product SET inStock = ? WHERE id = ?");
			pst.setInt(1, this.getInStock()+quantityIn);
			pst.setInt(2, this.getId());
			pst.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}	
	
	@Override
	public String toString() {
		return "InboundProduct [purchasePrice=" + purchasePrice + ", salePrice=" + salePrice + ", batchNo=" + batchNo
				+ ", mfgDate=" + mfgDate + ", expDate=" + expDate + ", quantityIn=" + quantityIn + ", getId()=" + getId() + ", getName()=" + getName() + "]";
	}
}
