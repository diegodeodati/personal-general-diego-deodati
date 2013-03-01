package serviseatl.project;

import java.io.Serializable;

public class Project  implements Serializable {

    static final long serialVersionUID = 103144314401000002L;
    
    private double MeterIn;
    private double MeterOut;
    private double MeterDrop;
    private int MeterGames;
    private double MeterJackPot;
    private double VoucherOut;
    private double MeterHandPay;
    private double SumJPHF;
    private double EFTAFT;
    private double MeterVoucherIn;
    private double MeterBill;
    private double WinLose;

   
    public Project() {
    	
    }

    public Project( double MeterIn, double MeterOut, double MeterDrop, int MeterGames,
    	    double MeterJackPot, double VoucherOut, double MeterHandPay, double SumJPHF, double EFTAFT, double MeterVoucherIn,
    	    double MeterBill, double WinLose)    
 
    {
		this.MeterIn = MeterIn;
		this.MeterOut = MeterOut;
		this.MeterDrop = MeterDrop;
		this.MeterGames = MeterGames;
		this.MeterJackPot = MeterJackPot;
		this.VoucherOut = VoucherOut;
		this.MeterHandPay = MeterHandPay;
		this.SumJPHF = SumJPHF;
		this.EFTAFT = EFTAFT;
		this.MeterVoucherIn = MeterVoucherIn;
		this.MeterBill = MeterBill;
		this.WinLose = WinLose;
	}
    
	public void setMeterIn(double meterIn) {
		MeterIn = meterIn;
	}

	public double getMeterIn() {
		return MeterIn;
	}

	public void setMeterOut(double meterOut) {
		MeterOut = meterOut;
	}

	public double getMeterOut() {
		return MeterOut;
	}

	public void setMeterDrop(double meterDrop) {
		MeterDrop = meterDrop;
	}

	public double getMeterDrop() {
		return MeterDrop;
	}

	public void setMeterGames(int meterGames) {
		MeterGames = meterGames;
	}

	public int getMeterGames() {
		return MeterGames;
	}

	public void setMeterJackPot(double meterJackPot) {
		MeterJackPot = meterJackPot;
	}

	public double getMeterJackPot() {
		return MeterJackPot;
	}

	public void setVoucherOut(double voucherOut) {
		VoucherOut = voucherOut;
	}

	public double getVoucherOut() {
		return VoucherOut;
	}

	public void setMeterHandPay(double meterHandPay) {
		MeterHandPay = meterHandPay;
	}

	public double getMeterHandPay() {
		return MeterHandPay;
	}

	public void setSumJPHF(double sumJPHF) {
		SumJPHF = sumJPHF;
	}

	public double getSumJPHF() {
		return SumJPHF;
	}

	public void setEFTAFT(double eFTAFT) {
		EFTAFT = eFTAFT;
	}

	public double getEFTAFT() {
		return EFTAFT;
	}

	public void setMeterVoucherIn(double meterVoucherIn) {
		MeterVoucherIn = meterVoucherIn;
	}

	public double getMeterVoucherIn() {
		return MeterVoucherIn;
	}

	public void setMeterBill(double meterBill) {
		MeterBill = meterBill;
	}

	public double getMeterBill() {
		return MeterBill;
	}

	public void setWinLose(double winLose) {
		WinLose = winLose;
	}

	public double getWinLose() {
		return WinLose;
	}


}