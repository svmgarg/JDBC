package learn.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class DbUtils {

	
	@SuppressWarnings("finally")
	public static Connection getConnection(){
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con  = DriverManager.getConnection("jdbc:mysql://localhost:3306/Test" , "root" , "root");
			/*Statement stmt = con.createStatement();
			stmt.executeQuery("Use Test");*/
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Exception Occurs Reutrning Null COnnection");
		}
		finally{
			return con;
		}
		
	}
}
