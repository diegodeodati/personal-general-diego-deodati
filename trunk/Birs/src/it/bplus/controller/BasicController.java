package it.bplus.controller;

import it.bplus.bean.UserBean;
import it.bplus.business.UtentiBusinessDelegate;
import it.bplus.exception.BusinessLayerException;
import it.bplus.model.Utenti;
import it.bplus.util.IConstants;
import it.bplus.util.IErrorCodes;

import java.util.TimeZone;

import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.application.ViewHandler;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

public abstract class BasicController {

	boolean ivIsStartup=true;
//	private List<?> ivRows = null;
	private String ivSortColumn = null;
	/** Indica la direzione del sorting */
	private boolean ivIsAscending = true;
	// we only want to resort if the oder or column has changed.
	protected String oldSort;
	protected boolean oldAscending;

	protected String ivView="default";
	
	
	
	/**
	 * Flag di validazione viene impostato a true dopo il processo di
	 * validazione
	 */
//	private boolean ivIsValid = false;


	protected static Logger logger = Logger.getLogger(BasicController.class);

	public BasicController() {
//		LifecycleFactory lifecycleFactory = (LifecycleFactory) FactoryFinder
//		.getFactory(FactoryFinder.LIFECYCLE_FACTORY);
//		Lifecycle lifecycle = lifecycleFactory
//		.getLifecycle(LifecycleFactory.DEFAULT_LIFECYCLE);
//
//		lifecycle.addPhaseListener(new PhaseListener() {
//			public void beforePhase(PhaseEvent event) {
//
//			}
//
//			public void afterPhase(PhaseEvent event) {
//
//				ivIsValid = true;
//			}
//
//			public PhaseId getPhaseId() {
//				return PhaseId.UPDATE_MODEL_VALUES;
//			}
//		});
//
//		lifecycle.addPhaseListener(new PhaseListener() {
//			public void beforePhase(PhaseEvent event) {
//				ivIsValid = false;
//			}
//
//			public void afterPhase(PhaseEvent event) {
//
//			}
//
//			public PhaseId getPhaseId() {
//				return PhaseId.PROCESS_VALIDATIONS;
//			}
//		});

	}


	protected BasicController(String defaultSortColumn) {
		ivSortColumn = defaultSortColumn;
		ivIsAscending = isDefaultAscending(defaultSortColumn);
		oldSort = ivSortColumn;
		// make sure sortColumnName on first render
		oldAscending = !ivIsAscending;
	}


	public static HttpServletRequest getRequest()
	{
		HttpServletRequest request = 
			(HttpServletRequest)FacesContext
			.getCurrentInstance()
			.getExternalContext()
			.getRequest();
		if (request == null)
		{
			throw new RuntimeException("Sorry. Got a null request from faces context");
		}
		return request;
	}


	public static HttpSession getSession(){
		HttpServletRequest request = getRequest();
		return request.getSession(true);
	}


	/**
	 * Sort the list.
	 */
	//protected abstract void sort();

	/**
	 * Is the default sortColumnName direction for the given column "ascending" ?
	 */
	protected abstract boolean isDefaultAscending(String sortColumn);

	/**
	 * Gets the sortColumnName column.
	 *
	 * @return column to sortColumnName
	 */
	public String getSortColumnName() {
		return ivSortColumn;
	}

	/**
	 * Sets the sortColumnName column
	 *
	 * @param sortColumnName column to sortColumnName
	 */
	public void setSortColumnName(String sortColumnName) {
		oldSort = this.ivSortColumn;
		this.ivSortColumn = sortColumnName;

	}

	/**
	 * Is the sortColumnName ascending.
	 *
	 * @return true if the ascending sortColumnName otherwise false.
	 */
	public boolean isAscending() {
		return ivIsAscending;
	}

	/**
	 * Set sortColumnName type.
	 *
	 * @param ascending true for ascending sortColumnName, false for desending sortColumnName.
	 */
	public void setAscending(boolean ascending) {
		oldAscending = this.ivIsAscending;
		this.ivIsAscending = ascending;
	}

	public void setView(String value) {
		ivView = value;
	}

	public String getView() {
		return ivView;
	}


//	public List<?> getRows() {
//		
//		
//		if (ivIsStartup) {
//			ivRows = query();
//			ivIsStartup = false;
//		}
//		else{
//			if (ivSortColumn != null && ivSortColumnOld != null
//					&& !this.ivSortColumnOld.equals(ivSortColumn)
//					|| this.ivIsAscendingOld != ivIsAscending) {
//				ivRows = query();
//				ivSortColumnOld = ivSortColumn;
//				ivIsAscendingOld = ivIsAscending;
//			}
//		}
//		return ivRows;
//	}




	public abstract void setEntity(Object value);
	
	public void reset() {
		FacesContext context = FacesContext.getCurrentInstance();
		Application application = context.getApplication();
		ViewHandler viewHandler = application.getViewHandler();
		UIViewRoot viewRoot = viewHandler.createView(context, context
				.getViewRoot().getViewId());
		context.setViewRoot(viewRoot);
		context.renderResponse();
	}
	

	public String getCurrentRuolo() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
		if(session.getAttribute(IConstants.LOGGEDUSERSESSION)!=null){
			UserBean lu=(UserBean)session.getAttribute(IConstants.LOGGEDUSERSESSION);
			return lu.getRoleName();
		}
		else
			return null;
	}
	
	

	public UserBean getCurrentUser() throws BusinessLayerException {
		UserBean loggedUser;
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);

		if(session.getAttribute(IConstants.LOGGEDUSERSESSION)!=null)
			return (UserBean) session.getAttribute(IConstants.LOGGEDUSERSESSION);
		else{
//			String current_user=facesContext.getExternalContext().getUserPrincipal().getName();
			String username= facesContext.getExternalContext().getRemoteUser();

			if(username!=null){
				
				//devo inserire nella session il ruolo del'utente corrente
				Utenti user = UtentiBusinessDelegate.getInstance().getUtenteByUsername(username);
				if(user!=null){
					loggedUser=new UserBean(user.getUsername(),user.getUserPass(),user.getNome(),user.getCognome(),user.getRuoli().iterator().next());
					session.setAttribute(IConstants.LOGGEDUSERSESSION, loggedUser);
					return loggedUser;	
				}
				else{
					logger.error("Utente non trovato");
					throw new BusinessLayerException(IErrorCodes.DB_ERROR_KEY, "Utente non trovato");
				}
			}
			else
			{
				//utente non loggato
				logger.error("accesso negato per : "+username+" utente non loggato");	
				throw new BusinessLayerException("accesso negato per : "+username+" utente non loggato");
			}
		}
	}




	public void addFacesMessageForUI(String uiMessage)
	{
		FacesMessage facesMessage = new FacesMessage(uiMessage);
		FacesContext facesContext = FacesContext.getCurrentInstance();

		// Passing null for the client ID argument to the FacesContext 
		// addMessage() method specifies the message as not belonging
		// to any particular UI component. This will cause the message
		// to be displayed as a general message on the UI

		facesContext.addMessage(null,facesMessage);
	}
	
	
	public TimeZone getTimeZone() {
		return java.util.TimeZone.getDefault();
	}
}
