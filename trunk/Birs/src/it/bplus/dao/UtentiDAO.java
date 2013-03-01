package it.bplus.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

import it.bplus.db.DBConnectionManager;
import it.bplus.exception.DataAccessException;
import it.bplus.model.Utenti;
import it.bplus.util.IErrorCodes;

public class UtentiDAO  {

	protected static Logger logger = Logger.getLogger(UtentiDAO.class);

	private static UtentiDAO instance;

	private UtentiDAO() {
		super();
	}


	public static synchronized UtentiDAO getInstance() {
		if (instance == null)
		{
			synchronized(UtentiDAO.class) {      //1
				UtentiDAO inst = instance;         //2
				if (inst == null)
				{
					synchronized(UtentiDAO.class) {  //3
						instance = new UtentiDAO();
					}
					//instance = inst;               //5
				}
			}
		}
		return instance;
	}


	public Utenti getUtenteByUsernamePassword(String username, String password) throws DataAccessException{
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection connDataMart = null;
		Utenti user=null;
		List<String> ruoli = null;

		try{
			connDataMart = DBConnectionManager.DataMartConnectionFactory();
			
			String sql = "select * from utenti where username=? and user_pass=?";
			
			ps = connDataMart.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			rs = ps.executeQuery();

			while (rs.next()){
				user = new Utenti();
				user.setUsername(rs.getString("USERNAME"));
				user.setUserPass(rs.getString("USER_PASS"));
				user.setNome(rs.getString("NOME"));
				user.setCognome(rs.getString("COGNOME"));
			}
			
			ruoli = findRuoliByUsername(username, connDataMart);
			user.setRuoli(ruoli);
			
			logger.info("user logged: "+user.getUsername());
			
		} catch (SQLException e) {
			logger.error("getUtenteByUsernamePassword: SQL failed", e);
			e.printStackTrace();
			throw new DataAccessException(IErrorCodes.DB_ERROR_KEY, e.toString(), e);
		}catch(RuntimeException e){
			throw new DataAccessException(IErrorCodes.DB_ERROR_KEY,e.toString());
		} finally {
			DBConnectionManager.CloseConnection(rs, ps, connDataMart);				
		}
		return user;

	}

	
	public List<String> findRuoliByUsername(String username, Connection connDataMart)throws DataAccessException{
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<String> ruoli = null;
		
		try{			
			String sql_1 = "select ruolo from ruoli where username=?";

			ps = connDataMart.prepareStatement(sql_1);
			ps.setString(1, username);
			rs = ps.executeQuery();

			ruoli = new ArrayList<String>();
			while (rs.next()) {
				ruoli.add(rs.getString("RUOLO"));
			}

			
		} catch (SQLException e) {
			logger.error("findRuoliByUsername: SQL failed", e);
			e.printStackTrace();
			throw new DataAccessException(IErrorCodes.DB_ERROR_KEY, e.toString(), e);
		}catch(RuntimeException e){
			throw new DataAccessException(IErrorCodes.DB_ERROR_KEY,e.toString());
		} finally {
			DBConnectionManager.CloseStatement(rs, ps);				
		}
		
		return ruoli;
	}
	
	
	
