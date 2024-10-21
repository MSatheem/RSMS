package retailStore;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InboundProductList {
	Connection con;
	
	private int logNo;
	private Date date = new Date();
	private int supplierId;
	
	private int numberOfInboundProducts = 0;
	List<InboundProduct> productList = new ArrayList<InboundProduct>();

	public int getLogNo() {
		return logNo;
	}

	public void setLogNo(int logNo) {
		this.logNo = logNo;
	}

	//getters
	public Date getDate() {
		return date;
	}

	public int getSupplierId() {
		return supplierId;
	}
	
	//setters
	public void setDate(Date date) {
		this.date = date;
	}

	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}
	
	public void addtoList(InboundProduct product) {
		productList.add(product);
		numberOfInboundProducts++;
	}
	
	public int getNumberOfInboundProducts() {
		return numberOfInboundProducts;
	}

	//2d array for table population
	public Object[][] listToArray() {
		Object productArray[][] = new Object[numberOfInboundProducts][7];
		for(int i=0; i<numberOfInboundProducts; i++) {
			productArray[i][0] = productList.get(i).getId();
			productArray[i][1] = productList.get(i).getName();
			productArray[i][2] = productList.get(i).getPurchasePrice();
			productArray[i][3] = productList.get(i).getBatchNo();
			productArray[i][4] = productList.get(i).getMfgDate();
			productArray[i][5] = productList.get(i).getExpDate();
			productArray[i][6] = productList.get(i).getQuantityIn();
		}
		return productArray;
	}
	
	public void Save() {
		connectToDatabase();
		PreparedStatement pst,pst2;
		ResultSet rst;
		java.sql.Date dateToSave = new java.sql.Date(date.getTime());
		
		try {
			pst = con.prepareStatement("INSERT INTO inbound(date,supplierId) VALUES(?,?)");
			pst.setDate(1, dateToSave);
			pst.setInt(2, supplierId);
			pst.execute();
			
			pst = con.prepareStatement("SELECT LAST_INSERT_ID();");
			rst = pst.executeQuery();
			if(rst.next()) {
				logNo = rst.getInt(1);
				pst2 = con.prepareStatement("INSERT INTO inbound_product (logNo, productId, purchasePrice, batchNo, mfgDate, expDate, quantityRecieved,quantityInStock) VALUES (?, ?,?, ?, ?, ?, ?, ?); ");
				for(int i=0; i<numberOfInboundProducts; i++) {
					//saving the in bound list to database
					pst2.setInt(1, logNo);
					pst2.setInt(2, productList.get(i).getId());
					pst2.setDouble(3, productList.get(i).getPurchasePrice());
					pst2.setInt(4, productList.get(i).getBatchNo());
					pst2.setDate(5, productList.get(i).getMfgDate());
					pst2.setDate(6, productList.get(i).getExpDate());
					pst2.setInt(7, productList.get(i).getQuantityIn());
					pst2.setInt(8, productList.get(i).getQuantityIn());
					pst2.execute();
				}
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		closeConnection();
	}
	
	public void retrieveInboundDetails() {
		//date,logNo from inbound and poductId, purchasePrice,batchNo,mfgDate,expDate,quantityIOnstock from inbound product
		connectToDatabase();
		PreparedStatement pst;
		ResultSet rst;
		
		try {
			pst = con.prepareStatement("SELECT productId, purchasePrice, batchNo, mfgDate, expDate, quantityInStock from inbound_product WHERE logNo = ? AND quantityInStock > 0");
			pst.setInt(1, logNo);
			rst = pst.executeQuery();
			while(rst.next()) {//not empty
				InboundProduct product = new InboundProduct(rst.getInt(1));
				product.setPurchasePrice(rst.getDouble(2));
				product.setBatchNo(rst.getInt(3));
				product.setMfgDate(rst.getDate(4));
				product.setExpDate(rst.getDate(5));
				product.setQuantityInStore(rst.getInt(6));
				productList.add(product);
				numberOfInboundProducts++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void retrieveInboundDetails(int productId) {
		//date,logNo from in bound and poductId, purchasePrice,batchNo,mfgDate,expDate,quantityIOnstock from in bound product
		connectToDatabase();
		PreparedStatement pst;
		ResultSet rst;
		
		try {
			pst = con.prepareStatement("SELECT productId, purchasePrice, batchNo, mfgDate, expDate, quantityInStock from inbound_product WHERE logNo = ? AND productId = ? AND quantityInStock >0");
			pst.setInt(1, logNo);
			pst.setInt(2, productId);
			rst = pst.executeQuery();
			while(rst.next()) {//not empty
				InboundProduct product = new InboundProduct(rst.getInt(1));
				product.setPurchasePrice(rst.getDouble(2));
				product.setBatchNo(rst.getInt(3));
				product.setMfgDate(rst.getDate(4));
				product.setExpDate(rst.getDate(5));
				product.setQuantityInStore(rst.getInt(6));
				productList.add(product);
				numberOfInboundProducts++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//establishing connection to database
		private void connectToDatabase() {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/rsms","root","");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		//closing connection to database
		private void closeConnection() {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
}