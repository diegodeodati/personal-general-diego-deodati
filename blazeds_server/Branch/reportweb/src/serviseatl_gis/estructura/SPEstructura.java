package serviseatl_gis.estructura;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import java.sql.*;

import serviseatl.connection.ConnectionMarka;
import serviseatl.connection.DAOException;

public class SPEstructura implements Serializable {
    static final long serialVersionUID = 103824574001900300L;
	public List<Estructura> getSPEstructura(byte LocationID, int CodCasino) throws DAOException {

		List<Estructura> list = new ArrayList<Estructura>();
		Connection c = null;
		CallableStatement s = null;
		try {
			c = ConnectionMarka.getConnection();
			s = c.prepareCall("{ call GIS.ListaEstructura(?,?) }");
			s.setByte("LocationID", LocationID);
			s.setInt("CodCasino", CodCasino);
			
			ResultSet rs = s.executeQuery();
			while (rs.next()) {
				list.add(new Estructura(
						rs.getInt("Capa"),
						rs.getInt("Shp"),
						rs.getInt("Punto"),
					    rs.getDouble("X"),
					    rs.getDouble("Y")
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
