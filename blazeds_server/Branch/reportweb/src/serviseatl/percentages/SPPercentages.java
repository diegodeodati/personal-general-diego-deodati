package serviseatl.percentages;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import serviseatl.connection.ConnectionMarka;
import serviseatl.connection.DAOException;


public class SPPercentages implements Serializable {
    static final long serialVersionUID = 103841544771067772L;
	public List<Percentages> getSPPercentages() throws DAOException {

		List<Percentages> list = new ArrayList<Percentages>();
		Connection c = null;
		CallableStatement s = null;

		try {
			c = ConnectionMarka.getConnection();
			s = c.prepareCall("{ call Slot.QLPercentages(?) }");
			s.setInt("op", 1);

			ResultSet rs = s.executeQuery();

			while (rs.next()) {
				
				list.add(new Percentages(
					    rs.getDouble("Percentage")
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
