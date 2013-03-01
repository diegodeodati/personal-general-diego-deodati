package it.bplus.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import it.bplus.bean.Confronto;
import it.bplus.bean.MeterBean;
import it.bplus.db.DBConnectionManager;
import it.bplus.exception.DataAccessException;

import it.bplus.util.DateUtil;
import it.bplus.util.IConstants;
import it.bplus.util.IErrorCodes;

public class SeicentoDAO {

	protected static Logger logger = Logger.getLogger(SeicentoDAO.class);


	private static SeicentoDAO instance;

	private SeicentoDAO() {
		super();
	}


	public static synchronized SeicentoDAO getInstance() {
		if (instance == null)
		{
			synchronized(SeicentoDAO.class) {      //1
				SeicentoDAO inst = instance;         //2
				if (inst == null)
				{
					synchronized(SeicentoDAO.class) {  //3
						instance = new SeicentoDAO();
					}
					//instance = inst;               //5
				}
			}
		}
		return instance;
	}


	
	
	
	
	public List<MeterBean> retrieveMeter600_locations(String location_name, String aams_location_id,String sortColumn, boolean isAscending,Date data1,Date data2)  throws DataAccessException {
		
//		String location_name,
//		String aams_location_id, String id_regione, String id_provincia, String id_comune,
//		String cod_gestore, Date data1, Date data2, 
		
		List<MeterBean> meters = null;
		ResultSet rs=null;
		PreparedStatement ps=null;
		Connection connStaging=null;
		
		try {
			connStaging=DBConnectionManager.StagingConnectionFactory();
						
			String sql = "select aams_location_id, commercial_name, data, count(code_id) as num_vlt, sum(bet) as bet,sum(win) as win, sum(bet_num) as bet_num, " +
					"sum(tot_in) as tot_in,sum(tot_out) as tot_out " +
					"from " +
					"(select loc.aams_location_id,loc.commercial_name,vlt.gs_vlt_code,met.code_id,met.data, " +
					"met.bet,met.win,met.bet_num,met.tot_in,met.tot_out " +
					"from birsaamsmeters met " +
					"left join birsvlt vlt on met.code_id=vlt.aams_vlt_id " +
					"left join birslocation loc on vlt.aams_location_id=loc.aams_location_id " +
					"where data between ? and ? ";
					
			
			if((location_name!=null && !location_name.equals(""))  || (aams_location_id!=null && !aams_location_id.equals(""))){
//					|| (id_regione!=null && !id_regione.equals("") && !id_regione.equals(" - ")) 
//					|| (id_provincia!=null && !id_provincia.equals("") && !id_provincia.equals(" - "))
//					|| (id_comune!=null && !id_comune.equals("") && !id_comune.equals("-1")) 
//					|| (cod_gestore!=null && !cod_gestore.equals("")) ||	data1!=null || data2!=null){
				sql = sql+" AND ";
				boolean and = false;
				if (location_name!=null && !location_name.equals("")){
					sql=sql+"lower(loc.commercial_name) like lower('%"+location_name+"%') ";
					and = true;
				}
				if (aams_location_id!=null && !aams_location_id.equals("")){
					if (and) sql=sql+" AND ";
					sql=sql+"lower(loc.aams_location_id) like lower('%"+aams_location_id+"%') ";
					and = true;
				}
//				if (id_regione!=null && !id_regione.equals("") && !id_regione.equals(" - ")){
//					if (and) sql=sql+" AND ";
//					sql=sql+"vista2.ID_REGIONE = "+id_regione+" ";
//					and = true;
//				}
//				if (id_provincia!=null && !id_provincia.equals("") && !id_provincia.equals(" - ")){
//					if (and) sql=sql+" AND ";
//					sql=sql+"vista2.ID_PROVINCIA = "+id_provincia+" ";
//					and = true;
//				}
//				if (id_comune!=null && !id_comune.equals("") && !id_comune.equals("-1")){
//					if (and) sql=sql+" AND ";
//					sql=sql+"vista2.ID_COMUNE = "+id_comune+" ";
//					and = true;
//				}
//				if (cod_gestore!=null && !cod_gestore.equals("")){
//					if (and) sql=sql+" AND ";
//					sql=sql+"lower(vista1.COD_GESTORE) like lower('%"+cod_gestore+"%') ";
//					and = true;
//				}
//				if (data1!=null && data2!=null){
//					DateFormat df = new SimpleDateFormat(IConstants.DATE_FORMAT);
//					if (and) sql=sql+" AND ";
//					sql=sql+"vista1.DATA between TO_DATE('"+df.format(data1)+"','"+IConstants.DATE_FORMAT+"') AND TO_DATE('"+df.format(data2)+"','"+IConstants.DATE_FORMAT+"') ";
//					and = false;
//				}
//				else if (data1!=null){
//					DateFormat df = new SimpleDateFormat(IConstants.DATE_FORMAT);					
//					if (and) sql=sql+" AND ";
//					sql=sql+"vista1.DATA = TO_DATE('"+df.format(data1)+"','"+IConstants.DATE_FORMAT+"') ";
//					and = false;
//				}
//				else if (data2!=null){
//					DateFormat df = new SimpleDateFormat(IConstants.DATE_FORMAT);
//					if (and) sql=sql+" AND ";
//					sql=sql+"vista1.DATA = TO_DATE('"+df.format(data2)+"','"+IConstants.DATE_FORMAT+"') ";
//					and = false;
//				}				
			} 
			
			sql = sql + ") group by aams_location_id, commercial_name, data ";
			//"order by commercial_name asc";
			
			//ORDINAMENTO
			String ascDesc = null;
			if (!isAscending)
				ascDesc = " DESC ";
			else if (isAscending)
				ascDesc = " ASC ";
			
			
			if (sortColumn!=null && sortColumn.equals("num_vlt"))
				sortColumn=sortColumn+ascDesc+", data"+ascDesc+", commercial_name";
			else if (sortColumn!=null && sortColumn.equals("data"))
				sortColumn=sortColumn+ascDesc+", commercial_name";
			else if (sortColumn!=null && sortColumn.equals("commercial_name"))
				sortColumn=sortColumn+ascDesc+", data";
//			 else if (sortColumn!=null && sortColumn.equals("vista1.provincia"))
//				sortColumn="vista1.provincia"+ascDesc+",vista1.COMUNE"+ascDesc+",vista1.LOCATION_NAME";
//			 else if (sortColumn!=null && sortColumn.equals("vista1.comune"))
//				sortColumn="vista1.COMUNE"+ascDesc+",vista1.LOCATION_NAME";
//							
			if(sortColumn!=null && !(sortColumn.equals("")))
				sql=sql+" ORDER BY "+sortColumn+ascDesc;								
			else if (sortColumn==null || sortColumn.equals(""))
				sql=sql+" ORDER BY data ASC, commercial_name ASC";		
			
			//logger.info(sql+" "+data1+" "+data2);
			
			ps = connStaging.prepareStatement(sql);
			
			java.sql.Date data1_ = new java.sql.Date(data1.getTime());
			java.sql.Date data2_ = new java.sql.Date(data2.getTime());
			ps.setDate(1, data1_);
			ps.setDate(2, data2_);
								
			rs = ps.executeQuery();
			
			meters = new ArrayList<MeterBean>();
			
			while (rs.next()){
				MeterBean mbean = new MeterBean();
				mbean.setAams_location_id(rs.getString("AAMS_LOCATION_ID"));
				mbean.setLocation_name(rs.getString("COMMERCIAL_NAME"));				
				mbean.setData(rs.getDate("DATA"));
				mbean.setNumVlt(rs.getInt("NUM_VLT"));
				mbean.setBet(BigDecimal.valueOf(rs.getLong("BET"),2).doubleValue());
				mbean.setWin(BigDecimal.valueOf(rs.getLong("WIN"),2).doubleValue());
				mbean.setGamesPlayed(rs.getLong("BET_NUM"));
				mbean.setTotalIn(BigDecimal.valueOf(rs.getLong("TOT_IN"),2).doubleValue());
				mbean.setTotalOut(BigDecimal.valueOf(rs.getLong("TOT_OUT"),2).doubleValue());	
							
				meters.add(mbean);
			}			
		} catch (SQLException e) {
			logger.error("retrieveMeter600_locations: SQL failed", e);
			e.printStackTrace();
			throw new DataAccessException(IErrorCodes.DB_ERROR_KEY, e.toString(), e);
		}		
		catch (ArrayIndexOutOfBoundsException e) {
			logger.error("EXCEPTION: retrieveMeter600_locations ArrayIndexOutOfBoundsException", e);
			throw new DataAccessException(IErrorCodes.DB_ERROR_KEY, e.toString(), e);
		}
		catch (Exception e) {
			logger.error("retrieveMeter600_locations failed", e);
			e.printStackTrace();
			throw new DataAccessException(IErrorCodes.DB_ERROR_KEY, e.toString(), e);
		}
		finally{
				try {
					DBConnectionManager.CloseConnection(rs, ps, connStaging);				
				} catch (DataAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					logger.error(e);
				}
		}
		return meters;
	}	
	
	
	
	public Map<String,MeterBean> retrieveMeterMap600_locations(Date data1,Date data2)  throws DataAccessException {
		
//		String location_name,
//		String aams_location_id, String id_regione, String id_provincia, String id_comune,
//		String cod_gestore, Date data1, Date data2, String sortColumn, boolean isAscending
		
		Map<String,MeterBean> meters = null;
		ResultSet rs=null;
		PreparedStatement ps=null;
		Connection connStaging=null;
		
		try {
			connStaging=DBConnectionManager.StagingConnectionFactory();
						
			String sql = "select aams_location_id, commercial_name, data, count(code_id) as num_vlt, sum(bet) as bet,sum(win) as win, sum(bet_num) as bet_num, " +
					"sum(tot_in) as tot_in,sum(tot_out) as tot_out " +
					"from " +
					"(select loc.aams_location_id,loc.commercial_name,vlt.gs_vlt_code,met.code_id,met.data, " +
					"met.bet,met.win,met.bet_num,met.tot_in,met.tot_out " +
					"from birsaamsmeters met " +
					"left join birsvlt vlt on met.code_id=vlt.aams_vlt_id " +
					"left join birslocation loc on vlt.aams_location_id=loc.aams_location_id " +
					"where data between ? and ?) " +
					"group by aams_location_id, commercial_name, data " +
					"order by aams_location_id asc";
			
//			if((location_name!=null && !location_name.equals(""))  || (aams_location_id!=null && !aams_location_id.equals(""))
//					|| (id_regione!=null && !id_regione.equals("") && !id_regione.equals(" - ")) 
//					|| (id_provincia!=null && !id_provincia.equals("") && !id_provincia.equals(" - "))
//					|| (id_comune!=null && !id_comune.equals("") && !id_comune.equals("-1")) 
//					|| (cod_gestore!=null && !cod_gestore.equals("")) ||	data1!=null || data2!=null){
//				sql = sql+"WHERE ";
//				boolean and = false;
//				if (location_name!=null && !location_name.equals("")){
//					sql=sql+"lower(vista1.LOCATION_NAME) like lower('%"+location_name+"%') ";
//					and = true;
//				}
//				if (aams_location_id!=null && !aams_location_id.equals("")){
//					if (and) sql=sql+" AND ";
//					sql=sql+"lower(vista1.AAMS_LOCATION_ID) like lower('%"+aams_location_id+"%') ";
//					and = true;
//				}
//				if (id_regione!=null && !id_regione.equals("") && !id_regione.equals(" - ")){
//					if (and) sql=sql+" AND ";
//					sql=sql+"vista2.ID_REGIONE = "+id_regione+" ";
//					and = true;
//				}
//				if (id_provincia!=null && !id_provincia.equals("") && !id_provincia.equals(" - ")){
//					if (and) sql=sql+" AND ";
//					sql=sql+"vista2.ID_PROVINCIA = "+id_provincia+" ";
//					and = true;
//				}
//				if (id_comune!=null && !id_comune.equals("") && !id_comune.equals("-1")){
//					if (and) sql=sql+" AND ";
//					sql=sql+"vista2.ID_COMUNE = "+id_comune+" ";
//					and = true;
//				}
//				if (cod_gestore!=null && !cod_gestore.equals("")){
//					if (and) sql=sql+" AND ";
//					sql=sql+"lower(vista1.COD_GESTORE) like lower('%"+cod_gestore+"%') ";
//					and = true;
//				}
//				if (data1!=null && data2!=null){
//					DateFormat df = new SimpleDateFormat(IConstants.DATE_FORMAT);
//					if (and) sql=sql+" AND ";
//					sql=sql+"vista1.DATA between TO_DATE('"+df.format(data1)+"','"+IConstants.DATE_FORMAT+"') AND TO_DATE('"+df.format(data2)+"','"+IConstants.DATE_FORMAT+"') ";
//					and = false;
//				}
//				else if (data1!=null){
//					DateFormat df = new SimpleDateFormat(IConstants.DATE_FORMAT);					
//					if (and) sql=sql+" AND ";
//					sql=sql+"vista1.DATA = TO_DATE('"+df.format(data1)+"','"+IConstants.DATE_FORMAT+"') ";
//					and = false;
//				}
//				else if (data2!=null){
//					DateFormat df = new SimpleDateFormat(IConstants.DATE_FORMAT);
//					if (and) sql=sql+" AND ";
//					sql=sql+"vista1.DATA = TO_DATE('"+df.format(data2)+"','"+IConstants.DATE_FORMAT+"') ";
//					and = false;
//				}				
//			} 
			
						
			//logger.info(sql+" "+data1);
			
			ps = connStaging.prepareStatement(sql);
			
			java.sql.Date data1_ = new java.sql.Date(data1.getTime());
			java.sql.Date data2_ = new java.sql.Date(data2.getTime());
			ps.setDate(1, data1_);
			ps.setDate(2, data2_);
								
			rs = ps.executeQuery();
			
			meters = new HashMap<String,MeterBean>();
			
			while (rs.next()){
				MeterBean mbean = new MeterBean();
				mbean.setAams_location_id(rs.getString("AAMS_LOCATION_ID"));
				mbean.setLocation_name(rs.getString("COMMERCIAL_NAME"));				
				mbean.setData(rs.getDate("DATA"));
				mbean.setNumVlt(rs.getInt("NUM_VLT"));
				mbean.setBet(BigDecimal.valueOf(rs.getLong("BET"),2).doubleValue());
				mbean.setWin(BigDecimal.valueOf(rs.getLong("WIN"),2).doubleValue());
				mbean.setGamesPlayed(rs.getLong("BET_NUM"));
				mbean.setTotalIn(BigDecimal.valueOf(rs.getLong("TOT_IN"),2).doubleValue());
				mbean.setTotalOut(BigDecimal.valueOf(rs.getLong("TOT_OUT"),2).doubleValue());	
							
				meters.put(mbean.getAams_location_id(),mbean);
			}			
		} catch (SQLException e) {
			logger.error("retrieveMeterMap600_locations: SQL failed", e);
			e.printStackTrace();
			throw new DataAccessException(IErrorCodes.DB_ERROR_KEY, e.toString(), e);
		}
		catch (Exception e) {
			logger.error("retrieveMeterMap600_locations failed", e);
			e.printStackTrace();
			throw new DataAccessException(IErrorCodes.DB_ERROR_KEY, e.toString(), e);
		}
		finally{
				try {
					DBConnectionManager.CloseConnection(rs, ps, connStaging);				
				} catch (DataAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					logger.error(e);
				}
		}
		return meters;
	}	
	
	
	
	
	
	
	
	
	
