package serviseatl.eventos;

import java.io.Serializable;

public class Evento implements Serializable {

    static final long serialVersionUID = 109233347451060072L;
    
    private String Fecha;
    private String Hora;
    private int EventoId;
    private String Evento;

    public Evento() {
    	
    }

    public Evento( String Fecha, String Hora, int EventoId, 
    		String Evento)    
    {
		this.Fecha = Fecha;
		this.Hora = Hora;
		this.EventoId = EventoId;
		this.Evento = Evento;

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

	public void setFecha(String fecha) {
		Fecha = fecha;
	}

	public String getFecha() {
		return Fecha;
	}

}