package serviseatl.areas_e;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import serviseatl.connection.ConnectionMarka;
import serviseatl.connection.DAOException;

public class SPAreas_E implements Serializable {
    static final long serialVersionUID = 103841544771067777L;
	public List<Areas_E> getSPAreas_E() throws DAOException {

		List<Areas_E> list = new ArrayList<Areas_E>();
		Connection c = null;
		CallableStatement s = null;

		try {
			c = ConnectionMarka.getConnection();
			s = c.prepareCall("{ call Slot.QLAreas_E(?) }");
			s.setInt("op", 0);
			
			ResultSet rs = s.executeQuery();

			while (rs.next()) {
				list.add(new Areas_E(
					    rs.getString("AreaName")
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
