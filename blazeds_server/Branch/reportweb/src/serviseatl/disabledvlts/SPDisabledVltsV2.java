package serviseatl.disabledvlts;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import serviseatl.connection.ConnectionItalyV2;
import serviseatl.connection.DAOException;

public class SPDisabledVltsV2 implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2307147361839238219L;

	public List<DisabledVltsV2> getSPDisabledVltsV2(String aamsLocationId) throws DAOException {
		List<DisabledVltsV2> list = new ArrayList<DisabledVltsV2>();
		Connection c = null;
		CallableStatement s = null;
		try {
			c = ConnectionItalyV2.getConnection();
			s = c.prepareCall("{ call Web.QLDisabledVLTsV2(?) }");
			s.setString("aamsLocationId", aamsLocationId);
			
			ResultSet rs = s.executeQuery();

			while (rs.next()) {
				list.add(new DisabledVltsV2(
						rs.getString("aamsvltid"),
					    rs.getString("FechaStr")
						));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e);
		} finally {
			ConnectionItalyV2.close(c);
		}

		return list;
	}
	
}
