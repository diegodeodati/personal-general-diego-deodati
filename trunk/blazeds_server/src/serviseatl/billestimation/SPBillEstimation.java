package serviseatl.billestimation;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import java.sql.*;

import serviseatl.connection.ConnectionMarka;
import serviseatl.connection.DAOException;


public class SPBillEstimation implements Serializable {
    static final long serialVersionUID = 103844514001000300L;
	public List<BillEstimation> getSPBillEstimation(int Op, byte LocationID, int CodCasino, long FechaI , long FechaF, long Fecha, int DayClose) throws DAOException {

		List<BillEstimation> list = new ArrayList<BillEstimation>();
		Connection c = null;
		CallableStatement s = null;
		java.sql.Date FI = new java.sql.Date(FechaI);
		java.sql.Date FF = new java.sql.Date(FechaF);		
		java.sql.Date F = new java.sql.Date(Fecha);
		try {
			c = ConnectionMarka.getConnection();
			s = c.prepareCall("{ call Slot.QResumenBillPrint(?,?,?,?,?,?,?) }");
			s.setInt("Op", Op);
			s.setByte("LocationID", LocationID);
			s.setInt("CodCasino", CodCasino);
			s.setDate("FechaI", FI);
			s.setDate("FechaF", FF);
			s.setDate("Fecha", F);
			s.setInt("DayClose", DayClose);
			
			ResultSet rs = s.executeQuery();

			while (rs.next()) {
				
				list.add(new BillEstimation(
						rs.getString("CodMaquina"),
						rs.getDouble("DenominacionM"),
						rs.getInt("NumMaquina"),
						rs.getString("NFabricante"),
						rs.getString("NameGame"),
					    rs.getString("SimboloM"),
					    rs.getInt("MeterOpen"),
					    rs.getInt("MeterClose"),
					    rs.getDouble("ResultM"),
					    rs.getDouble("MTD"),
					    rs.getDouble("AVGT"),
					    rs.getString("NombreS")
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