	public Utenti loadCurrentUserDetail(String current_user) throws DataAccessException{
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection connDataMart = null;
		Utenti user=null;
		List<String> ruoli = null;
		
		try{
			connDataMart = DBConnectionManager.DataMartConnectionFactory();
			
			String sql = "select * from utenti where username=?";
			
			ps = connDataMart.prepareStatement(sql);
			ps.setString(1, current_user);
			rs = ps.executeQuery();

			while (rs.next()) {
				user = new Utenti();
				user.setUsername(rs.getString("USERNAME"));
				user.setUserPass(rs.getString("USER_PASS"));
				user.setNome(rs.getString("NOME"));
				user.setCognome(rs.getString("COGNOME"));
			}

			ruoli = findRuoliByUsername(current_user, connDataMart);
			user.setRuoli(ruoli);

			
		} catch (SQLException e) {
			logger.error("loadCurrentUserDetail: SQL failed", e);
			e.printStackTrace();
			throw new DataAccessException(IErrorCodes.DB_ERROR_KEY, e.toString(), e);
		}catch(RuntimeException e){
			throw new DataAccessException(IErrorCodes.DB_ERROR_KEY,e.toString());
		} finally {
			DBConnectionManager.CloseConnection(rs, ps, connDataMart);				
		}		
		return user;
	}
	
	
	public List<Utenti> getList(String sortColumn,boolean asc)throws DataAccessException{
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection connDataMart = null;
		List<Utenti> lista=null;
		
		try{
			connDataMart = DBConnectionManager.DataMartConnectionFactory();
			
			String sql = "select username, nome, cognome from utenti";
			
			ps = connDataMart.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			lista = new ArrayList<Utenti>();
			
			while (rs.next()){
				Utenti user = new Utenti();
				user.setNome(rs.getString("NOME"));
				user.setCognome(rs.getString("COGNOME"));
				user.setUsername(rs.getString("USERNAME"));				
				List<String> ruoli = findRuoliByUsername(user.getUsername(), connDataMart);				
				user.setRuoli(ruoli);
				
				lista.add(user);
			}
			
			
			if(lista!=null && lista.size()>0){
				logger.debug("successful, " + lista.size() + " instances found");

			}else{
				logger.debug(" successful, no instance found");
				return null;
			}
		} catch (SQLException e) {
			logger.error("getList: SQL failed", e);
			e.printStackTrace();
			throw new DataAccessException(IErrorCodes.DB_ERROR_KEY, e.toString(), e);
		}catch(RuntimeException e){			
			throw new DataAccessException(IErrorCodes.DB_ERROR_KEY,e.toString());
		} finally {
			DBConnectionManager.CloseConnection(rs, ps, connDataMart);				
		}
		return lista;
	}
	
	
	
	public int inserisciNuovoUtente(Utenti ut) throws DataAccessException{
		PreparedStatement ps = null;
		Connection connDataMart = null;
		int risultato = 0;
		
		try{
			connDataMart = DBConnectionManager.DataMartConnectionFactory();
			
			String sql = "insert into utenti(username, user_pass, nome, cognome) values(?,?,?,?)";
			
			ps = connDataMart.prepareStatement(sql);
			ps.setString(1, ut.getUsername());
			ps.setString(2, ut.getUserPass());
			ps.setString(3, ut.getNome());
			ps.setString(4, ut.getCognome());
			
			risultato = ps.executeUpdate();
			
			
			if (ut.getRuoli()!=null && !(ut.getRuoli().isEmpty())){
				for (String ruolo : ut.getRuoli()){
					if (risultato==1){
						String sql_2 = "insert into ruoli(username, ruolo) values(?,?)";
						ps = connDataMart.prepareStatement(sql_2);
						ps.setString(1, ut.getUsername());
						ps.setString(2, ruolo);
						risultato = ps.executeUpdate();
					}
				}
			}
			
			if (risultato==1)
				connDataMart.commit();
			else connDataMart.rollback();
			
		} catch (SQLException e) {
			logger.error("inserisciNuovoUtente: SQL failed", e);
			e.printStackTrace();
			throw new DataAccessException(IErrorCodes.DB_ERROR_KEY, e.toString(), e);
		}catch(RuntimeException e){
			throw new DataAccessException(IErrorCodes.DB_ERROR_KEY,e.toString());
		} finally {
			DBConnectionManager.CloseConnection(ps, connDataMart);				
		}		
		return risultato;
	}
	
	
	public boolean verificaUsername(String username) throws DataAccessException{
		PreparedStatement ps = null;
		Connection connDataMart = null;
		ResultSet rs = null;
		boolean esiste = false;
		
		try{
			connDataMart = DBConnectionManager.DataMartConnectionFactory();
			
			String sql = "select username from utenti where username = ?";
			
			ps = connDataMart.prepareStatement(sql);
			ps.setString(1, username);
			
			rs = ps.executeQuery();
			
			if (rs.next())
				esiste = true;
			
		} catch (SQLException e) {
			logger.error("verificaUsername: SQL failed", e);
			e.printStackTrace();
			throw new DataAccessException(IErrorCodes.DB_ERROR_KEY, e.toString(), e);
		}catch(RuntimeException e){
			throw new DataAccessException(IErrorCodes.DB_ERROR_KEY,e.toString());
		} finally {
			DBConnectionManager.CloseConnection(ps, connDataMart);				
		}		
		return esiste;
	}
	
