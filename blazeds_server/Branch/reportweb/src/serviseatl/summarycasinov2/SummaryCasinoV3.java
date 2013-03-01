package serviseatl.summarycasinov2;

import java.io.Serializable;

public class SummaryCasinoV3 implements Serializable {
    

    /**
	 * 
	 */
	private static final long serialVersionUID = -200588884721597519L;
	private String aamsLocationId;
    private String aamsLocationName;
    private String cciudad;
    private String esercenteName;
    private String gestoreName;
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
    private double MeterEftAft;	
    private double Progressive;
    private double Courtesy;
    private double Complementary;
    private double ValuePointRedeem;    
    private double PointsRedeem;
    private double TotalPoints;
    private double TotalPromo;
    private double NetWinLose;
    private double PercBill;
    private double Bet;
    private double Raffle;
    
    //***************** Nuevos campos para italia
    
   private int NumberVLTs;
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
   private int WorkingDays;
   private double AbsVlts;
   private double AbsVltsPerDay;
   private double AvgCreditsWagPerDayPerVLT;
   private String OpenDate;
   private int VltsZeroes;
   private double RatioVltsZeroes;
    
    public SummaryCasinoV3() {
    	
    }
    
    public SummaryCasinoV3(String aamsLocationId, String aamsLocationName, String cciudad, String esercenteName, String gestoreName, 
    		double MeterIn, double MeterOut, double MeterDrop, int MeterGames,
    	    double MeterJackPot, double MeterVoucherOut, double MeterHandPay, double SumJPHF, double MeterVoucherIn,
    	    double MeterBill, double WinLoose, double MeterEftAft, double Progressive, double Courtesy, double Complementary,    
    	    double ValuePointRedeem, double PointsRedeem, double TotalPoints, double TotalPromo, double NetWinLose, double PercBill,
    	    double Bet, double Raffle, int NumberVLTs, double PercentGestore, double PercentSupplier, double RTP, double PREU,
    		double AMMS05p, double AMMS03p, double PREUAMMSfee, double TotalHouseWin, double RetQuotaGestore, double RetQuotaSupplier, 
    		double QuotaHWBPlus, double AvgNWPerMachine, double AVGVlts, int WorkingDays, double AbsVlts, double AbsVltsPerDay, 
    		double AvgCreditsWagPerDayPerVLT, String OpenDate, int VltsZeroes, double RatioVltsZeroes)    
    {
		this.aamsLocationId = aamsLocationId;		
    	this.aamsLocationName = aamsLocationName;
		this.cciudad= cciudad;
		this.esercenteName = esercenteName;
		this.gestoreName = gestoreName;
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
		this.MeterEftAft = MeterEftAft;
		this.Progressive = Progressive;
		this.Courtesy = Courtesy;
		this.Complementary = Complementary;    
		this.ValuePointRedeem = ValuePointRedeem;
		this.PointsRedeem = PointsRedeem;
		this.TotalPoints = TotalPoints;
		this.TotalPromo = TotalPromo;
		this.NetWinLose = NetWinLose;
		this.PercBill = PercBill;
		this.Bet = Bet;    
		this.Raffle = Raffle;

		this.NumberVLTs =NumberVLTs;
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
		this.OpenDate = OpenDate;
		this.VltsZeroes = VltsZeroes;
		this.RatioVltsZeroes = RatioVltsZeroes;
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
	public double getSumJPHF() {
		return SumJPHF;
	}
	public void setSumJPHF(double SumJPHF) {
		this.SumJPHF = SumJPHF;
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
	public double getWinLoose() {
		return WinLoose;
	}
	public void setWinLoose(double WinLoose) {
		this.WinLoose = WinLoose;
	}
	public double getMeterEftAft() {
		return MeterEftAft;
	}
	public void setMeterEftAft(double MeterEftAft) {
		this.MeterEftAft = MeterEftAft;
	}
	public double getProgressive() {
		return Progressive;
	}
	public void setProgressive(double Progressive) {
		this.Progressive = Progressive;
	}
	public double getCourtesy() {
		return Courtesy;
	}
	public void setCourtesy(double Courtesy) {
		this.Courtesy = Courtesy;
	}
	public double getComplementary() {
		return Complementary;
	}
	public void setComplementary(double Complementary) {
		this.Complementary = Complementary;
	}
	public double getValuePointRedeem() {
		return ValuePointRedeem;
	}
	public void setValuePointRedeem(double ValuePointRedeem) {
		this.ValuePointRedeem = ValuePointRedeem;
	}
	public double getPointsRedeem() {
		return PointsRedeem;
	}
	public void setPointsRedeem(double PointsRedeem) {
		this.PointsRedeem = PointsRedeem;
	}
	public double getTotalPoints() {
		return TotalPoints;
	}
	public void setTotalPoints(double TotalPoints) {
		this.TotalPoints = TotalPoints;
	}
	public double getTotalPromo() {
		return TotalPromo;
	}
	public void setTotalPromo(double TotalPromo) {
		this.TotalPromo = TotalPromo;
	}
	public double getNetWinLose() {
		return NetWinLose;
	}
	public void setNetWinLose(double NetWinLose) {
		this.NetWinLose = NetWinLose;
	}
	public double getPercBill() {
		return PercBill;
	}
	public void setPercBill(double PercBill) {
		this.PercBill = PercBill;
	}
	public double getBet() {
		return Bet;
	}
	public void setBet(double Bet) {
		this.Bet = Bet;
	}

	public double getRaffle() {
		return Raffle;
	}
	public void setRaffle(double Raffle) {
		this.Raffle = Raffle;
	}
	public void setNumberVLTs(int numberVLTs) {
		NumberVLTs = numberVLTs;
	}
	public int getNumberVLTs() {
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

	public void setWorkingDays(int workingDays) {
		WorkingDays = workingDays;
	}

	public int getWorkingDays() {
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

	public void setOpenDate(String openDate) {
		OpenDate = openDate;
	}

	public String getOpenDate() {
		return OpenDate;
	}

	
	public void setVltsZeroes(int vltsZeroes) {
		VltsZeroes = vltsZeroes;
	}

	public int getVltsZeroes() {
		return VltsZeroes;
	}
	
	public void setRatioVltsZeroes(double ratioVltsZeroes) {
		RatioVltsZeroes = ratioVltsZeroes;
	}

	public double getRatioVltsZeroes() {
		return RatioVltsZeroes;
	}

	public String getAamsLocationId() {
		return aamsLocationId;
	}

	public void setAamsLocationId(String aamsLocationId) {
		this.aamsLocationId = aamsLocationId;
	}

	public String getAamsLocationName() {
		return aamsLocationName;
	}

	public void setAamsLocationName(String aamsLocationName) {
		this.aamsLocationName = aamsLocationName;
	}

	public String getCciudad() {
		return cciudad;
	}

	public void setCciudad(String cciudad) {
		this.cciudad = cciudad;
	}

	public String getEsercenteName() {
		return esercenteName;
	}

	public void setEsercenteName(String esercenteName) {
		this.esercenteName = esercenteName;
	}

	public String getGestoreName() {
		return gestoreName;
	}

	public void setGestoreName(String gestoreName) {
		this.gestoreName = gestoreName;
	}

}
