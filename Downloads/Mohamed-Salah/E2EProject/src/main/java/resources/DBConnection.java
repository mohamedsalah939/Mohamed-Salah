package resources;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;


public class DBConnection extends base{
	
	public Statement dbConnect() throws SQLException, IOException {
		
		Properties prop = uploadProp();

		// To read the db password from prop. files
		String pw = (String) prop.get("testdbpassword");
		String host = (String) prop.get("testdbhost");
		String port = (String) prop.get("testdbport");
		String dbName = (String) prop.get("testdb");
		String dbuser = (String) prop.get("testdbuser");
		// Connect to database
		Connection con = DriverManager.getConnection(
				"jdbc:mysql://" + host + ":" + port + "/" + dbName + "?autoReconnect=true&useSSL=false", dbuser, pw);
		Statement stmt = con.createStatement();

		return stmt; 
	}
}
