package it.betplus.wri.db.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionItalyV2 {
	
	private String url;
	private static ConnectionItalyV2 instance;
	//ResourceBundle resource = ResourceBundle.getBundle("WebReportingIntranet");

	private ConnectionItalyV2()
	{
		Compiler CompilerVar = Compiler.getCompiler();
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

			String jdbc_url="jdbc:sqlserver://10.0.6.108:1433;databaseName=Italy;user=awg;password=mining.2011;";

			if (CompilerVar.getLocationID_Compiler() == 0 && CompilerVar.getCodCasino_Compiler()==3){
				//servidor marka atlantis
				url = "jdbc:sqlserver://20.80.1.8:1433;databaseName=Atlantissxm;user=admin;password=sa12121;";
			} else if (CompilerVar.getLocationID_Compiler() == 5 && CompilerVar.getCodCasino_Compiler()==1){
				// servidor jatun san geronimo
				url = "jdbc:sqlserver://20.80.2.6:1433;databaseName=jatun;user=admin;password=sa12121;";
			}  else if (CompilerVar.getLocationID_Compiler() == 6 && CompilerVar.getCodCasino_Compiler()==3){
				// servidor  atlantiscur curacao
				url = "jdbc:sqlserver://20.80.2.6:1433;databaseName=atlantiscur;user=admin;password=sa12121;";
			} else if (CompilerVar.getLocationID_Compiler() == 8 && CompilerVar.getCodCasino_Compiler()==1){
				// servidor italia
				//url = "jdbc:sqlserver://192.168.100.250:1433;databaseName=Italy;user=sadmin;password=sadmin;";
				url = jdbc_url;
			}
			System.out.println("url da usare:"+url);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() throws SQLException {
		if (instance == null) {
			instance = new ConnectionItalyV2();
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
