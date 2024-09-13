package retailStore;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InboundProductListDetails {
	Connection con;
	int numberOfEntries = 0; //storing the number of in bound logs in in bound table
	
	List<InboundProductList> inboundProductReadList = new ArrayList<InboundProductList>();
	
	public void getInboundProductDetails() {
		PreparedStatement pst;
		ResultSet rst;
		connectToDatabase();
		try {
			pst = con.prepareStatement("SELECT date, logNo FROM inbound");
			rst = pst.executeQuery();
			while(rst.next()) {
				InboundProductList inboundProductList =  new InboundProductList();
				java.sql.Date dateRead = rst.getDate(1);
				inboundProductList.setDate(dateRead);
				inboundProductList.setLogNo(rst.getInt(2));
				inboundProductList.retrieveInboundDetails();
				inboundProductReadList.add(inboundProductList);
				numberOfEntries++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//2d array to populate table
	public Object[][] tableArray() {
		getInboundProductDetails();
		int numberOfRowsRead = DataBaseConnection.getCount("inbound_product","productId");
		int rows = 0;
		Object[][] object1 =  new Object[numberOfRowsRead][9];
		while(rows != numberOfRowsRead) {
			for(int i=0; i<numberOfEntries; i++) { //number of in bound table entries 
				
				
				for(int j=0; j<inboundProductReadList.get(i).getNumberOfInboundProducts(); j++) { //number of tables associated with inbound table entries in inbound product table 
					object1[rows][0] = inboundProductReadList.get(i).getDate();
					object1[rows][1] = inboundProductReadList.get(i).getLogNo();
					object1[rows][2] = inboundProductReadList.get(i).productList.get(j).getId();
					object1[rows][3] = inboundProductReadList.get(i).productList.get(j).getName();
					object1[rows][4] = inboundProductReadList.get(i).productList.get(j).getBatchNo();
					object1[rows][5] = inboundProductReadList.get(i).productList.get(j).getMfgDate();
					object1[rows][6] = inboundProductReadList.get(i).productList.get(j).getExpDate();
					object1[rows][7] = inboundProductReadList.get(i).productList.get(j).getQuantityInStore();
					object1[rows][8] = inboundProductReadList.get(i).productList.get(j).getPurchasePrice();	
					if(rows != numberOfRowsRead) {
						rows++;
					} else {
						break;
					}
				}
			}
			
		}
		return object1;
		
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
}
