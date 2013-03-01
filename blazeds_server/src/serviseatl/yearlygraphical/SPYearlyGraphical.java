package serviseatl.yearlygraphical;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import java.sql.*;

import serviseatl.connection.ConnectionMarka;
import serviseatl.connection.DAOException;

public class SPYearlyGraphical implements Serializable {
    static final long serialVersionUID = 103844514001000202L;
	public List<YearlyGraphical> getSPYearlyGraphical (int LocationID, int CodCasino, long Fecha1, long Fecha2, int EmployeeID) throws DAOException {

		List<YearlyGraphical> list = new ArrayList<YearlyGraphical>();
		Connection c = null;
		CallableStatement s = null;
		java.sql.Date F1 = new java.sql.Date(Fecha1);
		java.sql.Date F2 = new java.sql.Date(Fecha2);
		
		try {
			c = ConnectionMarka.getConnection();
			s = c.prepareCall("{ call Slot.QLProjectionGraph(?,?,?,?,?,?,?) }");
			s.setInt("op", 1);
			s.setInt("LocationID", LocationID);
			s.setInt("Criterio", 2);
			s.setInt("CodCasino", CodCasino);
			s.setDate("Fecha1", F1);
			s.setDate("Fecha2", F2);
			s.setInt("EmployeeID", EmployeeID);
			
			ResultSet rs = s.executeQuery();

			while (rs.next()) {
				list.add(new YearlyGraphical (
						rs.getInt("anio"),
					    rs.getInt("mes"),
					    rs.getDouble("WinLoose")
						));
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
