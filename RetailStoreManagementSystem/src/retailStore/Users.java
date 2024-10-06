package retailStore;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.crypto.Data;

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
	
	public Users() {
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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
			if(accessStatusCheck()) {
				if(passwordCheck()) { //password matches
					
				} else { //password no match
					wrongPasswordCount = getWrongPasswordCount();
				}
			}
			
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
	
	private int getWrongPasswordCount() {
		try {
			PreparedStatement pst = DataBaseConnection.con.prepareStatement("SELECT wrongPassword FROM employee Where username = ?");
			pst.setString(1, userName);
			
			ResultSet rst = pst.executeQuery();
			if(rst.next()) {
				int wrongPasswordCount = rst.getInt(1);
				return wrongPasswordCount;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return -1;
	}
	
	
	
	// adding new user to the system
	public void saveUser() {
		
	}

	private boolean passwordCheck() {
		try {
			PreparedStatement pst = DataBaseConnection.con.prepareStatement("SELECT password FROM users WHERE userName = ?");
			pst.setString(1, userName);
			ResultSet rst = pst.executeQuery();
			if(rst.next()) { //password matches
				return true;
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
			PreparedStatement pst = DataBaseConnection.con.prepareStatement("SELECT employeeid, userName, accessStatus, accesslevel, wrongPassword FROM users WHERE employeeId > 0");
			ResultSet rst = pst.executeQuery();
			int i =0 ;
			if(rst.next()) {
				obj[i][0] = rst.getInt(1);
				obj[i][1] = rst.getString(2);
				obj[i][2] = rst.getBoolean(3);
				obj[i][3] = rst.getInt(4);
				obj[i][4] = rst.getInt(5);
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
			PreparedStatement pst = DataBaseConnection.con.prepareStatement("SELECT id, name FROM employee WHERE id != (SELECT employeeId FROM users)");
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
}
