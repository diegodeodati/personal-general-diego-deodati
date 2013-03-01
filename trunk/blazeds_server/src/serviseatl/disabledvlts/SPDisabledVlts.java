package serviseatl.disabledvlts;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import serviseatl.connection.ConnectionMarka;
import serviseatl.connection.DAOException;


public class SPDisabledVlts implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4268890024794954894L;

	public List<DisabledVlts> getSPDisabledVlts(byte LocationId, int CodCasino) throws DAOException {
		List<DisabledVlts> list = new ArrayList<DisabledVlts>();
		Connection c = null;
		CallableStatement s = null;
		try {
			c = ConnectionMarka.getConnection();
			s = c.prepareCall("{ call Web.QLDisabledVLTs(?,?) }");
			s.setByte("LocationId", LocationId);
			s.setInt("CodCasino", CodCasino);
			
			ResultSet rs = s.executeQuery();

			while (rs.next()) {
				list.add(new DisabledVlts(
						rs.getInt("CodCasino"),
						rs.getString("Ccasino"),
						rs.getString("CodMaquina"),
					    rs.getString("FechaStr")
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
