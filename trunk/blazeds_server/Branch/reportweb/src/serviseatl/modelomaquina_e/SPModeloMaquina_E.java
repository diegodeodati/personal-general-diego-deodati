package serviseatl.modelomaquina_e;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import serviseatl.connection.ConnectionMarka;
import serviseatl.connection.DAOException;

public class SPModeloMaquina_E implements Serializable {
    static final long serialVersionUID = 103841544771067773L;
	public List<ModeloMaquina_E> getSPModeloMaquina_E() throws DAOException {

		List<ModeloMaquina_E> list = new ArrayList<ModeloMaquina_E>();
		Connection c = null;
		CallableStatement s = null;

		try {
			c = ConnectionMarka.getConnection();
			s = c.prepareCall("{ call Slot.QModeloMaquina_E(?) }");
			s.setInt("op", 0);
			
			ResultSet rs = s.executeQuery();

			while (rs.next()) {
				list.add(new ModeloMaquina_E(
					    rs.getString("NameModelo")
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
