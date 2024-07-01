package retailStore;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataBaseConnection {
	
	static Connection con;
	
	static {
	
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/rsms","root","");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//getting number of entries saved in database
	static int getCount(String tableName) {
		PreparedStatement pst;
		ResultSet rst;

		try {
			pst = DataBaseConnection.con.prepareStatement("SELECT COUNT(id) FROM " + tableName);
			rst = pst.executeQuery();

			if (rst.next()) {
				return rst.getInt(1);
			} else { // table empty
				return 0;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	//closing the connection to database
	static void connectionClose() {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
