package serviseatl.projection;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import java.sql.*;
import serviseatl.connection.ConnectionMarka;
import serviseatl.connection.DAOException;

public class SPProjection implements Serializable {
    static final long serialVersionUID = 103844514001000202L;
	public List<Projection> getSPProjection(int Op, byte LocationID, int Criterio, long Fecha1 , long Fecha2, int CodCasino, int EmployeeID) throws DAOException {

		List<Projection> list = new ArrayList<Projection>();
		Connection c = null;
		CallableStatement s = null;
		java.sql.Date FI = new java.sql.Date(Fecha1);
		java.sql.Date FF = new java.sql.Date(Fecha2);		

		try {
			c = ConnectionMarka.getConnection();
			s = c.prepareCall("{ call Slot.QLProjection(?,?,?,?,?,?,?) }");
			s.setInt("Op", Op);
			s.setByte("LocationID", LocationID);
			s.setInt("Criterio", Criterio);			
			s.setDate("Fecha1", FI);
			s.setDate("Fecha2", FF);
			s.setInt("CodCasino", CodCasino);
			s.setInt("EmployeeID", CodCasino);
			
			ResultSet rs = s.executeQuery();

			while (rs.next()) {
				list.add(new Projection(
						rs.getString("AnioMes"),
					    rs.getDouble("MeterIn"),
					    rs.getDouble("MeterOut"),
					    rs.getDouble("MeterDrop"),
					    rs.getInt("MeterGames"),
					    rs.getDouble("MeterJackPot"),
					    rs.getDouble("VoucherOut"),
					    rs.getDouble("MeterHandPay"),
					    rs.getDouble("SumJPHF"),
					    rs.getDouble("EFTAFT"),
					    rs.getDouble("MeterVoucherIn"),
					    rs.getDouble("MeterBill"),
					    rs.getDouble("WinLoose"),
					    rs.getDouble("Courtesy"),
					    rs.getDouble("PercBill"),
					    rs.getDouble("AVGBill"),
					    rs.getDouble("AVGWinLoose"),    
					    rs.getDouble("AVGCourtesy"),
					    rs.getDouble("HoldPerc"),
					    rs.getDouble("TheoricalP"),
					    rs.getDouble("DiffPercent"),
					    rs.getDouble("HoldTheoMoney"),
					    rs.getDouble("Bill_F"),
					    rs.getDouble("Coins_F"),    
					    rs.getDouble("Jackpot_F"),    
					    rs.getDouble("HopperFill_F"),    
					    rs.getDouble("MalFunction_F"),    
					    rs.getDouble("OverPaid"),    
					    rs.getDouble("Progressive"),						
					    rs.getDouble("WLoose_F"),
					    rs.getDouble("VarianzaBill"),
					    rs.getDouble("VarianzaJP"),
					    rs.getDouble("Varianza")					    
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
