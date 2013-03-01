package serviseatl.mystery;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import java.sql.*;

import serviseatl.connection.ConnectionPoolerAtl;
import serviseatl.connection.ConnectionPoolerPar;
import serviseatl.connection.ConnectionPoolerSD;
import serviseatl.connection.ConnectionPoolerSG;
import serviseatl.connection.ConnectionPoolerBpl;
import serviseatl.connection.ConnectionPoolerParSD;
import serviseatl.connection.DAOException;

public class SPLastPaids implements Serializable {
    static final long serialVersionUID = 103844994306007232L;
	public List<LastPaids> getSPLastPaids (byte LocationID, int CodCasino, byte op) throws DAOException {

		List<LastPaids> list = new ArrayList<LastPaids>();
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
			else if (LocationID ==0 && CodCasino == 5) {
				c = ConnectionPoolerBpl.getConnection();				
			}
			else if (LocationID ==0 && CodCasino == 2) {
				c = ConnectionPoolerParSD.getConnection();				
			}
			
			s = c.prepareCall("{ call Mystery.QLastPaids(?) }");
			s.setByte("op", op);
			
			ResultSet rs = s.executeQuery();

			while (rs.next()) {
				list.add(new LastPaids (
						rs.getString("Nombre"),
					    rs.getString("Fecha"),
						rs.getString("FechaPago"),
					    rs.getInt("TimeHours"),
					    rs.getDouble("RandomWin"),
					    rs.getInt("NumMaquina"),
					    rs.getDouble("DenominacionM"),
					    rs.getString("NameGame"),
					    rs.getString("AreaName"),
					    rs.getInt("Customer ID"),
					    rs.getString("Customer Name")
						));
			}

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
		
		return list;
	}
}
