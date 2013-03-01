package it.bplus.db;


import it.bplus.exception.DataAccessException;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

public class DBConnectionManager {

	protected static Logger logger = Logger.getLogger(DBConnectionManager.class);



	public static Connection DataMartConnectionFactory() throws DataAccessException{
		Connection conn=null;
		try {
			Context initContext = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			DataSource ds;

			ds = (DataSource)envContext.lookup("jdbc/oracle_datamart");
			conn = ds.getConnection();
			
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

		} catch (NamingException e) {
			e.printStackTrace();
			throw new DataAccessException("Errore nella gestione delle risorse jndi", e.toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new DataAccessException("Errore nella apertura della connessione", e.toString());
		}
		return conn;	

	}


	public static Connection StagingConnectionFactory() throws DataAccessException{
		Connection conn=null;
		try {
			Context initContext = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			DataSource ds;

			ds = (DataSource)envContext.lookup("jdbc/oracle_staging");
			conn = ds.getConnection();
			
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

		} catch (NamingException e) {
			e.printStackTrace();
			throw new DataAccessException("Errore nella gestione delle risorse jndi", e.toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new DataAccessException("Errore nella apertura della connessione", e.toString());
		}
		return conn;	
	}


	public static void CloseConnection(ResultSet rs, Statement stmt, Connection conn) throws DataAccessException{
		try{
			conn.close(); // Return to connection pool
			conn = null;  // Make sure we don't close it twice
		} catch (SQLException e) {
			logger.error(e.toString());
			throw new DataAccessException("Errore nella chiusura della connessione", e.toString());
		} finally {
			if (rs != null) {
				try { rs.close(); } catch (SQLException e) { ; }
				rs = null;
			}
			if (stmt != null) {
				try { stmt.close(); } catch (SQLException e) { ; }
				stmt = null;
			}
			if (conn != null) {
				try { conn.close(); } catch (SQLException e) { ; }
				conn = null;
			}
		}
	}

	public static void CloseConnection(Connection conn) throws DataAccessException{
		try{
			if(conn!=null)
				conn.close(); // Return to connection pool
			conn = null;  // Make sure we don't close it twice
		} catch (SQLException e) {
			logger.error(e.toString());
			throw new DataAccessException("Errore nella chiusura della connessione", e.toString());
		} finally {
			// Always make sure result sets and statements are closed,
			// and the connection is returned to the pool
			if (conn != null) {
				try { conn.close(); } catch (SQLException e) { ; }
				conn = null;
			}

		}
	}


	public static void CloseConnection(Statement stmt, Connection conn) throws DataAccessException{
		try{
			conn.close(); // Return to connection pool
			conn = null;  // Make sure we don't close it twice
		} catch (SQLException e) {
			logger.error(e.toString());
			throw new DataAccessException("Errore nella chiusura della connessione", e.toString());
		} finally {
			if (stmt != null) {
				try { stmt.close(); } catch (SQLException e) { ; }
				stmt = null;
			}
			if (conn != null) {
				try { conn.close(); } catch (SQLException e) { ; }
				conn = null;
			}

		}
	}

	public static void CloseStatement(ResultSet rs, Statement stmt) throws DataAccessException{

		if (rs != null) {
			try { rs.close(); } catch (SQLException e) { ; }
			rs = null;
		}
		if (stmt != null) {
			try { stmt.close(); } catch (SQLException e) { ; }
			stmt = null;
		}

	}	


	public static void CloseStatement(Statement stmt) throws DataAccessException{

		if (stmt != null) {
			try { stmt.close(); } catch (SQLException e) { ; }
			stmt = null;
		}

	}		
}
