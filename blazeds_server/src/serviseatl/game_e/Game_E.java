package serviseatl.game_e;

import java.io.Serializable;

public class Game_E implements Serializable {

    static final long serialVersionUID = 103144314700021002L;
    
    private String NameGame;

   
    public Game_E() {
    	
    }

    public Game_E( String NameGame)    
    {
		this.NameGame = NameGame;
	}

	public void setNameGame(String nameGame) {
		NameGame = nameGame;
	}

	public String getNameGame() {
		return NameGame;
	}


}