package serviseatl.reportetrend;

import java.io.Serializable;

public class TrendV2 implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1970694658723380300L;

	private String aamsLocationId;
    private String aamsLocationName;
    private String cciudad;
    private String OpeningDate;
    private String DateMonth;
    private String Yyear;
    private String Mmonth;
    private int NroWorkDays;
    private int EnabledVLTs;
    private int WorkVLTs;
    private double AvgMeterIn;
    private double PercentMeterIn;
    private double AvgMeterOut;
    private double PercentMeterOut;
    
    public TrendV2() {
    	
    }
    
    public TrendV2 (String aamsLocationId,
    String aamsLocationName,
    String cciudad,
    String OpeningDate,
    String DateMonth,
    String Yyear,
    String Mmonth,
    int NroWorkDays,
    int EnabledVLTs,
    int WorkVLTs,
    double AvgMeterIn,
    double PercentMeterIn,
    double AvgMeterOut,
    double PercentMeterOut
    ){
    	this.aamsLocationId = aamsLocationId;
    	this.aamsLocationName = aamsLocationName;
    	this.cciudad = cciudad; 
    	this.OpeningDate = OpeningDate;
    	this.DateMonth = DateMonth;
    	this.Yyear = Yyear;
    	this.Mmonth = Mmonth;
    	this.NroWorkDays = NroWorkDays;
    	this.EnabledVLTs = EnabledVLTs;
    	this.WorkVLTs = WorkVLTs;
    	this.AvgMeterIn = AvgMeterIn;
    	this.PercentMeterIn = PercentMeterIn;
    	this.AvgMeterOut = AvgMeterOut;
    	this.PercentMeterOut = PercentMeterOut;
    }
    
	
	public String getAamsLocationId() {
		return aamsLocationId;
	}

	public void setAamsLocationId(String aamsLocationId) {
		this.aamsLocationId = aamsLocationId;
	}

	public String getAamsLocationName() {
		return aamsLocationName;
	}

	public void setAamsLocationName(String aamsLocationName) {
		this.aamsLocationName = aamsLocationName;
	}

	public String getCciudad() {
		return cciudad;
	}

	public void setCciudad(String cciudad) {
		this.cciudad = cciudad;
	}

	public void setOpeningDate(String openingdate) {
		OpeningDate = openingdate;
	}
	public String getOpeningDate() {
		return OpeningDate;
	}
	
	public void setDateMonth(String datemonth) {
		DateMonth = datemonth;
	}
	public String getDateMonth() {
		return DateMonth;
	}

	public void setYyear(String yyear) {
		Yyear = yyear;
	}
	public String getYyear() {
		return Yyear;
	}

	public void setMmonth(String mmonth) {
		Mmonth = mmonth;
	}
	public String getMmonth() {
		return Mmonth;
	}

	public void setNroWorkDays(int nroworkdays) {
		NroWorkDays = nroworkdays;
	}
	public int getNroWorkDays() {
		return NroWorkDays;
	}

	public void setEnabledVLTs(int enabledvlts) {
		EnabledVLTs = enabledvlts;
	}
	public int getEnabledVLTs() {
		return EnabledVLTs;
	}

	public void setWorkVLTs(int workvlts) {
		WorkVLTs = workvlts;
	}
	public int getWorkVLTs() {
		return WorkVLTs;
	}

	public void setAvgMeterIn(double avgmeterin) {
		AvgMeterIn = avgmeterin;
	}
	public double getAvgMeterIn() {
		return AvgMeterIn;
	}

	public void setPercentMeterIn(double percentmeterin) {
		PercentMeterIn = percentmeterin;
	}
	public double getPercentMeterIn() {
		return PercentMeterIn;
	}


	public void setAvgMeterOut(double avgmeterout) {
		AvgMeterOut = avgmeterout;
	}
	public double getAvgMeterOut() {
		return AvgMeterOut;
	}

	public void setPercentMeterOut(double percentmeterout) {
		PercentMeterOut = percentmeterout;
	}
	public double getPercentMeterOut() {
		return PercentMeterOut;
	}

}
