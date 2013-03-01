package serviseatl.permisosFlex;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import java.sql.*;

import serviseatl.connection.ConnectionMarka;
import serviseatl.connection.DAOException;


public class SPPermisosFlex implements Serializable {
    static final long serialVersionUID = 103841544501078902L;
	public List<PermisosFlex> getSPPermisosFlex(byte LocationID, int EmployeeID) throws DAOException {

		List<PermisosFlex> list = new ArrayList<PermisosFlex>();
		Connection c = null;
		CallableStatement s = null;

		try {
			c = ConnectionMarka.getConnection();
			s = c.prepareCall("{ call Permisos.PermisosFlex(?,?) }");
			s.setByte("LocationID", LocationID);
			s.setInt("EmployeeID", EmployeeID);

			ResultSet rs = s.executeQuery();

			while (rs.next()) {
				list.add(new PermisosFlex(
					    rs.getString("MenuName"),
					    rs.getBoolean("Access"),
					    rs.getBoolean("Modified")
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
