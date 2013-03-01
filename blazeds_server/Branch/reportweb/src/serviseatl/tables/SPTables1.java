package serviseatl.tables;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import java.sql.*;

import serviseatl.connection.ConnectionMarka;
import serviseatl.connection.DAOException;

public class SPTables1  implements Serializable {
    static final long serialVersionUID = 103111004401000001L;
	public List<Tables1> getSPTables1(byte Op, byte LocationID, int CodCasino, long Fecha) throws DAOException {

		List<Tables1> list = new ArrayList<Tables1>();
		Connection c = null;
		CallableStatement s = null;
		java.sql.Date F = new java.sql.Date(Fecha);
		try {

			c = ConnectionMarka.getConnection();
			s = c.prepareCall("{ call Tables.LSlipCash(?,?,?,?) }");
			s.setByte("Op", Op);
			s.setByte("LocationID", LocationID);
			s.setInt("CodCasino", CodCasino);
			s.setDate("Fecha", F);

			ResultSet rs = s.executeQuery();

			while (rs.next()) {
				list.add(new Tables1(
					    rs.getInt("CodGameTable"),
					    rs.getInt("CodGrupoTabla"),
					    rs.getString("CodigoTabla"),
					    rs.getString("Cash"),
					    rs.getString("Marker"),
					    rs.getString("Credit"),
					    rs.getString("Fill"),
					    rs.getString("Result")
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
