package serviseatl.tjuegos_e;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import serviseatl.connection.ConnectionMarka;
import serviseatl.connection.DAOException;


public class SPTJuegos_E implements Serializable {
    static final long serialVersionUID = 103841544771067775L;
	public List<TJuegos_E> getSPTJuegos_E() throws DAOException {

		List<TJuegos_E> list = new ArrayList<TJuegos_E>();
		Connection c = null;
		CallableStatement s = null;

		try {
			c = ConnectionMarka.getConnection();
			s = c.prepareCall("{ call Slot.QLTJuegos_E(?) }");
			s.setInt("op", 0);
			
			ResultSet rs = s.executeQuery();

			while (rs.next()) {
				list.add(new TJuegos_E(
					    rs.getString("NJuego")
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
