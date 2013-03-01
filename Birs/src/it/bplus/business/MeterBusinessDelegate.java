package it.bplus.business;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import it.bplus.bean.MeterBean;
import it.bplus.bean.MillionVltBean;
import it.bplus.dao.MeterDAO;
import it.bplus.exception.BusinessLayerException;
import it.bplus.exception.DataAccessException;
import it.bplus.util.IErrorCodes;
import org.apache.log4j.Logger;

public class MeterBusinessDelegate {

	protected static Logger logger = Logger.getLogger(MeterBusinessDelegate.class);

	private static MeterBusinessDelegate instance;

	public static synchronized MeterBusinessDelegate getInstance() {
		if (instance == null)
		{
			synchronized(MeterBusinessDelegate.class) {      //1
				MeterBusinessDelegate inst = instance;         //2
				if (inst == null)
				{
					synchronized(MeterBusinessDelegate.class) {  //3
						instance = new MeterBusinessDelegate();
					}
					//instance = inst;               //5
				}
			}
		}
		return instance;
	}
	

	public List<MeterBean> retrieveMeter_game(String location_name,
			String aams_location_id, String aams_vlt_id, String regione, String provincia, String comune,
			String gestore, Date data1, Date data2, String sortColumn, boolean isAscending, boolean nascondiSaleNonAperte, boolean mostraTotale)
			throws BusinessLayerException {
		try {
			return MeterDAO.getInstance().retrieveMeter_game(location_name, aams_location_id, aams_vlt_id,
					regione, provincia, comune, gestore, data1, data2, sortColumn, isAscending, nascondiSaleNonAperte, mostraTotale);
		} catch (DataAccessException e) {
			throw new BusinessLayerException(IErrorCodes.DB_ERROR_KEY, e);
		}
	}
	
	
	public List<MeterBean> retrieveMeter_vlt(String location_name,
			String aams_location_id, String aams_vlt_id, String regione, String provincia, String comune,
			String gestore, Date data1, Date data2, String sortColumn, boolean isAscending, boolean nascondiSaleNonAperte, boolean mostraTotale)
			throws BusinessLayerException {
		try {
			return MeterDAO.getInstance().retrieveMeter_vlt(location_name, aams_location_id, aams_vlt_id,
					regione, provincia, comune, gestore, data1, data2, sortColumn, isAscending, nascondiSaleNonAperte, mostraTotale);
		} catch (DataAccessException e) {
			throw new BusinessLayerException(IErrorCodes.DB_ERROR_KEY, e);
		}
	}
	
	public List<MeterBean> retrieveMeter_vlt_graph(String aams_vlt_id, String location_name,
			String aams_location_id, String regione, String provincia, String comune,
			String gestore, Date data1, Date data2, String sortColumn, boolean isAscending)
			throws BusinessLayerException {
		try {
			return MeterDAO.getInstance().retrieveMeter_vlt_graph(aams_vlt_id, location_name, aams_location_id,
					regione, provincia, comune, gestore, data1, data2, sortColumn, isAscending);
		} catch (DataAccessException e) {
			throw new BusinessLayerException(IErrorCodes.DB_ERROR_KEY, e);
		}
	}
	
