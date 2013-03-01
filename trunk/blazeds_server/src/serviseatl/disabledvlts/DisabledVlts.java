package serviseatl.disabledvlts;

import java.io.Serializable;

public class DisabledVlts implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4667826562773545551L;
	private int CodCasino;
    private String Ccasino;
    private String CodMaquina;
    private String FechaStr;
    public DisabledVlts() {
    	
    }
    
    public DisabledVlts(
        int CodCasino,
        String Ccasino,
        String CodMaquina,
        String FechaStr){
    	this.CodCasino = CodCasino;
    	this.Ccasino = Ccasino;
    	this.CodMaquina = CodMaquina;
    	this.FechaStr = FechaStr;
    }

    public void setCodCasino(int codCasino) {
    	CodCasino = codCasino;
	}
	public int getCodCasino() {
		return CodCasino;
	}

	
	public void setCcasino(String ccasino) {
    	Ccasino = ccasino;
	}
	public String getCcasino() {
		return Ccasino;
	}

	public void setCodMaquina(String codMaquina) {
    	CodMaquina = codMaquina;
	}
	public String getCodMaquina() {
		return CodMaquina;
	}

	public void setFechaStr(String fechaStr) {
		FechaStr = fechaStr;
	}
	public String getFechaStr() {
		return FechaStr;
	}
	
}
