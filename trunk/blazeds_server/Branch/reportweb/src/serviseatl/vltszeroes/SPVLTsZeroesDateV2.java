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

public class SPVLTsZeroesDateV2 implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4256056869025212936L;

	public List<VLTsZeroesDateV2> getSPVLTsZeroesDateV2(int Op, String aamsVltId, long FechaI, long FechaF) throws DAOException {
		List<VLTsZeroesDateV2> list = new ArrayList<VLTsZeroesDateV2>();
		java.sql.Date FI = new java.sql.Date(FechaI);
		java.sql.Date FF = new java.sql.Date(FechaF);		
		Connection c = null;
		CallableStatement s = null;
		try {
			c = ConnectionItalyV2.getConnection();
			s = c.prepareCall("{ call web.QVLTsZeroesDateV2(?,?,?,?) }");
			s.setInt("op", Op);
			//s.setInt("codcasino", CodCasino);
			s.setString("aamsvltid", aamsVltId);
			s.setDate("fechai", FI);
			s.setDate("fechaf", FF);

			ResultSet rs = s.executeQuery();

			while (rs.next()) {
				list.add(new VLTsZeroesDateV2(
						rs.getString("DateZero")
						));
				//System.out.println(rs.getString("DateZero"));
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
