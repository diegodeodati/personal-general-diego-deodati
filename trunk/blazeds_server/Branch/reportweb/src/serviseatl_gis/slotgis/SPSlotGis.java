package serviseatl_gis.slotgis;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import java.sql.*;

import serviseatl.connection.ConnectionMarka;
import serviseatl.connection.DAOException;

public class SPSlotGis  implements Serializable {
    static final long serialVersionUID = 103844514001000300L;
	public List<SlotGis> getSPSlotGis(byte LocationID, int CodCasino, String CodMaquina) throws DAOException {

		List<SlotGis> list = new ArrayList<SlotGis>();
		Connection c = null;
		CallableStatement s = null;
		try {
			c = ConnectionMarka.getConnection();
			s = c.prepareCall("{ call GIS.ListaSlotGis(?,?,?) }");
			s.setByte("LocationID", LocationID);
			s.setInt("CodCasino", CodCasino);
			s.setString("CodMaquina", CodMaquina);
			
			ResultSet rs = s.executeQuery();

			while (rs.next()) {
				
				list.add(new SlotGis(
						rs.getString("CodMaquina"),
						rs.getDouble("DenominacionM"),
						rs.getInt("NumMaquina"),
						rs.getString("NameGame"),
						rs.getString("NFabricante"),
					    rs.getString("TipoPago"),
					    rs.getDouble("X"),
					    rs.getDouble("Y"),
					    rs.getDouble("Alfa"),
					    rs.getDouble("XC"),
					    rs.getDouble("YC"),
					    false,
						0,
						0,						
					    "",
					    rs.getInt("CodFabricante")
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
