package it.bplus.bean;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class BasicBean {

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
	    	return request.getSession();
	    }
}
