package it.bplus.controller;

import it.bplus.bean.UserBean;
import it.bplus.business.UtentiBusinessDelegate;
import it.bplus.exception.BusinessLayerException;
import it.bplus.model.Utenti;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;


@ManagedBean(name="userController")
@ViewScoped
public class UserController extends BasicController implements Serializable {

	private static final long serialVersionUID = 1L;
	

	
	private final String usernameColumnName = "Username";
	private final String nomeColumnName = "Nome";
	private final String cognomeColumnName = "Cognome";
	private final String listaRuoliColumnName = "Lista Ruoli";
	private UserBean ivItem;
	private List<UserBean> userList;
	private boolean isAscending = false;
	private String sortColumn = "username";
	
	
	
	public List<UserBean> query() {
		logger.info("getUtenti");

		
		Collection<Utenti> lista;
		try {
			lista = UtentiBusinessDelegate.getInstance().getLista(sortColumn, isAscending);
			
			userList=UtentiBusinessDelegate.getInstance().trasformaFromMeterfactToMeterBean(lista);
			ivItem = null;
		} catch (BusinessLayerException e) {
			e.printStackTrace();
			logger.error(e.toString());
			addFacesMessageForUI("Exception detected. Message: " +e.toString());
		}
		return userList;	
	}	
	
	
	public void ordinaPerNome(ActionEvent event){
		userList=null;
		if (this.sortColumn.equals("nome"))
			this.isAscending = !this.isAscending;
		else {
			this.setSortColumn("nome");
			this.isAscending = false;
		}
	}
	
	public void ordinaPerCognome(ActionEvent event){
		userList=null;
		if (this.sortColumn.equals("cognome"))
			this.isAscending = !this.isAscending;
		else {
			this.setSortColumn("cognome");
			this.isAscending = false;
		}
	}	
	
	
	public void ordinaPerUsername(ActionEvent event){
		userList=null;
		if (this.sortColumn.equals("username"))
			this.isAscending = !this.isAscending;
		else {
			this.setSortColumn("username");
			this.isAscending = false;
		}
	}
	
	

	public String getSortColumn() {
		return sortColumn;
	}

	public void setSortColumn(String sortColumn) {
		this.sortColumn = sortColumn;
	}


	@Override
	public void setEntity(Object value) {
		// TODO Auto-generated method stub
		
	}

	public void setItem(UserBean ivItem) {
		this.ivItem = ivItem;
	}

	public UserBean getItem() {
		return ivItem;
	}

	public List<UserBean> getUserList() {
		if (userList==null)
			userList = query();
		return userList;
	}




	public UserController(){
		super();

	}




	@Override
	protected boolean isDefaultAscending(String sortColumn) {
		// TODO Auto-generated method stub
		return false;
	}



	public String getUsernameColumnName() {
		return usernameColumnName;
	}



	public String getNomeColumnName() {
		return nomeColumnName;
	}



	public String getCognomeColumnName() {
		return cognomeColumnName;
	}



	public String getListaRuoliColumnName() {
		return listaRuoliColumnName;
	}
	
	
	






	//INSERIMENTO NUOVO UTENTE
	private String username;
	private String password;
	private String nome;
	private String cognome;
	private String[] selected_ruoli;
	private List<String> ruoli;
	private String msgUtenteCreato = "";
	private String msgUtenteNonCreato = "";


	public void setUsername(String username) {
		this.username = username;
	}


