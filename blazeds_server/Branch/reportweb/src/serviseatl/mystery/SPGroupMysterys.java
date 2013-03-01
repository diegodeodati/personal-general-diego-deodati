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

public class SPGroupMysterys implements Serializable {
    static final long serialVersionUID = 103844994006107232L;
	public List<GroupMysterys> getSPGroupMysterys (byte LocationID, int CodCasino, byte op, String dummy) throws DAOException {

		List<GroupMysterys> list = new ArrayList<GroupMysterys>();
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
			
			s = c.prepareCall("{ call web.QLGroupMysterys(?,?) }");
			s.setByte("op", op);
			s.setString("dummy", dummy);
			
			ResultSet rs = s.executeQuery();

			while (rs.next()) {
				list.add(new GroupMysterys (
						rs.getString("GNombre"),
					    rs.getString("Nombre"),
						rs.getDouble("Minimun"),
					    rs.getDouble("Maximun"),
					    rs.getDouble(5),
					    rs.getDouble("MinBet"),
					    rs.getDouble("Acumulate")
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
