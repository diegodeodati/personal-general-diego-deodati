package serviseatl.connection;
/* conexion a servidore pooler sedundarios*/
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionPoolerSD {
	private String url;
	private static ConnectionPoolerSD instance;

	private ConnectionPoolerSD()
	{
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			url = "jdbc:sqlserver://20.80.2.8:1433;databaseName=PoolerSD;user=admin;password=sa12121;";			
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() throws SQLException {
		if (instance == null) {
			instance = new ConnectionPoolerSD();
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
