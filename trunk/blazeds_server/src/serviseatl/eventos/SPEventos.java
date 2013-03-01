package serviseatl.eventos;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import serviseatl.connection.ConnectionMarka;
import serviseatl.connection.ConnectionPoolerAtl;
import serviseatl.connection.ConnectionPoolerBpl;
import serviseatl.connection.ConnectionPoolerPar;
import serviseatl.connection.ConnectionPoolerParSD;
import serviseatl.connection.ConnectionPoolerSD;
import serviseatl.connection.ConnectionPoolerSG;
import serviseatl.connection.DAOException;

public class SPEventos implements Serializable {
    static final long serialVersionUID = 103844514001000202L;
	public List<Eventos> getSPEventos (byte LocationID, int CodCasino, int Tiempo, long FHI, long FHF) throws DAOException {

		List<Eventos> list = new ArrayList<Eventos>();
		Connection c = null;
		Connection c2 = null;		
		CallableStatement s = null;

		java.sql.Date FHI_D = new java.sql.Date(FHI);
		java.sql.Date FHF_D = new java.sql.Date(FHF);		
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
			
			c2 = ConnectionMarka.getConnection();
			s = c.prepareCall("{ call web.ListaEventos(?,?) }");
			s.setInt("Tiempo", Tiempo);
			s.setDate("FHI", FHI_D);
			s.setDate("FHF", FHF_D);
			
			ResultSet rs = s.executeQuery();

			while (rs.next()) {
				list.add(new Eventos (
						rs.getString("CodMaquina"),
					    rs.getString("Fecha"),
					    rs.getString("Hora"),
					    rs.getInt("EventoId"),
					    rs.getString("Evento"),
					    rs.getInt("NumMaquina"),
					    rs.getString("NameGame")
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
			ConnectionMarka.close(c2);
		}
		
		return list;
	}

}
