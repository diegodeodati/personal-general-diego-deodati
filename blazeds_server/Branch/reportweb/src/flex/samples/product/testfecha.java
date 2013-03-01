package flex.samples.product;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

import flex.samples.ConnectionHelperSqlServer;
import flex.samples.DAOException;

public class testfecha {
	public void getTestFecha() {

		Connection c = null;
		CallableStatement s = null;
		System.out.println("  1");
		try {
			c = ConnectionHelperSqlServer.getConnection();
			System.out.println("  2");
			java.util.Date f = new java.util.Date(); 
			java.sql.Date FF = new java.sql.Date(f.getTime());
			s = c.prepareCall("{ call fechatest(?) }");
			s.setDate(1, FF);
			s.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e);
		} finally {
			ConnectionHelperSqlServer.close(c);
		}
		return ;

	}

}