	//QUI SFRUTTO IL CONNECTION POOL
	public List<MeterBean> retrieveMeter600_vlt(String location_name, String aams_location_id,String sortColumn, boolean isAscending,Date data1,Date data2)  throws DataAccessException {

//		String location_name,
//		String aams_location_id, String id_regione, String id_provincia, String id_comune,
//		String cod_gestore, Date data1, Date data2, String sortColumn, boolean isAscending		
		
		List<MeterBean> meters = null;
		ResultSet rs=null;
		PreparedStatement ps=null;
		Connection connStaging=null;
			
		
		try {
			connStaging=DBConnectionManager.StagingConnectionFactory();
			
			String sql = "select loc.aams_location_id,loc.commercial_name,vlt.gs_vlt_code,met.code_id,met.data," +
					"met.bet,met.win,met.bet_num,met.tot_in,met.tot_out,met.aams_games_system_code " +
					"from birsaamsmeters met " +
					"left join birsvlt vlt on met.code_id=vlt.aams_vlt_id " +
					"left join birslocation loc on vlt.aams_location_id=loc.aams_location_id " +
					"where data between ? and ?";	//2 punti interrogativi
			
			if ((location_name != null && !location_name.equals(""))
					|| (aams_location_id != null && !aams_location_id
							.equals(""))) {

				sql = sql + " AND ";
				boolean and = false;
				if (location_name != null && !location_name.equals("")) {
					sql = sql + "lower(loc.commercial_name) like lower('%"
							+ location_name + "%') ";
					and = true;
				}
				if (aams_location_id != null && !aams_location_id.equals("")) {
					if (and)
						sql = sql + " AND ";
					sql = sql + "lower(loc.aams_location_id) like lower('%"
							+ aams_location_id + "%') ";
					and = true;
				}
			}
			
			
			//ORDINAMENTO
			String ascDesc = null;
			if (!isAscending)
				ascDesc = " DESC ";
			else if (isAscending)
				ascDesc = " ASC ";
			
			
			if (sortColumn!=null && sortColumn.equals("num_vlt"))
				sortColumn=sortColumn+ascDesc+", data"+ascDesc+", commercial_name"+ascDesc+", code_id";
			else if (sortColumn!=null && sortColumn.equals("data"))
				sortColumn=sortColumn+ascDesc+", commercial_name"+ascDesc+", code_id";
			else if (sortColumn!=null && sortColumn.equals("commercial_name"))
				sortColumn=sortColumn+ascDesc+", data"+ascDesc+", code_id";
			else if (sortColumn!=null && sortColumn.equals("code_id"))
				sortColumn=sortColumn+ascDesc+", data";
			else if (sortColumn!=null && sortColumn.equals("gs_vlt_code"))
				sortColumn=sortColumn+ascDesc+", data";
							
			if(sortColumn!=null && !(sortColumn.equals("")))
				sql=sql+" ORDER BY "+sortColumn+ascDesc;								
			else if (sortColumn==null || sortColumn.equals(""))
				sql=sql+" ORDER BY commercial_name ASC, code_id ASC, data ASC";		
			
			//logger.info(sql+" "+data1+" "+data2);
//				//ORDINAMENTO
//				String ascDesc = null;
//				if (!isAscending)
//					ascDesc = " DESC ";
//				else if (isAscending)
//					ascDesc = " ASC ";
//				
//				if (sortColumn!=null && sortColumn.equals("tempodim.DATA"))
//					sortColumn="spaziodim.LOCATION_NAME"+ascDesc+",spaziodim.AAMS_VLT_ID"+ascDesc+", "+sortColumn;
//				 else if (sortColumn!=null && sortColumn.equals("spaziodim.SIGLA_PROVINCIA"))
//					sortColumn="spaziodim.SIGLA_PROVINCIA"+ascDesc+",spaziodim.CITY"+ascDesc+", spaziodim.LOCATION_NAME"+ascDesc+",spaziodim.AAMS_VLT_ID";
//				 else if (sortColumn!=null && sortColumn.equals("spaziodim.city"))
//					sortColumn="spaziodim.city"+ascDesc+", spaziodim.LOCATION_NAME"+ascDesc+",spaziodim.AAMS_VLT_ID";
//				 else if (sortColumn!=null && sortColumn.equals("spaziodim.location_Name"))
//					sortColumn="spaziodim.LOCATION_NAME"+ascDesc+",spaziodim.AAMS_VLT_ID";
//				
//				
//				if(sortColumn!=null && !(sortColumn.equals("")))
//					sql=sql+" ORDER BY "+sortColumn+ascDesc;								
//				else if (sortColumn==null || sortColumn.equals(""))
//					sql=sql+" ORDER BY spaziodim.LOCATION_NAME ASC,spaziodim.AAMS_VLT_ID ASC,tempodim.DATA ASC";
						
			ps = connStaging.prepareStatement(sql);
			
			java.sql.Date data1_ = new java.sql.Date(data1.getTime());
			java.sql.Date data2_ = new java.sql.Date(data2.getTime());
			
			ps.setDate(1, data1_);
			ps.setDate(2, data2_);
							
			rs = ps.executeQuery();
			
			meters = new ArrayList<MeterBean>();
			
			while (rs.next()){
				MeterBean mbean = new MeterBean();
				mbean.setAams_location_id(rs.getString("AAMS_LOCATION_ID"));
				mbean.setLocation_name(rs.getString("COMMERCIAL_NAME"));				
				mbean.setGs_vlt_id(rs.getString("GS_VLT_CODE"));
				mbean.setAams_vlt_id(rs.getString("CODE_ID"));
				mbean.setData(rs.getDate("DATA"));
				mbean.setBet(BigDecimal.valueOf(rs.getLong("BET"),2).doubleValue());
				mbean.setWin(BigDecimal.valueOf(rs.getLong("WIN"),2).doubleValue());
				mbean.setGamesPlayed(rs.getLong("BET_NUM"));
				mbean.setTotalIn(BigDecimal.valueOf(rs.getLong("TOT_IN"),2).doubleValue());
				mbean.setTotalOut(BigDecimal.valueOf(rs.getLong("TOT_OUT"),2).doubleValue());	
				
//				mbean.setCod_gestore(rs.getString("COD_GESTORE"));				
//				mbean.setRegione(rs.getString("REGIONE"));
//				mbean.setProvincia(rs.getString("PROVINCIA"));
//				mbean.setComune(rs.getString("COMUNE"));
//				mbean.setLocation_address(rs.getString("TOPONIMO")+" "+rs.getString("LOCATION_ADDRESS")+" "+rs.getString("NUMERO_CIVICO"));			
//				mbean.setStato(rs.getString("STATO"));			
				
				meters.add(mbean);
			}
			
		} catch (SQLException e) {
			logger.error("retrieveMeter600_vlt failed", e);
			e.printStackTrace();
			throw new DataAccessException(IErrorCodes.DB_ERROR_KEY, e.toString(), e);
		}
		catch (Exception e) {
			logger.error("retrieveMeter600_vlt failed", e);
			e.printStackTrace();
			throw new DataAccessException(IErrorCodes.DB_ERROR_KEY, e.toString(), e);
		}
		finally{
				try {
					DBConnectionManager.CloseConnection(rs, ps, connStaging);				
				} catch (DataAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					logger.error(e);
				}
		}
		return meters;
	}	
	
	
	
	
	
	
	//dettagli per gioco
	public List<MeterBean> retrieveMeter_game(String location_name,
			String aams_location_id, String id_regione, String id_provincia, String id_comune,
			String cod_gestore, Date data1, Date data2, String sortColumn, boolean isAscending)  throws DataAccessException {

		List<MeterBean> meters = null;
		ResultSet rs=null;
		PreparedStatement ps=null;
		Connection connDataMart=null;
			
		
		try {
			connDataMart=DBConnectionManager.DataMartConnectionFactory();
			String sql = "select sum(this_.BET) as BET, sum(this_.WIN) as WIN, sum(this_.GAMES_PLAYED) as GAMES_PLAYED, min(this_.TOTAL_IN) as TOTAL_IN, min(this_.TOTAL_OUT) as TOTAL_OUT, " +
					"min(this_.TICKET_IN) as TICKET_IN, min(this_.TICKET_OUT) as TICKET_OUT, min(this_.COIN_IN) as COIN_IN, min(this_.BILL_IN) as BILL_IN, min(this_.CARD_IN) as CARD_IN, " +
					"min(this_.TOTAL_PREPAID_IN) as TOTAL_PREPAID_IN, sum(this_.JACKPOT_WINS) as JACKPOT_WINS, sum(this_.JACKPOT_CONTRIBUTION) as JACKPOT_CONTRIBUTION, sum(this_.PREU) as PREU," +
					"sum(this_.AAMS) as AAMS, sum(this_.NET_WIN) as NET_WIN, sum(this_.HOUSE_WIN) as HOUSE_WIN, sum(this_.SUPPLIER_PROFIT) as SUPPLIER_PROFIT, " +
					"sum(this_.OPERATORS_PROFIT) as OPERATORS_PROFIT, sum(this_.BPLUS_NET_PROFIT) as BPLUS_NET_PROFIT, spaziodim.GS_GAME_ID as GS_GAME_ID, " +
					"spaziodim.GAME_NAME as GAME_NAME, spaziodim.AAMS_VLT_ID as AAMS_VLT_ID, spaziodim.GS_VLT_ID as GS_VLT_ID,spaziodim.AAMS_LOCATION_ID as AAMS_LOCATION_ID, " +
					"spaziodim.LOCATION_NAME as LOCATION_NAME, clientidim.COD_GESTORE as COD_GESTORE, spaziodim.REGION as REGIONE, " +
					"spaziodim.SIGLA_PROVINCIA as PROVINCIA, spaziodim.CITY as COMUNE, spaziodim.LOCATION_ADDRESS as LOCATION_ADDRESS, spaziodim.NUMERO_CIVICO as NUMERO_CIVICO,spaziodim.TOPONIMO AS TOPONIMO, " +
					"tempodim.DATA as DATA, this_.STATO as STATO " +
					"from METERFACT this_ " +
					"inner join TEMPODIM tempodim on this_.DMTEMPO_ID=tempodim.ID " +
					"inner join CLIENTIDIM clientidim on this_.DMCLIENTI_ID=clientidim.ID " +
					"left outer join SPAZIODIM spaziodim on this_.DMSPAZIO_ID=spaziodim.ID " +
					"where tempodim.DATA between ? and ? ";	//2 punti interrogativi
			
			if((location_name!=null && !location_name.equals(""))  || (aams_location_id!=null && !aams_location_id.equals(""))
					|| (id_regione!=null && !id_regione.equals("") && !id_regione.equals(" - ")) 
					|| (id_provincia!=null && !id_provincia.equals("") && !id_provincia.equals(" - "))
					|| (id_comune!=null && !id_comune.equals("") && !id_comune.equals("-1")) 
					|| (cod_gestore!=null && !cod_gestore.equals(""))){
				sql = sql+"AND ";
				boolean and = false;
				if (location_name!=null && !location_name.equals("")){
					sql=sql+"lower(spaziodim.LOCATION_NAME) like lower('%"+location_name+"%') ";
					and = true;
				}
				if (aams_location_id!=null && !aams_location_id.equals("")){
					if (and) sql=sql+" AND ";
					sql=sql+"lower(spaziodim.AAMS_LOCATION_ID) like lower('%"+aams_location_id+"%') ";
					and = true;
				}
				if (id_regione!=null && !id_regione.equals("") && !id_regione.equals(" - ")){
					if (and) sql=sql+" AND ";
					sql=sql+"spaziodim.ID_REGIONE = "+id_regione+" ";
					and = true;
				}
				if (id_provincia!=null && !id_provincia.equals("") && !id_provincia.equals(" - ")){
					if (and) sql=sql+" AND ";
					sql=sql+"spaziodim.ID_PROVINCIA = "+id_provincia+" ";
					and = true;
				}
				if (id_comune!=null && !id_comune.equals("") && !id_comune.equals("-1")){
					if (and) sql=sql+" AND ";
					sql=sql+"spaziodim.ID_COMUNE = "+id_comune+" ";
					and = true;
				}
				if (cod_gestore!=null && !cod_gestore.equals("")){
					if (and) sql=sql+" AND ";
					sql=sql+"lower(clientidim.COD_GESTORE) like lower('%"+cod_gestore+"%') ";
					and = false;
				}
			}
					
				//RAGGRUPPAMENTO
				sql=sql+" group by spaziodim.AAMS_LOCATION_ID,spaziodim.LOCATION_NAME, clientidim.COD_GESTORE, spaziodim.REGION, spaziodim.SIGLA_PROVINCIA, " +
						"spaziodim.CITY, spaziodim.LOCATION_ADDRESS,spaziodim.NUMERO_CIVICO,spaziodim.TOPONIMO, spaziodim.AAMS_VLT_ID,spaziodim.GS_VLT_ID,spaziodim.GS_GAME_ID, spaziodim.GAME_NAME, tempodim.DATA, this_.STATO ";
				
				//ORDINAMENTO
				String ascDesc = null;
				if (!isAscending)
					ascDesc = " DESC ";
				else if (isAscending)
					ascDesc = " ASC ";
				
				if (sortColumn!=null && sortColumn.equals("tempodim.DATA"))
					sortColumn="spaziodim.LOCATION_NAME"+ascDesc+",spaziodim.AAMS_VLT_ID"+ascDesc+", "+sortColumn;
				 else if (sortColumn!=null && sortColumn.equals("spaziodim.SIGLA_PROVINCIA"))
					sortColumn="spaziodim.SIGLA_PROVINCIA"+ascDesc+",spaziodim.CITY"+ascDesc+", spaziodim.LOCATION_NAME"+ascDesc+",spaziodim.AAMS_VLT_ID"+ascDesc+",spaziodim.GAME_NAME";
				 else if (sortColumn!=null && sortColumn.equals("spaziodim.city"))
					sortColumn="spaziodim.city"+ascDesc+", spaziodim.LOCATION_NAME"+ascDesc+",spaziodim.AAMS_VLT_ID"+ascDesc+",spaziodim.GAME_NAME";
				 else if (sortColumn!=null && sortColumn.equals("spaziodim.location_Name"))
					sortColumn="spaziodim.LOCATION_NAME"+ascDesc+",spaziodim.AAMS_VLT_ID"+ascDesc+",spaziodim.GAME_NAME";
				 else if (sortColumn!=null && sortColumn.equals("spaziodim.aams_Vlt_Id"))
					sortColumn="spaziodim.AAMS_VLT_ID"+ascDesc+",spaziodim.GAME_NAME";
				 else if (sortColumn!=null && sortColumn.equals("spaziodim.gs_Vlt_Id"))
						sortColumn="spaziodim.GS_VLT_ID"+ascDesc+",spaziodim.GAME_NAME";
				
				if(sortColumn!=null && !(sortColumn.equals("")))
					sql=sql+" ORDER BY "+sortColumn+ascDesc;								
				else if (sortColumn==null || sortColumn.equals(""))
					sql=sql+" ORDER BY spaziodim.LOCATION_NAME ASC, spaziodim.AAMS_VLT_ID ASC, spaziodim.GAME_NAME ASC, tempodim.DATA ASC";
				
//				if (sortColumn.equals("tempodim.DATA")){
//					sortColumn="spaziodim.LOCATION_NAME,spaziodim.AAMS_VLT_ID, "+sortColumn;
//				}
//				
//				if(sortColumn!=null && !(sortColumn.equals(""))){
//					sql=sql+" ORDER BY "+sortColumn;
//					if (!isAscending) sql=sql+" DESC ";
//					else sql=sql+" ASC ";
//				}				
//				else sql=sql+" ORDER BY spaziodim.LOCATION_NAME,spaziodim.AAMS_VLT_ID ASC";
				
			
			
			ps = connDataMart.prepareStatement(sql);
			
			java.sql.Date data1_ = new java.sql.Date(data1.getTime());
			java.sql.Date data2_ = new java.sql.Date(data2.getTime());
			
			ps.setDate(1, data1_);
			ps.setDate(2, data2_);
							
			rs = ps.executeQuery();
			
			meters = new ArrayList<MeterBean>();
			
			while (rs.next()){
				MeterBean mbean = new MeterBean();
				mbean.setBet(rs.getDouble("BET"));
				mbean.setWin(rs.getDouble("WIN"));
				mbean.setGamesPlayed(rs.getLong("GAMES_PLAYED"));
				mbean.setTotalIn(rs.getDouble("TOTAL_IN"));
				mbean.setTotalOut(rs.getDouble("TOTAL_OUT"));	
				mbean.setTicketIn(rs.getDouble("TICKET_IN"));
				mbean.setTicketOut(rs.getDouble("TICKET_OUT"));
				mbean.setCoinIn(rs.getDouble("COIN_IN"));
				mbean.setBillIn(rs.getDouble("BILL_IN"));
				mbean.setCardIn(rs.getDouble("CARD_IN"));
				mbean.setTotalPrepaidIn(rs.getDouble("TOTAL_PREPAID_IN"));
				mbean.setJackpotWins(rs.getDouble("JACKPOT_WINS"));
				mbean.setJackpotContribution(rs.getDouble("JACKPOT_CONTRIBUTION"));
				mbean.setPreu(rs.getDouble("PREU"));
				mbean.setAams(rs.getDouble("AAMS"));
				mbean.setNetWin(rs.getDouble("NET_WIN"));
				mbean.setHouseWin(rs.getDouble("HOUSE_WIN"));
				mbean.setSupplierProfit(rs.getDouble("SUPPLIER_PROFIT"));
				mbean.setOperatorsProfit(rs.getDouble("OPERATORS_PROFIT"));
				mbean.setBplusNetProfit(rs.getDouble("BPLUS_NET_PROFIT"));
				mbean.setLocation_name(rs.getString("LOCATION_NAME"));
				mbean.setCod_gestore(rs.getString("COD_GESTORE"));
				mbean.setAams_location_id(rs.getString("AAMS_LOCATION_ID"));
				mbean.setRegione(rs.getString("REGIONE"));
				mbean.setProvincia(rs.getString("PROVINCIA"));
				mbean.setComune(rs.getString("COMUNE"));
				mbean.setLocation_address(rs.getString("TOPONIMO")+" "+rs.getString("LOCATION_ADDRESS")+" "+rs.getString("NUMERO_CIVICO"));
				mbean.setAams_vlt_id(rs.getString("AAMS_VLT_ID"));
				mbean.setGs_vlt_id(rs.getString("GS_VLT_ID"));
				mbean.setData(rs.getDate("DATA"));
				mbean.setStato(rs.getString("STATO"));
				mbean.setCode_game(rs.getLong("GS_GAME_ID"));
				mbean.setGame_name(rs.getString("GAME_NAME"));
				
				
				if (mbean.getOperatorsProfit()==null)
					mbean.setOperatorsProfit(0.00);
				if (mbean.getBplusNetProfit()==null)
					mbean.setBplusNetProfit(0.00);
				if (mbean.getCod_gestore()==null)
					mbean.setCod_gestore("");
				if (mbean.getRegione()==null)
					mbean.setRegione("");
				if (mbean.getProvincia()==null)
					mbean.setProvincia("");
				if (mbean.getComune()==null)
					mbean.setComune("");
				if (mbean.getLocation_address()==null)
					mbean.setLocation_address("");
				if (mbean.getStato()==null)
					mbean.setStato("");
				
			
				String nota = "";
				if (rs.getString("AAMS_VLT_ID") == null || rs.getString("GS_VLT_ID") == null
						|| rs.getString("COD_GESTORE") == null
						|| rs.getString("GAME_NAME") == null
						|| rs.getString("LOCATION_ADDRESS") == null) {
					
					nota = "Dati mancanti su: ";
					boolean virgola = false;
					
					if (rs.getString("AAMS_VLT_ID") == null || rs.getString("GS_VLT_ID") == null) {				
						nota += "vlt ";
						virgola = true;
					}
					if (rs.getString("COD_GESTORE") == null) {
						if (virgola==true)
							nota=nota+", ";
						nota += "gestore";
						virgola=true;
					}
					if (rs.getString("GAME_NAME") == null) {
						if (virgola==true)
							nota=nota+", ";
						nota += "gioco";
						virgola=true;
					}
					if (rs.getString("LOCATION_ADDRESS") == null) {
						if (virgola==true)
							nota=nota+", ";
						nota += "sala";
					}			
				}
				mbean.setNote(nota);
				
				meters.add(mbean);
			}
			
		} catch (SQLException e) {
			logger.error("retrieveMeter_game failed", e);
			e.printStackTrace();
			throw new DataAccessException(IErrorCodes.DB_ERROR_KEY, e.toString(), e);
		}
		catch (Exception e) {
			logger.error("retrieveMeter_game failed", e);
			e.printStackTrace();
			throw new DataAccessException(IErrorCodes.DB_ERROR_KEY, e.toString(), e);
		}
		finally{
				try {
					DBConnectionManager.CloseConnection(rs, ps, connDataMart);				
				} catch (DataAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					logger.error(e);
				}
		}
		return meters;
	}
	
	
	public List<Confronto> old_retrieveConfronti600Sogei_vlt(String location_name, String aams_location_id,String sortColumn, 
			boolean isAscending,Date data1,Date data2, boolean mostraTutto)  throws DataAccessException {

		List<Confronto> lista_confronti = null;
		ResultSet rs=null;
		PreparedStatement ps=null;
		Connection connDatamart=null;
			
		
		try {
			connDatamart=DBConnectionManager.DataMartConnectionFactory();
			
		
			String sql = "select vistaSogei.SOGEI_LOCATION_ID, vistaSogei.SOGEI_LOCATION_NAME," +
					"        vistaSogei.SOGEI_AAMS_VLT_ID,vistaSogei.SOGEI_GS_VLT_ID,vistaSogei.SOGEI_BET, vistaSogei.SOGEI_WIN," +
					"        vistaSogei.SOGEI_GAMES_PLAYED,vistaSogei.SOGEI_TOTAL_IN,vistaSogei.SOGEI_TOTAL_OUT,vistaSogei.SOGEI_DATA," +
					"        vista600.LOCATION_ID_600,vista600.LOCATION_NAME_600,vista600.CODE_ID_600,vista600.GS_VLT_ID_600,vista600.BET_600,vista600.WIN_600,vista600.BET_NUM_600," +
					"        vista600.TOT_IN_600,vista600.TOT_OUT_600,vista600.DATA_600 " +
					"from " +
					"(select spaziodim.aams_location_id as SOGEI_LOCATION_ID,spaziodim.location_name AS SOGEI_LOCATION_NAME," +
					"          sum(metSogei.BET) as SOGEI_BET, sum(metSogei.WIN) as SOGEI_WIN, sum(metSogei.GAMES_PLAYED) as SOGEI_GAMES_PLAYED, " +
					"			max(metSogei.TOTAL_IN) as SOGEI_TOTAL_IN, max(metSogei.TOTAL_OUT) as SOGEI_TOTAL_OUT," +
					"			spaziodim.AAMS_VLT_ID as SOGEI_AAMS_VLT_ID,spaziodim.GS_VLT_ID as SOGEI_GS_VLT_ID, tempodim.DATA as SOGEI_DATA 		" +
					"			from METERFACT metSogei " +
					"			inner join TEMPODIM tempodim on metSogei.DMTEMPO_ID=tempodim.ID " +
					"			inner join SPAZIODIM spaziodim on metSogei.DMSPAZIO_ID=spaziodim.ID " +
					"			where tempodim.DATA between ? and ? ";
					if ((location_name != null && !location_name.equals(""))
							|| (aams_location_id != null && !aams_location_id
									.equals(""))) {

						sql = sql + " AND ";
						boolean and = false;
						if (location_name != null && !location_name.equals("")) {
							sql = sql + "lower(spaziodim.LOCATION_NAME) like lower('%"+ location_name +"%') ";
							and = true;
						}
						if (aams_location_id != null && !aams_location_id.equals("")) {
							if (and)
								sql = sql + " AND ";
							sql = sql + "lower(spaziodim.AAMS_LOCATION_ID) like lower('%"+ aams_location_id +"%') ";
							and = false;
						}
					}				
			sql=sql+"          group by spaziodim.aams_location_id, spaziodim.location_name, spaziodim.AAMS_VLT_ID, spaziodim.GS_VLT_ID, tempodim.DATA " +
					") vistaSogei        " +
					"full outer join         " +
					"( select l.aams_location_id as LOCATION_ID_600,l.commercial_name AS LOCATION_NAME_600,met.code_id as CODE_ID_600,v.GS_VLT_CODE AS GS_VLT_ID_600, met.data AS DATA_600," +
					"        met.bet/100 AS BET_600,met.win/100 AS WIN_600,met.bet_num AS BET_NUM_600,met.tot_in/100 AS TOT_IN_600,met.tot_out/100 AS TOT_OUT_600 " +
					"		from birsaamsmeters met " +
					"       inner join birsvlt v on met.code_id=v.aams_vlt_id " +
					"  		inner join birslocation l on v.aams_location_id=l.aams_location_id " +
					"		where data between ? and ? ";
					if ((location_name != null && !location_name.equals(""))
							|| (aams_location_id != null && !aams_location_id
									.equals(""))) {

						sql = sql + " AND ";
						boolean and = false;
						if (location_name != null && !location_name.equals("")) {
							sql = sql + "lower(l.commercial_name) like lower('%"+ location_name +"%') ";
							and = true;
						}
						if (aams_location_id != null && !aams_location_id.equals("")) {
							if (and)
								sql = sql + " AND ";
							sql = sql + "lower(l.aams_location_id) like lower('%"+ aams_location_id +"%') ";
							and = false;
						}
					}
			sql=sql+") vista600 " +
					"on vistaSogei.SOGEI_aams_vlt_id = vista600.code_id_600 and vistaSogei.SOGEI_DATA=vista600.DATA_600 ";
					
					if(!mostraTutto){
						sql=sql+"where BET_600-SOGEI_BET<>0 OR WIN_600-SOGEI_WIN<>0 OR BET_NUM_600-SOGEI_GAMES_PLAYED<>0 OR TOT_IN_600-SOGEI_TOTAL_IN<>0 " +
								"      OR TOT_OUT_600-SOGEI_TOTAL_OUT<>0 OR SOGEI_AAMS_VLT_ID is null OR CODE_ID_600 is null ";
					}	
			
			//ORDINAMENTO
			sql=sql+ " order by sogei_location_name asc, sogei_data asc, location_name_600 asc, data_600 asc";
			
//			String ascDesc = null;
//			if (!isAscending)
//				ascDesc = " DESC ";
//			else if (isAscending)
//				ascDesc = " ASC ";
//			
//			
//			if (sortColumn!=null && sortColumn.equals("num_vlt"))
//				sortColumn=sortColumn+ascDesc+", data"+ascDesc+", commercial_name"+ascDesc+", code_id";
//			else if (sortColumn!=null && sortColumn.equals("data"))
//				sortColumn=sortColumn+ascDesc+", commercial_name"+ascDesc+", code_id";
//			else if (sortColumn!=null && sortColumn.equals("commercial_name"))
//				sortColumn=sortColumn+ascDesc+", data"+ascDesc+", code_id";
//			else if (sortColumn!=null && sortColumn.equals("code_id"))
//				sortColumn=sortColumn+ascDesc+", data";
//			else if (sortColumn!=null && sortColumn.equals("gs_vlt_code"))
//				sortColumn=sortColumn+ascDesc+", data";
//							
//			if(sortColumn!=null && !(sortColumn.equals("")))
//				sql=sql+" ORDER BY "+sortColumn+ascDesc;								
//			else if (sortColumn==null || sortColumn.equals(""))
//				sql=sql+" ORDER BY commercial_name ASC, code_id ASC, data ASC";		
			
						
			ps = connDatamart.prepareStatement(sql);
			
			java.sql.Date data1_ = new java.sql.Date(data1.getTime());
			java.sql.Date data2_ = new java.sql.Date(data2.getTime());
			
			ps.setDate(1, data1_);
			ps.setDate(2, data2_);
			ps.setDate(3, data1_);
			ps.setDate(4, data2_);
							
			rs = ps.executeQuery();
			
			lista_confronti = new ArrayList<Confronto>();
			
			while (rs.next()){
				Confronto conf = new Confronto();
				
				if (rs.getString("SOGEI_LOCATION_ID") != null)
					conf.setAams_location_id(rs.getString("SOGEI_LOCATION_ID"));
				else if (rs.getString("LOCATION_ID_600") != null)
					conf.setAams_location_id(rs.getString("LOCATION_ID_600"));
				else conf.setAams_location_id("");
				
				if (rs.getString("SOGEI_LOCATION_NAME") != null)
					conf.setLocation_name(rs.getString("SOGEI_LOCATION_NAME"));
				else if (rs.getString("LOCATION_NAME_600") != null)
					conf.setLocation_name(rs.getString("LOCATION_NAME_600"));
				else conf.setLocation_name("");
				
				if (rs.getString("SOGEI_AAMS_VLT_ID") != null)
					conf.setAams_vlt_id(rs.getString("SOGEI_AAMS_VLT_ID"));
				else if (rs.getString("CODE_ID_600") != null)
					conf.setAams_vlt_id(rs.getString("CODE_ID_600"));
				else conf.setAams_vlt_id("");
				
				if (rs.getString("SOGEI_DATA") != null)
					conf.setData(rs.getDate("SOGEI_DATA"));
				else if (rs.getString("DATA_600") != null)
					conf.setData(rs.getDate("DATA_600"));
				
				if(rs.getString("SOGEI_GS_VLT_ID")!=null)
					conf.setGs_vlt_id(rs.getString("SOGEI_GS_VLT_ID"));
				else if (rs.getString("GS_VLT_ID_600") != null)
					conf.setGs_vlt_id(rs.getString("GS_VLT_ID_600"));
				else conf.setGs_vlt_id("");
				
				
				MeterBean mbSogei = null;
				if (rs.getString("SOGEI_AAMS_VLT_ID") != null) {
					mbSogei = new MeterBean();
					mbSogei.setAams_location_id(rs.getString("SOGEI_LOCATION_ID"));
					mbSogei.setLocation_name(rs.getString("SOGEI_LOCATION_NAME"));
					mbSogei.setGs_vlt_id(rs.getString("SOGEI_GS_VLT_ID"));
					mbSogei.setAams_vlt_id(rs.getString("SOGEI_AAMS_VLT_ID"));
					mbSogei.setData(rs.getDate("SOGEI_DATA"));
					mbSogei.setBet(rs.getDouble("SOGEI_BET"));
					mbSogei.setWin(rs.getDouble("SOGEI_WIN"));
					mbSogei.setGamesPlayed(rs.getLong("SOGEI_GAMES_PLAYED"));
					mbSogei.setTotalIn(rs.getDouble("SOGEI_TOTAL_IN"));
					mbSogei.setTotalOut(rs.getDouble("SOGEI_TOTAL_OUT"));
				}
				
				MeterBean mb600 = null;				
				if (rs.getString("CODE_ID_600") != null) {
					mb600 = new MeterBean();
					mb600.setAams_location_id(rs.getString("LOCATION_ID_600"));
					mb600.setLocation_name(rs.getString("LOCATION_NAME_600"));
					mb600.setGs_vlt_id(rs.getString("GS_VLT_ID_600"));
					mb600.setAams_vlt_id(rs.getString("CODE_ID_600"));
					mb600.setData(rs.getDate("DATA_600"));
					mb600.setBet(rs.getDouble("BET_600"));
					mb600.setWin(rs.getDouble("WIN_600"));
					mb600.setGamesPlayed(rs.getLong("BET_NUM_600"));
					mb600.setTotalIn(rs.getDouble("TOT_IN_600"));
					mb600.setTotalOut(rs.getDouble("TOT_OUT_600"));
				}		
				conf.setSeicento(mb600);
				conf.setSogei(mbSogei);
				
				lista_confronti.add(conf);
			}
			
		} catch (SQLException e) {
			logger.error("retrieveConfronti600Sogei_vlt failed", e);
			e.printStackTrace();
			throw new DataAccessException(IErrorCodes.DB_ERROR_KEY, e.toString(), e);
		}
		catch (Exception e) {
			logger.error("retrieveConfronti600Sogei_vlt failed", e);
			e.printStackTrace();
			throw new DataAccessException(IErrorCodes.DB_ERROR_KEY, e.toString(), e);
		}
		finally{
				try {
					DBConnectionManager.CloseConnection(rs, ps, connDatamart);				
				} catch (DataAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					logger.error(e);
				}
		}
		return lista_confronti;
	}	


