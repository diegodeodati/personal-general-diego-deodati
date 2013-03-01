package it.bplus.controller;

import it.bplus.bean.MeterBean;
import it.bplus.business.GeographicBusinessDelegate;
import it.bplus.business.MeterBusinessDelegate;
import it.bplus.exception.BusinessLayerException;
import it.bplus.model.Comuni;
import it.bplus.model.Province;
import it.bplus.model.Regioni;
import it.bplus.util.DateUtil;
import it.bplus.util.FacesUtils;
import it.bplus.util.IConstants;
import it.bplus.util.MyExcelOutputHandler;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import org.primefaces.component.chart.series.ChartSeries;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.PieChartModel;

import com.icesoft.faces.component.dataexporter.DataExporter;
import com.icesoft.faces.component.dataexporter.OutputTypeHandler;
import com.icesoft.faces.component.datapaginator.DataPaginator;
import com.icesoft.faces.component.ext.HtmlSelectOneMenu;
import com.icesoft.faces.component.paneltabset.TabChangeEvent;



@ManagedBean(name="meterTable")
@SessionScoped
public class MeterController extends BasicController implements Serializable {
	
	private DataPaginator dataPaginator = new DataPaginator();

	private static final long serialVersionUID = 1L;
	
	private static final String regioneColumnName = "Regione";
	private static final String locationAddressColumnName = "Indirizzo";
	private static final String provinciaColumnName = "Provincia";
	private static final String comuneColumnName = "Comune";	
	private static final String machineSystemIdColumnName = "Machine System ID";
	private static final String aamsGameIdColumnName = "AAMS Game ID";
	private static final String aamsGameNameColumnName = "Nome gioco";
	private static final String codGestoreColumnName = "Cod. Gestore";
	private static final String aamsLocationIdColumnName = "Cod. Sala";
	private static final String aamsVltIdColumnName = "AAMS Vlt ID";
	private static final String gsVltIdColumnName = "GS Vlt ID";
	private static final String locationNameColumnName = "Nome sala";
	private static final String monthColumnName = "Mese";
	private static final String dayColumnName = "Data";
	private static final String machineColumnName = "Machine";
	private static final String betColumnName = "Bet";
	private static final String winColumnName = "Win";
	private static final String gamesPlayedColumnName = "Games Played";
	private static final String totalInColumnName = "Total In";
	private static final String totalOutColumnName = "Total Out";
	private static final String ticketInColumnName = "Ticket In";
	private static final String ticketOutColumnName = "Ticket Out";
	private static final String coinInColumnName = "Coin In";
	private static final String billInColumnName = "Bill In";
	private static final String cardInColumnName = "Card In";
	private static final String totalPrepaidInColumnName = "Total Prepaid In";
	private static final String jackpotWinsColumnName = "Jackpot Wins";
	private static final String jackpotContributionColumnName = "Jackpot Contribution";
	private static final String preuColumnName = "Preu";
	private static final String aamsColumnName = "AAMS";
	private static final String netWinColumnName = "Net Win";
	private static final String houseWinColumnName = "House Win";
	private static final String supplierProfitColumnName = "Supplier Profit";
	private static final String operatorsProfitColumnName = "Operators Profit";
	private static final String bplusNetProfitColumnName = "Bplus Net Profit";
	private static final String statoColumnName = "Stato";
	private static final String noteColumnName = "Note";
	private static final String numVltColumnName = "Giorni macchina";
	
	protected DataExporter dataExporterXlsGame= new DataExporter(); 
	protected DataExporter dataExporterCsvGame= new DataExporter(); 
	
	protected DataExporter dataExporterXlsVlt= new DataExporter(); 
	protected DataExporter dataExporterCsvVlt= new DataExporter(); 
	
	protected DataExporter dataExporterXlsLocation= new DataExporter(); 
	protected DataExporter dataExporterCsvLocation= new DataExporter(); 
	
	protected DataExporter dataExporterXlsRegioni= new DataExporter(); 
	protected DataExporter dataExporterCsvRegioni= new DataExporter(); 
	

	private MeterBean ivItem;
	private List<MeterBean> meterList_game;
	
	private List<MeterBean> meterList_vlt;
	
	private List<MeterBean> meterList_location;
	
	private List<MeterBean> meterList_regioni;
	
	private String sortColumn = "tempodim.data";
	private String sortColumn_vlt = "tempodim.DATA";
	private String sortColumn_locations = "vista1.location_Name";
	private String sortColumn_regioni = "vista1.regione";
	private boolean isAscending = true;
	private boolean isAscending_vlt = true;
	private boolean isAscending_locations = true;
	private boolean isAscending_regioni = true;
	
	
	private OutputTypeHandler customExcelExporter; 
	
	private static MeterController instance;
	

	public static synchronized MeterController getInstance() {
		if (instance == null)
		{
			synchronized(MeterController.class) {      //1
				MeterController inst = instance;         //2
				if (inst == null)
				{
					synchronized(MeterController.class) {  //3
						instance = new MeterController();
					}
					//instance = inst;               //5
				}
			}
		}
		return instance;
	}
	

	
	public OutputTypeHandler getCustomExcelExporter() {
		customExcelExporter = null;
		customExcelExporter = new MyExcelOutputHandler("export_"+DateUtil.formatExport()+".xls");
		return customExcelExporter;
	}



	public void setCustomExcelExporter(OutputTypeHandler customExcelExporter) {
		this.customExcelExporter = customExcelExporter;
	}


	//Variabile che mi fa sapere se la lista dei meter per i giochi è superiore a 65.536 record per via del problema di excel
	private boolean gameIsBig = false;
	
	public void setGameIsBig(boolean gameIsBig) {
		this.gameIsBig = gameIsBig;
	}
	public boolean isGameIsBig() {
		return gameIsBig;
	}

	public String getRpData1(){
		DateFormat df = new SimpleDateFormat(IConstants.DATE_FORMAT);
		if (data1!=null)
			return df.format(data1);
		else return null;
	}
	
	public String getRpData2(){
		DateFormat df = new SimpleDateFormat(IConstants.DATE_FORMAT);
		if (data2!=null)
			return df.format(data2);
		else return null;
	}
	
	public String getData2MenoSette(){
		DateFormat df = new SimpleDateFormat(IConstants.DATE_FORMAT);
		if (data2!=null){
			Date x = DateUtil.calcolaSettimanaPrecedente(data2);	
			return df.format(x);
		}
		else return null;
	}
	
	public String getData2MenoTrenta(){
		DateFormat df = new SimpleDateFormat(IConstants.DATE_FORMAT);
		if (data2!=null){
			Date x = DateUtil.calcolaMesePrecedente(data2);
			return df.format(x);
		}
		else return null;
	}


	public boolean isAscending_vlt() {
		return isAscending_vlt;
	}

	public void setAscending_vlt(boolean isAscending_vlt) {
		this.isAscending_vlt = isAscending_vlt;
	}
	
	public boolean isAscending_locations() {
		return isAscending_locations;
	}

	public void setAscending_locations(boolean isAscending_locations) {
		this.isAscending_locations = isAscending_locations;
	}
	
	public boolean isAscending_regioni() {
		return isAscending_regioni;
	}

	public void setAscending_regioni(boolean isAscending_regioni) {
		this.isAscending_regioni = isAscending_regioni;
	}


	public String getSortColumn() {
		return sortColumn;
	}

	public void setSortColumn(String sortColumn) {
		this.sortColumn = sortColumn;
	}
	public String getSortColumn_vlt() {
		return sortColumn_vlt;
	}

	public void setSortColumn_vlt(String sortColumn_vlt) {
		this.sortColumn_vlt = sortColumn_vlt;
	}
	public String getSortColumn_locations() {
		return sortColumn_locations;
	}

	public void setSortColumn_locations(String sortColumn_locations) {
		this.sortColumn_locations = sortColumn_locations;
	}
	
	public String getSortColumn_regioni() {
		return sortColumn_regioni;
	}

	public void setSortColumn_regioni(String sortColumn_regioni) {
		this.sortColumn_regioni = sortColumn_regioni;
	}

	public boolean isAscending() {
		return isAscending;
	}

	public void setAscending(boolean isAscending) {
		this.isAscending = isAscending;
	}

	
	public DataExporter getDataExporterXlsGame() {
		return dataExporterXlsGame;
	}

	public void setDataExporterXlsGame(DataExporter dataExporterXlsGame) {
		this.dataExporterXlsGame = dataExporterXlsGame;
	}

	public DataExporter getDataExporterCsvGame() {
		return dataExporterCsvGame;
	}

