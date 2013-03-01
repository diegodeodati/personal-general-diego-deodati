package serviseatl.vltszeroes;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import serviseatl.connection.ConnectionMarka;
import serviseatl.connection.DAOException;

public class SPVLTsZeroesDate implements  Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8766042685837757659L;

	public List<VLTsZeroesDate> getSPVLTsZeroesDate(int Op, String CodMaquina, long FechaI, long FechaF) throws DAOException {
		List<VLTsZeroesDate> list = new ArrayList<VLTsZeroesDate>();
		java.sql.Date FI = new java.sql.Date(FechaI);
		java.sql.Date FF = new java.sql.Date(FechaF);		
		Connection c = null;
		CallableStatement s = null;
		try {
			c = ConnectionMarka.getConnection();
			s = c.prepareCall("{ call web.QVLTsZeroesDate(?,?,?,?) }");
			s.setInt("op", Op);
			//s.setInt("codcasino", CodCasino);
			s.setString("codmaquina", CodMaquina);
			s.setDate("fechai", FI);
			s.setDate("fechaf", FF);

			ResultSet rs = s.executeQuery();

			while (rs.next()) {
				list.add(new VLTsZeroesDate(
						rs.getString("DateZero")
						));
				//System.out.println(rs.getString("DateZero"));
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
