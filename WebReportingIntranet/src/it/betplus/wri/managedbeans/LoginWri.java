package it.betplus.wri.managedbeans;

import java.util.ArrayList;
import java.util.Date;

import it.betplus.web.framework.exceptions.DataLayerException;
import it.betplus.web.framework.managedbeans.LoginBean;
import it.betplus.wri.db.dto.Session;
import it.betplus.wri.db.procedure.SPSessionV2;
import it.betplus.wri.frontend.beans.UserBean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;

@ManagedBean(name = "loginBean")
@SessionScoped
public class LoginWri extends LoginBean {  
	  
	private static final long serialVersionUID = -7859104635092743231L;
	
	protected UserBean loggedUser;
	private String theme;
	
    public LoginWri() {			
		
    	super();	
		configureOutcomes("summary", "index");
		theme = "betplus-blue"; // default theme
		
		
    }

    //*** Business methods ***//
    @Override
    public Object getUserAuthenticationDTO(String username, String password) throws DataLayerException, Exception{
    	
    	Session dto = null;
    	
    	try {
    		
    		// dati da DB
    		SPSessionV2 spSession = new SPSessionV2();
    		ArrayList<Session> usersList = (ArrayList<Session>) spSession.getSPSession(username, password, 0, 0);
    		
    		if(!usersList.isEmpty())
    			dto = usersList.get(0);
    		
    	} catch (Exception e) {
    		throw new DataLayerException(e.getMessage());
		}
    	
    	return dto;
    	
    }
    
    @Override
	public void resetLoginSession() {
    	
    	// remove user from session bean
		this.loggedUser = null;
		this.username = "";
		this.password = "";
		
    }

    @Override
    public void setLoggedUserFromDTO(Object dto) {
    	
    	loggedUser = new UserBean((Session) dto, true);
    	loggedUser.setUsername(username);
    	loggedUser.setLoginDate(new Date());
    	
	} 

    public String reset() {
    	resetLoginSession();
    	return "index";
    }
    
    public void sendErrorAuthMessage(String messageSummary, String messageText) {
    	
    	sendErrorMsgToGrowl(messageSummary, messageText);  
        
    }
    
    public void sendErrorMsgToGrowl(String messageSummary, String messageText) {
    	
		sendErrorMessageToUser(messageSummary, messageText);  
		
    }
    
    // switch theme color
    public void changeThemeAjax(AjaxBehaviorEvent event){
    
    	System.out.println("Theme value changed to " + theme);
    	
    }
    
    //*** get & set methods ***//
	public UserBean getLoggedUser() {
		return loggedUser;
	}

	public void setLoggedUser(UserBean loggedUser) {
		this.loggedUser = loggedUser;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

}  
