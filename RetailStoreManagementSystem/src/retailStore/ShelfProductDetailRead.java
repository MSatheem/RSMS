package retailStore;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ShelfProductDetailRead extends ShelfProduct{
	private int logNo;

	
	public ShelfProductDetailRead(int id) {
		Product product = new Product().getProductInfo(id);
		this.setId(product.getId());
		this.setName(product.getName());
		product = null;
	}

	public int getLogNo() {
		return logNo;
	}

	public void setLogNo(int logNo) {
		this.logNo = logNo;
	}
}