	public Map<String,MeterBean> retrieveMeterMap_vlt(String location_name,
			String aams_location_id, String regione, String provincia, String comune,
			String gestore, Date data1, Date data2)
			throws BusinessLayerException {
		try {
			return MeterDAO.getInstance().retrieveMeterMap_vlt(location_name, aams_location_id,
					regione, provincia, comune, gestore, data1, data2);
		} catch (DataAccessException e) {
			throw new BusinessLayerException(IErrorCodes.DB_ERROR_KEY, e);
		}
	}
	
	
	public List<MeterBean> retrieveMeter_game_graph(Long aams_game_id, String location_name,
			String aams_location_id, String regione, String provincia, String comune,
			String gestore, Date data1, Date data2, String sortColumn, boolean isAscending)
			throws BusinessLayerException {
		try {
			return MeterDAO.getInstance().retrieveMeter_game_graph(aams_game_id, location_name, aams_location_id,
					regione, provincia, comune, gestore, data1, data2, sortColumn, isAscending);
		} catch (DataAccessException e) {
			throw new BusinessLayerException(IErrorCodes.DB_ERROR_KEY, e);
		}
	}
	
	
	public List<MeterBean> retrieveMeter_regioni(String regione, Date data1, Date data2, String sortColumn, 
			boolean isAscending, boolean escludiSaleNonAperte, boolean mostraTotale)	throws BusinessLayerException {
		try {
			return MeterDAO.getInstance().retrieveMeter_regioni(regione,data1, data2, sortColumn, isAscending, escludiSaleNonAperte, mostraTotale);
		} catch (DataAccessException e) {
			throw new BusinessLayerException(IErrorCodes.DB_ERROR_KEY, e);
		}
	}	
	
	
	public List<MeterBean> retrieveMeter_locations(String location_name,
			String aams_location_id, String regione, String provincia, String comune,
			String gestore, Date data1, Date data2, String sortColumn, boolean isAscending, boolean nascondiSaleNonAperte, boolean mostraTotale)
			throws BusinessLayerException {
		try {
			return MeterDAO.getInstance().retrieveMeter_locations(location_name, aams_location_id,
					regione, provincia, comune, gestore, data1, data2, sortColumn, isAscending, nascondiSaleNonAperte, mostraTotale);
		} catch (DataAccessException e) {
			throw new BusinessLayerException(IErrorCodes.DB_ERROR_KEY, e);
		}
	}	
	
	
	public Map<String,MeterBean> retrieveMeterMap_locations(String location_name,
			String aams_location_id, String regione, String provincia, String comune,
			String gestore, Date data1, Date data2)
			throws BusinessLayerException {
		try {
			return MeterDAO.getInstance().retrieveMeterMap_locations(location_name, aams_location_id,
					regione, provincia, comune, gestore, data1, data2);
		} catch (DataAccessException e) {
			throw new BusinessLayerException(IErrorCodes.DB_ERROR_KEY, e);
		}
	}
	
	
	
	public List<MeterBean> retrieveMeterVltByLocationsId(String location_name,
			String aams_location_id, String regione, String provincia, String comune,
			String gestore, Date data1, Date data2, String sortColumn, boolean isAscending, boolean nascondiSaleNonAperte)
			throws BusinessLayerException {
		try {
			return MeterDAO.getInstance().retrieveMeter_vlt(location_name, aams_location_id, null,
					regione, provincia, comune, gestore, data1, data2, sortColumn, isAscending, nascondiSaleNonAperte, false);
		} catch (DataAccessException e) {
			throw new BusinessLayerException(IErrorCodes.DB_ERROR_KEY, e);
		}
	}	
	
