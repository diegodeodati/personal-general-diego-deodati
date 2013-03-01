package it.bplus.controller;

import it.bplus.bean.MeterBean;
import it.bplus.business.GeographicBusinessDelegate;
import it.bplus.business.MeterBusinessDelegate;
import it.bplus.exception.BusinessLayerException;
import it.bplus.model.Spaziodim;
import it.bplus.util.DateUtil;
import it.bplus.util.FacesUtils;
import it.bplus.util.IConstants;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import org.primefaces.component.chart.series.ChartSeries;
import org.primefaces.model.chart.CartesianChartModel;

import com.icesoft.faces.component.ext.HtmlSelectManyListbox;
import com.icesoft.faces.component.ext.HtmlSelectOneMenu;

@ManagedBean(name="graphTable")
@SessionScoped
public class GraphicController extends BasicController implements Serializable {




	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private CartesianChartModel cartesianModel;  	
	private Map<String,String> locationMap;
	private Map<Long,String> gameNameIdMap;
	private Map<String, List<MeterBean>> vltMeterMap;
	private Map<Long, List<MeterBean>> gameMeterMap;
	private List<Spaziodim> locationList;
	private List<Spaziodim> vltList;
	private List<Spaziodim> gameList;
	private List<SelectItem> location_list;
	private List<SelectItem> vlt_list;
	private List<SelectItem> game_list;
	private HtmlSelectOneMenu cmbLocation;
	private HtmlSelectManyListbox cmbVlt;
	private HtmlSelectOneMenu cmbParameter;
	private HtmlSelectOneMenu cmbParameter_2;
	private String nome_selected_location;
	private String id_selected_location;
	private String selected_vlt;
	private String gd_selected_vlt;
	private String[] selected_vlts;
	private Long[] selected_games;
	private String selected_parameter;
	private String selected_parameter_2;
	private String asseX = "€";
	

	private Date data1 = DateUtil.oraDelleStreghe(DateUtil.calcolaGiornoPrecedente(new Date()));	
	private Date data2 = DateUtil.ventitreCinquantanove(DateUtil.calcolaGiornoPrecedente(new Date()));
	
	private List<MeterBean> meterList_locations;

	private static GraphicController instance;
	
	public static synchronized GraphicController getInstance() {
		if (instance == null)
		{
			synchronized(GraphicController.class) {      //1
				GraphicController inst = instance;         //2
				if (inst == null)
				{
					synchronized(GraphicController.class) {  //3
						instance = new GraphicController();
					}
					//instance = inst;               //5
				}
			}
		}
		return instance;
	}
	
	
	
	public Date getData1() {
		return data1;
	}

	public void setData1(Date data1) {
		this.data1 = data1;
	}

	public Date getData2() {
		return data2;
	}

	public void setData2(Date data2) {
		this.data2 = data2;
	}
	
	public Map<String, String> getLocationMap() {
		return locationMap;
	}
	
	public Map<Long, List<MeterBean>> getGameMeterMap() {
		return gameMeterMap;
	}



	public void setGameMeterMap(Map<Long, List<MeterBean>> gameMeterMap) {
		this.gameMeterMap = gameMeterMap;
	}



