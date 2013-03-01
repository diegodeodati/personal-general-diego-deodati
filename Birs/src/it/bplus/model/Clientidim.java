package it.bplus.model;

public class Clientidim implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;


	private String locationName;
	private String aamsLocationId;
	private Long codEsercente;
	private String cfPivaEsercente;
	private String codGestore;
	private String cfPivaGestore;
	private String nomeGestore;
	private String nomeEsercente;
	private String rappresentanteLegaleGs;
	private String email_pec_gs;

	public Clientidim() {
	}

	public Clientidim(Long id) {
		this.id = id;

	}

	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public String getAamsLocationId() {
		return aamsLocationId;
	}

	public void setAamsLocationId(String aamsLocationId) {
		this.aamsLocationId = aamsLocationId;
	}

	public Long getCodEsercente() {
		return codEsercente;
	}

	public void setCodEsercente(Long codEsercente) {
		this.codEsercente = codEsercente;
	}

	public String getCfPivaEsercente() {
		return cfPivaEsercente;
	}

	public void setCfPivaEsercente(String cfPivaEsercente) {
		this.cfPivaEsercente = cfPivaEsercente;
	}
	

	public String getCodGestore() {
		return codGestore;
	}

	public void setCodGestore(String codGestore) {
		this.codGestore = codGestore;
	}

	public String getCfPivaGestore() {
		return cfPivaGestore;
	}

	public void setCfPivaGestore(String cfPivaGestore) {
		this.cfPivaGestore = cfPivaGestore;
	}

	public String getNomeGestore() {
		return nomeGestore;
	}

	public void setNomeGestore(String nomeGestore) {
		this.nomeGestore = nomeGestore;
	}


	public String getNomeEsercente() {
		return nomeEsercente;
	}

	public void setNomeEsercente(String nomeEsercente) {
		this.nomeEsercente = nomeEsercente;
	}

	public void setRappresentanteLegaleGs(String rappresentanteLegaleGs) {
		this.rappresentanteLegaleGs = rappresentanteLegaleGs;
	}

	public String getRappresentanteLegaleGs() {
		return rappresentanteLegaleGs;
	}

	public void setEmail_pec_gs(String email_pec_gs) {
		this.email_pec_gs = email_pec_gs;
	}

	public String getEmail_pec_gs() {
		return email_pec_gs;
	}

	

}