	public List<MillionVltBean> retrieveMeter_vlt_milionarie(Date data1)
			throws BusinessLayerException {
		try {
			return MeterDAO.getInstance().retrieveMeter_vlt_milionarie(data1);
		} catch (DataAccessException e) {
			throw new BusinessLayerException(IErrorCodes.DB_ERROR_KEY, e);
		}
	}	
	
	
	

//	public List<MeterBean> trasformaFromMeterfactToMeterBean(Collection<Meterfact> listaMeter) {
//		List<MeterBean> lista = new ArrayList<MeterBean>();
//		for (Meterfact c : listaMeter) {
//			String giorno = c.getTempodim().getData().toString().substring(8);
//			String mese = c.getTempodim().getData().toString().substring(5,7);
//			String anno = c.getTempodim().getData().toString().substring(0,4);
//			String data = giorno.concat("/").concat(mese).concat("/").concat(anno);

//			MeterBean p = new MeterBean(c.getBet(),
//					c.getWin(), c.getGamesPlayed(), c.getTotalIn(),
//					c.getTotalOut(), c.getTicketIn(), c.getTicketOut(),
//					c.getCoinIn(), c.getBillIn(), c.getCardIn(),
//					c.getTotalPrepaidIn(), c.getJackpotWins(),
//					c.getJackpotContribution(), c.getPreu(), c.getAams(),
//					c.getNetWin(), c.getHouseWin(), c.getSupplierProfit(),
//					c.getOperatorsProfit(), c.getBplusNetProfit(),
//					c.getStato(), c.getClientidim().getLocationName(),
//					c.getTempodim().getData(), c.getClientidim()
//							.getCodGestore(), c.getSpaziodim().getAamsLocationId(), c.getSpaziodim().getRegion(),
//					c.getSpaziodim().getDistrict(), c.getSpaziodim().getCity(),
//					c.getSpaziodim().getAamsGameId(), c.getSpaziodim().getAamsVltId(), c.getSpaziodim().getLocationAddress(), null);
//			
//			MeterBean p = new MeterBean();
//			p.setBet(c.getBet());
//			p.setWin(c.getWin());
//			p.setGamesPlayed(c.getGamesPlayed());
//			p.setTotalIn(c.getTotalIn());
//			p.setTotalOut(c.getTotalOut());
//			p.setTicketIn(c.getTicketIn());
//			p.setTicketOut(c.getTicketOut());
//			p.setCoinIn(c.getCoinIn());
//			p.setBillIn(c.getBillIn());
//			p.setCardIn(c.getCardIn());
//			p.setTotalPrepaidIn(c.getTotalPrepaidIn());
//			p.setJackpotWins(c.getJackpotWins());
//			p.setJackpotContribution(c.getJackpotContribution());
//			p.setPreu(c.getPreu());
//			p.setAams(c.getAams());
//			p.setNetWin(c.getNetWin());
//			p.setHouseWin(c.getHouseWin());
//			p.setSupplierProfit(c.getSupplierProfit());
//			p.setOperatorsProfit(c.getOperatorsProfit());
//			p.setBplusNetProfit(c.getBplusNetProfit());
//			p.setStato(c.getStato());
//			p.setLocation_name(c.getClientidim().getLocationName());
//			p.setData(c.getTempodim().getData());
//			p.setCod_gestore(c.getClientidim().getCodGestore());
//			p.setAams_location_id(c.getSpaziodim().getAamsLocationId());
//			p.setRegione(c.getSpaziodim().getRegion());
//			p.setProvincia(c.getSpaziodim().getDistrict());
//			p.setComune(c.getSpaziodim().getCity());
//			p.setCode_game(c.getSpaziodim().getAamsGameId());
//			p.setAams_vlt_id(c.getSpaziodim().getAamsVltId());
//			p.setLocation_address(c.getSpaziodim().getLocationAddress());
//			
//			//controllo per vedere se manca qualche informazione, verrà inserito nel campo note
//			String nota = "";
//			if (c.getError_vlt() != null
//					|| c.getClientidim().getError_esercente() != null
//					|| c.getClientidim().getError_gestore() != null
//					|| c.getClientidim().getError_location() != null
//					|| c.getSpaziodim().getError_game() != null
//					|| c.getSistemagiocodim().getError_gamesystem() != null) {
//				
//				nota = "Dati mancanti su: ";
//				
//				if (c.getError_vlt() != null) {				
//					nota += "vlt ";
//				}
//				if (c.getClientidim().getError_esercente() != null) {
//					nota += "esercente ";
//				}
//				if (c.getClientidim().getError_gestore() != null) {
//					nota += "gestore ";
//				}
//				if (c.getClientidim().getError_location() != null) {
//					nota += "sala ";
//				}
//				if (c.getSpaziodim().getError_game() != null) {
//					nota += "gioco ";
//				}
//				if (c.getSistemagiocodim().getError_gamesystem() != null) {
//					nota += "sistema_gioco ";
//				}				
//			}
//			p.setNote(nota);
//						
//			lista.add(p);
//		}
//		return lista;
//	}
	
//	
//	public List<MeterBean> trasformaFromObjectArrayToMeterBean(Collection<Object[]> listaMeter) {
//		List<MeterBean> lista = new ArrayList<MeterBean>();
//		
//		BigDecimal bet = null;
//		BigDecimal win = null;
//		Long gamesPlayed = null;
//		BigDecimal totalIn = null;
//		BigDecimal totalOut = null;
//		BigDecimal ticketIn = null;
//		BigDecimal ticketOut = null;
//		BigDecimal coinIn = null;
//		BigDecimal billIn = null;
//		BigDecimal cardIn = null;
//		BigDecimal totalPrepaidIn = null;
//		BigDecimal jackpotWins = null;
//		BigDecimal jackpotContribution = null;
//		BigDecimal preu = null;
//		BigDecimal aams = null;
//		BigDecimal netWin = null;
//		BigDecimal houseWin = null;
//		BigDecimal supplierProfit = null;
//		BigDecimal operatorsProfit = null;
//		BigDecimal bplusNetProfit = null;
//		String stato = null;
//		String locationName = null;
//		Date data  = null;
//		String codGestore = null;
//		String aamsLocationId = null;
//		String region = null;
//		String district = null;
//		String city = null;
//		Long aamsGameId = null;
//		String gameName = null;
//		String aamsVltId = null;
//		String locationAddress = null;
//		String note = "";
//		
//		for (Object[] c : listaMeter) {
//			
//			bet = (BigDecimal)c[0];
//			win = (BigDecimal)c[1];
//			gamesPlayed = (Long)c[2];
//			totalIn = (BigDecimal)c[3];
//			totalOut = (BigDecimal)c[4];
//			ticketIn = (BigDecimal)c[5];
//			ticketOut = (BigDecimal)c[6];
//			coinIn = (BigDecimal)c[7];
//			billIn = (BigDecimal)c[8];
//			cardIn = (BigDecimal)c[9];
//			totalPrepaidIn = (BigDecimal)c[10];
//			jackpotWins = (BigDecimal)c[11];
//			jackpotContribution = (BigDecimal)c[12];
//			preu = (BigDecimal)c[13];
//			aams = (BigDecimal)c[14];
//			netWin = (BigDecimal)c[15];
//			houseWin = (BigDecimal)c[16];
//			supplierProfit = (BigDecimal)c[17];
//			operatorsProfit = (BigDecimal)c[18];
//			bplusNetProfit = (BigDecimal)c[19];			
//			aamsGameId = (Long)c[20];   //
//			gameName = (String)c[21]; //
//			aamsVltId = (String)c[22]; //
//			aamsLocationId = (String)c[23];			
//			locationName = (String)c[24];
//			codGestore = (String)c[25];
//			region = (String)c[26];
//			city = (String)c[27];
//			district = (String)c[28];			
//			locationAddress = (String)c[29];
//			data = (Date)c[30];			
//			stato = (String)c[31];
//			
//			if (codGestore==null || city ==null || stato==null || aamsGameId==null){
//				note = "Dati mancanti:";
//				if (aamsGameId==null){
//					note+=" gioco";
//					if (city ==null || stato==null || aamsGameId==null)
//						note+=",";
//				}
//				if (codGestore==null){
//					note+=" gestore";
//					if(city ==null || stato==null)
//						note+=",";
//				}
//				if (city==null){
//					note+=" sala";
//					if(stato==null)
//						note+=",";
//				}
//				if (stato==null)
//					note+=" vlt";
//			}
//
//			MeterBean p = new MeterBean(bet, win, gamesPlayed, totalIn,
//					totalOut, ticketIn, ticketOut, coinIn, billIn, cardIn,
//					totalPrepaidIn, jackpotWins, jackpotContribution, preu,
//					aams, netWin, houseWin, supplierProfit, operatorsProfit,
//					bplusNetProfit, stato, locationName, data, 
//					codGestore, aamsLocationId, region, district, city,
//					gameName, aamsVltId, locationAddress, note);
//			
//			
//			
//			
//			
//	
//			lista.add(p);
//		}
//		return lista;
//	}	
//	
//	
//	
//	public List<MeterBean> trasformaFromObjectArrayToMeterBean_vlt(Collection<Object[]> listaMeter) {
//		List<MeterBean> lista = new ArrayList<MeterBean>();
//		
//		BigDecimal bet = null;
//		BigDecimal win = null;
//		Long gamesPlayed = null;
//		BigDecimal totalIn = null;
//		BigDecimal totalOut = null;
//		BigDecimal ticketIn = null;
//		BigDecimal ticketOut = null;
//		BigDecimal coinIn = null;
//		BigDecimal billIn = null;
//		BigDecimal cardIn = null;
//		BigDecimal totalPrepaidIn = null;
//		BigDecimal jackpotWins = null;
//		BigDecimal jackpotContribution = null;
//		BigDecimal preu = null;
//		BigDecimal aams = null;
//		BigDecimal netWin = null;
//		BigDecimal houseWin = null;
//		BigDecimal supplierProfit = null;
//		BigDecimal operatorsProfit = null;
//		BigDecimal bplusNetProfit = null;
//		String stato = null;
//		String locationName = null;
//		Date data  = null;
//		String codGestore = null;
//		String aamsLocationId = null;
//		String region = null;
//		String district = null;
//		String city = null;
//		String aamsVltId = null;
//		String locationAddress = null;
//		String note = "";
//		
//		
//		for (Object[] c : listaMeter) {
//			
//			bet = (BigDecimal)c[0];
//			win = (BigDecimal)c[1];
//			gamesPlayed = (Long)c[2];
//			totalIn = (BigDecimal)c[3];
//			totalOut = (BigDecimal)c[4];
//			ticketIn = (BigDecimal)c[5];
//			ticketOut = (BigDecimal)c[6];
//			coinIn = (BigDecimal)c[7];
//			billIn = (BigDecimal)c[8];
//			cardIn = (BigDecimal)c[9];
//			totalPrepaidIn = (BigDecimal)c[10];
//			jackpotWins = (BigDecimal)c[11];
//			jackpotContribution = (BigDecimal)c[12];
//			preu = (BigDecimal)c[13];
//			aams = (BigDecimal)c[14];
//			netWin = (BigDecimal)c[15];
//			houseWin = (BigDecimal)c[16];
//			supplierProfit = (BigDecimal)c[17];
//			operatorsProfit = (BigDecimal)c[18];
//			bplusNetProfit = (BigDecimal)c[19];			
//			locationName = (String)c[20];
//			codGestore = (String)c[21];
//			aamsLocationId = (String)c[22];			
//			region = (String)c[23];
//			district = (String)c[24];
//			city = (String)c[25];
//			locationAddress = (String)c[26];
//			aamsVltId = (String)c[27];						
//			data = (Date)c[28];			
//			stato = (String)c[29];
//			
//			if (codGestore==null || city ==null || stato==null){
//				note = "Dati mancanti:";
//				if (codGestore==null){
//					note+=" gestore";
//					if(city ==null || stato==null)
//						note+=",";
//				}
//				if (city==null){
//					note+=" sala";
//					if(stato==null)
//						note+=",";
//				}
//				if (stato==null)
//					note+=" vlt";
//			}
//
//			MeterBean p = new MeterBean(bet, win, gamesPlayed, totalIn,
//					totalOut, ticketIn, ticketOut, coinIn, billIn, cardIn,
//					totalPrepaidIn, jackpotWins, jackpotContribution, preu,
//					aams, netWin, houseWin, supplierProfit, operatorsProfit,
//					bplusNetProfit, stato, locationName, data, 
//					codGestore, aamsLocationId, region, district, city,
//					aamsVltId, locationAddress, note);
//	
//			lista.add(p);
//		}
//		return lista;
//	}		
//	
//	
//	public List<MeterBean> trasformaFromObjectArrayToMeterBean_locations(Collection<Object[]> listaMeter) {
//		List<MeterBean> lista = new ArrayList<MeterBean>();
//		
//		BigDecimal bet = null;
//		BigDecimal win = null;
//		Long gamesPlayed = null;
//		BigDecimal totalIn = null;
//		BigDecimal totalOut = null;
//		BigDecimal ticketIn = null;
//		BigDecimal ticketOut = null;
//		BigDecimal coinIn = null;
//		BigDecimal billIn = null;
//		BigDecimal cardIn = null;
//		BigDecimal totalPrepaidIn = null;
//		BigDecimal jackpotWins = null;
//		BigDecimal jackpotContribution = null;
//		BigDecimal preu = null;
//		BigDecimal aams = null;
//		BigDecimal netWin = null;
//		BigDecimal houseWin = null;
//		BigDecimal supplierProfit = null;
//		BigDecimal operatorsProfit = null;
//		BigDecimal bplusNetProfit = null;
//		String stato = null;
//		String locationName = null;
//		Date data  = null;
//		String codGestore = null;
//		String aamsLocationId = null;
//		String region = null;
//		String district = null;
//		String city = null;
//		String locationAddress = null;
//		String note = "";
//		
//		for (Object[] c : listaMeter) {
//			
//			bet = (BigDecimal)c[0];
//			win = (BigDecimal)c[1];
//			gamesPlayed = Long.valueOf(c[2].toString());			
//			jackpotWins = (BigDecimal)c[3];
//			jackpotContribution = (BigDecimal)c[4];
//			preu = (BigDecimal)c[5];
//			aams = (BigDecimal)c[6];
//			netWin = (BigDecimal)c[7];
//			houseWin = (BigDecimal)c[8];
//			supplierProfit = (BigDecimal)c[9];
//			operatorsProfit = (BigDecimal)c[10];
//			bplusNetProfit = (BigDecimal)c[11];		
//			
//			aamsLocationId = (String)c[12];			
//			locationName = (String)c[13];
//			codGestore = (String)c[14];
//			locationAddress = (String)c[15];	
//			data = (Date)c[16];			
//			stato = (String)c[17];
//			
//			region = (String)c[18];
//			district = (String)c[19];
//			city = (String)c[20];
//			
//			totalIn = (BigDecimal)c[21];
//			totalOut = (BigDecimal)c[22];
//			ticketIn = (BigDecimal)c[23];
//			ticketOut = (BigDecimal)c[24];
//			coinIn = (BigDecimal)c[25];
//			billIn = (BigDecimal)c[26];
//			cardIn = (BigDecimal)c[27];
//			totalPrepaidIn = (BigDecimal)c[28];
//			
//			
//			
//			if (codGestore==null || city ==null || stato==null){
//				note = "Dati mancanti:";
//				if (codGestore==null){
//					note+=" gestore";
//					if(city ==null || stato==null)
//						note+=",";
//				}
//				if (city==null){
//					note+=" sala";
//					if(stato==null)
//						note+=",";
//				}
//				if (stato==null)
//					note+=" vlt";
//			}
//			
//
//			MeterBean p = new MeterBean(bet, win, gamesPlayed, totalIn,
//					totalOut, ticketIn, ticketOut, coinIn, billIn, cardIn,
//					totalPrepaidIn, jackpotWins, jackpotContribution, preu,
//					aams, netWin, houseWin, supplierProfit, operatorsProfit,
//					bplusNetProfit, stato, locationName, data,
//					codGestore, aamsLocationId, region, district, city, locationAddress, note);
//	
//			lista.add(p);
//		}
//		return lista;
//	}		
//	
//	
//	

