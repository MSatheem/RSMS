package retailStore;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InboundProductListDetails {
	int numberOfDataRead = 0; //storing the number of in bound logs in in bound table
	
	
	List<InboundProductList> inboundProductReadList = new ArrayList<InboundProductList>();
	
	public void getInboundProductDetails() {
		inboundProductReadList.clear();
		numberOfDataRead = 0;
		try {
			PreparedStatement pst = DataBaseConnection.con.prepareStatement("SELECT date, logNo FROM inbound");
			ResultSet rst = pst.executeQuery();
			while(rst.next()) {
				InboundProductList inboundProductList =  new InboundProductList();
				java.sql.Date dateRead = rst.getDate(1);
				inboundProductList.setDate(dateRead);
				inboundProductList.setLogNo(rst.getInt(2));
				inboundProductList.retrieveInboundDetails();
				inboundProductReadList.add(inboundProductList);
				numberOfDataRead += inboundProductList.getNumberOfInboundProducts();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void getInboundProductDetails(int productId) {
		inboundProductReadList.clear();
		numberOfDataRead = 0;
		try {
			PreparedStatement pst = DataBaseConnection.con.prepareStatement("SELECT date, logNo FROM inbound WHERE logNo = (SELECT logNo FROM inbound_product WHERE productId = ? AND quantityInStock > 0)");
			pst.setInt(1, productId);
			ResultSet rst = pst.executeQuery();
			while(rst.next()) {
				InboundProductList inboundProductList =  new InboundProductList();
				java.sql.Date dateRead = rst.getDate(1);
				inboundProductList.setDate(dateRead);
				inboundProductList.setLogNo(rst.getInt(2));
				inboundProductList.retrieveInboundDetails(productId);
				inboundProductReadList.add(inboundProductList);
				numberOfDataRead += inboundProductList.getNumberOfInboundProducts();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//2d array to populate table
	public Object[][] tableArrayAll(int productId) {
		if (productId == -1) {
			getInboundProductDetails();
		} else {
			getInboundProductDetails(productId);
		}
		int rows = 0;
		Object[][] object1 = new Object[numberOfDataRead][9];
		for (int i = 0; i < inboundProductReadList.size(); i++) { // number of in bound table entries
			for (int j = 0; j < inboundProductReadList.get(i).getNumberOfInboundProducts(); j++) { // number of tables associated with in bound table entries in inbound_product table
				object1[rows][0] = inboundProductReadList.get(i).getDate();
				object1[rows][1] = inboundProductReadList.get(i).getLogNo();
				object1[rows][2] = inboundProductReadList.get(i).productList.get(j).getId();
				object1[rows][3] = inboundProductReadList.get(i).productList.get(j).getName();
				object1[rows][4] = inboundProductReadList.get(i).productList.get(j).getBatchNo();
				object1[rows][5] = inboundProductReadList.get(i).productList.get(j).getMfgDate();
				object1[rows][6] = inboundProductReadList.get(i).productList.get(j).getExpDate();
				object1[rows][7] = inboundProductReadList.get(i).productList.get(j).getQuantityInStore();
				object1[rows][8] = inboundProductReadList.get(i).productList.get(j).getPurchasePrice();
				rows++;
			}
		}
		return object1;
	}
	
}
