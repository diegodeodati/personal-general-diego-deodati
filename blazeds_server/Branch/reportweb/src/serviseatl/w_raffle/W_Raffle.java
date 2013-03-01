package serviseatl.w_raffle;

import java.io.Serializable;

public class W_Raffle   implements Serializable {

    static final long serialVersionUID = 109234314401000002L;
    
    private String Nombre;
    private String FechaI;
    private String HoraI;
    private String FechaF;
    private String HoraF;
    private int CustomerID;
    private String NewCard;
    private String TicketNumber;
    private String NCustomer;
    private int Numero;
    private double Monto;

    public W_Raffle() {
    	
    }

    public W_Raffle( String Nombre, String FechaI, String HoraI, String FechaF, String HoraF, int CustomerID, 
    		String NewCard, String TicketNumber, String NCustomer, int Numero, double Monto)    
    {
		this.Nombre = Nombre;
		this.FechaI = FechaI;
		this.HoraI = HoraI;
		this.FechaF = FechaF;
		this.HoraF = HoraF;
		this.CustomerID = CustomerID;
		this.NewCard = NewCard;
		this.TicketNumber = TicketNumber;
		this.NCustomer = NCustomer;
		this.Numero = Numero;
		this.Monto = Monto;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setFechaI(String fechaI) {
		FechaI = fechaI;
	}

	public String getFechaI() {
		return FechaI;
	}

	public void setHoraI(String horaI) {
		HoraI = horaI;
	}

	public String getHoraI() {
		return HoraI;
	}

	public void setFechaF(String fechaF) {
		FechaF = fechaF;
	}

	public String getFechaF() {
		return FechaF;
	}

	public void setHoraF(String horaF) {
		HoraF = horaF;
	}

	public String getHoraF() {
		return HoraF;
	}

	public void setCustomerID(int customerID) {
		CustomerID = customerID;
	}

	public int getCustomerID() {
		return CustomerID;
	}

	public void setNewCard(String newCard) {
		NewCard = newCard;
	}

	public String getNewCard() {
		return NewCard;
	}

	public void setTicketNumber(String ticketNumber) {
		TicketNumber = ticketNumber;
	}

	public String getTicketNumber() {
		return TicketNumber;
	}

	public void setNCustomer(String nCustomer) {
		NCustomer = nCustomer;
	}

	public String getNCustomer() {
		return NCustomer;
	}

	public void setNumero(int numero) {
		Numero = numero;
	}

	public int getNumero() {
		return Numero;
	}

	public void setMonto(double monto) {
		Monto = monto;
	}

	public double getMonto() {
		return Monto;
	}
}