	//format per le cifre, fa in modo che le migliaia siano separate con il punto e i numeri decimali con la virgola
	public String puntoVirgola(BigDecimal x){
		String num = x.toString();
		String app = "";
		String ret = "";
		num = num.replace(".", ",");
		int s;

		if (num.contains(",")) {
			if (num.charAt(num.length() - 3) == ',' && num.length() > 6) {
				s = num.length() - 3;
				ret = ret.concat(num.substring(s));
				for (int i = 0; i <= s - 1; i++) {
					if (i > 0 && i % 3 == 0)
						ret = ".".concat(ret);
					app = num.substring(s - i - 1, s - i);
					ret = app.concat(ret);
				}
			}
			else if (num.charAt(num.length() - 2) == ',' && num.length() > 5) {
				s = num.length() - 2;
				ret = ret.concat(num.substring(s));
				for (int i = 0; i <= s - 1; i++) {
					if (i > 0 && i % 3 == 0)
						ret = ".".concat(ret);
					app = num.substring(s - i - 1, s - i);
					ret = app.concat(ret);
				}
			}
			else ret = num;
		} else if ((!num.contains(",")) && num.length() > 3) {
			s = num.length();
			for (int i = 0; i <= s - 1; i++) {
				if (i > 0 && i % 3 == 0)
					ret = ".".concat(ret);
				app = num.substring(s - i - 1, s - i);
				ret = app.concat(ret);

			}
			ret = ret.concat(",0");
		}

		else
			ret = num.concat(",0");

		return ret;
	}


