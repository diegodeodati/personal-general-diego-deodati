package serviseatl.summarycasinodate;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import serviseatl.connection.ConnectionMarka;
import serviseatl.connection.DAOException;

public class SPSummaryCasinoDate implements Serializable {
    static final long serialVersionUID = 103844514006007301L;
	public List<SummaryCasinoDate> getSPSummaryCasinoDate(int Op, int Criterio, long Fecha, int EmployeeID) throws DAOException {

		List<SummaryCasinoDate> list = new ArrayList<SummaryCasinoDate>();
		Connection c = null;
		CallableStatement s = null;
		java.sql.Date F = new java.sql.Date(Fecha);
		String Aux="";
		String AuxST = "Warehouse";		
		try {
			c = ConnectionMarka.getConnection();
			s = c.prepareCall("{ call Web.QSummaryCasinoDate(?,?,?,?) }");
			s.setInt("Op", Op);
			s.setInt("Criterio", Criterio);
			s.setDate("Fecha", F);
			s.setInt("EmployeeID", EmployeeID);			
			ResultSet rs = s.executeQuery();
			while (rs.next()) {
				Aux = rs.getString("Casino");
				if (!Aux.equals(AuxST)){
					list.add(new SummaryCasinoDate(
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
