package it.betplus.database.connector.facade;

import it.betplus.database.connector.manager.ConnectionManager;
import it.betplus.database.connector.utils.LoadSettings;
import it.betplus.database.connector.utils.Settings;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBFacade {

	protected ConnectionManager cm;

	public DBFacade() {
		
		String driverClass = LoadSettings.getSetting(Settings.CONF_RESOURCE_FILE, Settings.MSSQL_DRIVER_CLASS);
		String serverIp = LoadSettings.getSetting(Settings.CONF_RESOURCE_FILE, Settings.MSSQL_SERVER_IP);
		String dbName = LoadSettings.getSetting(Settings.CONF_RESOURCE_FILE, Settings.MSSQL_DB_NAME);
		String dbUser = LoadSettings.getSetting(Settings.CONF_RESOURCE_FILE, Settings.MSSQL_USER);
		String dbPwd = LoadSettings.getSetting(Settings.CONF_RESOURCE_FILE, Settings.MSSQL_PASSWORD);
		
		cm = new ConnectionManager(driverClass, serverIp, dbName, dbUser, dbPwd);
		
	}
	
	public ResultSet executeQuery(String sql) {
		
		ResultSet rs = null;
		
		cm.openConnection();
		
		try {
			
			Statement st = cm.getConnection().createStatement();		
			rs = st.executeQuery(sql);
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		} 
		
		return rs;

	}
	
	public ResultSet executeQueryStatement(PreparedStatement statement) {
		
		ResultSet rs = null;
		
		//cm.openConnection();
		
		try {
				
			rs = statement.executeQuery(); 
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		} 
		
		return rs;

	}

	public ConnectionManager getCm() {
		return cm;
	}

	public void setCm(ConnectionManager cm) {
		this.cm = cm;
	}
	
}
