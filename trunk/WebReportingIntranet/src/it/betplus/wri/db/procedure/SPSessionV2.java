package it.betplus.wri.db.procedure;

import it.betplus.wri.db.connection.ConnectionItalyV2;
import it.betplus.wri.db.dto.Session;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class SPSessionV2 implements Serializable {

	/**
	 * Get login user
	 */
	private static final long serialVersionUID = -3410400051694950497L;

	public List<Session> getSPSession(String NLogin, String NPassword, int LocationID_Compiler, int CodCasino_Compiler) throws Exception {

		List<Session> list = new ArrayList<Session>();
		Connection c = null;
		CallableStatement s = null;
		Calendar Tiempo = Calendar.getInstance();
		Calendar Fecha = Calendar.getInstance();		
		//java.sql.Date F = new java.sql.Date(Fecha);

		try {
			c = ConnectionItalyV2.getConnection();
			s = c.prepareCall("{ call Web.AutentificacionV2(?,?,?,?) }");
			s.setString("NLogin", NLogin);
			s.setString("NPassword", NPassword);
			s.setInt("LocationID_Compiler", LocationID_Compiler);
			s.setInt("CodCasino_Compiler", CodCasino_Compiler);			
			ResultSet rs = s.executeQuery();

			while (rs.next()) {
				Tiempo.setTimeInMillis(rs.getTime("FechaHora").getTime());
				Fecha.setTimeInMillis(rs.getDate("FechaHora").getTime());				
				list.add(new Session(
						rs.getInt("EmployeeID"),
						rs.getString("EmployeeName"),
					    rs.getDate("FechaHora"),
					    rs.getInt("lyear"),
					    rs.getInt("lmonth"),
					    rs.getInt("lday"),
					    /*Fecha.get(Calendar.YEAR),
					    Fecha.get(Calendar.MONTH)+1,					    
					    Fecha.get(Calendar.DAY_OF_MONTH),*/
					    Tiempo.get(Calendar.HOUR_OF_DAY),
					    Tiempo.get(Calendar.MINUTE)
						));
				System.out.println(NLogin);
				System.out.println(rs.getDate("FechaHora"));
				System.out.println(rs.getInt("lyear"));
				//System.out.print(Fecha.get(Calendar.YEAR));
				System.out.print(".");
				System.out.println(rs.getInt("lmonth"));
				//System.out.print(Fecha.get(Calendar.MONTH)+1);
				System.out.print(".");				
				System.out.println(rs.getInt("lday"));
				//System.out.print(Fecha.get(Calendar.DAY_OF_MONTH));
				System.out.print(".");				
				System.out.print(Tiempo.get(Calendar.HOUR_OF_DAY));
				System.out.print(".");				
				System.out.println(Tiempo.get(Calendar.MINUTE));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			ConnectionItalyV2.close(c);
		}
		return list;
	}

}
