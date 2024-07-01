package retailStore;

import java.sql.Connection;

public class Employee {
	private int id;
	private int name;
	private String position;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getName() {
		return name;
	}

	public void setName(int name) {
		this.name = name;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	Connection con;
	public Employee(int id, int name, String position) {
		this.id = id;
		this.name = name;
		this.position = position;
	}
	
	//saving employee
	public void saveEmployee() {
		
	}
	
	public void employeeInfo() {
		
	}
	/*
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
		}*/
}
