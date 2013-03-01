package it.bplus.dao;

import org.apache.log4j.Logger;


public class SistemagiocoDAO  {

	protected static Logger logger = Logger.getLogger(SistemagiocoDAO.class);

	private static SistemagiocoDAO instance;

	private SistemagiocoDAO() {
		super();
	}


	public static synchronized SistemagiocoDAO getInstance() {
		if (instance == null)
		{
			synchronized(SistemagiocoDAO.class) {      //1
				SistemagiocoDAO inst = instance;         //2
				if (inst == null)
				{
					synchronized(SistemagiocoDAO.class) {  //3
						instance = new SistemagiocoDAO();
					}
					//instance = inst;               //5
				}
			}
		}
		return instance;
	}



//	public void saveNewSistemagiocodim(Sistemagiocodim sdgdim) throws DataAccessException{	
//		try{
//
//			this.persist(sdgdim);
//			
//		}catch(RuntimeException e){
//			logger.error("getAll failed", e);
//
//			throw new DataAccessException(IErrorCodes.DB_ERROR_KEY,e.toString());
//		}
//	}

}