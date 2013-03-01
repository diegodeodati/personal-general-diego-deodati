package serviseatl.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionPoolerCur {
	private String url;
	private static ConnectionPoolerCur instance;

	private ConnectionPoolerCur()
	{
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			url = "jdbc:sqlserver://20.80.2.8:1433;databaseName=PoolerCur;user=admin;password=sa12121;";
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() throws SQLException {
		if (instance == null) {
			instance = new ConnectionPoolerCur();
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
