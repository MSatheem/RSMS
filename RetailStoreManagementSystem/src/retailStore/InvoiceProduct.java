package retailStore;

public class InvoiceProduct {
	private int productId;
	private String name;
	private int qunatity;
	private double price;
	private double totalPrice;
	private double discount = 0;
	private int inboundLogNo;
	private int batchNo;
	
	public InvoiceProduct(int productId, String name, int qunatity, double price, double totalPrice, double discount,
			int inboundLogNo, int batchNo) {
		super();
		this.productId = productId;
		this.name = name;
		this.qunatity = qunatity;
		this.price = price;
		this.totalPrice = totalPrice;
		this.discount = discount;
		this.inboundLogNo = inboundLogNo;
		this.batchNo = batchNo;
	}

	@Override
	public String toString() {
		return "InvoiceProduct [productId=" + productId + ", name=" + name + ", qunatity=" + qunatity + ", price="
				+ price + ", totalPrice=" + totalPrice + ", discount=" + discount + ", inboundLogNo=" + inboundLogNo
				+ ", batchNo=" + batchNo + "]";
	}
	
	
	

}
