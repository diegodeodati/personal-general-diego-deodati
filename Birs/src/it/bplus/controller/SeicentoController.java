package it.bplus.controller;

import it.bplus.bean.Confronto;
import it.bplus.bean.MeterBean;
import it.bplus.business.GeographicBusinessDelegate;
import it.bplus.business.SeicentoBusinessDelegate;
import it.bplus.exception.BusinessLayerException;
import it.bplus.util.DateUtil;
import it.bplus.util.MyExcelOutputHandler;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import com.icesoft.faces.component.dataexporter.DataExporter;
import com.icesoft.faces.component.dataexporter.OutputTypeHandler;

@ManagedBean(name="seicentoTable")
@SessionScoped
public class SeicentoController extends BasicController implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private static SeicentoController instance;
	
	public static synchronized SeicentoController getInstance() {
		if (instance == null)
		{
			synchronized(SeicentoController.class) {      //1
				SeicentoController inst = instance;         //2
				if (inst == null)
				{
					synchronized(SeicentoController.class) {  //3
						instance = new SeicentoController();
					}
					//instance = inst;               //5
				}
			}
		}
		return instance;
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
	
	
	private List<MeterBean> meterList600_vlt;
	private List<MeterBean> meterListSogei_locations;
	private List<MeterBean> meterList600_locations;
	private Map<String,MeterBean> mapSogei_vlt;
	private Map<String,MeterBean> mapSogei_locations;
	private Map<String,MeterBean> map600_locations;
	private List<Confronto> confronti_locations;
	private List<Confronto> confronti_vlt;
	private Object ivItem;
	private int selectIndex = 1;
	private boolean mostraTutto_vlt = false;
	private boolean mostraTutto_locations = false;
	
	protected DataExporter dataExporterXlsVlt= new DataExporter(); 
	protected DataExporter dataExporterCsvVlt= new DataExporter(); 
	protected DataExporter dataExporterXlsLocation= new DataExporter(); 
	protected DataExporter dataExporterCsvLocation= new DataExporter(); 
	private OutputTypeHandler customExcelExporter; 
	
	private Date data1 = DateUtil.oraDelleStreghe(DateUtil.calcolaGiornoPrecedente(new Date()));	
	private Date data2 = DateUtil.ventitreCinquantanove(DateUtil.calcolaGiornoPrecedente(new Date()));
	private final Date oggi = new Date();

	
	
	public void query() {
//		if (this.selectIndex == 0)
//			return query_game();
//		else 
			if (this.selectIndex == 0)
			query_vlt();
		else if (this.selectIndex == 1)
			query_locations();
	}
	
	
	public void query_vlt() {
		logger.info("getMeterConfr600_vlt");
		
		dataExporterXlsVlt.setResource(null);
		dataExporterCsvVlt.setResource(null);
				
		try {
						
			confronti_vlt = SeicentoBusinessDelegate.getInstance().
			retrieveConfronti600Sogei_vlt(location_name, aams_location_id, aams_vlt_id,sortColumn_vlt,isAscending_vlt, data1, data2, mostraTutto_vlt);
			
			calcolaTotDiff(confronti_vlt);
			
			ivItem = null;		
		} catch (BusinessLayerException e) {
			e.printStackTrace();
			logger.error(e.toString());
			addFacesMessageForUI("Exception detected. Message: " +e.toString());
		}
	}	
	
	public void query_locations() {
		logger.info("getMeterConfr600_Locations");
		
		dataExporterXlsLocation.setResource(null);
		dataExporterCsvLocation.setResource(null);
		
		try {

			confronti_locations = SeicentoBusinessDelegate.getInstance().
			retrieveConfronti600Sogei_locations(location_name, aams_location_id, sortColumn_vlt,isAscending_vlt, data1, data2, mostraTutto_locations);
			
			calcolaTotDiff(confronti_locations);
//			meterList600_locations = SeicentoBusinessDelegate.getInstance().
//			retrieveMeter600_locations(location_name,aams_location_id,sortColumn_locations,isAscending_locations,data1,data2);
//			
//			mapSogei_locations = MeterBusinessDelegate.getInstance().retrieveMeterMap_locations(
//					location_name, aams_location_id, null, null,
//					null, null, data1, data2);
//			
//			creaConfronti_locations();
			
			ivItem = null;
		} catch (BusinessLayerException e) {
			e.printStackTrace();
			logger.error(e.toString());
			addFacesMessageForUI("Exception detected. Message: " +e.toString());
		}
	}
	
	public List<MeterBean> getMeterList600_vlt() {
		if (this.selectIndex == 1) {
			if (meterList600_vlt == null)
				query_vlt();
		}
		return meterList600_vlt;
	}
	
	public List<Confronto> getConfronti_locations() {
		if (this.selectIndex == 1) {
			if (confronti_locations == null)
				query_locations();
		}
		return confronti_locations;
	}

	public void setItem(Object ivItem) {
		this.ivItem = ivItem;
	}

	public Object getItem() {
		return ivItem;
	}
	
	public Date getData1(){
		return data1;
	}

	public void setData1(Date data1){
		this.data1 = data1;
	}


	public Date getData2(){
		return data2;
	}

	public void setData2(Date data2){
		this.data2 = data2;
	}

	public void setMap600_locations(Map<String,MeterBean> map600_locations) {
		this.map600_locations = map600_locations;
	}

	public Map<String,MeterBean> getMap600_locations() {
		return map600_locations;
	}

	public void setMeterListSogei_locations(List<MeterBean> meterListSogei_locations) {
		this.meterListSogei_locations = meterListSogei_locations;
	}

	public List<MeterBean> getMeterListSogei_locations() {
		return meterListSogei_locations;
	}
	
	public void tabChangeListenerIcefaces(com.icesoft.faces.component.paneltabset.TabChangeEvent event){
		this.selectIndex = event.getNewTabIndex();
		query();
	}
	
	public void tabChangeListenerPrimefaces(org.primefaces.event.TabChangeEvent event){
		if(event.getTab().getId().equals("panel_tab_locations"))
			this.selectIndex = 1;
		else if (event.getTab().getId().equals("panel_tab_vlt"))
		this.selectIndex = 0;
	}
	
//	public void creaConfronti_locations(){
//		if (meterList600_locations!=null && !(meterList600_locations.isEmpty()) 
//				&& mapSogei_locations!=null && !(mapSogei_locations.isEmpty())){
//			
//			confronti_locations = new ArrayList<Confronto>();
//			
//			impostaTotDiffAZero();
//			
//			for (MeterBean mb600 : meterList600_locations){
//				
//				MeterBean mbSogei = mapSogei_locations.get(mb600.getAams_location_id()+" - "+mb600.getData().getTime());
//				
//				if(differenzeConfronti(mb600,mbSogei)){
//					Confronto cf = new Confronto();
//				
//					cf.setSogei(mbSogei);
//					cf.setSeicento(mb600);
//					calcolaTot(mb600, mbSogei);
//				
//					confronti_locations.add(cf);
//				}
//			}
//			calcolaDiff();
//		} else if (confronti_locations!=null)	confronti_locations.clear();
//	}
//	
//	public boolean differenzeConfronti(MeterBean mb600,MeterBean mbSogei){
//		boolean diff = false;
//		try{
//		
//			if (mb600 != null && mbSogei != null) {
//				if (mb600.getBet() - mbSogei.getBet() != 0
//						|| mb600.getWin() - mbSogei.getWin() != 0
//						|| mb600.getGamesPlayed() - mbSogei.getGamesPlayed() != 0
//						|| mb600.getTotalIn() - mbSogei.getTotalIn() != 0
//						|| mb600.getTotalOut() - mbSogei.getTotalOut() != 0
//						|| mb600.getNumVlt() - mbSogei.getNumVlt() != 0)
//					diff = true;
//
//			} else diff = true;
//		}
//		catch(NullPointerException e){
//			e.printStackTrace();
//			logger.info(mbSogei);
//		}
//		return diff;
//	}
	
//	public void creaConfronti_vlt(){
//		if (meterList600_vlt!=null && !(meterList600_vlt.isEmpty()) 
//				&& mapSogei_vlt!=null && !(mapSogei_vlt.isEmpty())){
//			
//			confronti_vlt = new ArrayList<Confronto>();
//			impostaTotDiffAZero();
//			
//			for (MeterBean mb600 : meterList600_vlt){
//				
//				MeterBean mbSogei = mapSogei_vlt.get(mb600.getAams_vlt_id()+" - "+mb600.getData().getTime());
//				
//				if(differenzeConfronti(mb600,mbSogei)){
//					Confronto cf = new Confronto();
//				
//					cf.setSogei(mbSogei);
//					cf.setSeicento(mb600);
//					calcolaTot(mb600, mbSogei);
//										
//					confronti_vlt.add(cf);
//				}
//			}			
//			calcolaDiff();
//		} else if (confronti_vlt!=null)	confronti_vlt.clear();
//	}
	
	public void impostaTotDiffAZero(){
		totGM600 = 0;
		totGMS = 0;
		diffGM = 0;
		totBet600 = 0;
		totBetS = 0;
		diffBet = 0;
		totWin600 = 0;
		totWinS = 0;
		diffWin = 0;
		totGP600 = 0;
		totGPS = 0;
		diffGP = 0;
		totTotIn600 = 0;
		totTotInS = 0;
		diffTotIn = 0;
		totTotOut600 = 0;
		totTotOutS = 0;
		diffTotOut = 0;
	}
	
	public void calcolaTot(MeterBean m600, MeterBean mS){
		if (m600!=null){
			totGM600 = totGM600+m600.getNumVlt();
			totBet600 = totBet600+m600.getBet();
			totWin600 = totWin600+m600.getWin();
			totGP600 = totGP600+m600.getGamesPlayed();
			totTotIn600 = totTotIn600+m600.getTotalIn();
			totTotOut600 = totTotOut600+m600.getTotalOut();
		}
		if (mS != null) {
			totGMS = totGMS + mS.getNumVlt();
			totBetS = totBetS + mS.getBet();
			totWinS = totWinS + mS.getWin();
			totGPS = totGPS + mS.getGamesPlayed();
			totTotInS = totTotInS + mS.getTotalIn();
			totTotOutS = totTotOutS + mS.getTotalOut();
		}
	}
	
	public void calcolaDiff(){
		diffGM = totGM600-totGMS;
		diffBet = totBet600-totBetS;
		diffWin = totWin600-totWinS;
		diffGP = totGP600-totGPS;
		diffTotIn = totTotIn600-totTotInS;
		diffTotOut = totTotOut600-totTotOutS;
	}
	
	public void calcolaTotDiff(List<Confronto> list){
		impostaTotDiffAZero();
		
		for(Confronto c : list){
			calcolaTot(c.getSeicento(),c.getSogei());			
		}
		calcolaDiff();
	}

	public void setMeterList600_locations(List<MeterBean> meterList600_locations) {
		this.meterList600_locations = meterList600_locations;
	}

	public List<MeterBean> getMeterList600_locations() {
		return meterList600_locations;
	}

	public void setMapSogei_locations(Map<String,MeterBean> mapSogei_locations) {
		this.mapSogei_locations = mapSogei_locations;
	}

	public Map<String,MeterBean> getMapSogei_locations() {
		return mapSogei_locations;
	}
	
	public void setSelectIndex(int selectIndex) {
		this.selectIndex = selectIndex;
	}
	
	public int getSelectIndex() {
		return selectIndex;
	}

	public void setMapSogei_vlt(Map<String,MeterBean> mapSogei_vlt) {
		this.mapSogei_vlt = mapSogei_vlt;
	}

	public Map<String,MeterBean> getMapSogei_vlt() {
		return mapSogei_vlt;
	}

	public void setConfronti_vlt(List<Confronto> confronti_vlt) {
		this.confronti_vlt = confronti_vlt;
	}

	public List<Confronto> getConfronti_vlt() {
		if (this.selectIndex == 0) {
			if (confronti_vlt == null)
				query_vlt();
		}
		return confronti_vlt;
	}
	
	public DataExporter getDataExporterXlsLocation() {
		return dataExporterXlsLocation;
	}

	public void setDataExporterXlsLocation(DataExporter dataExporterXlsLocation) {
		this.dataExporterXlsLocation = dataExporterXlsLocation;
	}

	public DataExporter getDataExporterCsvLocation() {
		return dataExporterCsvLocation;
	}

	public void setDataExporterCsvLocation(DataExporter dataExporterCsvLocation) {
		this.dataExporterCsvLocation = dataExporterCsvLocation;
	}
	
	public DataExporter getDataExporterXlsVlt() {
		return dataExporterXlsVlt;
	}

	public void setDataExporterXlsVlt(DataExporter dataExporterXlsVlt) {
		this.dataExporterXlsVlt = dataExporterXlsVlt;
	}

	public DataExporter getDataExporterCsvVlt() {
		return dataExporterCsvVlt;
	}

	public void setDataExporterCsvVlt(DataExporter dataExporterCsvVlt) {
		this.dataExporterCsvVlt = dataExporterCsvVlt;
	}
	
	public OutputTypeHandler getCustomExcelExporter() {
		customExcelExporter = null;
		customExcelExporter = new MyExcelOutputHandler("Confronto_600-Sogei_Report_"+DateUtil.formatExport()+".xls");
		return customExcelExporter;
	}



	public void setCustomExcelExporter(OutputTypeHandler customExcelExporter) {
		this.customExcelExporter = customExcelExporter;
	}
		
	
	
	
	
	
	
	
	
	//RICERCA
	
	private String location_name;
	private String aams_location_id;
	private String aams_vlt_id;
	private String sortColumn_locations = "commercial_name";
	private String sortColumn_vlt = "commercial_name";
	private boolean isAscending_locations = true;
	private boolean isAscending_vlt = true;
	public boolean ricerca_visibile = false;
	
	public void ricerca(){
		if (ricerca_visibile){
			ricerca_visibile = false;
		} else ricerca_visibile = true;
	}
	
	public void annullaRicerca(){
		location_name="";
		aams_location_id="";
		query();
	}

	public boolean isRicerca_visibile() {
		return ricerca_visibile;
	}
	
	public String getLocation_name() {
		return location_name;
	}

	public void setLocation_name(String location_name) {
		this.location_name = location_name;
	}

	public String getAams_location_id() {
		return aams_location_id;
	}

	public void setAams_location_id(String aams_location_id) {
		this.aams_location_id = aams_location_id;
	}
	
	public void setAams_vlt_id(String aams_vlt_id) {
		this.aams_vlt_id = aams_vlt_id;
	}

	public String getAams_vlt_id() {
		return aams_vlt_id;
	}
	
	
	public void ordinaPerAamsLocationId_locations(ActionEvent event){
		confronti_locations=null;
		if (this.sortColumn_locations.equals("aams_location_id"))
			this.isAscending_locations = !this.isAscending_locations;
		else {
			this.setSortColumn_locations("aams_location_id");
			this.isAscending_locations = false;
		}
	}
	public void ordinaPerLocationName_locations(ActionEvent event){
		confronti_locations=null;
		if (this.sortColumn_locations.equals("commercial_name"))
			this.isAscending_locations = !this.isAscending_locations;
		else {
			this.setSortColumn_locations("commercial_name");
			this.isAscending_locations = false;
		}
	}
	public void ordinaPerNumVlt_locations(ActionEvent event){
		confronti_locations=null;
		if (this.sortColumn_locations.equals("num_vlt"))
			this.isAscending_locations = !this.isAscending_locations;
		else {
			this.setSortColumn_locations("num_vlt");
			this.isAscending_locations = false;
		}
	}
	public void ordinaPerData_locations(ActionEvent event){
		confronti_locations=null;
		if (this.sortColumn_locations.equals("data"))
			this.isAscending_locations = !this.isAscending_locations;
		else {
			this.setSortColumn_locations("data");
			this.isAscending_locations = false;
		}
	}
	public void ordinaPerBet_locations(ActionEvent event){
		confronti_locations=null;
		if (this.sortColumn_locations.equals("bet"))
			this.isAscending_locations = !this.isAscending_locations;
		else {
			this.setSortColumn_locations("bet");
			this.isAscending_locations = false;
		}
	}
	public void ordinaPerWin_locations(ActionEvent event){
		confronti_locations=null;
		if (this.sortColumn_locations.equals("win"))
			this.isAscending_locations = !this.isAscending_locations;
		else {
			this.setSortColumn_locations("win");
			this.isAscending_locations = false;
		}
	}
	public void ordinaPerGamesPlayed_locations(ActionEvent event){
		confronti_locations=null;
		if (this.sortColumn_locations.equals("bet_num"))
			this.isAscending_locations = !this.isAscending_locations;
		else {
			this.setSortColumn_locations("bet_num");
			this.isAscending_locations = false;
		}
	}
	public void ordinaPerTotalIn_locations(ActionEvent event){
		confronti_locations=null;
		if (this.sortColumn_locations.equals("tot_in"))
			this.isAscending_locations = !this.isAscending_locations;
		else {
			this.setSortColumn_locations("tot_in");
			this.isAscending_locations = false;
		}
	}
	public void ordinaPerTotalOut_locations(ActionEvent event){
		confronti_locations=null;
		if (this.sortColumn_locations.equals("tot_out"))
			this.isAscending_locations = !this.isAscending_locations;
		else {
			this.setSortColumn_locations("tot_out");
			this.isAscending_locations = false;
		}
	}
	
	
	
	
	public void ordinaPerAamsLocationId_vlt(ActionEvent event){
		confronti_vlt=null;
		if (this.sortColumn_vlt.equals("aams_location_id"))
			this.isAscending_vlt = !this.isAscending_vlt;
		else {
			this.setSortColumn_vlt("aams_location_id");
			this.isAscending_vlt = false;
		}
	}
	public void ordinaPerLocationName_vlt(ActionEvent event){
		confronti_vlt=null;
		if (this.sortColumn_vlt.equals("commercial_name"))
			this.isAscending_vlt = !this.isAscending_vlt;
		else {
			this.setSortColumn_vlt("commercial_name");
			this.isAscending_vlt = false;
		}
	}
	public void ordinaPerGsVltId(ActionEvent event){
		confronti_vlt=null;
		if (this.sortColumn_vlt.equals("gs_vlt_code"))
			this.isAscending_vlt = !this.isAscending_vlt;
		else {
			this.setSortColumn_vlt("gs_vlt_code");
			this.isAscending_vlt = false;
		}
	}
	public void ordinaPerAamsVltId(ActionEvent event){
		confronti_vlt=null;
		if (this.sortColumn_vlt.equals("code_id"))
			this.isAscending_vlt = !this.isAscending_vlt;
		else {
			this.setSortColumn_vlt("code_id");
			this.isAscending_vlt = false;
		}
	}
	public void ordinaPerNumVlt_vlt(ActionEvent event){
		confronti_vlt=null;
		if (this.sortColumn_vlt.equals("num_vlt"))
			this.isAscending_vlt = !this.isAscending_vlt;
		else {
			this.setSortColumn_vlt("num_vlt");
			this.isAscending_vlt = false;
		}
	}
	public void ordinaPerData_vlt(ActionEvent event){
		confronti_vlt=null;
		if (this.sortColumn_vlt.equals("data"))
			this.isAscending_vlt = !this.isAscending_vlt;
		else {
			this.setSortColumn_vlt("data");
			this.isAscending_vlt = false;
		}
	}
	public void ordinaPerBet_vlt(ActionEvent event){
		confronti_vlt=null;
		if (this.sortColumn_vlt.equals("bet"))
			this.isAscending_vlt = !this.isAscending_vlt;
		else {
			this.setSortColumn_vlt("bet");
			this.isAscending_vlt = false;
		}
	}
	public void ordinaPerWin_vlt(ActionEvent event){
		confronti_vlt=null;
		if (this.sortColumn_vlt.equals("win"))
			this.isAscending_vlt = !this.isAscending_vlt;
		else {
			this.setSortColumn_vlt("win");
			this.isAscending_vlt = false;
		}
	}
	public void ordinaPerGamesPlayed_vlt(ActionEvent event){
		confronti_vlt=null;
		if (this.sortColumn_vlt.equals("bet_num"))
			this.isAscending_vlt = !this.isAscending_vlt;
		else {
			this.setSortColumn_vlt("bet_num");
			this.isAscending_vlt = false;
		}
	}
	public void ordinaPerTotalIn_vlt(ActionEvent event){
		confronti_vlt=null;
		if (this.sortColumn_vlt.equals("tot_in"))
			this.isAscending_vlt = !this.isAscending_vlt;
		else {
			this.setSortColumn_vlt("tot_in");
			this.isAscending_vlt = false;
		}
	}
	public void ordinaPerTotalOut_vlt(ActionEvent event){
		confronti_vlt=null;
		if (this.sortColumn_vlt.equals("tot_out"))
			this.isAscending_vlt = !this.isAscending_vlt;
		else {
			this.setSortColumn_vlt("tot_out");
			this.isAscending_vlt = false;
		}
	}

	public String getSortColumn_locations() {
		return sortColumn_locations;
	}

	public void setSortColumn_locations(String sortColumn_locations) {
		this.sortColumn_locations = sortColumn_locations;
	}
	
	public void setSortColumn_vlt(String sortColumn_vlt) {
		this.sortColumn_vlt = sortColumn_vlt;
	}

	public boolean isAscending_locations() {
		return isAscending_locations;
	}

	public void setAscending_locations(boolean isAscending_locations) {
		this.isAscending_locations = isAscending_locations;
	}

	public Date getOggi() {
		return oggi;
	}
	

	private int totGM600;
	private int totGMS;
	private int diffGM;
	private double totBet600;
	private double totBetS;
	private double diffBet;
	private double totWin600;
	private double totWinS;
	private double diffWin;
	private long totGP600;
	private long totGPS;
	private long diffGP;
	private double totTotIn600;
	private double totTotInS;
	private double diffTotIn;
	private double totTotOut600;
	private double totTotOutS;
	private double diffTotOut;

	public Object getIvItem() {
		return ivItem;
	}

	public void setIvItem(Object ivItem) {
		this.ivItem = ivItem;
	}

	public int getTotGM600() {
		return totGM600;
	}

	public int getTotGMS() {
		return totGMS;
	}

	public int getDiffGM() {
		return diffGM;
	}

	public double getTotBet600() {
		return totBet600;
	}

	public double getTotBetS() {
		return totBetS;
	}

	public double getDiffBet() {
		return diffBet;
	}

	public double getTotWin600() {
		return totWin600;
	}

	public double getTotWinS() {
		return totWinS;
	}

	public double getDiffWin() {
		return diffWin;
	}

	public long getTotGP600() {
		return totGP600;
	}

	public long getTotGPS() {
		return totGPS;
	}

	public long getDiffGP() {
		return diffGP;
	}

	public double getTotTotIn600() {
		return totTotIn600;
	}

	public double getTotTotInS() {
		return totTotInS;
	}

	public double getDiffTotIn() {
		return diffTotIn;
	}

	public double getTotTotOut600() {
		return totTotOut600;
	}

	public double getTotTotOutS() {
		return totTotOutS;
	}

	public double getDiffTotOut() {
		return diffTotOut;
	}
	
	public void svuota(){
		location_name = "";
		aams_location_id = "";
	}
	
	
	
	

	public boolean isMostraTutto_vlt() {
		return mostraTutto_vlt;
	}
	
	public void mostraTutto_vlt(){
		if(!mostraTutto_vlt){
			mostraTutto_vlt = true;
			query();
		}
	}
	
	public void nonMostrareTutto_vlt(){
		if(mostraTutto_vlt){
			mostraTutto_vlt = false;
			query();
		}
	}
	
	
	public boolean isMostraTutto_locations() {
		return mostraTutto_locations;
	}
	
	public void mostraTutto_locations(){
		if(!mostraTutto_locations){
			mostraTutto_locations = true;
			query();
		}
	}
	
	public void nonMostrareTutto_locations(){
		if(mostraTutto_locations){
			mostraTutto_locations = false;
			query();
		}
	}
	
	
	
	
	private String fixresultGame = "";
	private String fixresultVlt = "";
	private String fixresultLocation = "";
	private String fixresultClienti = "";
	
	
	public void fixGame() throws BusinessLayerException{		
		logger.info("Start Fix Game");
		
		int risultato = 0;	
		risultato = GeographicBusinessDelegate.getInstance().fixGame();
		setFixresultVlt("Sono stati aggiornati "+risultato+" record tra i giochi.");
		
		logger.info("End Fix Game. risultato = "+risultato);		
	}

	public void fixVlt() throws BusinessLayerException{		
		logger.info("Start Fix Vlt");
		
		int risultato = 0;	
		risultato = GeographicBusinessDelegate.getInstance().fixVlt();
		setFixresultVlt("Sono stati aggiornati "+risultato+" record tra le vlt.");
		
		logger.info("End Fix Vlt. risultato = "+risultato);		
	}
	
	public void fixLocation() throws BusinessLayerException{		
		logger.info("Start Fix Location");
		
		int risultato = 0;	
		risultato = GeographicBusinessDelegate.getInstance().fixLocation();
		setFixresultLocation("Sono stati aggiornati "+risultato+" record tra le location.");
		
		logger.info("End Fix Location. risultato = "+risultato);		
	}
	
	public void fixClienti() throws BusinessLayerException{		
		logger.info("Start Fix Clienti");
		
		int risultato = 0;	
		risultato = GeographicBusinessDelegate.getInstance().fixClienti();
		setFixresultLocation("Sono stati aggiornati "+risultato+" record tra esercenti e gestori.");
		
		logger.info("End Fix Clienti. risultato = "+risultato);		
	}
	
	public void setFixresultGame(String fixresultGame) {
		this.fixresultGame = fixresultGame;
	}

	public String getFixresultGame() {
		return fixresultGame;
	}

	public void setFixresultVlt(String fixresultVlt) {
		this.fixresultVlt = fixresultVlt;
	}

	public String getFixresultVlt() {
		return fixresultVlt;
	}
	
	public void setFixresultLocation(String fixresultLocation) {
		this.fixresultLocation = fixresultLocation;
	}

	public String getFixresultLocation() {
		return fixresultLocation;
	}
	
	public String getFixresultClienti() {
		return fixresultClienti;
	}

	public void setFixresultClienti(String fixresultClienti) {
		this.fixresultClienti = fixresultClienti;
	}

}
