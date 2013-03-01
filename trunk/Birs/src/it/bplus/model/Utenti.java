package it.bplus.model;

// Generated 31-mar-2011 10.51.22 by Hibernate Tools 3.4.0.CR1

import java.util.List;

/**
 * Utenti generated by hbm2java
 */
public class Utenti implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String username;
	private String userPass;
	private String nome;
	private String cognome;
	private List<String> ruoli;

	public Utenti() {
	}

	public Utenti(String username) {
		this.username = username;
	}

	public Utenti(String username, String userPass, String nome,
			String cognome, List<String> ruoli) {
		this.username = username;
		this.userPass = userPass;
		this.nome = nome;
		this.cognome = cognome;
		this.ruoli = ruoli;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserPass() {
		return this.userPass;
	}

	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return this.cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public List<String> getRuoli() {
		return this.ruoli;
	}

	public void setRuoli(List<String> ruoli) {
		this.ruoli = ruoli;
	}

}
