package it.betplus.awg.db.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class LocationDataDTO implements Serializable {
	private static final long serialVersionUID = -2830761566900978623L;
	
	private Date datafecham;
	private String aamslocationname;
	private String aamslocationid;
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
	private ArrayList<VltDTO> vltList;
	private Double allbet;
	private Double allwin;
	private Double allgamesplayed;
	private Double alltotalin;
	private Double alltotalout;
	private Double allticketin;
	private Double allticketout;
	private Double allcoinin;
	private Double allbillin;
	private Double allcardin;
	private Double alltotalprepaidin;
	private Double alljackpotwins;
	private Double alljackpotcontribution;
	private String aamsaddress;
	private String aamsprovince;
	private String aamscity;
	private String manufacturename;

	private String LocationStatus;
	private String VltStatus;
	
	
	public LocationDataDTO() {
		
	}
	
	public LocationDataDTO(String aamslocationname, String aamslocationid, Double bet, Double win, Double gamesplayed
			, Double totalin, Double totalout, Double ticketin, Double ticketout, Double coinin, Double billin 
			, Double cardin, Double totalprepaidin, Double jackpotwins, Double jackpotcontribution
			, String aamsaddress, String aamsprovince, String aamscity, String manufacturename) 
	{
		this(bet, win, gamesplayed
				, totalin, totalout, ticketin, ticketout, coinin, billin 
				, cardin, totalprepaidin, jackpotwins, jackpotcontribution
				, aamsaddress, aamsprovince, aamscity, manufacturename);
		this.aamslocationname = aamslocationname;
		this.aamslocationid = aamslocationid;
	}
	
	public LocationDataDTO(String aamslocationname, String aamslocationid, Date datafecham, Double bet, Double win, Double gamesplayed
			, Double totalin, Double totalout, Double ticketin, Double ticketout, Double coinin, Double billin 
			, Double cardin, Double totalprepaidin, Double jackpotwins, Double jackpotcontribution
			, String aamsaddress, String aamsprovince, String aamscity, String manufacturename) 
	{
		this(bet, win, gamesplayed
				, totalin, totalout, ticketin, ticketout, coinin, billin 
				, cardin, totalprepaidin, jackpotwins, jackpotcontribution
				, aamsaddress, aamsprovince, aamscity, manufacturename);
		this.aamslocationname = aamslocationname;
		this.aamslocationid = aamslocationid;
		this.datafecham = datafecham;
	}
	
	public LocationDataDTO(Double bet, Double win, Double gamesplayed
			, Double totalin, Double totalout, Double ticketin, Double ticketout, Double coinin, Double billin 
			, Double cardin, Double totalprepaidin, Double jackpotwins, Double jackpotcontribution
			, String aamsaddress, String aamsprovince, String aamscity, String manufacturename) {
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
		this.aamsaddress = aamsaddress;
		this.aamsprovince = aamsprovince;
		this.aamscity = aamscity;
		this.manufacturename = manufacturename;
	}
	
	public LocationDataDTO(Date datafecham, Double bet, Double win, Double gamesplayed
			, Double totalin, Double totalout, Double ticketin, Double ticketout, Double coinin, Double billin 
			, Double cardin, Double totalprepaidin, Double jackpotwins, Double jackpotcontribution
			, String aamsaddress, String aamsprovince, String aamscity, String manufacturename) {
		this(bet, win, gamesplayed
				, totalin, totalout, ticketin, ticketout, coinin, billin 
				, cardin, totalprepaidin, jackpotwins, jackpotcontribution
				, aamsaddress, aamsprovince, aamscity, manufacturename);
		this.datafecham = datafecham;
	}
	
	public LocationDataDTO(String aamslocationname, String aamslocationid, ArrayList<VltDTO> vltList) {
		this.aamslocationname = aamslocationname;
		this.aamslocationid = aamslocationid;
		this.vltList = vltList;
		this.allbet = 0d;
		this.allwin = 0d;
		this.allgamesplayed = 0d;
		this.alltotalin = 0d;
		this.alltotalout = 0d;
		this.allticketin = 0d;
		this.allticketout = 0d;
		this.allcoinin = 0d;
		this.allbillin = 0d;
		this.allcardin = 0d;
		this.alltotalprepaidin = 0d;
		this.alljackpotwins = 0d;
		this.alljackpotcontribution = 0d;
	}

	public String getAamslocationname() {
		return aamslocationname;
	}

	public void setAamslocationname(String aamslocationname) {
		this.aamslocationname = aamslocationname;
	}

	public String getAamslocationid() {
		return aamslocationid;
	}

	public void setAamslocationid(String aamslocationid) {
		this.aamslocationid = aamslocationid;
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

	public ArrayList<VltDTO> getVltList() {
		return vltList;
	}

	public void setVltList(ArrayList<VltDTO> vltList) {
		this.vltList = vltList;
	}

	public Double getAllbet() {
		if (this.vltList != null && !this.vltList.isEmpty())
			for (VltDTO vltDTO : vltList) 
				allbet += vltDTO.getBet();
		return allbet;
	}

	public void setAllbet(Double allbet) {
		this.allbet = allbet;
	}

	public Double getAllwin() {
		if (this.vltList != null && !this.vltList.isEmpty())
			for (VltDTO vltDTO : vltList)
				allwin += vltDTO.getWin();
		return allwin;
	}

	public void setAllwin(Double allwin) {
		this.allwin = allwin;
	}

	public Double getAllgamesplayed() {
		if (this.vltList != null && !this.vltList.isEmpty())
			for (VltDTO vltDTO : vltList)
				allgamesplayed += vltDTO.getGamesplayed();
		return allgamesplayed;
	}

	public void setAllgamesplayed(Double allgamesplayed) {
		this.allgamesplayed = allgamesplayed;
	}

	public Double getAlltotalin() {
		if (this.vltList != null && !this.vltList.isEmpty())
			for (VltDTO vltDTO : vltList)
				alltotalin += vltDTO.getTotalin();
		return alltotalin;
	}

	public void setAlltotalin(Double alltotalin) {
		this.alltotalin = alltotalin;
	}

	public Double getAlltotalout() {
		if (this.vltList != null && !this.vltList.isEmpty())
			for (VltDTO vltDTO : vltList)
				alltotalout += vltDTO.getTotalout();
		return alltotalout;
	}

	public void setAlltotalout(Double alltotalout) {
		this.alltotalout = alltotalout;
	}

	public Double getAllticketin() {
		if (this.vltList != null && !this.vltList.isEmpty())
			for (VltDTO vltDTO : vltList)
				allticketin += vltDTO.getTicketin();
		return allticketin;
	}

	public void setAllticketin(Double allticketin) {
		this.allticketin = allticketin;
	}

	public Double getAllticketout() {
		if (this.vltList != null && !this.vltList.isEmpty())
			for (VltDTO vltDTO : vltList)
				allticketout += vltDTO.getTicketout();
		return allticketout;
	}

	public void setAllticketout(Double allticketout) {
		this.allticketout = allticketout;
	}

	public Double getAllcoinin() {
		if (this.vltList != null && !this.vltList.isEmpty())
			for (VltDTO vltDTO : vltList)
				allcoinin += vltDTO.getCoinin();
		return allcoinin;
	}

	public void setAllcoinin(Double allcoinin) {
		this.allcoinin = allcoinin;
	}

	public Double getAllbillin() {
		if (this.vltList != null && !this.vltList.isEmpty())
			for (VltDTO vltDTO : vltList)
				allbillin += vltDTO.getBillin();
		return allbillin;
	}

	public void setAllbillin(Double allbillin) {
		this.allbillin = allbillin;
	}

	public Double getAllcardin() {
		if (this.vltList != null && !this.vltList.isEmpty())
			for (VltDTO vltDTO : vltList)
				allcardin += vltDTO.getCardin();
		return allcardin;
	}

	public void setAllcardin(Double allcardin) {
		this.allcardin = allcardin;
	}

	public Double getAlltotalprepaidin() {
		if (this.vltList != null && !this.vltList.isEmpty())
			for (VltDTO vltDTO : vltList)
				alltotalprepaidin += vltDTO.getTotalprepaidin();
		return alltotalprepaidin;
	}

	public void setAlltotalprepaidin(Double alltotalprepaidin) {
		this.alltotalprepaidin = alltotalprepaidin;
	}

	public Double getAlljackpotwins() {
		if (this.vltList != null && !this.vltList.isEmpty())
			for (VltDTO vltDTO : vltList)
				alljackpotwins += vltDTO.getJackpotwins();
		return alljackpotwins;
	}

	public void setAlljackpotwins(Double alljackpotwins) {
		this.alljackpotwins = alljackpotwins;
	}

	public Double getAlljackpotcontribution() {
		if (this.vltList != null && !this.vltList.isEmpty())
			for (VltDTO vltDTO : vltList)
				alljackpotcontribution += vltDTO.getJackpotcontribution();
		return alljackpotcontribution;
	}

	public void setAlljackpotcontribution(Double alljackpotcontribution) {
		this.alljackpotcontribution = alljackpotcontribution;
	}

	public Date getDatafecham() {
		return datafecham;
	}

	public void setDatafecham(Date datafecham) {
		this.datafecham = datafecham;
	}

	public String getAamsaddress() {
		return aamsaddress;
	}

	public void setAamsaddress(String aamsaddress) {
		this.aamsaddress = aamsaddress;
	}

	public String getAamsprovince() {
		return aamsprovince;
	}

	public void setAamsprovince(String aamsprovince) {
		this.aamsprovince = aamsprovince;
	}

	public String getAamscity() {
		return aamscity;
	}

	public void setAamscity(String aamscity) {
		this.aamscity = aamscity;
	}

	public String getManufacturename() {
		return manufacturename;
	}

	public void setManufacturename(String manufacturename) {
		this.manufacturename = manufacturename;
	}

	public String getLocationStatus() {
		return LocationStatus;
	}

	public void setLocationStatus(String locationStatus) {
		LocationStatus = locationStatus;
	}

	public String getVltStatus() {
		return VltStatus;
	}

	public void setVltStatus(String vltStatus) {
		VltStatus = vltStatus;
	}

	
}
