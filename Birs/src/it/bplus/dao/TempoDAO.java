package it.bplus.dao;

import org.apache.log4j.Logger;


public class TempoDAO  {

	protected static Logger logger = Logger.getLogger(TempoDAO.class);


	private static TempoDAO instance;

	private TempoDAO() {
		super();
	}



	public static synchronized TempoDAO getInstance() {
		if (instance == null)
		{
			synchronized(TempoDAO.class) {      //1
				TempoDAO inst = instance;         //2
				if (inst == null)
				{
					synchronized(TempoDAO.class) {  //3
						instance = new TempoDAO();
					}
					//instance = inst;               //5
				}
			}
		}
		return instance;
	}



//	public void saveNewTempodim(Tempodim tdim) throws DataAccessException{	
//		try{
//
//			this.persist(tdim);
//			
//		}catch(RuntimeException e){
//			logger.error("getAll failed", e);
//
//			throw new DataAccessException(IErrorCodes.DB_ERROR_KEY,e.toString());
//		}
//	}

}