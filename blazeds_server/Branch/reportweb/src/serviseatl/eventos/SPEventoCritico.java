package serviseatl.eventos;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import serviseatl.connection.ConnectionMarkaEvents;
import serviseatl.connection.DAOException;

public class SPEventoCritico implements Serializable {
    static final long serialVersionUID = 107844614471077271L;

    public List<EventoCritico> getSPEventoCritico (byte LocationID, int CodCasino, long Fecha, int Hora, int Tiempo, boolean EventoCritico) throws DAOException {

		List<EventoCritico> list = new ArrayList<EventoCritico>();
		Connection c = null;
		CallableStatement s = null;

		java.sql.Date Fecha_D = new java.sql.Date(Fecha);
		
		try {
			c = ConnectionMarkaEvents.getConnection();
			s = c.prepareCall("{ call ListaEventoCritico(?,?,?,?,?,?) }");
			s.setByte("LocationID", LocationID);
			s.setInt("CodCasino", CodCasino);
			s.setDate("Fecha", Fecha_D);
			s.setInt("Hora", Hora);
			s.setInt("Tiempo", Tiempo);
			s.setBoolean("EventoCritico", EventoCritico);
			
			ResultSet rs = s.executeQuery();

			while (rs.next()) {
				list.add(new EventoCritico (
					    rs.getString("CodMaquina"),
					    rs.getInt("NumMaquina"),
					    rs.getString("TipoPago"),
					    rs.getString("NFabricante"),
					    rs.getString("Modelo"),
					    rs.getString("NameGame"),
					    rs.getInt("EventoId"),
					    rs.getString("Evento"),
					    rs.getInt("Cantidad")
						));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e);
		} finally {
			ConnectionMarkaEvents.close(c);
		}
		
		return list;
	}

}
