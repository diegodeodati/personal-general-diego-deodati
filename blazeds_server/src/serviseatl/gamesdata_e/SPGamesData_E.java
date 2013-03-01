package serviseatl.gamesdata_e;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import serviseatl.connection.ConnectionMarka;
import serviseatl.connection.DAOException;


public class SPGamesData_E implements Serializable {
    static final long serialVersionUID = 103841544799960202L;
	public List<GamesData_E> getSPGamesData_E() throws DAOException {

		List<GamesData_E> list = new ArrayList<GamesData_E>();
		Connection c = null;
		CallableStatement s = null;

		try {
			c = ConnectionMarka.getConnection();
			s = c.prepareCall("{ call Slot.QLGamesData_E(?) }");
			s.setInt("op", 0);

			ResultSet rs = s.executeQuery();

			while (rs.next()) {
				list.add(new GamesData_E(
					    rs.getString("IDGame")
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
