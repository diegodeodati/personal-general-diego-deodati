package serviseatl_gis.betmaquina;

import java.io.Serializable;

public class BetMaquina implements Serializable {
    static final long serialVersionUID = 103849516906090001L;
    private String CodMaquina;
    private double Bet;
    
    public BetMaquina() {
    	
    }
    
    public BetMaquina(String CodMaquina, double Bet)    
 
    {
		this.CodMaquina = CodMaquina;
		this.Bet = Bet;
	}

	public void setCodMaquina(String codMaquina) {
		CodMaquina = codMaquina;
	}

	public String getCodMaquina() {
		return CodMaquina;
	}

	public void setBet(double bet) {
		Bet = bet;
	}

	public double getBet() {
		return Bet;
	}

}

