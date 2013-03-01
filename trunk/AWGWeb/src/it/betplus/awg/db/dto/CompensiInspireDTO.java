package it.betplus.awg.db.dto;

import java.io.Serializable;

public class CompensiInspireDTO implements Serializable {
	private static final long serialVersionUID = 1392364995936780404L;
	
	private String aamsvltid;
	private String manufacturername;
	private String cabinet;
	private Double bet;
	private Double win;
	private Double jackpotcontribution;
	private Double jackpotwins;
	private Double netwin;
	private Double totalhousewin;
	private Double feeperc;
	private Double ricavoinspired;
	
	
	public CompensiInspireDTO() {
		
	}
	
	public CompensiInspireDTO(String aamsvltid, String manufacturername, String cabinet, Double bet, Double win, Double jackpotcontribution, Double jackpotwins, Double netwin, Double totalhousewin, Double feeperc, Double ricavoinspired) {
		
		this.aamsvltid = aamsvltid;
		this.cabinet = cabinet;
		this.manufacturername = manufacturername;
		this.bet = bet;
		this.win = win;
		this.jackpotcontribution = jackpotcontribution;
		this.jackpotwins = jackpotwins;
		this.netwin = netwin;
		this.totalhousewin = totalhousewin;
		this.feeperc = feeperc;
		this.ricavoinspired = ricavoinspired;
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

	public Double getJackpotcontribution() {
		return jackpotcontribution;
	}

	public void setJackpotcontribution(Double jackpotcontribution) {
		this.jackpotcontribution = jackpotcontribution;
	}

	public Double getNetwin() {
		return netwin;
	}

	public void setNetwin(Double netwin) {
		this.netwin = netwin;
	}

	public Double getTotalhousewin() {
		return totalhousewin;
	}

	public void setTotalhousewin(Double totalhousewin) {
		this.totalhousewin = totalhousewin;
	}

	public Double getFeeperc() {
		return feeperc;
	}

	public void setFeeperc(Double feeperc) {
		this.feeperc = feeperc;
	}

	public Double getRicavoinspired() {
		return ricavoinspired;
	}

	public void setRicavoinspired(Double ricavoinspired) {
		this.ricavoinspired = ricavoinspired;
	}

	public String getManufacturername() {
		return manufacturername;
	}

	public void setManufacturername(String manufacturername) {
		this.manufacturername = manufacturername;
	}

	public Double getJackpotwins() {
		return jackpotwins;
	}

	public void setJackpotwins(Double jackpotwins) {
		this.jackpotwins = jackpotwins;
	}

	
}
