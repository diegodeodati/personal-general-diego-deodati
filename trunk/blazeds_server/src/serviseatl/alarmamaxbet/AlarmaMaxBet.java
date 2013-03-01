package serviseatl.alarmamaxbet;

import java.io.Serializable;

public class AlarmaMaxBet implements Serializable {

    static final long serialVersionUID = 103155414222010002L;
    
    private String Fecha;
    private String Hora;    
    private String CodMaquina;
    private int NumMaquina;
    private int CustomerID;
    private double Amount;
    private double MaxBet;
    private double Denominacion;

   
    public AlarmaMaxBet() {
    	
    }

    public AlarmaMaxBet(String Fecha, String Hora, String CodMaquina, int NumMaquina, int CustomerID, double Amount, double MaxBet, double Denominacion)    
    {
		this.Fecha = Fecha;		
    	this.Hora = Hora;
		this.CodMaquina = CodMaquina;		
		this.NumMaquina = NumMaquina;
		this.CustomerID = CustomerID;
		this.Amount = Amount;
		this.MaxBet = MaxBet;
		this.Denominacion = Denominacion;
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

	public void setCodMaquina(String codMaquina) {
		CodMaquina = codMaquina;
	}

	public String getCodMaquina() {
		return CodMaquina;
	}

	public void setNumMaquina(int numMaquina) {
		NumMaquina = numMaquina;
	}

	public int getNumMaquina() {
		return NumMaquina;
	}

	public void setCustomerID(int customerID) {
		CustomerID = customerID;
	}

	public int getCustomerID() {
		return CustomerID;
	}

	public void setAmount(double amount) {
		Amount = amount;
	}

	public double getAmount() {
		return Amount;
	}

	public void setMaxBet(double maxBet) {
		MaxBet = maxBet;
	}

	public double getMaxBet() {
		return MaxBet;
	}

	public void setDenominacion(double denominacion) {
		Denominacion = denominacion;
	}

	public double getDenominacion() {
		return Denominacion;
	}


}