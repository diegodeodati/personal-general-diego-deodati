package it.bplus.model;

/**
 * Aree generated by hbm2java
 */
public class Aree implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idArea;
	private String nome;

	public Aree() {
	}

	public Aree(int idArea, String nome) {
		this.idArea = idArea;
		this.nome = nome;
	}


	public int getIdArea() {
		return this.idArea;
	}

	public void setIdArea(int idArea) {
		this.idArea = idArea;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
