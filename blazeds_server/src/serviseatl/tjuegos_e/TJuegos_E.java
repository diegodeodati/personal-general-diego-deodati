package serviseatl.tjuegos_e;

import java.io.Serializable;

public class TJuegos_E implements Serializable {

    static final long serialVersionUID = 103144111178521005L;
    
    private String NJuego;

   
    public TJuegos_E() {
    	
    }

    public TJuegos_E( String NJuego)    
    {
		this.NJuego = NJuego;
	}

	public void setNJuego(String nJuego) {
		NJuego = nJuego;
	}

	public String getNJuego() {
		return NJuego;
	}




}