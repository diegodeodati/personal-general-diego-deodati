package flex.samples.product;


import java.util.ArrayList;
import java.util.List;
import java.sql.*;

import flex.samples.ConnectionHelperSqlServer;
import flex.samples.DAOException;

public class ProductServiceSqlServer {
	public List getProducts() throws DAOException {

		List list = new ArrayList();
		Connection c = null;

		try {
			c = ConnectionHelperSqlServer.getConnection();
			Statement s = c.createStatement();

			ResultSet rs = s.executeQuery("SELECT * FROM abcd ORDER BY a");
			while (rs.next()) {
				list.add(new Product(1,
						rs.getString("a"),
						rs.getString("b"),
						rs.getString("c"),
						rs.getString("d"),
						45.36,
						44));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e);
		} finally {
			ConnectionHelperSqlServer.close(c);
		}
		return list;

	}

}