	public List<Confronto> old_retrieveConfronti600Sogei_locations(
			String location_name, String aams_location_id, String sortColumn,
			boolean isAscending, Date data1, Date data2, boolean mostraTutto)
			throws DataAccessException {

		List<Confronto> lista_confronti = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		Connection connDatamart = null;

		try {
			connDatamart = DBConnectionManager.DataMartConnectionFactory();

			String sql = "select vistaSogei.SOGEI_LOCATION_ID, vistaSogei.SOGEI_LOCATION_NAME, vistaSogei.SOGEI_NUM_VLT," +
					"       vistaSogei.SOGEI_BET, vistaSogei.SOGEI_WIN," +
					"        vistaSogei.SOGEI_GAMES_PLAYED,vistaSogei.SOGEI_TOTAL_IN,vistaSogei.SOGEI_TOTAL_OUT,vistaSogei.SOGEI_DATA," +
					"        vista600.LOCATION_ID_600,vista600.LOCATION_NAME_600,vista600.NUM_VLT_600,vista600.BET_600,vista600.WIN_600,vista600.BET_NUM_600," +
					"        vista600.TOT_IN_600,vista600.TOT_OUT_600,vista600.DATA_600 " +
					" from " +
					"      (select SOGEI_LOCATION_ID,SOGEI_LOCATION_NAME,count(AAMS_VLT_ID) AS SOGEI_NUM_VLT,sum(SOGEI_BET) as SOGEI_BET,sum(SOGEI_WIN) as SOGEI_WIN,sum(SOGEI_GAMES_PLAYED) as SOGEI_GAMES_PLAYED," +
					"          sum(SOGEI_TOTAL_IN) as SOGEI_TOTAL_IN,sum(SOGEI_TOTAL_OUT) as SOGEI_TOTAL_OUT, SOGEI_DATA " +
					"        from " +
					"            (select spaziodim.aams_location_id as SOGEI_LOCATION_ID,spaziodim.location_name AS SOGEI_LOCATION_NAME,spaziodim.AAMS_VLT_ID AS AAMS_VLT_ID," +
					"					          sum(metSogei.BET) as SOGEI_BET, sum(metSogei.WIN) as SOGEI_WIN, sum(metSogei.GAMES_PLAYED) as SOGEI_GAMES_PLAYED, " +
					"                    max(metSogei.TOTAL_IN) as SOGEI_TOTAL_IN, max(metSogei.TOTAL_OUT) as SOGEI_TOTAL_OUT, " +
					"                    tempodim.DATA as SOGEI_DATA 		" +
					"            from METERFACT metSogei " +
					"               inner join TEMPODIM tempodim on metSogei.DMTEMPO_ID=tempodim.ID " +
					"               inner join SPAZIODIM spaziodim on metSogei.DMSPAZIO_ID=spaziodim.ID " +
					"            where tempodim.DATA between ? and ?";
			if ((location_name != null && !location_name.equals(""))
					|| (aams_location_id != null && !aams_location_id
							.equals(""))) {

				sql = sql + " AND ";
				boolean and = false;
				if (location_name != null && !location_name.equals("")) {
					sql = sql + "lower(spaziodim.LOCATION_NAME) like lower('%"
							+ location_name + "%') ";
					and = true;
				}
				if (aams_location_id != null && !aams_location_id.equals("")) {
					if (and)
						sql = sql + " AND ";
					sql = sql
							+ "lower(spaziodim.AAMS_LOCATION_ID) like lower('%"
							+ aams_location_id + "%') ";
					and = false;
				}
			}
			sql = sql
					+ "            group by spaziodim.aams_location_id, spaziodim.location_name, spaziodim.AAMS_VLT_ID, tempodim.DATA) " +
							"        group by SOGEI_LOCATION_ID, SOGEI_LOCATION_NAME, SOGEI_DATA    " +
							"        ) vistaSogei        " +
							"  full outer join         " +
							"        ( select l.aams_location_id as LOCATION_ID_600,l.commercial_name AS LOCATION_NAME_600, count(met.code_id) AS NUM_VLT_600, met.data AS DATA_600," +
							"					        sum(met.bet/100) AS BET_600,sum(met.win/100) AS WIN_600,sum(met.bet_num) AS BET_NUM_600,sum(met.tot_in/100) AS TOT_IN_600,sum(met.tot_out/100) AS TOT_OUT_600 " +
							"          from birsaamsmeters met " +
							"              inner join birsvlt v on met.code_id=v.aams_vlt_id " +
							"              inner join birslocation l on v.aams_location_id=l.aams_location_id " +
							"          where data between ? and ? ";
			if ((location_name != null && !location_name.equals(""))
					|| (aams_location_id != null && !aams_location_id
							.equals(""))) {

				sql = sql + " AND ";
				boolean and = false;
				if (location_name != null && !location_name.equals("")) {
					sql = sql + "lower(l.commercial_name) like lower('%"
							+ location_name + "%') ";
					and = true;
				}
				if (aams_location_id != null && !aams_location_id.equals("")) {
					if (and)
						sql = sql + " AND ";
					sql = sql + "lower(l.aams_location_id) like lower('%"
							+ aams_location_id + "%') ";
					and = false;
				}
			}
			sql = sql + " group by l.aams_location_id, l.commercial_name, met.data "
					+ ") vista600 "
					+ " on vistaSogei.SOGEI_LOCATION_ID = vista600.LOCATION_ID_600 and vistaSogei.SOGEI_DATA=vista600.DATA_600 ";

			if (!mostraTutto) {
				sql = sql
						+ "where NUM_VLT_600<>SOGEI_NUM_VLT OR BET_600-SOGEI_BET<>0 OR WIN_600-SOGEI_WIN<>0 OR BET_NUM_600-SOGEI_GAMES_PLAYED<>0 OR TOT_IN_600-SOGEI_TOTAL_IN<>0 "
						+ "      OR TOT_OUT_600-SOGEI_TOTAL_OUT<>0 OR SOGEI_LOCATION_ID is null OR LOCATION_ID_600 is null ";
			}

			// ORDINAMENTO
			sql = sql
					+ " order by sogei_location_name asc, sogei_data asc, location_name_600 asc, data_600 asc";

			// String ascDesc = null;
			// if (!isAscending)
			// ascDesc = " DESC ";
			// else if (isAscending)
			// ascDesc = " ASC ";
			//
			//
			// if (sortColumn!=null && sortColumn.equals("num_vlt"))
			// sortColumn=sortColumn+ascDesc+", data"+ascDesc+", commercial_name"+ascDesc+", code_id";
			// else if (sortColumn!=null && sortColumn.equals("data"))
			// sortColumn=sortColumn+ascDesc+", commercial_name"+ascDesc+", code_id";
			// else if (sortColumn!=null &&
			// sortColumn.equals("commercial_name"))
			// sortColumn=sortColumn+ascDesc+", data"+ascDesc+", code_id";
			// else if (sortColumn!=null && sortColumn.equals("code_id"))
			// sortColumn=sortColumn+ascDesc+", data";
			// else if (sortColumn!=null && sortColumn.equals("gs_vlt_code"))
			// sortColumn=sortColumn+ascDesc+", data";
			//
			// if(sortColumn!=null && !(sortColumn.equals("")))
			// sql=sql+" ORDER BY "+sortColumn+ascDesc;
			// else if (sortColumn==null || sortColumn.equals(""))
			// sql=sql+" ORDER BY commercial_name ASC, code_id ASC, data ASC";

			ps = connDatamart.prepareStatement(sql);

			java.sql.Date data1_ = new java.sql.Date(data1.getTime());
			java.sql.Date data2_ = new java.sql.Date(data2.getTime());

			ps.setDate(1, data1_);
			ps.setDate(2, data2_);
			ps.setDate(3, data1_);
			ps.setDate(4, data2_);

			rs = ps.executeQuery();

			lista_confronti = new ArrayList<Confronto>();

			while (rs.next()) {
				Confronto conf = new Confronto();

				if (rs.getString("SOGEI_LOCATION_ID") != null)
					conf.setAams_location_id(rs.getString("SOGEI_LOCATION_ID"));
				else if (rs.getString("LOCATION_ID_600") != null)
					conf.setAams_location_id(rs.getString("LOCATION_ID_600"));
				else
					conf.setAams_location_id("");

				if (rs.getString("SOGEI_LOCATION_NAME") != null)
					conf.setLocation_name(rs.getString("SOGEI_LOCATION_NAME"));
				else if (rs.getString("LOCATION_NAME_600") != null)
					conf.setLocation_name(rs.getString("LOCATION_NAME_600"));
				else
					conf.setLocation_name("");

				

				if (rs.getString("SOGEI_DATA") != null)
					conf.setData(rs.getDate("SOGEI_DATA"));
				else if (rs.getString("DATA_600") != null)
					conf.setData(rs.getDate("DATA_600"));

				

				MeterBean mbSogei = null;
				if (rs.getString("SOGEI_LOCATION_ID") != null) {
					mbSogei = new MeterBean();
					mbSogei.setAams_location_id(rs.getString("SOGEI_LOCATION_ID"));
					mbSogei.setLocation_name(rs.getString("SOGEI_LOCATION_NAME"));
					mbSogei.setNumVlt(rs.getInt("SOGEI_NUM_VLT"));
					mbSogei.setData(rs.getDate("SOGEI_DATA"));
					mbSogei.setBet(rs.getDouble("SOGEI_BET"));
					mbSogei.setWin(rs.getDouble("SOGEI_WIN"));
					mbSogei.setGamesPlayed(rs.getLong("SOGEI_GAMES_PLAYED"));
					mbSogei.setTotalIn(rs.getDouble("SOGEI_TOTAL_IN"));
					mbSogei.setTotalOut(rs.getDouble("SOGEI_TOTAL_OUT"));
				}

				MeterBean mb600 = null;
				if (rs.getString("LOCATION_ID_600") != null) {
					mb600 = new MeterBean();
					mb600.setAams_location_id(rs.getString("LOCATION_ID_600"));
					mb600.setLocation_name(rs.getString("LOCATION_NAME_600"));
					mb600.setNumVlt(rs.getInt("NUM_VLT_600"));
					mb600.setData(rs.getDate("DATA_600"));
					mb600.setBet(rs.getDouble("BET_600"));
					mb600.setWin(rs.getDouble("WIN_600"));
					mb600.setGamesPlayed(rs.getLong("BET_NUM_600"));
					mb600.setTotalIn(rs.getDouble("TOT_IN_600"));
					mb600.setTotalOut(rs.getDouble("TOT_OUT_600"));
				}
				conf.setSeicento(mb600);
				conf.setSogei(mbSogei);

				lista_confronti.add(conf);
			}

		} catch (SQLException e) {
			logger.error("retrieveConfronti600Sogei_locations failed", e);
			e.printStackTrace();
			throw new DataAccessException(IErrorCodes.DB_ERROR_KEY,
					e.toString(), e);
		} catch (Exception e) {
			logger.error("retrieveConfronti600Sogei_locations failed", e);
			e.printStackTrace();
			throw new DataAccessException(IErrorCodes.DB_ERROR_KEY,
					e.toString(), e);
		} finally {
			try {
				DBConnectionManager.CloseConnection(rs, ps, connDatamart);
			} catch (DataAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.error(e);
			}
		}
		return lista_confronti;
	}

	
	
