package serviseatl.summarycasinodate;

import java.io.Serializable;
import java.util.Date;

public class SummaryCasinoDateV2 implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -8372266453471754133L;
	private String aamsLocationName;
    private Date Fecha;
    private long FechaInt;
    private double NetWinLose;
   
    
    public SummaryCasinoDateV2() {
    	
    }
    
    public SummaryCasinoDateV2(String aamsLocationName, Date Fecha, long FechaInt, double NetWinLose)    
     {
		this.aamsLocationName = aamsLocationName;
		this.Fecha = Fecha;
		this.FechaInt = FechaInt;
		this.NetWinLose = NetWinLose;

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

	public void setAamsLocationName(String aamsLocationName) {
		this.aamsLocationName = aamsLocationName;
	}

	public String getAamsLocationName() {
		return aamsLocationName;
	}

}