	public String getUsername() {
		return username;
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

	public void setRuoli(List<String> ruoli) {
		this.ruoli = ruoli;
	}

	public List<String> getRuoli() {
		return ruoli;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getPassword() {
		return password;
	}
	
	public String getMsgUtenteCreato() {
		return msgUtenteCreato;
	}


	public void setMsgUtenteCreato(String msgUtenteCreato) {
		this.msgUtenteCreato = msgUtenteCreato;
	}


	public String getMsgUtenteNonCreato() {
		return msgUtenteNonCreato;
	}


	public void setMsgUtenteNonCreato(String msgUtenteNonCreato) {
		this.msgUtenteNonCreato = msgUtenteNonCreato;
	}
	
	public void setSelected_ruoli(String[] selected_ruoli) {
		this.selected_ruoli = selected_ruoli;
	}


	public String[] getSelected_ruoli() {
		return selected_ruoli;
	}
	
	public void inserisciUtente() throws BusinessLayerException{
		msgUtenteCreato = null;
		msgUtenteNonCreato = null;
		int inserito = 0;
		
		if (username==null || username.equals("") || password==null || password.equals("") 
				|| nome==null || nome.equals("") || cognome==null || cognome.equals("") || selected_ruoli.length<=0){
			inserito = -2;
		}
		else {

			if (!UtentiBusinessDelegate.getInstance().verificaUsername(username)) {
				ruoli = new ArrayList<String>();
				for(int i=0;i<selected_ruoli.length;i++){
					ruoli.add(selected_ruoli[i]);
				}
				Utenti ut = new Utenti(username, password, nome, cognome, ruoli);
				inserito = UtentiBusinessDelegate.getInstance().inserisciNuovoUtente(ut);
			} else
				inserito = -1;
		}
		
		if (inserito==1){
			msgUtenteCreato = "Utente creato correttamente.";
			query();
		}
		else if (inserito==0)
			msgUtenteNonCreato = "Non è stato possibile creare il nuovo utente.";
		else if (inserito==-1)
			msgUtenteNonCreato = "L'username non è disponibile. Sceglierne un altro.";
		else if (inserito==-2)
			msgUtenteNonCreato = "Tutti i campi sono obbligatori.";	
	}

	
	
	
	//MODIFICA DATI UTENTE
	private boolean modUser = false;
	private boolean elimUser = false;
	private String elim_username = "";
	private String mod_username;
	private String old_username;
	private String mod_password;
	private String mod_nome;
	private String mod_cognome;
	private String[] mod_selected_ruoli;
	private List<String> mod_ruoli;
	private String msgUtenteModificato = "";
	private String msgUtenteNonModificato = "";
	private String msgUtenteEliminato = "";
	private String msgUtenteNonEliminato = "";

	
	public void modUtente(ActionEvent ev) throws BusinessLayerException {
		msgUtenteModificato = "";
		msgUtenteNonModificato = "";
		String usernameMod = (String) ev.getComponent().getAttributes().get("username_mod");
		old_username = usernameMod;
		if (usernameMod != null && !(usernameMod.equals(""))) {
			if (modUser && mod_username.equals(usernameMod))
				modUser = false;
			else {
				Utenti ut = UtentiBusinessDelegate.getInstance().getUtenteByUsername(usernameMod);
				mod_username = ut.getUsername();
				mod_password = ut.getUserPass();
				mod_nome = ut.getNome();
				mod_cognome = ut.getCognome();
				
				modUser = true;
			}
		}
	}

	public void modificaUtente() throws BusinessLayerException{
		msgUtenteModificato = "";
		msgUtenteNonModificato = "";
		int modificato = 0;
		
		if (mod_username==null || mod_username.equals("") || mod_password==null || mod_password.equals("") 
				|| mod_nome==null || mod_nome.equals("") || mod_cognome==null || mod_cognome.equals("") || mod_selected_ruoli.length<=0){
			modificato = -2;
		}
		else {
			if (!UtentiBusinessDelegate.getInstance().verificaUsername(mod_username)) {
				mod_ruoli = new ArrayList<String>();
				for(int i=0;i<mod_selected_ruoli.length;i++){
					mod_ruoli.add(mod_selected_ruoli[i]);
				}
				Utenti ut = new Utenti(mod_username, mod_password, mod_nome, mod_cognome, mod_ruoli);
				modificato = UtentiBusinessDelegate.getInstance().modificaUtente(ut,old_username);
			} else
				modificato = -1;
		}		
		if (modificato>0){
			msgUtenteModificato = "Utente modificato correttamente.";
			query();
		}
		else if (modificato==0)
			msgUtenteNonModificato = "Non è stato possibile modificare i dati dell'utente.";
		else if (modificato==-1)
			msgUtenteNonModificato = "L'username non è disponibile. Sceglierne un altro.";
		else if (modificato==-2)
			msgUtenteNonModificato = "Tutti i campi sono obbligatori.";		
	}

	
	
	
	//MODIFICA UTENTE
	public void elimUtente(ActionEvent ev) throws BusinessLayerException {
		setMsgUtenteEliminato("");
		setMsgUtenteNonEliminato("");
		String usernameElim = (String) ev.getComponent().getAttributes().get("username_elim");
		
		if (usernameElim != null && !(usernameElim.equals(""))) {
			if (elimUser && elim_username.equals(usernameElim))
				elimUser = false;
			else {			
				elim_username = usernameElim;				
				elimUser = true;
			}
		}
	}

	public void eliminaUtente() throws BusinessLayerException{
		setMsgUtenteEliminato("");
		setMsgUtenteNonEliminato("");
		int eliminato = 0;
		
		if (elim_username==null || elim_username.equals("")){
			eliminato = -2;
		}
		else {
				eliminato = UtentiBusinessDelegate.getInstance().eliminaUtente(elim_username);
		}		
		if (eliminato>0){
			msgUtenteEliminato = "Utente eliminato correttamente.";
			query();
		}
		else if (eliminato==0)
			msgUtenteNonEliminato = "Non è stato possibile eliminare l'utente.";
		else if (eliminato==-2)
			msgUtenteNonEliminato = "Username non trovato.";		
	}


	public void setModUser(boolean modUser) {
		this.modUser = modUser;
	}


	public boolean isModUser() {
		return modUser;
	}


	public String getMod_username() {
		return mod_username;
	}


	public void setMod_username(String mod_username) {
		this.mod_username = mod_username;
	}


	public String getMod_password() {
		return mod_password;
	}


	public void setMod_password(String mod_password) {
		this.mod_password = mod_password;
	}


	public String getMod_nome() {
		return mod_nome;
	}


	public void setMod_nome(String mod_nome) {
		this.mod_nome = mod_nome;
	}


	public String getMod_cognome() {
		return mod_cognome;
	}


	public void setMod_cognome(String mod_cognome) {
		this.mod_cognome = mod_cognome;
	}


	public String[] getMod_selected_ruoli() {
		return mod_selected_ruoli;
	}


	public void setMod_selected_ruoli(String[] mod_selected_ruoli) {
		this.mod_selected_ruoli = mod_selected_ruoli;
	}


	public List<String> getMod_ruoli() {
		return mod_ruoli;
	}


	public void setMod_ruoli(List<String> mod_ruoli) {
		this.mod_ruoli = mod_ruoli;
	}


	public String getMsgUtenteModificato() {
		return msgUtenteModificato;
	}


	public void setMsgUtenteModificato(String msgUtenteModificato) {
		this.msgUtenteModificato = msgUtenteModificato;
	}


	public String getMsgUtenteNonModificato() {
		return msgUtenteNonModificato;
	}


	public void setMsgUtenteNonModificato(String msgUtenteNonModificato) {
		this.msgUtenteNonModificato = msgUtenteNonModificato;
	}


	public void setElimUser(boolean elimUser) {
		this.elimUser = elimUser;
	}


	public boolean isElimUser() {
		return elimUser;
	}
	
	public void noElim(){
		this.elimUser = false;
	}


	public void setMsgUtenteEliminato(String msgUtenteEliminato) {
		this.msgUtenteEliminato = msgUtenteEliminato;
	}


	public String getMsgUtenteEliminato() {
		return msgUtenteEliminato;
	}


	public void setMsgUtenteNonEliminato(String msgUtenteNonEliminato) {
		this.msgUtenteNonEliminato = msgUtenteNonEliminato;
	}


	public String getMsgUtenteNonEliminato() {
		return msgUtenteNonEliminato;
	}


	public String getElim_username() {
		return elim_username;
	}


	public void setElim_username(String elim_username) {
		this.elim_username = elim_username;
	}


	
	
}

