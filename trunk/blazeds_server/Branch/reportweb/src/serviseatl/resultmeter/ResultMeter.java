package serviseatl.resultmeter;

import java.io.Serializable;

public class ResultMeter implements Serializable {

    static final long serialVersionUID = 103844514001000002L;
    
    private String CodMaquina;
    private int NumMaquina;
    private String NameGame;
    private String NFabricante;
    private double Denominacion;
    private int CoinAccept;
    private String TipoPago;
    private String Modelo;
    private String SimboloM;
    private double MeterIn;
    private double MeterOut;
    private double MeterDrop;
    private int MeterGames;
    private double MeterJackPot;
    private double VoucherOut;
    private double MeterHandPay;
    private double SumJPHF;
    private double EFTAFT;
    private double VoucherIn;
    private double MeterBill;
    private double WinLoose;
    private double HoldPerc;
    private double TheoricalP;
    private double DiffPercent;
    private double HoldTheoMoney;    
    private double Bill_F;
    private double Coins_F;
    private double JackPot_F;
    private double HopperFill_F;
    private double MalFunction_F;
    private double OverPaid;
    private double Progressive;    
    private double WLoose_F;    
    private double VarianzaBill;    
    private double VarianzaJP;    
    private double Varianza;    
    private double CoinsForm;    
    
    public ResultMeter() {
    	
    }
    
    public ResultMeter(String CodMaquina, int NumMaquina, String NameGame, String NFabricante, double Denominacion, int CoinAccept, String TipoPago,
    	    String Modelo, String SimboloM, double MeterIn, double MeterOut, double MeterDrop, int MeterGames,
    	    double MeterJackPot, double VoucherOut, double MeterHandPay, double SumJPHF, double EFTAFT, double VoucherIn,
    	    double MeterBill, double WinLoose, double HoldPerc, double TheoricalP, double DiffPercent, double HoldTheoMoney,    
    	    double Bill_F, double Coins_F, double JackPot_F, double HopperFill_F, double MalFunction_F, double OverPaid,
    	    double Progressive, double WLoose_F, double VarianzaBill, double VarianzaJP, double Varianza, double CoinsForm)    
 
