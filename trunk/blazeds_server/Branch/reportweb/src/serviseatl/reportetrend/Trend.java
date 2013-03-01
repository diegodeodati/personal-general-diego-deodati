package serviseatl.reportetrend;
import java.io.Serializable;

public class Trend implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -792758363158591494L;

	private String CodCasino;
    private String Ccasino;
    private String Cciudad;
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
    
    public Trend() {
    	
    }
    
    public Trend (String CodCasino,
    String Ccasino,
    String Cciudad,
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
    	this.CodCasino = CodCasino; 
    	this.Ccasino = Ccasino;
    	this.Cciudad = Cciudad; 
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
    
	public void setCodCasino(String codcasino) {
		CodCasino = codcasino;
	}
	public String getCodCasino() {
		return CodCasino;
	}

	public void setCcasino(String ccasino) {
		Ccasino = ccasino;
	}
	public String getCcasino() {
		return Ccasino;
	}

	public void setCciudad(String cciudad) {
		Cciudad = cciudad;
	}
	public String getCciudad() {
		return Cciudad;
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
