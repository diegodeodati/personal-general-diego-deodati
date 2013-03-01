package it.bplus.dao;

//import org.hibernate.SessionFactory;
import it.bplus.db.DBConnectionManager;
import it.bplus.exception.DataAccessException;
import it.bplus.model.Clientidim;
import it.bplus.util.IErrorCodes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class ClientiDAO {

	protected static Logger logger = Logger.getLogger(ClientiDAO.class);
//	public void setSessionFactory(SessionFactory sf){
//	}


	private static ClientiDAO instance;

	private ClientiDAO() {
		super();
	}

//	public ClientiDAO(SessionFactory sf){
//		super();
//	}

	public static synchronized ClientiDAO getInstance() {
		if (instance == null)
		{
			synchronized(ClientiDAO.class) {      //1
				ClientiDAO inst = instance;         //2
				if (inst == null)
				{
					synchronized(ClientiDAO.class) {  //3
						instance = new ClientiDAO();
					}
					//instance = inst;               //5
				}
			}
		}
		return instance;
	}

	

	public int fixClienti() throws DataAccessException{
		//logger.info("FixLocation");
		List<String> locs = null;
		ResultSet rs=null;
		PreparedStatement ps=null;
		Connection connDataMart=null;
		Connection connStaging=null;
		int risultato=0;
		
		try {
			connDataMart=DBConnectionManager.DataMartConnectionFactory();
			connStaging=DBConnectionManager.StagingConnectionFactory();
			
			String sql_1 = "select distinct aams_location_id from clientidim";
			ps = connDataMart.prepareStatement(sql_1);
			rs = ps.executeQuery();
			
			locs = new ArrayList<String>();
			while (rs.next()){
				locs.add(rs.getString("AAMS_LOCATION_ID"));
			}
			
			DBConnectionManager.CloseStatement(rs, ps);
			
			for (String s : locs){
				String sql_2 = "select LOC.AAMS_LOCATION_ID aams_location_id, LOC.COMMERCIAL_NAME LOCATION_NAME, es.cod_esercente, es.piva piva_esercente, es.person_cf person_cf_esercente, " +
						"es.company_cf company_cf_esercente, es.person_name nome_esercente," +
						"es.person_surname cognome_esercente, ges.cod_gestore cod_gestore, ges.denominazione nome_gestore, ges.email email_gestore, " +
						"ges.cf cf_gestore, ges.piva piva_gestore " +
						"from BIRSLOCATION LOC " +
						"left JOIN birscontratto con ON CON.ID_AAMS_LOCATION = LOC.AAMS_LOCATION_ID " +
						"left join birsesercente es on con.cod_esercente = es.cod_esercente " +
						"left join birsgestore ges on con.cod_gestore = ges.cod_gestore " +
						"where LOC.AAMS_LOCATION_ID='"+s+"'";
				
				ps = connStaging.prepareStatement(sql_2);
				rs = ps.executeQuery();
				
				Clientidim cd = null;
				while (rs.next()){
					cd = new Clientidim();
					cd.setAamsLocationId(rs.getString("AAMS_LOCATION_ID"));
					cd.setLocationName(rs.getString("LOCATION_NAME"));
					cd.setCodEsercente(rs.getLong("cod_esercente"));
					if(rs.getString("piva_esercente")!=null)
						cd.setCfPivaEsercente(rs.getString("piva_esercente"));
					else cd.setCfPivaEsercente(rs.getString("person_cf_esercente"));
					cd.setNomeEsercente(rs.getString("cognome_esercente")+" "+rs.getString("nome_esercente"));
					cd.setCodGestore(rs.getString("cod_gestore"));
					cd.setNomeGestore(rs.getString("nome_gestore"));
					cd.setCfPivaGestore(rs.getString("piva_gestore"));
					cd.setEmail_pec_gs(rs.getString("email_gestore"));
				}
				
				DBConnectionManager.CloseStatement(rs, ps);
				
				if (cd!=null){
				String sql_3 = "update clientidim set " +
						" cod_esercente = ?, cf_piva_esercente = ?,cod_gestore = ?,cf_piva_gestore = ?," +
						"nome_gestore = ?,email_pec_gestore = ?,nome_esercente = ? , LOCATION_NAME = ? " +
						"where aams_location_id = ?";
				
				ps = connDataMart.prepareStatement(sql_3);
				ps.setLong(1, cd.getCodEsercente());
				ps.setString(2, cd.getCfPivaEsercente());
				ps.setString(3, cd.getCodGestore());
				ps.setString(4, cd.getCfPivaGestore());
				ps.setString(5, cd.getNomeGestore());
				ps.setString(6, cd.getEmail_pec_gs());
				ps.setString(7, cd.getNomeEsercente());
				ps.setString(8, cd.getLocationName());
				ps.setString(9, cd.getAamsLocationId());
			
				
				risultato = risultato + ps.executeUpdate();
				}
				DBConnectionManager.CloseStatement(ps);
			}
					
			connDataMart.commit();
			
		} catch (SQLException e) {
			logger.error("fixClienti failed", e);
			e.printStackTrace();
			throw new DataAccessException(IErrorCodes.DB_ERROR_KEY, e.toString(), e);
		}
		catch (Exception e) {
			logger.error("fixClienti failed", e);
			e.printStackTrace();
			throw new DataAccessException(IErrorCodes.DB_ERROR_KEY, e.toString(), e);
		}
		finally{
				try {
					DBConnectionManager.CloseConnection(rs, ps, connDataMart);	
					DBConnectionManager.CloseConnection(connStaging);
				} catch (DataAccessException e) {
					e.printStackTrace();
					logger.error(e);
				}
		}
		return risultato;	
	}

}