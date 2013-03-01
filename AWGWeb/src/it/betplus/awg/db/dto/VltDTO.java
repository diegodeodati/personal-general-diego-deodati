package it.betplus.awg.db.dto;

import java.io.Serializable;
import java.util.Date;

public class VltDTO implements Serializable {
	
	private static final long serialVersionUID = 9054563289845505827L;
	
	private String manufacturername;
	private String aamsvltid;
	private String cabinet;
	private Double feePerc;
	private String aamslocationid;
	private String aamslocationname;
	private String aamscity;
	private String aamsprovince;
	private Double bet;
	private Double win;
	private Double gamesplayed;
	private Double totalin;
	private Double totalout;
	private Double ticketin;
	private Double ticketout;
	private Double coinin;
	private Double billin;
	private Double cardin;
	private Double totalprepaidin;
	private Double jackpotwins;
	private Double jackpotcontribution;
	private Date datafecham;
	
	
	private String jackpotInfo;
	private long jackpotId;
	private String gsJackpotId;
	
	
	
	public VltDTO() {
		
	}
	
	public VltDTO(String manufacturername, String aamsvltid, String cabinet, Double feePerc,
			String aamslocationid, String aamslocationname, String aamscity) {
		this.manufacturername = manufacturername;
		this.aamsvltid = aamsvltid;
		this.cabinet = cabinet;
		this.feePerc = feePerc;
		this.aamslocationid = aamslocationid;
		this.aamslocationname = aamslocationname;
		this.aamscity = aamscity;
	}
	
	public VltDTO(String manufacturername, String aamsvltid, String aamslocationid, String aamslocationname, 
			String aamscity, String aamsprovince, Double jackpotwins, Date datafecham) {
		this.manufacturername = manufacturername;
		this.aamsvltid = aamsvltid;
		this.aamslocationid = aamslocationid;
		this.aamslocationname = aamslocationname;
		this.aamscity = aamscity;
		this.aamsprovince = aamsprovince;
		this.jackpotwins = jackpotwins;
		this.datafecham = datafecham;
	}
	
	public VltDTO(String manufacturername, String aamsvltid, String aamslocationname, String aamslocationid, Double bet, Double win, Double gamesplayed
			, Double totalin, Double totalout, Double ticketin, Double ticketout, Double coinin, Double billin 
			, Double cardin, Double totalprepaidin, Double jackpotwins, Double jackpotcontribution) {
		this.manufacturername = manufacturername;
		this.aamslocationname = aamslocationname;
		this.aamslocationid = aamslocationid;
		this.aamsvltid = aamsvltid;
		this.bet = bet;
		this.win = win;
		this.gamesplayed = gamesplayed;
		this.totalin = totalin;
		this.totalout = totalout;
		this.ticketin = ticketin;
		this.ticketout = ticketout;
		this.coinin = coinin;
		this.billin = billin;
		this.cardin = cardin;
		this.totalprepaidin = totalprepaidin;
		this.jackpotwins = jackpotwins;
		this.jackpotcontribution = jackpotcontribution;
	}

	public String getAamsvltid() {
		return aamsvltid;
	}

	public void setAamsvltid(String aamsvltid) {
		this.aamsvltid = aamsvltid;
	}

	public String getCabinet() {
		return cabinet;
	}

	public void setCabinet(String cabinet) {
		this.cabinet = cabinet;
	}

	public Double getFeePerc() {
		return feePerc;
	}

	public void setFeePerc(Double feePerc) {
		this.feePerc = feePerc;
	}

	public String getAamslocationid() {
		return aamslocationid;
	}

	public void setAamslocationid(String aamslocationid) {
		this.aamslocationid = aamslocationid;
	}

	public String getAamslocationname() {
		return aamslocationname;
	}

	public void setAamslocationname(String aamslocationname) {
		this.aamslocationname = aamslocationname;
	}

	public String getAamscity() {
		return aamscity;
	}

	public void setAamscity(String aamscity) {
		this.aamscity = aamscity;
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

	public Double getGamesplayed() {
		return gamesplayed;
	}

	public void setGamesplayed(Double gamesplayed) {
		this.gamesplayed = gamesplayed;
	}

	public Double getTotalin() {
		return totalin;
	}

	public void setTotalin(Double totalin) {
		this.totalin = totalin;
	}

	public Double getTotalout() {
		return totalout;
	}

	public void setTotalout(Double totalout) {
		this.totalout = totalout;
	}

	public Double getTicketin() {
		return ticketin;
	}

	public void setTicketin(Double ticketin) {
		this.ticketin = ticketin;
	}

	public Double getTicketout() {
		return ticketout;
	}

	public void setTicketout(Double ticketout) {
		this.ticketout = ticketout;
	}

	public Double getCoinin() {
		return coinin;
	}

	public void setCoinin(Double coinin) {
		this.coinin = coinin;
	}

	public Double getBillin() {
		return billin;
	}

	public void setBillin(Double billin) {
		this.billin = billin;
	}

	public Double getCardin() {
		return cardin;
	}

	public void setCardin(Double cardin) {
		this.cardin = cardin;
	}

	public Double getTotalprepaidin() {
		return totalprepaidin;
	}

	public void setTotalprepaidin(Double totalprepaidin) {
		this.totalprepaidin = totalprepaidin;
	}

	public Double getJackpotwins() {
		return jackpotwins;
	}

	public void setJackpotwins(Double jackpotwins) {
		this.jackpotwins = jackpotwins;
	}

	public Double getJackpotcontribution() {
		return jackpotcontribution;
	}

	public void setJackpotcontribution(Double jackpotcontribution) {
		this.jackpotcontribution = jackpotcontribution;
	}

	public String getManufacturername() {
		return manufacturername;
	}

	public void setManufacturername(String manufacturername) {
		this.manufacturername = manufacturername;
	}

	public Date getDatafecham() {
		return datafecham;
	}

	public void setDatafecham(Date datafecham) {
		this.datafecham = datafecham;
	}

	public String getAamsprovince() {
		return aamsprovince;
	}

	public void setAamsprovince(String aamsprovince) {
		this.aamsprovince = aamsprovince;
	}

	public String getJackpotInfo() {
		return jackpotInfo;
	}

	public void setJackpotInfo(String jackpotInfo) {
		this.jackpotInfo = jackpotInfo;
	}

	public long getJackpotId() {
		return jackpotId;
	}

	public void setJackpotId(long jackpotId) {
		this.jackpotId = jackpotId;
	}

	public String getGsJackpotId() {
		return gsJackpotId;
	}

	public void setGsJackpotId(String gsJackpotId) {
		this.gsJackpotId = gsJackpotId;
	}


}
