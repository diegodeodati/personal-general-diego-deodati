package it.bplus.model;

// Generated 31-mar-2011 10.51.22 by Hibernate Tools 3.4.0.CR1

/**
 * Ruoli generated by hbm2java
 */
public class Ruoli implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private RuoliId id;
	private Utenti utenti;

	public Ruoli() {
	}

	public Ruoli(RuoliId id, Utenti utenti) {
		this.id = id;
		this.utenti = utenti;
	}

	public RuoliId getId() {
		return this.id;
	}

	public void setId(RuoliId id) {
		this.id = id;
	}

	public Utenti getUtenti() {
		return this.utenti;
	}

	public void setUtenti(Utenti utenti) {
		this.utenti = utenti;
	}

}
