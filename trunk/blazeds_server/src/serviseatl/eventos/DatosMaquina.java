package serviseatl.eventos;

import java.io.Serializable;

public class DatosMaquina implements Serializable {

    static final long serialVersionUID = 109232314421002002L;
    
    private int	LocationID;
    private int CodCasino;
    private String CodMaquina;
    private int NumMaquina;
    private String LCountry;
    private String NCasino;
    private String TipoPago;
    private String Cabinet;
    private String NFabricante;
    private String Modelo; 
    private String NameGame;
    private String GameType;
    private String DenominationL;

    

    public DatosMaquina() {
    	
    }

    public DatosMaquina( int LocationID, int CodCasino, String CodMaquina, int NumMaquina, String LCountry,
    String NCasino, String TipoPago, String Cabinet, String NFabricante, String Modelo, String NameGame, String GameType, String DenominationL)    
    {
    	this.LocationID = LocationID;
    	this.CodCasino=	CodCasino;
    	this.CodMaquina=	CodMaquina;
    	this.NumMaquina=	NumMaquina;
    	this.LCountry=	LCountry;
    	this.NCasino=	NCasino;
    	this.TipoPago=	TipoPago;
    	this.Cabinet=	Cabinet;
    	this.NFabricante=	NFabricante;
    	this.Modelo= 	Modelo; 
    	this.NameGame=	NameGame;
    	this.GameType=	GameType;
    	this.DenominationL=	DenominationL;

	}

	public void setLocationID(int locationID) {
		LocationID = locationID;
	}

	public int getLocationID() {
		return LocationID;
	}

	public void setCodCasino(int codCasino) {
		CodCasino = codCasino;
	}

	public int getCodCasino() {
		return CodCasino;
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

	public void setLCountry(String lCountry) {
		LCountry = lCountry;
	}

	public String getLCountry() {
		return LCountry;
	}

	public void setNCasino(String nCasino) {
		NCasino = nCasino;
	}

	public String getNCasino() {
		return NCasino;
	}

	public void setTipoPago(String tipoPago) {
		TipoPago = tipoPago;
	}

	public String getTipoPago() {
		return TipoPago;
	}

	public void setCabinet(String cabinet) {
		Cabinet = cabinet;
	}

	public String getCabinet() {
		return Cabinet;
	}

	public void setNFabricante(String nFabricante) {
		NFabricante = nFabricante;
	}

	public String getNFabricante() {
		return NFabricante;
	}

	public void setModelo(String modelo) {
		Modelo = modelo;
	}

	public String getModelo() {
		return Modelo;
	}

	public void setNameGame(String nameGame) {
		NameGame = nameGame;
	}

	public String getNameGame() {
		return NameGame;
	}

	public void setGameType(String gameType) {
		GameType = gameType;
	}

	public String getGameType() {
		return GameType;
	}

	public void setDenominationL(String denominationL) {
		DenominationL = denominationL;
	}

	public String getDenominationL() {
		return DenominationL;
	}

}