package serviseatl.customers;

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

public class SPCustomers  implements Serializable {
    static final long serialVersionUID = 103844994006007232L;
	public List<Customers> getSPCustomers (byte LocationID, int CodCasino, long FechaI, long FechaF) throws DAOException {

		List<Customers> list = new ArrayList<Customers>();
		Connection c = null;
		CallableStatement s = null;
		java.sql.Date FI = new java.sql.Date(FechaI);
		java.sql.Date FF = new java.sql.Date(FechaF);
		
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
			
			s = c.prepareCall("{ call dbo.QRepCountCustomers(?,?,?) }");
			s.setInt("op", 1);
			s.setDate("FechaI", FI);
			s.setDate("FechaF", FF);			
			
			ResultSet rs = s.executeQuery();

			while (rs.next()) {
				list.add(new Customers (
						rs.getString("AreaName"),
					    rs.getInt("c0"),
						rs.getInt("c1"),
					    rs.getInt("c2"),
					    rs.getInt("c3"),
					    rs.getInt("c4"),
					    rs.getInt("c5"),
					    rs.getInt("c6"),
					    rs.getInt("c7"),
					    rs.getInt("c8"),
					    rs.getInt("c9"),
					    rs.getInt("c10"),
					    rs.getInt("c11"),
					    rs.getInt("c12"),
					    rs.getInt("c13"),
					    rs.getInt("c14"),
					    rs.getInt("c15"),
					    rs.getInt("c16"),
					    rs.getInt("c17"),
					    rs.getInt("c18"),
					    rs.getInt("c19"),
					    rs.getInt("c20"),
					    rs.getInt("c21"),
					    rs.getInt("c22"),
					    rs.getInt("c23"),
					    rs.getInt("c24"),
					    rs.getInt("c25"),
					    rs.getInt("Suma"),
					    rs.getDouble("Average")
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
				ConnectionPoolerSD.close(c);
			}

		}
		
		return list;
	}
}