	public Map<String,String> inQualeLocation(String code_id, Date data,Connection connStaging)  throws DataAccessException {

		Map<String,String> risultato = null;
		ResultSet rs=null;
		PreparedStatement ps=null;
			
		
		try {
			DateFormat df = new SimpleDateFormat(IConstants.DATE_FORMAT_4);
			
			
			String sql = "select vh.*,l.commercial_name,vl.gs_vlt_code " +
					" from (select * from " +
					"		(select aams_vlt_id, date_change, id_aams_location " +
					"		  from vlthistory " +
					"         where aams_vlt_id= ? " +
					"                and date_change<= TO_DATE('"+df.format(DateUtil.ventitreCinquantanove(data))+"','"+IConstants.DATE_FORMAT_3 +"')"+
					"          order by date_change desc) where rownum=1) vh   " +
					" inner join birslocation l on l.aams_location_id = vh.id_aams_location " +
					" inner join birsvlt vl on vl.aams_vlt_id = vh.aams_vlt_id ";
			
			
			ps = connStaging.prepareStatement(sql);
			
	//		data = DateUtil.calcolaGiornoSuccessivo(data);
			
			
			//java.sql.Date data_ = new java.sql.Date(data.getTime());
						
			ps.setString(1, code_id);
			//ps.setDate(2, data_);
							
			rs = ps.executeQuery();
			
			risultato = new HashMap<String,String>();
			
			while (rs.next()){
				risultato.put("aams_vlt_id",rs.getString("aams_vlt_id"));
				risultato.put("gs_vlt_code",rs.getString("gs_vlt_code"));
				risultato.put("aams_location_id",rs.getString("id_aams_location"));
				risultato.put("location_name",rs.getString("commercial_name"));
				risultato.put("data",rs.getDate("date_change").toString());
			}
			
		} catch (SQLException e) {
			logger.error("inQualeLocation failed", e);
			e.printStackTrace();
			throw new DataAccessException(IErrorCodes.DB_ERROR_KEY, e.toString(), e);
		}
		catch (Exception e) {
			logger.error("inQualeLocation failed", e);
			e.printStackTrace();
			throw new DataAccessException(IErrorCodes.DB_ERROR_KEY, e.toString(), e);
		}
		finally{
				try {
					DBConnectionManager.CloseStatement(rs, ps);				
				} catch (DataAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					logger.error(e);
				}
		}
		return risultato;
	}	
	
	
	
	
	
