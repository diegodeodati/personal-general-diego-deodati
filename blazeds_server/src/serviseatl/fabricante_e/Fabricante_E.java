package serviseatl.fabricante_e;

import java.io.Serializable;

public class Fabricante_E  implements Serializable {

    static final long serialVersionUID = 103144314478521002L;
    
    private String NFabricante;

   
    public Fabricante_E() {
    	
    }

    public Fabricante_E( String NFabricante)    
    {
		this.NFabricante = NFabricante;
	}

	public void setNFabricante(String nFabricante) {
		NFabricante = nFabricante;
	}

	public String getNFabricante() {
		return NFabricante;
	}
    


}