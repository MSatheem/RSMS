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
	private double total = 0.00;
	private int invoiceNumber;
	
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
	
	
	public Object[][] tableArray() {
		Object[][] obj = new Object[invoiceProductList.size()][6];
		for(int i=0; i<invoiceProductList.size(); i++) {
			obj[i][0] = invoiceProductList.get(i).getId();
			obj[i][1] = invoiceProductList.get(i).getInboundLogNo();
			obj[i][2] = invoiceProductList.get(i).getBatchNo();
			obj[i][3] = invoiceProductList.get(i).getPrice();
			obj[i][4] = invoiceProductList.get(i).getSalePercentage();
		}
		return obj;
	}
	
	public Object[][] tableArrayRead() {
		Object[][] obj = new Object[invoiceProductList.size()][8];
		for(int i=0; i<invoiceProductList.size(); i++) {
			obj[i][0] = invoiceProductList.get(i).getId();
			obj[i][1] = invoiceProductList.get(i).getName();
			obj[i][2] = invoiceProductList.get(i).getInboundLogNo();
			obj[i][3] = invoiceProductList.get(i).getBatchNo();
			obj[i][4] = invoiceProductList.get(i).getPrice();
			obj[i][5] = invoiceProductList.get(i).getSalePercentage();
			obj[i][6] = invoiceProductList.get(i).getQuantity();
			total += invoiceProductList.get(i).getPrice() * invoiceProductList.get(i).getQuantity();
			obj[i][7] = invoiceProductList.get(i).getPrice() * invoiceProductList.get(i).getQuantity();;
		}
		return obj;
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
			
			PreparedStatement pst,pst2;
			pst = DataBaseConnection.con.prepareStatement("INSERT INTO invoice(date, customerId) VALUES(?,?)");
			pst.setDate(1, dateToSave);
			pst.setInt(2, customerId);
			pst.execute();
			
			pst = DataBaseConnection.con.prepareStatement("SELECT LAST_INSERT_ID();");
			ResultSet rst = pst.executeQuery();
			if(rst.next()) {
				invoiceID = rst.getInt(1);
				pst2 = DataBaseConnection.con.prepareStatement("INSERT INTO invoice_product (billNumber, productID, inboundLogNo, quantity, pricePerUnit, totalPrice, discount, batchNo) VALUES (?,?, ?, ?, ?, ?, ?, ?)");
				for(int i=0; i<invoiceProductList.size(); i++) {
					//saving the invoice list to database
					pst2.setInt(1, invoiceID);
					pst2.setInt(2, invoiceProductList.get(i).getId());
					pst2.setInt(3, invoiceProductList.get(i).getInboundLogNo());
					pst2.setInt(4, invoiceProductList.get(i).getQuantity());
					pst2.setDouble(5, invoiceProductList.get(i).getPrice());
					pst2.setDouble(6, invoiceProductList.get(i).getTotalPrice());
					pst2.setDouble(7, invoiceProductList.get(i).getSalePercentage());
					pst2.setInt(8, invoiceProductList.get(i).getBatchNo());
					pst2.execute();
					
					//updateQuantityInShelf
					invoiceProductList.get(i).updateQuantityInShelf();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void readInvoice() {
		PreparedStatement pst,pst2;
		ResultSet rst,rst2;
		
		//read from database
		try {
			pst = DataBaseConnection.con.prepareStatement("SELECT customerId, date FROM invoice where number = ?");
			pst.setInt(1, invoiceNumber);
			rst = pst.executeQuery();
			if(rst.next()) {
				setDate(rst.getDate(2));
				pst2 = DataBaseConnection.con.prepareStatement("SELECT productID, inboundLogNo, quantity, pricePerUnit, discount, batchNo FROM invoice_product WHERE billNumber = ?"); 
				pst2.setInt(1, invoiceNumber);
				rst2 = pst2.executeQuery();
				while(rst2.next()) {
					InvoiceProduct invoiceProduct = new InvoiceProduct(rst2.getInt(1));
					invoiceProduct.setInboundLogNo(rst2.getInt(2));
					invoiceProduct.setQuantity(rst2.getInt(3));
					invoiceProduct.setPrice(rst2.getDouble(4));
					invoiceProduct.setDiscount(rst2.getDouble(5));
					invoiceProduct.setBatchNo(rst2.getInt(6));
					invoiceProductList.add(invoiceProduct);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	
	public boolean isSaved() {
		PreparedStatement pst;
		ResultSet rst;
		try {
			pst = DataBaseConnection.con.prepareStatement("SELECT number FROM invoice WHERE number = ?");
			pst.setInt(1, invoiceNumber);
			rst = pst.executeQuery();
			if(rst.next()) {
				readInvoice();
				return true;
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public int getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(int invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

}
