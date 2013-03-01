package serviseatl.fabricante_e;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import java.sql.*;

import serviseatl.connection.ConnectionMarka;
import serviseatl.connection.DAOException;

public class SPFabricante_E implements Serializable {
    static final long serialVersionUID = 103841544771060202L;
	public List<Fabricante_E> getSPFabricante_E() throws DAOException {

		List<Fabricante_E> list = new ArrayList<Fabricante_E>();
		Connection c = null;
		CallableStatement s = null;

		try {
			c = ConnectionMarka.getConnection();
			s = c.prepareCall("{ call Slot.QLFabricante_E(?) }");
			s.setInt("op", 0);

			ResultSet rs = s.executeQuery();

			while (rs.next()) {
				list.add(new Fabricante_E(
					    rs.getString("NFabricante")
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
