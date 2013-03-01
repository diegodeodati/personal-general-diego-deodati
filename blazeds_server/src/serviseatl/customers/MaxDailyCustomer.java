package serviseatl.customers;

import java.io.Serializable;

public class MaxDailyCustomer implements Serializable {

    static final long serialVersionUID = 103144314222000772L;
    
    private String Fecha;
    private String Hora;
    private int ConTarjeta;
    private int SinTarjeta;
    private int Total;

   
    public MaxDailyCustomer() {
    	
    }

    public MaxDailyCustomer(String Fecha, String Hora, int ConTarjeta, int SinTarjeta, int Total)    
 
    {
		this.Fecha = Fecha;
		this.Hora = Hora;		
		this.ConTarjeta = ConTarjeta;
		this.SinTarjeta = SinTarjeta;
		this.Total = Total;

	}

	public void setFecha(String fecha) {
		Fecha = fecha;
	}

	public String getFecha() {
		return Fecha;
	}

	public void setHora(String hora) {
		Hora = hora;
	}

	public String getHora() {
		return Hora;
	}

	public void setConTarjeta(int conTarjeta) {
		ConTarjeta = conTarjeta;
	}

	public int getConTarjeta() {
		return ConTarjeta;
	}

	public void setSinTarjeta(int sinTarjeta) {
		SinTarjeta = sinTarjeta;
	}

	public int getSinTarjeta() {
		return SinTarjeta;
	}

	public void setTotal(int total) {
		Total = total;
	}

	public int getTotal() {
		return Total;
	}

	
}