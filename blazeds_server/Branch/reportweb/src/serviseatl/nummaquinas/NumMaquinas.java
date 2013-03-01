package serviseatl.nummaquinas;

import java.io.Serializable;

public class NumMaquinas implements Serializable {

    static final long serialVersionUID = 103144111178521004L;
    
    private int NumMaquina;

   
    public NumMaquinas() {
    	
    }

    public NumMaquinas(int NumMaquina)    
    {
		this.NumMaquina = NumMaquina;
	}

	public void setNumMaquina(int numMaquina) {
		NumMaquina = numMaquina;
	}

	public int getNumMaquina() {
		return NumMaquina;
	}



}