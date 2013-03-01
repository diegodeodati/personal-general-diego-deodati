package serviseatl.topcustomer;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import serviseatl.connection.ConnectionRunaYearlyCustomer;
import serviseatl.connection.DAOException;

public class SPTopCustomer implements Serializable {
    static final long serialVersionUID = 103844577001000202L;
	public List<TopCustomer> getSPTopCustomer(byte Op, byte LocationID, long FechaI , long FechaF, int EmployeeID) throws DAOException {

		List<TopCustomer> list = new ArrayList<TopCustomer>();
		Connection c = null;
		CallableStatement s = null;
		java.sql.Date FI = new java.sql.Date(FechaI);
		java.sql.Date FF = new java.sql.Date(FechaF);		

		try {
			c = ConnectionRunaYearlyCustomer.getConnection();
			s = c.prepareCall("{ call dbo.QTopCustByDate(?,?,?,?,?) }");
			s.setByte("Op", Op);
			s.setByte("LocationID", LocationID);
			s.setDate("FechaI", FI);
			s.setDate("FechaF", FF);
			s.setInt("EmployeeID", EmployeeID);
			
			ResultSet rs = s.executeQuery();

			while (rs.next()) {
				list.add(new TopCustomer(
						rs.getInt("CustomerID"),
						rs.getString("CustName"),
						rs.getString("Nationality"),						
						rs.getInt("Points")
						));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e);
		} finally {
			ConnectionRunaYearlyCustomer.close(c);
		}
		
		return list;
	}

}
