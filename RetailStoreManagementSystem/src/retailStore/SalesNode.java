package retailStore;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SalesNode {
	private Invoice invoice;
	List<ReturnProduct> returnProductList = new ArrayList<ReturnProduct>();
	private int numberOfProductReturned = 0;
	private double profit = 0.00;
	
	public SalesNode(int invoiceNumber) {
		invoice = new Invoice();
		invoice.setInvoiceNumber(invoiceNumber);
		invoice.readInvoice();
		hasReturn();
		profitcalculator();
	}
	
	private boolean hasReturn() {
		PreparedStatement pst;
		ResultSet rst;
		
		try {
			pst = DataBaseConnection.con.prepareStatement("SELECT productId, inboundLogNo, batchNo, quantity FROM return_product WHERE invoiceNumber = ?");
			pst.setInt(1, invoice.getInvoiceNumber());
			rst = pst.executeQuery();
			if(rst.next()) {
				ReturnProduct returnProduct = new ReturnProduct(rst.getInt(1));
				returnProduct.setInboundLogNo(rst.getInt(2));
				returnProduct.setBatchNo(rst.getInt(3));
				returnProduct.setQuantityOfReturn(rst.getInt(4));
				returnProductList.add(returnProduct);
				for(int i=0; i<invoice.invoiceProductList.size(); i++) {
					if(invoice.invoiceProductList.get(i).getId() == returnProduct.getId()) {
						if(invoice.invoiceProductList.get(i).getBatchNo() == returnProduct.getBatchNo()) {
							if(invoice.invoiceProductList.get(i).getInboundLogNo() == returnProduct.getInboundLogNo()) {
								invoice.setTotal(invoice.getTotal() - invoice.invoiceProductList.get(i).getPrice() * returnProduct.getQuantityOfReturn());
								invoice.invoiceProductList.get(i).setQuantity(invoice.invoiceProductList.get(i).getQuantity() - returnProduct.getQuantityOfReturn());
								setNumberOfProductReturned(getNumberOfProductReturned() + returnProduct.getQuantityOfReturn());
							}
						}
					}
				}
				
			}
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public double saleValue() {
		return invoice.getTotal();
	}
	
	public int numberOfProducts() {
		return invoice.getNumberOfProducts();
	}

	public int getNumberOfProductReturned() {
		return numberOfProductReturned;
	}

	public void setNumberOfProductReturned(int numberOfProductReturned) {
		this.numberOfProductReturned = numberOfProductReturned;
	}
	
	private void profitcalculator() {
		for(int i=0; i<invoice.invoiceProductList.size(); i++) {
			PreparedStatement pst;
			ResultSet rst;
			
			try {
				pst = DataBaseConnection.con.prepareStatement("SELECT purchasePrice FROM inbound_product WHERE logNo = ? AND productId = ? AND batchNo = ?");
				pst.setInt(1, invoice.invoiceProductList.get(i).getInboundLogNo());
				pst.setInt(2, invoice.invoiceProductList.get(i).getId());
				pst.setInt(3, invoice.invoiceProductList.get(i).getBatchNo());
				rst=pst.executeQuery();
				if(rst.next()) {
					double profitCalculate = (invoice.invoiceProductList.get(i).getPrice() - rst.getDouble(1)) * invoice.invoiceProductList.get(i).getQuantity();
					profit = profit + profitCalculate;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public double getProfit() {
		return profit;
	}
} 
