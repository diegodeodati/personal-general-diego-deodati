package serviseatl.eventos;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import serviseatl.connection.ConnectionMarka;
import serviseatl.connection.DAOException;

public class SPSASExceptions implements Serializable {
    static final long serialVersionUID = 103844584801737232L;
	public List<SASExceptions> getSPSASExceptions () throws DAOException {

		List<SASExceptions> list = new ArrayList<SASExceptions>();
		Connection c = null;
	
		CallableStatement s = null;

		try {
			
			c = ConnectionMarka.getConnection();
			s = c.prepareCall(" select * from slot.sasexceptions order by code");
			
			ResultSet rs = s.executeQuery();

			while (rs.next()) {
				list.add(new SASExceptions (
						rs.getInt("Code"),
						rs.getString("DescriptionE")
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
