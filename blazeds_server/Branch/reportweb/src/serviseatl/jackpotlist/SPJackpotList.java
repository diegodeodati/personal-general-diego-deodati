package serviseatl.jackpotlist;

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

import serviseatl.connection.ConnectionMarka;
import serviseatl.connection.DAOException;


public class SPJackpotList implements Serializable {
    static final long serialVersionUID = 103844514003022202L;
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

    public List<JackpotList> getSPJackpotList (byte LocationID, int CodCasino, long Fecha) throws DAOException {

		List<JackpotList> list = new ArrayList<JackpotList>();
		Connection c = null;
		CallableStatement s = null;

		java.sql.Date Fecha_D = new java.sql.Date(Fecha);
		
		try {
			c = ConnectionMarka.getConnection();
			s = c.prepareCall("{ call slot.QListaJackpots(?,?,?,?) }");
			s.setInt("Op", 1);
			s.setByte("LocationID", LocationID);
			s.setInt("CodCasino", CodCasino);
			s.setDate("Fecha", Fecha_D);
			
			ResultSet rs = s.executeQuery();

			while (rs.next()) {
				list.add(new JackpotList (
						OnlyDateToStr(rs.getDate("FechaJP")),
						OnlyTimeToStr(rs.getTime("HoraJP")),
					    rs.getString("Winning"),
					    rs.getInt("NumMaquina"),
					    rs.getInt("NumTrans"),
					    rs.getInt("Slip"),
					    rs.getDouble("Amount"),
					    rs.getDouble("Progressive"),
					    rs.getDouble("OverPaid"),
					    rs.getDouble("Amount")+rs.getDouble("Malfunction")+rs.getDouble("Progressive")+rs.getDouble("OverPaid"),
					    rs.getString("Manager"),
					    rs.getString("Cashier"),
					    rs.getString("Slot"),
					    rs.getString("CustID"),
					    rs.getString("CustomerName"),
					    rs.getDouble("Malfunction")
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
