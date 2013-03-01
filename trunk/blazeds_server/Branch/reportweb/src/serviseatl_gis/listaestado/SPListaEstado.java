package serviseatl_gis.listaestado;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import java.sql.*;

import serviseatl.connection.ConnectionMarka;
import serviseatl.connection.ConnectionPoolerAtl;
import serviseatl.connection.ConnectionPoolerPar;
import serviseatl.connection.ConnectionPoolerSG;
import serviseatl.connection.ConnectionPoolerSD;
import serviseatl.connection.ConnectionPoolerBpl;
import serviseatl.connection.ConnectionPoolerParSD;
import serviseatl.connection.DAOException;

public class SPListaEstado implements Serializable {
    static final long serialVersionUID = 101845516007666300L;
	public List<ListaEstado> getSPListaEstado(byte Accion, byte LocationID, int CodCasino, int EmployeeID, String LoginID) throws DAOException {

		List<ListaEstado> list = new ArrayList<ListaEstado>();
		
		Connection c = null;
		Connection c3 = null;		
		CallableStatement s = null;
		CallableStatement s2 = null;		
		CallableStatement s3 = null;
		
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

			c3 = ConnectionMarka.getConnection();
			s = c.prepareCall("{ call dbo.QEmployeeOnLine(?,?,?) }");
			s.setByte("Op", (byte)2);
			s.setInt("EmployeeID", EmployeeID);
			s.setString("LoginID", LoginID);
			//s.registerOutParameter("LoginID", Types.VARCHAR);
			s.execute();

			s2 = c.prepareCall("{ call dbo.QListaEstado(?,?,?) }");
			s2.setByte("Op", Accion);
			s2.setInt("EmployeeID", EmployeeID);
			s2.setString("LoginID", LoginID);
			ResultSet rs = s2.executeQuery();
			
			s3 = c3.prepareCall("{ call GIS.DatosMaquinaCliente(?,?) }");
			
			while (rs.next()) {
				s3.setString("CodMaquina", rs.getString("CodMaquina"));
				s3.setInt("CustomerID", rs.getInt("CustomerID"));
				ResultSet rs3 = s3.executeQuery();


				while (rs3.next()) {
					if (rs3.getBoolean("EstadoMaq") == true){
						list.add(new ListaEstado(
									rs3.getString("CodMaquina"),
									rs3.getDouble("DenominacionM"),
									rs3.getInt("NumMaquina"),
									rs3.getString("NameGame"),
									rs3.getString("NFabricante"),
									rs3.getString("TipoPago"),
									rs3.getDouble("X"),
									rs3.getDouble("Y"),
									rs3.getDouble("Alfa"),
									rs3.getDouble("XC"),
									rs3.getDouble("YC"),
									rs.getBoolean("EnLinea"),
									rs.getInt("Cliente"),
									rs3.getInt("CustomerID"),						
									rs3.getString("Nombre"),
									rs3.getInt("CodFabricante")
								));
					
					}
					
				}
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
			ConnectionMarka.close(c3);
		}

		return list;
	}
}
