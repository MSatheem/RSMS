package retailStore;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Users {
	private int userId;
	private String password;
	public int getUserId() {
		return userId;
	}




	public void setUserId(int userId) {
		this.userId = userId;
	}




	public String getPassword() {
		return password;
	}




	public void setPassword(String password) {
		this.password = password;
	}




	public int getStatus() {
		return status;
	}




	public void setStatus(int status) {
		this.status = status;
	}

	private int  status;
	
	Connection con;
	
	
	
	public Users(int userId, String password, int status) {
		this.userId = userId;
		this.password = password;
		this.status = status;
	}

	


	public Users(int userId, String password) {
		this.userId = userId;
		this.password = password;
	}

	public void addNewUser() {
		
	}
	
	public boolean userLogin() {
		connectToDatabase();
		passwordCheck();
		accessStatusCheck();
		return false;
	}
	
	private void passwordCheck() {
		
	}
	
	private void accessStatusCheck() {
		
	}

	//adding new user to the system
	public void saveUser() {
		connectToDatabase();
		
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
