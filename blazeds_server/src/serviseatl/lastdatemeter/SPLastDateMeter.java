package serviseatl.lastdatemeter;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import serviseatl.connection.ConnectionMarka;
import serviseatl.connection.DAOException;

public class SPLastDateMeter implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8433856124304169867L;
	public List<LastDateMeter> getSPLastDateMeter(int Op) throws DAOException {

		List<LastDateMeter> list = new ArrayList<LastDateMeter>();
		Connection c = null;
		CallableStatement s = null;

		try {
			c = ConnectionMarka.getConnection();
			s = c.prepareCall("{ call web.QLastDateMeter(?) }");
			s.setInt("op", Op);
			
			ResultSet rs = s.executeQuery();

			while (rs.next()) {
				list.add(new LastDateMeter(
					    rs.getDate("Fecha")
				));
				System.out.println(rs.getDate("Fecha"));
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
