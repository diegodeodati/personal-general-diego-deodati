package serviseatl.connection;
/*conecion al servidor de base de datos principal*/

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;

import serviseatl.compiler.Compiler;

public class ConnectionMarka {
	private String url;
	private static ConnectionMarka instance;
	ResourceBundle resource=ResourceBundle.getBundle("atlweb");

	private ConnectionMarka()
	{
		Compiler CompilerVar = Compiler.getCompiler();
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

			String jdbc_url="";
			try{
				Properties prop=loadProp(System.getProperty("catalina.home")+"/webapps/atlweb/WEB-INF/classes/atlweb.properties");
				if(prop!=null){
					jdbc_url=prop.getProperty("jdbc_url");
				}
//				else{
//					jdbc_url=resource.getString("jdbc_url");
//				}

			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
			//			catch(Exception e){
			//				e.printStackTrace();
			//			}

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

	private Properties loadProp(String fileName) throws IOException{
		Properties prop = new Properties();

		InputStream is = new FileInputStream(fileName);

		prop.load(is);

		return prop;
	}

	public static Connection getConnection() throws SQLException {
		if (instance == null) {
			instance = new ConnectionMarka();
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
