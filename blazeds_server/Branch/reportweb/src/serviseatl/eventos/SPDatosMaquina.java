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

public class SPDatosMaquina implements Serializable {
    static final long serialVersionUID = 103844584801030232L;
	public List<DatosMaquina> getSPDatosMaquina (byte LocationID, int CodCasino, int NumMaquina, String CodMaquina) throws DAOException {

		List<DatosMaquina> list = new ArrayList<DatosMaquina>();
		Connection c = null;
	
		CallableStatement s = null;

		try {
			
			c = ConnectionMarka.getConnection();
			s = c.prepareCall("{ call web.DatosMaquina(?,?,?,?) }");
			s.setByte("LocationID", LocationID);
			s.setInt("CodCasino", CodCasino);
			s.setInt("NumMaquina", NumMaquina);
			s.setString("CodMaquina", CodMaquina);
			
			ResultSet rs = s.executeQuery();

			while (rs.next()) {
				list.add(new DatosMaquina (
						rs.getInt("LocationID"),
						rs.getInt("CodCasino"),
						rs.getString("CodMaquina"),
						rs.getInt("NumMaquina"),
						rs.getString("LCountry"),
					    rs.getString("CCasino"),
					    rs.getString("TipoPago"),
					    rs.getString("Cabinet"),
					    rs.getString("NFabricante"),
					    rs.getString("Modelo"),
					    rs.getString("NameGame"),
					    rs.getString("NJuego"),
					    rs.getString("DenominacionL")
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
