package serviseatl.graphicaldaily;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import java.sql.*;

import serviseatl.connection.ConnectionMarka;
import serviseatl.connection.DAOException;

public class SPGraphicalDaily implements Serializable {
    static final long serialVersionUID = 103844514006007232L;
	public List<GraphicalDaily> getSPGraphicalDaily (byte LocationID, int CodCasino, long FechaM, int DayClose) throws DAOException {

		List<GraphicalDaily> list = new ArrayList<GraphicalDaily>();
		Connection c = null;
		CallableStatement s = null;
		java.sql.Date FM = new java.sql.Date(FechaM);

		try {
			c = ConnectionMarka.getConnection();
			s = c.prepareCall("{ call Slot.w_QResultMetersByDate(?,?,?,?,?) }");
			s.setInt("op", 1);
			s.setByte("LocationID", LocationID);
			s.setInt("CodCasino", CodCasino);
			s.setDate("FechaM", FM);
			s.setInt("DayClose", DayClose);
			
			ResultSet rs = s.executeQuery();

			while (rs.next()) {
				list.add(new GraphicalDaily (
						rs.getDate("FechaM"),
					    rs.getDouble("MeterBill"),
					    rs.getDouble("WinLoose"),
					    rs.getDouble("TheoricalP"),
					    rs.getDouble("HoldPerc"),
					    rs.getDouble("DiffPercent"),
					    rs.getDouble("MeterIn"),
					    rs.getInt("MeterGames")
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
