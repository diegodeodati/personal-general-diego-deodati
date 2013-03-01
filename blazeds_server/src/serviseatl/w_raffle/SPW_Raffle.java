package serviseatl.w_raffle;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import java.sql.*;

import serviseatl.connection.ConnectionMarka;
import serviseatl.connection.DAOException;

public class SPW_Raffle  implements Serializable {
    static final long serialVersionUID = 103844514001000202L;
	public List<W_Raffle> getSPW_Raffle (byte LocationID, int CodCasino) throws DAOException {

		List<W_Raffle> list = new ArrayList<W_Raffle>();
		Connection c = null;
		CallableStatement s = null;

		try {
			c = ConnectionMarka.getConnection();
			s = c.prepareCall("{ call Customer.W_Raffle(?,?) }");
			s.setByte("LocationID", LocationID);
			s.setInt("CodCasino", CodCasino);
			
			ResultSet rs = s.executeQuery();

			while (rs.next()) {
				list.add(new W_Raffle (
						rs.getString("Nombre"),
					    rs.getString("FechaI"),
					    rs.getString("HoraI"),
					    rs.getString("FechaF"),
					    rs.getString("HoraF"),
					    rs.getInt("CustomerID"),
					    rs.getString("NewCard"),
					    rs.getString("TicketNumber"),
					    rs.getString("NCustomer"),
					    rs.getInt("Numero"),
					    rs.getDouble("Monto")
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
