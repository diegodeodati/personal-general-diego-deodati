package serviseatl_gis.traffic;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.io.Serializable;
import java.sql.*;

import serviseatl.connection.ConnectionMarka;
import serviseatl.connection.ConnectionAtlTr;
import serviseatl.connection.ConnectionParTr;
import serviseatl.connection.ConnectionSGTr;
import serviseatl.connection.ConnectionSDTr;
import serviseatl.connection.ConnectionBplTr;
import serviseatl.connection.ConnectionParSDTr;
import serviseatl.connection.DAOException;


public class SPTraffic implements Serializable {
		    static final long serialVersionUID = 103824574001900300L;
			public List<Traffic> getSPTraffic(byte Accion, byte LocationID, int CodCasino) throws DAOException {
				List<Traffic> list = new ArrayList<Traffic>();
				Connection c = null;
				CallableStatement s = null;
				Calendar DT = Calendar.getInstance();
				Calendar FA = Calendar.getInstance();
				Calendar HA = Calendar.getInstance();				
		    	int Y = 0,M = 0,D = 0,H = 0, Mi = 0;		
		    	int NPar = 0;
				try {
					if (LocationID == 0 && CodCasino == 3) {
						c = ConnectionAtlTr.getConnection();
					}
					else if (LocationID ==0 && CodCasino == 6) {
						c = ConnectionParTr.getConnection();				
					}
					else if (LocationID ==0 && CodCasino == 1) {
						c = ConnectionSGTr.getConnection();				
					}
					else if (LocationID ==0 && CodCasino == 7) {
						c = ConnectionSDTr.getConnection();				
					}
					else if (LocationID ==0 && CodCasino == 2) {
						c = ConnectionParSDTr.getConnection();				
					}
					else if (LocationID ==0 && CodCasino == 5) {
						c = ConnectionBplTr.getConnection();				
					}

					s = c.prepareCall("{ call dbo.QLTrafficCustomers(?,?,?) }");
					s.setByte("op", Accion);
					s.setInt("CodCasino", 0);
					s.setDate("Fecha", null);
					
					ResultSet rs = s.executeQuery();

					while (rs.next()) {
						DT.setTimeInMillis(rs.getTime("FechaHora").getTime());
						Mi = DT.get(Calendar.MINUTE);
						if (Mi % 2 == 0) {
							H = DT.get(Calendar.HOUR_OF_DAY);
						
							DT.setTimeInMillis(rs.getDate("FechaHora").getTime());
							Y = DT.get(Calendar.YEAR);
							M = DT.get(Calendar.MONTH)+1;
							D = DT.get(Calendar.DAY_OF_MONTH);
						
							list.add(new Traffic(
								Y,
								M,
								D,
							    H,
							    Mi,
							    rs.getInt("ConTarjeta"),							    
							    rs.getInt("SinTarjeta")
								));
							NPar = NPar + 1;
						}
					}
					
				} catch (SQLException e) {
					e.printStackTrace();
					throw new DAOException(e);
				} finally {
					if (LocationID == 0 && CodCasino == 3) {
						ConnectionAtlTr.close(c);
					}
					else if (LocationID ==0 && CodCasino == 6) {
						ConnectionParTr.close(c);
					}
					else if (LocationID ==0 && CodCasino == 1) {
						ConnectionSGTr.close(c);
					}
					else if (LocationID ==0 && CodCasino == 7) {
						ConnectionSDTr.close(c);
					}
					else if (LocationID ==0 && CodCasino == 5) {
						ConnectionBplTr.close(c);
					}
					
				}
				/**************************************/
				
				Connection c2 = null;
				CallableStatement s2 = null;
				try {
					c2 = ConnectionMarka.getConnection();
					s2 = c2.prepareCall(" SELECT GETDATE() AS FH ");

					ResultSet rs2 = s2.executeQuery();

					while (rs2.next()) {
						FA.setTimeInMillis(rs2.getDate("FH").getTime());
						HA.setTimeInMillis(rs2.getTime("FH").getTime());						
					}
					DT.set(Calendar.YEAR, FA.get(Calendar.YEAR));
					DT.set(Calendar.MONTH, FA.get(Calendar.MONTH));
					DT.set(Calendar.DAY_OF_MONTH, FA.get(Calendar.DAY_OF_MONTH));
					DT.set(Calendar.HOUR_OF_DAY, HA.get(Calendar.HOUR_OF_DAY));
					DT.set(Calendar.MINUTE, HA.get(Calendar.MINUTE));					
					
			        if (DT.get(Calendar.MINUTE) % 2 == 1)
			        {
			        	DT.add(Calendar.MINUTE, -1);
			        }
			        if (NPar == 0)
			        {
						list.add(new Traffic(
								DT.get(Calendar.YEAR),
								DT.get(Calendar.MONTH)+1,
								DT.get(Calendar.DAY_OF_MONTH),
								DT.get(Calendar.HOUR_OF_DAY),
								DT.get(Calendar.MINUTE),
							    -1,	    
							    -1
								));
			            NPar = NPar + 1;
			        }
			        else 
			        if (!(list.get(NPar-1).getTiempoY() == DT.get(Calendar.YEAR) && list.get(NPar-1).getTiempoM() == DT.get(Calendar.MONTH) && list.get(NPar-1).getTiempoD() == DT.get(Calendar.DAY_OF_MONTH)
			            && list.get(NPar-1).getTiempoH() == DT.get(Calendar.HOUR_OF_DAY) && list.get(NPar-1).getTiempoMi() == DT.get(Calendar.MINUTE)))
			        {

						list.add(new Traffic(
								DT.get(Calendar.YEAR),
								DT.get(Calendar.MONTH)+1,
								DT.get(Calendar.DAY_OF_MONTH),
								DT.get(Calendar.HOUR_OF_DAY),
								DT.get(Calendar.MINUTE),
							    -1,	    
							    -1
								));
			            NPar = NPar + 1;
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

