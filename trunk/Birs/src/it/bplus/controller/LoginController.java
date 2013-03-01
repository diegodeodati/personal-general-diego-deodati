package it.bplus.controller;

import it.bplus.bean.UserBean;
import it.bplus.business.UtentiBusinessDelegate;
import it.bplus.exception.BusinessLayerException;
import it.bplus.model.Utenti;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

@ManagedBean(name="loginController")
@SessionScoped
public class LoginController extends BasicController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private UserBean loggedUser;



	public UserBean getLoggedUser() {
		if(loggedUser==null || (loggedUser!=null && loggedUser.getUsername()==null))
			try {
				loggedUser=getCurrentUser();
			} catch (BusinessLayerException e) {
				e.printStackTrace();
			}
			return loggedUser;
	}


	public void setLoggedUser(UserBean loggedUser) {
		this.loggedUser = loggedUser;
	}


	public LoginController() {
		this.loggedUser=new UserBean();

		//		HttpSession session=getSession();
		//		if(session!=null){
		//			Object obj=session.getAttribute(IConstants.LOGGEDUSERSESSION);
		//			if(obj !=null)
		//			{
		//				Utenti loggedUtente=(Utenti)obj;
		//				loggedUser=trasforma(loggedUtente);
		//			}
		//		}

	}

	protected LoginController(String defaultSortColumn) {
		super(defaultSortColumn);
	}


	public String logout(){
 		FacesContext context = FacesContext.getCurrentInstance();
 		ExternalContext ec = context.getExternalContext();
 
 		final HttpServletRequest request = (HttpServletRequest)ec.getRequest();
 	     request.getSession( false ).invalidate();
 	  
 	     
 	     return "logout";
 	}



	@Override
	protected boolean isDefaultAscending(String sortColumn) {
		return false;
	}


	public String login(){
		logger.info("start: getUtenti (login)");

		try {
			Utenti user = UtentiBusinessDelegate.getInstance().getUtenteByUsernamePassword(loggedUser.getUsername(),loggedUser.getPassword());
			if(user!=null)
				loggedUser=new UserBean(user.getUsername(),user.getUserPass(),user.getNome(),user.getCognome(),user.getRuoli().iterator().next());
			else
				logger.error("Utente non trovato");

		} catch (BusinessLayerException e) {
			e.printStackTrace();
			logger.error(e.toString());
		}
		return "index";
	}



	@Override
	public void setEntity(Object value) {
		// TODO Auto-generated method stub

	}


	public UserBean trasforma(Utenti utente){
		UserBean ub=new UserBean();
		if(utente!=null)
		{
			ub.setUsername(utente.getUsername());
			ub.setRoleName(utente.getRuoli().iterator().next());
		}

		return ub;
	}


	public boolean getIsAdmin(){
		return loggedUser.getRoleName().equals("admin");
	}
	
	public boolean getIsPresidio(){
		return loggedUser.getRoleName().equals("presidio");
	}

}