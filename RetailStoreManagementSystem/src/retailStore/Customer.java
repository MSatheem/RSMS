package retailStore;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

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


	//constructor
	public Customer(int id, String name, String address, String city, int contactNumber, String email, Date joiningDate) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.city = city;
		this.contactNumber = contactNumber;
		this.email = email;
		this.joiningDate = joiningDate;
	}

	public Customer(String name, String address, String city, int contactNumber, String email, Date joiningDate) {
		super();
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
	public void  saveNewCustomer() {
		connectToDatabase();
		PreparedStatement pst;

		try {
			pst = con.prepareStatement("INSERT INTO customer (id, name, address, city, contactNumber, email, dateJoined) VALUES ('1', '?', '?', '?', '1', '?', '2023-11-01')");
			pst.setInt(1, this.id);
			pst.setString(2, this.name);
			pst.setString(3, this.address);
			pst.setString(4, this.city);
			pst.setInt(5, this.contactNumber);
			pst.setString(6, this.email);
			//pst.setDate(7, this.joiningDate);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		closeConnection();	
	}


	//establishing connection to database
	private void connectToDatabase() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/rsms","root","");
			System.out.println("Connected");
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
