package it.bplus.util;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class DataBaseConnUtil {

	String poolName=IConstants.JNDIRESOURCENAME;

	public Connection getConnection() throws SQLException{
		InitialContext ctxt;
		Connection conn=null;
		try {
			ctxt = new InitialContext();

			DataSource ds = (DataSource) ctxt.lookup(poolName);
			conn       = ds.getConnection();
			//Connection dconn = ((DelegatingConnection) conn).getInnermostDelegate();
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return conn;
	}
}
