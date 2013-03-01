package it.bplus.business;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import it.bplus.bean.UserBean;
import it.bplus.dao.UtentiDAO;
import it.bplus.exception.BusinessLayerException;
import it.bplus.exception.DataAccessException;
import it.bplus.model.Utenti;

import it.bplus.util.IErrorCodes;

import org.apache.log4j.Logger;

public class UtentiBusinessDelegate {

	protected static Logger logger = Logger.getLogger(UtentiBusinessDelegate.class);

	private static UtentiBusinessDelegate instance;

	public static synchronized UtentiBusinessDelegate getInstance() {
		if (instance == null)
		{
			synchronized(UtentiBusinessDelegate.class) {      //1
				UtentiBusinessDelegate inst = instance;         //2
				if (inst == null)
				{
					synchronized(UtentiBusinessDelegate.class) {  //3
						instance = new UtentiBusinessDelegate();
					}
					//instance = inst;               //5
				}
			}
		}
		return instance;
	}

	public Utenti getUtenteByUsernamePassword(String username, String password) throws BusinessLayerException{
		try {
			return UtentiDAO.getInstance().getUtenteByUsernamePassword(username,password);
		} catch (DataAccessException e) {
			throw new BusinessLayerException(IErrorCodes.DB_ERROR_KEY,e);
		}
	}
	
	
	public Utenti getUtenteByUsername(String username) throws BusinessLayerException{
		try {
			return UtentiDAO.getInstance().loadCurrentUserDetail(username);
		} catch (DataAccessException e) {
			throw new BusinessLayerException(IErrorCodes.DB_ERROR_KEY,e);
		}
	}
	
	
	public List<Utenti> getLista(String sortColumn,boolean isAscending) throws BusinessLayerException{
		try {
			return UtentiDAO.getInstance().getList(sortColumn,isAscending);
		} catch (DataAccessException e) {
			throw new BusinessLayerException(IErrorCodes.DB_ERROR_KEY,e);
		}
	}

	public List<UserBean> trasformaFromMeterfactToMeterBean(Collection<Utenti> listaUtenti) {
		List<UserBean> lista = new ArrayList<UserBean>();
		for (Utenti c : listaUtenti) {

			String listaRuoli="";
			String unRuolo="";
			Iterator<String> l=c.getRuoli().iterator();
			while(l.hasNext()){
				String ruolo=(String)l.next();
				if(unRuolo!=null && unRuolo.length()==0)
					unRuolo=ruolo;
				listaRuoli+=" "+ruolo;
			}
			UserBean p = new UserBean(c.getUsername(),c.getUserPass(),c.getNome(),c.getCognome(),c.getRuoli().iterator().next());
			p.setListaRuoli(listaRuoli);
			lista.add(p);
		}
		return lista;
	}
	
	
	public int inserisciNuovoUtente(Utenti ut) throws BusinessLayerException{
		try {
			return UtentiDAO.getInstance().inserisciNuovoUtente(ut);
		} catch (DataAccessException e) {
			throw new BusinessLayerException(IErrorCodes.DB_ERROR_KEY,e);
		}
	}
	
	public boolean verificaUsername(String username) throws BusinessLayerException{
		try {
			return UtentiDAO.getInstance().verificaUsername(username);
		} catch (DataAccessException e) {
			throw new BusinessLayerException(IErrorCodes.DB_ERROR_KEY,e);
		}
	}
	
	public int modificaUtente(Utenti ut, String username) throws BusinessLayerException{
		try {
			return UtentiDAO.getInstance().modificaUtente(ut, username);
		} catch (DataAccessException e) {
			throw new BusinessLayerException(IErrorCodes.DB_ERROR_KEY,e);
		}
	}
	public int eliminaUtente(String username) throws BusinessLayerException{
		try {
			return UtentiDAO.getInstance().eliminaUtente(username);
		} catch (DataAccessException e) {
			throw new BusinessLayerException(IErrorCodes.DB_ERROR_KEY,e);
		}
	}
}
