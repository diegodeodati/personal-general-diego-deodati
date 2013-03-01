package serviseatl.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionLocal {
	private String url;
	private static ConnectionLocal instance;

	private ConnectionLocal()
	{
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			url = "jdbc:sqlserver://20.80.1.127:1433;databaseName=pruebaflex;user=sa;password=sa12121;";
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() throws SQLException {
		if (instance == null) {
			instance = new ConnectionLocal();
		}
		try {
			return DriverManager.getConnection(instance.url);
		} catch (SQLException e) {
			throw e;
		}
	}
	
	public static void close(Connection connection)
	{
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
