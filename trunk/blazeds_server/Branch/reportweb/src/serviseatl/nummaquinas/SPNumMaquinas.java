package serviseatl.nummaquinas;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import serviseatl.connection.ConnectionMarka;
import serviseatl.connection.DAOException;

public class SPNumMaquinas  implements Serializable {
    static final long serialVersionUID = 103841544771067774L;
	public List<NumMaquinas> getSPNumMaquinas() throws DAOException {

		List<NumMaquinas> list = new ArrayList<NumMaquinas>();
		Connection c = null;
		CallableStatement s = null;

		try {
			c = ConnectionMarka.getConnection();
			s = c.prepareCall("{ call Slot.QLNumMaquinas(?,?) }");
			s.setInt("op", 0);
			s.setByte("LocationID", (byte)0);
			
			ResultSet rs = s.executeQuery();

			while (rs.next()) {
				list.add(new NumMaquinas(
					    rs.getInt("NumMaquina")
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
