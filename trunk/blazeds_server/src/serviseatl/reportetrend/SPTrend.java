package serviseatl.reportetrend;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import serviseatl.connection.ConnectionMarka;
import serviseatl.connection.DAOException;

public class SPTrend implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2312412461899007329L;

	public List<Trend> getSPTrend(int Op, int CodCasino) throws DAOException {
		List<Trend> list = new ArrayList<Trend>();
		Connection c = null;
		CallableStatement s = null;
		try {
			c = ConnectionMarka.getConnection();
			s = c.prepareCall("{ call Web.QTrend(?,?) }");
			s.setInt("op", Op);
			s.setInt("codcasino", CodCasino);
			
			ResultSet rs = s.executeQuery();

			while (rs.next()) {
				list.add(new Trend(
						rs.getString("CodCasino"),
					    rs.getString("Ccasino"),
					    rs.getString("Cciudad"),
					    rs.getString("OpeningDate"),
					    rs.getString("DateMonth"),
					    rs.getString("Yyear"),
					    rs.getString("Mmonth"),
					    rs.getInt("NroWorkDays"),
					    rs.getInt("EnabledVLTs"),
					    rs.getInt("WorkVLTs"),
					    rs.getDouble("AvgMeterIn"),
					    rs.getDouble("PercentMeterIn"),
					    rs.getDouble("AvgMeterOut"),
					    rs.getDouble("PercentMeterOut")
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
