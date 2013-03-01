package serviseatl.summarycasinomonth;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import serviseatl.connection.ConnectionMarka;
import serviseatl.connection.DAOException;

public class SPSummaryCasinoMonth  implements Serializable {
    static final long serialVersionUID = 103844514011107301L;
	public List<SummaryCasinoMonth> getSPSummaryCasinoMonth(int Op, int Criterio, long Fecha, int EmployeeID) throws DAOException {

		List<SummaryCasinoMonth> list = new ArrayList<SummaryCasinoMonth>();
		Connection c = null;
		CallableStatement s = null;
		java.sql.Date F = new java.sql.Date(Fecha);
		String Aux="";
		String AuxST = "Warehouse";
		try {
			c = ConnectionMarka.getConnection();
			s = c.prepareCall("{ call Web.QSummaryCasinoMonth(?,?,?,?) }");
			s.setInt("Op", Op);
			s.setInt("Criterio", Criterio);
			s.setDate("Fecha", F);
			s.setInt("EmployeeID", EmployeeID);
			ResultSet rs = s.executeQuery();
			
			while (rs.next()) {
				Aux = rs.getString("Casino");
				if (!Aux.equals(AuxST)){
					list.add(new SummaryCasinoMonth(
						rs.getString("Casino"),
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
			ConnectionMarka.close(c);
		}
		
		return list;
	}
}
