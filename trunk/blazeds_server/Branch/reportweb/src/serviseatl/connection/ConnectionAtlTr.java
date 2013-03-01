package serviseatl.connection;
/* conexcion a servidor trafico primario*/
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import serviseatl.compiler.Compiler;

public class ConnectionAtlTr  {
	private String url;
	private static ConnectionAtlTr instance;

	private ConnectionAtlTr()
	{
		Compiler CompilerVar = Compiler.getCompiler();		
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			if (CompilerVar.getLocationID_Compiler() == 6 && CompilerVar.getCodCasino_Compiler()==3){			
				//solo en caso de curacao
				url = "jdbc:sqlserver://20.80.2.8:1433;databaseName=Traffic;user=admin;password=sa12121;";
			} else {
				url = "jdbc:sqlserver://20.80.1.9:1433;databaseName=Traffic;user=admin;password=sa12121;";				
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() throws SQLException {
		if (instance == null) {
			instance = new ConnectionAtlTr();
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
