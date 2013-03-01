package serviseatl.reimport;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import serviseatl.connection.ConnectionMarka;
import serviseatl.connection.DAOException;

public class ReImport implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7205029437337192393L;
	public Integer getReImport(long FechaI , long FechaF, int employeeId) throws DAOException {
		Integer result = null;
		Connection c = null;
		CallableStatement s = null;
		java.sql.Date FI = new java.sql.Date(FechaI);
		java.sql.Date FF = new java.sql.Date(FechaF);	
		try {
			c = ConnectionMarka.getConnection();
			s = c.prepareCall("{ call dbo.QReImportFromNovo(?, ?, ?) }");
			s.setInt("employeeid", employeeId);
			s.setDate("datestart", FI);
			s.setDate("dateend", FF);
			s.execute();// Query();
			result = 1;
		} catch (SQLException e) {
			result = 0;
			e.printStackTrace();
			throw new DAOException(e);
		} finally {
			ConnectionMarka.close(c);
		}
		return result;
		
	}

}
