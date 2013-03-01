package serviseatl.prueba;

import java.io.Serializable;

public class Prueba  implements Serializable {

    static final long serialVersionUID = 107777314401000002L;
    
    private String Texto;

   
    public Prueba() {
    	
    }

    public Prueba( String Texto)    
 
    {
		this.Texto = Texto;
	}

	public void setTexto(String texto) {
		Texto = texto;
	}

	public String getTexto() {
		return Texto;
	}
    

}