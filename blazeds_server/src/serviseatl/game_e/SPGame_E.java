package serviseatl.game_e;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import serviseatl.connection.ConnectionMarka;
import serviseatl.connection.DAOException;


public class SPGame_E implements Serializable {
    static final long serialVersionUID = 1038415777060202L;
	public List<Game_E> getSPGame_E() throws DAOException {

		List<Game_E> list = new ArrayList<Game_E>();
		Connection c = null;
		CallableStatement s = null;

		try {
			c = ConnectionMarka.getConnection();
			s = c.prepareCall("{ call Slot.QLGame_E(?) }");
			s.setInt("op", 0);

			ResultSet rs = s.executeQuery();

			while (rs.next()) {
				list.add(new Game_E(
					    rs.getString("NameGame")
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
