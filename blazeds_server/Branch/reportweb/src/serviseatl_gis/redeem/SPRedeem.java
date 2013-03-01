package serviseatl_gis.redeem;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.io.Serializable;
import java.sql.*;

import serviseatl.connection.ConnectionMarka;
import serviseatl.connection.DAOException;

public class SPRedeem implements Serializable {
    static final long serialVersionUID = 103844514001000202L;

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
    
	public List<Redeem> getSPRedeem(byte LocationID, int CustomerID) throws DAOException {

		List<Redeem> list = new ArrayList<Redeem>();
		Connection c = null;
		CallableStatement s = null;

		try {
			c = ConnectionMarka.getConnection();
			s = c.prepareCall("{ call Customer.QLGroupRedeemPoints(?,?,?) }");
			s.setByte("Op", (byte)4);
			s.setByte("LocationID", LocationID);
			s.setInt("CustomerID", CustomerID);			
			
			
			
			ResultSet rs = s.executeQuery();

			while (rs.next()) {

				list.add(new Redeem(
						rs.getString("CCasino"),
					    rs.getInt("FYear"),
					    rs.getString("FMonth"),
					    OnlyDateToStr(rs.getDate("FF")),
					    rs.getInt("RedeemPoints"),
					    rs.getInt("Balance"),
					    rs.getString("TypeRedeem"),
					    rs.getDouble("AmountRedeem"),
					    rs.getDouble("AmountRedeemSus"),
					    rs.getString("SimboloM")
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
