package serviseatl.tables;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import java.sql.*;

import serviseatl.connection.ConnectionMarka;
import serviseatl.connection.DAOException;

public class SPTables3  implements Serializable {
    static final long serialVersionUID = 103111004401000331L;
	public List<Tables2> getSPTables3(byte Op, byte LocationID, int CodCasino, long Fecha) throws DAOException {

		List<Tables2> list = new ArrayList<Tables2>();
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
				list.add(new Tables2(
					    rs.getInt("CodGameTable"),
					    rs.getInt("CodGrupoTabla"),
					    rs.getString("CodigoTabla"),
					    rs.getString("e15"),
					    rs.getString("e16"),
					    rs.getString("e17"),
					    rs.getString("e18"),
					    rs.getString("e19"),
					    rs.getString("e20"),
					    rs.getString("e21"),
					    rs.getString("e22"),
					    rs.getString("e23"),
					    rs.getString("e00"),
					    rs.getString("e01"),
					    rs.getString("e02"),
					    rs.getString("e03"),
					    rs.getString("e04"),
					    rs.getString("e05")
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
