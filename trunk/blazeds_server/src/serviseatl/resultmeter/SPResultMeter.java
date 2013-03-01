package serviseatl.resultmeter;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import java.sql.*;
import serviseatl.connection.ConnectionMarka;
import serviseatl.connection.DAOException;

public class SPResultMeter implements Serializable {
    static final long serialVersionUID = 103844514001000202L;
	public List<ResultMeter> getSPResultMeter(int Op, byte LocationID, int CodCasino, long FechaI , long FechaF) throws DAOException {

		List<ResultMeter> list = new ArrayList<ResultMeter>();
		Connection c = null;
		CallableStatement s = null;
		java.sql.Date FI = new java.sql.Date(FechaI);
		java.sql.Date FF = new java.sql.Date(FechaF);		

		try {
			c = ConnectionMarka.getConnection();
			s = c.prepareCall("{ call Slot.QResultMeters(?,?,?,?,?) }");
			s.setInt("Op", Op);
			s.setByte("LocationID", LocationID);
			s.setInt("CodCasino", CodCasino);
			s.setDate("FechaI", FI);
			s.setDate("FechaF", FF);

			ResultSet rs = s.executeQuery();

			while (rs.next()) {
				list.add(new ResultMeter(
						rs.getString("CodMaquina"),
						rs.getInt("NumMaquina"),
						rs.getString("NameGame"),
						rs.getString("NFabricante"),
						rs.getDouble("Denominacion"),
						rs.getInt("CoinAccept"),
						rs.getString("TipoPago"),
						rs.getString("Modelo"),
					    rs.getString("SimboloM"),
					    rs.getDouble("MeterIn"),
					    rs.getDouble("MeterOut"),
					    rs.getDouble("MeterDrop"),
					    rs.getInt("MeterGames"),
					    rs.getDouble("MeterJackPot"),
					    rs.getDouble("VoucherOut"),
					    rs.getDouble("MeterHandPay"),
					    rs.getDouble("SumJPHF"),
					    rs.getDouble("EFTAFT"),
					    rs.getDouble("VoucherIn"),
					    rs.getDouble("MeterBill"),
					    rs.getDouble("WinLoose"),
					    rs.getDouble("HoldPerc"),
					    rs.getDouble("TheoricalP"),
					    rs.getDouble("DiffPercent"),
					    rs.getDouble("HoldTheoMoney"),    
					    rs.getDouble("Bill_F"),
					    rs.getDouble("Coins_F"),
					    rs.getDouble("JackPot_F"),
					    rs.getDouble("HopperFill_F"),
					    rs.getDouble("MalFunction_F"),
					    rs.getDouble("OverPaid"),
					    rs.getDouble("Progressive"),    
					    rs.getDouble("WLoose_F"),    
					    rs.getDouble("VarianzaBill"),    
					    rs.getDouble("VarianzaJP"),    
					    rs.getDouble("Varianza"),    
					    rs.getDouble("CoinsForm")						
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
