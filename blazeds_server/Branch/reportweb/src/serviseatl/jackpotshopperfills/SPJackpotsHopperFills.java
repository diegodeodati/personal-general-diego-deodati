package serviseatl.jackpotshopperfills;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import java.sql.*;

import serviseatl.connection.ConnectionMarka;
import serviseatl.connection.DAOException;

public class SPJackpotsHopperFills implements Serializable {
    static final long serialVersionUID = 103844599001000202L;
	public List<JackpotsHopperFills> getSPJackpotsHopperFills(int Op, byte LocationID, int CodCasino, int DayClose, long FechaI , long FechaF) throws DAOException {

		List<JackpotsHopperFills> list = new ArrayList<JackpotsHopperFills>();
		Connection c = null;
		CallableStatement s = null;
		java.sql.Date FI = new java.sql.Date(FechaI);
		java.sql.Date FF = new java.sql.Date(FechaF);		

		try {
			c = ConnectionMarka.getConnection();
			s = c.prepareCall("{ call Slot.QDailyJPHP(?,?,?,?,?,?) }");
			s.setInt("Op", Op);
			s.setByte("LocationID", LocationID);
			s.setInt("CodCasino", CodCasino);
			s.setInt("DayClose", DayClose);
			s.setDate("FechaI", FI);
			s.setDate("FechaF", FF);

			ResultSet rs = s.executeQuery();

			while (rs.next()) {
				list.add(new JackpotsHopperFills(
						rs.getString("CodMaquina"),
						rs.getInt("NumMaquina"),
						rs.getDouble("AmountMeter"),
						rs.getDouble("Jackpot"),
						rs.getDouble("HopperFill"),
						rs.getDouble("MalFunction"),
						rs.getDouble("OverPaid"),
						rs.getDouble("Progressive"),
					    rs.getDouble("Variance")						
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