	public void setDataExporterCsvGame(DataExporter dataExporterCsvGame) {
		this.dataExporterCsvGame = dataExporterCsvGame;
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
	
	public DataExporter getDataExporterXlsRegioni() {
		return dataExporterXlsRegioni;
	}

	public void setDataExporterXlsRegioni(DataExporter dataExporterXlsRegioni) {
		this.dataExporterXlsRegioni = dataExporterXlsRegioni;
	}

	public DataExporter getDataExporterCsvRegioni() {
		return dataExporterCsvRegioni;
	}

	public void setDataExporterCsvRegioni(DataExporter dataExporterCsvRegioni) {
		this.dataExporterCsvRegioni = dataExporterCsvRegioni;
	}
	
	
	
	//Ordinamenti
	public void ordinaPerLocationAddress(ActionEvent event){
		meterList_game=null;
		if (this.sortColumn.equals("spaziodim.location_Address"))
			this.isAscending = !this.isAscending;
		else {
			this.setSortColumn("spaziodim.location_Address");
			this.isAscending = false;
		}
	}
	public void ordinaPerRegione(ActionEvent event){
		meterList_game=null;
		if (this.sortColumn.equals("spaziodim.region"))
			this.isAscending = !this.isAscending;
		else {
			this.setSortColumn("spaziodim.region");
			this.isAscending = false;
		}
	}
	public void ordinaPerProvincia(ActionEvent event){
		meterList_game=null;
		if (this.sortColumn.equals("spaziodim.SIGLA_PROVINCIA"))
			this.isAscending = !this.isAscending;
		else {
			this.setSortColumn("spaziodim.SIGLA_PROVINCIA");
			this.isAscending = false;
		}
	}
	public void ordinaPerComune(ActionEvent event){
		meterList_game=null;
		if (this.sortColumn.equals("spaziodim.city"))
			this.isAscending = !this.isAscending;
		else {
			this.setSortColumn("spaziodim.city");
			this.isAscending = false;
		}
	}
	public void ordinaPerMachineSystemId(ActionEvent event){
		meterList_game=null;
		if (this.sortColumn.equals("spaziodim.machineSystemId"))
			this.isAscending = !this.isAscending;
		else {
			this.setSortColumn("spaziodim.machineSystemId");
			this.isAscending = false;
		}
	}
	public void ordinaPerAamsGameId(ActionEvent event){
		meterList_game=null;
		if (this.sortColumn.equals("spaziodim.aams_Game_Id"))
			this.isAscending = !this.isAscending;
		else {
			this.setSortColumn("spaziodim.aams_Game_Id");
			this.isAscending = false;
		}
	}
	public void ordinaPerAamsGameName(ActionEvent event){
		meterList_game=null;
		if (this.sortColumn.equals("spaziodim.game_Name"))
			this.isAscending = !this.isAscending;
		else {
			this.setSortColumn("spaziodim.game_Name");
			this.isAscending = false;
		}
	}
	public void ordinaPerCodGestore(ActionEvent event){
		meterList_game=null;
		if (this.sortColumn.equals("clientidim.cod_Gestore"))
			this.isAscending = !this.isAscending;
		else {
			this.setSortColumn("clientidim.cod_Gestore");
			this.isAscending = false;
		}
	}
	public void ordinaPerAamsLocationId(ActionEvent event){
		meterList_game=null;
		if (this.sortColumn.equals("spaziodim.aams_Location_Id"))
			this.isAscending = !this.isAscending;
		else {
			this.setSortColumn("spaziodim.aams_Location_Id");
			this.isAscending = false;
		}
	}
	public void ordinaPerAamsVltId(ActionEvent event){
		meterList_game=null;
		if (this.sortColumn.equals("spaziodim.aams_Vlt_Id"))
			this.isAscending = !this.isAscending;
		else {
			this.setSortColumn("spaziodim.aams_Vlt_Id");
			this.isAscending = false;
		}
	}	
	public void ordinaPerGsVltId(ActionEvent event){
		meterList_game=null;
		if (this.sortColumn.equals("spaziodim.gs_Vlt_Id"))
			this.isAscending = !this.isAscending;
		else {
			this.setSortColumn("spaziodim.gs_Vlt_Id");
			this.isAscending = false;
		}
	}	
	public void ordinaPerLocationName(ActionEvent event){
		meterList_game=null;
		if (this.sortColumn.equals("spaziodim.location_Name"))
			this.isAscending = !this.isAscending;
		else {
			this.setSortColumn("spaziodim.location_Name");
			this.isAscending = false;
		}
	}
	public void ordinaPerMonth(ActionEvent event){
		meterList_game=null;
		if (this.sortColumn.equals("tempodim.month"))
			this.isAscending = !this.isAscending;
		else {
			this.setSortColumn("tempodim.month");
			this.isAscending = false;
		}
	}
	public void ordinaPerDay(ActionEvent event){
		meterList_game=null;
		if (this.sortColumn.equals("tempodim.data"))
			this.isAscending = !this.isAscending;
		else {
			this.setSortColumn("tempodim.data");
			this.isAscending = false;
		}
	}
	public void ordinaPerMachine(ActionEvent event){
		meterList_game=null;
		if (this.sortColumn.equals("spaziodim.codeIdVlt"))
			this.isAscending = !this.isAscending;
		else {
			this.setSortColumn("spaziodim.codeIdVlt");
			this.isAscending = false;
		}
	}
	public void ordinaPerGattivita(ActionEvent event){
		meterList_game=null;
		if (this.sortColumn.equals("GIORNI_ATTIVITA"))
			this.isAscending = !this.isAscending;
		else {
			this.setSortColumn("GIORNI_ATTIVITA");
			this.isAscending = false;
		}
	}
	public void ordinaPerBet(ActionEvent event){
		meterList_game=null;
		if (this.sortColumn.equals("bet"))
			this.isAscending = !this.isAscending;
		else {
			this.setSortColumn("bet");
			this.isAscending = false;
		}
	}
	public void ordinaPerWin(ActionEvent event){
		meterList_game=null;
		if (this.sortColumn.equals("win"))
			this.isAscending = !this.isAscending;
		else {
			this.setSortColumn("win");
			this.isAscending = false;
		}
	}
	public void ordinaPerGamesPlayed(ActionEvent event){
		meterList_game=null;
		if (this.sortColumn.equals("games_Played"))
			this.isAscending = !this.isAscending;
		else {
			this.setSortColumn("games_Played");
			this.isAscending = false;
		}
	}
	public void ordinaPerTotalIn(ActionEvent event){
		meterList_game=null;
		if (this.sortColumn.equals("total_In"))
			this.isAscending = !this.isAscending;
		else {
			this.setSortColumn("total_In");
			this.isAscending = false;
		}
	}
	public void ordinaPerTotalOut(ActionEvent event){
		meterList_game=null;
		if (this.sortColumn.equals("total_Out"))
			this.isAscending = !this.isAscending;
		else {
			this.setSortColumn("total_Out");
			this.isAscending = false;
		}
	}
	public void ordinaPerTicketIn(ActionEvent event){
		meterList_game=null;
		if (this.sortColumn.equals("ticket_In"))
			this.isAscending = !this.isAscending;
		else {
			this.setSortColumn("ticket_In");
			this.isAscending = false;
		}
	}
	public void ordinaPerTicketOut(ActionEvent event){
		meterList_game=null;
		if (this.sortColumn.equals("ticket_Out"))
			this.isAscending = !this.isAscending;
		else {
			this.setSortColumn("ticket_Out");
			this.isAscending = false;
		}
	}
	public void ordinaPerCoinIn(ActionEvent event){
		meterList_game=null;
		if (this.sortColumn.equals("coin_In"))
			this.isAscending = !this.isAscending;
		else {
			this.setSortColumn("coin_In");
			this.isAscending = false;
		}
	}	
	public void ordinaPerBillIn(ActionEvent event){
		meterList_game=null;
		if (this.sortColumn.equals("bill_In"))
			this.isAscending = !this.isAscending;
		else {
			this.setSortColumn("bill_In");
			this.isAscending = false;
		}
	}	
	public void ordinaPerCardIn(ActionEvent event){
		meterList_game=null;
		if (this.sortColumn.equals("card_In"))
			this.isAscending = !this.isAscending;
		else {
			this.setSortColumn("card_In");
			this.isAscending = false;
		}
	}
	public void ordinaPerTotalPrepaidIn(ActionEvent event){
		meterList_game=null;
		if (this.sortColumn.equals("total_Prepaid_In"))
			this.isAscending = !this.isAscending;
		else {
			this.setSortColumn("total_Prepaid_In");
			this.isAscending = false;
		}
	}
	public void ordinaPerJackpotWins(ActionEvent event){
		meterList_game=null;
		if (this.sortColumn.equals("jackpot_Wins"))
			this.isAscending = !this.isAscending;
		else {
			this.setSortColumn("jackpot_Wins");
			this.isAscending = false;
		}
	}
	public void ordinaPerJackpotContribution(ActionEvent event){
		meterList_game=null;
		if (this.sortColumn.equals("jackpot_Contribution"))
			this.isAscending = !this.isAscending;
		else {
			this.setSortColumn("jackpot_Contribution");
			this.isAscending = false;
		}
	}
	public void ordinaPerPreu(ActionEvent event){
		meterList_game=null;
		if (this.sortColumn.equals("preu"))
			this.isAscending = !this.isAscending;
		else {
			this.setSortColumn("preu");
			this.isAscending = false;
		}
	}
	public void ordinaPerAams(ActionEvent event){
		meterList_game=null;
		if (this.sortColumn.equals("aams"))
			this.isAscending = !this.isAscending;
		else {
			this.setSortColumn("aams");
			this.isAscending = false;
		}
	}
	public void ordinaPerNetWin(ActionEvent event){
		meterList_game=null;
		if (this.sortColumn.equals("net_Win"))
			this.isAscending = !this.isAscending;
		else {
			this.setSortColumn("net_Win");
			this.isAscending = false;
		}
	}
	public void ordinaPerHouseWin(ActionEvent event){
		meterList_game=null;
		if (this.sortColumn.equals("house_Win"))
			this.isAscending = !this.isAscending;
		else {
			this.setSortColumn("house_Win");
			this.isAscending = false;
		}
	}
	public void ordinaPerSupplierProfit(ActionEvent event){
		meterList_game=null;
		if (this.sortColumn.equals("supplier_Profit"))
			this.isAscending = !this.isAscending;
		else {
			this.setSortColumn("supplier_Profit");
			this.isAscending = false;
		}
	}
	public void ordinaPerOperatorsProfit(ActionEvent event){
		meterList_game=null;
		if (this.sortColumn.equals("operators_Profit"))
			this.isAscending = !this.isAscending;
		else {
			this.setSortColumn("operators_Profit");
			this.isAscending = false;
		}
	}
	public void ordinaPerBplusNetProfit(ActionEvent event){
		meterList_game=null;
		if (this.sortColumn.equals("bplus_Net_Profit"))
			this.isAscending = !this.isAscending;
		else {
			this.setSortColumn("bplus_Net_Profit");
			this.isAscending = false;
		}
	}
	public void ordinaPerStato(ActionEvent event){
		meterList_game=null;
		if (this.sortColumn.equals("stato"))
			this.isAscending = !this.isAscending;
		else {
			this.setSortColumn("stato");
			this.isAscending = false;
		}
	}
	
	
	
	
	
	//ORDINAMENTI TAB LOCATION
	public void ordinaPerLocationAddress_locations(ActionEvent event){
		meterList_location=null;
		if (this.sortColumn_locations.equals("vista1.location_Address"))
			this.isAscending_locations = !this.isAscending_locations;
		else {
			this.setSortColumn_locations("vista1.location_Address");
			this.isAscending_locations = false;
		}
	}
	public void ordinaPerRegione_locations(ActionEvent event){
		meterList_location=null;
		if (this.sortColumn_locations.equals("vista1.regione"))
			this.isAscending_locations = !this.isAscending_locations;
		else {
			this.setSortColumn_locations("vista1.regione");
			this.isAscending_locations = false;
		}
	}
	public void ordinaPerProvincia_locations(ActionEvent event){
		meterList_location=null;
		if (this.sortColumn_locations.equals("vista1.provincia"))
			this.isAscending_locations = !this.isAscending_locations;
		else {
			this.setSortColumn_locations("vista1.provincia");
			this.isAscending_locations = false;
		}
	}
	public void ordinaPerComune_locations(ActionEvent event){
		meterList_location=null;
		if (this.sortColumn_locations.equals("vista1.comune"))
			this.isAscending_locations = !this.isAscending_locations;
		else {
			this.setSortColumn_locations("vista1.comune");
			this.isAscending_locations = false;
		}
	}
	public void ordinaPerCodGestore_locations(ActionEvent event){
		meterList_location=null;
		if (this.sortColumn_locations.equals("vista1.cod_Gestore"))
			this.isAscending_locations = !this.isAscending_locations;
		else {
			this.setSortColumn_locations("vista1.cod_Gestore");
			this.isAscending_locations = false;
		}
	}
	public void ordinaPerAamsLocationId_locations(ActionEvent event){
		meterList_location=null;
		if (this.sortColumn_locations.equals("vista1.aams_Location_Id"))
			this.isAscending_locations = !this.isAscending_locations;
		else {
			this.setSortColumn_locations("vista1.aams_Location_Id");
			this.isAscending_locations = false;
		}
	}
	public void ordinaPerLocationName_locations(ActionEvent event){
		meterList_location=null;
		if (this.sortColumn_locations.equals("vista1.location_Name"))
			this.isAscending_locations = !this.isAscending_locations;
		else {
			this.setSortColumn_locations("vista1.location_Name");
			this.isAscending_locations = false;
		}
	}
	public void ordinaPerNumVlt_locations(ActionEvent event){
		meterList_location=null;
		if (this.sortColumn_locations.equals("vista2.num_vlt"))
			this.isAscending_locations = !this.isAscending_locations;
		else {
			this.setSortColumn_locations("vista2.num_vlt");
			this.isAscending_locations = false;
		}
	}
	public void ordinaPerDay_locations(ActionEvent event){
		meterList_location=null;
		if (this.sortColumn_locations.equals("vista1.data"))
			this.isAscending_locations = !this.isAscending_locations;
		else {
			this.setSortColumn_locations("vista1.data");
			this.isAscending_locations = false;
		}
	}
	public void ordinaPerMachine_locations(ActionEvent event){
		meterList_location=null;
		if (this.sortColumn_locations.equals("vista1.code_Id_locations"))
			this.isAscending_locations = !this.isAscending_locations;
		else {
			this.setSortColumn_locations("vista1.code_Id_locations");
			this.isAscending_locations = false;
		}
	}
	public void ordinaPerBet_locations(ActionEvent event){
		meterList_location=null;
		if (this.sortColumn_locations.equals("vista1.bet"))
			this.isAscending_locations = !this.isAscending_locations;
		else {
			this.setSortColumn_locations("vista1.bet");
			this.isAscending_locations = false;
		}
	}
	public void ordinaPerWin_locations(ActionEvent event){
		meterList_location=null;
		if (this.sortColumn_locations.equals("vista1.win"))
			this.isAscending_locations = !this.isAscending_locations;
		else {
			this.setSortColumn_locations("vista1.win");
			this.isAscending_locations = false;
		}
	}
	public void ordinaPerGamesPlayed_locations(ActionEvent event){
		meterList_location=null;
		if (this.sortColumn_locations.equals("vista1.games_Played"))
			this.isAscending_locations = !this.isAscending_locations;
		else {
			this.setSortColumn_locations("vista1.games_Played");
			this.isAscending_locations = false;
		}
	}
	public void ordinaPerTotalIn_locations(ActionEvent event){
		meterList_location=null;
		if (this.sortColumn_locations.equals("vista2.total_In"))
			this.isAscending_locations = !this.isAscending_locations;
		else {
			this.setSortColumn_locations("vista2.total_In");
			this.isAscending_locations = false;
		}
	}
	public void ordinaPerTotalOut_locations(ActionEvent event){
		meterList_location=null;
		if (this.sortColumn_locations.equals("vista2.total_Out"))
			this.isAscending_locations = !this.isAscending_locations;
		else {
			this.setSortColumn_locations("vista2.total_Out");
			this.isAscending_locations = false;
		}
	}
	public void ordinaPerTicketIn_locations(ActionEvent event){
		meterList_location=null;
		if (this.sortColumn_locations.equals("vista2.ticket_In"))
			this.isAscending_locations = !this.isAscending_locations;
		else {
			this.setSortColumn_locations("vista2.ticket_In");
			this.isAscending_locations = false;
		}
	}
	public void ordinaPerTicketOut_locations(ActionEvent event){
		meterList_location=null;
		if (this.sortColumn_locations.equals("vista2.ticket_Out"))
			this.isAscending_locations = !this.isAscending_locations;
		else {
			this.setSortColumn_locations("vista2.ticket_Out");
			this.isAscending_locations = false;
		}
	}
	public void ordinaPerCoinIn_locations(ActionEvent event){
		meterList_location=null;
		if (this.sortColumn_locations.equals("vista2.coin_In"))
			this.isAscending_locations = !this.isAscending_locations;
		else {
			this.setSortColumn_locations("vista2.coin_In");
			this.isAscending_locations = false;
		}
	}	
	public void ordinaPerBillIn_locations(ActionEvent event){
		meterList_location=null;
		if (this.sortColumn_locations.equals("vista2.bill_In"))
			this.isAscending_locations = !this.isAscending_locations;
		else {
			this.setSortColumn_locations("vista2.bill_In");
			this.isAscending_locations = false;
		}
	}	
	public void ordinaPerCardIn_locations(ActionEvent event){
		meterList_location=null;
		if (this.sortColumn_locations.equals("vista2.card_In"))
			this.isAscending_locations = !this.isAscending_locations;
		else {
			this.setSortColumn_locations("vista2.card_In");
			this.isAscending_locations = false;
		}
	}
	public void ordinaPerTotalPrepaidIn_locations(ActionEvent event){
		meterList_location=null;
		if (this.sortColumn_locations.equals("vista2.total_Prepaid_In"))
			this.isAscending_locations = !this.isAscending_locations;
		else {
			this.setSortColumn_locations("vista2.total_Prepaid_In");
			this.isAscending_locations = false;
		}
	}
	public void ordinaPerJackpotWins_locations(ActionEvent event){
		meterList_location=null;
		if (this.sortColumn_locations.equals("vista1.jackpot_Wins"))
			this.isAscending_locations = !this.isAscending_locations;
		else {
			this.setSortColumn_locations("vista1.jackpot_Wins");
			this.isAscending_locations = false;
		}
	}
	public void ordinaPerJackpotContribution_locations(ActionEvent event){
		meterList_location=null;
		if (this.sortColumn_locations.equals("vista1.jackpot_Contribution"))
			this.isAscending_locations = !this.isAscending_locations;
		else {
			this.setSortColumn_locations("vista1.jackpot_Contribution");
			this.isAscending_locations = false;
		}
	}
	public void ordinaPerPreu_locations(ActionEvent event){
		meterList_location=null;
		if (this.sortColumn_locations.equals("vista1.preu"))
			this.isAscending_locations = !this.isAscending_locations;
		else {
			this.setSortColumn_locations("vista1.preu");
			this.isAscending_locations = false;
		}
	}
	public void ordinaPerAams_locations(ActionEvent event){
		meterList_location=null;
		if (this.sortColumn_locations.equals("vista1.aams"))
			this.isAscending_locations = !this.isAscending_locations;
		else {
			this.setSortColumn_locations("vista1.aams");
			this.isAscending_locations = false;
		}
	}
	public void ordinaPerNetWin_locations(ActionEvent event){
		meterList_location=null;
		if (this.sortColumn_locations.equals("vista1.net_Win"))
			this.isAscending_locations = !this.isAscending_locations;
		else {
			this.setSortColumn_locations("vista1.net_Win");
			this.isAscending_locations = false;
		}
	}
	public void ordinaPerHouseWin_locations(ActionEvent event){
		meterList_location=null;
		if (this.sortColumn_locations.equals("vista1.house_Win"))
			this.isAscending_locations = !this.isAscending_locations;
		else {
			this.setSortColumn_locations("vista1.house_Win");
			this.isAscending_locations = false;
		}
	}
	public void ordinaPerSupplierProfit_locations(ActionEvent event){
		meterList_location=null;
		if (this.sortColumn_locations.equals("vista1.supplier_Profit"))
			this.isAscending_locations = !this.isAscending_locations;
		else {
			this.setSortColumn_locations("vista1.supplier_Profit");
			this.isAscending_locations = false;
		}
	}
	public void ordinaPerOperatorsProfit_locations(ActionEvent event){
		meterList_location=null;
		if (this.sortColumn_locations.equals("vista1.operators_Profit"))
			this.isAscending_locations = !this.isAscending_locations;
		else {
			this.setSortColumn_locations("vista1.operatorsProfit");
			this.isAscending_locations = false;
		}
	}
	public void ordinaPerBplusNetProfit_locations(ActionEvent event){
		meterList_location=null;
		if (this.sortColumn_locations.equals("vista1.bplus_Net_Profit"))
			this.isAscending_locations = !this.isAscending_locations;
		else {
			this.setSortColumn_locations("vista1.bplus_Net_Profit");
			this.isAscending_locations = false;
		}
	}
	public void ordinaPerStato_locations(ActionEvent event){
		meterList_location=null;
		if (this.sortColumn_locations.equals("vista1.stato"))
			this.isAscending_locations = !this.isAscending_locations;
		else {
			this.setSortColumn_locations("vista1.stato");
			this.isAscending_locations = false;
		}
	}
	
	
	
	//ORDINAMENTI TAB VLT
	public void ordinaPerLocationAddress_vlt(ActionEvent event){
		meterList_vlt=null;
		if (this.sortColumn_vlt.equals("spaziodim.location_Address"))
			this.isAscending_vlt = !this.isAscending_vlt;
		else {
			this.setSortColumn_vlt("spaziodim.location_Address");
			this.isAscending_vlt = false;
		}
	}
	public void ordinaPerRegione_vlt(ActionEvent event){
		meterList_vlt=null;
		if (this.sortColumn_vlt.equals("spaziodim.region"))
			this.isAscending_vlt = !this.isAscending_vlt;
		else {
			this.setSortColumn_vlt("spaziodim.region");
			this.isAscending_vlt = false;
		}
	}
	public void ordinaPerProvincia_vlt(ActionEvent event){
		meterList_vlt=null;
		if (this.sortColumn_vlt.equals("spaziodim.SIGLA_PROVINCIA"))
			this.isAscending_vlt = !this.isAscending_vlt;
		else {
			this.setSortColumn_vlt("spaziodim.SIGLA_PROVINCIA");
			this.isAscending_vlt = false;
		}
	}
	public void ordinaPerComune_vlt(ActionEvent event){
		meterList_vlt=null;
		if (this.sortColumn_vlt.equals("spaziodim.city"))
			this.isAscending_vlt = !this.isAscending_vlt;
		else {
			this.setSortColumn_vlt("spaziodim.city");
			this.isAscending_vlt = false;
		}
	}
	public void ordinaPerMachineSystemId_vlt(ActionEvent event){
		meterList_vlt=null;
		if (this.sortColumn_vlt.equals("spaziodim.machineSystemId"))
			this.isAscending_vlt = !this.isAscending_vlt;
		else {
			this.setSortColumn_vlt("spaziodim.machineSystemId");
			this.isAscending_vlt = false;
		}
	}
	public void ordinaPerAamsGameId_vlt(ActionEvent event){
		meterList_vlt=null;
		if (this.sortColumn_vlt.equals("spaziodim.aamsGameId"))
			this.isAscending_vlt = !this.isAscending_vlt;
		else {
			this.setSortColumn_vlt("spaziodim.aamsGameId");
			this.isAscending_vlt = false;
		}
	}
	public void ordinaPerCodGestore_vlt(ActionEvent event){
		meterList_vlt=null;
		if (this.sortColumn_vlt.equals("clientidim.cod_Gestore"))
			this.isAscending_vlt = !this.isAscending_vlt;
		else {
			this.setSortColumn_vlt("clientidim.cod_Gestore");
			this.isAscending_vlt = false;
		}
	}
	public void ordinaPerAamsLocationId_vlt(ActionEvent event){
		meterList_vlt=null;
		if (this.sortColumn_vlt.equals("spaziodim.aams_Location_Id"))
			this.isAscending_vlt = !this.isAscending_vlt;
		else {
			this.setSortColumn_vlt("spaziodim.aams_Location_Id");
			this.isAscending_vlt = false;
		}
	}
	public void ordinaPerAamsVltId_vlt(ActionEvent event){
		meterList_vlt=null;
		if (this.sortColumn_vlt.equals("spaziodim.aams_Vlt_Id"))
			this.isAscending_vlt = !this.isAscending_vlt;
		else {
			this.setSortColumn_vlt("spaziodim.aams_Vlt_Id");
			this.isAscending_vlt = false;
		}
	}	
	public void ordinaPerGsVltId_vlt(ActionEvent event){
		meterList_vlt=null;
		if (this.sortColumn_vlt.equals("spaziodim.gs_Vlt_Id"))
			this.isAscending_vlt = !this.isAscending_vlt;
		else {
			this.setSortColumn_vlt("spaziodim.gs_Vlt_Id");
			this.isAscending_vlt = false;
		}
	}	
	public void ordinaPerLocationName_vlt(ActionEvent event){
		meterList_vlt=null;
		if (this.sortColumn_vlt.equals("spaziodim.location_Name"))
			this.isAscending_vlt = !this.isAscending_vlt;
		else {
			this.setSortColumn_vlt("spaziodim.location_Name");
			this.isAscending_vlt = false;
		}
	}
	public void ordinaPerMonth_vlt(ActionEvent event){
		meterList_vlt=null;
		if (this.sortColumn_vlt.equals("tempodim.month"))
			this.isAscending_vlt = !this.isAscending_vlt;
		else {
			this.setSortColumn_vlt("tempodim.month");
			this.isAscending_vlt = false;
		}
	}
	public void ordinaPerDay_vlt(ActionEvent event){
		meterList_vlt=null;
		if (this.sortColumn_vlt.equals("tempodim.data"))
			this.isAscending_vlt = !this.isAscending_vlt;
		else {
			this.setSortColumn_vlt("tempodim.data");
			this.isAscending_vlt = false;
		}
	}
	public void ordinaPerMachine_vlt(ActionEvent event){
		meterList_vlt=null;
		if (this.sortColumn_vlt.equals("spaziodim.code_Id_Vlt"))
			this.isAscending_vlt = !this.isAscending_vlt;
		else {
			this.setSortColumn_vlt("spaziodim.code_Id_Vlt");
			this.isAscending_vlt = false;
		}
	}
	public void ordinaPerBet_vlt(ActionEvent event){
		meterList_vlt=null;
		if (this.sortColumn_vlt.equals("bet"))
			this.isAscending_vlt = !this.isAscending_vlt;
		else {
			this.setSortColumn_vlt("bet");
			this.isAscending_vlt = false;
		}
	}
	public void ordinaPerWin_vlt(ActionEvent event){
		meterList_vlt=null;
		if (this.sortColumn_vlt.equals("win"))
			this.isAscending_vlt = !this.isAscending_vlt;
		else {
			this.setSortColumn_vlt("win");
			this.isAscending_vlt = false;
		}
	}
	public void ordinaPerGamesPlayed_vlt(ActionEvent event){
		meterList_vlt=null;
		if (this.sortColumn_vlt.equals("games_Played"))
			this.isAscending_vlt = !this.isAscending_vlt;
		else {
			this.setSortColumn_vlt("games_Played");
			this.isAscending_vlt = false;
		}
	}
	public void ordinaPerTotalIn_vlt(ActionEvent event){
		meterList_vlt=null;
		if (this.sortColumn_vlt.equals("total_In"))
			this.isAscending_vlt = !this.isAscending_vlt;
		else {
			this.setSortColumn_vlt("total_In");
			this.isAscending_vlt = false;
		}
	}
	public void ordinaPerTotalOut_vlt(ActionEvent event){
		meterList_vlt=null;
		if (this.sortColumn_vlt.equals("total_Out"))
			this.isAscending_vlt = !this.isAscending_vlt;
		else {
			this.setSortColumn_vlt("total_Out");
			this.isAscending_vlt = false;
		}
	}
	public void ordinaPerTicketIn_vlt(ActionEvent event){
		meterList_vlt=null;
		if (this.sortColumn_vlt.equals("ticket_In"))
			this.isAscending_vlt = !this.isAscending_vlt;
		else {
			this.setSortColumn_vlt("ticket_In");
			this.isAscending_vlt = false;
		}
	}
	public void ordinaPerTicketOut_vlt(ActionEvent event){
		meterList_vlt=null;
		if (this.sortColumn_vlt.equals("ticket_Out"))
			this.isAscending_vlt = !this.isAscending_vlt;
		else {
			this.setSortColumn_vlt("ticket_Out");
			this.isAscending_vlt = false;
		}
	}
	public void ordinaPerCoinIn_vlt(ActionEvent event){
		meterList_vlt=null;
		if (this.sortColumn_vlt.equals("coin_In"))
			this.isAscending_vlt = !this.isAscending_vlt;
		else {
			this.setSortColumn_vlt("coin_In");
			this.isAscending_vlt = false;
		}
	}	
	public void ordinaPerBillIn_vlt(ActionEvent event){
		meterList_vlt=null;
		if (this.sortColumn_vlt.equals("bill_In"))
			this.isAscending_vlt = !this.isAscending_vlt;
		else {
			this.setSortColumn_vlt("bill_In");
			this.isAscending_vlt = false;
		}
	}	
	public void ordinaPerCardIn_vlt(ActionEvent event){
		meterList_vlt=null;
		if (this.sortColumn_vlt.equals("card_In"))
			this.isAscending_vlt = !this.isAscending_vlt;
		else {
			this.setSortColumn_vlt("card_In");
			this.isAscending_vlt = false;
		}
	}
	public void ordinaPerTotalPrepaidIn_vlt(ActionEvent event){
		meterList_vlt=null;
		if (this.sortColumn_vlt.equals("total_Prepaid_In"))
			this.isAscending_vlt = !this.isAscending_vlt;
		else {
			this.setSortColumn_vlt("total_Prepaid_In");
			this.isAscending_vlt = false;
		}
	}
	public void ordinaPerJackpotWins_vlt(ActionEvent event){
		meterList_vlt=null;
		if (this.sortColumn_vlt.equals("jackpot_Wins"))
			this.isAscending_vlt = !this.isAscending_vlt;
		else {
			this.setSortColumn_vlt("jackpot_Wins");
			this.isAscending_vlt = false;
		}
	}
	public void ordinaPerJackpotContribution_vlt(ActionEvent event){
		meterList_vlt=null;
		if (this.sortColumn_vlt.equals("jackpot_Contribution"))
			this.isAscending_vlt = !this.isAscending_vlt;
		else {
			this.setSortColumn_vlt("jackpot_Contribution");
			this.isAscending_vlt = false;
		}
	}
	public void ordinaPerPreu_vlt(ActionEvent event){
		meterList_vlt=null;
		if (this.sortColumn_vlt.equals("preu"))
			this.isAscending_vlt = !this.isAscending_vlt;
		else {
			this.setSortColumn_vlt("preu");
			this.isAscending_vlt = false;
		}
	}
	public void ordinaPerAams_vlt(ActionEvent event){
		meterList_vlt=null;
		if (this.sortColumn_vlt.equals("aams"))
			this.isAscending_vlt = !this.isAscending_vlt;
		else {
			this.setSortColumn_vlt("aams");
			this.isAscending_vlt = false;
		}
	}
	public void ordinaPerNetWin_vlt(ActionEvent event){
		meterList_vlt=null;
		if (this.sortColumn_vlt.equals("net_Win"))
			this.isAscending_vlt = !this.isAscending_vlt;
		else {
			this.setSortColumn_vlt("net_Win");
			this.isAscending_vlt = false;
		}
	}
	public void ordinaPerHouseWin_vlt(ActionEvent event){
		meterList_vlt=null;
		if (this.sortColumn_vlt.equals("house_Win"))
			this.isAscending_vlt = !this.isAscending_vlt;
		else {
			this.setSortColumn_vlt("house_Win");
			this.isAscending_vlt = false;
		}
	}
	public void ordinaPerSupplierProfit_vlt(ActionEvent event){
		meterList_vlt=null;
		if (this.sortColumn_vlt.equals("supplier_Profit"))
			this.isAscending_vlt = !this.isAscending_vlt;
		else {
			this.setSortColumn_vlt("supplier_Profit");
			this.isAscending_vlt = false;
		}
	}
	public void ordinaPerOperatorsProfit_vlt(ActionEvent event){
		meterList_vlt=null;
		if (this.sortColumn_vlt.equals("operators_Profit"))
			this.isAscending_vlt = !this.isAscending_vlt;
		else {
			this.setSortColumn_vlt("operators_Profit");
			this.isAscending_vlt = false;
		}
	}
	public void ordinaPerBplusNetProfit_vlt(ActionEvent event){
		meterList_vlt=null;
		if (this.sortColumn_vlt.equals("bplus_Net_Profit"))
			this.isAscending_vlt = !this.isAscending_vlt;
		else {
			this.setSortColumn_vlt("bplus_Net_Profit");
			this.isAscending_vlt = false;
		}
	}
	public void ordinaPerStato_vlt(ActionEvent event){
		meterList_vlt=null;
		if (this.sortColumn_vlt.equals("stato"))
			this.isAscending_vlt = !this.isAscending_vlt;
		else {
			this.setSortColumn_vlt("stato");
			this.isAscending_vlt = false;
		}
	}
	
	
	
	
	
	
	public List<MeterBean> query_vlt() {
		logger.info("getMeter_vlt");
		
		try {
						
			meterList_vlt = MeterBusinessDelegate.getInstance().retrieveMeter_vlt(
			location_name, aams_location_id, aams_vlt_id, regione, provincia,
			comune, cod_gestore, data1, data2, sortColumn_vlt, isAscending_vlt, nascondiSaleNonAperte_vlt, mostraTotale_vlt);		
			
//			if (mostraTotale_vlt){	//se è stato scelto di vedere i valori di un periodo di giorni come somma di essi
//				sommaTotale_vlt();
//			}
			
			ivItem = null;
			
			dataExporterXlsVlt.setResource(null);
			dataExporterCsvVlt.setResource(null);
			
		} catch (BusinessLayerException e) {
			e.printStackTrace();
			logger.error(e.toString());
			addFacesMessageForUI("Exception detected. Message: " +e.toString());
		}
		return meterList_vlt;	
	}	
	
	
	
	public List<MeterBean> query_game() {
		logger.info("getMeter_Game");		
		
		dataExporterXlsGame.setResource(null);
		dataExporterCsvGame.setResource(null);

		try {

			meterList_game = MeterBusinessDelegate.getInstance().retrieveMeter_game(
					location_name, aams_location_id, aams_vlt_id, regione, provincia,
					comune, cod_gestore, data1, data2, sortColumn, isAscending, nascondiSaleNonAperte_game, mostraTotale_game);
						
			ivItem = null;
	
			if (meterList_game.size()>65536){
				gameIsBig = true;			
			} else gameIsBig = false;
			
		} catch (BusinessLayerException e) {
			e.printStackTrace();
			logger.error(e.toString());
			addFacesMessageForUI("Exception detected. Message: " +e.toString());
		}
		return meterList_game;	
	}
	


	public List<MeterBean> query_location() {
		logger.info("getMeter_Location");
			
		dataExporterXlsLocation.setResource(null);
		dataExporterCsvLocation.setResource(null);
		
		try {
			
			meterList_location = MeterBusinessDelegate.getInstance().retrieveMeter_locations(
					location_name, aams_location_id, regione, provincia,
					comune, cod_gestore, data1, data2, sortColumn_locations, isAscending_locations, nascondiSaleNonAperte_location, mostraTotale_location);
			
//			if (mostraTotale_location){	//se è stato scelto di vedere i valori di un periodo di giorni come somma di essi
//				sommaTotale_location();
//			}
			
			ivItem = null;
			
		} catch (BusinessLayerException e) {
			e.printStackTrace();
			logger.error(e.toString());
			addFacesMessageForUI("Exception detected. Message: " +e.toString());
		}
		return meterList_location;	
	}
	
	
	
	public List<MeterBean> query_regioni() {
		logger.info("getMeter_Regioni");
			
		dataExporterXlsRegioni.setResource(null);
		dataExporterCsvRegioni.setResource(null);
		
		try {
			
			meterList_regioni = MeterBusinessDelegate.getInstance().retrieveMeter_regioni(
					regione, data1, data2, sortColumn_regioni, isAscending_regioni, escludiSaleNonAperte_regioni, mostraTotale_regioni);
			
			if (mostraTotale_regioni){	//se è stato scelto di vedere i valori di un periodo di giorni come somma di essi
				sommaTotale_regioni();
			}
			
			ivItem = null;
			
		} catch (BusinessLayerException e) {
			e.printStackTrace();
			logger.error(e.toString());
			addFacesMessageForUI("Exception detected. Message: " +e.toString());
		}
		return meterList_regioni;	
	}
	
	
	
	public List<MeterBean> query() {
		if (this.selectIndex == 0)
			return query_game();
		else if (this.selectIndex == 1)
			return query_vlt();
		else if (this.selectIndex == 2)
			return query_location();
		else if (this.selectIndex == 3)
			return query_regioni();
		else return new ArrayList<MeterBean>();
	}


	@Override
	public void setEntity(Object value) {
		// TODO Auto-generated method stub
		
	}

	public void setItem(MeterBean ivItem) {
		this.ivItem = ivItem;
	}

	public MeterBean getItem() {
		return ivItem;
	}

	public List<MeterBean> getMeterList_game() {
		if (this.selectIndex == 0) {
			if (meterList_game == null)
				meterList_game = query_game();
		}
		return meterList_game;
		
	}

	public void setMeterList_game(List<MeterBean> meterList_game) {
		this.meterList_game = meterList_game;
	}
	
	public List<MeterBean> getMeterList_vlt() {
		if (this.selectIndex == 1) {
			if (meterList_vlt == null)
				meterList_vlt = query_vlt();
		}
		return meterList_vlt;
	}

	public void setMeterList_vlt(List<MeterBean> meterList_vlt) {
		this.meterList_vlt = meterList_vlt;
	}
	
	public List<MeterBean> getmeterList_location() {
		if (this.selectIndex == 2) {
			if (meterList_location == null)
				meterList_location = query_location();
		}
		return meterList_location;
	}

	public void setmeterList_location(List<MeterBean> meterList_location) {
		this.meterList_location = meterList_location;
	}
	
	
	public List<MeterBean> getMeterList_regioni() {
		if (this.selectIndex == 3) {
			if (meterList_regioni == null)
				meterList_regioni = query_regioni();
		}
		return meterList_regioni;
	}

	public void setMeterList_regioni(List<MeterBean> meterList_regioni) {
		this.meterList_regioni = meterList_regioni;
	}
	
	
	
	public String getLocationAddressColumnName(){
		return locationAddressColumnName;
	}
	public String getRegioneColumnName() {
		return regioneColumnName;
	}
	public String getProvinciaColumnName() {
		return provinciaColumnName;
	}

	public String getComuneColumnName() {
		return comuneColumnName;
	}

	public String getMachineSystemIdColumnName() {
		return machineSystemIdColumnName;
	}

	public String getAamsGameIdColumnName() {
		return aamsGameIdColumnName;
	}
	public String getAamsGameNameColumnName() {
		return aamsGameNameColumnName;
	}

	
	public String getAamsVltIdColumnName() {
		return aamsVltIdColumnName;
	}
	
	public String getGsVltIdColumnName() {
		return gsVltIdColumnName;
	}
	
	public String getCodGestoreColumnName() {
		return codGestoreColumnName;
	}

	public String getAamsLocationIdColumnName() {
		return aamsLocationIdColumnName;
	}

	public String getLocationNameColumnName() {
		return locationNameColumnName;
	}

	public String getMonthColumnName() {
		return monthColumnName;
	}

	public  String getDayColumnName() {
		return dayColumnName;
	}

	public  String getMachineColumnName() {
		return machineColumnName;
	}
	public  String getBetColumnName() {
		return betColumnName;
	}
	public  String getWinColumnName() {
		return winColumnName;
	}
	public  String getGamesPlayedColumnName() {
		return gamesPlayedColumnName;
	}
	public  String getTotalInColumnName() {
		return totalInColumnName;
	}
	public  String getTotalOutColumnName() {
		return totalOutColumnName;
	}
	public  String getTicketInColumnName() {
		return ticketInColumnName;
	}
	public  String getTicketOutColumnName() {
		return ticketOutColumnName;
	}

	public  String getCoinInColumnName() {
		return coinInColumnName;
	}

	public  String getBillInColumnName() {
		return billInColumnName;
	}

	public  String getCardInColumnName() {
		return cardInColumnName;
	}

	public  String getTotalPrepaidInColumnName() {
		return totalPrepaidInColumnName;
	}

	public  String getJackpotWinsColumnName() {
		return jackpotWinsColumnName;
	}

	public  String getJackpotContributionColumnName() {
		return jackpotContributionColumnName;
	}

	public  String getPreuColumnName() {
		return preuColumnName;
	}

	public  String getAamsColumnName() {
		return aamsColumnName;
	}

	public  String getNetWinColumnName() {
		return netWinColumnName;
	}

	public  String getHouseWinColumnName() {
		return houseWinColumnName;
	}

	public  String getSupplierProfitColumnName() {
		return supplierProfitColumnName;
	}

	public  String getOperatorsProfitColumnName() {
		return operatorsProfitColumnName;
	}

	public  String getBplusNetProfitColumnName() {
		return bplusNetProfitColumnName;
	}

	public  String getStatoColumnName() {
		return statoColumnName;
	}
	
	public  String getNoteColumnName() {
		return noteColumnName;
	}
	
	public  String getNumVltColumnName() {
		return numVltColumnName;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

// Da qui in poi è la parte della ricerca
	
	
	
	
	
	
	
	
	
	
	
	
	
	private String location_name;
	private String aams_location_id;
	private String aams_vlt_id;
	private String regione;
	private String provincia;
	private String comune;
	private String cod_gestore;

	private String exporttype;
	
	private int selectIndex = 2;
	
	private Date data1 = DateUtil.oraDelleStreghe(DateUtil.calcolaGiornoPrecedente(new Date()));	
	private Date data2 = DateUtil.ventitreCinquantanove(DateUtil.calcolaGiornoPrecedente(new Date()));
	

	private List<MeterBean> ricercaList;
	private List<Regioni> listRegioni;
	private List<Province> listProvince;
	private List<Comuni> listComuni;
	private List<SelectItem> lista_regioni;
	private List<SelectItem> lista_province;
	private List<SelectItem> lista_comuni;
	private HtmlSelectOneMenu cmbRegioni;
	private HtmlSelectOneMenu cmbProvince;
	private HtmlSelectOneMenu cmbComuni;
	

	private String visibile_province="default";
	private String visibile_comuni="default";


	public MeterController(){
		super();

	}



	public List<SelectItem> getLista_regioni() throws BusinessLayerException {
		if (lista_regioni == null) {
			lista_regioni = new ArrayList<SelectItem>();

			listRegioni = this.getListRegioni();

			Iterator<Regioni> i = listRegioni.iterator();
			while (i.hasNext()) {
				Regioni reg = i.next();
				lista_regioni.add(new SelectItem(reg.getIdReg(),reg.getNome()));
			}

		}
		return lista_regioni;
	}

	public List<SelectItem> getLista_province() throws BusinessLayerException {
		if (lista_province == null) {
			lista_province = new ArrayList<SelectItem>();
		}
		else
			lista_province.clear();
		listProvince = this.getListProvince();
		if (listProvince != null) {

			Iterator<Province> i = listProvince.iterator();
			while (i.hasNext()) {
				Province pro = i.next();
				lista_province.add(new SelectItem(pro.getIdProv(),pro.getNome()));
			}
		}

		return lista_province;
	}

	public List<SelectItem> getLista_comuni() throws BusinessLayerException {
		if (lista_comuni == null) {
			lista_comuni = new ArrayList<SelectItem>();
		}
		else
			lista_comuni.clear();		
		listComuni = this.getListComuni();
		if (listComuni != null) {

			Iterator<Comuni> i = listComuni.iterator();
			while (i.hasNext()) {
				Comuni com = i.next();
				lista_comuni.add(new SelectItem(String.valueOf(com.getIdComune()),com.getNome()));
			}
		}

		return lista_comuni;
	}

	public void changeRegione(ValueChangeEvent ev) {
		logger.debug("changeRegione: " +ev.toString());
		if (ev.getNewValue() != null) {
			changeRegione((String) ev.getNewValue());
		}
	}

	/**
	 * carica le provincie della regione e resetta i valori di selezione di
	 * provincia e comune
	 * 
	 * @param value
	 */
	public void changeRegione(String value) {
		if (lista_comuni != null && lista_comuni.size() > 0)
			lista_comuni.clear();
		if (lista_province != null && lista_province.size() > 0)
			lista_province.clear();		

		if (listComuni != null && listComuni.size() > 0)
			listComuni.clear();
		if (listProvince != null && listProvince.size() > 0)
			listProvince.clear();	
		regione = value;
		cmbRegioni.setValue(value);
		cmbProvince.setValue(" - ");
		cmbComuni.setValue("-1");
		provincia = " - ";
		comune = "-1";
	}
	
	public void changeProvincia(ValueChangeEvent ev) {
		logger.debug("changeProvincia: " +ev.toString());
		if (ev.getNewValue() != null) {
			changeProvincia((String) ev.getNewValue());
		}
	}
	
	
	public void changeProvincia(String value) {
		if (lista_comuni != null && lista_comuni.size() > 0)
			lista_comuni.clear();
		if (listComuni != null && listComuni.size() > 0)
			listComuni.clear();

		provincia = value;	
		comune = "-1";
		cmbProvince.setValue(value);
		cmbComuni.setValue("-1");		
	}	
	
	public void changeComune(ValueChangeEvent ev) {
		logger.debug("changeComune: " +ev.toString());
		if (ev.getNewValue() != null) {
			changeComune((String) ev.getNewValue());
		}
	}
	
	
	public void changeComune(String value) {
		comune = value;
		cmbComuni.setValue(value);		
	}	
	


	public HtmlSelectOneMenu getCmbRegioni() {
		return cmbRegioni;
	}

	public void setCmbRegioni(HtmlSelectOneMenu cmbRegioni) {
		this.cmbRegioni = cmbRegioni;
	}

	public HtmlSelectOneMenu getCmbProvince() {
		return cmbProvince;
	}

	public void setCmbProvince(HtmlSelectOneMenu cmbProvince) {
		this.cmbProvince = cmbProvince;
	}

	public HtmlSelectOneMenu getCmbComuni() {
		return cmbComuni;
	}

	public void setCmbComuni(HtmlSelectOneMenu cmbComuni) {
		this.cmbComuni = cmbComuni;
	}

	public void setComune(String comune) {
		this.comune = comune;
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
	
	public String getAams_vlt_id() {
		return aams_vlt_id;
	}

	public void setAams_vlt_id(String aams_vlt_id) {
		this.aams_vlt_id = aams_vlt_id;
	}

	public String getRegione() {
		return regione;
	}

	public void setRegione(String regione) {
		this.regione = regione;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getComune() {
		return comune;
	}


	public String getCod_gestore() {
		return cod_gestore;
	}

	public void setCod_gestore(String cod_gestore) {
		this.cod_gestore = cod_gestore;
	}


	@Override
	protected boolean isDefaultAscending(String sortColumn) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public void tabChangeListener(TabChangeEvent event){
		this.selectIndex = event.getNewTabIndex();
	}
	
	
	public void mdplocation(){
		reset();
		aams_location_id = FacesUtils.getRequestParameter("loc_id");
		query_vlt();
		this.selectIndex = 1;
	}
	
	public void mdpvlt(){
		aams_vlt_id = FacesUtils.getRequestParameter("vlt_id");
		query_game();
		this.selectIndex = 0;
	}
	
	public void pageChangeListener(){
		getRequest().getAttribute("pageIndex");
	}


	public void setExporttype(String exporttype) {
		this.exporttype = exporttype;
	}

	public String getExporttype() {
		return exporttype;
	}
	
	public void setSelectIndex(int selectIndex) {
		this.selectIndex = selectIndex;
	}
	
	public int getSelectIndex() {
		return selectIndex;
	}


	public List<MeterBean> getRicercaList() {
		return ricercaList;
	}

	public void setRicercaList(List<MeterBean> ricercaList) {
		this.ricercaList = ricercaList;
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

	public void setListRegioni(List<Regioni> listRegioni) {
		this.listRegioni = listRegioni;
	}

	public List<Regioni> getListRegioni() throws BusinessLayerException {
		try {
			if (listRegioni == null) {
				listRegioni = GeographicBusinessDelegate.getInstance().getRegioni();
			}
			return listRegioni;
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

	public void setListProvince(List<Province> listProvince) {
		this.listProvince = listProvince;
	}

	public List<Province> getListProvince() throws BusinessLayerException {
		try {
			if (listProvince == null || (listProvince!=null && listProvince.size()==0)) {
				if (regione != null && (regione.length() > 0 && !regione.equals(" - ")))
					listProvince = GeographicBusinessDelegate.getInstance()
					.getProvinceByIdRegione(regione);
				
			}
			return listProvince;
		} catch (BusinessLayerException ble) {
			ble.printStackTrace();
			logger.error(ble.toString());
			throw ble;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.toString());
			addFacesMessageForUI("ListProvince: "+e.toString());
			throw new BusinessLayerException(e.toString());
		}
	}

	public void setListComuni(List<Comuni> listComuni) {
		this.listComuni = listComuni;
	}

	public List<Comuni> getListComuni() throws BusinessLayerException {
		try {
			if (listComuni == null || (listComuni!=null && listComuni.size()==0)) {
				if (provincia != null && (provincia.length() > 0 && !provincia.equals(" - ")))
					listComuni = GeographicBusinessDelegate.getInstance()
					.getComuniByIdProvincia(provincia);

			}
			return listComuni;
		} catch (BusinessLayerException ble) {
			ble.printStackTrace();
			logger.error(ble.toString());
			addFacesMessageForUI("getListComuni: "+ble.toString());
			throw ble;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.toString());
			addFacesMessageForUI("getListComuni: "+e.toString());
			throw new BusinessLayerException(e.toString());
		}
	}

	public String getVisibile_province() {
		return visibile_province;
	}

	public void setVisibile_province(String visibile_province) {
		this.visibile_province = visibile_province;
	}

	public String getVisibile_comuni() {
		return visibile_comuni;
	}

	public void setVisibile_comuni(String visibile_comuni) {
		this.visibile_comuni = visibile_comuni;
	}

	public void setLista_regioni(List<SelectItem> lista_regioni) {
		this.lista_regioni = lista_regioni;
	}
	
	
	

	
	
	
	
	
	
	
	public void setDataPaginator(DataPaginator dataPaginator) {
		this.dataPaginator = dataPaginator;
	}

	public DataPaginator getDataPaginator() {
		return dataPaginator;
	}

	public int getDataNumPagina(){
		if (dataPaginator!=null)
			return dataPaginator.getPageIndex();
		else return 0;
	}
	
	
	
	
	//MOSTRA TOTALE
	//LOCATION	
	private boolean mostraTotale_location = false;
	
	public boolean isMostraTotale_location() {
		return mostraTotale_location;
	}
	
	
	public void mostraSingoliGiorni_location(){
		logger.info("mostraSingoliGiorni_location");
		if(mostraTotale_location){
			mostraTotale_location = false;
			query_location();
		}
	}
	
	public void mostraTotale_location(){
		logger.info("mostraTotale_location");
		if (!mostraTotale_location) {
			mostraTotale_location = true;
			query_location();
		}
	}
	
	public void sommaTotale_location() {

		if (meterList_location.size() != 0) {
			MeterBean t = new MeterBean();
			inizializzaMeterBean(t);

			List<MeterBean> totalList = new ArrayList<MeterBean>();

			String current_location = meterList_location.get(0).getAams_location_id();

			t.setRegione(meterList_location.get(0).getRegione());
			t.setProvincia(meterList_location.get(0).getProvincia());
			t.setComune(meterList_location.get(0).getComune());
			t.setLocation_address(meterList_location.get(0).getLocation_address());
			t.setCod_gestore(meterList_location.get(0).getCod_gestore());
			t.setAams_location_id(meterList_location.get(0).getAams_location_id());
			t.setLocation_name(meterList_location.get(0).getLocation_name());

			for (MeterBean m : meterList_location) {
				if (!current_location.equals(m.getAams_location_id())) {

					t = new MeterBean();
					inizializzaMeterBean(t);

					current_location = m.getAams_location_id();

					t.setAams_location_id(current_location);
					t.setRegione(m.getRegione());
					t.setProvincia(m.getProvincia());
					t.setComune(m.getComune());
					t.setLocation_address(m.getLocation_address());
					t.setCod_gestore(m.getCod_gestore());
					t.setAams_location_id(m.getAams_location_id());
					t.setLocation_name(m.getLocation_name());
					
					totalList.add(t);
				}
				t.setNumVlt(t.getNumVlt() + m.getNumVlt());
				t.setBet(t.getBet() + m.getBet());
				t.setWin(t.getWin() + m.getWin());
				t.setGamesPlayed(t.getGamesPlayed() + m.getGamesPlayed());
				t.setTotalIn(t.getTotalIn() + m.getTotalIn());
				t.setTotalOut(t.getTotalOut() + m.getTotalOut());
				t.setTicketIn(t.getTicketIn() + m.getTicketIn());
				t.setTicketOut(t.getTicketOut() + m.getTicketOut());
				t.setCoinIn(t.getCoinIn() + m.getCoinIn());
				t.setBillIn(t.getBillIn() + m.getBillIn());
				t.setCardIn(t.getCardIn() + m.getCardIn());
				t.setTotalPrepaidIn(t.getTotalPrepaidIn()+ m.getTotalPrepaidIn());
				t.setJackpotWins(t.getJackpotWins() + m.getJackpotWins());
				t.setJackpotContribution(t.getJackpotContribution()+ m.getJackpotContribution());
				t.setPreu(t.getPreu() + m.getPreu());
				t.setAams(t.getAams() + m.getAams());
				t.setNetWin(t.getNetWin() + m.getNetWin());
				t.setHouseWin(t.getHouseWin() + m.getHouseWin());
				t.setSupplierProfit(t.getSupplierProfit()+ m.getSupplierProfit());
				t.setOperatorsProfit(t.getOperatorsProfit()+ m.getOperatorsProfit());
				t.setBplusNetProfit(t.getBplusNetProfit()+ m.getBplusNetProfit());
			}
			totalList.add(t);

			meterList_location = totalList;
		}
	}
	
	public void inizializzaMeterBean(MeterBean m){
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		
		m.setPeriodo("da "+df.format(data1)+" a "+df.format(data2));
		m.setNumVlt(0);
		m.setBet(0.0);
		m.setWin(0.0);
		m.setGamesPlayed((long) 0);
		m.setTotalIn(0.0);
		m.setTotalOut(0.0);
		m.setTicketIn(0.0);
		m.setTicketOut(0.0);
		m.setCoinIn(0.0);
		m.setBillIn(0.0);
		m.setCardIn(0.0);
		m.setTotalPrepaidIn(0.0);
		m.setJackpotWins(0.0);
		m.setJackpotContribution(0.0);
		m.setPreu(0.0);
		m.setAams(0.0);
		m.setNetWin(0.0);
		m.setHouseWin(0.0);
		m.setSupplierProfit(0.0);
		m.setOperatorsProfit(0.0);
		m.setBplusNetProfit(0.0);
	}
	
	//VLT
	private boolean mostraTotale_vlt = false;
	
	public boolean isMostraTotale_vlt() {
		return mostraTotale_vlt;
	}
	
	
	public void mostraSingoliGiorni_vlt(){
		logger.info("mostraSingoliGiorni_vlt");
		if(mostraTotale_vlt){
			mostraTotale_vlt = false;
			query_vlt();
		}
	}
	
	public void mostraTotale_vlt(){
		logger.info("mostraTotale_vlt");
		if (!mostraTotale_vlt) {
			mostraTotale_vlt = true;
			query_vlt();
		}
	}
	
	public void sommaTotale_vlt() {

		if (meterList_vlt.size() != 0) {
			MeterBean t = new MeterBean();
			inizializzaMeterBean(t);

			List<MeterBean> totalList = new ArrayList<MeterBean>();

			String current_vlt = meterList_vlt.get(0).getAams_vlt_id();

			t.setRegione(meterList_vlt.get(0).getRegione());
			t.setProvincia(meterList_vlt.get(0).getProvincia());
			t.setComune(meterList_vlt.get(0).getComune());
			t.setLocation_address(meterList_vlt.get(0).getLocation_address());
			t.setCod_gestore(meterList_vlt.get(0).getCod_gestore());
			t.setAams_location_id(meterList_vlt.get(0).getAams_location_id());
			t.setLocation_name(meterList_vlt.get(0).getLocation_name());
			t.setAams_vlt_id(meterList_vlt.get(0).getAams_vlt_id());
			t.setGs_vlt_id(meterList_vlt.get(0).getGs_vlt_id());

			for (MeterBean m : meterList_vlt) {
				if (!current_vlt.equals(m.getAams_vlt_id())) {
					
					t = new MeterBean();
					inizializzaMeterBean(t);

					current_vlt = m.getAams_vlt_id();

					t.setRegione(m.getRegione());
					t.setProvincia(m.getProvincia());					
					t.setComune(m.getComune());
					t.setLocation_address(m.getLocation_address());
					t.setCod_gestore(m.getCod_gestore());
					t.setAams_location_id(m.getAams_location_id());
					t.setLocation_name(m.getLocation_name());
					t.setAams_vlt_id(m.getAams_vlt_id());
					t.setGs_vlt_id(m.getGs_vlt_id());
					
					totalList.add(t);
				}
				t.setBet(t.getBet() + m.getBet());
				t.setWin(t.getWin() + m.getWin());
				t.setGamesPlayed(t.getGamesPlayed() + m.getGamesPlayed());
				t.setTotalIn(t.getTotalIn() + m.getTotalIn());
				t.setTotalOut(t.getTotalOut() + m.getTotalOut());
				t.setTicketIn(t.getTicketIn() + m.getTicketIn());
				t.setTicketOut(t.getTicketOut() + m.getTicketOut());
				t.setCoinIn(t.getCoinIn() + m.getCoinIn());
				t.setBillIn(t.getBillIn() + m.getBillIn());
				t.setCardIn(t.getCardIn() + m.getCardIn());
				t.setTotalPrepaidIn(t.getTotalPrepaidIn()+ m.getTotalPrepaidIn());
				t.setJackpotWins(t.getJackpotWins() + m.getJackpotWins());
				t.setJackpotContribution(t.getJackpotContribution()+ m.getJackpotContribution());
				t.setPreu(t.getPreu() + m.getPreu());
				t.setAams(t.getAams() + m.getAams());
				t.setNetWin(t.getNetWin() + m.getNetWin());
				t.setHouseWin(t.getHouseWin() + m.getHouseWin());
				t.setSupplierProfit(t.getSupplierProfit()+ m.getSupplierProfit());
				t.setOperatorsProfit(t.getOperatorsProfit()+ m.getOperatorsProfit());
				t.setBplusNetProfit(t.getBplusNetProfit()+ m.getBplusNetProfit());
			}
			totalList.add(t);

			meterList_vlt = totalList;
		}
	}
	
	
	//REGIONE
	private boolean mostraTotale_regioni = false;
	
	public boolean isMostraTotale_regioni() {
		return mostraTotale_regioni;
	}
	
	
	public void mostraSingoliGiorni_regioni(){
		logger.info("mostraSingoliGiorni_regioni");
		if(mostraTotale_regioni){
			mostraTotale_regioni = false;
			query_regioni();
		}
	}
	
	public void mostraTotale_regioni(){
		logger.info("mostraTotale_regioni");
		if (!mostraTotale_regioni) {
			mostraTotale_regioni = true;
			query_regioni();
		}
	}
	
	public void sommaTotale_regioni() {

		if (meterList_regioni.size() != 0) {
			MeterBean t = new MeterBean();
			inizializzaMeterBean(t);

			List<MeterBean> totalList = new ArrayList<MeterBean>();

			String current_regione = meterList_regioni.get(0).getRegione();

			t.setRegione(meterList_regioni.get(0).getRegione());

			for (MeterBean m : meterList_regioni) {
				if (!current_regione.equals(m.getRegione())) {
					totalList.add(t);

					t = new MeterBean();
					inizializzaMeterBean(t);

					current_regione = m.getRegione();

					t.setRegione(current_regione);
				}
				t.setNumVlt(t.getNumVlt() + m.getNumVlt());
				t.setBet(t.getBet() + m.getBet());
				t.setWin(t.getWin() + m.getWin());
				t.setGamesPlayed(t.getGamesPlayed() + m.getGamesPlayed());
				t.setTotalIn(t.getTotalIn() + m.getTotalIn());
				t.setTotalOut(t.getTotalOut() + m.getTotalOut());
				t.setTicketIn(t.getTicketIn() + m.getTicketIn());
				t.setTicketOut(t.getTicketOut() + m.getTicketOut());
				t.setCoinIn(t.getCoinIn() + m.getCoinIn());
				t.setBillIn(t.getBillIn() + m.getBillIn());
				t.setCardIn(t.getCardIn() + m.getCardIn());
				t.setTotalPrepaidIn(t.getTotalPrepaidIn()+ m.getTotalPrepaidIn());
				t.setJackpotWins(t.getJackpotWins() + m.getJackpotWins());
				t.setJackpotContribution(t.getJackpotContribution()+ m.getJackpotContribution());
				t.setPreu(t.getPreu() + m.getPreu());
				t.setAams(t.getAams() + m.getAams());
				t.setNetWin(t.getNetWin() + m.getNetWin());
				t.setHouseWin(t.getHouseWin() + m.getHouseWin());
				t.setSupplierProfit(t.getSupplierProfit()+ m.getSupplierProfit());
				t.setOperatorsProfit(t.getOperatorsProfit()+ m.getOperatorsProfit());
				t.setBplusNetProfit(t.getBplusNetProfit()+ m.getBplusNetProfit());
			}
			totalList.add(t);

			meterList_regioni = totalList;
		}
	}
	
	
	//GIOCO
	private boolean mostraTotale_game = false;
	
	public boolean isMostraTotale_game() {
		return mostraTotale_game;
	}
	
	
	public void mostraSingoliGiorni_game(){
		logger.info("mostraSingoliGiorni_game");
		if(mostraTotale_game){
			mostraTotale_game = false;
			query_game();
		}
	}
	
	public void mostraTotale_game(){
		logger.info("mostraTotale_game");
		if (!mostraTotale_game) {
			mostraTotale_game = true;
			query_game();
		}
	}
	
	public void sommaTotale_game() {

		if (meterList_game.size() != 0) {
			MeterBean t = new MeterBean();
			inizializzaMeterBean(t);

			List<MeterBean> totalList = new ArrayList<MeterBean>();

			String current_vlt_game = meterList_game.get(0).getAams_vlt_id()+"-"+meterList_game.get(0).getCode_game();

			t.setRegione(meterList_game.get(0).getRegione());
			t.setProvincia(meterList_game.get(0).getProvincia());
			t.setComune(meterList_game.get(0).getComune());
			t.setLocation_address(meterList_game.get(0).getLocation_address());
			t.setCod_gestore(meterList_game.get(0).getCod_gestore());
			t.setAams_location_id(meterList_game.get(0).getAams_location_id());
			t.setLocation_name(meterList_game.get(0).getLocation_name());
			t.setAams_vlt_id(meterList_game.get(0).getAams_vlt_id());
			t.setGs_vlt_id(meterList_game.get(0).getGs_vlt_id());
			t.setCode_game(meterList_game.get(0).getCode_game());
			t.setGame_name(meterList_game.get(0).getGame_name());

			for (MeterBean m : meterList_game) {
				if (!current_vlt_game.equals(m.getAams_vlt_id()+"-"+m.getCode_game())) {
					totalList.add(t);

					t = new MeterBean();
					inizializzaMeterBean(t);

					current_vlt_game = m.getAams_vlt_id()+"-"+m.getCode_game();

					t.setRegione(m.getRegione());
					t.setProvincia(m.getProvincia());					
					t.setComune(m.getComune());
					t.setLocation_address(m.getLocation_address());
					t.setCod_gestore(m.getCod_gestore());
					t.setAams_location_id(m.getAams_location_id());
					t.setLocation_name(m.getLocation_name());
					t.setAams_vlt_id(m.getAams_vlt_id());
					t.setGs_vlt_id(m.getGs_vlt_id());
					t.setCode_game(m.getCode_game());
					t.setGame_name(m.getGame_name());
				}
				t.setBet(t.getBet() + m.getBet());
				t.setWin(t.getWin() + m.getWin());
				t.setGamesPlayed(t.getGamesPlayed() + m.getGamesPlayed());
				t.setTotalIn(t.getTotalIn() + m.getTotalIn());
				t.setTotalOut(t.getTotalOut() + m.getTotalOut());
				t.setTicketIn(t.getTicketIn() + m.getTicketIn());
				t.setTicketOut(t.getTicketOut() + m.getTicketOut());
				t.setCoinIn(t.getCoinIn() + m.getCoinIn());
				t.setBillIn(t.getBillIn() + m.getBillIn());
				t.setCardIn(t.getCardIn() + m.getCardIn());
				t.setTotalPrepaidIn(t.getTotalPrepaidIn()+ m.getTotalPrepaidIn());
				t.setJackpotWins(t.getJackpotWins() + m.getJackpotWins());
				t.setJackpotContribution(t.getJackpotContribution()+ m.getJackpotContribution());
				t.setPreu(t.getPreu() + m.getPreu());
				t.setAams(t.getAams() + m.getAams());
				t.setNetWin(t.getNetWin() + m.getNetWin());
				t.setHouseWin(t.getHouseWin() + m.getHouseWin());
				t.setSupplierProfit(t.getSupplierProfit()+ m.getSupplierProfit());
				t.setOperatorsProfit(t.getOperatorsProfit()+ m.getOperatorsProfit());
				t.setBplusNetProfit(t.getBplusNetProfit()+ m.getBplusNetProfit());
			}
			totalList.add(t);

			meterList_game = totalList;
		}
	}
	
	
	
	
	
	////// MOSTRA E NASCONDI SALE NON ANCORA APERTE
	
	//LOCATION
	private boolean nascondiSaleNonAperte_location = false;
	
	 public boolean isNascondiSaleNonAperte_location() {
			return nascondiSaleNonAperte_location;
		}

		public void mostraSaleNonAperte_location(){
			logger.info("mostraSaleNonAperte_location");
			if(nascondiSaleNonAperte_location){
				nascondiSaleNonAperte_location = false;
				query_location();
			}
		}
		
		public void nascondiSaleNonAperte_location(){
			logger.info("nascondiSaleNonAperte_location");
			if (!nascondiSaleNonAperte_location) {
				nascondiSaleNonAperte_location = true;
				query_location();
			}
		}
	 
		//VLT
		private boolean nascondiSaleNonAperte_vlt = false;
		
		 public boolean isNascondiSaleNonAperte_vlt() {
				return nascondiSaleNonAperte_vlt;
			}

			public void mostraSaleNonAperte_vlt(){
				logger.info("mostraSaleNonAperte_vlt");
				if(nascondiSaleNonAperte_vlt){
					nascondiSaleNonAperte_vlt = false;
					query_vlt();
				}
			}
			
			public void nascondiSaleNonAperte_vlt(){
				logger.info("nascondiSaleNonAperte_vlt");
				if (!nascondiSaleNonAperte_vlt) {
					nascondiSaleNonAperte_vlt = true;
					query_vlt();
				}
			}
		
		
		//REGIONI
			private boolean escludiSaleNonAperte_regioni = false;
			
			 public boolean isEscludiSaleNonAperte_regioni() {
					return escludiSaleNonAperte_regioni;
				}

				public void includiSaleNonAperte_regioni(){
					logger.info("includiSaleNonAperte_regioni");
					if(escludiSaleNonAperte_regioni){
						escludiSaleNonAperte_regioni = false;
						query_regioni();
					}
				}
				
				public void escludiSaleNonAperte_regioni(){
					logger.info("escludiSaleNonAperte_regioni");
					if (!escludiSaleNonAperte_regioni) {
						escludiSaleNonAperte_regioni = true;
						query_regioni();
					}
				}
			
				
			//GIOCHI
				private boolean nascondiSaleNonAperte_game = false;
				
				 public boolean isNascondiSaleNonAperte_game() {
						return nascondiSaleNonAperte_game;
					}

					public void mostraSaleNonAperte_game(){
						logger.info("mostraSaleNonAperte_game");
						if(nascondiSaleNonAperte_game){
							nascondiSaleNonAperte_game = false;
							query_game();
						}
					}
					
					public void nascondiSaleNonAperte_game(){
						logger.info("nascondiSaleNonAperte_game");
						if (!nascondiSaleNonAperte_game) {
							nascondiSaleNonAperte_game = true;
							query_game();
						}
					}
				
				

	public void nulla(){}
	
	public void reset(){
		location_name = "";
		aams_location_id = "";
		aams_vlt_id = "";
		regione = "";
		provincia = "";
		comune = "";
		cod_gestore = "";
	}
				
	
	
	//GRAFICI
	
	

	private CartesianChartModel cartesianModel;  
    
    private PieChartModel pieModel;  
  
    public CartesianChartModel getCartesianModel() {  
    	if (cartesianModel==null)
    		createCartesianModel();  
        return cartesianModel;  
    }
    
    public CartesianChartModel getNewCartesianModel() {  
    	createCartesianModel();
        return cartesianModel;  
    }
  
    public PieChartModel getPieModel() {  
    	if (pieModel==null)
    		createPieModel();  
        return pieModel;  
    } 
    
    public PieChartModel getNewPieModel(){
    	createPieModel();
    	return pieModel;
    }
  
    private void createCartesianModel() {  
        cartesianModel = new CartesianChartModel();  
  
        ChartSeries locBet = new ChartSeries();  
        ChartSeries locNw = new ChartSeries();  
        
        for (MeterBean mb : meterList_location){
        	String data = mb.getData().toString().substring(0, 10);
        	
        	locBet.setLabel("Bet");  
        	locBet.set(data, mb.getBet());
        	
        	locNw.setLabel("NetWin");  
        	locNw.set(data, mb.getNetWin());
        } 
        
        cartesianModel.addSeries(locBet);  
        cartesianModel.addSeries(locNw);  
    }  
  
    private void createPieModel() {  
        pieModel = new PieChartModel();  
        
        meterList_location = query_location();
        
        
        for (MeterBean mb : meterList_location){
        	pieModel.set(mb.getLocation_name(), mb.getWin());
        }
        
        
    }  
	
	
}








