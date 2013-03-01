package serviseatl.eventos;

import java.io.Serializable;

public class Eventos implements Serializable {

    static final long serialVersionUID = 109233314401000002L;
    
    private String CodMaquina;
    private String Fecha;
    private String Hora;
    private int EventoId;
    private String Evento;
    private int NumMaquina;
    private String NameGame;

    public Eventos() {
    	
    }

    public Eventos( String CodMaquina, String Fecha, String Hora, int EventoId, 
    		String Evento, int NumMaquina, String NameGame)    
    {
		this.CodMaquina = CodMaquina;
		this.Fecha = Fecha;
		this.Hora = Hora;
		this.EventoId = EventoId;
		this.Evento = Evento;
		this.NumMaquina = NumMaquina;
		this.NameGame = NameGame;

	}

	public void setCodMaquina(String codMaquina) {
		CodMaquina = codMaquina;
	}

	public String getCodMaquina() {
		return CodMaquina;
	}

	public void setFecha(String fecha) {
		Fecha = fecha;
	}

	public String getFecha() {
		return Fecha;
	}

	public void setHora(String hora) {
		Hora = hora;
	}

	public String getHora() {
		return Hora;
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

}