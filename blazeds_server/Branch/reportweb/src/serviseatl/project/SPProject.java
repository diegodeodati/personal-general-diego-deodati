package serviseatl.project;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import java.sql.*;

import serviseatl.connection.ConnectionMarka;
import serviseatl.connection.DAOException;

public class SPProject implements Serializable {
    static final long serialVersionUID = 103841544501060202L;
	public List<Project> getSPProject(byte LocationID, int CodCasino, int EmployeeID , int Criterio) throws DAOException {

		List<Project> list = new ArrayList<Project>();
		Connection c = null;
		CallableStatement s = null;

		try {
			c = ConnectionMarka.getConnection();
			s = c.prepareCall("{ call Slot.w_QLProject(?,?,?,?) }");
			s.setByte("LocationID", LocationID);
			s.setInt("CodCasino", CodCasino);
			s.setInt("EmployeeID", EmployeeID);
			s.setInt("Criterio", Criterio);

			ResultSet rs = s.executeQuery();

			while (rs.next()) {
				list.add(new Project(
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
					    rs.getDouble("WinLose")
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