	public String puntoVirgola(Long x){
		String num = x.toString();
		String app = "";
		String ret = "";
		num = num.replace(".", ",");
		int s;

		if (num.contains(",")) {
			if (num.charAt(num.length() - 3) == ',' && num.length() > 6) {
				s = num.length() - 3;
				ret = ret.concat(num.substring(s));
				for (int i = 0; i <= s - 1; i++) {
					if (i > 0 && i % 3 == 0)
						ret = ".".concat(ret);
					app = num.substring(s - i - 1, s - i);
					ret = app.concat(ret);
				}
			}
			else if (num.charAt(num.length() - 2) == ',' && num.length() > 5) {
				s = num.length() - 2;
				ret = ret.concat(num.substring(s));
				for (int i = 0; i <= s - 1; i++) {
					if (i > 0 && i % 3 == 0)
						ret = ".".concat(ret);
					app = num.substring(s - i - 1, s - i);
					ret = app.concat(ret);
				}
			}
			else ret = num;
		} else if ((!num.contains(",")) && num.length() > 3) {
			s = num.length();
			for (int i = 0; i <= s - 1; i++) {
				if (i > 0 && i % 3 == 0)
					ret = ".".concat(ret);
				app = num.substring(s - i - 1, s - i);
				ret = app.concat(ret);

			}
			ret = ret.concat(",0");
		}

		else
			ret = num.concat(",0");

		return ret;
	}	



