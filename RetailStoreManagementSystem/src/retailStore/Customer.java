package retailStore;

import java.sql.Connection;
import java.sql.DriverManager;
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

	//connection 
	Connection con;
	
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
		connectToDatabase();
		PreparedStatement pst;

		try {
			pst = con.prepareStatement("INSERT INTO customer (name, address, city, contactNumber, email, dateJoined) VALUES (?, ?, ?, ?, ?, ?)");
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
		
		closeConnection();
		return false;	
	}
	
	//remove customer from database
	public void removeCustomer() {
		connectToDatabase();
		PreparedStatement pst;
		
		try {
			pst = con.prepareStatement("DELETE FROM customer WHERE id ?");
			pst.setInt(1, this.id);
			pst.execute();
			System.out.println("Customer removed");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//getting number of customers saved
	public int getCustomerCount() {
		connectToDatabase();
		PreparedStatement pst;
		ResultSet rst;
		
		try {
			pst = con.prepareStatement("SELECT COUNT(id) FROM customer");
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
		connectToDatabase();
		PreparedStatement pst;
		ResultSet rst;
		
		try {
			pst = con.prepareStatement("SELECT id,name,address,city,contactNumber,email,dateJoined FROM customer");
			rst = pst.executeQuery();
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

	//closing connection to database
	private void closeConnection() {
		try {
			con.close();
			System.out.println("Connection closed");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