	public List<Confronto> retrieveConfronti600Sogei_vlt(String location_name, String aams_location_id, String aams_vlt_id,
			String sortColumn,boolean isAscending,Date data1,Date data2, boolean mostraTutto)  throws DataAccessException {

		List<Confronto> lista_confronti = null;
		ResultSet rs=null;
		PreparedStatement ps=null;
		Connection connDatamart=null;
		Connection connStaging=null;	
		
		try {
			connDatamart=DBConnectionManager.DataMartConnectionFactory();
			
		
			String query_sogei = "select spaziodim.aams_location_id as SOGEI_LOCATION_ID,spaziodim.location_name AS SOGEI_LOCATION_NAME," +
					"          sum(metSogei.BET) as SOGEI_BET, sum(metSogei.WIN) as SOGEI_WIN, sum(metSogei.GAMES_PLAYED) as SOGEI_GAMES_PLAYED, " +
					"			sum(distinct(metSogei.TOTAL_IN)) as SOGEI_TOTAL_IN, sum(distinct(metSogei.TOTAL_OUT)) as SOGEI_TOTAL_OUT," +
					"			spaziodim.AAMS_VLT_ID as SOGEI_AAMS_VLT_ID,spaziodim.GS_VLT_ID as SOGEI_GS_VLT_ID, tempodim.DATA as SOGEI_DATA 		" +
					"			from METERFACT metSogei " +
					"			inner join TEMPODIM tempodim on metSogei.DMTEMPO_ID=tempodim.ID " +
					"			inner join SPAZIODIM spaziodim on metSogei.DMSPAZIO_ID=spaziodim.ID " +
					"			where tempodim.DATA between ? and ? ";
					if ((location_name != null && !location_name.equals(""))
							|| (aams_location_id != null && !aams_location_id.equals(""))
							|| (aams_vlt_id != null && !aams_vlt_id.equals(""))) {

						query_sogei = query_sogei + " AND ";
						boolean and = false;
						if (location_name != null && !location_name.equals("")) {
							query_sogei = query_sogei + "lower(spaziodim.LOCATION_NAME) like lower('%"+ location_name +"%') ";
							and = true;
						}
						if (aams_location_id != null && !aams_location_id.equals("")) {
							if (and)
								query_sogei = query_sogei + " AND ";
							query_sogei = query_sogei + "lower(spaziodim.AAMS_LOCATION_ID) like lower('%"+ aams_location_id +"%') ";
							and = true;
						}
						if (aams_vlt_id != null && !aams_vlt_id.equals("")) {
							if (and)
								query_sogei = query_sogei + " AND ";
							query_sogei = query_sogei + "lower(spaziodim.AAMS_VLT_ID) like lower('%"+ aams_vlt_id +"%') ";
							and = false;
						}
					}				
			query_sogei=query_sogei+"          group by spaziodim.aams_location_id, spaziodim.location_name, spaziodim.AAMS_VLT_ID, spaziodim.GS_VLT_ID, tempodim.DATA " +
									" order by spaziodim.location_name, spaziodim.aams_location_id, spaziodim.AAMS_VLT_ID, spaziodim.GS_VLT_ID, tempodim.DATA ";

			
			
						
			ps = connDatamart.prepareStatement(query_sogei);
			
			java.sql.Date data1_ = new java.sql.Date(data1.getTime());
			java.sql.Date data2_ = new java.sql.Date(data2.getTime());
			
			ps.setDate(1, data1_);
			ps.setDate(2, data2_);
							
			rs = ps.executeQuery();
			
			List<MeterBean> lista_sogei = new ArrayList<MeterBean>();
			while (rs.next()){
				MeterBean mbSogei = new MeterBean();
				mbSogei.setAams_location_id(rs.getString("SOGEI_LOCATION_ID"));
				mbSogei.setLocation_name(rs.getString("SOGEI_LOCATION_NAME"));
				mbSogei.setGs_vlt_id(rs.getString("SOGEI_GS_VLT_ID"));
				mbSogei.setAams_vlt_id(rs.getString("SOGEI_AAMS_VLT_ID"));
				mbSogei.setData(rs.getDate("SOGEI_DATA"));
				mbSogei.setBet(rs.getDouble("SOGEI_BET"));
				mbSogei.setWin(rs.getDouble("SOGEI_WIN"));
				mbSogei.setGamesPlayed(rs.getLong("SOGEI_GAMES_PLAYED"));
				mbSogei.setTotalIn(rs.getDouble("SOGEI_TOTAL_IN"));
				mbSogei.setTotalOut(rs.getDouble("SOGEI_TOTAL_OUT"));
				
				lista_sogei.add(mbSogei);
			}
			
			
			String query_600 = "select distinct gs.aams_location_id as AAMS_LOCATION_ID,loc.commercial_name as COMMERCIAL_NAME, met.code_id as CODE_ID_600, met.data AS DATA_600," +
			"        met.bet/100 AS BET_600,met.win/100 AS WIN_600,met.bet_num AS BET_NUM_600,met.tot_in/100 AS TOT_IN_600,met.tot_out/100 AS TOT_OUT_600 " +
			"		from birsaamsmeters met " +
			"		full outer join birsgsmeters gs on met.code_id = gs.aams_vlt_code and met.data = gs.data " +
			"		full outer join birslocation loc on gs.aams_location_id = loc.aams_location_id " +
			"		where met.data between ? and ? ";
			if ((location_name != null && !location_name.equals(""))
					|| (aams_location_id != null && !aams_location_id.equals(""))
					|| (aams_vlt_id != null && !aams_vlt_id.equals(""))) {

				query_600 = query_600 + " AND ";
				boolean and = false;
				if (location_name != null && !location_name.equals("")) {
					query_600 = query_600 + "lower(loc.commercial_name) like lower('%"+ location_name +"%') ";
					and = true;
				}
				if (aams_location_id != null && !aams_location_id.equals("")) {
					if (and)
						query_600 = query_600 + " AND ";
					query_600 = query_600 + "lower(gs.aams_location_id) like lower('%"+ aams_location_id +"%') ";
					and = true;
				}
				if (aams_vlt_id != null && !aams_vlt_id.equals("")) {
					if (and)
						query_600 = query_600 + " AND ";
					query_600 = query_600 + "lower(met.code_id) like lower('%"+ aams_vlt_id +"%') ";
					and = false;
				}
			}				
						
			connStaging=DBConnectionManager.StagingConnectionFactory();
			ps = connStaging.prepareStatement(query_600);
			
			ps.setDate(1, data1_);
			ps.setDate(2, data2_);
			
			//logger.info(query_600);
							
			rs = ps.executeQuery();
			
			Map<String,MeterBean> mappa_600 = new HashMap<String,MeterBean>();			
			
			while (rs.next()) {
//				Map<String,String> actual;			
//				if (rs.getDouble("BET_600")>0){
//					actual = inQualeLocation(rs.getString("CODE_ID_600"),DateUtil.calcolaGiornoPrecedente(rs.getDate("DATA_600")),connStaging);
//					if (actual.get("aams_location_id")==null)
//						actual = inQualeLocation(rs.getString("CODE_ID_600"),rs.getDate("DATA_600"),connStaging);
//				}
//				else 
//					actual = inQualeLocation(rs.getString("CODE_ID_600"),rs.getDate("DATA_600"),connStaging);
//				
//				if ((location_name != null && !location_name.equals(""))
//						|| (aams_location_id != null && !aams_location_id.equals(""))) {
//															
//					if (aams_location_id != null && !aams_location_id.equals("") && 
//							(actual.get("aams_location_id").equals(aams_location_id.toUpperCase()) 
//									|| actual.get("aams_location_id").contains(aams_location_id.toUpperCase()))){
//							MeterBean mb600 = new MeterBean();
//							mb600.setAams_location_id(actual.get("aams_location_id"));
//							mb600.setLocation_name(actual.get("location_name"));
//							mb600.setGs_vlt_id(actual.get("gs_vlt_code"));
//							mb600.setAams_vlt_id(actual.get("aams_vlt_id"));
//							mb600.setData(rs.getDate("DATA_600"));
//							mb600.setBet(rs.getDouble("BET_600"));
//							mb600.setWin(rs.getDouble("WIN_600"));
//							mb600.setGamesPlayed(rs.getLong("BET_NUM_600"));
//							mb600.setTotalIn(rs.getDouble("TOT_IN_600"));
//							mb600.setTotalOut(rs.getDouble("TOT_OUT_600"));
//
//							mappa_600.put(mb600.getAams_vlt_id()+"-"+mb600.getData(), mb600);						
//					} else if (location_name != null && !location_name.equals("") && 
//							(actual.get("location_name")!=null && (actual.get("location_name").contains(location_name.toUpperCase()) 
//									|| actual.get("location_name").contains(location_name.toUpperCase())))){
//							MeterBean mb600 = new MeterBean();
//							mb600.setAams_location_id(actual.get("aams_location_id"));
//							mb600.setLocation_name(actual.get("location_name"));
//							mb600.setGs_vlt_id(actual.get("gs_vlt_code"));
//							mb600.setAams_vlt_id(actual.get("aams_vlt_id"));
//							mb600.setData(rs.getDate("DATA_600"));
//							mb600.setBet(rs.getDouble("BET_600"));
//							mb600.setWin(rs.getDouble("WIN_600"));
//							mb600.setGamesPlayed(rs.getLong("BET_NUM_600"));
//							mb600.setTotalIn(rs.getDouble("TOT_IN_600"));
//							mb600.setTotalOut(rs.getDouble("TOT_OUT_600"));
//
//							mappa_600.put(mb600.getAams_vlt_id()+"-"+mb600.getData(), mb600);						
//					} 
//					
//				} else {
					MeterBean mb600 = new MeterBean();
					mb600.setAams_location_id(rs.getString("aams_location_id"));
					mb600.setLocation_name(rs.getString("COMMERCIAL_NAME"));
//					mb600.setGs_vlt_id(actual.get("gs_vlt_code"));
					mb600.setAams_vlt_id(rs.getString("CODE_ID_600"));
					mb600.setData(rs.getDate("DATA_600"));
					mb600.setBet(rs.getDouble("BET_600"));
					mb600.setWin(rs.getDouble("WIN_600"));
					mb600.setGamesPlayed(rs.getLong("BET_NUM_600"));
					mb600.setTotalIn(rs.getDouble("TOT_IN_600"));
					mb600.setTotalOut(rs.getDouble("TOT_OUT_600"));

					mappa_600.put(mb600.getAams_vlt_id()+"-"+mb600.getData(), mb600);
//				}
			}
						
			//CON QUESTO CICLO FOR TROVO LE CORRISPONDENZE TRA SOGEI E 600 PER CREARE I CONFRONTI
			//SE UN SOGEI TROVA IL 600 CORRISPONDENTE, QUEST'ULTIMO VIENE ELIMINATO DALLA MAPPA
			lista_confronti = new ArrayList<Confronto>();	
			for (MeterBean meter_sogei : lista_sogei){
				if (mappa_600.containsKey(meter_sogei.getAams_vlt_id()+"-"+meter_sogei.getData())){
					if (mostraTutto){
						Confronto conf = new Confronto();
						
						conf.setAams_vlt_id(meter_sogei.getAams_vlt_id());
						conf.setAams_location_id(meter_sogei.getAams_location_id());
						conf.setLocation_name(meter_sogei.getLocation_name());
						conf.setGs_vlt_id(meter_sogei.getGs_vlt_id());
						conf.setData(meter_sogei.getData());
						
						conf.setSogei(meter_sogei);
						conf.setSeicento(mappa_600.get(meter_sogei.getAams_vlt_id()+"-"+meter_sogei.getData()));
						
						lista_confronti.add(conf);
					}
					else if ((meter_sogei.getBet()>0 && !mappa_600.get(meter_sogei.getAams_vlt_id()+"-"+meter_sogei.getData()).getAams_location_id().equals(meter_sogei.getAams_location_id()))
						||	mappa_600.get(meter_sogei.getAams_vlt_id()+"-"+meter_sogei.getData()).getBet()-meter_sogei.getBet()!=0 
						|| mappa_600.get(meter_sogei.getAams_vlt_id()+"-"+meter_sogei.getData()).getWin()-meter_sogei.getWin()!=0
						|| mappa_600.get(meter_sogei.getAams_vlt_id()+"-"+meter_sogei.getData()).getGamesPlayed()-meter_sogei.getGamesPlayed()!=0	
						|| mappa_600.get(meter_sogei.getAams_vlt_id()+"-"+meter_sogei.getData()).getTotalIn()-meter_sogei.getTotalIn()!=0
						|| mappa_600.get(meter_sogei.getAams_vlt_id()+"-"+meter_sogei.getData()).getTotalOut()-meter_sogei.getTotalOut()!=0){
						
						Confronto conf = new Confronto();
						
						conf.setAams_vlt_id(meter_sogei.getAams_vlt_id());
						if (!mappa_600.get(meter_sogei.getAams_vlt_id()+"-"+meter_sogei.getData()).getAams_location_id().equals(meter_sogei.getAams_location_id())){
							conf.setAams_location_id(mappa_600.get(meter_sogei.getAams_vlt_id()+"-"+meter_sogei.getData()).getAams_location_id()+" - "+meter_sogei.getAams_location_id());
							conf.setLocation_name(mappa_600.get(meter_sogei.getAams_vlt_id()+"-"+meter_sogei.getData()).getLocation_name()+" - "+meter_sogei.getLocation_name());
						}
						else {
							conf.setAams_location_id(meter_sogei.getAams_location_id());
							conf.setLocation_name(meter_sogei.getLocation_name());
						}
						conf.setGs_vlt_id(meter_sogei.getGs_vlt_id());
						conf.setData(meter_sogei.getData());
						
						conf.setSogei(meter_sogei);
						conf.setSeicento(mappa_600.get(meter_sogei.getAams_vlt_id()+"-"+meter_sogei.getData()));
						
						lista_confronti.add(conf);
					}
					mappa_600.remove(meter_sogei.getAams_vlt_id()+"-"+meter_sogei.getData());	
					
				} else {
					if (meter_sogei.getBet()!=0 ||
							meter_sogei.getWin()!=0 ||
							meter_sogei.getGamesPlayed()!=0 || 
							meter_sogei.getTotalIn()!=0 || 
							meter_sogei.getTotalOut()!=0)
					{
						
					
						Confronto conf = new Confronto();
					
						conf.setAams_vlt_id(meter_sogei.getAams_vlt_id());
						conf.setAams_location_id(meter_sogei.getAams_location_id());
						conf.setLocation_name(meter_sogei.getLocation_name());
						conf.setGs_vlt_id(meter_sogei.getGs_vlt_id());
						conf.setData(meter_sogei.getData());
					
						conf.setSogei(meter_sogei);
						conf.setSeicento(null);
					
						lista_confronti.add(conf);
					}
				}							
			}
			
			//CON QUESTO CICLO FOR SCANDISCO LA MAPPA CON I 600 RIMANENTI (QUELLI CHE NON AVEVANO UN SOGEI CORRISPONDENTE)
			Set<String> keySet_mappa = mappa_600.keySet();
			for (String vlt_id_data : keySet_mappa){
				
				MeterBean meter_600 = mappa_600.get(vlt_id_data);
				if(meter_600.getBet()!=0 || 
						meter_600.getWin()!=0 || 
						meter_600.getGamesPlayed()!=0 || 
						meter_600.getTotalIn()!=0 ||
						meter_600.getTotalOut()!=0)
				{
					Confronto conf = new Confronto();
					
					conf.setAams_vlt_id(meter_600.getAams_vlt_id());
					conf.setAams_location_id(meter_600.getAams_location_id());
//					conf.setLocation_name(meter_600.getLocation_name());
					conf.setGs_vlt_id(meter_600.getGs_vlt_id());
					conf.setData(meter_600.getData());
					
					conf.setSeicento(meter_600);
					conf.setSogei(null);
					
					lista_confronti.add(conf);
				}			
			}
			
		} catch (SQLException e) {
			logger.error("retrieveConfronti600Sogei_vlt failed", e);
			e.printStackTrace();
			throw new DataAccessException(IErrorCodes.DB_ERROR_KEY, e.toString(), e);
		}
		catch (Exception e) {
			logger.error("retrieveConfronti600Sogei_vlt failed", e);
			e.printStackTrace();
			throw new DataAccessException(IErrorCodes.DB_ERROR_KEY, e.toString(), e);
		}
		finally{
				try {
					DBConnectionManager.CloseConnection(rs, ps, connDatamart);	
					DBConnectionManager.CloseConnection(connStaging);
				} catch (DataAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					logger.error(e);
				}
		}
		return lista_confronti;
	}	
	
	
	
