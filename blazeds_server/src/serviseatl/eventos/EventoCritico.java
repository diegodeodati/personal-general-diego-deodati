package serviseatl.eventos;

import java.io.Serializable;

public class EventoCritico implements Serializable {

    static final long serialVersionUID = 109533647471070072L;
    
    private String CodMaquina;
    private int NumMaquina;
    private String TipoPago;
    private String NFabricante;
    private String Modelo;
    private String NameGame;
    private int EventoId;
    private String Evento;
    private int Cantidad;

    public EventoCritico() {
    	
    }

    public EventoCritico( String CodMaquina, int NumMaquina, String TipoPago, String NFabricante, String Modelo, String NameGame, int EventoId, 
    		String Evento, int Cantidad)    
    {
    	this.CodMaquina = CodMaquina;
    	this.NumMaquina = NumMaquina;
    	this.TipoPago = TipoPago;
    	this.NFabricante = NFabricante;
    	this.Modelo = Modelo;
    	this.NameGame = NameGame;
		this.EventoId = EventoId;
		this.Evento = Evento;
		this.Cantidad = Cantidad;
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

	public void setTipoPago(String tipoPago) {
		TipoPago = tipoPago;
	}

	public String getTipoPago() {
		return TipoPago;
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

	public void setEventoId(int eventoId) {
		EventoId = eventoId;
	}

	public int getEventoId() {
		return EventoId;
	}

	public void setEvento(String evento) {
		Evento = evento;
	}

	public String getEvento() {
		return Evento;
	}

	public void setCantidad(int cantidad) {
		Cantidad = cantidad;
	}

	public int getCantidad() {
		return Cantidad;
	}

}