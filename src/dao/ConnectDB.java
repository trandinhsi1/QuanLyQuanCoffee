package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
	public static Connection con = null;
	private static ConnectDB instance = new ConnectDB();
	
	public static ConnectDB getInstance() {
		return instance;
	}
	
	public static Connection getConnection() {
		return con;
	}
	
	public void connect() {
		String url = "jdbc:sqlserver://localhost:1433;databasename=QuanLyCoffee";
		String user = "sa";
		String password = "sapassword";
		try {
			con = DriverManager.getConnection(url, user, password);
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	public void disconnect() {
		if(con != null) {
			try {
				con.close();
			}catch(SQLException ex) {
				ex.printStackTrace();
			}
		}
	}
}	
