package serviseatl.summarycasinov2;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import java.sql.*;

import serviseatl.connection.ConnectionMarka;
import serviseatl.connection.DAOException;

public class SPSummaryCasinoV2 implements Serializable {
    static final long serialVersionUID = 103844514006007301L;
	public List<SummaryCasinoV2> getSPSummaryCasinoV2(int Op, int Criterio, long FechaI, long FechaF, int EmployeeID) throws DAOException {

		List<SummaryCasinoV2> list = new ArrayList<SummaryCasinoV2>();
		Connection c = null;
		CallableStatement s = null;
		java.sql.Date FI = new java.sql.Date(FechaI);
		java.sql.Date FF = new java.sql.Date(FechaF);		
		String Aux="";
		String AuxST = "Warehouse";
		try {
			c = ConnectionMarka.getConnection();
			s = c.prepareCall("{ call Web.QSummaryCasinov2(?,?,?,?,?) }");
			s.setInt("Op", Op);
			s.setInt("Criterio", Criterio);
			s.setDate("Fecha1", FI);
			s.setDate("Fecha2", FF);
			s.setInt("EmployeeID", EmployeeID);			
			ResultSet rs = s.executeQuery();
			while (rs.next()) {
				Aux = rs.getString("Casino");
				if (!Aux.equals(AuxST)){
					list.add(new SummaryCasinoV2(
						rs.getInt("LocationID"),
						rs.getInt("CodCasino"),
						rs.getString("Casino"),
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
					    rs.getString("CCiudad"),
					    rs.getString("OpenDate"),
					    rs.getInt("VltsZeroes"),
					    rs.getDouble("RatioVltsZeroes")
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
