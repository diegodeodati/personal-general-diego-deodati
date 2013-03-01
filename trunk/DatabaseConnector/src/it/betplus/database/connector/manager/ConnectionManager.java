package it.betplus.database.connector.manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
	
	private Connection connection;
	private String driverClass;
	private String connectionURL;
	private String userID;
	private String userPassword;
	
	public ConnectionManager(String driverClass, String serverIp, String dbName, String dbUser, String dbPwd) {
		
		this.driverClass = driverClass;
		this.connectionURL = "jdbc:sqlserver://" + serverIp + ";database=" + dbName + ";";
		this.userID = dbUser;
		this.userPassword = dbPwd;
		
	}
	
	public Connection getConnection() throws SQLException{
		
		if (!(connection != null) || connection.isClosed())			
			openConnection();
		
		return connection;
		
	}

	public void setConnection(Connection con) {
		this.connection = con;
	}

	public String getDriverClass() {
		return driverClass;
	}

	public void setDriverClass(String driverClass) {
		this.driverClass = driverClass;
	}

	public String getConnectionURL() {
		return connectionURL;
	}

	public void setConnectionURL(String connectionURL) {
		this.connectionURL = connectionURL;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public void openConnection() {
		
		try {
			
			Class.forName(driverClass).newInstance();
			System.out.println("Open connection");
			setConnection(DriverManager.getConnection(getConnectionURL(), getUserID(), getUserPassword()));
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}

	public void closeConnection() {

		try {
			System.out.print("  Closing Connection...\n");
			getConnection().close();

		} catch (SQLException e) {

			e.printStackTrace();

		}

	}

}
