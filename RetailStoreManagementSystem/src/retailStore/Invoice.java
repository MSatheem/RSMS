package retailStore;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Invoice {
	private int customerId;
	private Date date;
	
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

	public void remove(InvoiceProduct invoiceProduct) {
	
	}
	
	public void printList() {
		System.out.println(invoiceProductList.size());
		for(int i=0; i<invoiceProductList.size(); i++) {
			InvoiceProduct product = invoiceProductList.get(i);
			System.out.println(product.toString());
		}
	}
	
	public void printInvoice() {
		System.out.println("Customer Id: " + customerId + "/nDate: " + this.date);
		printList();
	}

}
