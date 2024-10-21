package retailStore;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReturnProductRead {
	private int invoiceNumber;
	List<ReturnProduct> ls = new ArrayList<ReturnProduct>();

	public ReturnProductRead(int invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public int getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(int invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}
	
	public int returnableCount(InvoiceProduct invoiceProduct) {
		try {
			PreparedStatement pst = DataBaseConnection.con.prepareStatement("SELECT SUM(quantity) FROM return_product WHERE productId = ? AND InboundLogNo = ? AND BatchNo = ? AND invoiceNumber = ?");
			pst.setInt(1, invoiceProduct.getId());
			pst.setInt(2, invoiceProduct.getInboundLogNo());
			pst.setInt(3, invoiceProduct.getBatchNo());
			pst.setInt(4, getInvoiceNumber());
			ResultSet rst = pst.executeQuery();
			rst = pst.executeQuery();
			if(rst.next()) {
				return invoiceProduct.getQuantity()-rst.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return invoiceProduct.getQuantity();
	}
}
