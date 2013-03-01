package serviseatl.reportmeter;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import java.sql.*;

import serviseatl.connection.ConnectionMarka;
import serviseatl.connection.DAOException;

public class SPReportMeter  implements Serializable {
    static final long serialVersionUID = 103844588001000202L;
	public List<ReportMeter> getSPReportMeter(byte LocationID, int CodCasino, long Fecha) throws DAOException {

		List<ReportMeter> list = new ArrayList<ReportMeter>();
		Connection c = null;
		CallableStatement s = null;
		java.sql.Date F = new java.sql.Date(Fecha);

		try {
			c = ConnectionMarka.getConnection();
			s = c.prepareCall("{ call Slot.QCorregMetersDay(?,?,?) }");
			s.setByte("LocationID", LocationID);
			s.setInt("CodCasino", CodCasino);
			s.setDate("Fecha", F);

			ResultSet rs = s.executeQuery();

			while (rs.next()) {
				list.add(new ReportMeter(
						rs.getString("CodMaquina"),
						rs.getInt("NumMaquina"),
					    rs.getDouble("MeterIn"),
					    rs.getDouble("MeterOut"),
					    rs.getDouble("MeterDrop"),
					    rs.getInt("MeterGames"),
					    rs.getDouble("MeterJackPot"),
					    rs.getDouble("MeterVoucherOut"),
					    rs.getDouble("MeterHandPay"),
					    rs.getDouble("MeterEFTAFT"),
					    rs.getDouble("MeterVoucherIn"),
					    rs.getDouble("MeterBill"),
					    rs.getBoolean("ClearRam"),
					    rs.getString("Names")
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
