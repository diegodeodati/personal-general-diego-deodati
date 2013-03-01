package serviseatl.yearlygraphical;

import java.io.Serializable;

public class YearlyGraphical implements Serializable {

    static final long serialVersionUID = 109234314401077002L;
    
    private int Year;
    private int Month;
    private double WinLoose;

    public YearlyGraphical() {
    	
    }

    public YearlyGraphical(int Year, int Month , double WinLoose)    
    {
		this.Year = Year;
		this.Month = Month;
		this.WinLoose = WinLoose;
	}

	public void setYear(int year) {
		Year = year;
	}

	public int getYear() {
		return Year;
	}

	public void setMonth(int month) {
		Month = month;
	}

	public int getMonth() {
		return Month;
	}

	public void setWinLoose(double winLoose) {
		WinLoose = winLoose;
	}

	public double getWinLoose() {
		return WinLoose;
	}

}