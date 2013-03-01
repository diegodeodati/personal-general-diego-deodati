package serviseatl.casinoweb;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import java.sql.*;

import serviseatl.connection.ConnectionMarka;
import serviseatl.connection.DAOException;

public class SPCasinoWeb implements Serializable {
    static final long serialVersionUID = 103844514001000301L;
	public List<CasinoWeb> getSPCasinoWeb(int Op, int EmployeeID) throws DAOException {

		List<CasinoWeb> list = new ArrayList<CasinoWeb>();
		Connection c = null;
		CallableStatement s = null;

		try {
			c = ConnectionMarka.getConnection();
			s = c.prepareCall("{ call Slot.Web_QLCasino2(?,?) }");
			s.setInt("Op", Op);
			s.setInt("EmployeeID", EmployeeID);

			ResultSet rs = s.executeQuery();

			while (rs.next()) {
				list.add(new CasinoWeb(
						rs.getString("CCasino"),
						rs.getString("Codigo")
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
