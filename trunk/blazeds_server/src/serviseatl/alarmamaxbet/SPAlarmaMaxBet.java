package serviseatl.alarmamaxbet;

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

import serviseatl.connection.ConnectionPoolerAtl;
import serviseatl.connection.ConnectionPoolerBpl;
import serviseatl.connection.ConnectionPoolerPar;
import serviseatl.connection.ConnectionPoolerSD;
import serviseatl.connection.ConnectionPoolerSG;
import serviseatl.connection.ConnectionPoolerParSD;
import serviseatl.connection.DAOException;

public class SPAlarmaMaxBet implements Serializable {
    static final long serialVersionUID = 103847774206007232L;
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
        if (f.get(Calendar.MONTH) < 10)
        {
            Aux = "0" + f.get(Calendar.MONTH);
        }
        else
        {
            Aux = ""+f.get(Calendar.MONTH);
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
    
	public List<AlarmaMaxBet> getSPAlarmaMaxBet(byte Op, byte LocationID, int CodCasino) throws DAOException {

		List<AlarmaMaxBet> list = new ArrayList<AlarmaMaxBet>();
		Connection c = null;
		CallableStatement s = null;
		
		try {
			if (LocationID == 0 && CodCasino == 3) {
				c = ConnectionPoolerAtl.getConnection();
			}
			else if (LocationID ==0 && CodCasino == 6) {
				c = ConnectionPoolerPar.getConnection();				
			}
			else if (LocationID ==0 && CodCasino == 1) {
				c = ConnectionPoolerSG.getConnection();				
			}
			else if (LocationID ==0 && CodCasino == 7) {
				c = ConnectionPoolerSD.getConnection();				
			}
			else if (LocationID ==0 && CodCasino == 2) {
				c = ConnectionPoolerParSD.getConnection();				
			}
			else if (LocationID ==0 && CodCasino == 5) {
				c = ConnectionPoolerBpl.getConnection();				
			}
			
			s = c.prepareCall("{ call dbo.QAlarma(?) }");
			s.setByte("op", Op);

			ResultSet rs = s.executeQuery();
			
			while (rs.next()) {
				list.add(new AlarmaMaxBet (
						OnlyDateToStr(rs.getDate("FechaHora")),
						OnlyTimeToStr(rs.getTime("FechaHora")),
					    rs.getString("CodMaquina"),
						rs.getInt("NumMaquina"),
					    rs.getInt("CustomerID"),
					    rs.getDouble("Amount"),
					    rs.getDouble("MaxBet"),					    
					    rs.getDouble("Denominacion")
						));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e);
		} finally {
			if (LocationID == 0 && CodCasino == 3) {
				ConnectionPoolerAtl.close(c);
			}
			else if (LocationID ==0 && CodCasino == 6) {
				ConnectionPoolerPar.close(c);
			}
			else if (LocationID ==0 && CodCasino == 1) {
				ConnectionPoolerSG.close(c);
			}
			else if (LocationID ==0 && CodCasino == 7) {
				ConnectionPoolerSD.close(c);
			}
			else if (LocationID ==0 && CodCasino == 5) {
				ConnectionPoolerBpl.close(c);
			}
			
		}
		
		return list;
	}
}
