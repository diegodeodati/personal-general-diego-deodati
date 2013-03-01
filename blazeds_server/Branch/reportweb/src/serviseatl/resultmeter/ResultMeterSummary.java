package serviseatl.resultmeter;

import java.io.Serializable;

public class ResultMeterSummary implements Serializable {

    static final long serialVersionUID = 103844514777705502L;
    
    private String CodMaquina;
    private int NumMaquina;
    private double MeterIn;
    private double MeterOut;
    private double MeterDrop;
    private int MeterGames;
    private double MeterJackPot;
    private double MeterVoucherOut;
    private double MeterHandPay;
    private double SumJPHF;
    private double MeterVoucherIn;
    private double MeterBill;
    private double WinLoose;
    private double MeterEFTAFT;
    private double Bet;
    private double NumberVLTs;             
    private double PercentGestore;         
    private double PercentSupplier;
    private double RTP;
    private double PREU;
    private double AMMS05p;
    private double AMMS03p;
    private double PREUAMMSfee;
    private double TotalHouseWin;
    private double RetQuotaGestore;
    private double RetQuotaSupplier;
    private double QuotaHWBPlus;
    private double AvgNWPerMachine;
    private double AVGVlts;
    private double WorkingDays;
    private double AbsVlts;
    private double AbsVltsPerDay;
    private double AvgCreditsWagPerDayPerVLT;
    private String	cabinet;
         
    public ResultMeterSummary() {
    	
    }
    
