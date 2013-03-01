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

public class SPAvgMystery implements Serializable {
    static final long serialVersionUID = 103844994206007232L;
	public List<AvgMystery> getSPAvgMystery (byte LocationID, int CodCasino, byte op, long Fecha) throws DAOException {

		List<AvgMystery> list = new ArrayList<AvgMystery>();
		Connection c = null;
		CallableStatement s = null;
		java.sql.Date F = new java.sql.Date(Fecha);
		
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
			
			s = c.prepareCall("{ call Mystery.QAvgMystery(?,?) }");
			s.setByte("op", op);
			s.setDate("Fecha", F);
			ResultSet rs = s.executeQuery();
			
			while (rs.next()) {
				list.add(new AvgMystery (
						rs.getString("Nombre"),
					    rs.getDouble("AvgHour"),
						rs.getDouble("AvgDays"),
					    rs.getDouble("AvgAmount"),
					    rs.getInt("Counter"),
					    rs.getDouble("TotalAmount")
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
