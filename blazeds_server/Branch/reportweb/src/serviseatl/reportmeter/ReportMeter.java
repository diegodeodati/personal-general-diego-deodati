package serviseatl.reportmeter;

import java.io.Serializable;

public class ReportMeter implements Serializable {

    static final long serialVersionUID = 103844518801000002L;
    
    private String CodMaquina;
    private int NumMaquina;
    private double MeterIn;
    private double MeterOut;
    private double MeterDrop;
    private int MeterGames;
    private double MeterJackPot;
    private double MeterVoucherOut;
    private double MeterHandPay;
    private double MeterEFTAFT;
    private double MeterVoucherIn;
    private double MeterBill;
    private boolean ClearRam;
    private String Names;    

    public ReportMeter() {
    	
    }
    
    public ReportMeter(String CodMaquina, int NumMaquina, double MeterIn, double MeterOut, double MeterDrop, int MeterGames,
    	    double MeterJackPot, double MeterVoucherOut, double MeterHandPay, double MeterEFTAFT, double MeterVoucherIn,
    	    double MeterBill, boolean ClearRam, String Names)    
     {
		this.CodMaquina = CodMaquina;
		this.NumMaquina = NumMaquina;
		this.MeterIn = MeterIn;
		this.MeterOut = MeterOut;
		this.MeterDrop = MeterDrop;
		this.MeterGames = MeterGames;
		this.MeterJackPot = MeterJackPot;
		this.MeterVoucherOut = MeterVoucherOut;
		this.MeterHandPay = MeterHandPay;
		this.MeterEFTAFT = MeterEFTAFT;
		this.MeterVoucherIn = MeterVoucherIn;
		this.MeterBill = MeterBill;
		this.ClearRam = ClearRam;
		this.Names = Names;
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
	public double getMeterIn() {
		return MeterIn;
	}
	public void setMeterIn(double MeterIn) {
		this.MeterIn = MeterIn;
	}
	public double getMeterOut() {
		return MeterOut;
	}
	public void setMeterOut(double MeterOut) {
		this.MeterOut = MeterOut;
	}
	public double getMeterDrop() {
		return MeterDrop;
	}
	public void setMeterDrop(double MeterDrop) {
		this.MeterDrop = MeterDrop;
	}
	public int getMeterGames() {
		return MeterGames;
	}
	public void setMeterGames(int MeterGames ) {
		this.MeterGames = MeterGames;
	}
	public double getMeterJackPot() {
		return MeterJackPot;
	}
	public void setMeterJackPot(double MeterJackPot) {
		this.MeterJackPot = MeterJackPot;
	}
	public double getMeterVoucherOut() {
		return MeterVoucherOut;
	}
	public void setMeterVoucherOut(double MeterVoucherOut) {
		this.MeterVoucherOut = MeterVoucherOut;
	}
	public double getMeterHandPay() {
		return MeterHandPay;
	}
	public void setMeterHandPay(double MeterHandPay) {
		this.MeterHandPay = MeterHandPay;
	}
	public double getMeterEFTAFT() {
		return MeterEFTAFT;
	}
	public void setMeterEFTAFT(double MeterEFTAFT) {
		this.MeterEFTAFT = MeterEFTAFT;
	}
	public double getMeterVoucherIn() {
		return MeterVoucherIn;
	}
	public void setMeterVoucherIn(double MeterVoucherIn) {
		this.MeterVoucherIn = MeterVoucherIn;
	}
	public double getMeterBill() {
		return MeterBill;
	}
	public void setMeterBill(double MeterBill ) {
		this.MeterBill = MeterBill;
	}
	public boolean getClearRam() {
		return ClearRam;
	}
	public void setClearRam(boolean ClearRam) {
		this.ClearRam = ClearRam;
	}
	public String getNames() {
		return Names;
	}
	public void setNames(String Names) {
		this.Names = Names;
	}
}