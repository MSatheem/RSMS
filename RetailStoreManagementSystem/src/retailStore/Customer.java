package retailStore;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

public class Customer {
	private int id;
	private String name;
	private String address;
	private String city;
	private int contactNumber;
	private String email;
	private Date joiningDate;
	
	//constructors
	public Customer() {
		
	}
	
	public Customer(int id) {
		this.id = id;
	}
	

	public Customer(int id, String name, String address) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
	}

	public Customer(int id, String name, String address, String city, int contactNumber, String email, Date joiningDate) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.city = city;
		this.contactNumber = contactNumber;
		this.email = email;
		this.joiningDate = joiningDate;
	}

	public Customer(String name, String address, String city, int contactNumber, String email, Date joiningDate) {
		this.name = name;
		this.address = address;
		this.city = city;
		this.contactNumber = contactNumber;
		this.email = email;
		this.joiningDate = joiningDate;
	}

	//getters
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getAddress() {
		return address;
	}
	public String getCity() {
		return city;
	}
	public int getContactNumber() {
		return contactNumber;
	}
	public String getEmail() {
		return email;
	}
	public Date getJoiningDate() {
		return joiningDate;
	}

	//setters
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public void setContactNumber(int contactNumber) {
		this.contactNumber = contactNumber;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setJoiningDate(Date joiningDate) {
		this.joiningDate = joiningDate;
	}

	//save new customer
	public boolean  saveNewCustomer() {
		try {
			PreparedStatement pst = DataBaseConnection.con.prepareStatement("INSERT INTO customer (name, address, city, contactNumber, email, dateJoined) VALUES (?, ?, ?, ?, ?, ?)");
			pst.setString(1, this.name);
			pst.setString(2, this.address);
			pst.setString(3, this.city);
			pst.setInt(4, this.contactNumber);
			pst.setString(5, this.email);
			pst.setDate(6, this.joiningDate);
			pst.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;	
	}
	
	//remove customer from database
	public void removeCustomer() { //use of this function should be avoided as foreign key constraint problems
		try {
			PreparedStatement pst = DataBaseConnection.con.prepareStatement("DELETE FROM customer WHERE id ?");
			pst.setInt(1, this.id);
			pst.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//updating customer details
	public void updateCustomer() {
		try {
			PreparedStatement pst = DataBaseConnection.con.prepareStatement("UPDATE customer SET name = ?, address = ?, city = ?, contactNumber = ?, email = ? WHERE id = ?");
			pst.setString(1, name);
			pst.setString(2, address);
			pst.setString(3, city);
			pst.setInt(4, contactNumber);
			pst.setString(5, email);
			pst.setInt(6, id);
			pst.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//getting number of customers saved
	public int getCustomerCount() {
		ResultSet rst;
		try {
			PreparedStatement pst = DataBaseConnection.con.prepareStatement("SELECT COUNT(id) FROM customer");
			rst = pst.executeQuery();
			
			if(rst.next()) { 
				return rst.getInt(1);
			} else { //table empty
				return 0;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;	
	}
	
	//getting all customer details
	public Customer[] getAllCustomers() {
		//creating array of customer objects from database
		Customer customer[] = new Customer[getCustomerCount()]; 
		try {
			PreparedStatement pst = DataBaseConnection.con.prepareStatement("SELECT id,name,address,city,contactNumber,email,dateJoined FROM customer");
			ResultSet rst = pst.executeQuery();
			int i=0;
			while(rst.next()) {
				int id = rst.getInt(1);
				String name = rst.getString(2);
				String address = rst.getString(3);
				String city = rst.getString(4);
				int contactNumber = rst.getInt(5);
				String email = rst.getString(6);
				Date date = rst.getDate(7);
				customer[i] = new Customer(id,name,address,city,contactNumber,email,date);
				i++;
			}
			return customer;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//2d object Array for table population
	public Object[][] populateCustomerTable() {
		Customer customer[] = getAllCustomers();
		Object objects[][] = new Object[getCustomerCount()][7];
		for(int i=0; i<getCustomerCount(); i++) {
			objects[i][0] = customer[i].id;
			objects[i][1] = customer[i].name;
			objects[i][2] = customer[i].address;
			objects[i][3] = customer[i].city;
			objects[i][4] = customer[i].contactNumber;
			objects[i][5] = customer[i].email;
			objects[i][6] = customer[i].joiningDate;
		}
		return objects;
	}

	//returning customer detail
	public Customer getCustomerDetails(int id) {
		Customer customer = new Customer(id);
		PreparedStatement pst;
		ResultSet rst;
		
		try {
			pst = DataBaseConnection.con.prepareStatement("SELECT name,address,city,contactNumber,email,dateJoined FROM customer WHERE id =?");
			pst.setInt(1, id);
			rst = pst.executeQuery();
			if(rst.next()) {
				customer.name = rst.getString(1);
				customer.address = rst.getString(2);
				customer.city = rst.getString(3);
				customer.contactNumber = rst.getInt(4);
				customer.email = rst.getString(5);
				//this.joiningDate = rst.getString(6);
				return customer;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
		
	}
}
