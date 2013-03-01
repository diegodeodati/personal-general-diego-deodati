package serviseatl.summarycasinodate;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import serviseatl.connection.ConnectionItalyV2;
import serviseatl.connection.DAOException;

public class SPSummaryCasinoDateV2 implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8208347703858090331L;
	public List<SummaryCasinoDateV2> getSPSummaryCasinoDateV2(int Op, int Criterio, long Fecha, int EmployeeID) throws DAOException {

		List<SummaryCasinoDateV2> list = new ArrayList<SummaryCasinoDateV2>();
		Connection c = null;
		CallableStatement s = null;
		java.sql.Date F = new java.sql.Date(Fecha);
		String Aux="";
		String AuxST = "Warehouse";		
		try {
			c = ConnectionItalyV2.getConnection();
			s = c.prepareCall("{ call Web.QSummaryCasinoDateV2(?,?,?,?) }");
			s.setInt("Op", Op);
			s.setInt("Criterio", Criterio);
			s.setDate("Fecha", F);
			s.setInt("EmployeeID", EmployeeID);			
			ResultSet rs = s.executeQuery();
			while (rs.next()) {
				//Aux = rs.getString("Casino");
				if (!Aux.equals(AuxST)){
					list.add(new SummaryCasinoDateV2(
						rs.getString("aamslocationname"),
						rs.getDate("Fecha"),
					    rs.getDate("Fecha").getTime(),
					    rs.getDouble("NetWinLose")
						));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e);
		} finally {
			ConnectionItalyV2.close(c);
		}
		
		return list;
	}

}