	public List<Confronto> new_old_retrieveConfronti600Sogei_locations(String location_name, String aams_location_id,String sortColumn, 
			boolean isAscending,Date data1,Date data2, boolean mostraTutto)  throws DataAccessException {

		List<Confronto> lista_confronti = null;
		ResultSet rs=null;
		PreparedStatement ps=null;
		Connection connDatamart=null;
		Connection connStaging=null;	
		
		try {
			connDatamart=DBConnectionManager.DataMartConnectionFactory();
			
		
			String query_sogei = "select spaziodim.aams_location_id as SOGEI_LOCATION_ID,spaziodim.location_name AS SOGEI_LOCATION_NAME," +
					"          sum(metSogei.BET) as SOGEI_BET, sum(metSogei.WIN) as SOGEI_WIN, sum(metSogei.GAMES_PLAYED) as SOGEI_GAMES_PLAYED, " +
					"			max(metSogei.TOTAL_IN) as SOGEI_TOTAL_IN, max(metSogei.TOTAL_OUT) as SOGEI_TOTAL_OUT," +
					"			spaziodim.AAMS_VLT_ID as SOGEI_AAMS_VLT_ID,spaziodim.GS_VLT_ID as SOGEI_GS_VLT_ID, tempodim.DATA as SOGEI_DATA 		" +
					"			from METERFACT metSogei " +
					"			inner join TEMPODIM tempodim on metSogei.DMTEMPO_ID=tempodim.ID " +
					"			inner join SPAZIODIM spaziodim on metSogei.DMSPAZIO_ID=spaziodim.ID " +
					"			where tempodim.DATA between ? and ? ";
					if ((location_name != null && !location_name.equals(""))
							|| (aams_location_id != null && !aams_location_id
									.equals(""))) {

						query_sogei = query_sogei + " AND ";
						boolean and = false;
						if (location_name != null && !location_name.equals("")) {
							query_sogei = query_sogei + "lower(spaziodim.LOCATION_NAME) like lower('%"+ location_name +"%') ";
							and = true;
						}
						if (aams_location_id != null && !aams_location_id.equals("")) {
							if (and)
							query_sogei = query_sogei + " AND ";
							query_sogei = query_sogei + "lower(spaziodim.AAMS_LOCATION_ID) like lower('%"+ aams_location_id +"%') ";
							and = false;
						}
					}				
			query_sogei=query_sogei+"          group by spaziodim.aams_location_id, spaziodim.location_name, spaziodim.AAMS_VLT_ID, spaziodim.GS_VLT_ID, tempodim.DATA " +
									" order by spaziodim.location_name, spaziodim.aams_location_id, spaziodim.AAMS_VLT_ID, spaziodim.GS_VLT_ID, tempodim.DATA ";

			
			
						
			ps = connDatamart.prepareStatement(query_sogei);
			
			java.sql.Date data1_ = new java.sql.Date(data1.getTime());
			java.sql.Date data2_ = new java.sql.Date(data2.getTime());
			
			ps.setDate(1, data1_);
			ps.setDate(2, data2_);
							
			rs = ps.executeQuery();
			
			List<MeterBean> lista_sogei = new ArrayList<MeterBean>();
			while (rs.next()){
				MeterBean mbSogei = new MeterBean();
				mbSogei.setAams_location_id(rs.getString("SOGEI_LOCATION_ID"));
				mbSogei.setLocation_name(rs.getString("SOGEI_LOCATION_NAME"));
				mbSogei.setGs_vlt_id(rs.getString("SOGEI_GS_VLT_ID"));
				mbSogei.setAams_vlt_id(rs.getString("SOGEI_AAMS_VLT_ID"));
				mbSogei.setData(rs.getDate("SOGEI_DATA"));
				mbSogei.setBet(rs.getDouble("SOGEI_BET"));
				mbSogei.setWin(rs.getDouble("SOGEI_WIN"));
				mbSogei.setGamesPlayed(rs.getLong("SOGEI_GAMES_PLAYED"));
				mbSogei.setTotalIn(rs.getDouble("SOGEI_TOTAL_IN"));
				mbSogei.setTotalOut(rs.getDouble("SOGEI_TOTAL_OUT"));
				
				lista_sogei.add(mbSogei);
			}
			
			
			String query_600 = "select met.code_id as CODE_ID_600, met.data AS DATA_600," +
			"        met.bet/100 AS BET_600,met.win/100 AS WIN_600,met.bet_num AS BET_NUM_600,met.tot_in/100 AS TOT_IN_600,met.tot_out/100 AS TOT_OUT_600 " +
			"		from birsaamsmeters met " +
			"		where data between ? and ? ";
						
			ps = connDatamart.prepareStatement(query_600);
			
			ps.setDate(1, data1_);
			ps.setDate(2, data2_);
							
			rs = ps.executeQuery();
			
			Map<String,MeterBean> mappa_600 = new HashMap<String,MeterBean>();
			
			connStaging=DBConnectionManager.StagingConnectionFactory();
			
			while (rs.next()) {
				Map<String,String> actual = inQualeLocation(rs.getString("CODE_ID_600"),rs.getDate("DATA_600"),connStaging);
				
				if ((location_name != null && !location_name.equals(""))
						|| (aams_location_id != null && !aams_location_id.equals(""))) {
															
					if (aams_location_id != null && !aams_location_id.equals("") && 
							(actual.get("aams_location_id").equals(aams_location_id.toUpperCase()) 
									|| actual.get("aams_location_id").contains(aams_location_id.toUpperCase()))){
							MeterBean mb600 = new MeterBean();
							mb600.setAams_location_id(actual.get("aams_location_id"));
							mb600.setLocation_name(actual.get("location_name"));
							mb600.setGs_vlt_id(actual.get("gs_vlt_code"));
							mb600.setAams_vlt_id(actual.get("aams_vlt_id"));
							mb600.setData(rs.getDate("DATA_600"));
							mb600.setBet(rs.getDouble("BET_600"));
							mb600.setWin(rs.getDouble("WIN_600"));
							mb600.setGamesPlayed(rs.getLong("BET_NUM_600"));
							mb600.setTotalIn(rs.getDouble("TOT_IN_600"));
							mb600.setTotalOut(rs.getDouble("TOT_OUT_600"));

							mappa_600.put(mb600.getAams_vlt_id()+"-"+mb600.getData(), mb600);						
					} else if (location_name != null && !location_name.equals("") && 
							(actual.get("location_name").equals(location_name.toUpperCase()) 
									|| actual.get("location_name").contains(location_name.toUpperCase()))){
							MeterBean mb600 = new MeterBean();
							mb600.setAams_location_id(actual.get("aams_location_id"));
							mb600.setLocation_name(actual.get("location_name"));
							mb600.setGs_vlt_id(actual.get("gs_vlt_code"));
							mb600.setAams_vlt_id(actual.get("aams_vlt_id"));
							mb600.setData(rs.getDate("DATA_600"));
							mb600.setBet(rs.getDouble("BET_600"));
							mb600.setWin(rs.getDouble("WIN_600"));
							mb600.setGamesPlayed(rs.getLong("BET_NUM_600"));
							mb600.setTotalIn(rs.getDouble("TOT_IN_600"));
							mb600.setTotalOut(rs.getDouble("TOT_OUT_600"));

							mappa_600.put(mb600.getAams_vlt_id()+"-"+mb600.getData(), mb600);						
					} 
					
				} else {
					MeterBean mb600 = new MeterBean();
					mb600.setAams_location_id(actual.get("aams_location_id"));
					mb600.setLocation_name(actual.get("location_name"));
					mb600.setGs_vlt_id(actual.get("gs_vlt_code"));
					mb600.setAams_vlt_id(actual.get("aams_vlt_id"));
					mb600.setData(rs.getDate("DATA_600"));
					mb600.setBet(rs.getDouble("BET_600"));
					mb600.setWin(rs.getDouble("WIN_600"));
					mb600.setGamesPlayed(rs.getLong("BET_NUM_600"));
					mb600.setTotalIn(rs.getDouble("TOT_IN_600"));
					mb600.setTotalOut(rs.getDouble("TOT_OUT_600"));

					mappa_600.put(mb600.getAams_vlt_id()+"-"+mb600.getData(), mb600);
				}
			}
						
			//CON QUESTO CICLO FOR TROVO LE CORRISPONDENZE TRA SOGEI E 600 PER CREARE I CONFRONTI
			//SE UN SOGEI TROVA IL 600 CORRISPONDENTE, QUEST'ULTIMO VIENE ELIMINATO DALLA MAPPA
			lista_confronti = new ArrayList<Confronto>();
			
			for (MeterBean meter_sogei : lista_sogei){
				if (mappa_600.containsKey(meter_sogei.getAams_vlt_id()+"-"+meter_sogei.getData())){
					if (mostraTutto){
						Confronto conf = new Confronto();
						
						conf.setAams_vlt_id(meter_sogei.getAams_vlt_id());
						conf.setAams_location_id(meter_sogei.getAams_location_id());
						conf.setLocation_name(meter_sogei.getLocation_name());
						conf.setGs_vlt_id(meter_sogei.getGs_vlt_id());
						conf.setData(meter_sogei.getData());
						
						conf.setSogei(meter_sogei);
						conf.setSeicento(mappa_600.get(meter_sogei.getAams_vlt_id()+"-"+meter_sogei.getData()));
						
						lista_confronti.add(conf);
					}
					else if (mappa_600.get(meter_sogei.getAams_vlt_id()+"-"+meter_sogei.getData()).getBet()-meter_sogei.getBet()!=0 
						|| mappa_600.get(meter_sogei.getAams_vlt_id()+"-"+meter_sogei.getData()).getWin()-meter_sogei.getWin()!=0
						|| mappa_600.get(meter_sogei.getAams_vlt_id()+"-"+meter_sogei.getData()).getGamesPlayed()-meter_sogei.getGamesPlayed()!=0	
						|| mappa_600.get(meter_sogei.getAams_vlt_id()+"-"+meter_sogei.getData()).getTotalIn()-meter_sogei.getTotalIn()!=0
						|| mappa_600.get(meter_sogei.getAams_vlt_id()+"-"+meter_sogei.getData()).getTotalOut()-meter_sogei.getTotalOut()!=0){
						
						Confronto conf = new Confronto();
						
						conf.setAams_vlt_id(meter_sogei.getAams_vlt_id());
						conf.setAams_location_id(meter_sogei.getAams_location_id());
						conf.setLocation_name(meter_sogei.getLocation_name());
						conf.setGs_vlt_id(meter_sogei.getGs_vlt_id());
						conf.setData(meter_sogei.getData());
						
						conf.setSogei(meter_sogei);
						conf.setSeicento(mappa_600.get(meter_sogei.getAams_vlt_id()+"-"+meter_sogei.getData()));
						
						lista_confronti.add(conf);
					}
					mappa_600.remove(meter_sogei.getAams_vlt_id()+"-"+meter_sogei.getData());	
					
				} else {
					if (meter_sogei.getBet()!=0 && 
							meter_sogei.getWin()!=0 && 
							meter_sogei.getGamesPlayed()!=0 && 
							meter_sogei.getTotalIn()!=0 && 
							meter_sogei.getTotalOut()!=0){
				
						Confronto conf = new Confronto();
					
						conf.setAams_vlt_id(meter_sogei.getAams_vlt_id());
						conf.setAams_location_id(meter_sogei.getAams_location_id());
						conf.setLocation_name(meter_sogei.getLocation_name());
						conf.setGs_vlt_id(meter_sogei.getGs_vlt_id());
						conf.setData(meter_sogei.getData());
					
						conf.setSogei(meter_sogei);
						conf.setSeicento(null);
					
						lista_confronti.add(conf);
					}	
				}							
			}
			
			//CON QUESTO CICLO FOR SCANDISCO LA MAPPA CON I 600 RIMANENTI (QUELLI CHE NON AVEVANO UN SOGEI CORRISPONDENTE)
			Set<String> keySet_mappa = mappa_600.keySet();
			for (String vlt_id_data : keySet_mappa){
				
				MeterBean meter_600 = mappa_600.get(vlt_id_data);
				if(meter_600.getBet()!=0 && 
						meter_600.getWin()!=0 && 
						meter_600.getGamesPlayed()!=0 && 
						meter_600.getTotalIn()!=0 && 
						meter_600.getTotalOut()!=0)
				{
					Confronto conf = new Confronto();
					
					conf.setAams_vlt_id(meter_600.getAams_vlt_id());
					conf.setAams_location_id(meter_600.getAams_location_id());
					conf.setLocation_name(meter_600.getLocation_name());
					conf.setGs_vlt_id(meter_600.getGs_vlt_id());
					conf.setData(meter_600.getData());
					
					conf.setSeicento(meter_600);
					conf.setSogei(null);
					
					lista_confronti.add(conf);
				}			
			}
			
			//SOMMO I TOTALI DELLE VLT PER OTTENERE I TOTALI DELLE SALE
			Confronto cfattuale;			
			Map<String, Confronto> mappa_confronti = new HashMap<String, Confronto>();
			for (Confronto c : lista_confronti)
			{
				if (!mappa_confronti.containsKey(c.getAams_location_id()+"-"+c.getData()))
				{
					cfattuale = new Confronto();
					cfattuale.setAams_location_id(c.getAams_location_id());
					cfattuale.setLocation_name(c.getLocation_name());					
					cfattuale.setData(c.getData());					
					cfattuale.setSeicento(c.getSeicento());
					cfattuale.setSogei(c.getSogei());
					
					mappa_confronti.put(cfattuale.getAams_location_id()+"-"+cfattuale.getData(), cfattuale);
				} 
				else 
				{
					cfattuale = mappa_confronti.get(c.getAams_location_id()+"-"+c.getData());
					MeterBean mb600a = cfattuale.getSeicento();
					if (mb600a!=null && c.getSeicento()!=null){
						mappa_confronti.get(c.getAams_location_id()+"-"+c.getData()).getSeicento().setNumVlt(mb600a.getNumVlt()+c.getSeicento().getNumVlt());
						mappa_confronti.get(c.getAams_location_id()+"-"+c.getData()).getSeicento().setBet(mb600a.getBet()+c.getSeicento().getBet());
						mappa_confronti.get(c.getAams_location_id()+"-"+c.getData()).getSeicento().setWin(mb600a.getWin()+c.getSeicento().getWin());
						mappa_confronti.get(c.getAams_location_id()+"-"+c.getData()).getSeicento().setGamesPlayed(mb600a.getGamesPlayed()+c.getSeicento().getGamesPlayed());
						mappa_confronti.get(c.getAams_location_id()+"-"+c.getData()).getSeicento().setTotalIn(mb600a.getTotalIn()+c.getSeicento().getTotalIn());
						mappa_confronti.get(c.getAams_location_id()+"-"+c.getData()).getSeicento().setTotalOut(mb600a.getTotalOut()+c.getSeicento().getTotalOut());
					}
					MeterBean mbSa = cfattuale.getSogei();
					if (mbSa!=null && c.getSogei()!=null){					
						mappa_confronti.get(c.getAams_location_id()+"-"+c.getData()).getSogei().setNumVlt(mbSa.getNumVlt()+c.getSogei().getNumVlt());
						mappa_confronti.get(c.getAams_location_id()+"-"+c.getData()).getSogei().setBet(mbSa.getBet()+c.getSogei().getBet());
						mappa_confronti.get(c.getAams_location_id()+"-"+c.getData()).getSogei().setWin(mbSa.getWin()+c.getSogei().getWin());
						mappa_confronti.get(c.getAams_location_id()+"-"+c.getData()).getSogei().setGamesPlayed(mbSa.getGamesPlayed()+c.getSogei().getGamesPlayed());
						mappa_confronti.get(c.getAams_location_id()+"-"+c.getData()).getSogei().setTotalIn(mbSa.getTotalIn()+c.getSogei().getTotalIn());
						mappa_confronti.get(c.getAams_location_id()+"-"+c.getData()).getSogei().setTotalOut(mbSa.getTotalOut()+c.getSogei().getTotalOut());						
					}
				}				
			}
			
			Set<String> keySet_mappa_2 = mappa_confronti.keySet();
			List<Confronto> app = new ArrayList<Confronto>();
			for (String loc_id_data : keySet_mappa_2){
				app.add(mappa_confronti.get(loc_id_data));
			}
			
			lista_confronti = app;
			
		} catch (SQLException e) {
			logger.error("retrieveConfronti600Sogei_location failed", e);
			e.printStackTrace();
			throw new DataAccessException(IErrorCodes.DB_ERROR_KEY, e.toString(), e);
		}
		catch (Exception e) {
			logger.error("retrieveConfronti600Sogei_location failed", e);
			e.printStackTrace();
			throw new DataAccessException(IErrorCodes.DB_ERROR_KEY, e.toString(), e);
		}
		finally{
				try {
					DBConnectionManager.CloseConnection(rs, ps, connDatamart);	
					DBConnectionManager.CloseConnection(connStaging);
				} catch (DataAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					logger.error(e);
				}
		}
		return lista_confronti;
	}	

	
	
	
	public List<Confronto> retrieveConfronti600Sogei_locations(String location_name, String aams_location_id,String sortColumn, 
			boolean isAscending,Date data1,Date data2, boolean mostraTutto)  throws DataAccessException {

		List<Confronto> lista_confronti = null;
		ResultSet rs=null;
		PreparedStatement ps=null;
		Connection connDatamart=null;
		Connection connStaging=null;	
		
		try {
			connDatamart=DBConnectionManager.DataMartConnectionFactory();
			
		
			String query_sogei = "select SOGEI_LOCATION_ID,SOGEI_LOCATION_NAME,count(AAMS_VLT_ID) AS SOGEI_NUM_VLT,sum(SOGEI_BET) as SOGEI_BET," +
					"        sum(SOGEI_WIN) as SOGEI_WIN,sum(SOGEI_GAMES_PLAYED) as SOGEI_GAMES_PLAYED, sum(SOGEI_TOTAL_IN) as SOGEI_TOTAL_IN," +
					"        sum(SOGEI_TOTAL_OUT) as SOGEI_TOTAL_OUT, SOGEI_DATA					       " +
					"from (select spaziodim.aams_location_id as SOGEI_LOCATION_ID,spaziodim.location_name AS SOGEI_LOCATION_NAME," +
					"      spaziodim.AAMS_VLT_ID AS AAMS_VLT_ID," +
					"      sum(metSogei.BET) as SOGEI_BET, sum(metSogei.WIN) as SOGEI_WIN, sum(metSogei.GAMES_PLAYED) as SOGEI_GAMES_PLAYED, " +
					"			max(metSogei.TOTAL_IN) as SOGEI_TOTAL_IN, max(metSogei.TOTAL_OUT) as SOGEI_TOTAL_OUT, tempodim.DATA as SOGEI_DATA 		" +
					"      from METERFACT metSogei " +
					"			inner join TEMPODIM tempodim on metSogei.DMTEMPO_ID=tempodim.ID " +
					"			inner join SPAZIODIM spaziodim on metSogei.DMSPAZIO_ID=spaziodim.ID " +
					"			where tempodim.DATA between ? and ? ";
					if ((location_name != null && !location_name.equals(""))
							|| (aams_location_id != null && !aams_location_id
									.equals(""))) {

						query_sogei = query_sogei + " AND ";
						boolean and = false;
						if (location_name != null && !location_name.equals("")) {
							query_sogei = query_sogei + "lower(spaziodim.LOCATION_NAME) like lower('%"+ location_name +"%') ";
							and = true;
						}
						if (aams_location_id != null && !aams_location_id.equals("")) {
							if (and)
							query_sogei = query_sogei + " AND ";
							query_sogei = query_sogei + "lower(spaziodim.AAMS_LOCATION_ID) like lower('%"+ aams_location_id +"%') ";
							and = false;
						}
					}				
			query_sogei=query_sogei+"          group by spaziodim.aams_location_id, spaziodim.location_name, spaziodim.AAMS_VLT_ID, tempodim.DATA)" +
					" group by SOGEI_LOCATION_ID, SOGEI_LOCATION_NAME, SOGEI_DATA     ";

			
			
						
			ps = connDatamart.prepareStatement(query_sogei);
			
			java.sql.Date data1_ = new java.sql.Date(data1.getTime());
			java.sql.Date data2_ = new java.sql.Date(data2.getTime());
			
			ps.setDate(1, data1_);
			ps.setDate(2, data2_);
							
			rs = ps.executeQuery();
			
			List<MeterBean> lista_sogei = new ArrayList<MeterBean>();
			while (rs.next()){
				MeterBean mbSogei = new MeterBean();
				mbSogei.setAams_location_id(rs.getString("SOGEI_LOCATION_ID"));
				mbSogei.setLocation_name(rs.getString("SOGEI_LOCATION_NAME"));
				mbSogei.setNumVlt(rs.getInt("SOGEI_NUM_VLT"));
				//mbSogei.setNumVlt(0);
				mbSogei.setData(rs.getDate("SOGEI_DATA"));
				mbSogei.setBet(rs.getDouble("SOGEI_BET"));
				mbSogei.setWin(rs.getDouble("SOGEI_WIN"));
				mbSogei.setWin(Math.rint(mbSogei.getWin()*100)/100);
				mbSogei.setGamesPlayed(rs.getLong("SOGEI_GAMES_PLAYED"));
				mbSogei.setTotalIn(rs.getDouble("SOGEI_TOTAL_IN"));
				mbSogei.setTotalIn(Math.rint(mbSogei.getTotalIn()*100)/100);
				mbSogei.setTotalOut(rs.getDouble("SOGEI_TOTAL_OUT"));
				
				lista_sogei.add(mbSogei);
			}
			
			
			String query_600 = "select aams_location_id, commercial_name, data_600, count(code_id_600) AS NUM_VLT, sum(BET_600) AS BET_600, " +
					"        sum(WIN_600) AS WIN_600,sum(BET_NUM_600) AS BET_NUM_600,sum(TOT_IN_600) AS TOT_IN_600,sum(TOT_OUT_600) AS TOT_OUT_600 " +
					"from " +
					"(select distinct gs.aams_location_id as AAMS_LOCATION_ID,loc.commercial_name as COMMERCIAL_NAME, met.code_id as CODE_ID_600, met.data AS DATA_600," +
					"        met.bet/100 AS BET_600,met.win/100 AS WIN_600,met.bet_num AS BET_NUM_600,met.tot_in/100 AS TOT_IN_600,met.tot_out/100 AS TOT_OUT_600 " +
					"		from birsaamsmeters met " +
					"		full outer join birsgsmeters gs on met.code_id = gs.aams_vlt_code and met.data = gs.data " +
					"		full outer join birslocation loc on gs.aams_location_id = loc.aams_location_id " +
					"		where met.data between ? and ? ";				
			if ((location_name != null && !location_name.equals(""))
					|| (aams_location_id != null && !aams_location_id.equals(""))) {

				query_600 = query_600 + " AND ";
				boolean and = false;
				if (location_name != null && !location_name.equals("")) {
					query_600 = query_600 + "lower(loc.commercial_name) like lower('%"+ location_name +"%') ";
					and = true;
				}
				if (aams_location_id != null && !aams_location_id.equals("")) {
					if (and)
						query_600 = query_600 + " AND ";
					query_600 = query_600 + "lower(gs.aams_location_id) like lower('%"+ aams_location_id +"%') ";
					and = false;
				}
			}
			query_600 = query_600 +") group by aams_location_id, commercial_name, data_600 " +
			" ORDER BY COMMERCIAL_NAME ASC ";
						
			connStaging=DBConnectionManager.StagingConnectionFactory();
			ps = connStaging.prepareStatement(query_600);
			
			ps.setDate(1, data1_);
			ps.setDate(2, data2_);
							
			rs = ps.executeQuery();
			
			//List<MeterBean> lista_600 = new ArrayList<MeterBean>();
			Map<String, MeterBean> mappa_mb_600 = new HashMap<String, MeterBean>();	
			
			while (rs.next()) {

					MeterBean mb600 = new MeterBean();
					mb600.setAams_location_id(rs.getString("aams_location_id"));
					mb600.setLocation_name(rs.getString("COMMERCIAL_NAME"));
					mb600.setNumVlt(rs.getInt("NUM_VLT"));
					mb600.setData(rs.getDate("DATA_600"));
					mb600.setBet(rs.getDouble("BET_600"));
					mb600.setWin(rs.getDouble("WIN_600"));
					mb600.setGamesPlayed(rs.getLong("BET_NUM_600"));
					mb600.setTotalIn(rs.getDouble("TOT_IN_600"));
					mb600.setTotalOut(rs.getDouble("TOT_OUT_600"));

					//lista_600.add(mb600);	
					mappa_mb_600.put(mb600.getAams_location_id()+"-"+mb600.getData(), mb600);
				}

						
			//CON QUESTO CICLO FOR TROVO LE CORRISPONDENZE TRA SOGEI E 600 PER CREARE I CONFRONTI
			//SE UN SOGEI TROVA IL 600 CORRISPONDENTE, QUEST'ULTIMO VIENE ELIMINATO DALLA MAPPA
			lista_confronti = new ArrayList<Confronto>();
			
			for (MeterBean meter_sogei : lista_sogei){
				//IL METER SOGEI HA UNA CORRISPONDENZA CON UN METER 600
				if (mappa_mb_600.containsKey(meter_sogei.getAams_location_id()+"-"+meter_sogei.getData())){
					if (mostraTutto){
						Confronto conf = new Confronto();
											
						conf.setGamesystem_name(meter_sogei.getGamesystem_name());
						conf.setAams_location_id(meter_sogei.getAams_location_id());
						conf.setLocation_name(meter_sogei.getLocation_name());
						conf.setData(meter_sogei.getData());						
						conf.setSogei(meter_sogei);
						conf.setSeicento(mappa_mb_600.get(meter_sogei.getAams_location_id()+"-"+meter_sogei.getData()));
						
						lista_confronti.add(conf);
					}	//BET O WIN O GAMESPLAYED O TOTALIN O TOTALOUT SONO DIFFERENTI TRA SOGEI E 600. CREO UN CONFRONTO
					else if (mappa_mb_600.get(meter_sogei.getAams_location_id()+"-"+meter_sogei.getData()).getBet()-meter_sogei.getBet()!=0 
						|| mappa_mb_600.get(meter_sogei.getAams_location_id()+"-"+meter_sogei.getData()).getWin()-meter_sogei.getWin()!=0
						|| mappa_mb_600.get(meter_sogei.getAams_location_id()+"-"+meter_sogei.getData()).getGamesPlayed()-meter_sogei.getGamesPlayed()!=0	
						|| mappa_mb_600.get(meter_sogei.getAams_location_id()+"-"+meter_sogei.getData()).getTotalIn()-meter_sogei.getTotalIn()!=0
						|| mappa_mb_600.get(meter_sogei.getAams_location_id()+"-"+meter_sogei.getData()).getTotalOut()-meter_sogei.getTotalOut()!=0){
						
						Confronto conf = new Confronto();
						
						conf.setGamesystem_name(meter_sogei.getGamesystem_name());
						conf.setAams_location_id(meter_sogei.getAams_location_id());
						conf.setLocation_name(meter_sogei.getLocation_name());
						conf.setData(meter_sogei.getData());					
						conf.setSogei(meter_sogei);
						conf.setSeicento(mappa_mb_600.get(meter_sogei.getAams_location_id()+"-"+meter_sogei.getData()));
						
						lista_confronti.add(conf);
					} //CREATO IL CONFRONTO ELIMINO IL 600 DALLA MAPPA
					mappa_mb_600.remove(meter_sogei.getAams_location_id()+"-"+meter_sogei.getData());	
					
				} else { //IL SOGEI NON HA UN 600 CORRISPONDENTE. SE NON HA TUTTI I TOTALI = 0 (VLT NON ANCORA ATTIVA) CREO UN CONFRONTO
					if (meter_sogei.getBet()!=0 || 
							meter_sogei.getWin()!=0 || 
							meter_sogei.getGamesPlayed()!=0 ||
							meter_sogei.getTotalIn()!=0 ||
							meter_sogei.getTotalOut()!=0){
				
						Confronto conf = new Confronto();
					
						conf.setGamesystem_name(meter_sogei.getGamesystem_name());
						conf.setAams_location_id(meter_sogei.getAams_location_id());
						conf.setLocation_name(meter_sogei.getLocation_name());
						conf.setData(meter_sogei.getData());
					
						conf.setSogei(meter_sogei);
						conf.setSeicento(null);
					
						lista_confronti.add(conf);
					}	
				}							
			}
			
			//CON QUESTO CICLO FOR SCANDISCO LA MAPPA CON I 600 RIMANENTI (QUELLI CHE NON AVEVANO UN SOGEI CORRISPONDENTE)
			Set<String> keySet_mappa = mappa_mb_600.keySet();
			for (String loc_id_data : keySet_mappa){
				
				MeterBean meter_600 = mappa_mb_600.get(loc_id_data);
				if(meter_600.getBet()!=0 ||
						meter_600.getWin()!=0 ||
						meter_600.getGamesPlayed()!=0 ||
						meter_600.getTotalIn()!=0 ||
						meter_600.getTotalOut()!=0)
				{
					Confronto conf = new Confronto();

					conf.setGamesystem_name(meter_600.getGamesystem_name());
					conf.setAams_location_id(meter_600.getAams_location_id());
//					conf.setLocation_name(meter_600.getLocation_name());
					conf.setData(meter_600.getData());
					
					conf.setSeicento(meter_600);
					conf.setSogei(null);
					
					lista_confronti.add(conf);
				}			
			}
			
			
		} catch (SQLException e) {
			logger.error("retrieveConfronti600Sogei_location failed", e);
			e.printStackTrace();
			throw new DataAccessException(IErrorCodes.DB_ERROR_KEY, e.toString(), e);
		}
		catch (Exception e) {
			logger.error("retrieveConfronti600Sogei_location failed", e);
			e.printStackTrace();
			throw new DataAccessException(IErrorCodes.DB_ERROR_KEY, e.toString(), e);
		}
		finally{
				try {
					DBConnectionManager.CloseConnection(rs, ps, connDatamart);	
					DBConnectionManager.CloseConnection(connStaging);
				} catch (DataAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					logger.error(e);
				}
		}
		return lista_confronti;
	}	
}
	