package retailStore;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Employee {
	private int id;
	private String name;
	private String address;
	private String position;
	private String email;
	private int contactNumber;
	
	public Employee() {
	}
	
	public Employee(String name, String position, String email, String address, int contactNumber) {
		this.name = name;
		this.position = position;
		this.email = email;
		this.address = address;
		this.contactNumber = contactNumber;
	}
	
	

	public Employee(int id, String name, String address, String position, String email, int contactNumber) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.position = position;
		this.email = email;
		this.contactNumber = contactNumber;
	}

	public void addNewEmployee() {
		try {
			PreparedStatement pst = DataBaseConnection.con.prepareStatement("INSERT INTO employee(name,contactNo,address,email,position) VALUES (?,?,?,?,?)");
			pst.setString(1, name);
			pst.setInt(2, contactNumber);
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
			PreparedStatement pst = DataBaseConnection.con.prepareStatement("UPDATE employee SET name = ?, contactNo = ?, address = ?, email = ?, position = ? WHERE id = ?");
			pst.setString(1, name);
			pst.setInt(2, contactNumber);
			pst.setString(3, address);
			pst.setString(4, email);
			pst.setString(5, position);
			pst.setInt(6, id);
			pst.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Employee getEmployeeInfo() {
		try {
			PreparedStatement pst = DataBaseConnection.con.prepareStatement("SELECT id,name,contactNo,address,position,email FROM employee WHERE id = ?");
			pst.setInt(1, this.id);
			ResultSet rst = pst.executeQuery();
			if (rst.next()) {
				int id = rst.getInt(1);
				String name = rst.getString(2);
				int contactNumber = rst.getInt(3);
				String address = rst.getString(4);
				String position = rst.getString(5);
				String email = rst.getString(6);
				Employee employee = new Employee(id, name, address, position, email, contactNumber);
				return employee;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private Employee[] getAllEmployee() {
		//creating array of customer objects from database
		Employee employee[] = new Employee[DataBaseConnection.getCount("employee", "id")-1];
		PreparedStatement pst;
		ResultSet rst;
		
		try {
			pst = DataBaseConnection.con.prepareStatement("SELECT id,name,contactNo,address,email,position FROM employee WHERE id > 0");
			rst = pst.executeQuery();
			int i=0;
			while(rst.next()) {
				int id = rst.getInt(1);
				String name = rst.getString(2);
				int contactNumber = rst.getInt(3);
				String address = rst.getString(4);
				String email = rst.getString(5);
				String position = rst.getString(6);
				employee[i] = new Employee(name, position, email, address, contactNumber);
				employee[i].setId(id);
				i++;
			}
			return employee;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Object[][] populateTableArray() {
		Employee employee[] = getAllEmployee();
		int count = DataBaseConnection.getCount("employee" ,"id")-1;
		Object objects[][] = new Object[count][5];
		for(int i=0; i<count; i++) {
			objects[i][0] = employee[i].id;
			objects[i][1] = employee[i].name;
			objects[i][2] = employee[i].address;
			objects[i][3] = employee[i].contactNumber;
			objects[i][4] = employee[i].email;
		}
		return objects;
	}
	
	public boolean isSaved() {
		try {
			PreparedStatement pst = DataBaseConnection.con.prepareStatement("SELECT COUNT(id) FROM emplyee WHERE id = ?");
			pst.setInt(1, id);
			ResultSet rst = pst.executeQuery();
			if(rst.next()) { //employee saved in database
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(int conatactNumber) {
		this.contactNumber = conatactNumber;
	}
	
	
}

