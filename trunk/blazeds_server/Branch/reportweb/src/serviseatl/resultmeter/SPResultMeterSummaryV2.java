package serviseatl.resultmeter;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import serviseatl.connection.ConnectionItalyV2;
import serviseatl.connection.DAOException;

public class SPResultMeterSummaryV2 implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2308977212380925940L;
	
	public List<ResultMeterSummaryV2> getSPResultMeterSummaryV2(int Op, String aamsLocationId, long FechaI , long FechaF, int EmployeeID) throws DAOException {

		List<ResultMeterSummaryV2> list = new ArrayList<ResultMeterSummaryV2>();
		Connection c = null;
		CallableStatement s = null;
		java.sql.Date FI = new java.sql.Date(FechaI);
		java.sql.Date FF = new java.sql.Date(FechaF);		

		try {
			c = ConnectionItalyV2.getConnection();
			s = c.prepareCall("{ call Web.QResultMetersV2(?,?,?,?,?) }");
			s.setInt("Op", Op);
			s.setString("aamslocationid", aamsLocationId);
			s.setDate("Fecha1", FI);
			s.setDate("Fecha2", FF);
			s.setInt("EmployeeID", EmployeeID);
			
			ResultSet rs = s.executeQuery();

			while (rs.next()) {
				list.add(new ResultMeterSummaryV2(
					    rs.getString("aamsvltid"),
					    rs.getString("concesionairevltid"),
					    rs.getDouble("MeterIn"),
					    rs.getDouble("MeterOut"),
					    rs.getDouble("MeterDrop"),
					    rs.getInt("MeterGames"),
					    rs.getDouble("MeterJackPot"),
					    rs.getDouble("MeterVoucherOut"),
					    rs.getDouble("MeterHandPay"),
					    rs.getDouble("SumJPHF"),
					    rs.getDouble("MeterVoucherIn"),
					    rs.getDouble("MeterBill"),
					    rs.getDouble("WinLoose"),
					    rs.getDouble("MeterEFTAFT"),
					    rs.getDouble("Bet"),
					    rs.getDouble("NumberVLTs"),             
					    rs.getDouble("PercentGestore"),         
					    rs.getDouble("PercentSupplier"),
					    rs.getDouble("RTP"),
					    rs.getDouble("PREU"),
					    rs.getDouble("AMMS05p"),
					    rs.getDouble("AMMS03p"),
					    rs.getDouble("PREUAMMSfee"),
					    rs.getDouble("TotalHouseWin"),
					    rs.getDouble("RetQuotaGestore"),
					    rs.getDouble("RetQuotaSupplier"),
					    rs.getDouble("QuotaHWBPlus"),
					    rs.getDouble("AvgNWPerMachine"),
					    rs.getDouble("AVGVlts"),
					    rs.getDouble("WorkingDays"),
					    rs.getDouble("AbsVlts"),
					    rs.getDouble("AbsVltsPerDay"),
					    rs.getDouble("AvgCreditsWagPerDayPerVLT"),
					    rs.getString("cabinet")
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
