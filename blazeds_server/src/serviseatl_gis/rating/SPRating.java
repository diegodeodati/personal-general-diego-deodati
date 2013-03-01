package serviseatl_gis.rating;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.io.Serializable;
import java.sql.*;

import serviseatl.connection.ConnectionRuna;
import serviseatl.connection.DAOException;

public class SPRating implements Serializable {
    static final long serialVersionUID = 103833514001066202L;

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

    private String DateTimeToStr(Date Fecha, Time Hora)
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
        f.setTimeInMillis(Hora.getTime());
        if (f.get(Calendar.HOUR_OF_DAY) < 10)
        {
        Aux = Aux + " 0" + f.get(Calendar.HOUR_OF_DAY);
        }
        else 
        {
            Aux = Aux + " " + f.get(Calendar.HOUR_OF_DAY);
        }
        if (f.get(Calendar.MINUTE) < 10)
        {
        	Aux = Aux + ":0" + f.get(Calendar.MINUTE);
        }
        else 
        {
        	Aux = Aux + ":" + f.get(Calendar.MINUTE);
        }
        if (f.get(Calendar.SECOND) < 10)
        {
        	Aux = Aux + ":0" + f.get(Calendar.SECOND);
        }
        else
        {
        	Aux = Aux + ":" + f.get(Calendar.SECOND);
        }
        
