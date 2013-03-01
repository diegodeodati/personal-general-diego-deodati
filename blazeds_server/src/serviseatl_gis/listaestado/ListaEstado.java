package serviseatl_gis.listaestado;

import java.io.Serializable;

public class ListaEstado  implements Serializable {
    static final long serialVersionUID = 101849617903090001L;
    private String CodMaquina;
    private double DenominacionM;
    private int NumMaquina;
    private String NameGame;
    private String NFabricante;
    private String TipoPago;
    private double X;
    private double Y;
    private double Alfa;
    private double XC;
    private double YC;
    private Boolean EnLinea;
    private int Cliente;
    private int CustomerID;
    private String NCustomer;
    private int CodFabricante;

    
    public ListaEstado() {
    	
    }
    
    public ListaEstado(String CodMaquina, double DenominacionM, int NumMaquina, String NameGame, String NFabricante,  
    		String TipoPago, double X, double Y, double Alfa, double XC, double YC, Boolean EnLinea, int Cliente,
    		int CustomerID, String NCustomer, int CodFabricante)    
 
    {
		this.CodMaquina = CodMaquina;
		this.DenominacionM = DenominacionM;
		this.NumMaquina = NumMaquina;
		this.NameGame = NameGame;
		this.NFabricante = NFabricante;
		this.TipoPago = TipoPago;
		this.X = X;
		this.Y = Y;
		this.Alfa = Alfa;
		this.XC = XC;
		this.YC = YC;		
		this.EnLinea = EnLinea;
		this.Cliente = Cliente;
		this.CustomerID = CustomerID;
		this.NCustomer = NCustomer;
		this.CodFabricante = CodFabricante;
	}

	public void setCodMaquina(String codMaquina) {
		CodMaquina = codMaquina;
	}

	public String getCodMaquina() {
		return CodMaquina;
	}

	public void setDenominacionM(double denominacionM) {
		DenominacionM = denominacionM;
	}

	public double getDenominacionM() {
		return DenominacionM;
	}

	public void setNumMaquina(int numMaquina) {
		NumMaquina = numMaquina;
	}

	public int getNumMaquina() {
		return NumMaquina;
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

	public void setTipoPago(String tipoPago) {
		TipoPago = tipoPago;
	}

	public String getTipoPago() {
		return TipoPago;
	}

	public void setX(double x) {
		X = x;
	}

	public double getX() {
		return X;
	}

	public void setY(double y) {
		Y = y;
	}

	public double getY() {
		return Y;
	}

	public void setAlfa(double alfa) {
		Alfa = alfa;
	}

	public double getAlfa() {
		return Alfa;
	}

	public void setXC(double xC) {
		XC = xC;
	}

	public double getXC() {
		return XC;
	}

	public void setYC(double yC) {
		YC = yC;
	}

	public double getYC() {
		return YC;
	}

	public void setEnLinea(Boolean enLinea) {
		EnLinea = enLinea;
	}

	public Boolean getEnLinea() {
		return EnLinea;
	}

	public void setCliente(int cliente) {
		Cliente = cliente;
	}

	public int getCliente() {
		return Cliente;
	}

	public void setCustomerID(int customerID) {
		CustomerID = customerID;
	}

	public int getCustomerID() {
		return CustomerID;
	}

	public void setNCustomer(String nCustomer) {
		NCustomer = nCustomer;
	}

	public String getNCustomer() {
		return NCustomer;
	}

	public void setCodFabricante(int codFabricante) {
		CodFabricante = codFabricante;
	}

	public int getCodFabricante() {
		return CodFabricante;
	}


}

