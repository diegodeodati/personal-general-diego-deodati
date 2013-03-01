package serviseatl.summarycasinomonth;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import serviseatl.connection.ConnectionItalyV2;
import serviseatl.connection.DAOException;

public class SPSummaryCasinoMonthV2 implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1148072725813293648L;

	public List<SummaryCasinoMonthV2> getSPSummaryCasinoMonthV2(int Op, int Criterio, long Fecha, int EmployeeID) throws DAOException {

		List<SummaryCasinoMonthV2> list = new ArrayList<SummaryCasinoMonthV2>();
		Connection c = null;
		CallableStatement s = null;
		java.sql.Date F = new java.sql.Date(Fecha);
		//String Aux="";
		//String AuxST = "Warehouse";
		try {
			c = ConnectionItalyV2.getConnection();
			s = c.prepareCall("{ call Web.QSummaryCasinoMonthV2(?,?,?,?) }");
			s.setInt("Op", Op);
			s.setInt("Criterio", Criterio);
			s.setDate("Fecha", F);
			s.setInt("EmployeeID", EmployeeID);
			ResultSet rs = s.executeQuery();
			
			while (rs.next()) {
				// Aux = rs.getString("Casino");
				//if (!Aux.equals(AuxST)){
					list.add(new SummaryCasinoMonthV2(
							rs.getString("aamslocationid"),
						rs.getString("aamslocationname"),
						rs.getDate("Fecha"),
					    rs.getDate("Fecha").getTime(),
					    rs.getDouble("NetWinLose")
						));
				//}
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
