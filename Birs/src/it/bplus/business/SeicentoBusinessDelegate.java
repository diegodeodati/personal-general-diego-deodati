package it.bplus.business;

import java.util.Date;
import java.util.List;
import java.util.Map;

import it.bplus.bean.Confronto;
import it.bplus.bean.MeterBean;
import it.bplus.dao.SeicentoDAO;
import it.bplus.exception.BusinessLayerException;
import it.bplus.exception.DataAccessException;
import it.bplus.util.IErrorCodes;
import org.apache.log4j.Logger;

public class SeicentoBusinessDelegate {

	protected static Logger logger = Logger.getLogger(SeicentoBusinessDelegate.class);

	private static SeicentoBusinessDelegate instance;

	public static synchronized SeicentoBusinessDelegate getInstance() {
		if (instance == null)
		{
			synchronized(SeicentoBusinessDelegate.class) {      //1
				SeicentoBusinessDelegate inst = instance;         //2
				if (inst == null)
				{
					synchronized(SeicentoBusinessDelegate.class) {  //3
						instance = new SeicentoBusinessDelegate();
					}
					//instance = inst;               //5
				}
			}
		}
		return instance;
	}


//	public List<MeterBean> retrieveMeter600_game(String location_name,
//			String aams_location_id, String regione, String provincia, String comune,
//			String gestore, Date data1, Date data2, String sortColumn, boolean isAscending)
//			throws BusinessLayerException {
//		try {
//			return SeicentoDAO.getInstance().retrieveMeter_game(location_name, aams_location_id,
//					regione, provincia, comune, gestore, data1, data2, sortColumn, isAscending);
//		} catch (DataAccessException e) {
//			throw new BusinessLayerException(IErrorCodes.DB_ERROR_KEY, e);
//		}
//	}
	
	

	public List<MeterBean> retrieveMeter600_vlt(String location_name, String aams_location_id,String sortColumn, boolean isAscending,Date data1,Date data2)
			throws BusinessLayerException {
		try {
			return SeicentoDAO.getInstance().retrieveMeter600_vlt(location_name,aams_location_id,sortColumn,isAscending,data1,data2);
		} catch (DataAccessException e) {
			throw new BusinessLayerException(IErrorCodes.DB_ERROR_KEY, e);
		}
	}
	
	
	public List<Confronto> retrieveConfronti600Sogei_vlt(String location_name,String aams_location_id, String aams_vlt_id,String sortColumn, boolean isAscending,
			Date data1, Date data2, boolean mostraTutto) throws BusinessLayerException {
		try {
			return SeicentoDAO.getInstance().retrieveConfronti600Sogei_vlt(location_name, aams_location_id, aams_vlt_id, sortColumn, isAscending,
					data1, data2, mostraTutto);
		} catch (DataAccessException e) {
			throw new BusinessLayerException(IErrorCodes.DB_ERROR_KEY, e);
		}
	}
	
	public List<Confronto> retrieveConfronti600Sogei_locations(String location_name,String aams_location_id, String sortColumn, boolean isAscending,
			Date data1, Date data2, boolean mostraTutto) throws BusinessLayerException {
		try {
			return SeicentoDAO.getInstance().retrieveConfronti600Sogei_locations(location_name, aams_location_id, sortColumn, isAscending,
					data1, data2, mostraTutto);
		} catch (DataAccessException e) {
			throw new BusinessLayerException(IErrorCodes.DB_ERROR_KEY, e);
		}
	}

	public List<MeterBean> retrieveMeter600_locations(String location_name, String aams_location_id,String sortColumn, boolean isAscending,Date data1,
			Date data2) throws BusinessLayerException {
		try {
			return SeicentoDAO.getInstance().retrieveMeter600_locations(location_name,aams_location_id,sortColumn,isAscending,data1,
					data2);
		} catch (DataAccessException e) {
			throw new BusinessLayerException(IErrorCodes.DB_ERROR_KEY, e);
		}
	}

	public Map<String,MeterBean> retrieveMeterMap600_locations(Date data1,Date data2)
			throws BusinessLayerException {
		try {
			return SeicentoDAO.getInstance().retrieveMeterMap600_locations(data1,data2);
		} catch (DataAccessException e) {
			throw new BusinessLayerException(IErrorCodes.DB_ERROR_KEY, e);
		}
	}	
	
}
