package serviseatl.summarycasinodate;

import java.io.Serializable;
import java.util.Date;

public class SummaryCasinoDate implements Serializable {
    static final long serialVersionUID = 103844514004000001L;
    
    private String Casino;
    private Date Fecha;
    private long FechaInt;
    private double NetWinLose;
   
    
    public SummaryCasinoDate() {
    	
    }
    
    public SummaryCasinoDate(String Casino, Date Fecha, long FechaInt, double NetWinLose)    
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
