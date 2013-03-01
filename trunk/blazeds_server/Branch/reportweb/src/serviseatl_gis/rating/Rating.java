package serviseatl_gis.rating;

import java.io.Serializable;

public class Rating implements Serializable {
    static final long serialVersionUID = 104844666002332701L;
    
    private String CCasino;
    private String IFecha;
    private String FFecha;
    private int FechaY;
    private int FechaM;
    private String FechaMTx;

    private int Machine;
    private String NameGame;
    private String NFabricante;

    private double MeterIn;
    private double MeterOut;
    private double MeterDrop;
    private int MeterGames;
    private double MeterJackpot;
    private double MeterHandPay;
    private double MeterVoucherOut;
    private double MeterVoucherIn;
    private double MeterBill;
    private double MeterEftAft;
    private double MeterWinLose;
    private String Tiempo;
    private int Points;
    private int CourtesyPoints;
    
    public Rating() {
    	
    }

    public Rating(String CCasino,
    	     String IFecha,
    	     String FFecha,
    	     int FechaY,
    	     int FechaM,
    	     String FechaMTx,

    	     int Machine,
    	     String NameGame,
    	     String NFabricante,

    	     double MeterIn,
    	     double MeterOut,
    	     double MeterDrop,
    	     int MeterGames,
    	     double MeterJackpot,
    	     double MeterHandPay,
    	     double MeterVoucherOut,
    	     double MeterVoucherIn,
    	     double MeterBill,
    	     double MeterEftAft,
    	     double MeterWinLose,
    	     String Tiempo,
    	     int Points,
    	     int CourtesyPoints)    
    {
        this.CCasino = CCasino;
        this.IFecha = IFecha;
        this.FFecha = FFecha;
        this.FechaY = FechaY;
        this.FechaM = FechaM;
        this.FechaMTx = FechaMTx;

        this.Machine = Machine;
        this.NameGame = NameGame;
        this.NFabricante = NFabricante;

        this.MeterIn = MeterIn;
        this.MeterOut = MeterOut;
        this.MeterDrop = MeterDrop;
        this.MeterGames = MeterGames;
        this.MeterJackpot = MeterJackpot;
        this.MeterHandPay = MeterHandPay;
        this.MeterVoucherOut = MeterVoucherOut;
        this.MeterVoucherIn = MeterVoucherIn;
        this.MeterBill = MeterBill;
        this.MeterEftAft = MeterEftAft;
        this.MeterWinLose = MeterWinLose;
        this.Tiempo = Tiempo;
        this.Points = Points;
        this.CourtesyPoints = CourtesyPoints;
     }

	public void setCCasino(String cCasino) {
		CCasino = cCasino;
	}

	public String getCCasino() {
		return CCasino;
	}

	public void setIFecha(String iFecha) {
		IFecha = iFecha;
	}

	public String getIFecha() {
		return IFecha;
	}

	public void setFFecha(String fFecha) {
		FFecha = fFecha;
	}

	public String getFFecha() {
		return FFecha;
	}

	public void setFechaY(int fechaY) {
		FechaY = fechaY;
	}

	public int getFechaY() {
		return FechaY;
	}

	public void setFechaM(int fechaM) {
		FechaM = fechaM;
	}

	public int getFechaM() {
		return FechaM;
	}

	public void setFechaMTx(String fechaMTx) {
		FechaMTx = fechaMTx;
	}

	public String getFechaMTx() {
		return FechaMTx;
	}

	public void setMachine(int machine) {
		Machine = machine;
	}

	public int getMachine() {
		return Machine;
	}

	public void setNameGame(String nameGame) {
		NameGame = nameGame;
	}

	public String getNameGame() {
		return NameGame;
	}

	public void setNFabricante(String nFabricante) {
		NFabricante = nFabricante;
	}

	public String getNFabricante() {
		return NFabricante;
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

	public void setMeterJackpot(double meterJackpot) {
		MeterJackpot = meterJackpot;
	}

	public double getMeterJackpot() {
		return MeterJackpot;
	}

	public void setMeterHandPay(double meterHandPay) {
		MeterHandPay = meterHandPay;
	}

	public double getMeterHandPay() {
		return MeterHandPay;
	}

	public void setMeterVoucherOut(double meterVoucherOut) {
		MeterVoucherOut = meterVoucherOut;
	}

	public double getMeterVoucherOut() {
		return MeterVoucherOut;
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

	public void setMeterEftAft(double meterEftAft) {
		MeterEftAft = meterEftAft;
	}

	public double getMeterEftAft() {
		return MeterEftAft;
	}

	public void setMeterWinLose(double meterWinLose) {
		MeterWinLose = meterWinLose;
	}

	public double getMeterWinLose() {
		return MeterWinLose;
	}

	public void setTiempo(String tiempo) {
		Tiempo = tiempo;
	}

	public String getTiempo() {
		return Tiempo;
	}

	public void setPoints(int points) {
		Points = points;
	}

	public int getPoints() {
		return Points;
	}

	public void setCourtesyPoints(int courtesyPoints) {
		CourtesyPoints = courtesyPoints;
	}

	public int getCourtesyPoints() {
		return CourtesyPoints;
	}


}