	public List<Spaziodim> getGameList() throws BusinessLayerException {
		try {			
			if (gameList == null || gameList.size()==0) {
				gameList = GeographicBusinessDelegate.getInstance().retrieveAllGames();
				gameNameIdMap = new HashMap<Long,String>();
				for(Spaziodim sd : gameList){					
					gameNameIdMap.put(sd.getGsGameId(), sd.getGameName());
				}
			}
			return gameList;
		} catch (BusinessLayerException ble) {
			ble.printStackTrace();
			logger.error(ble.toString());
			throw ble;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.toString());
			throw new BusinessLayerException(e.toString());
		}
	}



	public void setGameList(List<Spaziodim> gameList) {
		this.gameList = gameList;
	}



	public List<SelectItem> getGame_list() throws BusinessLayerException {
		if (game_list == null) {
			game_list = new ArrayList<SelectItem>();
		} else
			game_list.clear();

		gameList = this.getGameList();
		if (gameList != null) {
			for (Spaziodim sd : gameList) {
				game_list.add(new SelectItem(sd.getGsGameId(), sd.getGameName()));
			}
		}
		return game_list;
	}



	public void setGame_list(List<SelectItem> game_list) {
		this.game_list = game_list;
	}


	public Long[] getSelected_games() {
		return selected_games;
	}


	public void setSelected_games(Long[] selected_games) {
		this.selected_games = selected_games;
	}

	public void setSelected_vlt(String selected_vlt) {
		this.selected_vlt = selected_vlt;
	}

	public String getSelected_vlt() {
		return selected_vlt;
	}
	
	public void setGd_selected_vlt(String gd_selected_vlt) {
		this.gd_selected_vlt = gd_selected_vlt;
	}

	public String getGd_selected_vlt() {
		return gd_selected_vlt;
	}
	


	public List<Spaziodim> getVltList() throws BusinessLayerException {
		try {			
			if ((vltList == null || vltList.size()==0) && id_selected_location!=null) {
				vltList = GeographicBusinessDelegate.getInstance().retrieveVltByIdLocation(id_selected_location);
			}
			return vltList;
		} catch (BusinessLayerException ble) {
			ble.printStackTrace();
			logger.error(ble.toString());
			throw ble;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.toString());
			throw new BusinessLayerException(e.toString());
		}
	}



	public void setVltList(List<Spaziodim> vltList) {
		this.vltList = vltList;
	}



	public List<SelectItem> getVlt_list() throws BusinessLayerException {
		if (vlt_list == null) {
			vlt_list = new ArrayList<SelectItem>();
		} else
			vlt_list.clear();

		vltList = this.getVltList();
		if (vltList != null) {
			for (Spaziodim sd : vltList) {
				vlt_list.add(new SelectItem(sd.getAamsVltId(), sd
						.getAamsVltId()));
			}
		}
		return vlt_list;
	}



	public void setVlt_list(List<SelectItem> vlt_list) {
		this.vlt_list = vlt_list;
	}



	public HtmlSelectManyListbox getCmbVlt() {
		return cmbVlt;
	}



	public void setCmbVlt(HtmlSelectManyListbox cmbVlt) {
		this.cmbVlt = cmbVlt;
	}



	public void setLocationMap(Map<String, String> locationMap) {
		this.locationMap = locationMap;
	}

	public void setCmbParameter(HtmlSelectOneMenu cmbParameter) {
		this.cmbParameter = cmbParameter;
	}
	
	public void setCmbParameter_2(HtmlSelectOneMenu cmbParameter_2) {
		this.cmbParameter_2 = cmbParameter_2;
	}
	
	public HtmlSelectOneMenu getCmbParameter() {
		return cmbParameter;
	}
	
	public HtmlSelectOneMenu getCmbParameter_2() {
		return cmbParameter_2;
	}

	public void setSelected_parameter(String selected_parameter) {
		this.selected_parameter = selected_parameter;
	}

	public String getSelected_parameter() {
		return selected_parameter;
	}
	
	public void setSelected_parameter_2(String selected_parameter_2){
		this.selected_parameter_2 = selected_parameter_2;
	}
	
	public String getAsseX() {
		return asseX;
	}
	
	public void setAsseX(String asseX){
		this.asseX = asseX;
	}
	
	public String getSelected_parameter_2(){
		return selected_parameter_2;
	}

	public void setLocationList(List<Spaziodim> locationList) {
		this.locationList = locationList;
	}

	public List<Spaziodim> getLocationList() throws BusinessLayerException {
		try {
						
			if (locationList == null) {
				locationList = GeographicBusinessDelegate.getInstance().retrieveAllLocations();
				locationMap = new HashMap<String,String>();
				for(Spaziodim sd : locationList){					
					locationMap.put(sd.getAamsLocationId(), sd.getLocationName());
				}
			}
			return locationList;
		} catch (BusinessLayerException ble) {
			ble.printStackTrace();
			logger.error(ble.toString());
			throw ble;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.toString());
			throw new BusinessLayerException(e.toString());
		}
	}
	

	public void setSelected_vlts(String[] selected_vlts) {
		this.selected_vlts = selected_vlts;
	}



	public String[] getSelected_vlts() {
		return selected_vlts;
	}



	public void setVltMeterMap(Map<String, List<MeterBean>> vltMeterMap) {
		this.vltMeterMap = vltMeterMap;
	}



	public Map<String, List<MeterBean>> getVltMeterMap() {
		return vltMeterMap;
	}



	public List<MeterBean> query_vlt(String s) {
		logger.info("getMeter_vlt_graph");
		List <MeterBean> meterList_vlt = null;
		try {			
			meterList_vlt = MeterBusinessDelegate.getInstance().retrieveMeter_vlt_graph(
			s, nome_selected_location, id_selected_location, null, null,
			null, null, data1, data2, "tempodim.DATA", true);			
						
		} catch (BusinessLayerException e) {
			e.printStackTrace();
			logger.error(e.toString());
			addFacesMessageForUI("Exception detected. Message: " +e.toString());
		}
		return meterList_vlt;	
	}	
	
	public List<MeterBean> query_game(Long aamd_game_id) {
		logger.info("getMeter_game_graph");
		List <MeterBean> meterList_game = null;
		try {
			meterList_game = MeterBusinessDelegate.getInstance().retrieveMeter_game_graph(aamd_game_id, nome_selected_location, id_selected_location, null, null,
			null, null, data1, data2, "tempodim.DATA", true);			
						
		} catch (BusinessLayerException e) {
			e.printStackTrace();
			logger.error(e.toString());
			addFacesMessageForUI("Exception detected. Message: " +e.toString());
		}
		return meterList_game;	
	}	
	

	
	
	public List<MeterBean> query_locations() {
		logger.info("getMeter_Locations_graph");
		
		try {		
			meterList_locations = MeterBusinessDelegate.getInstance().retrieveMeter_locations(
					null, id_selected_location, null, null,
					null, null, data1, data2, "vista1.data", true, false, false);
			
		} catch (BusinessLayerException e) {
			e.printStackTrace();
			logger.error(e.toString());
			addFacesMessageForUI("Exception detected. Message: " +e.toString());
		}
		return meterList_locations;	
	}
	
	
	
	
	public List<MeterBean> getMeterList_locations() {
		return meterList_locations;
	}

	public void setMeterList_locations(List<MeterBean> meterList_locations) {
		this.meterList_locations = meterList_locations;
	}

  
	@Override
	protected boolean isDefaultAscending(String sortColumn) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setEntity(Object value) {
		// TODO Auto-generated method stub
		
	}
		
	   public void setId_selected_location(String id_selected_location) {
		this.id_selected_location = id_selected_location;
	}

	public String getId_selected_location() {
		return id_selected_location;
	}

	public void setNome_selected_location(String nome_selected_location) {
		this.nome_selected_location = nome_selected_location;
	}

	public String getNome_selected_location() {
		return nome_selected_location;
	}

	public void setGameNameIdMap(Map<Long,String> gameNameIdMap) {
		this.gameNameIdMap = gameNameIdMap;
	}



	public Map<Long,String> getGameNameIdMap() {
		return gameNameIdMap;
	}



	public void setLocation_list(List<SelectItem> location_list) {
		this.location_list = location_list;
	}

	public List<SelectItem> getLocation_list() throws BusinessLayerException {
		if (location_list == null) {
			location_list = new ArrayList<SelectItem>();

			locationList = this.getLocationList();

			if (locationList != null && location_list.isEmpty()) {
				for (Spaziodim sd : locationList) {
					location_list.add(new SelectItem(sd.getAamsLocationId(), sd.getLocationName()+ "  -  "+ sd.getAamsLocationId()));
				}
			}
		}
		return location_list;
	}

	public void setCmbLocation(HtmlSelectOneMenu cmbLocation) {
		this.cmbLocation = cmbLocation;
	}

	public HtmlSelectOneMenu getCmbLocation() {
		return cmbLocation;
	}
	
	public void changeLocation(ValueChangeEvent ev) {
		logger.debug("changeLocation: " +ev.getNewValue().toString());
		if (ev.getNewValue() != null) {
			changeLocation((String) ev.getNewValue());
		}
	}
	
	public void changeLocation(String value) {
		if (vlt_list != null && vlt_list.size() > 0)
			vlt_list.clear();
		if (vltList != null && vltList.size() > 0)
			vltList.clear();
		
		cmbLocation.setValue(value);
		id_selected_location = value;
		selected_vlt = " - ";
	}
	
	public void changeVlt(ValueChangeEvent ev) {
		logger.debug("changeVlt: " +ev.getNewValue().toString());
		if (ev.getNewValue() != null) {
			changeVlt((String) ev.getNewValue());
		}
	}
	
	public void changeVlt(String value) {
		selected_vlt = value;
		cmbVlt.setValue(value);
	}
	
	public void changeParameter(ValueChangeEvent ev) {
		logger.debug("changeParameter: " +ev.getNewValue().toString());
		if (ev.getNewValue() != null) {
			changeParameter((String) ev.getNewValue());
		}
	}
	
	public void changeParameter(String value) {
		
		selected_parameter = value;
		cmbParameter.setValue(value);
		if (selected_parameter.equals("Games Played"))
			asseX = " ";
		else asseX = "€";
	}
	
	public void changeParameter_2(ValueChangeEvent ev) {
		logger.debug("changeParameter_2: " +ev.getNewValue().toString());
		if (ev.getNewValue() != null) {
			changeParameter_2((String) ev.getNewValue());
		}
	}
	
	public void changeParameter_2(String value) {
		
		selected_parameter_2 = value;
		cmbParameter_2.setValue(value);
	}

		
    public CartesianChartModel getCartesianModel_vlt() {  
    	if (cartesianModel==null)
    		createCartesianModel_vlt();  
        return cartesianModel;  
    }
    
    public CartesianChartModel getNewCartesianModel_vlt() { 
    	if (id_selected_location!=null){
    		nome_selected_location = locationMap.get(id_selected_location);
    	}
    	if (selected_vlts!=null){
    		
// se selected_vlts era una lista    		
//    		for (String s : selected_vlts){
//    			List<MeterBean> meters = query_vlt(s);
//    			vltMeterMap.put(s, meters);
//    		}
    		if (vltMeterMap==null)
    			vltMeterMap = new HashMap<String,List<MeterBean>>();
    		else if (!vltMeterMap.isEmpty())
    			vltMeterMap.clear();
    		
    		for (int i=0;i<selected_vlts.length;i++){
    			String s = selected_vlts[i];
    			List<MeterBean> meters = query_vlt(s);
    			vltMeterMap.put(s, meters);
    		}
    	}    	
    	createCartesianModel_vlt();
        return cartesianModel;  
    }
    
    public CartesianChartModel getCartesianModel_game() {  
    	if (cartesianModel==null)
    		createCartesianModel_game();  
        return cartesianModel;  
    }
    
    public CartesianChartModel getNewCartesianModel_game() { 
    	if (id_selected_location!=null){
    		nome_selected_location = locationMap.get(id_selected_location);
    	}
    	if (selected_games!=null){
    		if (gameMeterMap==null)
    			gameMeterMap = new HashMap<Long,List<MeterBean>>();
    		else if (!gameMeterMap.isEmpty())
    			gameMeterMap.clear();
    		
    		for (int i=0;i<selected_games.length;i++){
    			Long aams_game_id = selected_games[i];
    			List<MeterBean> meters = query_game(aams_game_id);
    			gameMeterMap.put(aams_game_id, meters);
    		}
    	}    	
    	createCartesianModel_game();
        return cartesianModel;  
    }
	
	  
	    public CartesianChartModel getCartesianModel() {  
	    	if (cartesianModel==null)
	    		createCartesianModel(meterList_locations);  
	        return cartesianModel;  
	    }
	    
	    public CartesianChartModel getNewCartesianModel() { 
	    	if (id_selected_location!=null){
	    		nome_selected_location = locationMap.get(id_selected_location);
	    	}
	    	query_locations();
	    	createCartesianModel(meterList_locations);
	        return cartesianModel;  
	    }

	  
	    private void createCartesianModel(List<MeterBean> meterlist) {  
	        cartesianModel = new CartesianChartModel();  
	        
	        
	        if (meterlist!=null && selected_parameter!=null){
	        ChartSeries par1 = new ChartSeries();
	        ChartSeries par2 = null;
	        if (selected_parameter_2!=null && !selected_parameter_2.equals(" - ")){
        		par2 = new ChartSeries();
	        }
	        
	        for (MeterBean mb : meterlist){
	        	String data = mb.getData().toString().substring(0, 10);
	        	
	        	par1.setLabel(selected_parameter);  
	        	
	        	if (selected_parameter.equals("Bet"))	        		
	        		par1.set(data, mb.getBet());
	        	else if (selected_parameter.equals("Win"))
	        		par1.set(data, mb.getWin());
	        	else if (selected_parameter.equals("Games Played"))
	        		par1.set(data, mb.getGamesPlayed());
	        	else if (selected_parameter.equals("Total In"))
	        		par1.set(data, mb.getTotalIn());
	        	else if (selected_parameter.equals("Total Out"))
	        		par1.set(data, mb.getTotalOut());
	        	else if (selected_parameter.equals("Ticket In"))
	        		par1.set(data, mb.getTicketIn());
	        	else if (selected_parameter.equals("Ticket Out"))
	        		par1.set(data, mb.getTicketOut());
	        	else if (selected_parameter.equals("Coin In"))
	        		par1.set(data, mb.getCoinIn());
	        	else if (selected_parameter.equals("Card In"))
	        		par1.set(data, mb.getCardIn());
	        	else if (selected_parameter.equals("Bill In"))
	        		par1.set(data, mb.getBillIn());
	        	else if (selected_parameter.equals("Total Prepaid In"))
	        		par1.set(data, mb.getTotalPrepaidIn());
	        	else if (selected_parameter.equals("Jackpot Wins"))
	        		par1.set(data, mb.getJackpotWins());
	        	else if (selected_parameter.equals("Jackpot Contribution"))
	        		par1.set(data, mb.getJackpotContribution());
	        	else if (selected_parameter.equals("Preu"))
	        		par1.set(data, mb.getPreu());
	        	else if (selected_parameter.equals("AAMS"))
	        		par1.set(data, mb.getAams());
	        	else if (selected_parameter.equals("Net Win"))
	        		par1.set(data, mb.getNetWin());
	        	else if (selected_parameter.equals("House Win"))
	        		par1.set(data, mb.getHouseWin());
	        	else if (selected_parameter.equals("Supplier Profit"))
	        		par1.set(data, mb.getSupplierProfit());
	        	
	        	if (selected_parameter_2!=null && !selected_parameter_2.equals(" - ")){
	        		par2.setLabel(selected_parameter_2);
	        		
	        		if (selected_parameter_2.equals("Bet"))	        		
		        		par2.set(data, mb.getBet());
		        	else if (selected_parameter_2.equals("Win"))
		        		par2.set(data, mb.getWin());
		        	else if (selected_parameter_2.equals("Games Played"))
		        		par2.set(data, mb.getGamesPlayed());
		        	else if (selected_parameter_2.equals("Total In"))
		        		par2.set(data, mb.getTotalIn());
		        	else if (selected_parameter_2.equals("Total Out"))
		        		par2.set(data, mb.getTotalOut());
		        	else if (selected_parameter_2.equals("Ticket In"))
		        		par2.set(data, mb.getTicketIn());
		        	else if (selected_parameter_2.equals("Ticket Out"))
		        		par2.set(data, mb.getTicketOut());
		        	else if (selected_parameter_2.equals("Coin In"))
		        		par2.set(data, mb.getCoinIn());
		        	else if (selected_parameter_2.equals("Card In"))
		        		par2.set(data, mb.getCardIn());
		        	else if (selected_parameter_2.equals("Bill In"))
		        		par2.set(data, mb.getBillIn());
		        	else if (selected_parameter_2.equals("Total Prepaid In"))
		        		par2.set(data, mb.getTotalPrepaidIn());
		        	else if (selected_parameter_2.equals("Jackpot Wins"))
		        		par2.set(data, mb.getJackpotWins());
		        	else if (selected_parameter_2.equals("Jackpot Contribution"))
		        		par2.set(data, mb.getJackpotContribution());
		        	else if (selected_parameter_2.equals("Preu"))
		        		par2.set(data, mb.getPreu());
		        	else if (selected_parameter_2.equals("AAMS"))
		        		par2.set(data, mb.getAams());
		        	else if (selected_parameter_2.equals("Net Win"))
		        		par2.set(data, mb.getNetWin());
		        	else if (selected_parameter_2.equals("House Win"))
		        		par2.set(data, mb.getHouseWin());
		        	else if (selected_parameter_2.equals("Supplier Profit"))
		        		par2.set(data, mb.getSupplierProfit());
	        	}	        	
	        } 	        
	        cartesianModel.addSeries(par1);
	        if (par2!=null)
	        	cartesianModel.addSeries(par2);
	        }
	    }
	    
	private void createCartesianModel_vlt() {
		cartesianModel = new CartesianChartModel();

		if (vltMeterMap != null && selected_vlts != null && selected_parameter != null) {
			

			for (int i=0;i<selected_vlts.length;i++) {
				
				String s = selected_vlts[i];
				List<MeterBean> meterlist = vltMeterMap.get(s);
				
				ChartSeries cs = new ChartSeries();
				
				if (meterlist != null) {
					for (MeterBean mb : meterlist) {
						String data = mb.getData().toString().substring(0, 10);					

						cs.setLabel("VLT: "+s+"; Parametro: "+selected_parameter);

						if (selected_parameter.equals("Bet"))
							cs.set(data, mb.getBet());
						else if (selected_parameter.equals("Win"))
							cs.set(data, mb.getWin());
						else if (selected_parameter.equals("Games Played"))
							cs.set(data, mb.getGamesPlayed());
						else if (selected_parameter.equals("Total In"))
							cs.set(data, mb.getTotalIn());
						else if (selected_parameter.equals("Total Out"))
							cs.set(data, mb.getTotalOut());
						else if (selected_parameter.equals("Ticket In"))
							cs.set(data, mb.getTicketIn());
						else if (selected_parameter.equals("Ticket Out"))
							cs.set(data, mb.getTicketOut());
						else if (selected_parameter.equals("Coin In"))
							cs.set(data, mb.getCoinIn());
						else if (selected_parameter.equals("Card In"))
							cs.set(data, mb.getCardIn());
						else if (selected_parameter.equals("Bill In"))
							cs.set(data, mb.getBillIn());
						else if (selected_parameter.equals("Total Prepaid In"))
							cs.set(data, mb.getTotalPrepaidIn());
						else if (selected_parameter.equals("Jackpot Wins"))
							cs.set(data, mb.getJackpotWins());
						else if (selected_parameter.equals("Jackpot Contribution"))
							cs.set(data, mb.getJackpotContribution());
						else if (selected_parameter.equals("Preu"))
							cs.set(data, mb.getPreu());
						else if (selected_parameter.equals("AAMS"))
							cs.set(data, mb.getAams());
						else if (selected_parameter.equals("Net Win"))
							cs.set(data, mb.getNetWin());
						else if (selected_parameter.equals("House Win"))
							cs.set(data, mb.getHouseWin());
						else if (selected_parameter.equals("Supplier Profit"))
							cs.set(data, mb.getSupplierProfit());					
					}					
					cartesianModel.addSeries(cs);
				}
			}
		}

	}
	
	private void createCartesianModel_game() {
		cartesianModel = new CartesianChartModel();

		if (gameMeterMap != null && selected_games != null && selected_parameter != null) {
			

			for (int i=0;i<selected_games.length;i++) {
				
				Long id_gioco = selected_games[i];
				String gameName = gameNameIdMap.get(id_gioco);
				List<MeterBean> meterlist = gameMeterMap.get(id_gioco);
				
				ChartSeries cs = new ChartSeries();
				
				if (meterlist != null) {
					for (MeterBean mb : meterlist) {
						String data = mb.getData().toString().substring(0, 10);					

						cs.setLabel("Gioco: "+gameName+"; Parametro: "+selected_parameter);

						if (selected_parameter.equals("Bet"))
							cs.set(data, mb.getBet());
						else if (selected_parameter.equals("Win"))
							cs.set(data, mb.getWin());
						else if (selected_parameter.equals("Games Played"))
							cs.set(data, mb.getGamesPlayed());
						else if (selected_parameter.equals("Total In"))
							cs.set(data, mb.getTotalIn());
						else if (selected_parameter.equals("Total Out"))
							cs.set(data, mb.getTotalOut());
						else if (selected_parameter.equals("Ticket In"))
							cs.set(data, mb.getTicketIn());
						else if (selected_parameter.equals("Ticket Out"))
							cs.set(data, mb.getTicketOut());
						else if (selected_parameter.equals("Coin In"))
							cs.set(data, mb.getCoinIn());
						else if (selected_parameter.equals("Card In"))
							cs.set(data, mb.getCardIn());
						else if (selected_parameter.equals("Bill In"))
							cs.set(data, mb.getBillIn());
						else if (selected_parameter.equals("Total Prepaid In"))
							cs.set(data, mb.getTotalPrepaidIn());
						else if (selected_parameter.equals("Jackpot Wins"))
							cs.set(data, mb.getJackpotWins());
						else if (selected_parameter.equals("Jackpot Contribution"))
							cs.set(data, mb.getJackpotContribution());
						else if (selected_parameter.equals("Preu"))
							cs.set(data, mb.getPreu());
						else if (selected_parameter.equals("AAMS"))
							cs.set(data, mb.getAams());
						else if (selected_parameter.equals("Net Win"))
							cs.set(data, mb.getNetWin());
						else if (selected_parameter.equals("House Win"))
							cs.set(data, mb.getHouseWin());
						else if (selected_parameter.equals("Supplier Profit"))
							cs.set(data, mb.getSupplierProfit());					
					}					
					cartesianModel.addSeries(cs);
				}
			}
		}

	}

	    
		public String clickMenuPopupLocation() {
			
			DateFormat formatter = new SimpleDateFormat(IConstants.DATE_FORMAT);
			
			String clicked_location_id = FacesUtils.getRequestParameter("clicked_location_id");
			String clicked_location_name = FacesUtils.getRequestParameter("clicked_location_name");
			String clicked_data1 = FacesUtils.getRequestParameter("clicked_data1");
			String clicked_data2 = FacesUtils.getRequestParameter("clicked_data2");
			
			try {
				setData1(formatter.parse(clicked_data1));
				setData2(formatter.parse(clicked_data2));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			logger.debug(clicked_location_id+" - "+clicked_location_name+" - "+clicked_data1+" - "+clicked_data2);
					
			setId_selected_location(clicked_location_id);
			setNome_selected_location(clicked_location_name);
			setSelected_parameter("Bet");

			query_locations();
			createCartesianModel(meterList_locations);
			System.out.println("Redirect to Graphic Location Page");

			return "graphloc";
		}

}
