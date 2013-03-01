package serviseatl.vltszeroes;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import serviseatl.connection.ConnectionItalyV2;
import serviseatl.connection.DAOException;

public class SPVLTsZeroesCountV2 implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1951691630830420901L;

	public List<VLTsZeroesCountV2> getSPVLTsZeroesCountV2(int Op, String aamsLocationId, long FechaI, long FechaF) throws DAOException {
		List<VLTsZeroesCountV2> list = new ArrayList<VLTsZeroesCountV2>();
		java.sql.Date FI = new java.sql.Date(FechaI);
		java.sql.Date FF = new java.sql.Date(FechaF);		
		Connection c = null;
		CallableStatement s = null;
		try {
			c = ConnectionItalyV2.getConnection();
			s = c.prepareCall("{ call web.QVLTsZeroesCountV2(?,?,?,?) }");
			s.setInt("op", Op);
			s.setString("aamslocationid", aamsLocationId);
			s.setDate("fechai", FI);
			s.setDate("fechaf", FF);

			ResultSet rs = s.executeQuery();

			while (rs.next()) {
				list.add(new VLTsZeroesCountV2(
						rs.getString("aamsvltid"),
					    rs.getInt("TotalZeroes")
						));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e);
		} finally {
			ConnectionItalyV2.close(c);
		}
		
		return list;
		
	}
}
