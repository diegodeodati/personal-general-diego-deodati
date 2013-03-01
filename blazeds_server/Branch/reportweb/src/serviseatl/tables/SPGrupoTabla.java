package serviseatl.tables;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import java.sql.*;

import serviseatl.connection.ConnectionMarka;
import serviseatl.connection.DAOException;

public class SPGrupoTabla  implements Serializable {
    static final long serialVersionUID = 103111004401000000L;
	public List<GrupoTabla> getSPGrupoTabla() throws DAOException {

		List<GrupoTabla> list = new ArrayList<GrupoTabla>();
		Connection c = null;
		CallableStatement s = null;

		try {
			c = ConnectionMarka.getConnection();
			s = c.prepareCall("select CodGrupoTabla, NGrupoTabla from Tables.GrupoTabla where locationid = 0 order by orden, NGrupoTabla");

			ResultSet rs = s.executeQuery();

			while (rs.next()) {
				list.add(new GrupoTabla(
					    rs.getInt("CodGrupoTabla"),
					    rs.getString("NGrupoTabla")
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
