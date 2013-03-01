package serviseatl.reportetrend;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import serviseatl.connection.ConnectionItalyV2;
import serviseatl.connection.DAOException;

public class SPTrendV2 implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8352426605361156840L;
	public List<TrendV2> getSPTrendV2(int Op, String aamsLocationId) throws DAOException {
		List<TrendV2> list = new ArrayList<TrendV2>();
		Connection c = null;
		CallableStatement s = null;
		try {
			c = ConnectionItalyV2.getConnection();
			s = c.prepareCall("{ call Web.QTrendV2(?,?) }");
			s.setInt("op", Op);
			s.setString("aamslocationid", aamsLocationId);
			
			ResultSet rs = s.executeQuery();

			while (rs.next()) {
				list.add(new TrendV2(
						rs.getString("aamslocationid"),
					    rs.getString("aamslocationname"),
					    rs.getString("cciudad"),
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
			ConnectionItalyV2.close(c);
		}
		
		return list;
		
	}

}
