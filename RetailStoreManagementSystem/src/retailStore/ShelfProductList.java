package retailStore;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ShelfProductList {
	private Date date;
	private int count=0;
	
	List<ShelfProduct> shelfList = new ArrayList<ShelfProduct>();
	
	
	public void add(ShelfProduct product) {
		shelfList.add(product);
		count++;
	}
	
	public void remove() {
		
	}
	
	//saving to database
	public void saveList() {
		//reduce amount from inbound table quantity
			
		//
	}
	
}
