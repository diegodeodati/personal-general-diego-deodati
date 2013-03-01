package it.bplus.util;

import java.sql.DriverManager;
import java.sql.SQLException;

//import javax.naming.Context;
//import javax.naming.InitialContext;
//import javax.naming.NamingException;

import org.apache.tomcat.dbcp.dbcp.BasicDataSource;
//import org.hibernate.SessionFactory;

public class JndiHibernateUtil {

//	private static final SessionFactory sessionFactory = buildSessionFactory();
//
//	private static  SessionFactory buildSessionFactory() {
//		Context initialCntx;
//		SessionFactory mySessionFactory=null;
//		try {
//			initialCntx = new InitialContext();
//
//			mySessionFactory = (SessionFactory)initialCntx.lookup("java:comp/env/hibernate/OracleSessionFactory");
//		} catch (NamingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return mySessionFactory;
//	}
//
//	public static SessionFactory getSessionFactory() {
//		return sessionFactory;
//	}



	public class XBasicDataSource extends BasicDataSource {
	    @Override
	    public synchronized void close() throws SQLException {
	        DriverManager.deregisterDriver(DriverManager.getDriver(url));
	        super.close();
	    }
	}
}
