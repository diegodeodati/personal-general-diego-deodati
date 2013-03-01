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

public class SPTrendMonthV2 implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4005324583556046499L;
	public List<TrendMonthV2> getSPTrendMonthV2(int Op, String aamsLocationId, long Fecha) throws DAOException {
		List<TrendMonthV2> list = new ArrayList<TrendMonthV2>();
		Connection c = null;
		CallableStatement s = null;
		java.sql.Date F = new java.sql.Date(Fecha);
		try {
			c = ConnectionItalyV2.getConnection();
			s = c.prepareCall("{ call Web.QTrendMonthV2(?,?,?) }");
			s.setInt("op", Op);
			s.setString("aamslocationid", aamsLocationId);
			s.setDate("fecha", F);
			//s.executeUpdate();
			ResultSet rs = s.executeQuery();
			//ResultSet rs = s.getGeneratedKeys();

			while (rs.next()) {
				System.out.println("aamslocationid : "+rs.getString("aamslocationid") + " aamslocationname : " + rs.getString("aamslocationname"));
				list.add(new TrendMonthV2(
						rs.getString("aamslocationid"),
					    rs.getString("aamslocationname"),
					    rs.getString("cciudad"),
					    rs.getString("OpeningDate"),
					    rs.getString("DateMonth1"),
					    rs.getString("Mmonth1"),
					    rs.getInt("WorkVLTs1"),
					    rs.getDouble("AvgMeterIn1"),
					    rs.getDouble("PercentMeterIn1"),
					    rs.getDouble("AvgMeterOut1"),
					    rs.getDouble("PercentMeterOut1"),
					    rs.getString("DateMonth2"),
					    rs.getString("Mmonth2"),
					    rs.getInt("WorkVLTs2"),
					    rs.getDouble("AvgMeterIn2"),
					    rs.getDouble("PercentMeterIn2"),
					    rs.getDouble("AvgMeterOut2"),
					    rs.getDouble("PercentMeterOut2"),
					    rs.getString("DateMonth3"),
					    rs.getString("Mmonth3"),
					    rs.getInt("WorkVLTs3"),
					    rs.getDouble("AvgMeterIn3"),
					    rs.getDouble("PercentMeterIn3"),
					    rs.getDouble("AvgMeterOut3"),
					    rs.getDouble("PercentMeterOut3")
						));
				//System.out.println(rs.getString("CodCasino"));
				//System.out.println(rs.getString("Ccasino"));
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
