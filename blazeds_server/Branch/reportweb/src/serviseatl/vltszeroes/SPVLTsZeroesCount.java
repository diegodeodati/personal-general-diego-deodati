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


public class SPVLTsZeroesCount implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7942445818833028565L;

	public List<VLTsZeroesCount> getSPVLTsZeroesCount(int Op, int CodCasino, long FechaI, long FechaF) throws DAOException {
		List<VLTsZeroesCount> list = new ArrayList<VLTsZeroesCount>();
		java.sql.Date FI = new java.sql.Date(FechaI);
		java.sql.Date FF = new java.sql.Date(FechaF);		
		Connection c = null;
		CallableStatement s = null;
		try {
			c = ConnectionMarka.getConnection();
			s = c.prepareCall("{ call web.QVLTsZeroesCount(?,?,?,?) }");
			s.setInt("op", Op);
			s.setInt("codcasino", CodCasino);
			s.setDate("fechai", FI);
			s.setDate("fechaf", FF);

			ResultSet rs = s.executeQuery();

			while (rs.next()) {
				list.add(new VLTsZeroesCount(
						rs.getString("CodMaquina"),
					    rs.getInt("TotalZeroes")
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
