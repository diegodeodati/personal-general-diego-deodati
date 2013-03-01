package serviseatl.moneda_e;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import serviseatl.connection.ConnectionMarka;
import serviseatl.connection.DAOException;

public class SPMoneda_E implements Serializable {
    static final long serialVersionUID = 103841544771067778L;
	public List<Moneda_E> getSPMoneda_E() throws DAOException {

		List<Moneda_E> list = new ArrayList<Moneda_E>();
		Connection c = null;
		CallableStatement s = null;

		try {
			c = ConnectionMarka.getConnection();
			s = c.prepareCall("{ call QLMoneda_E(?) }");
			s.setInt("op", 0);
			
			ResultSet rs = s.executeQuery();

			while (rs.next()) {
				list.add(new Moneda_E(
					    rs.getString("SimboloM")
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
