package serviseatl.modelomaquina_e;

import java.io.Serializable;

public class ModeloMaquina_E implements Serializable {

    static final long serialVersionUID = 103144111178521003L;
    
    private String NameModelo;

   
    public ModeloMaquina_E() {
    	
    }

    public ModeloMaquina_E( String NameModelo)    
    {
		this.NameModelo = NameModelo;
	}

	public void setNameModelo(String nameModelo) {
		NameModelo = nameModelo;
	}

	public String getNameModelo() {
		return NameModelo;
	}



}