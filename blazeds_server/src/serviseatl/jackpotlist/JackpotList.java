package serviseatl.jackpotlist;

import java.io.Serializable;

public class JackpotList implements Serializable {

    static final long serialVersionUID = 109234314401000222L;
    
    private String FechaJP;
    private String HoraJP;
    private String Winning;
    private int NumMaquina;
    private int NumTrans;
    private int Slip;
    private double Amount;
    private double Progressive;
    private double OverPaid;
    private double Total;
    private String Manager;
    private String Cashier;
    private String Slot;
    private String CustID;
    private String CustomerName;
    private double Malfunction;
    
    public JackpotList() {
    	
    }

    public JackpotList( String FechaJP, String HoraJP, String Winning, int NumMaquina, int NumTrans, int Slip, 
    		double Amount, double Progressive, double OverPaid, double Total, String Manager, String Cashier, String Slot, String CustID, String CustomerName, double Malfunction)    
    {
		this.FechaJP = FechaJP;
		this.HoraJP = HoraJP;
		this.Winning = Winning;
		this.NumMaquina = NumMaquina;
		this.NumTrans = NumTrans;
		this.Slip = Slip;
		this.Amount = Amount;
		this.Progressive = Progressive;
		this.OverPaid = OverPaid;
		this.Total = Total;
		this.Manager = Manager;
		this.Cashier = Cashier;
		this.Slot = Slot;
		this.CustID = CustID;
		this.CustomerName = CustomerName;
		this.Malfunction = Malfunction;
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

	public void setWinning(String winning) {
		Winning = winning;
	}

	public String getWinning() {
		return Winning;
	}

	public void setNumMaquina(int numMaquina) {
		NumMaquina = numMaquina;
	}

	public int getNumMaquina() {
		return NumMaquina;
	}

	public void setNumTrans(int numTrans) {
		NumTrans = numTrans;
	}

	public int getNumTrans() {
		return NumTrans;
	}

	public void setSlip(int slip) {
		Slip = slip;
	}

	public int getSlip() {
		return Slip;
	}

	public void setAmount(double amount) {
		Amount = amount;
	}

	public double getAmount() {
		return Amount;
	}

	public void setProgressive(double progressive) {
		Progressive = progressive;
	}

	public double getProgressive() {
		return Progressive;
	}

	public void setOverPaid(double overPaid) {
		OverPaid = overPaid;
	}

	public double getOverPaid() {
		return OverPaid;
	}

	public void setTotal(double total) {
		Total = total;
	}

	public double getTotal() {
		return Total;
	}

	public void setManager(String manager) {
		Manager = manager;
	}

	public String getManager() {
		return Manager;
	}

	public void setCashier(String cashier) {
		Cashier = cashier;
	}

	public String getCashier() {
		return Cashier;
	}

	public void setSlot(String slot) {
		Slot = slot;
	}

	public String getSlot() {
		return Slot;
	}

	public void setCustID(String custID) {
		CustID = custID;
	}

	public String getCustID() {
		return CustID;
	}

	public void setCustomerName(String customerName) {
		CustomerName = customerName;
	}

	public String getCustomerName() {
		return CustomerName;
	}

	public void setMalfunction(double malfunction) {
		Malfunction = malfunction;
	}

	public double getMalfunction() {
		return Malfunction;
	}

}