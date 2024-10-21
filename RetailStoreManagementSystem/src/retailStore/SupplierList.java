package retailStore;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierList {
	List<Supplier> supplierDetailList = new ArrayList<Supplier>();
	int numberOfSupplierRead = 0;

	public void supplierInfo(String searchKey) {
		numberOfSupplierRead = 0;
		supplierDetailList.clear();
		try {
			PreparedStatement pst = DataBaseConnection.con.prepareStatement("SELECT id FROM supplier WHERE name LIKE '%"+searchKey+"%'");
			ResultSet rst = pst.executeQuery();
			while (rst.next()) {
				Supplier supplier = new Supplier();
				supplier.setId(rst.getInt(1));
				supplier = supplier.getSuppilerDetail();
				supplierDetailList.add(supplier);
				numberOfSupplierRead++;	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void supplierInfo() {
		numberOfSupplierRead = 0;
		supplierDetailList.clear();
		try {
			PreparedStatement pst = DataBaseConnection.con.prepareStatement("SELECT id FROM supplier");
			ResultSet rst = pst.executeQuery();
			while (rst.next()) {
				Supplier supplier = new Supplier();
				supplier.setId(rst.getInt(1));
				supplier = supplier.getSuppilerDetail();
				supplierDetailList.add(supplier);
				numberOfSupplierRead++;	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Object[][] tableArray() {
		Object[][] objects = new Object[numberOfSupplierRead][2];
		for (int i = 0; i < numberOfSupplierRead; i++) {
			objects[i][0] = supplierDetailList.get(i).getId();
			objects[i][1] = supplierDetailList.get(i).getName();
		}
		return objects;
	}
}
