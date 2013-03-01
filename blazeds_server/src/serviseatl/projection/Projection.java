package serviseatl.projection;

import java.io.Serializable;

public class Projection implements Serializable {

    static final long serialVersionUID = 103333514001000002L;
    
    private String AnioMes;
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
    private double WinLoose;
    private double Courtesy;
    private double PercBill;
    private double AVGBill;
    private double AVGWinLoose;
    private double AVGCourtesy;    
    private double HoldPerc;
    private double TheoricalP;
    private double DiffPercent;
    private double HoldTheoMoney;
    private double Bill_F;
    private double Coins_F;
    private double Jackpot_F;    
    private double HopperFill_F;    
    private double MalFunction_F;    
    private double OverPaid;    
    private double Progressive;    
    private double WLoose_F;
    private double VarianzaBill;    
    private double VarianzaJP;
    private double Varianza;    
    
    public Projection( String AnioMes, double MeterIn, double MeterOut, double MeterDrop, int MeterGames,
    					double MeterJackPot, double VoucherOut, double MeterHandPay, double SumJPHF, double EFTAFT,
    					double MeterVoucherIn, double MeterBill, double WinLoose, double Courtesy, double PercBill, double AVGBill, 
    					double AVGWinLoose, double AVGCourtesy, double HoldPerc, double TheoricalP, double DiffPercent, double HoldTheoMoney, 
    					double Bill_F, double Coins_F, double Jackpot_F, double HopperFill_F, double MalFunction_F, double OverPaid, 
    					double Progressive, double WLoose_F, double VarianzaBill, double VarianzaJP, double Varianza) {
    	this.AnioMes = AnioMes;
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
    	this.WinLoose = WinLoose;
    	this.Courtesy = Courtesy;
    	this.PercBill = PercBill;
    	this.AVGBill = AVGBill;
    	this.AVGWinLoose = AVGWinLoose;
    	this.AVGCourtesy = AVGCourtesy;
    	this.HoldPerc = HoldPerc;
    	this.TheoricalP = TheoricalP;
    	this.DiffPercent = DiffPercent;
    	this.HoldTheoMoney = HoldTheoMoney;
    	this.Bill_F = Bill_F;
    	this.Coins_F = Coins_F;
    	this.Jackpot_F = Jackpot_F;
    	this.HopperFill_F = HopperFill_F;
    	this.MalFunction_F = MalFunction_F;
    	this.OverPaid = OverPaid;
    	this.Progressive = Progressive;
    	this.WLoose_F = WLoose_F;
    	this.VarianzaBill = VarianzaBill;
    	this.VarianzaJP = VarianzaJP;
    	this.Varianza = Varianza;
    }

    public Projection() {
    	
    }

	public void setAnioMes(String anioMes) {
		AnioMes = anioMes;
	}

	public String getAnioMes() {
		return AnioMes;
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

	public void setWinLoose(double winLoose) {
		WinLoose = winLoose;
	}

	public double getWinLoose() {
		return WinLoose;
	}

	public void setCourtesy(double courtesy) {
		Courtesy = courtesy;
	}

	public double getCourtesy() {
		return Courtesy;
	}

	public void setPercBill(double percBill) {
		PercBill = percBill;
	}

	public double getPercBill() {
		return PercBill;
	}

	public void setAVGBill(double aVGBill) {
		AVGBill = aVGBill;
	}

	public double getAVGBill() {
		return AVGBill;
	}

	public void setAVGWinLoose(double aVGWinLoose) {
		AVGWinLoose = aVGWinLoose;
	}

	public double getAVGWinLoose() {
		return AVGWinLoose;
	}

	public void setAVGCourtesy(double aVGCourtesy) {
		AVGCourtesy = aVGCourtesy;
	}

	public double getAVGCourtesy() {
		return AVGCourtesy;
	}

	public void setHoldPerc(double holdPerc) {
		HoldPerc = holdPerc;
	}

	public double getHoldPerc() {
		return HoldPerc;
	}

	public void setTheoricalP(double theoricalP) {
		TheoricalP = theoricalP;
	}

	public double getTheoricalP() {
		return TheoricalP;
	}

	public void setDiffPercent(double diffPercent) {
		DiffPercent = diffPercent;
	}

	public double getDiffPercent() {
		return DiffPercent;
	}

	public void setHoldTheoMoney(double holdTheoMoney) {
		HoldTheoMoney = holdTheoMoney;
	}

	public double getHoldTheoMoney() {
		return HoldTheoMoney;
	}

	public void setBill_F(double bill_F) {
		Bill_F = bill_F;
	}

	public double getBill_F() {
		return Bill_F;
	}

	public void setCoins_F(double coins_F) {
		Coins_F = coins_F;
	}

	public double getCoins_F() {
		return Coins_F;
	}

	public void setJackpot_F(double jackpot_F) {
		Jackpot_F = jackpot_F;
	}

	public double getJackpot_F() {
		return Jackpot_F;
	}

	public void setHopperFill_F(double hopperFill_F) {
		HopperFill_F = hopperFill_F;
	}

	public double getHopperFill_F() {
		return HopperFill_F;
	}

	public void setMalFunction_F(double malFunction_F) {
		MalFunction_F = malFunction_F;
	}

	public double getMalFunction_F() {
		return MalFunction_F;
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

	public void setWLoose_F(double wLoose_F) {
		WLoose_F = wLoose_F;
	}

	public double getWLoose_F() {
		return WLoose_F;
	}

	public void setVarianzaBill(double varianzaBill) {
		VarianzaBill = varianzaBill;
	}

	public double getVarianzaBill() {
		return VarianzaBill;
	}

	public void setVarianzaJP(double varianzaJP) {
		VarianzaJP = varianzaJP;
	}

	public double getVarianzaJP() {
		return VarianzaJP;
	}

	public void setVarianza(double varianza) {
		Varianza = varianza;
	}

	public double getVarianza() {
		return Varianza;
	}
    

}