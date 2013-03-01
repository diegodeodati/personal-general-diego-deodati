package serviseatl.eventos;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import serviseatl.connection.ConnectionMarkaEvents;
import serviseatl.connection.DAOException;

public class SPEvento implements Serializable {
    static final long serialVersionUID = 107844714071070272L;
    private String OnlyTimeToStr(Time Tiempo)
    {
    	Calendar T = Calendar.getInstance();
    	T.setTimeInMillis(Tiempo.getTime());
        String Aux = "";
        if (T.get(Calendar.HOUR_OF_DAY) < 10)
        {
            Aux = "0" + T.get(Calendar.HOUR_OF_DAY);
        }
        else
        {
            Aux = ""+T.get(Calendar.HOUR_OF_DAY);
        }
        if (T.get(Calendar.MINUTE) < 10)
        {
            Aux = Aux + ":0" + T.get(Calendar.MINUTE);
        }
        else
        {
            Aux = Aux + ":"+T.get(Calendar.MINUTE);
        }
        if (T.get(Calendar.SECOND) < 10)
        {
            Aux = Aux + ":0" + T.get(Calendar.SECOND);
        }
        else
        {
            Aux = Aux + ":" + T.get(Calendar.SECOND);
        }
        return (Aux);

    }

    private String OnlyDateToStr(Date Fecha)
    {
    	Calendar f = Calendar.getInstance();
    	f.setTimeInMillis(Fecha.getTime());
        String Aux = "";
        if (f.get(Calendar.MONTH)+1 < 10)
        {
            Aux = "0" + (f.get(Calendar.MONTH)+1);
        }
        else
        {
            Aux = ""+(f.get(Calendar.MONTH)+1);
        }
        if (f.get(Calendar.DAY_OF_MONTH) < 10)
        {
            Aux = Aux + "/0" + f.get(Calendar.DAY_OF_MONTH);
        }
        else
        {
            Aux = Aux + "/" + f.get(Calendar.DAY_OF_MONTH);
        }
        Aux = Aux + "/" + f.get(Calendar.YEAR);
        return (Aux);
    }

    public List<Evento> getSPEvento (byte LocationID, int CodCasino, String CodMaquina, int EventoID, long FHI, long FHF) throws DAOException {

		List<Evento> list = new ArrayList<Evento>();
		Connection c = null;
		CallableStatement s = null;

		java.sql.Date FHI_D = new java.sql.Date(FHI);
		java.sql.Date FHF_D = new java.sql.Date(FHF);		
		try {
			c = ConnectionMarkaEvents.getConnection();
			s = c.prepareCall("{ call ListaEventoMaquina(?,?,?,?,?,?) }");
			s.setByte("LocationID", LocationID);
			s.setInt("CodCasino", CodCasino);
			s.setString("CodMaquina", CodMaquina);
			s.setInt("EventoID", EventoID);
			s.setDate("FechaI", FHI_D);
			s.setDate("FechaF", FHF_D);
			
			ResultSet rs = s.executeQuery();

			while (rs.next()) {
				list.add(new Evento (
						OnlyDateToStr(rs.getDate("Fecha")),
						OnlyTimeToStr(rs.getTime("Hora")),
					    rs.getInt("EventoId"),
					    rs.getString("Evento")
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
