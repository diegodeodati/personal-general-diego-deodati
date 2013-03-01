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

public class SPResultDateV2 implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3039822254477744518L;

	public List<ResultDateV2> getSPResultDateV2(int Op, String aamsLocationId, long FechaI , long FechaF, int EmployeeID) throws DAOException {
		List<ResultDateV2> list = new ArrayList<ResultDateV2>();
		Connection c = null;
		CallableStatement s = null;
		java.sql.Date FI = new java.sql.Date(FechaI);
		java.sql.Date FF = new java.sql.Date(FechaF);	
		try {
			c = ConnectionItalyV2.getConnection();
			s = c.prepareCall("{ call Web.QResultDateV2(?,?,?,?,?) }");
			s.setInt("Op", Op);
			s.setString("aamslocationid", aamsLocationId);
			s.setDate("Fecha1", FI);
			s.setDate("Fecha2", FF);
			s.setInt("EmployeeID", EmployeeID);
			
			ResultSet rs = s.executeQuery();

			while (rs.next()) {
				list.add(new ResultDateV2(
					    rs.getDate("Fecham"),
					    rs.getString("FechaStr"),
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
					    rs.getDouble("AvgCreditsWagPerDayPerVLT")
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
