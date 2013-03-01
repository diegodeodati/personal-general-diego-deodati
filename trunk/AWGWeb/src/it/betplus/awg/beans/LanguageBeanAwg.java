package it.betplus.awg.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import it.betplus.web.framework.managedbeans.LanguageBean;

@ManagedBean(name = "languageBean")
@SessionScoped
public class LanguageBeanAwg extends LanguageBean{

	private static final long serialVersionUID = -1002653040276064609L;

	public LanguageBeanAwg() {
		super();
	}
	
}
