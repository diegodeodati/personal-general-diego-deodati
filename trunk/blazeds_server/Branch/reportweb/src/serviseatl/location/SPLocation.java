package serviseatl.location;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import serviseatl.connection.ConnectionMarka;
import serviseatl.connection.DAOException;

public class SPLocation implements Serializable {
    static final long serialVersionUID = 103841544771666755L;
	public List<Location> getSPLocation() throws DAOException {

		List<Location> list = new ArrayList<Location>();
		Connection c = null;
		CallableStatement s = null;

		try {
			c = ConnectionMarka.getConnection();
			s = c.prepareCall("{ call QLLocation(?) }");
			s.setInt("op", 0);
			
			ResultSet rs = s.executeQuery();

			while (rs.next()) {
				list.add(new Location(
					    rs.getByte("LocationID"),
					    rs.getString("LCountry"),
					    rs.getString("LCity"),
					    rs.getString("Location")
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
