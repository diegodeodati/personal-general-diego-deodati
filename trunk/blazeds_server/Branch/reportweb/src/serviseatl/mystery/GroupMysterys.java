package serviseatl.mystery;

import java.io.Serializable;

public class GroupMysterys implements Serializable {

    static final long serialVersionUID = 103144314222030002L;
    
    private String GNombre;
    private String Nombre;
    private double Minimun;
    private double Maximun;
    private double Increment;
    private double MinBet;
    private double Acumulate;
   
    public GroupMysterys() {
    	
    }

    public GroupMysterys(String GNombre, String Nombre, double Minimun, double Maximun, 
    		double Increment, double MinBet, double Acumulate)    
    {
		this.GNombre = GNombre;
		this.Nombre = Nombre;		
		this.Minimun = Minimun;
		this.Maximun = Maximun;
		this.Increment = Increment;
		this.MinBet = MinBet;
		this.Acumulate = Acumulate;

	}

	public void setGNombre(String gNombre) {
		GNombre = gNombre;
	}

	public String getGNombre() {
		return GNombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setMinimun(double minimun) {
		Minimun = minimun;
	}

	public double getMinimun() {
		return Minimun;
	}

	public void setMaximun(double maximun) {
		Maximun = maximun;
	}

	public double getMaximun() {
		return Maximun;
	}

	public void setIncrement(double increment) {
		Increment = increment;
	}

	public double getIncrement() {
		return Increment;
	}

	public void setMinBet(double minBet) {
		MinBet = minBet;
	}

	public double getMinBet() {
		return MinBet;
	}

	public void setAcumulate(double acumulate) {
		Acumulate = acumulate;
	}

	public double getAcumulate() {
		return Acumulate;
	}
	
}