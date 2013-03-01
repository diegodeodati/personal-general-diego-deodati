package serviseatl.summarycasinov2;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import serviseatl.connection.ConnectionItalyV2;
import serviseatl.connection.DAOException;

public class SPSummaryCasinoV3 implements Serializable{
    
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 1755159420640477556L;

	public List<SummaryCasinoV3> getSPSummaryCasinoV3(int Op, int Criterio, long FechaI, long FechaF, int EmployeeID) throws DAOException {

		List<SummaryCasinoV3> list = new ArrayList<SummaryCasinoV3>();
		Connection c = null;
		CallableStatement s = null;
		java.sql.Date FI = new java.sql.Date(FechaI);
		java.sql.Date FF = new java.sql.Date(FechaF);		
		// String Aux="";
		// String AuxST = "Warehouse";
		try {
			c = ConnectionItalyV2.getConnection();
			s = c.prepareCall("{ call Web.QSummaryCasinov3(?,?,?,?,?) }");
			s.setInt("Op", Op);
			s.setInt("Criterio", Criterio);
			s.setDate("Fecha1", FI);
			s.setDate("Fecha2", FF);
			s.setInt("EmployeeID", EmployeeID);			
			ResultSet rs = s.executeQuery();
			while (rs.next()) {
				// Aux = rs.getString("Casino");
				//if (!Aux.equals(AuxST)){
					list.add(new SummaryCasinoV3(
						rs.getString("aamsLocationId"),
						rs.getString("aamsLocationName"),
						rs.getString("cciudad"),
						rs.getString("esercentename"),
						rs.getString("gestorename"),
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
					    rs.getDouble("MeterEftAft"),
					    rs.getDouble("Progressive"),
					    rs.getDouble("Courtesy"),
					    rs.getDouble("Complementary"),    
					    rs.getDouble("ValuePointRedeem"),
					    rs.getDouble("PointsRedeem"),
					    rs.getDouble("TotalPoints"),
					    rs.getDouble("TotalPromo"),
					    rs.getDouble("NetWinLose"),
					    rs.getDouble("PercBill"),
					    rs.getDouble("Bet"),
					    rs.getDouble("Raffle"),

					    rs.getInt("NumberVLTs"),
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
					    rs.getInt("WorkingDays"),
					    rs.getDouble("AbsVlts"),
					    rs.getDouble("AbsVltsPerDay"),
					    rs.getDouble("AvgCreditsWagPerDayPerVLT"),
					    rs.getString("OpenDate"),
					    rs.getInt("VltsZeroes"),
					    rs.getDouble("RatioVltsZeroes")
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
