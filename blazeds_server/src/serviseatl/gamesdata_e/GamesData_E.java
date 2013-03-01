package serviseatl.gamesdata_e;

import java.io.Serializable;

public class GamesData_E implements Serializable {

    static final long serialVersionUID = 103144314408521002L;
    
    private String IDGame;

   
    public GamesData_E() {
    	
    }

    public GamesData_E( String IDGame)    
    {
		this.IDGame = IDGame;
	}

	public void setIDGame(String iDGame) {
		IDGame = iDGame;
	}

	public String getIDGame() {
		return IDGame;
	}


}