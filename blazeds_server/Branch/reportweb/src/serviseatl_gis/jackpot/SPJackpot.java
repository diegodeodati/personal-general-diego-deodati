package serviseatl_gis.jackpot;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.io.Serializable;
import java.sql.*;

import serviseatl.connection.ConnectionMarka;
import serviseatl.connection.DAOException;


public class SPJackpot implements Serializable {
    static final long serialVersionUID = 103824574001900300L;
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
    
	public List<Jackpot> getSPJackpot(byte LocationID, int CodCasino) throws DAOException {

		List<Jackpot> list = new ArrayList<Jackpot>();
		Connection c = null;
		CallableStatement s = null;
		java.sql.Date F = new java.sql.Date(0);
		String Customer = "";
		String CustomerID = "";
		try {
			c = ConnectionMarka.getConnection();
			s = c.prepareCall("{ call Slot.QJackpotCustP(?,?,?,?,?) }");
			s.setInt("op", 2);			
			s.setByte("LocationID", LocationID);
			s.setInt("CodCasino", CodCasino);
			s.setDate("Fecha", F);
			s.setString("CodMaquina", "");			
			
			ResultSet rs = s.executeQuery();

			while (rs.next()) {
				if (rs.getString("CustomerID") == null)
				{ 
					CustomerID = "";
					Customer = "";
				}
				else
				{
					CustomerID = rs.getString("CustomerID");
					Customer = rs.getString("Customer");;
				}	
				list.add(new Jackpot(
						rs.getInt("NumMaquina"),
						OnlyDateToStr(rs.getDate("FechaJP")),
						OnlyTimeToStr(rs.getTime("HoraJP")),
					    rs.getDouble("Amount"),
					    CustomerID,
					    Customer,
					    rs.getString("Manager"),
					    rs.getString("Slot"),
					    rs.getString("Cashier")
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
