package serviseatl.percentages;

import java.io.Serializable;

public class Percentages implements Serializable {

    static final long serialVersionUID = 103144111178521002L;
    
    private double Percentage;

   
    public Percentages() {
    	
    }

    public Percentages( double Percentage)    
    {
		this.Percentage = Percentage;
	}

	public void setPercentage(double percentage) {
		Percentage = percentage;
	}

	public double getPercentage() {
		return Percentage;
	}

	


}