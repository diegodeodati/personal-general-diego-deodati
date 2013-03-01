package serviseatl.jackpotshopperfills;

import java.io.Serializable;

public class JackpotsHopperFills implements Serializable {

    static final long serialVersionUID = 103844599001000002L;
    
    private String CodMaquina;
    private int NumMaquina;
    private double AmountMeter;
    private double Jackpot;
    private double HopperFill;
    private double MalFunction;
    private double OverPaid;
    private double Progressive;
    private double Variance;
    
    public JackpotsHopperFills() {
    	
    }
    
    public JackpotsHopperFills(String CodMaquina, int NumMaquina, double AmountMeter, double Jackpot, double HopperFill, double MalFunction, double OverPaid,
    	    double Progressive, double Variance)    
 
    {
		this.CodMaquina = CodMaquina;
		this.NumMaquina = NumMaquina;
		this.AmountMeter = AmountMeter;
		this.Jackpot = Jackpot;
		this.HopperFill = HopperFill;
		this.MalFunction = MalFunction;
		this.OverPaid = OverPaid;
		this.Progressive = Progressive;    
		this.Variance = Variance;    
	}

    public String getCodMaquina() {
		return CodMaquina;
	}
	public void setCodMaquina(String CodMaquina) {
		this.CodMaquina = CodMaquina;
	}
	public int getNumMaquina() {
		return NumMaquina;
	}
	public void setNumMaquina(int NumMaquina) {
		this.NumMaquina = NumMaquina;
	}

	public void setAmountMeter(double amountMeter) {
		AmountMeter = amountMeter;
	}

	public double getAmountMeter() {
		return AmountMeter;
	}

	public void setJackpot(double jackpot) {
		Jackpot = jackpot;
	}

	public double getJackpot() {
		return Jackpot;
	}

	public void setHopperFill(double hopperFill) {
		HopperFill = hopperFill;
	}

	public double getHopperFill() {
		return HopperFill;
	}

	public void setMalFunction(double malFunction) {
		MalFunction = malFunction;
	}

	public double getMalFunction() {
		return MalFunction;
	}

	public void setOverPaid(double overPaid) {
		OverPaid = overPaid;
	}

	public double getOverPaid() {
		return OverPaid;
	}

	public void setProgressive(double progressive) {
		Progressive = progressive;
	}

	public double getProgressive() {
		return Progressive;
	}

	public void setVariance(double variance) {
		Variance = variance;
	}

	public double getVariance() {
		return Variance;
	}

}