package retailStore;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerList {

	List<Customer> customerDetailList = new ArrayList<Customer>();
	int numberOfCustomerRead = 0;

	public void customerInfo(int searchKey) {
		numberOfCustomerRead = 0;
		customerDetailList.clear();
		try {
			PreparedStatement pst = DataBaseConnection.con.prepareStatement("SELECT id FROM customer WHERE contactNumber LIKE '%"+searchKey+"%'");
			ResultSet rst = pst.executeQuery();
			while (rst.next()) {
				Customer customer = new Customer().getCustomerDetails(rst.getInt(1));
				customerDetailList.add(customer);
				numberOfCustomerRead++;	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Object[][] tableArray() {
		Object[][] objects = new Object[numberOfCustomerRead][3];
		for (int i = 0; i < numberOfCustomerRead; i++) {
			objects[i][0] = customerDetailList.get(i).getId();
			objects[i][1] = customerDetailList.get(i).getName();
			objects[i][2] = customerDetailList.get(i).getContactNumber();
		}
		return objects;
	}
}