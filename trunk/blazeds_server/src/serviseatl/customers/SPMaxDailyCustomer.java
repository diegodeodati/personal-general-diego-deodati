package serviseatl.customers;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import serviseatl.connection.ConnectionAtlTr;
import serviseatl.connection.ConnectionBplTr;
import serviseatl.connection.ConnectionParTr;
import serviseatl.connection.ConnectionSDTr;
import serviseatl.connection.ConnectionSGTr;
import serviseatl.connection.ConnectionParSDTr;
import serviseatl.connection.DAOException;

public class SPMaxDailyCustomer implements Serializable {
    static final long serialVersionUID = 103844994006777232L;
	public List<MaxDailyCustomer> getSPMaxDailyCustomer (byte LocationID, int CodCasino, long FechaI, long FechaF) throws DAOException {

		List<MaxDailyCustomer> list = new ArrayList<MaxDailyCustomer>();
		Connection c = null;
		CallableStatement s = null;
		java.sql.Date FI = new java.sql.Date(FechaI);
		java.sql.Date FF = new java.sql.Date(FechaF);
		
		try {
			if (LocationID == 0 && CodCasino == 3) {
				c = ConnectionAtlTr.getConnection();
			}
			else if (LocationID ==0 && CodCasino == 6) {
				c = ConnectionParTr.getConnection();				
			}
			else if (LocationID ==0 && CodCasino == 1) {
				c = ConnectionSGTr.getConnection();				
			}
			else if (LocationID ==0 && CodCasino == 7) {
				c = ConnectionSDTr.getConnection();				
			}
			else if (LocationID ==0 && CodCasino == 2) {
				c = ConnectionParSDTr.getConnection();				
			}
			else if (LocationID ==0 && CodCasino == 5) {
				c = ConnectionBplTr.getConnection();				
			}
			
			s = c.prepareCall("{ call dbo.ListaMaxDailyCustomer(?,?) }");
			s.setDate("FechaI", FI);
			s.setDate("FechaF", FF);			
			
			ResultSet rs = s.executeQuery();

			while (rs.next()) {
				list.add(new MaxDailyCustomer (
						rs.getString("Fecha"),
					    rs.getString("FechaHora"),
						rs.getInt("ConTarjeta"),
					    rs.getInt("SinTarjeta"),
					    rs.getInt("Total")
						));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e);
		} finally {
			if (LocationID == 0 && CodCasino == 3) {
				ConnectionAtlTr.close(c);
			}
			else if (LocationID ==0 && CodCasino == 6) {
				ConnectionParTr.close(c);
			}
			else if (LocationID ==0 && CodCasino == 1) {
				ConnectionSGTr.close(c);
			}
			else if (LocationID ==0 && CodCasino == 7) {
				ConnectionSDTr.close(c);
			}
			else if (LocationID ==0 && CodCasino == 5) {
				ConnectionSDTr.close(c);
			}

		}
		
		return list;
	}
}
