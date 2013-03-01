package serviseatl.graphicaldaily;

import java.io.Serializable;
import java.util.Date;

public class GraphicalDaily  implements Serializable {

    static final long serialVersionUID = 109234314401077702L;
    
    private Date FechaM;
    private double MeterBill;
    private double WinLoose;
    private double TheoricalP;
    private double HoldPerc;
    private double DiffPercent;
    private double MeterIn;
    private int MeterGames;
    
    public GraphicalDaily() {
    	
    }

    public GraphicalDaily(Date FechaM, double MeterBill , double WinLoose, double TheoricalP, double HoldPerc, double DiffPercent, double MeterIn, int MeterGames)    
    {
		this.FechaM = FechaM;
		this.MeterBill = MeterBill;
		this.WinLoose = WinLoose;
		this.TheoricalP = TheoricalP;
		this.HoldPerc = HoldPerc;
		this.DiffPercent = DiffPercent;
		this.MeterIn = MeterIn;
		this.MeterGames = MeterGames;		
	}

	public void setFechaM(Date fechaM) {
		FechaM = fechaM;
	}

	public Date getFechaM() {
		return FechaM;
	}

	public void setMeterBill(double meterBill) {
		MeterBill = meterBill;
	}

	public double getMeterBill() {
		return MeterBill;
	}

	public void setWinLoose(double winLoose) {
		WinLoose = winLoose;
	}

	public double getWinLoose() {
		return WinLoose;
	}

	public void setTheoricalP(double theoricalP) {
		TheoricalP = theoricalP;
	}

	public double getTheoricalP() {
		return TheoricalP;
	}

	public void setHoldPerc(double holdPerc) {
		HoldPerc = holdPerc;
	}

	public double getHoldPerc() {
		return HoldPerc;
	}

	public void setDiffPercent(double diffPercent) {
		DiffPercent = diffPercent;
	}

	public double getDiffPercent() {
		return DiffPercent;
	}

	public void setMeterIn(double meterIn) {
		MeterIn = meterIn;
	}

	public double getMeterIn() {
		return MeterIn;
	}

	public void setMeterGames(int meterGames) {
		MeterGames = meterGames;
	}

	public int getMeterGames() {
		return MeterGames;
	}



}