	public int modificaUtente(Utenti ut, String username) throws DataAccessException{
		PreparedStatement ps = null;
		Connection connDataMart = null;
		int risultato = 0;
		
		try{
			connDataMart = DBConnectionManager.DataMartConnectionFactory();
			
			String sql_1 = "delete from ruoli where username=?";
			ps = connDataMart.prepareStatement(sql_1);
			ps.setString(1, username);
			risultato = ps.executeUpdate();
			
			
			String sql = "update utenti set username=?, user_pass=?, nome=?, cognome=? where username=?";

			ps = connDataMart.prepareStatement(sql);
			ps.setString(1, ut.getUsername());
			ps.setString(2, ut.getUserPass());
			ps.setString(3, ut.getNome());
			ps.setString(4, ut.getCognome());
			ps.setString(5, username);

			risultato = ps.executeUpdate();

			if (ut.getRuoli() != null && !(ut.getRuoli().isEmpty())) {
				for (String ruolo : ut.getRuoli()) {
					if (risultato > 0) {
						String sql_2 = "insert into ruoli(username, ruolo) values(?,?)";
						ps = connDataMart.prepareStatement(sql_2);
						ps.setString(1, ut.getUsername());
						ps.setString(2, ruolo);
						risultato = ps.executeUpdate();
					}
				}
			}
		
			if (risultato>0)
				connDataMart.commit();
			else connDataMart.rollback();
			
		} catch (SQLException e) {
			logger.error("modificaUtente: SQL failed", e);
			e.printStackTrace();
			throw new DataAccessException(IErrorCodes.DB_ERROR_KEY, e.toString(), e);
		}catch(RuntimeException e){
			throw new DataAccessException(IErrorCodes.DB_ERROR_KEY,e.toString());
		} finally {
			DBConnectionManager.CloseConnection(ps, connDataMart);				
		}		
		return risultato;
	}
	
	
	
	public int eliminaUtente(String username) throws DataAccessException{
		PreparedStatement ps = null;
		Connection connDataMart = null;
		int risultato = 0;
		
		try{
			connDataMart = DBConnectionManager.DataMartConnectionFactory();
			
			String sql_1 = "delete from ruoli where username=?";
			ps = connDataMart.prepareStatement(sql_1);
			ps.setString(1, username);
			risultato = ps.executeUpdate();
						
			String sql = "delete from utenti where username=?";

			ps = connDataMart.prepareStatement(sql);
			ps.setString(1, username);
			risultato = ps.executeUpdate();

			if (risultato>0)
				connDataMart.commit();
			else connDataMart.rollback();
			
		} catch (SQLException e) {
			logger.error("eliminaUtente: SQL failed", e);
			e.printStackTrace();
			throw new DataAccessException(IErrorCodes.DB_ERROR_KEY, e.toString(), e);
		}catch(RuntimeException e){
			throw new DataAccessException(IErrorCodes.DB_ERROR_KEY,e.toString());
		} finally {
			DBConnectionManager.CloseConnection(ps, connDataMart);				
		}		
		return risultato;
	}
}