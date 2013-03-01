package it.bplus.business;

import java.util.List;

import it.bplus.dao.ClientiDAO;
import it.bplus.dao.ComuniDAO;
import it.bplus.dao.ProvinceDAO;
import it.bplus.dao.RegioniDAO;
import it.bplus.dao.SpazioDAO;
import it.bplus.exception.BusinessLayerException;
import it.bplus.exception.DataAccessException;
import it.bplus.model.Comuni;
import it.bplus.model.Province;
import it.bplus.model.Regioni;
import it.bplus.model.Spaziodim;
import it.bplus.util.IErrorCodes;
import org.apache.log4j.Logger;

public class GeographicBusinessDelegate {

	protected static Logger logger = Logger.getLogger(GeographicBusinessDelegate.class);

	private static GeographicBusinessDelegate instance;

	public static synchronized GeographicBusinessDelegate getInstance() {
		if (instance == null)
		{
			synchronized(GeographicBusinessDelegate.class) {      //1
				GeographicBusinessDelegate inst = instance;         //2
				if (inst == null)
				{
					synchronized(GeographicBusinessDelegate.class) {  //3
						instance = new GeographicBusinessDelegate();
					}
					//instance = inst;               //5
				}
			}
		}
		return instance;
	}
	
	public List<Regioni> getRegioni() throws BusinessLayerException{
		try {
			return RegioniDAO.getInstance().getAll();
		} catch (DataAccessException e) {
			throw new BusinessLayerException(IErrorCodes.DB_ERROR_KEY,e);
		}	
	}
	
	public List<Province> getProvinceByIdRegione(String id_regione) throws BusinessLayerException{
		try {
			return ProvinceDAO.getInstance().getProvinceByIdRegione(id_regione);
		} catch (DataAccessException e) {
			throw new BusinessLayerException(IErrorCodes.DB_ERROR_KEY,e);
		}	
	}
	
	public List<Comuni> getComuniByIdProvincia(String id_provincia) throws BusinessLayerException{
		try {
			return ComuniDAO.getInstance().getComuniByIdProvincia(id_provincia);
		} catch (DataAccessException e) {
			throw new BusinessLayerException(IErrorCodes.DB_ERROR_KEY,e);
		}	
	}
	
	
	
	public Comuni findComuneByCodCatasto(String codiceCatasto) throws BusinessLayerException{
			
		try {
			return ComuniDAO.getInstance().findComuneByCodCatastale(codiceCatasto);
		} catch (DataAccessException e) {
			throw new BusinessLayerException(IErrorCodes.DB_ERROR_KEY,e);
		}
	}
	
	public List<Spaziodim> retrieveAllLocations() throws BusinessLayerException{
		
		try {
			return SpazioDAO.getInstance().retrieveAllLocations();
		} catch (DataAccessException e) {
			throw new BusinessLayerException(IErrorCodes.DB_ERROR_KEY,e);
		}
	}
	
	public List<Spaziodim> retrieveAllGames() throws BusinessLayerException{
		
		try {
			return SpazioDAO.getInstance().retrieveAllGames();
		} catch (DataAccessException e) {
			throw new BusinessLayerException(IErrorCodes.DB_ERROR_KEY,e);
		}
	}
	
	public List<Spaziodim> retrieveVltByIdLocation(String id_location) throws BusinessLayerException{
		
		try {
			return SpazioDAO.getInstance().retrieveVltByIdLocation(id_location);
		} catch (DataAccessException e) {
			throw new BusinessLayerException(IErrorCodes.DB_ERROR_KEY,e);
		}
	}
	
	public int fixGame() throws BusinessLayerException{
		
		try {
			return SpazioDAO.getInstance().fixGame();
		} catch (DataAccessException e) {
			throw new BusinessLayerException(IErrorCodes.DB_ERROR_KEY,e);
		}
	}
	
	public int fixVlt() throws BusinessLayerException{
		
		try {
			return SpazioDAO.getInstance().fixVlt();
		} catch (DataAccessException e) {
			throw new BusinessLayerException(IErrorCodes.DB_ERROR_KEY,e);
		}
	}
	
	public int fixLocation() throws BusinessLayerException{
		
		try {
			return SpazioDAO.getInstance().fixLocation();
		} catch (DataAccessException e) {
			throw new BusinessLayerException(IErrorCodes.DB_ERROR_KEY,e);
		}
	}
	
	public int fixClienti() throws BusinessLayerException{
		
		try {
			return ClientiDAO.getInstance().fixClienti();
		} catch (DataAccessException e) {
			throw new BusinessLayerException(IErrorCodes.DB_ERROR_KEY,e);
		}
	}
	
	
}
