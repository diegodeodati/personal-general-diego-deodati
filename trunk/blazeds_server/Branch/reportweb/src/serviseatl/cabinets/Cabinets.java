package serviseatl.cabinets;

import java.io.Serializable;

public class Cabinets implements Serializable {

    static final long serialVersionUID = 103144111178521006L;
    
    private String Cabinet;

   
    public Cabinets() {
    	
    }

    public Cabinets( String Cabinet)    
    {
		this.Cabinet = Cabinet;
	}

	public void setCabinet(String cabinet) {
		Cabinet = cabinet;
	}

	public String getCabinet() {
		return Cabinet;
	}

}