	public String trasformaMese(String mese) {
		String newmese = null;
		if (mese.equals("1"))
			newmese = "Gennaio";
		if (mese.equals("2"))
			newmese = "Febbraio";
		if (mese.equals("3"))
			newmese = "Marzo";
		if (mese.equals("4"))
			newmese = "Aprile";
		if (mese.equals("5"))
			newmese = "Maggio";
		if (mese.equals("6"))
			newmese = "Giugno";
		if (mese.equals("7"))
			newmese = "Luglio";
		if (mese.equals("8"))
			newmese = "Agosto";
		if (mese.equals("9"))
			newmese = "Settembre";
		if (mese.equals("10"))
			newmese = "Ottobre";
		if (mese.equals("11"))
			newmese = "Novembre";
		if (mese.equals("12"))
			newmese = "Dicembre";

		return newmese;
	}	

//	public void insertNewMeter() throws BusinessLayerException{
//		Transaction t = null;
//		try {
//			Session session = JndiHibernateUtil.getSessionFactory().getCurrentSession();
//			t = session.beginTransaction();
//
//
//			Comuni comuni = GeographicBusinessDelegate.getInstance().findComuneByCodCatasto("F113");
//
//			Province province = comuni.getProvince();
//			Regioni regioni = province.getRegioni();
//			Nazioni nazioni = regioni.getNazioni();
//			Aree aree = regioni.getAree();
//
//			Meterfact meter = new Meterfact();
//
//			BigDecimal bet = new BigDecimal(Math.random()*10000);
//			meter.setBet(bet);
//			BigDecimal aams = new BigDecimal(Math.random()*10000);
//			meter.setAams(aams);
//			BigDecimal billIn = new BigDecimal(Math.random()*10000);
//			meter.setBillIn(billIn);
//			BigDecimal bplusNetProfit = new BigDecimal(Math.random()*10000);
//			meter.setBplusNetProfit(bplusNetProfit);
//			BigDecimal cardIn = new BigDecimal(Math.random()*10000);
//			meter.setCardIn(cardIn);
//			BigDecimal coinIn = new BigDecimal(Math.random()*10000);
//			meter.setCoinIn(coinIn);
//			long gamesPlayed = (long)(Math.random()*10000);
//			meter.setGamesPlayed(gamesPlayed);
//			BigDecimal houseWin = new BigDecimal(Math.random()*10000);
//			meter.setHouseWin(houseWin);
//			BigDecimal jackpotContribution = new BigDecimal(Math.random()*10000);
//			meter.setJackpotContribution(jackpotContribution);
//			BigDecimal jackpotWins = new BigDecimal(Math.random()*10000);
//			meter.setJackpotWins(jackpotWins);
//			BigDecimal netWin = new BigDecimal(Math.random()*10000);
//			meter.setNetWin(netWin);
//			BigDecimal operatorsProfit = new BigDecimal(Math.random()*10000);
//			meter.setOperatorsProfit(operatorsProfit);
//			BigDecimal preu = new BigDecimal(Math.random()*10000);
//			meter.setPreu(preu);
//			String stato = "attivo";
//			meter.setStato(stato);
//			BigDecimal supplierProfit = new BigDecimal(Math.random()*10000);
//			meter.setSupplierProfit(supplierProfit);
//			BigDecimal ticketIn = new BigDecimal(Math.random()*10000);
//			meter.setTicketIn(ticketIn);
//			BigDecimal ticketOut = new BigDecimal(Math.random()*10000);
//			meter.setTicketOut(ticketOut);
//			BigDecimal totalIn = new BigDecimal(Math.random()*10000);
//			meter.setTotalIn(totalIn);
//			BigDecimal totalOut = new BigDecimal(Math.random()*10000);
//			meter.setTotalOut(totalOut);
//			BigDecimal totalPrepaidIn = new BigDecimal(Math.random()*10000);
//			meter.setTotalPrepaidIn(totalPrepaidIn);
//			BigDecimal win = new BigDecimal(Math.random()*10000);
//			meter.setWin(win);
//			BigDecimal totalDrop = new BigDecimal(Math.random()*10000);
//			long aamsJackpotId = 1234567890;
//			meter.setTotalDrop(totalDrop);
//			meter.setAamsJackpotId(aamsJackpotId);
//
//			Tempodim tempodim = new Tempodim();
//
//			Date data = new Date();
//			tempodim.setData(data);
//			Short day = new Short(data.toString().substring(8, 10));
//			tempodim.setDay(day);
//			short fifteendays = (short)((day/15)+1);
//			tempodim.setFifteendays(fifteendays);
//			boolean holiday = false;
//			tempodim.setHoliday(holiday);
//			short hour = (short)data.getHours();
//			tempodim.setHour(hour);
//			short month = (short)(data.getMonth()+1);
//			tempodim.setMonth(month);
//			short sixmonth = (short)(((data.getMonth())/6)+1);
//			tempodim.setSixmonth(sixmonth);
//			short tendays = (short)((day/10)+1);
//			tempodim.setTendays(tendays);
//			short week = (short)(Math.random()*52);
//			tempodim.setWeek(week);
//			short year = (short)(2011);
//			tempodim.setYear(year);
//
//			TempoDAO.getInstance().saveNewTempodim(tempodim);
//
//			meter.setTempodim(tempodim);
//
//			Sistemagiocodim sistemagiocodim = SistemagiocoDAO.getInstance().findById(1711000045);
//
//
//			meter.setSistemagiocodim(sistemagiocodim);
//
//
//			Spaziodim spaziodim = new Spaziodim();
//
//			spaziodim.setAree(aree);
//			String city = comuni.getNome();
//			spaziodim.setCity(city);
//			String civVlt = "G555432";
//			spaziodim.setCivVlt(civVlt);
//			String codeIdVlt = "GD8889732";
//			long codGame = 123456;
//			spaziodim.setAamsGameId(codGame);
//			String codCatasto = comuni.getCadastrialCode();
//			spaziodim.setCodCatastoCity(codCatasto);
//
//
//			String codUbicazione = "R987HCEI8519";
//			spaziodim.setAamsLocationId(codUbicazione);
//			spaziodim.setComuni(comuni);
//			String district = comuni.getProvince().getSigla();
//			spaziodim.setDistrict(district);
//
//			spaziodim.setAamsVltId(codeIdVlt);
//			String nameGame = "Star Wars Poker";
//			spaziodim.setGameName(nameGame);
//			String nameUbicazione = "Casinò Sanremo";
//			spaziodim.setLocationName(nameUbicazione);
//			String nation = comuni.getProvince().getRegioni().getNazioni().getNome();
//			spaziodim.setNation(nation);
//			spaziodim.setNazioni(nazioni);
//			spaziodim.setProvince(province);
//			String region = regioni.getNome();
//			spaziodim.setRegion(region);
//			spaziodim.setRegioni(regioni);
//			String zone = regioni.getAree().getNome();
//			spaziodim.setZone(zone);
//
//			SpazioDAO.getInstance().saveNewSpaziodim(spaziodim);
//
//			meter.setSpaziodim(spaziodim);
//
//			Clientidim clientidim = new Clientidim();
//
//			clientidim.setAree(aree);
//			String cfPivaEs = "0099976628";
//			clientidim.setCfPivaEsercente(cfPivaEs);
//			String cfPivaGs = "0088885637";
//			clientidim.setCfPivaGestore(cfPivaGs);
//			clientidim.setCity(city);
//			String codEs = "ES123432";
//			clientidim.setCodEsercente(codEs);
//			String codGs = "GS7321";
//			clientidim.setCodGestore(codGs);
//			clientidim.setCodCatastoCity(codCatasto);
//			clientidim.setAamsLocationId(codUbicazione);
//			clientidim.setComuni(comuni);
//			String denominazioneGs = "Gianluca Neri";
//			clientidim.setNomeGestore(denominazioneGs);
//			clientidim.setDistrict(district);
//			String emailPecGs = "l.verdi@gmail.com";
//			clientidim.setEmailPecGestore(emailPecGs);
//			clientidim.setNation(nation);
//			clientidim.setNazioni(nazioni);
//			clientidim.setProvince(province);
//			String rappresentanteLegaleGs = "Franco Totaro";
//			clientidim.setRappresentanteLegaleGs(rappresentanteLegaleGs);
//			clientidim.setRegion(region);
//			clientidim.setRegioni(regioni);
//			
//			clientidim.setLocationName(nameUbicazione);
//			clientidim.setZone(zone);
//
//			ClientiDAO.getInstance().saveNewClientidim(clientidim);
//
//			meter.setClientidim(clientidim);
//
//			MeterDAO.getInstance().saveNewMeter(meter);
//
//			t.commit();
//		} catch (DataAccessException e) {
//			t.rollback();
//			throw new BusinessLayerException(IErrorCodes.DB_ERROR_KEY, e);
//
//		} catch (Exception e) {
//			t.rollback();
//			throw new BusinessLayerException(IErrorCodes.DB_ERROR_KEY, e.toString());
//
//		}		
//	}

}
