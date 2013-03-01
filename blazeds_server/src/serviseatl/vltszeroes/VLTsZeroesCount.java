package serviseatl.vltszeroes;

import java.io.Serializable;

public class VLTsZeroesCount implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 105695295917924377L;
	private String CodMaquina;
    private int TotalZeroes;
    
    public VLTsZeroesCount()
    {
    }
    
    public VLTsZeroesCount(String CodMaquina, int TotalZeroes)
    {
    	this.CodMaquina = CodMaquina;
    	this.TotalZeroes = TotalZeroes;
    }

	public void setCodMaquina(String codmaquina) {
		CodMaquina = codmaquina;
	}
	public String getCodMaquina() {
		return CodMaquina;
	}
    
	public void setTotalZeroes(int totalzeroes) {
		TotalZeroes = totalzeroes;
	}
	public int getTotalZeroes() {
		return TotalZeroes;
	}
}
