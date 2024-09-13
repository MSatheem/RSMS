package retailStore;

import java.sql.Date;
public class InboundProduct extends Product{

	private double purchasePrice;
	private double salePrice;
	private int batchNo;
	private Date mfgDate;
	private Date expDate;
	private int quantityIn; //quantity received from supplier
	private int quantityInStore; //quantity currently in store
	public InboundProduct() {
	}
	
	public InboundProduct(int id) {
		Product product = new Product().getProductInfo(id);
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
	
	public int getQuantityInStore() {
		return quantityInStore;
	}

	public void setQuantityInStore(int quantityInStore) {
		this.quantityInStore = quantityInStore;
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
	
	
}
