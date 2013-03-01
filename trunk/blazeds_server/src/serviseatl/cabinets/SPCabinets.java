package serviseatl.cabinets;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import serviseatl.connection.ConnectionMarka;
import serviseatl.connection.DAOException;

public class SPCabinets implements Serializable {
    static final long serialVersionUID = 103841544771067776L;
	public List<Cabinets> getSPCabinets() throws DAOException {

		List<Cabinets> list = new ArrayList<Cabinets>();
		Connection c = null;
		CallableStatement s = null;

		try {
			c = ConnectionMarka.getConnection();
			s = c.prepareCall("{ call Slot.QLCabinets(?,?) }");
			s.setInt("op", 1);
			s.setByte("LocationID", (byte)0);
			
			ResultSet rs = s.executeQuery();

			while (rs.next()) {
				list.add(new Cabinets(
					    rs.getString("Cabinet")
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
