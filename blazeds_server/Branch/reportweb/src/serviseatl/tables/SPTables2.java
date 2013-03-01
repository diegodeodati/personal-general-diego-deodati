package serviseatl.tables;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import java.sql.*;

import serviseatl.connection.ConnectionMarka;
import serviseatl.connection.DAOException;

public class SPTables2  implements Serializable {
    static final long serialVersionUID = 103111004401000221L;
	public List<Tables2> getSPTables2(byte Op, byte LocationID, int CodCasino, long Fecha) throws DAOException {

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
					    rs.getString("c15"),
					    rs.getString("c16"),
					    rs.getString("c17"),
					    rs.getString("c18"),
					    rs.getString("c19"),
					    rs.getString("c20"),
					    rs.getString("c21"),
					    rs.getString("c22"),
					    rs.getString("c23"),
					    rs.getString("c00"),
					    rs.getString("c01"),
					    rs.getString("c02"),
					    rs.getString("c03"),
					    rs.getString("c04"),
					    rs.getString("c05")
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
