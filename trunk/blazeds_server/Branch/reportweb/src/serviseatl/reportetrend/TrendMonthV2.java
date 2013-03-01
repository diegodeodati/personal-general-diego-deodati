package serviseatl.reportetrend;

import java.io.Serializable;

public class TrendMonthV2 implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3536531121161870782L;

	private String aamsLocationId;
    private String aamsLocationName;
    private String cciudad;
    private String OpeningDate;
    private String DateMonth1;
    private String Mmonth1;
    private int WorkVLTs1;
    private double AvgMeterIn1;
    private double PercentMeterIn1;
    private double AvgMeterOut1;
    private double PercentMeterOut1;
    private String DateMonth2;
    private String Mmonth2;
    private int WorkVLTs2;
    private double AvgMeterIn2;
    private double PercentMeterIn2;
    private double AvgMeterOut2;
    private double PercentMeterOut2;
    private String DateMonth3;
    private String Mmonth3;
    private int WorkVLTs3;
    private double AvgMeterIn3;
    private double PercentMeterIn3;
    private double AvgMeterOut3;
    private double PercentMeterOut3;
    
    public TrendMonthV2() {
    	
    }
    
    public TrendMonthV2 (String aamsLocationId,
    String aamsLocationName,
    String cciudad,
    String OpeningDate,
    String DateMonth1,
    String Mmonth1,
    int WorkVLTs1,
    double AvgMeterIn1,
    double PercentMeterIn1,
    double AvgMeterOut1,
    double PercentMeterOut1,
    String DateMonth2,
    String Mmonth2,
    int WorkVLTs2,
    double AvgMeterIn2,
    double PercentMeterIn2,
    double AvgMeterOut2,
    double PercentMeterOut2,
    String DateMonth3,
    String Mmonth3,
    int WorkVLTs3,
    double AvgMeterIn3,
    double PercentMeterIn3,
    double AvgMeterOut3,
    double PercentMeterOut3

    
    ){
    	this.aamsLocationId = aamsLocationId;
        this.aamsLocationName = aamsLocationName;
        this.cciudad = cciudad; 
    	this.OpeningDate = OpeningDate;
    	this.DateMonth1 = DateMonth1;
    	this.Mmonth1 = Mmonth1;
    	this.WorkVLTs1 = WorkVLTs1;
    	this.AvgMeterIn1 = AvgMeterIn1;
    	this.PercentMeterIn1 = PercentMeterIn1;
    	this.AvgMeterOut1 = AvgMeterOut1;
    	this.PercentMeterOut1 = PercentMeterOut1;
    	this.DateMonth2 = DateMonth2;
    	this.Mmonth2 = Mmonth2;
    	this.WorkVLTs2 = WorkVLTs2;
    	this.AvgMeterIn2 = AvgMeterIn2;
    	this.PercentMeterIn2 = PercentMeterIn2;
    	this.AvgMeterOut2 = AvgMeterOut2;
    	this.PercentMeterOut2 = PercentMeterOut2;
    	this.DateMonth3 = DateMonth3;
    	this.Mmonth3 = Mmonth3;
    	this.WorkVLTs3 = WorkVLTs3;
    	this.AvgMeterIn3 = AvgMeterIn3;
    	this.PercentMeterIn3 = PercentMeterIn3;
    	this.AvgMeterOut3 = AvgMeterOut3;
    	this.PercentMeterOut3 = PercentMeterOut3;
    }
    
	
	public String getAamslocationid() {
		return aamsLocationId;
	}

	public void setAamslocationid(String aamslocationid) {
		this.aamsLocationId = aamslocationid;
	}

	public String getAamslocationname() {
		return aamsLocationName;
	}

	public void setAamslocationname(String aamslocationname) {
		this.aamsLocationName = aamslocationname;
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


	public String getDateMonth1() {
		return DateMonth1;
	}

	public void setDateMonth1(String dateMonth1) {
		DateMonth1 = dateMonth1;
	}

	public String getMmonth1() {
		return Mmonth1;
	}

	public void setMmonth1(String mmonth1) {
		Mmonth1 = mmonth1;
	}

	public int getWorkVLTs1() {
		return WorkVLTs1;
	}

	public void setWorkVLTs1(int workVLTs1) {
		WorkVLTs1 = workVLTs1;
	}

	public double getAvgMeterIn1() {
		return AvgMeterIn1;
	}

	public void setAvgMeterIn1(double avgMeterIn1) {
		AvgMeterIn1 = avgMeterIn1;
	}

	public double getPercentMeterIn1() {
		return PercentMeterIn1;
	}

	public void setPercentMeterIn1(double percentMeterIn1) {
		PercentMeterIn1 = percentMeterIn1;
	}

	public double getAvgMeterOut1() {
		return AvgMeterOut1;
	}

	public void setAvgMeterOut1(double avgMeterOut1) {
		AvgMeterOut1 = avgMeterOut1;
	}

	public double getPercentMeterOut1() {
		return PercentMeterOut1;
	}

	public void setPercentMeterOut1(double percentMeterOut1) {
		PercentMeterOut1 = percentMeterOut1;
	}

	public String getDateMonth2() {
		return DateMonth2;
	}

	public void setDateMonth2(String dateMonth2) {
		DateMonth2 = dateMonth2;
	}

	public String getMmonth2() {
		return Mmonth2;
	}

	public void setMmonth2(String mmonth2) {
		Mmonth2 = mmonth2;
	}

	public int getWorkVLTs2() {
		return WorkVLTs2;
	}

	public void setWorkVLTs2(int workVLTs2) {
		WorkVLTs2 = workVLTs2;
	}

	public double getAvgMeterIn2() {
		return AvgMeterIn2;
	}

	public void setAvgMeterIn2(double avgMeterIn2) {
		AvgMeterIn2 = avgMeterIn2;
	}

	public double getPercentMeterIn2() {
		return PercentMeterIn2;
	}

	public void setPercentMeterIn2(double percentMeterIn2) {
		PercentMeterIn2 = percentMeterIn2;
	}

	public double getAvgMeterOut2() {
		return AvgMeterOut2;
	}

	public void setAvgMeterOut2(double avgMeterOut2) {
		AvgMeterOut2 = avgMeterOut2;
	}

	public double getPercentMeterOut2() {
		return PercentMeterOut2;
	}

	public void setPercentMeterOut2(double percentMeterOut2) {
		PercentMeterOut2 = percentMeterOut2;
	}

	public String getDateMonth3() {
		return DateMonth3;
	}

	public void setDateMonth3(String dateMonth3) {
		DateMonth3 = dateMonth3;
	}

	public String getMmonth3() {
		return Mmonth3;
	}

	public void setMmonth3(String mmonth3) {
		Mmonth3 = mmonth3;
	}

	public int getWorkVLTs3() {
		return WorkVLTs3;
	}

	public void setWorkVLTs3(int workVLTs3) {
		WorkVLTs3 = workVLTs3;
	}

	public double getAvgMeterIn3() {
		return AvgMeterIn3;
	}

	public void setAvgMeterIn3(double avgMeterIn3) {
		AvgMeterIn3 = avgMeterIn3;
	}

	public double getPercentMeterIn3() {
		return PercentMeterIn3;
	}

	public void setPercentMeterIn3(double percentMeterIn3) {
		PercentMeterIn3 = percentMeterIn3;
	}

	public double getAvgMeterOut3() {
		return AvgMeterOut3;
	}

	public void setAvgMeterOut3(double avgMeterOut3) {
		AvgMeterOut3 = avgMeterOut3;
	}

	public double getPercentMeterOut3() {
		return PercentMeterOut3;
	}

	public void setPercentMeterOut3(double percentMeterOut3) {
		PercentMeterOut3 = percentMeterOut3;
	}
	
}