    {
		this.CodMaquina = CodMaquina;
		this.NumMaquina = NumMaquina;
		this.NameGame = NameGame;
		this.NFabricante = NFabricante;
		this.Denominacion = Denominacion;
		this.CoinAccept = CoinAccept;
		this.TipoPago = TipoPago;
		this.Modelo = Modelo;
		this.SimboloM = SimboloM;
		this.MeterIn = MeterIn;
		this.MeterOut = MeterOut;
		this.MeterDrop = MeterDrop;
		this.MeterGames = MeterGames;
		this.MeterJackPot = MeterJackPot;
		this.VoucherOut = VoucherOut;
		this.MeterHandPay = MeterHandPay;
		this.SumJPHF = SumJPHF;
		this.EFTAFT = EFTAFT;
		this.VoucherIn = VoucherIn;
		this.MeterBill = MeterBill;
		this.WinLoose = WinLoose;
		this.HoldPerc = HoldPerc;
		this.TheoricalP = TheoricalP;
		this.DiffPercent = DiffPercent;
		this.HoldTheoMoney = HoldTheoMoney;    
		this.Bill_F = Bill_F;
		this.Coins_F = Coins_F;
		this.JackPot_F = JackPot_F;
		this.HopperFill_F = HopperFill_F;
		this.MalFunction_F = MalFunction_F;
		this.OverPaid = OverPaid;
		this.Progressive = Progressive;    
		this.WLoose_F = WLoose_F;    
		this.VarianzaBill = VarianzaBill;    
		this.VarianzaJP = VarianzaJP;    
		this.Varianza = Varianza;    
		this.CoinsForm = CoinsForm;    
		
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
	public String getNameGame() {
		return NameGame;
	}
	public void setNameGame(String NameGame) {
		this.NameGame = NameGame;
	}
	public String getNFabricante() {
		return NFabricante;
	}
	public void setNFabricante(String NFabricante) {
		this.NFabricante = NFabricante;
	}
	public double getDenominacion() {
		return Denominacion;
	}
	public void setDenominacion(double Denominacion) {
		this.Denominacion = Denominacion;
	}
	public int getCoinAccept() {
		return CoinAccept;
	}
	public void setCoinAccept(int CoinAccept) {
		this.CoinAccept = CoinAccept;
	}
	public String getTipoPago() {
		return TipoPago;
	}
	public void setTipoPago(String TipoPago) {
		this.TipoPago = TipoPago;
	}
	public String getModelo() {
		return Modelo;
	}
	public void setModelo(String Modelo) {
		this.Modelo = Modelo;
	}
	public String getSimboloM() {
		return SimboloM;
	}
	public void setSimboloM(String SimbolM) {
		this.SimboloM = SimbolM;
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
	public double getVoucherOut() {
		return VoucherOut;
	}
	public void setVoucherOut(double VoucherOut) {
		this.VoucherOut = VoucherOut;
	}
	public double getMeterHandPay() {
		return MeterHandPay;
	}
	public void setMeterHandPay(double MeterHandPay) {
		this.MeterHandPay = MeterHandPay;
	}
	public double getSumJPHF() {
		return SumJPHF;
	}
	public void setSumJPHF(double SumJPHF) {
		this.SumJPHF = SumJPHF;
	}
	public double getEFTAFT() {
		return EFTAFT;
	}
	public void setEFTAFT(double EFTAFT) {
		this.EFTAFT = EFTAFT;
	}
	public double getVoucherIn() {
		return VoucherIn;
	}
	public void setVoucherIn(double VoucherIn) {
		this.VoucherIn = VoucherIn;
	}
	public double getMeterBill() {
		return MeterBill;
	}
	public void setMeterBill(double MeterBill ) {
		this.MeterBill = MeterBill;
	}
	public double getWinLoose() {
		return WinLoose;
	}
	public void setWinLoose(double WinLoose) {
		this.WinLoose = WinLoose;
	}
	public double getHoldPerc() {
		return HoldPerc;
	}
	public void setHoldPerc(double HoldPerc) {
		this.HoldPerc = HoldPerc;
	}
	public double getTheoricalP() {
		return TheoricalP;
	}
	public void setTheoricalP(double TheoricalP) {
		this.TheoricalP = TheoricalP;
	}
	public double getDiffPercent() {
		return DiffPercent;
	}
	public void setDiffPercent(double DiffPercent) {
		this.DiffPercent = DiffPercent;
	}
	public double getHoldTheoMoney() {
		return HoldTheoMoney;
	}
	public void setHoldTheoMoney(double HoldTheoMoney) {
		this.HoldTheoMoney = HoldTheoMoney;
	}
	public double getBill_F() {
		return Bill_F;
	}
	public void setBill_F(double Bill_F) {
		this.Bill_F = Bill_F;
	}
	public double getCoins_F() {
		return Coins_F;
	}
	public void setCoins_F(double Coins_F) {
		this.Coins_F = Coins_F;
	}
	public double getJackPot_F() {
		return JackPot_F;
	}
	public void setJackPot_F(double JackPot_F) {
		this.JackPot_F = JackPot_F;
	}
	public double getHopperFill_F() {
		return HopperFill_F;
	}
	public void setHopperFill_F(double HopperFill_F) {
		this.HopperFill_F = HopperFill_F;
	}
	public double getMalFunction_F() {
		return MalFunction_F;
	}
	public void setMalFunction_F(double MalFunction_F) {
		this.MalFunction_F = MalFunction_F;
	}
	public double getOverPaid() {
		return OverPaid;
	}
	public void setOverPaid(double OverPaid) {
		this.OverPaid = OverPaid;
	}
	public double getProgressive() {
		return Progressive;
	}
	public void setProgressive(double Progressive) {
		this.Progressive = Progressive;
	}
	public double getWLoose_F() {
		return WLoose_F;
	}
	public void setWLoose_F(double WLoose_F) {
		this.WLoose_F = WLoose_F;
	}
	public double getVarianzaBill() {
		return VarianzaBill;
	}
	public void setVarianzaBill(double VarianzaBill) {
		this.VarianzaBill = VarianzaBill;
	}
	public double getVarianzaJP() {
		return VarianzaJP;
	}
	public void setVarianzaJP(double VarianzaJP) {
		this.VarianzaJP = VarianzaJP;
	}
	public double getVarianza() {
		return Varianza;
	}
	public void setVarianza(double Varianza) {
		this.Varianza = Varianza;
	}
	public double getCoinsForm() {
		return CoinsForm;
	}
	public void setCoinsForm(double CoinsForm) {
		this.CoinsForm = CoinsForm;
	}

}