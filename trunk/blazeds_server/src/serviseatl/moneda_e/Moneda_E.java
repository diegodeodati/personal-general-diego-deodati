package serviseatl.moneda_e;

import java.io.Serializable;

public class Moneda_E implements Serializable {

    static final long serialVersionUID = 103144111178521008L;
    
    private String SimboloM;

   
    public Moneda_E() {
    	
    }

    public Moneda_E( String SimboloM)    
    {
		this.SimboloM = SimboloM;
	}

	public void setSimboloM(String simboloM) {
		SimboloM = simboloM;
	}

	public String getSimboloM() {
		return SimboloM;
	}

	
}