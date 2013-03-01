package serviseatl.connection;
/* conexion servidor trafico secundario*/
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionParTr {
	private String url;
	private static ConnectionParTr instance;

	private ConnectionParTr()
	{
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			url = "jdbc:sqlserver://20.80.1.206:1433;databaseName=Traffic;user=admin;password=sa12121;";
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() throws SQLException {
		if (instance == null) {
			instance = new ConnectionParTr();
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