    public ResultMeterSummary(
    	    String CodMaquina,
    	    int NumMaquina,
    	    double MeterIn,
    	    double MeterOut,
    	    double MeterDrop,
    	    int MeterGames,
    	    double MeterJackPot,
    	    double MeterVoucherOut,
    	    double MeterHandPay,
    	    double SumJPHF,
    	    double MeterVoucherIn,
    	    double MeterBill,
    	    double WinLoose,
    	    double MeterEFTAFT,
    	    double Bet,
    	    double NumberVLTs,             
    	    double PercentGestore,         
    	    double PercentSupplier,
    	    double RTP,
    	    double PREU,
    	    double AMMS05p,
    	    double AMMS03p,
    	    double PREUAMMSfee,
    	    double TotalHouseWin,
    	    double RetQuotaGestore,
    	    double RetQuotaSupplier,
    	    double QuotaHWBPlus,
    	    double AvgNWPerMachine,
    	    double AVGVlts,
    	    double WorkingDays,
    	    double AbsVlts,
    	    double AbsVltsPerDay,
    	    double AvgCreditsWagPerDayPerVLT,
    	    String cabinet)    
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
        this.SumJPHF = SumJPHF;
        this.MeterVoucherIn = MeterVoucherIn;
        this.MeterBill = MeterBill;
        this.WinLoose = WinLoose;
        this.MeterEFTAFT = MeterEFTAFT;
        this.Bet = Bet;
        this.NumberVLTs = NumberVLTs;             
        this.PercentGestore = PercentGestore;         
        this.PercentSupplier = PercentSupplier;
        this.RTP = RTP;
        this.PREU = PREU;
        this.AMMS05p = AMMS05p;
        this.AMMS03p = AMMS03p;
        this.PREUAMMSfee = PREUAMMSfee;
        this.TotalHouseWin = TotalHouseWin;
        this.RetQuotaGestore = RetQuotaGestore;
        this.RetQuotaSupplier = RetQuotaSupplier;
        this.QuotaHWBPlus = QuotaHWBPlus;
        this.AvgNWPerMachine = AvgNWPerMachine;
        this.AVGVlts = AVGVlts;
        this.WorkingDays = WorkingDays;
        this.AbsVlts = AbsVlts;
        this.AbsVltsPerDay = AbsVltsPerDay;
        this.AvgCreditsWagPerDayPerVLT = AvgCreditsWagPerDayPerVLT;
        this.cabinet = cabinet; 
	}

	public void setCodMaquina(String codMaquina) {
		CodMaquina = codMaquina;
	}

	public String getCodMaquina() {
		return CodMaquina;
	}

	public void setNumMaquina(int numMaquina) {
		NumMaquina = numMaquina;
	}

	public int getNumMaquina() {
		return NumMaquina;
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

	public void setMeterVoucherOut(double meterVoucherOut) {
		MeterVoucherOut = meterVoucherOut;
	}

	public double getMeterVoucherOut() {
		return MeterVoucherOut;
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

	public void setMeterEFTAFT(double meterEFTAFT) {
		MeterEFTAFT = meterEFTAFT;
	}

	public double getMeterEFTAFT() {
		return MeterEFTAFT;
	}

	public void setBet(double bet) {
		Bet = bet;
	}

	public double getBet() {
		return Bet;
	}

	public void setNumberVLTs(double numberVLTs) {
		NumberVLTs = numberVLTs;
	}

	public double getNumberVLTs() {
		return NumberVLTs;
	}

	public void setPercentGestore(double percentGestore) {
		PercentGestore = percentGestore;
	}

	public double getPercentGestore() {
		return PercentGestore;
	}

	public void setPercentSupplier(double percentSupplier) {
		PercentSupplier = percentSupplier;
	}

	public double getPercentSupplier() {
		return PercentSupplier;
	}

	public void setRTP(double rTP) {
		RTP = rTP;
	}

	public double getRTP() {
		return RTP;
	}

	public void setPREU(double pREU) {
		PREU = pREU;
	}

	public double getPREU() {
		return PREU;
	}

	public void setAMMS05p(double aMMS05p) {
		AMMS05p = aMMS05p;
	}

	public double getAMMS05p() {
		return AMMS05p;
	}

	public void setAMMS03p(double aMMS03p) {
		AMMS03p = aMMS03p;
	}

	public double getAMMS03p() {
		return AMMS03p;
	}

	public void setPREUAMMSfee(double pREUAMMSfee) {
		PREUAMMSfee = pREUAMMSfee;
	}

	public double getPREUAMMSfee() {
		return PREUAMMSfee;
	}

	public void setTotalHouseWin(double totalHouseWin) {
		TotalHouseWin = totalHouseWin;
	}

	public double getTotalHouseWin() {
		return TotalHouseWin;
	}

	public void setRetQuotaGestore(double retQuotaGestore) {
		RetQuotaGestore = retQuotaGestore;
	}

	public double getRetQuotaGestore() {
		return RetQuotaGestore;
	}

	public void setRetQuotaSupplier(double retQuotaSupplier) {
		RetQuotaSupplier = retQuotaSupplier;
	}

	public double getRetQuotaSupplier() {
		return RetQuotaSupplier;
	}

	public void setQuotaHWBPlus(double quotaHWBPlus) {
		QuotaHWBPlus = quotaHWBPlus;
	}

	public double getQuotaHWBPlus() {
		return QuotaHWBPlus;
	}

	public void setAvgNWPerMachine(double avgNWPerMachine) {
		AvgNWPerMachine = avgNWPerMachine;
	}

	public double getAvgNWPerMachine() {
		return AvgNWPerMachine;
	}

	public void setAVGVlts(double aVGVlts) {
		AVGVlts = aVGVlts;
	}

	public double getAVGVlts() {
		return AVGVlts;
	}

	public void setWorkingDays(double workingDays) {
		WorkingDays = workingDays;
	}

	public double getWorkingDays() {
		return WorkingDays;
	}

	public void setAbsVlts(double absVlts) {
		AbsVlts = absVlts;
	}

	public double getAbsVlts() {
		return AbsVlts;
	}

	public void setAbsVltsPerDay(double absVltsPerDay) {
		AbsVltsPerDay = absVltsPerDay;
	}

	public double getAbsVltsPerDay() {
		return AbsVltsPerDay;
	}

	public void setAvgCreditsWagPerDayPerVLT(double avgCreditsWagPerDayPerVLT) {
		AvgCreditsWagPerDayPerVLT = avgCreditsWagPerDayPerVLT;
	}

	public double getAvgCreditsWagPerDayPerVLT() {
		return AvgCreditsWagPerDayPerVLT;
	}

	public String getCabinet() {
		return cabinet;
	}

	public void setCabinet(String cabinet) {
		this.cabinet = cabinet;
	}

}