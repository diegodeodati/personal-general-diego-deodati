package it.betplus.awg.beans.console;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import it.betplus.awg.beans.LoginBeanAwg;
import it.betplus.awg.utils.RolesEnum;
import it.betplus.web.framework.managedbeans.GeneralBean;

@ManagedBean(name = "homeBean")
@ViewScoped
public class HomeBean extends GeneralBean {
	
	private static final Log log = LogFactory.getLog(HomeBean.class);

	public HomeBean() {
    	super();
    }
    
	//*** Business methods ***//
	public void changeRole(){
		
	}
   
}
