package retailStore;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 
 */
public class Supplier {
	

	//class attributes
	private int id;
	private String name;
	private String address;
	private String email;
	private int contactNumber;
	private String contactPerson;

	Connection con;
	
	//constructors
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
	public String getEmail() {
		return email;
	}
	public int getContactNumber() {
		return contactNumber;
	}
	public String getContactPerson() {
		return contactPerson;
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
	public void setEmail(String email) {
		this.email = email;
	}
	public void setContactNumber(int contactNumber) {
		this.contactNumber = contactNumber;
	}
	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	
	//printing object details
	@Override
	public String toString() {
		return "Supplier [id=" + id + ", name=" + name + ", address=" + address + ", email=" + email
				+ ", contactNumber=" + contactNumber + ", contactPerson=" + contactPerson + "]";
	}

	
	//saving new supplier
	public void saveNewSupplier() {
		connectToDatabase();
		PreparedStatement pst;
			
		try {
			pst = con.prepareStatement("INSERT INTO supplier (id, name, address, contactNumber, contactPerson) VALUES (?, ?, ?, ?, ?) ");
			pst.setInt(1, this.id);
			pst.setString(2, this.name);
			pst.setString(3, this.address);
			pst.setInt(4,this.contactNumber);
			pst.setString(5, this.contactPerson);
			pst.executeUpdate();
			System.out.println("Supplier Added to database" + toString());
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
