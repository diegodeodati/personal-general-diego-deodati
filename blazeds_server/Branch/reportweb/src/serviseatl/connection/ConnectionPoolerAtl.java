package serviseatl.connection;
/* conexion a servidore pooler primario*/
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import serviseatl.compiler.Compiler;

public class ConnectionPoolerAtl {
	private String url;
	private static ConnectionPoolerAtl instance;

	private ConnectionPoolerAtl()
	{
		Compiler CompilerVar = Compiler.getCompiler();	
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			if (CompilerVar.getLocationID_Compiler() == 6 && CompilerVar.getCodCasino_Compiler()==3){
				// solo en caso de curacao
				url = "jdbc:sqlserver://20.80.2.8:1433;databaseName=PoolerCur;user=admin;password=sa12121;";
			} else {
				url = "jdbc:sqlserver://20.80.1.9:1433;databaseName=PoolerAtl;user=admin;password=sa12121;";				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() throws SQLException {
		if (instance == null) {
			instance = new ConnectionPoolerAtl();
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
