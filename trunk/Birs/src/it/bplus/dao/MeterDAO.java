package it.bplus.dao;

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

import org.apache.log4j.Logger;

import it.bplus.bean.MeterBean;
import it.bplus.bean.MillionVltBean;
import it.bplus.db.DBConnectionManager;
import it.bplus.exception.DataAccessException;

import it.bplus.util.DateUtil;
import it.bplus.util.IConstants;
import it.bplus.util.IErrorCodes;

public class MeterDAO {

	protected static Logger logger = Logger.getLogger(MeterDAO.class);


	private static MeterDAO instance;

	private MeterDAO() {
		super();
	}


	public static synchronized MeterDAO getInstance() {
		if (instance == null)
		{
			synchronized(MeterDAO.class) {      //1
				MeterDAO inst = instance;         //2
				if (inst == null)
				{
					synchronized(MeterDAO.class) {  //3
						instance = new MeterDAO();
					}
					//instance = inst;               //5
				}
			}
		}
		return instance;
	}


	
	
	public List<MeterBean> retrieveMeter_locations(String location_name,
			String aams_location_id, String id_regione, String id_provincia, String id_comune,
			String cod_gestore, Date data1, Date data2, String sortColumn, boolean isAscending, boolean nascondiSaleNonAperte, boolean mostraTotale)  throws DataAccessException {
		
		List<MeterBean> meters = null;
		ResultSet rs=null;
		PreparedStatement ps=null;
		Connection connDataMart=null;
		
		try {
			connDataMart=DBConnectionManager.DataMartConnectionFactory();
			
			//DateFormat df = new SimpleDateFormat(IConstants.DATE_FORMAT);
						
			String sql = "SELECT vista1.*, vista2.num_vlt, vista2.TOTAL_IN, vista2.TOTAL_OUT, vista2.TICKET_IN, vista2.TICKET_OUT, vista2.COIN_IN, vista2.BILL_IN, vista2.CARD_IN, vista2.TOTAL_PREPAID_IN " +
			"FROM " +
			"(select sum(this_.BET) as BET, sum(this_.WIN) as WIN, sum(this_.GAMES_PLAYED) as GAMES_PLAYED, " +
			"sum(this_.JACKPOT_WINS) as JACKPOT_WINS, sum(this_.JACKPOT_CONTRIBUTION) as JACKPOT_CONTRIBUTION, sum(this_.PREU) as PREU, " +
			"sum(this_.AAMS) as AAMS, sum(this_.NET_WIN) as NET_WIN, sum(this_.HOUSE_WIN) as HOUSE_WIN, sum(this_.SUPPLIER_PROFIT) as SUPPLIER_PROFIT, sum(this_.OPERATORS_PROFIT) as OPERATORS_PROFIT, " +
			"sum(this_.BPLUS_NET_PROFIT) as BPLUS_NET_PROFIT, spaziodim1_.AAMS_LOCATION_ID as AAMS_LOCATION_ID, spaziodim1_.LOCATION_NAME as LOCATION_NAME, clientidim6_.COD_GESTORE as COD_GESTORE, " +
			"spaziodim1_.LOCATION_ADDRESS as LOCATION_ADDRESS, spaziodim1_.NUMERO_CIVICO as NUMERO_CIVICO,spaziodim1_.TOPONIMO AS TOPONIMO,";
			if (!mostraTotale){
				sql=sql+"tempodim5_.DATA as DATA, ";
			}
			sql=sql+"spaziodim1_.REGION as REGIONE, spaziodim1_.SIGLA_PROVINCIA as PROVINCIA, spaziodim1_.CITY as COMUNE " +
			"from METERFACT this_ inner join TEMPODIM tempodim5_ on this_.DMTEMPO_ID=tempodim5_.ID " +
			"inner join CLIENTIDIM clientidim6_ on this_.DMCLIENTI_ID=clientidim6_.ID " +
			"left outer join SPAZIODIM spaziodim1_ on this_.DMSPAZIO_ID=spaziodim1_.ID " +
			"WHERE tempodim5_.DATA between ? and ? " + //qui 2 punti interrogativi, DATA1 e DATA2
			"GROUP BY spaziodim1_.AAMS_LOCATION_ID, spaziodim1_.LOCATION_NAME, clientidim6_.COD_GESTORE, spaziodim1_.LOCATION_ADDRESS,spaziodim1_.NUMERO_CIVICO,spaziodim1_.TOPONIMO, spaziodim1_.REGION, spaziodim1_.SIGLA_PROVINCIA, spaziodim1_.CITY ";
			if (!mostraTotale){
				sql=sql+", tempodim5_.DATA ";
			}
			sql=sql+" ) vista1 " +			
//			"LEFT OUTER JOIN " + perché c'era questo? inner sembra dare lo stesso risultato...
			"INNER JOIN ";
			if (mostraTotale){
				sql=sql+"(SELECT AAMS_LOCATION_ID, SUM(NUM_VLT) AS NUM_VLT, SUM(DISTINCT(TOTAL_IN)) AS TOTAL_IN, SUM(DISTINCT(TOTAL_OUT)) AS TOTAL_OUT, SUM(DISTINCT(TICKET_IN)) AS TICKET_IN, " +
						"			SUM(DISTINCT(TICKET_OUT)) AS TICKET_OUT, SUM(DISTINCT(COIN_IN)) AS COIN_IN, SUM(DISTINCT(BILL_IN)) AS BILL_IN, SUM(DISTINCT(CARD_IN)) AS CARD_IN, SUM(DISTINCT(TOTAL_PREPAID_IN)) AS TOTAL_PREPAID_IN ," +
						"			ID_REGIONE, ID_PROVINCIA, ID_COMUNE " +
						"      FROM ";
			} 
			sql=sql+"(select AAMS_LOCATION_ID, count(distinct(vlt_id)) as num_vlt, SUM(DISTINCT(TOTAL_IN)) AS TOTAL_IN, SUM(DISTINCT(TOTAL_OUT)) AS TOTAL_OUT, SUM(DISTINCT(TICKET_IN)) AS TICKET_IN, " +
			"SUM(DISTINCT(TICKET_OUT)) AS TICKET_OUT, SUM(DISTINCT(COIN_IN)) AS COIN_IN, SUM(DISTINCT(BILL_IN)) AS BILL_IN, SUM(DISTINCT(CARD_IN)) AS CARD_IN, SUM(DISTINCT(TOTAL_PREPAID_IN)) AS TOTAL_PREPAID_IN , DATA AS DATA, "+
			"ID_REGIONE AS ID_REGIONE, ID_PROVINCIA AS ID_PROVINCIA, ID_COMUNE AS ID_COMUNE " +
			"from (select distinct(spaziodim2_.AAMS_VLT_ID) as vlt_id, TOTAL_IN, TOTAL_OUT,TICKET_IN, TICKET_OUT,COIN_IN, BILL_IN, " +
			"CARD_IN, TOTAL_PREPAID_IN, spaziodim2_.AAMS_LOCATION_ID as AAMS_LOCATION_ID, spaziodim2_.ID_REGIONE AS ID_REGIONE, spaziodim2_.ID_PROVINCIA AS ID_PROVINCIA, spaziodim2_.ID_COMUNE AS ID_COMUNE, tempodim8_.data as DATA " +
			"from meterfact inner join TEMPODIM tempodim8_ on DMTEMPO_ID=tempodim8_.ID  " +
			"left outer join SPAZIODIM spaziodim2_ on DMSPAZIO_ID=spaziodim2_.ID ";
			if (nascondiSaleNonAperte){
				sql=sql+" inner join birslocation loc on spaziodim2_.aams_location_id = loc.aams_location_id ";			
			}
			sql=sql+" WHERE DATA BETWEEN ? AND ? ";	//2 PUNTI INTERROGATIVI, DATA1 e DATA2
			if (nascondiSaleNonAperte){
				sql=sql+"    AND loc.opening_date is not null and loc.opening_date <= ? ";	//1 PUNTO INTERROGATIVO, DATA 2
			}
			sql=sql+") group by AAMS_LOCATION_ID, ID_REGIONE, ID_PROVINCIA, ID_COMUNE, DATA ";
			if (mostraTotale){
				sql=sql+")group by AAMS_LOCATION_ID, ID_REGIONE, ID_PROVINCIA, ID_COMUNE ";
			} 
			sql=sql+") vista2 " +
			"on vista1.AAMS_LOCATION_ID=vista2.AAMS_LOCATION_ID ";
			if (!mostraTotale){
				sql=sql+"and vista1.DATA=vista2.DATA ";
			}			
			
			if((location_name!=null && !location_name.equals(""))  || (aams_location_id!=null && !aams_location_id.equals(""))
					|| (id_regione!=null && !id_regione.equals("") && !id_regione.equals(" - ")) 
					|| (id_provincia!=null && !id_provincia.equals("") && !id_provincia.equals(" - "))
					|| (id_comune!=null && !id_comune.equals("") && !id_comune.equals("-1")) 
					|| (cod_gestore!=null && !cod_gestore.equals(""))){
				sql = sql+"WHERE ";
				boolean and = false;
				if (location_name!=null && !location_name.equals("")){
					sql=sql+"lower(vista1.LOCATION_NAME) like lower('%"+location_name+"%') ";
					and = true;
				}
				if (aams_location_id!=null && !aams_location_id.equals("")){
					if (and) sql=sql+" AND ";
					sql=sql+"lower(vista1.AAMS_LOCATION_ID) like lower('%"+aams_location_id+"%') ";
					and = true;
				}
				if (id_regione!=null && !id_regione.equals("") && !id_regione.equals(" - ")){
					if (and) sql=sql+" AND ";
					sql=sql+"vista2.ID_REGIONE = "+id_regione+" ";
					and = true;
				}
				if (id_provincia!=null && !id_provincia.equals("") && !id_provincia.equals(" - ")){
					if (and) sql=sql+" AND ";
					sql=sql+"vista2.ID_PROVINCIA = "+id_provincia+" ";
					and = true;
				}
				if (id_comune!=null && !id_comune.equals("") && !id_comune.equals("-1")){
					if (and) sql=sql+" AND ";
					sql=sql+"vista2.ID_COMUNE = "+id_comune+" ";
					and = true;
				}
				if (cod_gestore!=null && !cod_gestore.equals("")){
					if (and) sql=sql+" AND ";
					sql=sql+"lower(vista1.COD_GESTORE) like lower('%"+cod_gestore+"%') ";
					and = true;
				}
//				if (data1!=null && data2!=null){
//					
//					if (and) sql=sql+" AND ";
//					sql=sql+"vista1.DATA between TO_DATE('"+df.format(data1)+"','"+IConstants.DATE_FORMAT+"') AND TO_DATE('"+df.format(data2)+"','"+IConstants.DATE_FORMAT+"') ";
//					and = false;
//				}
//				else if (data1!=null){				
//					if (and) sql=sql+" AND ";
//					sql=sql+"vista1.DATA = TO_DATE('"+df.format(data1)+"','"+IConstants.DATE_FORMAT+"') ";
//					and = false;
//				}
//				else if (data2!=null){				
//					if (and) sql=sql+" AND ";
//					sql=sql+"vista1.DATA = TO_DATE('"+df.format(data2)+"','"+IConstants.DATE_FORMAT+"') ";
//					and = false;
//				}				
			} 
			
			
			//ORDINAMENTO
			String ascDesc = null;
			if (!isAscending)
				ascDesc = " DESC ";
			else if (isAscending)
				ascDesc = " ASC ";
			
			
			if (sortColumn!=null && sortColumn.equals("vista2.num_vlt"))
				sortColumn=sortColumn+ascDesc+", vista1.LOCATION_NAME";
			else if (sortColumn!=null && sortColumn.equals("vista1.DATA")){
				if (!mostraTotale)
					sortColumn=sortColumn+ascDesc+", vista1.LOCATION_NAME";
				else sortColumn="vista1.location_Name";
				}
			 else if (sortColumn!=null && sortColumn.equals("vista1.regione"))
					sortColumn="vista1.regione"+ascDesc+",vista1.provincia"+ascDesc+",vista1.COMUNE"+ascDesc+",vista1.LOCATION_NAME";
			 else if (sortColumn!=null && sortColumn.equals("vista1.provincia"))
				sortColumn="vista1.provincia"+ascDesc+",vista1.COMUNE"+ascDesc+",vista1.LOCATION_NAME";
			 else if (sortColumn!=null && sortColumn.equals("vista1.comune"))
				sortColumn="vista1.COMUNE"+ascDesc+",vista1.LOCATION_NAME";
							
			if(sortColumn!=null && !(sortColumn.equals("")) && !(sortColumn.equals("vista1.DATA"))){
				sql=sql+" ORDER BY "+sortColumn+ascDesc;	
				if (!mostraTotale){
					sql=sql+", vista1.DATA"+ascDesc;
				}
			}							
			else if (sortColumn == null || sortColumn.equals("")) {
				if (!mostraTotale) {
					sql = sql+ " ORDER BY vista1.DATA ASC, vista1.LOCATION_NAME ASC";
				} else sql = sql + " ORDER BY vista1.LOCATION_NAME ASC";
			}
			
			//logger.info(sql);
			
			ps = connDataMart.prepareStatement(sql);
			
			java.sql.Date data1_ = new java.sql.Date(data1.getTime());
			java.sql.Date data2_ = new java.sql.Date(data2.getTime());
			
			ps.setDate(1, data1_);
			ps.setDate(2, data2_);
			ps.setDate(3, data1_);
			ps.setDate(4, data2_);
			if (nascondiSaleNonAperte){
				ps.setDate(5, data2_);
			}
			
			
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
				if (!mostraTotale)
					mbean.setData(rs.getDate("DATA"));
				else {
					DateFormat df = new SimpleDateFormat("dd/MM/yyyy");					
					mbean.setPeriodo("da "+df.format(data1)+" a "+df.format(data2));
				}
				mbean.setNumVlt(rs.getInt("NUM_VLT"));
				
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
				
				String nota = "";
				if (rs.getString("COD_GESTORE") == null
						|| rs.getString("LOCATION_ADDRESS") == null) {
					
					nota = "Dati mancanti su: ";
					boolean virgola = false;
										
					if (rs.getString("COD_GESTORE") == null) {
						nota += "gestore";
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
			logger.error("retrieveMeter_locations: SQL failed", e);
			e.printStackTrace();
			throw new DataAccessException(IErrorCodes.DB_ERROR_KEY, e.toString(), e);
		}		
		catch (ArrayIndexOutOfBoundsException e) {
			logger.error("EXCEPTION: retrieveMeter_locations ArrayIndexOutOfBoundsException", e);
			throw new DataAccessException(IErrorCodes.DB_ERROR_KEY, e.toString(), e);
		}
		catch (Exception e) {
			logger.error("retrieveMeter_locations failed", e);
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
	
	
	
	
	
	
	public List<MeterBean> retrieveMeter_regioni(String id_regione, Date data1, Date data2, String sortColumn, 
			boolean isAscending, boolean escludiSaleNonAperte, boolean mostraTotale)  throws DataAccessException {
		
		List<MeterBean> meters = null;
		ResultSet rs=null;
		PreparedStatement ps=null;
		Connection connDataMart=null;
		
		try {
			connDataMart=DBConnectionManager.DataMartConnectionFactory();
						
			String sql = "SELECT vista1.*, vista2.num_vlt, vista2.TOTAL_IN, vista2.TOTAL_OUT, vista2.TICKET_IN, vista2.TICKET_OUT, vista2.COIN_IN, vista2.BILL_IN, vista2.CARD_IN, vista2.TOTAL_PREPAID_IN " +
					"			FROM       " +
					"			(select sum(this_.BET) as BET, sum(this_.WIN) as WIN, sum(this_.GAMES_PLAYED) as GAMES_PLAYED, " +
					"			sum(this_.JACKPOT_WINS) as JACKPOT_WINS, sum(this_.JACKPOT_CONTRIBUTION) as JACKPOT_CONTRIBUTION, sum(this_.PREU) as PREU, " +
					"			sum(this_.AAMS) as AAMS, sum(this_.NET_WIN) as NET_WIN, sum(this_.HOUSE_WIN) as HOUSE_WIN, sum(this_.SUPPLIER_PROFIT) as SUPPLIER_PROFIT, sum(this_.OPERATORS_PROFIT) as OPERATORS_PROFIT, " +
					"			sum(this_.BPLUS_NET_PROFIT) as BPLUS_NET_PROFIT, spaziodim1_.REGION as REGIONE,spaziodim1_.id_regione as id_regione ";
			if (!mostraTotale){		
				sql=sql+", tempodim5_.DATA as DATA ";
			}
			sql=sql+"			from METERFACT this_ inner join TEMPODIM tempodim5_ on this_.DMTEMPO_ID=tempodim5_.ID " +
					"			left outer join SPAZIODIM spaziodim1_ on this_.DMSPAZIO_ID=spaziodim1_.ID 		" +
					"		WHERE tempodim5_.DATA between ? and ? " + //qui 2 punti interrogativi, DATA1 e DATA2
				  //"			WHERE spaziodim1_.GS_GAME_ID<>-1 " +
					"      GROUP BY spaziodim1_.REGION, spaziodim1_.id_regione";
			if (!mostraTotale){
				sql=sql+",tempodim5_.DATA ";
			}
			sql=sql+"			) vista1     " +
					"			INNER JOIN " +
					"			(select ID_REGIONE AS ID_REGIONE, count(distinct(vlt_id)) as num_vlt, SUM(TOTAL_IN) AS TOTAL_IN, SUM(TOTAL_OUT) AS TOTAL_OUT, SUM(TICKET_IN) AS TICKET_IN, SUM(TICKET_OUT) AS TICKET_OUT, " +
					"      SUM(COIN_IN) AS COIN_IN, SUM(BILL_IN) AS BILL_IN, SUM(CARD_IN) AS CARD_IN, SUM(TOTAL_PREPAID_IN) AS TOTAL_PREPAID_IN ";
			if (!mostraTotale){
				sql=sql+", DATA AS DATA 	";
			}				
			sql=sql+"			from (select distinct(spaziodim2_.AAMS_VLT_ID) as vlt_id, TOTAL_IN, TOTAL_OUT,TICKET_IN, TICKET_OUT,COIN_IN, BILL_IN, " +
					"			CARD_IN, TOTAL_PREPAID_IN, spaziodim2_.AAMS_LOCATION_ID as AAMS_LOCATION_ID, spaziodim2_.ID_REGIONE AS ID_REGIONE ";
			if (!mostraTotale){		
					sql=sql+", tempodim8_.data as DATA ";
			}				
			sql=sql+"			from meterfact inner join TEMPODIM tempodim8_ on DMTEMPO_ID=tempodim8_.ID  		 " +
					"      left outer join SPAZIODIM spaziodim2_ on DMSPAZIO_ID=spaziodim2_.ID ";
			if (escludiSaleNonAperte){
				sql=sql+" inner join birslocation loc on spaziodim2_.aams_location_id = loc.aams_location_id ";			
			}
			sql=sql+" WHERE DATA BETWEEN ? AND ? ";	//2 PUNTI INTERROGATIVI, DATA1 e DATA2
			if (escludiSaleNonAperte){
				sql=sql+"    AND loc.opening_date is not null and loc.opening_date <= ? ";	//1 PUNTO INTERROGATIVO, DATA 2
			}
			sql=sql+" ) 	GROUP BY ID_REGIONE ";
			if (!mostraTotale){		
					sql=sql+",DATA ";
			}
			sql=sql+") vista2 "+
					"			on vista1.id_regione=vista2.id_regione ";
			if (!mostraTotale){
				sql=sql+"and vista1.DATA=vista2.DATA ";
			}	
			
			
			if((id_regione!=null && !id_regione.equals("") && !id_regione.equals(" - "))){
				sql = sql+"AND ";
				boolean and = false;
				if (id_regione!=null && !id_regione.equals("") && !id_regione.equals(" - ")){
					if (and) sql=sql+" AND ";
					sql=sql+"vista2.ID_REGIONE = "+id_regione+" ";
					and = true;
				}
//				if (data1!=null && data2!=null){
//					
//					if (and) sql=sql+" AND ";
//					sql=sql+"vista1.DATA between TO_DATE('"+df.format(data1)+"','"+IConstants.DATE_FORMAT+"') AND TO_DATE('"+df.format(data2)+"','"+IConstants.DATE_FORMAT+"') ";
//					and = false;
//				}
//				else if (data1!=null){
//					
//					if (and) sql=sql+" AND ";
//					sql=sql+"vista1.DATA = TO_DATE('"+df.format(data1)+"','"+IConstants.DATE_FORMAT+"') ";
//					and = false;
//				}
//				else if (data2!=null){
//					
//					if (and) sql=sql+" AND ";
//					sql=sql+"vista1.DATA = TO_DATE('"+df.format(data2)+"','"+IConstants.DATE_FORMAT+"') ";
//					and = false;
//				}				
			} 
			
			
			//ORDINAMENTO
			String ascDesc = null;
			if (!isAscending)
				ascDesc = " DESC ";
			else if (isAscending)
				ascDesc = " ASC ";
			
			
			if (sortColumn!=null && sortColumn.equals("vista2.num_vlt")){
				if (!mostraTotale) sortColumn=sortColumn+ascDesc+", vista1.DATA";
			} 
			else if (sortColumn!=null && sortColumn.equals("vista1.DATA")){
				if (!mostraTotale) sortColumn="vista1.data"+ascDesc+", vista1.regione";
				else sortColumn="vista1.regione";
			}
			 else if (sortColumn!=null && sortColumn.equals("vista1.regione")){
				 if (!mostraTotale) sortColumn="vista1.regione"+ascDesc+", vista1.DATA";
			}
							
			if(sortColumn!=null && !(sortColumn.equals("")))
				sql=sql+" ORDER BY "+sortColumn+ascDesc;								
			else if (sortColumn==null || sortColumn.equals("")){
				if (!mostraTotale) sql=sql+" ORDER BY vista1.DATA ASC, vista1.regione ASC";
				else  sql=sql+" ORDER BY vista1.regione ASC";
			}		
			
			//logger.info(sql);
			
			ps = connDataMart.prepareStatement(sql);
			
			java.sql.Date data1_ = new java.sql.Date(data1.getTime());
			java.sql.Date data2_ = new java.sql.Date(data2.getTime());
			
			ps.setDate(1, data1_);
			ps.setDate(2, data2_);
			ps.setDate(3, data1_);
			ps.setDate(4, data2_);
			if (escludiSaleNonAperte){
				ps.setDate(5, data2_);
			}
			
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
				mbean.setRegione(rs.getString("REGIONE"));
				if (!mostraTotale)
					mbean.setData(rs.getDate("DATA"));
				else {
					DateFormat df = new SimpleDateFormat("dd/MM/yyyy");					
					mbean.setPeriodo("da "+df.format(data1)+" a "+df.format(data2));
				}
				mbean.setNumVlt(rs.getInt("NUM_VLT"));
				
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
				
				String nota = "";
				mbean.setNote(nota);
							
				meters.add(mbean);
			}			
		} catch (SQLException e) {
			logger.error("retrieveMeter_regioni: SQL failed", e);
			e.printStackTrace();
			throw new DataAccessException(IErrorCodes.DB_ERROR_KEY, e.toString(), e);
		}		
		catch (ArrayIndexOutOfBoundsException e) {
			logger.error("EXCEPTION: retrieveMeter_regioni ArrayIndexOutOfBoundsException", e);
			throw new DataAccessException(IErrorCodes.DB_ERROR_KEY, e.toString(), e);
		}
		catch (Exception e) {
			logger.error("retrieveMeter_regioni failed", e);
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
	
	
	
	
	
	
	
	
	
	//QUI SFRUTTO IL CONNECTION POOL
	public List<MeterBean> retrieveMeter_vlt(String location_name,
			String aams_location_id, String aams_vlt_id, String id_regione, String id_provincia, String id_comune,
			String cod_gestore, Date data1, Date data2, String sortColumn, boolean isAscending , 
			boolean nascondiSaleNonAperte, boolean mostraTotale)  throws DataAccessException {

		List<MeterBean> meters = null;
		ResultSet rs=null;
		PreparedStatement ps=null;
		Connection connDataMart=null;
			
		
		try {
			connDataMart=DBConnectionManager.DataMartConnectionFactory();
			String sql = "select sum(this_.BET) as BET, sum(this_.WIN) as WIN, sum(this_.GAMES_PLAYED) as GAMES_PLAYED, " +
					"sum(distinct(this_.TOTAL_IN)) as TOTAL_IN, sum(distinct(this_.TOTAL_OUT)) as TOTAL_OUT,sum(distinct(this_.TICKET_IN)) as TICKET_IN, " +
					"sum(distinct(this_.TICKET_OUT)) as TICKET_OUT, sum(distinct(this_.COIN_IN)) as COIN_IN, sum(distinct(this_.BILL_IN)) as BILL_IN, sum(distinct(this_.CARD_IN)) as CARD_IN, " +
					"sum(distinct(this_.TOTAL_PREPAID_IN)) as TOTAL_PREPAID_IN, sum(distinct(this_.JACKPOT_WINS)) as JACKPOT_WINS, " +
					"sum(distinct(this_.JACKPOT_CONTRIBUTION)) as JACKPOT_CONTRIBUTION, sum(this_.PREU) as PREU, " +
					"sum(this_.AAMS) as AAMS, sum(this_.NET_WIN) as NET_WIN, sum(this_.HOUSE_WIN) as HOUSE_WIN, sum(this_.SUPPLIER_PROFIT) as SUPPLIER_PROFIT, " +
					"sum(this_.OPERATORS_PROFIT) as OPERATORS_PROFIT, sum(this_.BPLUS_NET_PROFIT) as BPLUS_NET_PROFIT, spaziodim.LOCATION_NAME as LOCATION_NAME, " +
					"clientidim.COD_GESTORE as COD_GESTORE, spaziodim.AAMS_LOCATION_ID as AAMS_LOCATION_ID, spaziodim.REGION as REGIONE, " +
					"spaziodim.SIGLA_PROVINCIA as PROVINCIA, spaziodim.CITY as COMUNE, spaziodim.LOCATION_ADDRESS as LOCATION_ADDRESS, spaziodim.NUMERO_CIVICO as NUMERO_CIVICO,spaziodim.TOPONIMO AS TOPONIMO, " +
					"spaziodim.AAMS_VLT_ID as AAMS_VLT_ID,spaziodim.GS_VLT_ID as GS_VLT_ID, this_.STATO as STATO ";
			if (!mostraTotale)	{
				sql=sql+", tempodim.DATA as DATA ";
			}
			sql=sql+"from METERFACT this_ " +
					"inner join TEMPODIM tempodim on this_.DMTEMPO_ID=tempodim.ID " +
					"inner join CLIENTIDIM clientidim on this_.DMCLIENTI_ID=clientidim.ID " +
					"inner join SPAZIODIM spaziodim on this_.DMSPAZIO_ID=spaziodim.ID ";
			if (nascondiSaleNonAperte){
				sql=sql+" inner join BIRSLOCATION loc on spaziodim.aams_location_id = loc.aams_location_id ";
			}
					
			sql=sql+"where tempodim.DATA between ? and ? ";	//2 punti interrogativi
			
			if (nascondiSaleNonAperte){
				sql=sql+" and loc.opening_date is not null and loc.opening_date <= ? ";
			}
			
			if((location_name!=null && !location_name.equals(""))  
					|| (aams_location_id!=null && !aams_location_id.equals(""))
					|| (aams_vlt_id!=null && !aams_vlt_id.equals(""))
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
				if (aams_vlt_id!=null && !aams_vlt_id.equals("")){
					if (and) sql=sql+" AND ";
					sql=sql+"lower(spaziodim.AAMS_VLT_ID) like lower('%"+aams_vlt_id+"%') ";
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
				sql=sql+" GROUP BY spaziodim.REGION,spaziodim.SIGLA_PROVINCIA,spaziodim.CITY,spaziodim.AAMS_LOCATION_ID," +
						"spaziodim.LOCATION_NAME,spaziodim.AAMS_VLT_ID,spaziodim.GS_VLT_ID,this_.STATO,clientidim.COD_GESTORE,spaziodim.LOCATION_ADDRESS," +
						"spaziodim.NUMERO_CIVICO,spaziodim.TOPONIMO ";
				if (!mostraTotale){	
				sql=sql+", tempodim.DATA  ";
				}
				
				//ORDINAMENTO
				String ascDesc = null;
				if (!isAscending)
					ascDesc = " DESC ";
				else if (isAscending)
					ascDesc = " ASC ";
				
				if (sortColumn!=null && sortColumn.equals("tempodim.DATA")){
					if (!mostraTotale){	
						sortColumn="spaziodim.LOCATION_NAME"+ascDesc+",spaziodim.AAMS_VLT_ID"+ascDesc+", tempodim.DATA";
					} else sortColumn="spaziodim.LOCATION_NAME"+ascDesc+",spaziodim.AAMS_VLT_ID";
				}
				else if (sortColumn!=null && sortColumn.equals("spaziodim.SIGLA_PROVINCIA")){
					if (!mostraTotale){	
						sortColumn="spaziodim.SIGLA_PROVINCIA"+ascDesc+",spaziodim.CITY"+ascDesc+", spaziodim.LOCATION_NAME"+ascDesc+",spaziodim.AAMS_VLT_ID"+ascDesc+", tempodim.DATA ";
					} else sortColumn="spaziodim.SIGLA_PROVINCIA"+ascDesc+",spaziodim.CITY"+ascDesc+", spaziodim.LOCATION_NAME"+ascDesc+",spaziodim.AAMS_VLT_ID";
				}
				else if (sortColumn!=null && sortColumn.equals("spaziodim.city")){
					if (!mostraTotale){	
						sortColumn="spaziodim.city"+ascDesc+", spaziodim.LOCATION_NAME"+ascDesc+",spaziodim.AAMS_VLT_ID"+ascDesc+", tempodim.DATA ";
					} else sortColumn="spaziodim.city"+ascDesc+", spaziodim.LOCATION_NAME"+ascDesc+",spaziodim.AAMS_VLT_ID";
				}
				else if (sortColumn!=null && sortColumn.equals("spaziodim.location_Name")){
					if (!mostraTotale){	
						sortColumn="spaziodim.LOCATION_NAME"+ascDesc+",spaziodim.AAMS_VLT_ID"+ascDesc+", tempodim.DATA ";
					} else sortColumn="spaziodim.LOCATION_NAME"+ascDesc+",spaziodim.AAMS_VLT_ID";
				}
				else if (sortColumn!=null && sortColumn.equals("spaziodim.aams_Vlt_Id")){
					if (!mostraTotale){	
						sortColumn="spaziodim.AAMS_VLT_ID"+ascDesc+", tempodim.DATA ";
					} else sortColumn="spaziodim.AAMS_VLT_ID";
				}
				else if (sortColumn!=null && sortColumn.equals("spaziodim.gs_Vlt_Id")){
					if (!mostraTotale){	
						sortColumn="spaziodim.GS_VLT_ID"+ascDesc+", tempodim.DATA ";
					} else sortColumn="spaziodim.GS_VLT_ID";
				}
				
				
				if(sortColumn!=null && !(sortColumn.equals("")))
					sql=sql+" ORDER BY "+sortColumn+ascDesc;								
				else if (sortColumn==null || sortColumn.equals("")){
					if (!mostraTotale){	
						sql=sql+" ORDER BY spaziodim.LOCATION_NAME ASC,spaziodim.AAMS_VLT_ID ASC,tempodim.DATA ASC";
					} else sql=sql+" ORDER BY spaziodim.LOCATION_NAME ASC,spaziodim.AAMS_VLT_ID ASC";
				}
				
			
			//logger.info(sql);
				
			ps = connDataMart.prepareStatement(sql);
			
			java.sql.Date data1_ = new java.sql.Date(data1.getTime());
			java.sql.Date data2_ = new java.sql.Date(data2.getTime());
			
			ps.setDate(1, data1_);
			ps.setDate(2, data2_);
			
			if (nascondiSaleNonAperte){
				ps.setDate(3, data2_);
			}
			
							
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
				if (!mostraTotale)
					mbean.setData(rs.getDate("DATA"));
				else {
					DateFormat df = new SimpleDateFormat("dd/MM/yyyy");					
					mbean.setPeriodo("da "+df.format(data1)+" a "+df.format(data2));
				}
				mbean.setStato(rs.getString("STATO"));
				
				
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
			logger.error("retrieveMeter_vlt failed", e);
			e.printStackTrace();
			throw new DataAccessException(IErrorCodes.DB_ERROR_KEY, e.toString(), e);
		}
		catch (Exception e) {
			logger.error("retrieveMeter_vlt failed", e);
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
	
	
	
	
	
	
	//dettagli per gioco
	public List<MeterBean> retrieveMeter_game(String location_name,
			String aams_location_id, String aams_vlt_id, String id_regione, String id_provincia, String id_comune,
			String cod_gestore, Date data1, Date data2, String sortColumn, boolean isAscending, 
			boolean nascondiSaleNonAperte, boolean mostraTotale)  throws DataAccessException {

		List<MeterBean> meters = null;
		ResultSet rs=null;
		PreparedStatement ps=null;
		Connection connDataMart=null;
			
		
		try {
			connDataMart=DBConnectionManager.DataMartConnectionFactory();
			String sql = "select sum(this_.BET) as BET, sum(this_.WIN) as WIN, sum(this_.GAMES_PLAYED) as GAMES_PLAYED, "+
					"sum(this_.JACKPOT_WINS) as JACKPOT_WINS, sum(this_.JACKPOT_CONTRIBUTION) as JACKPOT_CONTRIBUTION, sum(this_.PREU) as PREU," +
					"sum(this_.AAMS) as AAMS, sum(this_.NET_WIN) as NET_WIN, sum(this_.HOUSE_WIN) as HOUSE_WIN, sum(this_.SUPPLIER_PROFIT) as SUPPLIER_PROFIT, " +
					"sum(this_.OPERATORS_PROFIT) as OPERATORS_PROFIT, sum(this_.BPLUS_NET_PROFIT) as BPLUS_NET_PROFIT, spaziodim.GS_GAME_ID as GS_GAME_ID, " +
					"spaziodim.GAME_NAME as GAME_NAME, spaziodim.AAMS_VLT_ID as AAMS_VLT_ID, spaziodim.GS_VLT_ID as GS_VLT_ID,spaziodim.AAMS_LOCATION_ID as AAMS_LOCATION_ID, " +
					"spaziodim.LOCATION_NAME as LOCATION_NAME, clientidim.COD_GESTORE as COD_GESTORE, spaziodim.REGION as REGIONE, " +
					"spaziodim.SIGLA_PROVINCIA as PROVINCIA, spaziodim.CITY as COMUNE, spaziodim.LOCATION_ADDRESS as LOCATION_ADDRESS, spaziodim.NUMERO_CIVICO as NUMERO_CIVICO,spaziodim.TOPONIMO AS TOPONIMO, ";
					if (mostraTotale){
						sql=sql+"count(spaziodim.GS_VLT_ID) as GIORNI_ATTIVITA, ";
					}
					else {
						sql=sql+"tempodim.DATA as DATA, ";
					}
					sql=sql+"this_.STATO as STATO " +
					"from METERFACT this_ " +
					"inner join TEMPODIM tempodim on this_.DMTEMPO_ID=tempodim.ID " +
					"inner join CLIENTIDIM clientidim on this_.DMCLIENTI_ID=clientidim.ID " +
					"inner join SPAZIODIM spaziodim on this_.DMSPAZIO_ID=spaziodim.ID ";
			if (nascondiSaleNonAperte){
				sql=sql+" inner join BIRSLOCATION loc on spaziodim.aams_location_id = loc.aams_location_id ";
			}
			sql=sql+"where tempodim.DATA between ? and ? ";	//2 punti interrogativi
			if (nascondiSaleNonAperte){
				sql=sql+" and loc.opening_date is not null and loc.opening_date <= ? ";
			}
			
			if((location_name!=null && !location_name.equals(""))  || (aams_location_id!=null && !aams_location_id.equals(""))
					|| (aams_vlt_id!=null && !aams_vlt_id.equals(""))
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
				if (aams_vlt_id!=null && !aams_vlt_id.equals("")){
					if (and) sql=sql+" AND ";
					sql=sql+"lower(spaziodim.AAMS_VLT_ID) like lower('%"+aams_vlt_id+"%') ";
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
				sql=sql+" group by spaziodim.GS_GAME_ID, spaziodim.GAME_NAME, spaziodim.AAMS_VLT_ID, spaziodim.GS_VLT_ID, spaziodim.AAMS_LOCATION_ID, " +
						"spaziodim.LOCATION_NAME, clientidim.COD_GESTORE, spaziodim.REGION, spaziodim.SIGLA_PROVINCIA, spaziodim.CITY, " +
						"spaziodim.LOCATION_ADDRESS, spaziodim.NUMERO_CIVICO, spaziodim.TOPONIMO, ";
				if (!mostraTotale){
					sql=sql+"tempodim.DATA, ";
				}
				sql=sql+"this_.STATO ";
				
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
				 else if (sortColumn!=null && sortColumn.equals("tempodim.data")){
					 if (!mostraTotale)
					 sortColumn="spaziodim.LOCATION_NAME"+ascDesc+",spaziodim.AAMS_VLT_ID, spaziodim.game_name, "+sortColumn ;
					 else
						sortColumn="spaziodim.LOCATION_NAME"+ascDesc+",spaziodim.AAMS_VLT_ID, spaziodim.game_name ";
				}
				
				if(sortColumn!=null && !(sortColumn.equals("")))
					sql=sql+" ORDER BY "+sortColumn+ascDesc;								
				else if (sortColumn==null || sortColumn.equals("")){
					sql=sql+" ORDER BY spaziodim.LOCATION_NAME ASC, spaziodim.AAMS_VLT_ID ASC, spaziodim.GAME_NAME ASC";						
					if (!mostraTotale){
						sql=sql+", tempodim.DATA ASC";
					}
				}
				
				
				
				
//				if(sortColumn!=null && !(sortColumn.equals(""))){
//					sql=sql+" ORDER BY "+sortColumn;
//					if (!isAscending) sql=sql+" DESC ";
//					else sql=sql+" ASC ";
//				}				
//				else sql=sql+" ORDER BY spaziodim.LOCATION_NAME,spaziodim.AAMS_VLT_ID ASC";
				
			//logger.info(sql);
			
			ps = connDataMart.prepareStatement(sql);
			
			java.sql.Date data1_ = new java.sql.Date(data1.getTime());
			java.sql.Date data2_ = new java.sql.Date(data2.getTime());
			
			ps.setDate(1, data1_);
			ps.setDate(2, data2_);
			if (nascondiSaleNonAperte){
				ps.setDate(3, data2_);
			}
							
			rs = ps.executeQuery();
			
			meters = new ArrayList<MeterBean>();
			
			while (rs.next()){
				MeterBean mbean = new MeterBean();
				mbean.setBet(rs.getDouble("BET"));
				mbean.setWin(rs.getDouble("WIN"));
				mbean.setGamesPlayed(rs.getLong("GAMES_PLAYED"));
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
				if (!mostraTotale){
					mbean.setData(rs.getDate("DATA"));
					mbean.setGiorniGioco(0);
					}
				else {
					DateFormat df = new SimpleDateFormat("dd/MM/yyyy");					
					mbean.setPeriodo("da "+df.format(data1)+" a "+df.format(data2));
					mbean.setGiorniGioco(rs.getInt("GIORNI_ATTIVITA"));
				}				
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
	
		
	
	
	
	//GRAFICI
	
	
	
	//Ricerca dei meter per una singola VLT, mi serve per i grafici
	public List<MeterBean> retrieveMeter_vlt_graph(String aams_vlt_id, String location_name,
			String aams_location_id, String id_regione, String id_provincia, String id_comune,
			String cod_gestore, Date data1, Date data2, String sortColumn, boolean isAscending)  throws DataAccessException {

		List<MeterBean> meters = null;
		ResultSet rs=null;
		PreparedStatement ps=null;
		Connection connDataMart=null;
			
		
		try {
			connDataMart=DBConnectionManager.DataMartConnectionFactory();
			String sql = "select sum(this_.BET) as BET, sum(this_.WIN) as WIN, sum(this_.GAMES_PLAYED) as GAMES_PLAYED, " +
					"max(this_.TOTAL_IN) as TOTAL_IN, max(this_.TOTAL_OUT) as TOTAL_OUT,max(this_.TICKET_IN) as TICKET_IN, " +
					"max(this_.TICKET_OUT) as TICKET_OUT, max(this_.COIN_IN) as COIN_IN, max(this_.BILL_IN) as BILL_IN, max(this_.CARD_IN) as CARD_IN, " +
					"max(this_.TOTAL_PREPAID_IN) as TOTAL_PREPAID_IN, sum(this_.JACKPOT_WINS) as JACKPOT_WINS, " +
					"sum(this_.JACKPOT_CONTRIBUTION) as JACKPOT_CONTRIBUTION, sum(this_.PREU) as PREU, " +
					"sum(this_.AAMS) as AAMS, sum(this_.NET_WIN) as NET_WIN, sum(this_.HOUSE_WIN) as HOUSE_WIN, sum(this_.SUPPLIER_PROFIT) as SUPPLIER_PROFIT, " +
					"sum(this_.OPERATORS_PROFIT) as OPERATORS_PROFIT, sum(this_.BPLUS_NET_PROFIT) as BPLUS_NET_PROFIT, spaziodim.LOCATION_NAME as LOCATION_NAME, " +
					"clientidim.COD_GESTORE as COD_GESTORE, spaziodim.AAMS_LOCATION_ID as AAMS_LOCATION_ID, spaziodim.REGION as REGIONE, " +
					"spaziodim.SIGLA_PROVINCIA as PROVINCIA, spaziodim.CITY as COMUNE, spaziodim.LOCATION_ADDRESS as LOCATION_ADDRESS, spaziodim.NUMERO_CIVICO as NUMERO_CIVICO,spaziodim.TOPONIMO AS TOPONIMO, " +
					"spaziodim.AAMS_VLT_ID as AAMS_VLT_ID, spaziodim.GS_VLT_ID as GS_VLT_ID, tempodim.DATA as DATA, this_.STATO as STATO " +
//					",regioni2_.NOME as NOME_REGIONE, province3_.NOME as NOME_PROVINCIA, comuni4_.NOME as NOME_COMUNE " +
					"from METERFACT this_ " +
					"inner join TEMPODIM tempodim on this_.DMTEMPO_ID=tempodim.ID " +
					"inner join CLIENTIDIM clientidim on this_.DMCLIENTI_ID=clientidim.ID " +
					"left outer join SPAZIODIM spaziodim on this_.DMSPAZIO_ID=spaziodim.ID " +
//					"left outer join PROVINCE province3_ on spaziodim.ID_PROVINCIA=province3_.ID_PROV " +
//					"left outer join REGIONI regioni2_ on spaziodim.ID_REGIONE=regioni2_.ID_REG " +
//					"left outer join COMUNI comuni4_ on spaziodim.ID_COMUNE=comuni4_.ID_COMUNE " +
					"where tempodim.DATA between ? and ? and spaziodim.AAMS_VLT_ID='"+aams_vlt_id+"' ";	//2 punti interrogativi
			
			
			if((location_name!=null && !location_name.equals(""))  || (aams_location_id!=null && !aams_location_id.equals(""))
					|| (id_regione!=null && !id_regione.equals("") && !id_regione.equals(" - ")) 
					|| (id_provincia!=null && !id_provincia.equals("") && !id_provincia.equals(" - "))
					|| (id_comune!=null && !id_comune.equals("") && !id_comune.equals("-1")) 
					|| (cod_gestore!=null && !cod_gestore.equals(""))){
				sql = sql+"AND ";
				boolean and = false;
				//commentato perché andava in errore con delle sale che avevano un ' nel nome 
//				if (location_name!=null && !location_name.equals("")){
//					sql=sql+"lower(spaziodim.LOCATION_NAME) like lower('%"+location_name+"%') ";
//					and = true;
//				}
				if (aams_location_id!=null && !aams_location_id.equals("")){
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
					
				sql=sql+" group by spaziodim.LOCATION_NAME, clientidim.COD_GESTORE, spaziodim.AAMS_LOCATION_ID, spaziodim.REGION, spaziodim.SIGLA_PROVINCIA, " +
					"spaziodim.CITY, spaziodim.LOCATION_ADDRESS,spaziodim.NUMERO_CIVICO,spaziodim.TOPONIMO, spaziodim.AAMS_VLT_ID, spaziodim.GS_VLT_ID, tempodim.DATA, this_.STATO ";
				
				if (sortColumn.equals("tempodim.DATA")){
					sortColumn="spaziodim.AAMS_VLT_ID, spaziodim.AAMS_LOCATION_ID, "+sortColumn;
				}
				
				if(sortColumn!=null && !(sortColumn.equals(""))){
					sql=sql+" ORDER BY "+sortColumn;
					if (!isAscending) sql=sql+" DESC ";
					else sql=sql+" ASC ";
				}				
				else sql=sql+" ORDER BY spaziodim.AAMS_VLT_ID,spaziodim.AAMS_LOCATION_ID ASC";
			
			
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
				
				String nota = "";
				if (rs.getString("AAMS_VLT_ID") == null || rs.getString("GS_VLT_ID") == null
						|| rs.getString("COD_GESTORE") == null
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
			logger.error("retrieveMeter_vlt failed", e);
			e.printStackTrace();
			throw new DataAccessException(IErrorCodes.DB_ERROR_KEY, e.toString(), e);
		}
		catch (Exception e) {
			logger.error("retrieveMeter_vlt failed", e);
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
	
	
	
	
	
	public List<MeterBean> retrieveMeter_game_graph(Long gs_game_id, String location_name,
			String aams_location_id, String id_regione, String id_provincia, String id_comune,
			String cod_gestore, Date data1, Date data2, String sortColumn, boolean isAscending)  throws DataAccessException {

		List<MeterBean> meters = null;
		ResultSet rs=null;
		PreparedStatement ps=null;
		Connection connDataMart=null;
			
		
		try {
			connDataMart=DBConnectionManager.DataMartConnectionFactory();
			String sql = "select sum(this_.BET) as BET, sum(this_.WIN) as WIN, sum(this_.GAMES_PLAYED) as GAMES_PLAYED, max(this_.TOTAL_IN) as TOTAL_IN, " +					
					"sum(this_.JACKPOT_WINS) as JACKPOT_WINS, " +
					"sum(this_.JACKPOT_CONTRIBUTION) as JACKPOT_CONTRIBUTION, sum(this_.PREU) as PREU,sum(this_.AAMS) as AAMS, sum(this_.NET_WIN) as NET_WIN, " +
					"sum(this_.HOUSE_WIN) as HOUSE_WIN, sum(this_.SUPPLIER_PROFIT) as SUPPLIER_PROFIT, sum(this_.OPERATORS_PROFIT) as OPERATORS_PROFIT, " +
					"sum(this_.BPLUS_NET_PROFIT) as BPLUS_NET_PROFIT, spaziodim.GS_GAME_ID as GS_GAME_ID, spaziodim.GAME_NAME as GAME_NAME, ";
					if((location_name!=null && !location_name.equals(""))  || (aams_location_id!=null && !aams_location_id.equals("")  && !aams_location_id.equals(" - "))){
						sql = sql+"spaziodim.AAMS_LOCATION_ID as AAMS_LOCATION_ID, spaziodim.LOCATION_NAME as LOCATION_NAME, ";
					}
					sql=sql+"tempodim.DATA as DATA " +
					"from METERFACT this_ " +
					"inner join TEMPODIM tempodim on this_.DMTEMPO_ID=tempodim.ID " +
					"left outer join SPAZIODIM spaziodim on this_.DMSPAZIO_ID=spaziodim.ID " +
					"where tempodim.DATA between ? and ? AND spaziodim.GS_GAME_ID="+gs_game_id+" ";	//2 punti interrogativi
			
			if((location_name!=null && !location_name.equals(""))  || (aams_location_id!=null && !aams_location_id.equals("")  && !aams_location_id.equals(" - "))){
				sql = sql+"AND ";
				boolean and = false;
				if (location_name!=null && !location_name.equals("")){
					sql=sql+"lower(spaziodim.LOCATION_NAME) like lower('%"+location_name+"%') ";
					and = true;
				}
				if (aams_location_id!=null && !aams_location_id.equals("") && !aams_location_id.equals(" - ")){
					if (and) sql=sql+" AND ";
					sql=sql+"lower(spaziodim.AAMS_LOCATION_ID) like lower('%"+aams_location_id+"%') ";				
				}
				sql=sql+" group by spaziodim.GS_GAME_ID, spaziodim.GAME_NAME, spaziodim.AAMS_LOCATION_ID, spaziodim.LOCATION_NAME, tempodim.DATA ";
				
			} else sql=sql+" group by spaziodim.GS_GAME_ID, spaziodim.GAME_NAME, tempodim.DATA ";
					
				
				sql=sql+" order by tempodim.DATA ASC ";
			
			
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
				mbean.setJackpotWins(rs.getDouble("JACKPOT_WINS"));
				mbean.setJackpotContribution(rs.getDouble("JACKPOT_CONTRIBUTION"));
				mbean.setPreu(rs.getDouble("PREU"));
				mbean.setAams(rs.getDouble("AAMS"));
				mbean.setNetWin(rs.getDouble("NET_WIN"));
				mbean.setHouseWin(rs.getDouble("HOUSE_WIN"));
				mbean.setSupplierProfit(rs.getDouble("SUPPLIER_PROFIT"));
				mbean.setOperatorsProfit(rs.getDouble("OPERATORS_PROFIT"));
				mbean.setBplusNetProfit(rs.getDouble("BPLUS_NET_PROFIT"));				
				mbean.setData(rs.getDate("DATA"));
				mbean.setCode_game(rs.getLong("GS_GAME_ID"));
				mbean.setGame_name(rs.getString("GAME_NAME"));
							
				meters.add(mbean);
			}
//			logger.info("retrieveMeter_game_graph: "+meters.size());
//			logger.info(sql);
			
		} catch (SQLException e) {
			logger.error("retrieveMeter_game_graph failed", e);
			e.printStackTrace();
			throw new DataAccessException(IErrorCodes.DB_ERROR_KEY, e.toString(), e);
		}
		catch (Exception e) {
			logger.error("retrieveMeter_game_graph failed", e);
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
	
	
	
	
	public List<MillionVltBean> retrieveMeter_vlt_milionarie(Date data1)  throws DataAccessException {
		
		List<MillionVltBean> meters = null;
		ResultSet rs=null;
		PreparedStatement ps=null;
		Connection connDataMart=null;
		
		try {
			connDataMart=DBConnectionManager.DataMartConnectionFactory();
			DateFormat df = new SimpleDateFormat(IConstants.DATE_FORMAT);
			
			String sql = "select vista1.*, vista2.* " +
					" from  (SELECT aams_location_id,location_name , count(distinct(aams_vlt_id)) as num_vlt_attive, sum(sum_bet) as total_bet_sala, avg(sum_bet) as media_bet_sala " +
					"       FROM (SELECT sdim.aams_location_id as aams_location_id,sdim.location_name as location_name, sdim.aams_vlt_id as aams_vlt_id, sum(mf.bet) as sum_bet " +
					"             FROM meterfact mf " +
					"             inner join tempodim tdim on mf.dmtempo_id=tdim.id " +
					"             left outer join spaziodim sdim on mf.dmspazio_id = sdim.id " +
					"             where tdim.data between TO_DATE('"+df.format(data1)+" 00:00:00','"+IConstants.DATE_FORMAT_3+"') and TO_DATE('"+df.format(data1)+" 23:59:59','"+IConstants.DATE_FORMAT_3+"') " +
					"             group by sdim.aams_location_id, sdim.location_name, sdim.aams_vlt_id) " +
					"       group by aams_location_id, location_name) vista1 " +
					"right join (SELECT sdim.aams_location_id, sdim.aams_vlt_id as aams_vlt_id, sdim.gs_vlt_id as gs_vlt_id, sum(mf.bet) as bet_vlt " +
					"            FROM meterfact mf " +
					"            inner join tempodim tdim on mf.dmtempo_id=tdim.id " +
					"            left outer join spaziodim sdim on mf.dmspazio_id = sdim.id " +
					"            where tdim.data between TO_DATE('"+df.format(data1)+" 00:00:00','"+IConstants.DATE_FORMAT_3+"') and TO_DATE('"+df.format(data1)+" 23:59:59','"+IConstants.DATE_FORMAT_3+"') " +
					"            group by sdim.aams_location_id, sdim.aams_vlt_id, sdim.gs_vlt_id " +
					"            having sum(mf.bet)>=15000 " +
					"           order by sdim.aams_location_id asc) vista2 " +
					"on vista1.aams_location_id=vista2.aams_location_id " +
					"order by vista1.location_name asc ";		
			
			//logger.info(sql);
			
			ps = connDataMart.prepareStatement(sql);
								
			rs = ps.executeQuery();
			
			meters = new ArrayList<MillionVltBean>();
			
			while (rs.next()){
				MillionVltBean mvb = new MillionVltBean();
				mvb.setAams_location_id(rs.getString("AAMS_LOCATION_ID"));
				mvb.setLocation_name(rs.getString("LOCATION_NAME"));
				mvb.setNum_vlt(rs.getInt("NUM_VLT_ATTIVE"));
				mvb.setTotal_bet_sala(rs.getDouble("TOTAL_BET_SALA"));
//				String s = rs.getString("MEDIA_BET_SALA");
//				s.replace(".", ",");
//				Double d = Double.valueOf(s);
//				BigDecimal b = BigDecimal.valueOf(d);
//				mvb.setMedia_bet_vlt(b);
				mvb.setMedia_bet_vlt(rs.getDouble("MEDIA_BET_SALA"));
				mvb.setMax_bet_vlt(rs.getDouble("BET_VLT"));
				mvb.setAams_vlt_id_max_bet(rs.getString("AAMS_VLT_ID"));
				mvb.setGs_vlt_id(rs.getString("GS_VLT_ID"));
				
				Double pmb_a = mvb.getMax_bet_vlt() / mvb.getTotal_bet_sala();
				pmb_a = pmb_a * 100;
				int pmb = pmb_a.intValue();
//				double pmb_d = (mvb.getMax_bet_vlt().doubleValue() / mvb.getTotal_bet_sala().doubleValue()) * 100;
//				BigDecimal pmb = new BigDecimal(pmb_d);
//				pmb.setScale(0, RoundingMode.HALF_UP);
				mvb.setPercent_max_bet(pmb);
				
				medie_vlt_ultimi_7_e_30gg(data1, mvb,connDataMart);
				
				meters.add(mvb);				
			}
			
			
		} catch (SQLException e) {
			logger.error("retrieveMeter_vlt_milionarie: SQL failed", e);
			e.printStackTrace();
			throw new DataAccessException(IErrorCodes.DB_ERROR_KEY, e.toString(), e);
		}
		
		catch (Exception e) {
			logger.error("retrieveMeter_vlt_milionarie failed", e);
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
	
	
	public void medie_vlt_ultimi_7_e_30gg(Date data, MillionVltBean mvb, Connection connDataMart)  throws DataAccessException {
	
		ResultSet rs=null;
		PreparedStatement ps=null;
				
		try {
			
			DateFormat df = new SimpleDateFormat(IConstants.DATE_FORMAT);
			
			Date data1ggPrima = DateUtil.calcolaGiornoPrecedente(data);
			Date data8ggPrima = DateUtil.calcola8ggPrima(data);
			Date data30ggPrima = DateUtil.calcolaMesePrecedente(data1ggPrima);
			
			String sql = "select avg(v1.bet_vlt) as MEDIA_7GG, avg(v2.bet_vlt) as MEDIA_30GG " +
					" from (SELECT tdim.data,sdim.aams_vlt_id as aams_vlt_id, sum(mf.bet) as bet_vlt " +
							" FROM meterfact mf " +
							" inner join tempodim tdim on mf.dmtempo_id=tdim.id " +
							" left outer join spaziodim sdim on mf.dmspazio_id = sdim.id " +
							" where tdim.data between TO_DATE('"+df.format(data8ggPrima)+" 00:00:00','"+IConstants.DATE_FORMAT_3+"') and TO_DATE('"+df.format(data1ggPrima)+" 00:00:00','"+IConstants.DATE_FORMAT_3+"') " +
							" and sdim.aams_vlt_id = '"+mvb.getAams_vlt_id_max_bet()+"' " +
							" GROUP BY tdim.data, sdim.aams_vlt_id) v1 " +
					" left join (SELECT tdim.data,sdim.aams_vlt_id as aams_vlt_id, sum(mf.bet) as bet_vlt " +
							" FROM meterfact mf " +
							" inner join tempodim tdim on mf.dmtempo_id=tdim.id " +
							" left outer join spaziodim sdim on mf.dmspazio_id = sdim.id " +
							" where tdim.data between TO_DATE('"+df.format(data30ggPrima)+" 00:00:00','"+IConstants.DATE_FORMAT_3+"') and TO_DATE('"+df.format(data1ggPrima)+" 00:00:00','"+IConstants.DATE_FORMAT_3+"') " +
							" and sdim.aams_vlt_id = '"+mvb.getAams_vlt_id_max_bet()+"' " +
							" GROUP BY tdim.data, sdim.aams_vlt_id) v2 on v1.aams_vlt_id=v2.aams_vlt_id ";
			
			//logger.info(sql);
			
			ps = connDataMart.prepareStatement(sql);
								
			rs = ps.executeQuery();
					
			while (rs.next()){
				mvb.setMedia_7gg(rs.getDouble("MEDIA_7GG"));
				mvb.setMedia_30gg(rs.getDouble("MEDIA_30GG"));
			}
			
			
		} catch (SQLException e) {
			logger.error("media_vlt_ultimi_7gg: SQL failed", e);
			e.printStackTrace();
			throw new DataAccessException(IErrorCodes.DB_ERROR_KEY, e.toString(), e);
		}
		
		catch (Exception e) {
			logger.error("media_vlt_ultimi_7gg failed", e);
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
	}	

	
	
	
	
	
	
	//METODI PER IL CONFRONTO 600/SOGEI
	public Map<String,MeterBean> retrieveMeterMap_locations(String location_name,
			String aams_location_id, String id_regione, String id_provincia, String id_comune,
			String cod_gestore, Date data1, Date data2)  throws DataAccessException {
		
		Map<String,MeterBean> meters = null;
		ResultSet rs=null;
		PreparedStatement ps=null;
		Connection connDataMart=null;
		
		try {
			connDataMart=DBConnectionManager.DataMartConnectionFactory();
						
			String sql = "SELECT vista1.*, vista2.num_vlt, vista2.TOTAL_IN, vista2.TOTAL_OUT, vista2.TICKET_IN, vista2.TICKET_OUT, vista2.COIN_IN, vista2.BILL_IN, vista2.CARD_IN, vista2.TOTAL_PREPAID_IN " +
			"FROM " +
			"(select sum(this_.BET) as BET, sum(this_.WIN) as WIN, sum(this_.GAMES_PLAYED) as GAMES_PLAYED, " +
			"sum(this_.JACKPOT_WINS) as JACKPOT_WINS, sum(this_.JACKPOT_CONTRIBUTION) as JACKPOT_CONTRIBUTION, sum(this_.PREU) as PREU, " +
			"sum(this_.AAMS) as AAMS, sum(this_.NET_WIN) as NET_WIN, sum(this_.HOUSE_WIN) as HOUSE_WIN, sum(this_.SUPPLIER_PROFIT) as SUPPLIER_PROFIT, sum(this_.OPERATORS_PROFIT) as OPERATORS_PROFIT, " +
			"sum(this_.BPLUS_NET_PROFIT) as BPLUS_NET_PROFIT, spaziodim1_.AAMS_LOCATION_ID as AAMS_LOCATION_ID, spaziodim1_.LOCATION_NAME as LOCATION_NAME, clientidim6_.COD_GESTORE as COD_GESTORE, " +
			"spaziodim1_.LOCATION_ADDRESS as LOCATION_ADDRESS, spaziodim1_.NUMERO_CIVICO as NUMERO_CIVICO,spaziodim1_.TOPONIMO AS TOPONIMO,tempodim5_.DATA as DATA, spaziodim1_.REGION as REGIONE, spaziodim1_.SIGLA_PROVINCIA as PROVINCIA, spaziodim1_.CITY as COMUNE " +
			"from METERFACT this_ inner join TEMPODIM tempodim5_ on this_.DMTEMPO_ID=tempodim5_.ID " +
			"inner join CLIENTIDIM clientidim6_ on this_.DMCLIENTI_ID=clientidim6_.ID " +
			"left outer join SPAZIODIM spaziodim1_ on this_.DMSPAZIO_ID=spaziodim1_.ID " +
			"GROUP BY spaziodim1_.AAMS_LOCATION_ID, spaziodim1_.LOCATION_NAME, clientidim6_.COD_GESTORE, spaziodim1_.LOCATION_ADDRESS,spaziodim1_.NUMERO_CIVICO,spaziodim1_.TOPONIMO, tempodim5_.DATA, spaziodim1_.REGION, spaziodim1_.SIGLA_PROVINCIA, spaziodim1_.CITY " +
			"order by tempodim5_.DATA desc) vista1 " +
			"LEFT OUTER JOIN " +
			"(select AAMS_LOCATION_ID, count(distinct(vlt_id)) as num_vlt, SUM(TOTAL_IN) AS TOTAL_IN, SUM(TOTAL_OUT) AS TOTAL_OUT, SUM(TICKET_IN) AS TICKET_IN, SUM(TICKET_OUT) AS TICKET_OUT, SUM(COIN_IN) AS COIN_IN, SUM(BILL_IN) AS BILL_IN, SUM(CARD_IN) AS CARD_IN, SUM(TOTAL_PREPAID_IN) AS TOTAL_PREPAID_IN , DATA AS DATA, " +
			"ID_REGIONE AS ID_REGIONE, ID_PROVINCIA AS ID_PROVINCIA, ID_COMUNE AS ID_COMUNE " +
			"from (select distinct(spaziodim2_.AAMS_VLT_ID) as vlt_id, TOTAL_IN, TOTAL_OUT,TICKET_IN, TICKET_OUT,COIN_IN, BILL_IN, " +
			"CARD_IN, TOTAL_PREPAID_IN, spaziodim2_.AAMS_LOCATION_ID as AAMS_LOCATION_ID, spaziodim2_.ID_REGIONE AS ID_REGIONE, spaziodim2_.ID_PROVINCIA AS ID_PROVINCIA, spaziodim2_.ID_COMUNE AS ID_COMUNE, tempodim8_.data as DATA " +
			"from meterfact inner join TEMPODIM tempodim8_ on DMTEMPO_ID=tempodim8_.ID  " +
			"left outer join SPAZIODIM spaziodim2_ on DMSPAZIO_ID=spaziodim2_.ID) " +
			"group by AAMS_LOCATION_ID, DATA, ID_REGIONE, ID_PROVINCIA, ID_COMUNE) vista2 " +
			"on vista1.AAMS_LOCATION_ID=vista2.AAMS_LOCATION_ID and vista1.DATA=vista2.DATA ";
			
			if((location_name!=null && !location_name.equals(""))  || (aams_location_id!=null && !aams_location_id.equals(""))
					|| (id_regione!=null && !id_regione.equals("") && !id_regione.equals(" - ")) 
					|| (id_provincia!=null && !id_provincia.equals("") && !id_provincia.equals(" - "))
					|| (id_comune!=null && !id_comune.equals("") && !id_comune.equals("-1")) 
					|| (cod_gestore!=null && !cod_gestore.equals("")) ||	data1!=null || data2!=null){
				sql = sql+"WHERE ";
				boolean and = false;
				if (location_name!=null && !location_name.equals("")){
					sql=sql+"lower(vista1.LOCATION_NAME) like lower('%"+location_name+"%') ";
					and = true;
				}
				if (aams_location_id!=null && !aams_location_id.equals("")){
					if (and) sql=sql+" AND ";
					sql=sql+"lower(vista1.AAMS_LOCATION_ID) like lower('%"+aams_location_id+"%') ";
					and = true;
				}
				if (id_regione!=null && !id_regione.equals("") && !id_regione.equals(" - ")){
					if (and) sql=sql+" AND ";
					sql=sql+"vista2.ID_REGIONE = "+id_regione+" ";
					and = true;
				}
				if (id_provincia!=null && !id_provincia.equals("") && !id_provincia.equals(" - ")){
					if (and) sql=sql+" AND ";
					sql=sql+"vista2.ID_PROVINCIA = "+id_provincia+" ";
					and = true;
				}
				if (id_comune!=null && !id_comune.equals("") && !id_comune.equals("-1")){
					if (and) sql=sql+" AND ";
					sql=sql+"vista2.ID_COMUNE = "+id_comune+" ";
					and = true;
				}
				if (cod_gestore!=null && !cod_gestore.equals("")){
					if (and) sql=sql+" AND ";
					sql=sql+"lower(vista1.COD_GESTORE) like lower('%"+cod_gestore+"%') ";
					and = true;
				}
				if (data1!=null && data2!=null){
					DateFormat df = new SimpleDateFormat(IConstants.DATE_FORMAT);
					if (and) sql=sql+" AND ";
					sql=sql+"vista1.DATA between TO_DATE('"+df.format(data1)+"','"+IConstants.DATE_FORMAT+"') AND TO_DATE('"+df.format(data2)+"','"+IConstants.DATE_FORMAT+"') ";
					and = false;
				}
				else if (data1!=null){
					DateFormat df = new SimpleDateFormat(IConstants.DATE_FORMAT);					
					if (and) sql=sql+" AND ";
					sql=sql+"vista1.DATA = TO_DATE('"+df.format(data1)+"','"+IConstants.DATE_FORMAT+"') ";
					and = false;
				}
				else if (data2!=null){
					DateFormat df = new SimpleDateFormat(IConstants.DATE_FORMAT);
					if (and) sql=sql+" AND ";
					sql=sql+"vista1.DATA = TO_DATE('"+df.format(data2)+"','"+IConstants.DATE_FORMAT+"') ";
					and = false;
				}				
			} 
			
			
			//ORDINAMENTO
//			String ascDesc = null;
//			if (!isAscending)
//				ascDesc = " DESC ";
//			else if (isAscending)
//				ascDesc = " ASC ";
//			
//			
//			if (sortColumn!=null && sortColumn.equals("vista2.num_vlt"))
//				sortColumn=sortColumn+ascDesc+", vista1.DATA"+ascDesc+", vista1.LOCATION_NAME";
//			else if (sortColumn!=null && sortColumn.equals("vista1.DATA"))
//				sortColumn=sortColumn+", vista1.LOCATION_NAME";
//			 else if (sortColumn!=null && sortColumn.equals("vista1.provincia"))
//				sortColumn="vista1.provincia"+ascDesc+",vista1.COMUNE"+ascDesc+",vista1.LOCATION_NAME";
//			 else if (sortColumn!=null && sortColumn.equals("vista1.comune"))
//				sortColumn="vista1.COMUNE"+ascDesc+",vista1.LOCATION_NAME";
//							
//			if(sortColumn!=null && !(sortColumn.equals("")))
//				sql=sql+" ORDER BY "+sortColumn+ascDesc;								
//			else if (sortColumn==null || sortColumn.equals(""))
//				sql=sql+" ORDER BY vista1.DATA ASC, vista1.LOCATION_NAME ASC";		
			
			//logger.info(sql);
			ps = connDataMart.prepareStatement(sql);
								
			rs = ps.executeQuery();
			
			meters = new HashMap<String,MeterBean>();
			
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
				mbean.setData(rs.getDate("DATA"));
				mbean.setNumVlt(rs.getInt("NUM_VLT"));
				
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
				
				String nota = "";
				if (rs.getString("COD_GESTORE") == null
						|| rs.getString("LOCATION_ADDRESS") == null) {
					
					nota = "Dati mancanti su: ";
					boolean virgola = false;
										
					if (rs.getString("COD_GESTORE") == null) {
						nota += "gestore";
						virgola=true;
					}
					if (rs.getString("LOCATION_ADDRESS") == null) {
						if (virgola==true)
							nota=nota+", ";
						nota += "sala";
					}			
				}
				mbean.setNote(nota);
							
				meters.put(mbean.getAams_location_id()+" - "+mbean.getData().getTime(),mbean);
			}			
		} catch (SQLException e) {
			logger.error("retrieveMeterMap_locations: SQL failed", e);
			e.printStackTrace();
			throw new DataAccessException(IErrorCodes.DB_ERROR_KEY, e.toString(), e);
		}		
		catch (Exception e) {
			logger.error("retrieveMeterMap_locations failed", e);
			e.printStackTrace();
			throw new DataAccessException(IErrorCodes.DB_ERROR_KEY, e.toString(), e);
		}
		finally{
				try {
					DBConnectionManager.CloseConnection(rs, ps, connDataMart);				
				} catch (DataAccessException e) {
					e.printStackTrace();
					logger.error(e);
				}
		}
		return meters;
	}	

	
	
	public Map<String,MeterBean> retrieveMeterMap_vlt(String location_name,
			String aams_location_id, String id_regione, String id_provincia, String id_comune,
			String cod_gestore, Date data1, Date data2)  throws DataAccessException {

		Map<String,MeterBean> meters = null;
		ResultSet rs=null;
		PreparedStatement ps=null;
		Connection connDataMart=null;
			
		
		try {
			connDataMart=DBConnectionManager.DataMartConnectionFactory();
			String sql = "select sum(this_.BET) as BET, sum(this_.WIN) as WIN, sum(this_.GAMES_PLAYED) as GAMES_PLAYED, " +
					"max(this_.TOTAL_IN) as TOTAL_IN, max(this_.TOTAL_OUT) as TOTAL_OUT," +
					"spaziodim.LOCATION_NAME as LOCATION_NAME, " +
					"spaziodim.AAMS_LOCATION_ID as AAMS_LOCATION_ID, " +
					"spaziodim.AAMS_VLT_ID as AAMS_VLT_ID,spaziodim.GS_VLT_ID as GS_VLT_ID, tempodim.DATA as DATA " +					
					"from METERFACT this_ " +
					"inner join TEMPODIM tempodim on this_.DMTEMPO_ID=tempodim.ID " +
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
					and = false;
				}
			}
					
				//RAGGRUPPAMENTO
				sql=sql+" group by spaziodim.LOCATION_NAME, clientidim.COD_GESTORE, spaziodim.AAMS_LOCATION_ID, spaziodim.REGION, spaziodim.SIGLA_PROVINCIA, " +
					"spaziodim.CITY, spaziodim.LOCATION_ADDRESS,spaziodim.NUMERO_CIVICO,spaziodim.TOPONIMO, spaziodim.AAMS_VLT_ID,spaziodim.GS_VLT_ID, tempodim.DATA, this_.STATO ";
				
				//ORDINAMENTO
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
							
			
			ps = connDataMart.prepareStatement(sql);
			
			java.sql.Date data1_ = new java.sql.Date(data1.getTime());
			java.sql.Date data2_ = new java.sql.Date(data2.getTime());
			
			ps.setDate(1, data1_);
			ps.setDate(2, data2_);
							
			rs = ps.executeQuery();
			
			meters = new HashMap<String,MeterBean>();
			
			while (rs.next()){
				MeterBean mbean = new MeterBean();
				mbean.setBet(rs.getDouble("BET"));
				mbean.setWin(rs.getDouble("WIN"));
				mbean.setGamesPlayed(rs.getLong("GAMES_PLAYED"));
				mbean.setTotalIn(rs.getDouble("TOTAL_IN"));
				mbean.setTotalOut(rs.getDouble("TOTAL_OUT"));	
				mbean.setLocation_name(rs.getString("LOCATION_NAME"));
				mbean.setAams_location_id(rs.getString("AAMS_LOCATION_ID"));
				mbean.setAams_vlt_id(rs.getString("AAMS_VLT_ID"));
				mbean.setGs_vlt_id(rs.getString("GS_VLT_ID"));
				mbean.setData(rs.getDate("DATA"));

				
				meters.put(mbean.getAams_vlt_id()+" - "+mbean.getData().getTime(),mbean);
			}
			
		} catch (SQLException e) {
			logger.error("retrieveMeterMap_vlt: SQL failed", e);
			e.printStackTrace();
			throw new DataAccessException(IErrorCodes.DB_ERROR_KEY, e.toString(), e);
		}
		catch (Exception e) {
			logger.error("retrieveMeterMap_vlt failed", e);
			e.printStackTrace();
			throw new DataAccessException(IErrorCodes.DB_ERROR_KEY, e.toString(), e);
		}
		finally{
				try {
					DBConnectionManager.CloseConnection(rs, ps, connDataMart);				
				} catch (DataAccessException e) {
					e.printStackTrace();
					logger.error(e);
				}
		}
		return meters;
	}	
	
	
	
	
}