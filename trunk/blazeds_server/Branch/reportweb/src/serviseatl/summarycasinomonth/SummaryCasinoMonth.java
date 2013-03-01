package serviseatl.summarycasinomonth;

import java.io.Serializable;
import java.util.Date;

public class SummaryCasinoMonth  implements Serializable {
    static final long serialVersionUID = 103844514111100001L;
    
    private String Casino;
    private Date Fecha;
    private long FechaInt;
    private double NetWinLose;
   
    
    public SummaryCasinoMonth() {
    	
    }
    
    public SummaryCasinoMonth(String Casino, Date Fecha, long FechaInt, double NetWinLose)    
     {
		this.Casino = Casino;
		this.Fecha = Fecha;
		this.FechaInt = FechaInt;
		this.NetWinLose = NetWinLose;

	}

	public String getCasino() {
		return Casino;
	}
	public void setCasino(String Casino) {
		this.Casino = Casino;
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
