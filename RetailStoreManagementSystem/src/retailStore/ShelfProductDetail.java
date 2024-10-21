package retailStore;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShelfProductDetail{
	
	int numberOfElements=0;
	
	List<ShelfProductDetailRead> shelfProductDetailList = new ArrayList<ShelfProductDetailRead>();
	
	public void  updateQunatityInShelf() {
		//reduce the quantity after invoice generated
		try {
			PreparedStatement pst = DataBaseConnection.con.prepareStatement("");
			ResultSet rst = pst.executeQuery();
			while(rst.next()) {
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	private void readShelfProductTable(int id) {
		try {
			PreparedStatement pst = DataBaseConnection.con.prepareStatement("SELECT logNo,inboundLogNo, batchNo, salePrice, salePercentage, quantityInShelf FROM shelf_product WHERE productId = ? AND quantityInShelf >0");
			pst.setInt(1, id);
			ResultSet rst = pst.executeQuery();
			while(rst.next()) {
				ShelfProductDetailRead shelfProductDetailRead  = new ShelfProductDetailRead(id);
				shelfProductDetailRead.setLogNo(rst.getInt(1));
				shelfProductDetailRead.setInboundLogNo(rst.getInt(2));
				shelfProductDetailRead.setBatchNo(rst.getInt(3));
				shelfProductDetailRead.setSalePrice(rst.getDouble(4));
				shelfProductDetailRead.setSalePercentage(rst.getDouble(5));
				shelfProductDetailRead.setQuantityInShelf(rst.getInt(6));
				shelfProductDetailList.add(shelfProductDetailRead);
				numberOfElements++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Object[][] tableArray(int id) {
		readShelfProductTable(id);
		Object[][] objects = new Object[numberOfElements][5];
		for(int i=0; i<numberOfElements; i++) {
			objects[i][0] = shelfProductDetailList.get(i).getInboundLogNo();
			objects[i][1] = shelfProductDetailList.get(i).getBatchNo();
			objects[i][2] = shelfProductDetailList.get(i).getSalePrice();
			objects[i][3] = shelfProductDetailList.get(i).getSalePercentage();
			objects[i][4] = shelfProductDetailList.get(i).getQuantityInShelf();
		}
		return objects;
	}
	
	
}
