package it.betplus.awg.beans;

import it.betplus.awg.db.dto.UserAuthenticationDTO;
import it.betplus.awg.db.facade.AwgFacade;
import it.betplus.awg.frontend.beans.UserBean;
import it.betplus.web.framework.exceptions.DataLayerException;
import it.betplus.web.framework.managedbeans.LoginBean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "loginBean")
@SessionScoped
public class LoginBeanAwg extends LoginBean {  
	  
	private static final long serialVersionUID = -7859104635092743231L;
	
	protected UserBean loggedUser;
    
    protected AwgFacade facade;
    
    public LoginBeanAwg() {			
		super();	
		facade = new AwgFacade();
		configureOutcomes("home", "index");
	}

    //*** Business methods ***//

    @Override
    public Object getUserAuthenticationDTO(String username, String password) throws DataLayerException, Exception{
    	
    	return facade.getUserAuthentication(username, password);
    
    }

    @Override
    public void setLoggedUserFromDTO(Object dto) {
    	
    	loggedUser = new UserBean((UserAuthenticationDTO) dto, true);
    	
	} 
    
    @Override
	public void resetLoginSession() {
    	
    	// remove user from session bean
		this.loggedUser = null;
		this.username = "";
		this.password = "";
		
    }
    
    //*** get & set methods ***//
	public UserBean getLoggedUser() {
		return loggedUser;
	}

	public void setLoggedUser(UserBean loggedUser) {
		this.loggedUser = loggedUser;
	}

	public AwgFacade getFacade() {
		return facade;
	}

	public void setFacade(AwgFacade facade) {
		this.facade = facade;
	}  

}  
