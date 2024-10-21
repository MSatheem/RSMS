package retailStore;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Supplier {

	// class attributes
	private int id;
	private String name;
	private String address;
	private String email;
	private int contactNumber;
	private String contactPerson;

	// constructors
	public Supplier() {
	}

	public Supplier(int id, String name, String address, String email, int contactNumber, String contactPerson) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.email = email;
		this.contactNumber = contactNumber;
		this.contactPerson = contactPerson;
	}

	public Supplier(String name, String address, String email, int contactNumber, String contactPerson) {
		this.name = name;
		this.address = address;
		this.email = email;
		this.contactNumber = contactNumber;
		this.contactPerson = contactPerson;
	}

	// getters
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public String getEmail() {
		return email;
	}

	public int getContactNumber() {
		return contactNumber;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	// setters
	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setContactNumber(int contactNumber) {
		this.contactNumber = contactNumber;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	// saving new supplier
	public void saveNewSupplier() {
		PreparedStatement pst;

		try {
			pst = DataBaseConnection.con.prepareStatement("INSERT INTO supplier (id, name, address, email, contactNumber, contactPerson) VALUES (?, ?, ?, ?, ?, ?) ");
			pst.setInt(1, this.id);
			pst.setString(2, this.name);
			pst.setString(3, this.address);
			pst.setString(4, this.email);
			pst.setInt(5, this.contactNumber);
			pst.setString(6, this.contactPerson);
			pst.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	//edit supplier details
	public void editSupplier() {
		PreparedStatement pst;

		try {
			pst = DataBaseConnection.con.prepareStatement("UPDATE supplier SET name = ?, address = ?, email = ?, contactNumber = ?, contactPerson = ? WHERE id = ?");
			
			pst.setString(1, this.name);
			pst.setString(2, this.address);
			pst.setString(3, this.email);
			pst.setInt(4, this.contactNumber);
			pst.setString(5, this.contactPerson);
			pst.setInt(6, this.id);
			pst.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	public Supplier getSuppilerDetail() {
		PreparedStatement pst;
		ResultSet rst;

		try {
			pst = DataBaseConnection.con.prepareStatement("SELECT name,address,email,contactNumber,contactPerson FROM supplier WHERE id = ?");
			pst.setInt(1, this.id);
			rst = pst.executeQuery();
			if (rst.next()) {
				String name = rst.getString(1);
				String address = rst.getString(2);
				String email = rst.getString(3);
				int contactNumber = rst.getInt(4);
				String contactPerson = rst.getString(5);
				Supplier supplier = new Supplier(this.id, name, address, email, contactNumber, contactPerson);
				return supplier;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	// getting all suppliers details
	public Supplier[] getAllSuppliers() {
		// creating array of suppliers objects from database
		Supplier supplier[] = new Supplier[getSupplierCount()];
		PreparedStatement pst;
		ResultSet rst;

		try {
			pst = DataBaseConnection.con.prepareStatement("SELECT id,name,address,email,contactNumber,contactPerson FROM supplier");
			rst = pst.executeQuery();
			int i = 0;
			while (rst.next()) {
				int id = rst.getInt(1);
				String name = rst.getString(2);
				String address = rst.getString(3);
				String email = rst.getString(4);
				int contactNumber = rst.getInt(5);
				String contactPerson = rst.getString(6);
				supplier[i] = new Supplier(id, name, address, email, contactNumber, contactPerson);
				i++;
			}
			return supplier;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	
	public Object[][] populateSupplierTable() {
		Supplier supplier[] = getAllSuppliers();
		Object objects[][] = new Object[getSupplierCount()][6];
		for(int i=0; i<getSupplierCount(); i++) {
			objects[i][0] = supplier[i].id;
			objects[i][1] = supplier[i].name;
			objects[i][2] = supplier[i].address;
			objects[i][3] = supplier[i].email;
			objects[i][4] = supplier[i].contactNumber;
			objects[i][5] = supplier[i].contactPerson;
		}
		return objects;
	}
	// getting number of suppliers saved
	public int getSupplierCount() {
		return DataBaseConnection.getCount("supplier","id");
	}
	
	public boolean updateSupplier() {
		PreparedStatement pst;
		
		try {
			pst = DataBaseConnection.con.prepareStatement("UPDATE supplier SET name = ?,  address = ?, email = ?, contactNumber = ?, contactPerson = ? WHERE id = ? ");
			pst.setString(1, name);
			pst.setString(2, address);
			pst.setString(3, email);
			pst.setInt(4, contactNumber);
			pst.setString(5, contactPerson);
			pst.setInt(6, id);
			pst.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
