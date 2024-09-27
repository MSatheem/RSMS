package retailStore;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class ReturnProduct extends InvoiceProduct {
	private int invoiceNumber;
	private int quantityOfReturn;
	private Date date;
	
	public ReturnProduct(int productId) {
		super(productId);
	}

	public void save() {
		
	}
	
	public int getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(int invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}
	
	public int getQuantityOfReturn() {
		return quantityOfReturn;
	}

	public void setQuantityOfReturn(int qunatityOfReturn) {
		this.quantityOfReturn = qunatityOfReturn;
	}
	
	private void updateShelfQuantityReturn() {
		if(readQuantityInShelf() > -1) {
			PreparedStatement pst;
			
			try {
				pst = DataBaseConnection.con.prepareStatement("UPDATE shelf_product SET quantityInShelf = ? WHERE inboundLogNo = ? AND productId = ? AND batchNo = ?");
				pst.setInt(1, readQuantityInShelf() + quantityOfReturn);
				pst.setInt(2, getInboundLogNo());
				pst.setInt(3, getId());
				pst.setInt(4, getBatchNo());
				pst.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	private int readQuantityInShelf() {
		PreparedStatement pst;
		ResultSet rst;
		try {
			pst = DataBaseConnection.con.prepareStatement("SELECT quantityInShelf FROM shelf_product WHERE productId = ? AND batchNo = ? AND inboundLogNo = ?" );
			pst.setInt(1, getId());
			pst.setInt(2, getBatchNo());
			pst.setInt(3, getInboundLogNo());
			rst = pst.executeQuery();
			if(rst.next()) {
				return rst.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return -1;
	}
	
	public void saveReturn() {
		PreparedStatement pst;
		java.sql.Date dateToSave = new java.sql.Date(date.getTime());
		try {
			pst = DataBaseConnection.con.prepareStatement("INSERT INTO return_product(invoiceNumber, productId, InboundLogNo,BatchNo,quantity,date) VALUES(?,?,?,?,?,?)");
			pst.setInt(1, invoiceNumber);
			pst.setInt(2, getId());
			pst.setInt(3, getInboundLogNo());
			pst.setInt(4, getBatchNo());
			pst.setInt(5, quantityOfReturn);
			pst.setDate(6, dateToSave);
			pst.execute();
			
			updateShelfQuantityReturn();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public void setDate(java.util.Date date) {
		this.date = new java.util.Date(date.getTime());
	}

}
