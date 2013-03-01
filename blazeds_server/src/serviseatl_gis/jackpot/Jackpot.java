package serviseatl_gis.jackpot;

import java.io.Serializable;

public class Jackpot implements Serializable {
    static final long serialVersionUID = 104834644006000701L;
    
    private int NumMaquina;
    private String FechaJP;
    private String HoraJP;
    private double Amount;
    private String CustomerID;
    private String Customer;
    private String Manager;
    private String Slot;
    private String Cashier;
    
    public Jackpot() {
    	
    }

    public Jackpot(int NumMaquina, String FechaJP, String HoraJP, double Amount, String CustomerID, String Customer, String Manager, String Slot, String Cashier)    
    {
		this.NumMaquina = NumMaquina;
		this.FechaJP = FechaJP;
		this.HoraJP = HoraJP;
		this.Amount = Amount;
		this.CustomerID = CustomerID;
		this.Customer = Customer;
		this.Manager = Manager;
		this.Slot = Slot;
		this.Cashier = Cashier;		
	}


	public void setNumMaquina(int numMaquina) {
		NumMaquina = numMaquina;
	}

	public int getNumMaquina() {
		return NumMaquina;
	}

	public void setFechaJP(String fechaJP) {
		FechaJP = fechaJP;
	}

	public String getFechaJP() {
		return FechaJP;
	}

	public void setHoraJP(String horaJP) {
		HoraJP = horaJP;
	}

	public String getHoraJP() {
		return HoraJP;
	}

	public void setAmount(double amount) {
		Amount = amount;
	}

	public double getAmount() {
		return Amount;
	}

	public void setCustomerID(String customerID) {
		CustomerID = customerID;
	}

	public String getCustomerID() {
		return CustomerID;
	}

	public void setCustomer(String customer) {
		Customer = customer;
	}

	public String getCustomer() {
		return Customer;
	}

	public void setManager(String manager) {
		Manager = manager;
	}

	public String getManager() {
		return Manager;
	}

	public void setSlot(String slot) {
		Slot = slot;
	}

	public String getSlot() {
		return Slot;
	}

	public void setCashier(String cashier) {
		Cashier = cashier;
	}

	public String getCashier() {
		return Cashier;
	}
    

}
