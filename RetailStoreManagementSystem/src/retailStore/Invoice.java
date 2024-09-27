package retailStore;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Invoice {
	private int customerId = 1;
	private Date date;
	private double total;
	
	List<InvoiceProduct> invoiceProductList = new ArrayList<InvoiceProduct>();

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public int getCustomerId() {
		return customerId;
	}

	public Date getDate() {
		return date;
	}

	public void add(InvoiceProduct invoiceProduct) {
		setTotal(getTotal() + invoiceProduct.getTotalPrice());
		invoiceProductList.add(invoiceProduct);
	}
	
	public void remove(InvoiceProduct invoiceProduct) {
	
	}
	
	
	public void printList() {
		System.out.println(invoiceProductList.size());
		for(int i=0; i<invoiceProductList.size(); i++) {
			InvoiceProduct product = invoiceProductList.get(i);
			System.out.println(product.toString());
		}
	}
	
	public Object[][] tableArray() {
		Object[][] obj = new Object[invoiceProductList.size()][6];
		for(int i=0; i<invoiceProductList.size(); i++) {
			System.out.println( invoiceProductList.get(i).toString());
			obj[i][0] = invoiceProductList.get(i).getId();
			obj[i][1] = invoiceProductList.get(i).getInboundLogNo();
			obj[i][2] = invoiceProductList.get(i).getBatchNo();
			obj[i][3] = invoiceProductList.get(i).getPrice();
			obj[i][4] = invoiceProductList.get(i).getSalePercentage();
		}
		
		return obj;
	}
	
	public void printInvoice() {
		System.out.println("Customer Id: " + customerId + "/nDate: " + this.date);
		printList();
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}
	
	public void saveInvoice() {
		java.sql.Date dateToSave = new java.sql.Date(date.getTime());
		int invoiceID;
		try {
			
			PreparedStatement pst,pst2,pst3;
			pst = DataBaseConnection.con.prepareStatement("INSERT INTO invoice(date, customerId) VALUES(?,?)");
			pst.setDate(1, dateToSave);
			pst.setInt(2, customerId);
			pst.execute();
			
			pst = DataBaseConnection.con.prepareStatement("SELECT LAST_INSERT_ID();");
			ResultSet rst = pst.executeQuery();
			if(rst.next()) {
				invoiceID = rst.getInt(1);
				pst2 = DataBaseConnection.con.prepareStatement("INSERT INTO invoice_product (billNumber, productID, inboundLogNo, quantity, pricePerUnit, totalPrice, discount) VALUES (?, ?, ?, ?, ?, ?, ?)");
				for(int i=0; i<invoiceProductList.size(); i++) {
					//saving the invoice list to database
					pst2.setInt(1, invoiceID);
					pst2.setInt(2, invoiceProductList.get(i).getId());
					pst2.setInt(3, invoiceProductList.get(i).getInboundLogNo());
					pst2.setInt(4, invoiceProductList.get(i).getQuantity());
					pst2.setDouble(5, invoiceProductList.get(i).getPrice());
					pst2.setDouble(6, invoiceProductList.get(i).getTotalPrice());
					pst2.setDouble(7, invoiceProductList.get(i).getSalePercentage());
					pst2.execute();
					
					//updateQuantityInShelf
					invoiceProductList.get(i).updateQuantityInShelf();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
