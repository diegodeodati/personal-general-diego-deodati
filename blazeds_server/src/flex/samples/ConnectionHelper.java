package flex.samples;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionHelper
{
	private String url;
	private static ConnectionHelper instance;

	private ConnectionHelper()
	{
		try {
			Class.forName("org.hsqldb.jdbcDriver");
			url = "jdbc:hsqldb:hsql://localhost:9002/flexdemodb";
//			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//			url = "jdbc:sqlserver://20.80.1.127:1433;databaseName=pruebaflex;user=user;password=user;";
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() throws SQLException {
		if (instance == null) {
			instance = new ConnectionHelper();
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