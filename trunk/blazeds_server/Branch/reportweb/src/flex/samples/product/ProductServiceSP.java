package flex.samples.product;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

import flex.samples.ConnectionHelperSqlServer;
import flex.samples.DAOException;

public class ProductServiceSP {
	public List<Product> getProducts(String P1) throws DAOException {

		List<Product> list = new ArrayList<Product>();
		Connection c = null;
		CallableStatement s = null;
		System.out.println("  1");
		try {
			c = ConnectionHelperSqlServer.getConnection();
			//Statement s = c.createStatement();
			System.out.println("  2");
			s = c.prepareCall("{ call Lista1(?) }");
			s.setString(1, P1);
			ResultSet rs = s.executeQuery();
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
