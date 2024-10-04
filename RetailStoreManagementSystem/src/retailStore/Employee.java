package retailStore;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Employee {
	private int id;
	private String name;
	private String position;
	private String email;
	private String address;
	private int conatactNumber;
	
	public Employee(String name, String position, String email, String address, int conatactNumber) {
		this.name = name;
		this.position = position;
		this.email = email;
		this.address = address;
		this.conatactNumber = conatactNumber;
	}

	public void addNewEmployee() {
		try {
			PreparedStatement pst = DataBaseConnection.con.prepareStatement("INSERT INTO employee(name,contactNo,address,email,position) VALUES (?,?,?,?,?)");
			pst.setString(1, name);
			pst.setInt(2, conatactNumber);
			pst.setString(3, address);
			pst.setString(4, email);
			pst.setString(5, position);
			pst.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateEmployee() {
		try {
			PreparedStatement pst = DataBaseConnection.con.prepareStatement("UPDATE users SET name = ?, contactNo = ?, address = ?, email = ?, position = ? WHERE id = ?");
			pst.setString(1, name);
			pst.setInt(2, conatactNumber);
			pst.setString(3, address);
			pst.setString(4, email);
			pst.setString(5, position);
			pst.setInt(6, id);
			pst.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public String[][] populateTableArray() {
		return null;
	}
}

