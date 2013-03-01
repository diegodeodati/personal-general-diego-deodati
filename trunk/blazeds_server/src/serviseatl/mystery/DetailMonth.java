package serviseatl.mystery;

import java.io.Serializable;

public class DetailMonth  implements Serializable {

    static final long serialVersionUID = 103144314222020002L;
    
    private String Nombre;
    private String Fecha;
    private String FechaPago;
    private int TimeHours;
    private double RandomWin;
    private int NumMaquina;
    private double DenominacionM;
    private String NameGame;
    private String AreaName;
    private int CustomerID;
    private String CustomerName;

    public DetailMonth() {
    	
    }

    public DetailMonth(String Nombre, String Fecha, String FechaPago, int TimeHours, double RandomWin, int NumMaquina, 
    				double DenominacionM, String NameGame, String AreaName, 
    				int CustomerID, String CustomerName)    
 
    {
		this.Nombre = Nombre;		
		this.Fecha = Fecha;
		this.FechaPago = FechaPago;
		this.TimeHours = TimeHours;
		this.RandomWin = RandomWin;
		this.NumMaquina = NumMaquina;
		this.DenominacionM = DenominacionM;
		this.NameGame = NameGame;
		this.AreaName = AreaName;
		this.CustomerID = CustomerID;
		this.CustomerName = CustomerName;

	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setFecha(String fecha) {
		Fecha = fecha;
	}

	public String getFecha() {
		return Fecha;
	}

	public void setFechaPago(String fechaPago) {
		FechaPago = fechaPago;
	}

	public String getFechaPago() {
		return FechaPago;
	}

	public void setTimeHours(int timeHours) {
		TimeHours = timeHours;
	}

	public int getTimeHours() {
		return TimeHours;
	}

	public void setRandomWin(double randomWin) {
		RandomWin = randomWin;
	}

	public double getRandomWin() {
		return RandomWin;
	}

	public void setNumMaquina(int numMaquina) {
		NumMaquina = numMaquina;
	}

	public int getNumMaquina() {
		return NumMaquina;
	}

	public void setDenominacionM(double denominacionM) {
		DenominacionM = denominacionM;
	}

	public double getDenominacionM() {
		return DenominacionM;
	}

	public void setNameGame(String nameGame) {
		NameGame = nameGame;
	}

	public String getNameGame() {
		return NameGame;
	}

	public void setAreaName(String areaName) {
		AreaName = areaName;
	}

	public String getAreaName() {
		return AreaName;
	}

	public void setCustomerID(int customerID) {
		CustomerID = customerID;
	}

	public int getCustomerID() {
		return CustomerID;
	}

	public void setCustomerName(String customerName) {
		CustomerName = customerName;
	}

	public String getCustomerName() {
		return CustomerName;
	}
	
}