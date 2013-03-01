package serviseatl.mystery;

import java.io.Serializable;

public class AvgMystery  implements Serializable {

    static final long serialVersionUID = 103144314222010002L;
    
    private String Nombre;
    private double AvgHour;
    private double AvgDays;
    private double AvgAmount;
    private int Counter;
    private double TotalAmount;

   
    public AvgMystery() {
    	
    }

    public AvgMystery(String Nombre, double AvgHour, double AvgDays, double AvgAmount, int Counter, double TotalAmount)    
    {
		this.Nombre = Nombre;
		this.AvgHour = AvgHour;		
		this.AvgDays = AvgDays;
		this.AvgAmount = AvgAmount;
		this.Counter = Counter;
		this.TotalAmount = TotalAmount;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setAvgHour(double avgHour) {
		AvgHour = avgHour;
	}

	public double getAvgHour() {
		return AvgHour;
	}

	public void setAvgDays(double avgDays) {
		AvgDays = avgDays;
	}

	public double getAvgDays() {
		return AvgDays;
	}

	public void setAvgAmount(double avgAmount) {
		AvgAmount = avgAmount;
	}

	public double getAvgAmount() {
		return AvgAmount;
	}

	public void setCounter(int counter) {
		Counter = counter;
	}

	public int getCounter() {
		return Counter;
	}

	public void setTotalAmount(double totalAmount) {
		TotalAmount = totalAmount;
	}

	public double getTotalAmount() {
		return TotalAmount;
	}

}