package it.bplus.model;

// Generated 31-mar-2011 10.51.22 by Hibernate Tools 3.4.0.CR1

import java.math.BigDecimal;

/**
 * Meterfact generated by hbm2java
 */
public class Meterfact implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BigDecimal id;
	private Sistemagiocodim sistemagiocodim;
	private Tempodim tempodim;
	private Clientidim clientidim;
	private Spaziodim spaziodim;
	private BigDecimal bet;
	private BigDecimal win;
	private Long gamesPlayed;
	private BigDecimal totalIn;
	private BigDecimal totalOut;
	private BigDecimal ticketIn;
	private BigDecimal ticketOut;
	private BigDecimal coinIn;
	private BigDecimal billIn;
	private BigDecimal cardIn;
	private BigDecimal totalPrepaidIn;
	private BigDecimal jackpotWins;
	private BigDecimal jackpotContribution;
	private BigDecimal preu;
	private BigDecimal aams;
	private BigDecimal netWin;
	private BigDecimal houseWin;
	private BigDecimal supplierProfit;
	private BigDecimal operatorsProfit;
	private BigDecimal bplusNetProfit;
	private String stato;
	private BigDecimal totalDrop;
	private Long aamsJackpotId;

	public Meterfact() {
		super();
	}

	public Meterfact(BigDecimal id, Sistemagiocodim sistemagiocodim,
			Tempodim tempodim, Clientidim clientidim, Spaziodim spaziodim) {
		this.id = id;
		this.sistemagiocodim = sistemagiocodim;
		this.tempodim = tempodim;
		this.clientidim = clientidim;
		this.spaziodim = spaziodim;
	}

	public Meterfact(BigDecimal id, Sistemagiocodim sistemagiocodim,
			Tempodim tempodim, Clientidim clientidim, Spaziodim spaziodim,
			BigDecimal bet, BigDecimal win, Long gamesPlayed,
			BigDecimal totalIn, BigDecimal totalOut, BigDecimal ticketIn,
			BigDecimal ticketOut, BigDecimal coinIn, BigDecimal billIn,
			BigDecimal cardIn, BigDecimal totalPrepaidIn,
			BigDecimal jackpotWins, BigDecimal jackpotContribution,
			BigDecimal preu, BigDecimal aams, BigDecimal netWin,
			BigDecimal houseWin, BigDecimal supplierProfit,
			BigDecimal operatorsProfit, BigDecimal bplusNetProfit,
			String stato, BigDecimal totalDrop, Long aamsJackpotId, String error_vlt) {
		this.id = id;
		this.sistemagiocodim = sistemagiocodim;
		this.tempodim = tempodim;
		this.clientidim = clientidim;
		this.spaziodim = spaziodim;
		this.bet = bet;
		this.win = win;
		this.gamesPlayed = gamesPlayed;
		this.totalIn = totalIn;
		this.totalOut = totalOut;
		this.ticketIn = ticketIn;
		this.ticketOut = ticketOut;
		this.coinIn = coinIn;
		this.billIn = billIn;
		this.cardIn = cardIn;
		this.totalPrepaidIn = totalPrepaidIn;
		this.jackpotWins = jackpotWins;
		this.jackpotContribution = jackpotContribution;
		this.preu = preu;
		this.aams = aams;
		this.netWin = netWin;
		this.houseWin = houseWin;
		this.supplierProfit = supplierProfit;
		this.operatorsProfit = operatorsProfit;
		this.bplusNetProfit = bplusNetProfit;
		this.stato = stato;
		this.totalDrop = totalDrop;
		this.aamsJackpotId = aamsJackpotId;
	}

	public BigDecimal getId() {
		return this.id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public Sistemagiocodim getSistemagiocodim() {
		return this.sistemagiocodim;
	}

	public void setSistemagiocodim(Sistemagiocodim sistemagiocodim) {
		this.sistemagiocodim = sistemagiocodim;
	}

	public Tempodim getTempodim() {
		return this.tempodim;
	}

	public void setTempodim(Tempodim tempodim) {
		this.tempodim = tempodim;
	}

	public Clientidim getClientidim() {
		return this.clientidim;
	}

	public void setClientidim(Clientidim clientidim) {
		this.clientidim = clientidim;
	}

	public Spaziodim getSpaziodim() {
		return this.spaziodim;
	}

	public void setSpaziodim(Spaziodim spaziodim) {
		this.spaziodim = spaziodim;
	}

	public BigDecimal getBet() {
		return this.bet;
	}

	public void setBet(BigDecimal bet) {
		this.bet = bet;
	}

	public BigDecimal getWin() {
		return this.win;
	}

	public void setWin(BigDecimal win) {
		this.win = win;
	}

	public Long getGamesPlayed() {
		return this.gamesPlayed;
	}

	public void setGamesPlayed(Long gamesPlayed) {
		this.gamesPlayed = gamesPlayed;
	}

	public BigDecimal getTotalIn() {
		return this.totalIn;
	}

	public void setTotalIn(BigDecimal totalIn) {
		this.totalIn = totalIn;
	}

	public BigDecimal getTotalOut() {
		return this.totalOut;
	}

	public void setTotalOut(BigDecimal totalOut) {
		this.totalOut = totalOut;
	}

	public BigDecimal getTicketIn() {
		return this.ticketIn;
	}

	public void setTicketIn(BigDecimal ticketIn) {
		this.ticketIn = ticketIn;
	}

	public BigDecimal getTicketOut() {
		return this.ticketOut;
	}

	public void setTicketOut(BigDecimal ticketOut) {
		this.ticketOut = ticketOut;
	}

	public BigDecimal getCoinIn() {
		return this.coinIn;
	}

	public void setCoinIn(BigDecimal coinIn) {
		this.coinIn = coinIn;
	}

	public BigDecimal getBillIn() {
		return this.billIn;
	}

	public void setBillIn(BigDecimal billIn) {
		this.billIn = billIn;
	}

	public BigDecimal getCardIn() {
		return this.cardIn;
	}

	public void setCardIn(BigDecimal cardIn) {
		this.cardIn = cardIn;
	}

	public BigDecimal getTotalPrepaidIn() {
		return this.totalPrepaidIn;
	}

	public void setTotalPrepaidIn(BigDecimal totalPrepaidIn) {
		this.totalPrepaidIn = totalPrepaidIn;
	}

	public BigDecimal getJackpotWins() {
		return this.jackpotWins;
	}

	public void setJackpotWins(BigDecimal jackpotWins) {
		this.jackpotWins = jackpotWins;
	}

	public BigDecimal getJackpotContribution() {
		return this.jackpotContribution;
	}

	public void setJackpotContribution(BigDecimal jackpotContribution) {
		this.jackpotContribution = jackpotContribution;
	}

	public BigDecimal getPreu() {
		return this.preu;
	}

	public void setPreu(BigDecimal preu) {
		this.preu = preu;
	}

	public BigDecimal getAams() {
		return this.aams;
	}

	public void setAams(BigDecimal aams) {
		this.aams = aams;
	}

	public BigDecimal getNetWin() {
		return this.netWin;
	}

	public void setNetWin(BigDecimal netWin) {
		this.netWin = netWin;
	}

	public BigDecimal getHouseWin() {
		return this.houseWin;
	}

	public void setHouseWin(BigDecimal houseWin) {
		this.houseWin = houseWin;
	}

	public BigDecimal getSupplierProfit() {
		return this.supplierProfit;
	}

	public void setSupplierProfit(BigDecimal supplierProfit) {
		this.supplierProfit = supplierProfit;
	}

	public BigDecimal getOperatorsProfit() {
		return this.operatorsProfit;
	}

	public void setOperatorsProfit(BigDecimal operatorsProfit) {
		this.operatorsProfit = operatorsProfit;
	}

	public BigDecimal getBplusNetProfit() {
		return this.bplusNetProfit;
	}

	public void setBplusNetProfit(BigDecimal bplusNetProfit) {
		this.bplusNetProfit = bplusNetProfit;
	}

	public String getStato() {
		return this.stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}

	public BigDecimal getTotalDrop() {
		return this.totalDrop;
	}

	public void setTotalDrop(BigDecimal totalDrop) {
		this.totalDrop = totalDrop;
	}

	public Long getAamsJackpotId() {
		return this.aamsJackpotId;
	}

	public void setAamsJackpotId(Long aamsJackpotId) {
		this.aamsJackpotId = aamsJackpotId;
	}

}
