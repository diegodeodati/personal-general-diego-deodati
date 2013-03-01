package flex.samples;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionHelperSqlServer {
	private String url;
	private static ConnectionHelperSqlServer instance;

	private ConnectionHelperSqlServer()
	{
		try {
			System.out.println("  c1");
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			System.out.println("  c2");
			url = "jdbc:sqlserver://20.80.1.127:1433;databaseName=pruebaflex;user=user;password=user;";
			System.out.println("  c3");
		} catch (Exception e) {
			System.out.println("  error c");
			e.printStackTrace();
		}
	}

	public static Connection getConnection() throws SQLException {
		if (instance == null) {
			instance = new ConnectionHelperSqlServer();
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
