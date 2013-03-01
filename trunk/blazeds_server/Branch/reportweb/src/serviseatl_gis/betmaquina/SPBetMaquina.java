package serviseatl_gis.betmaquina;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import java.sql.*;

import serviseatl.connection.ConnectionPoolerAtl;
import serviseatl.connection.ConnectionPoolerPar;
import serviseatl.connection.ConnectionPoolerSG;
import serviseatl.connection.ConnectionPoolerSD;
import serviseatl.connection.ConnectionPoolerBpl;
import serviseatl.connection.ConnectionPoolerParSD;
import serviseatl.connection.DAOException;

public class SPBetMaquina  implements Serializable {
    static final long serialVersionUID = 103841514101010100L;
	public List<BetMaquina> getSPBetMaquina(byte LocationID, int CodCasino, String CodMaquina) throws DAOException {

		List<BetMaquina> list = new ArrayList<BetMaquina>();
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

			s = c.prepareCall("{ call dbo.QLBet(?,?) }");
			s.setInt("op", 1);
			s.setString("CodMaquina", CodMaquina);
			
			ResultSet rs = s.executeQuery();

			while (rs.next()) {
				list.add(new BetMaquina( CodMaquina,
						rs.getDouble("bet")			
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
