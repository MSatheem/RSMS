package retailStore;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Users {
	private int employeeId;
	private String userName;
	private String password = "abcd@123" ; //defaultPassword
	private int accessLevel = 0;
	private boolean accessStatus = false;

	public Users(String userId, String password) {
		this.userName = userId;
		setPassword(password); //encrypt password
	}
	
	public Users() {
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userId) {
		this.userName = userId;
	}

	public void setPassword(String password) {
		this.password = password;
		this.password = encryptedPassword();
	}

	public void addNewUser() {
		String encryptedPassword = encryptedPassword();
		try {
			PreparedStatement pst = DataBaseConnection.con.prepareStatement("INSERT INTO users (employeeId, userName, password, accessStatus, accessLevel) VALUES (?, ?, ?, ?, ?) ");
			pst.setInt(1, employeeId);
			pst.setString(2, userName);
			pst.setString(3, encryptedPassword);
			pst.setBoolean(4, accessStatus);
			pst.setInt(5, accessLevel);
			pst.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public boolean login() {
		if(userNameCheck()) { //checking whether userName exists
			if(accessStatusCheck()) {
				if(passwordCheck()) { //password matches
					return true;
				}
			}
			
		}
		return false;
	}

 	private boolean userNameCheck() {
		try {
			PreparedStatement pst = DataBaseConnection.con.prepareStatement("SELECT COUNT(employeeId) FROM users WHERE userName = ? ");
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

	
	private boolean accessStatusCheck() {
		try {
			PreparedStatement pst = DataBaseConnection.con.prepareStatement("SELECT accessStatus FROM users WHERE userName = ?");
			pst.setString(1, userName);
			ResultSet rst = pst.executeQuery();
			if(rst.next()) {
				boolean status = rst.getBoolean(1);
				return status;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	private boolean passwordCheck() {
		try {
			PreparedStatement pst = DataBaseConnection.con.prepareStatement("SELECT password FROM users WHERE userName = ?");
			pst.setString(1, userName);
			ResultSet rst = pst.executeQuery();
			if(rst.next()) { //password matches
				if(rst.getString(1).equals(password)) {
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
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
	
	public Object[][] tableArray() {
		Object obj[][] = new Object[DataBaseConnection.getCount("users", "employeeid")-1][6];
		try {
			PreparedStatement pst = DataBaseConnection.con.prepareStatement("SELECT employeeid, userName, accessStatus, accesslevel FROM users WHERE employeeId > 0");
			ResultSet rst = pst.executeQuery();
			int i =0 ;
			while(rst.next()) {
				obj[i][0] = rst.getInt(1);
				obj[i][1] = rst.getString(2);
				obj[i][2] = rst.getBoolean(3);
				obj[i][3] = rst.getInt(4);
				i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return obj;
	}
	
	public List<Employee> employeeListWithoutUserAccounts() {
		List<Employee> employees = new ArrayList<Employee>();
		try {
			PreparedStatement pst = DataBaseConnection.con.prepareStatement("SELECT id, name FROM employee WHERE id NOT IN (SELECT employeeId FROM users)");
			ResultSet rst = pst.executeQuery();
			while(rst.next()) {
				Employee employee = new Employee();
				employee.setId(rst.getInt(1));
				employee.setName(rst.getString(2));
				employees.add(employee);
			}
			return employees;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public int getAccessLevel() {
		return accessLevel;
	}

	public void setAccessLevel(int accessLevel) {
		this.accessLevel = accessLevel;
	}

	public boolean isAccessStatus() {
		return accessStatus;
	}

	public void setAccessStatus(boolean accessStatus) {
		this.accessStatus = accessStatus;
	}


	
	
}
