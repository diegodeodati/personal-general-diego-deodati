package serviseatl.topcustomer;

import java.io.Serializable;

public class TopCustomer  implements Serializable {

    static final long serialVersionUID = 103844597701000002L;
    
    private int CustomerID;
    private String CustomerName;
    private String Nationality;
    private int Points;
    
    public TopCustomer() {
    	
    }
    
    public TopCustomer(int CustomerID, String CustomerName, String Nationality, int Points)    
    {
		this.CustomerID = CustomerID;
		this.CustomerName = CustomerName;
		this.Nationality = Nationality;
		this.Points = Points;
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

	public void setNationality(String nationality) {
		Nationality = nationality;
	}

	public String getNationality() {
		return Nationality;
	}

	public void setPoints(int points) {
		Points = points;
	}

	public int getPoints() {
		return Points;
	}


}