package serviseatl_gis.iniciarsesion;


import java.io.Serializable;
import java.sql.*;

import serviseatl.connection.ConnectionPoolerAtl;
import serviseatl.connection.ConnectionPoolerPar;
import serviseatl.connection.ConnectionPoolerSG;
import serviseatl.connection.ConnectionPoolerSD;
import serviseatl.connection.ConnectionPoolerBpl;
import serviseatl.connection.ConnectionPoolerParSD;
import serviseatl.connection.DAOException;

public class SPIniciarSesion implements Serializable {
    static final long serialVersionUID = 101844599001000200L;
	public String getSPIniciarSesion(byte LocationID, int CodCasino, int EmployeeID) throws DAOException {
		
		String LoginID="";
		Connection c = null;
		CallableStatement s = null;

		try {
			if (LocationID == 0 && CodCasino == 3) {
				c = ConnectionPoolerAtl.getConnection();
			}
			else if (LocationID ==0 && CodCasino == 6) {
				c = ConnectionPoolerPar.getConnection();				
			}
			else if (LocationID ==0 && CodCasino == 1) {
				c = ConnectionPoolerSG.getConnection();				
			}
			else if (LocationID ==0 && CodCasino == 7) {
				c = ConnectionPoolerSD.getConnection();				
			}
			else if (LocationID ==0 && CodCasino == 2) {
				c = ConnectionPoolerParSD.getConnection();				
			}
			else if (LocationID ==0 && CodCasino == 5) {
				c = ConnectionPoolerBpl.getConnection();				
			}
			
			s = c.prepareCall("{ call dbo.QEmployeeOnLine(?,?,?) }");
			s.setInt("Op", 1);
			s.setInt("EmployeeID", EmployeeID);
			s.registerOutParameter("LoginID", Types.VARCHAR);
			s.execute();
			LoginID = s.getString("LoginID");

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e);
		} finally {
			if (LocationID == 0 && CodCasino == 3) {
				ConnectionPoolerAtl.close(c);
			}
			else if (LocationID ==0 && CodCasino == 6) {
				ConnectionPoolerPar.close(c);
			}
			else if (LocationID ==0 && CodCasino == 1) {
				ConnectionPoolerSG.close(c);
			}
			else if (LocationID ==0 && CodCasino == 7) {
				ConnectionPoolerSD.close(c);
			}
			else if (LocationID ==0 && CodCasino == 5) {
				ConnectionPoolerBpl.close(c);
			}
			
		}
		
		return LoginID;
	}

}
