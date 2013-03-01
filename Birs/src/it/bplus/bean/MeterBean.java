package it.bplus.bean;

import java.io.Serializable;
import java.util.Date;


public class MeterBean extends BasicBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String regione;
	private String provincia;
	private String comune;
	private String location_address;
	private String aams_vlt_id;
	private String gs_vlt_id;
	private String gamesystem_id;
	private String gamesystem_name;
	private Long code_game;
	private String game_name;
	private String location_name;
	private String mese;
	private Date data;
	private String cod_gestore;
	private String aams_location_id;
	private Double bet;
	private Double win;
	private Long gamesPlayed;
	private Double totalIn;
	private Double totalOut;
	private Double ticketIn;
	private Double ticketOut;
	private Double coinIn;
	private Double billIn;
	private Double cardIn;
	private Double totalPrepaidIn;
	private Double jackpotWins;
	private Double jackpotContribution;
	private Double preu;
	private Double aams;
	private Double netWin;
	private Double houseWin;
	private Double supplierProfit;
	private Double operatorsProfit;
	private Double bplusNetProfit;
	private String stato;
	private String note;
	private int numVlt;
	private String periodo;
	private int giorniGioco;
	
	public MeterBean(){
		super();
	}	

	public Double getBet() {
		return bet;
	}
	public void setBet(Double bet) {
		this.bet = bet;
	}
	public Double getWin() {
		return win;
	}
	public void setWin(Double win) {
		this.win = win;
	}
	public Long getGamesPlayed() {
		return gamesPlayed;
	}
	public void setGamesPlayed(Long gamesPlayed) {
		this.gamesPlayed = gamesPlayed;
	}
	public Double getTotalIn() {
		return totalIn;
	}
	public void setTotalIn(Double totalIn) {
		this.totalIn = totalIn;
	}
	public Double getTotalOut() {
		return totalOut;
	}
	public void setTotalOut(Double totalOut) {
		this.totalOut = totalOut;
	}
	public Double getTicketIn() {
		return ticketIn;
	}
	public void setTicketIn(Double ticketIn) {
		this.ticketIn = ticketIn;
	}
	public Double getTicketOut() {
		return ticketOut;
	}
	public void setTicketOut(Double ticketOut) {
		this.ticketOut = ticketOut;
	}
	public Double getCoinIn() {
		return coinIn;
	}
	public void setCoinIn(Double coinIn) {
		this.coinIn = coinIn;
	}
	public Double getBillIn() {
		return billIn;
	}
	public void setBillIn(Double billIn) {
		this.billIn = billIn;
	}
	public Double getCardIn() {
		return cardIn;
	}
	public void setCardIn(Double cardIn) {
		this.cardIn = cardIn;
	}
	public Double getTotalPrepaidIn() {
		return totalPrepaidIn;
	}
	public void setTotalPrepaidIn(Double totalPrepaidIn) {
		this.totalPrepaidIn = totalPrepaidIn;
	}
	public Double getJackpotWins() {
		return jackpotWins;
	}
	public void setJackpotWins(Double jackpotWins) {
		this.jackpotWins = jackpotWins;
	}
	public Double getJackpotContribution() {
		return jackpotContribution;
	}
	public void setJackpotContribution(Double jackpotContribution) {
		this.jackpotContribution = jackpotContribution;
	}
	public Double getPreu() {
		return preu;
	}
	public void setPreu(Double preu) {
		this.preu = preu;
	}
	public Double getAams() {
		return aams;
	}
	public void setAams(Double aams) {
		this.aams = aams;
	}
	public Double getNetWin() {
		return netWin;
	}
	public void setNetWin(Double netWin) {
		this.netWin = netWin;
	}
	public Double getHouseWin() {
		return houseWin;
	}
	public void setHouseWin(Double houseWin) {
		this.houseWin = houseWin;
	}
	public Double getSupplierProfit() {
		return supplierProfit;
	}
	public void setSupplierProfit(Double supplierProfit) {
		this.supplierProfit = supplierProfit;
	}
	public Double getOperatorsProfit() {
		return operatorsProfit;
	}
	public void setOperatorsProfit(Double operatorsProfit) {
		this.operatorsProfit = operatorsProfit;
	}
	public Double getBplusNetProfit() {
		return bplusNetProfit;
	}
	public void setBplusNetProfit(Double bplusNetProfit) {
		this.bplusNetProfit = bplusNetProfit;
	}
	public String getStato() {
		return stato;
	}
	public void setStato(String stato) {
		this.stato = stato;
	}





	public String getMese() {
		return mese;
	}


	public void setMese(String mese) {
		this.mese = mese;
	}


	public Date getData() {
		return data;
	}


	public void setData(Date data) {
		this.data = data;
	}


	public String getCod_gestore() {
		return cod_gestore;
	}


	public void setCod_gestore(String cod_gestore) {
		this.cod_gestore = cod_gestore;
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


	public void setComune(String comune) {
		this.comune = comune;
	}


	public Long getCode_game() {
		return code_game;
	}


	public void setCode_game(Long code_game) {
		this.code_game = code_game;
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
	
	public String getLocation_address() {
		return location_address;
	}


	public void setLocation_address(String location_address) {
		this.location_address = location_address;
	}


	public void setNote(String note) {
		this.note = note;
	}


	public String getNote() {
		return note;
	}

	public void setGame_name(String game_name) {
		this.game_name = game_name;
	}

	public String getGame_name() {
		return game_name;
	}

	public void setGs_vlt_id(String gs_vlt_id) {
		this.gs_vlt_id = gs_vlt_id;
	}

	public String getGs_vlt_id() {
		return gs_vlt_id;
	}

	public void setNumVlt(int numVlt) {
		this.numVlt = numVlt;
	}

	public int getNumVlt() {
		return numVlt;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setGiorniGioco(int giorniGioco) {
		this.giorniGioco = giorniGioco;
	}

	public int getGiorniGioco() {
		return giorniGioco;
	}

	public void setGamesystem_id(String gamesystem_id) {
		this.gamesystem_id = gamesystem_id;
	}

	public String getGamesystem_id() {
		return gamesystem_id;
	}

	public void setGamesystem_name(String gamesystem_name) {
		this.gamesystem_name = gamesystem_name;
	}

	public String getGamesystem_name() {
		return gamesystem_name;
	}
}
