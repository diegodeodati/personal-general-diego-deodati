package serviseatl.reporteredeem;

import java.io.Serializable;

public class ReporteRedeem implements Serializable {
    static final long serialVersionUID = 104844667002333701L;
    
    private String Fecha;
    private String Hora;
    private int CustomerID;
    private String Customer;
    private int RedeemPoints;
    private double AmountRedeem;
    
    public ReporteRedeem() {

    }

    public ReporteRedeem(String Fecha,
            String Hora,
            int CustomerID,
            String Customer,
            int RedeemPoints,
            double AmountRedeem)    
    {
        this.Fecha = Fecha;
        this.Hora = Hora;
        this.CustomerID = CustomerID;
        this.RedeemPoints = RedeemPoints;
        this.AmountRedeem = AmountRedeem;
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

	public void setCustomerID(int customerID) {
		CustomerID = customerID;
	}

	public int getCustomerID() {
		return CustomerID;
	}

	public void setCustomer(String customer) {
		Customer = customer;
	}

	public String getCustomer() {
		return Customer;
	}

	public void setRedeemPoints(int redeemPoints) {
		RedeemPoints = redeemPoints;
	}

	public int getRedeemPoints() {
		return RedeemPoints;
	}

	public void setAmountRedeem(double amountRedeem) {
		AmountRedeem = amountRedeem;
	}

	public double getAmountRedeem() {
		return AmountRedeem;
	}


}
