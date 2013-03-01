package serviseatl.connection;
/* concexion servidor Runa*/
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import serviseatl.compiler.Compiler;

public class ConnectionRuna {
	private String url;
	private static ConnectionRuna instance;

	private ConnectionRuna()
	{
		Compiler CompilerVar = Compiler.getCompiler();		
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			if (CompilerVar.getLocationID_Compiler() == 0 && CompilerVar.getCodCasino_Compiler()==3){
				// servidor  runa atlantis
				url = "jdbc:sqlserver://20.80.1.19:1433;databaseName=Customers;user=admin;password=sa12121;";
			} else if (CompilerVar.getLocationID_Compiler() == 5 && CompilerVar.getCodCasino_Compiler()==1){
				// servidor runa San geronimo
				url = "jdbc:sqlserver://20.80.2.7:1433;databaseName=Customers;user=sdmin;password=sa12121;";
			}  else if (CompilerVar.getLocationID_Compiler() == 6 && CompilerVar.getCodCasino_Compiler()==3){	
				// servidor runa Curacao
				url = "jdbc:sqlserver://20.80.2.7:1433;databaseName=Customers;user=admin;password=sa12121;";
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() throws SQLException {
		if (instance == null) {
			instance = new ConnectionRuna();
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
