package serviseatl.summarycasinomonth;

import java.io.Serializable;
import java.util.Date;

public class SummaryCasinoMonthV2 implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6481688706887520408L;

	private String aamsLocationId;
    private String aamsLocationName;
    private Date Fecha;
    private long FechaInt;
    private double NetWinLose;
   
    
    public SummaryCasinoMonthV2() {
    	
    }
    
    public SummaryCasinoMonthV2(String aamsLocationId, String aamsLocationName, Date Fecha, long FechaInt, double NetWinLose)    
     {
    	this.aamsLocationId = aamsLocationId;
		this.aamsLocationName = aamsLocationName;
		this.Fecha = Fecha;
		this.FechaInt = FechaInt;
		this.NetWinLose = NetWinLose;

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

	public Date getFecha() {
		return Fecha;
	}
	public void setFecha(Date Fecha) {
		this.Fecha = Fecha;
	}
	public long getFechaInt() {
		return FechaInt;
	}
	public void setFechaInt(long FechaInt) {
		this.FechaInt = FechaInt;
	}
	public double getNetWinLose() {
		return NetWinLose;
	}
	public void setNetWinLose(double NetWinLose) {
		this.NetWinLose = NetWinLose;
	}
	
}
