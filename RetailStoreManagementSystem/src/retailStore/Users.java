package retailStore;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Users {
	private String userId;
	private String userName;
	private String password = "abcd@123" ; //defaultPassword
	private int accessLevel = 0;
	private boolean accessStatus = false;
	private int wrongPasswordCount = 0;

	public Users(String userId, String password) {
		this.userId = userId;
		setPassword(password);
	}
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
		this.password = encryptedPassword();
	}

	public void addNewUser() {
		try {
			PreparedStatement pst = DataBaseConnection.con.prepareStatement("INSERT INTO users (userName, password, accessStatus, accessLevel) VALUES (?, ?, ?, ?) ");
			pst.setString(1, userName);
			pst.setString(2, password);
			pst.setBoolean(3, accessStatus);
			pst.setInt(4, accessLevel);
			pst.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void login() {
		if(userNameCheck()) { //checking whether userName exists
			
		}
	}

 	private boolean userNameCheck() {
		try {
			PreparedStatement pst = DataBaseConnection.con.prepareStatement("SELECT COUNT(employeeId) FROM employee WHERE userName = ? ");
			pst.setString(1, userName);
			ResultSet rst = pst.executeQuery();
			if(rst.next()) { //user name exists
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	private void passwordCheck() {
		
	}

	private void accessStatusCheck() {

	}

	// adding new user to the system
	public void saveUser() {
		
	}

	private boolean passwordCheck(String password) {
		
		return false;
	}
	
	private String encryptedPassword() {
		String encrypted = null;
		try {
			MessageDigest m = MessageDigest.getInstance("MD5");
			m.update(password.getBytes());
			
			byte[] bytes = m.digest();
			
			StringBuilder s = new StringBuilder();  
            for(int i=0; i< bytes.length ;i++)  
            {  
                s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));  
            }  
              
            /* Complete hashed password in hexadecimal format */  
            encrypted = s.toString();  
            return encrypted;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