        return (Aux);
    }

    private String TimeToString(Date TiempoF)
    {
        String Aux = "";

/*        Tiempo = Tiempo.AddDays(-1);
        if (Tiempo.Year > 1900)
        {
            Aux = Aux + ((int)(Tiempo.Year - 1900)).ToString() + " years ";
        }
        if ( Tiempo.Year>=1900 && Tiempo.Month > 1)
        {
            Aux = Aux + (Tiempo.Month -1).ToString() + " months ";
        }
        if (Tiempo.Year >= 1900 && Tiempo.Day+1 >1)
        {
            Aux = Aux + Tiempo.Day.ToString() + " days ";
        }
        Aux = Aux+ Tiempo.Hour.ToString() + ":";
        if (Tiempo.Minute<10)
        {
            Aux = Aux+"0" + Tiempo.Minute.ToString();
        }
        else 
        {
            Aux = Aux+ Tiempo.Minute.ToString();
        }
        if (Tiempo.Second < 10)
        {
            Aux = Aux+":0" + Tiempo.Second.ToString();
        }
        else
        {
            Aux = Aux + ":" + Tiempo.Second.ToString();
        }
        */
        return (Aux);
  
        //return(Tiempo.ToString()+"  "+Aux);
    }
    
    public List<Rating> getSPRating(int Accion, byte LocationID, int CustomerID) throws DAOException {

		List<Rating> list = new ArrayList<Rating>();
		Connection c = null;
		CallableStatement s = null;
		Calendar FI = Calendar.getInstance();
		Calendar FF = Calendar.getInstance();
		Calendar FA = Calendar.getInstance();
        FA.set(Calendar.HOUR_OF_DAY,0);
        FA.set(Calendar.MINUTE,0);
        FA.set(Calendar.SECOND,0);
        FA.set(Calendar.MILLISECOND,0);            

        int Reporte = 0;
        if (Accion == 1)
        {
            FI.set(Calendar.DAY_OF_MONTH,1);
            FI.set(Calendar.HOUR_OF_DAY,0);
            FI.set(Calendar.MINUTE,0);
            FI.set(Calendar.SECOND,0);
            FI.set(Calendar.MILLISECOND,0);            

            Reporte = 2;
        }
        else if (Accion == 2)
        {
            FI.set(Calendar.MONTH,0);
        	FI.set(Calendar.DAY_OF_MONTH,1);
            FI.set(Calendar.HOUR_OF_DAY,0);
            FI.set(Calendar.MINUTE,0);
            FI.set(Calendar.SECOND,0);
            FI.set(Calendar.MILLISECOND,0);            
            Reporte = 2;
        }
        else if (Accion == 3)
        {
            FI.set(Calendar.YEAR,2007);            
        	FI.set(Calendar.MONTH,0);
        	FI.set(Calendar.DAY_OF_MONTH,1);
            FI.set(Calendar.HOUR_OF_DAY,0);
            FI.set(Calendar.MINUTE,0);
            FI.set(Calendar.SECOND,0);
            FI.set(Calendar.MILLISECOND,0);            
        	
            Reporte = 2;
        }
        else if (Accion == 4)
        {
            FI.set(Calendar.YEAR,2007);            
        	FI.set(Calendar.MONTH,0);
        	FI.set(Calendar.DAY_OF_MONTH,1);
            FI.set(Calendar.HOUR_OF_DAY,0);
            FI.set(Calendar.MINUTE,0);
            FI.set(Calendar.SECOND,0);
            FI.set(Calendar.MILLISECOND,0);            

            Reporte = 2;
        }
        else if (Accion == -1)
        {
            FI.setTimeInMillis(FA.getTimeInMillis());;
            FF.setTimeInMillis(FA.getTimeInMillis());;
            Reporte = 1;
        }

		try {
			java.sql.Date FI_ = new java.sql.Date(FI.getTimeInMillis());
			java.sql.Date FF_ = new java.sql.Date(FF.getTimeInMillis());
			c = ConnectionRuna.getConnection();
			s = c.prepareCall("{ call dbo.QSlotRating(?,?,?,?,?,?) }");
			s.setInt("op", Accion);
			s.setInt("Reporte", Reporte);			
			s.setByte("LocationID", LocationID);
			s.setInt("CustomerID", CustomerID);			
			s.setDate("Fecha", FI_);
			s.setDate("FFecha", FF_);
			
			ResultSet rs = s.executeQuery();

			while (rs.next()) {
				if (Accion == 1){
					list.add(new Rating(
						rs.getString("CCasino"),
						OnlyDateToStr(rs.getDate("IFecha")),
					    "",
					    0,
					    0,
					    "",
					    0,
					    "",
					    "",
					    rs.getDouble("MeterIn"),
					    rs.getDouble("MeterOut"),
					    rs.getDouble("MeterDrop"),
					    rs.getInt("MeterGames"),
					    rs.getDouble("MeterJackpot"),
					    rs.getDouble("MeterHandPay"),
					    rs.getDouble("MeterVoucherOut"),
					    rs.getDouble("MeterVoucherIn"),    
					    rs.getDouble("MeterBill"),
					    rs.getDouble("MeterEftAft"),
					    rs.getDouble("MeterWinLose"),
					    TimeToString(rs.getDate("Tiempo")),
					    rs.getInt("Points"),
					    rs.getInt("CourtesyPoints")
						));
				}
				else if (Accion == 2){
					list.add(new Rating(
						rs.getString("CCasino"),
						"",
					    "",
					    rs.getInt("FechaY"),
					    0,
					    rs.getString("FechaMTx"),
					    0,
					    "",
					    "",
					    rs.getDouble("MeterIn"),
					    rs.getDouble("MeterOut"),
					    rs.getDouble("MeterDrop"),
					    rs.getInt("MeterGames"),
					    rs.getDouble("MeterJackpot"),
					    rs.getDouble("MeterHandPay"),
					    rs.getDouble("MeterVoucherOut"),
					    rs.getDouble("MeterVoucherIn"),    
					    rs.getDouble("MeterBill"),
					    rs.getDouble("MeterEftAft"),
					    rs.getDouble("MeterWinLose"),
					    TimeToString(rs.getDate("Tiempo")),
					    rs.getInt("Points"),
					    rs.getInt("CourtesyPoints")
						));
				}
				else if (Accion == 3){
					list.add(new Rating(
						rs.getString("CCasino"),
						"",
					    "",
					    rs.getInt("FechaY"),
					    0,
					    "",
					    0,
					    "",
					    "",
					    rs.getDouble("MeterIn"),
					    rs.getDouble("MeterOut"),
					    rs.getDouble("MeterDrop"),
					    rs.getInt("MeterGames"),
					    rs.getDouble("MeterJackpot"),
					    rs.getDouble("MeterHandPay"),
					    rs.getDouble("MeterVoucherOut"),
					    rs.getDouble("MeterVoucherIn"),    
					    rs.getDouble("MeterBill"),
					    rs.getDouble("MeterEftAft"),
					    rs.getDouble("MeterWinLose"),
					    TimeToString(rs.getDate("Tiempo")),
					    rs.getInt("Points"),
					    rs.getInt("CourtesyPoints")
						));
				}
				else if (Accion == 4){
					list.add(new Rating(
						rs.getString("CCasino"),
						"",
					    "",
					    0,
					    0,
					    "",
					    rs.getInt("Machine"),
					    rs.getString("NameGame"),
					    rs.getString("NFabricante"),
					    rs.getDouble("MeterIn"),
					    rs.getDouble("MeterOut"),
					    rs.getDouble("MeterDrop"),
					    rs.getInt("MeterGames"),
					    rs.getDouble("MeterJackpot"),
					    rs.getDouble("MeterHandPay"),
					    rs.getDouble("MeterVoucherOut"),
					    rs.getDouble("MeterVoucherIn"),    
					    rs.getDouble("MeterBill"),
					    rs.getDouble("MeterEftAft"),
					    rs.getDouble("MeterWinLose"),
					    TimeToString(rs.getDate("Tiempo")),
					    rs.getInt("Points"),
					    rs.getInt("CourtesyPoints")
						));
				}
				else if (Accion == -1){
					list.add(new Rating(
						rs.getString("CCasino"),
						DateTimeToStr(rs.getDate("FechaI"), rs.getTime("FechaI")),
						DateTimeToStr(rs.getDate("FechaF"), rs.getTime("FechaF")),
					    0,
					    0,
					    "",
					    rs.getInt("NumMaquina"),
					    "",
					    "",
					    rs.getDouble("MeterIn"),
					    rs.getDouble("MeterOut"),
					    rs.getDouble("MeterDrop"),
					    rs.getInt("MeterGames"),
					    rs.getDouble("MeterJackpot"),
					    rs.getDouble("MeterHandPay"),
					    rs.getDouble("MeterVoucherOut"),
					    rs.getDouble("MeterVoucherIn"),    
					    rs.getDouble("MeterBill"),
					    rs.getDouble("MeterEftAft"),
					    rs.getDouble("MeterWinLose"),
					    TimeToString(rs.getDate("Tiempo")),
					    rs.getInt("Points"),
					    rs.getInt("CourtesyPoints")
						));
				}
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e);
		} finally {
			ConnectionRuna.close(c);
		}
		
		return list;
	}

}
