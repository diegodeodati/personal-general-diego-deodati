package serviseatl.billestimation;

import java.io.Serializable;

public class BillEstimation implements Serializable {
    static final long serialVersionUID = 103844514003000001L;
    
    private String CodMaquina;
    private double Denominacion;
    private int NumMaquina;
    private String NFabricante;
    private String NameGame;
    private String SimboloM;
    private int MeterOpen;
    private int MeterClose;
    private double ResultM;
    private double MTD;
    private double AVGT;
    private String NameS;
    
    
    public BillEstimation() {
    	
    }
    
    public BillEstimation(String CodMaquina, double Denominacion, int NumMaquina, String NFabricante, String NameGame, 
    		String SimboloM, int MeterOpen, int MeterClose, double ResultM, double MTD, double AVGT, String NameS)    
 
    {
		this.CodMaquina = CodMaquina;
		this.Denominacion = Denominacion;
		this.NumMaquina = NumMaquina;
		this.NFabricante = NFabricante;
		this.NameGame = NameGame;
		this.SimboloM = SimboloM;
		this.MeterOpen = MeterOpen;
		this.MeterClose = MeterClose;
		this.ResultM = ResultM;
		this.MTD = MTD;
		this.AVGT = AVGT;
		this.NameS = NameS;
	}

    public String getCodMaquina() {
		return CodMaquina;
	}
	public void setCodMaquina(String CodMaquina) {
		this.CodMaquina = CodMaquina;
	}
	public double getDenominacion() {
		return Denominacion;
	}
	public void setDenominacion(double Denominacion) {
		this.Denominacion = Denominacion;
	}
	public int getNumMaquina() {
		return NumMaquina;
	}
	public void setNumMaquina(int NumMaquina) {
		this.NumMaquina = NumMaquina;
	}
	public String getNFabricante() {
		return NFabricante;
	}
	public void setNFabricante(String NFabricante) {
		this.NFabricante = NFabricante;
	}
	public String getNameGame() {
		return NameGame;
	}
	public void setNameGame(String NameGame) {
		this.NameGame = NameGame;
	}
	public String getSimboloM() {
		return SimboloM;
	}
	public void setSimboloM(String SimbolM) {
		this.SimboloM = SimbolM;
	}
	public int getMeterOpen() {
		return MeterOpen;
	}
	public void setMeterOpen(int MeterOpen) {
		this.MeterOpen = MeterOpen;
	}
	public int getMeterClose() {
		return MeterClose;
	}
	public void setMeterClose(int MeterClose) {
		this.MeterClose = MeterClose;
	}
	public double getResultM() {
		return ResultM;
	}
	public void setResultM(double ResultM) {
		this.ResultM = ResultM;
	}
	public double getMTD() {
		return MTD;
	}
	public void setMTD(double MTD) {
		this.MTD = MTD;
	}
	public double getAVGT() {
		return AVGT;
	}
	public void setAVGT(double AVGT) {
		this.AVGT = AVGT;
	}
	public String getNameS() {
		return NameS;
	}
	public void setNameS(String NameS) {
		this.NameS = NameS;
	}

}
