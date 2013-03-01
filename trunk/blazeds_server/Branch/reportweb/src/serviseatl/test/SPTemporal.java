package serviseatl.test;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import java.sql.*;

import serviseatl.connection.ConnectionLocal;
import serviseatl.connection.DAOException;


public class SPTemporal  implements Serializable {
    static final long serialVersionUID = 103844514009700202L;
	public List<Temporal> getSPTemporal() throws DAOException {

		List<Temporal> list = new ArrayList<Temporal>();
		Connection c = null;
		CallableStatement s = null;

		try {
			c = ConnectionLocal.getConnection();
			s = c.prepareCall("{ call dbo.P3() }");

			ResultSet rs = s.executeQuery();

			while (rs.next()) {
				System.out.println(rs.getInt("a"));
				list.add(new Temporal(
						rs.getInt("a"),
						rs.getString("b"),
						rs.getString("c"),
						rs.getString("d")
						));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e);
		} finally {
			ConnectionLocal.close(c);
		}
		
		return list;
	}

}
