package it.bplus.bean;


import java.io.Serializable;





public class UserBean extends BasicBean implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private String username;
	private String password;
	private String nome;
	private String cognome;
	private String roleName;
	private String listaRuoli;

	public UserBean() {
	}

	public UserBean(String username, String password, String nome, String cognome, String roleName) {

		this.username=username;
		this.password=password;
		this.setNome(nome);
		this.setCognome(cognome);
		this.roleName=roleName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setListaRuoli(String listaRuoli) {
		this.listaRuoli = listaRuoli;
	}

	public String getListaRuoli() {
		return listaRuoli;
	}

	

}
