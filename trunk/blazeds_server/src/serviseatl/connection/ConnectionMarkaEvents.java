package serviseatl.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import serviseatl.compiler.Compiler;

public class ConnectionMarkaEvents 	{
	private String url;
	private static ConnectionMarkaEvents instance;

	private ConnectionMarkaEvents()
	{
		Compiler CompilerVar = Compiler.getCompiler();
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			
			if (CompilerVar.getLocationID_Compiler() == 0 && CompilerVar.getCodCasino_Compiler()==3){
				//servidor marka atlantis
				url = "jdbc:sqlserver://20.80.1.8:1433;databaseName=Events;user=admin;password=sa12121;";
			} else if (CompilerVar.getLocationID_Compiler() == 5 && CompilerVar.getCodCasino_Compiler()==1){
				// servidor jatun san geronimo
				url = "jdbc:sqlserver://20.80.2.6:1433;databaseName=Events;user=admin;password=sa12121;";
			}  else if (CompilerVar.getLocationID_Compiler() == 6 && CompilerVar.getCodCasino_Compiler()==3){
				// servidor  atlantiscur curacao
				url = "jdbc:sqlserver://20.80.2.6:1433;databaseName=Events;user=admin;password=sa12121;";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() throws SQLException {
		if (instance == null) {
			instance = new ConnectionMarkaEvents();
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
