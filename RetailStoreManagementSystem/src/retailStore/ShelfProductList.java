package retailStore;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ShelfProductList {
	private Date date;
	private int logNo;
	private int count=0;
	
	List<ShelfProduct> shelfList = new ArrayList<ShelfProduct>();
	
	
	public void add(ShelfProduct product) {
		shelfList.add(product);
		count++;
	}
	
	public void remove() {
		
	}
	
	
	public void setDate(Date date) {
		this.date = date;
	}

	//saving to database
	public void saveList() {
		//reduce amount from in bound table quantity
		PreparedStatement pst,pst2;
		ResultSet rst;
		java.sql.Date dateToSave = new java.sql.Date(date.getTime());
		
		try {
			pst = DataBaseConnection.con.prepareStatement("INSERT INTO shelf(date) VALUES(?)");
			pst.setDate(1, dateToSave);
			pst.execute();
			
			pst = DataBaseConnection.con.prepareStatement("SELECT LAST_INSERT_ID();");
			rst = pst.executeQuery();
			if(rst.next()) {
				logNo = rst.getInt(1);
				pst2 = DataBaseConnection.con.prepareStatement("INSERT INTO shelf_product (logNo, productId, batchNo,inboundLogNo,salePrice, quantity, quantityInShelf) VALUES (?, ?, ?, ?, ?, ?,?) ");
				for(int i=0; i<count; i++) {
					//saving the in bound list to database
					pst2.setInt(1, logNo);
					pst2.setInt(2, shelfList.get(i).getId());
					pst2.setInt(3, shelfList.get(i).getBatchNo());
					pst2.setInt(4, shelfList.get(i).getInboundLogNo());
					pst2.setDouble(5, shelfList.get(i).getSalePrice());
					pst2.setInt(6, shelfList.get(i).getQuantityMovedToShelf());
					pst2.setInt(7, shelfList.get(i).getQuantityMovedToShelf());
					//updating quantity in in bound product table
					shelfList.get(i).updateQuantity();
					pst2.execute();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Object[][] tableArray() {
		Object[][] obj = new Object[shelfList.size()][5];
		for(int i=0; i< shelfList.size(); i++) {
			obj[i][0] = shelfList.get(i).getId();
			obj[i][1] = shelfList.get(i).getBatchNo();
			obj[i][2] = shelfList.get(i).getPurchasePrice();
			obj[i][3] = shelfList.get(i).getQuantityMovedToShelf();
			obj[i][4] = shelfList.get(i).getSalePrice();			
		}
		return  obj;
	}
}
