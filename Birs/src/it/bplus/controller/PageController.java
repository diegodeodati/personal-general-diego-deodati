package it.bplus.controller;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

@ManagedBean(name="pageController")
@SessionScoped
public class PageController extends BasicController implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private static PageController instance;
	
	public static synchronized PageController getInstance() {
		if (instance == null)
		{
			synchronized(PageController.class) {      //1
				PageController inst = instance;         //2
				if (inst == null)
				{
					synchronized(PageController.class) {  //3
						instance = new PageController();
					}
					//instance = inst;               //5
				}
			}
		}
		return instance;
	}
	

	
	public String navigateHome() {
		System.out.println("Redirect to Home Page");

		return "home";
	}
	
	public String navigateRicerca() {
		System.out.println("Redirect to Ricerca Page");

		return "ricerca";
	}
	
	public String navigateUser() {
		System.out.println("Redirect to Utenti Page");

		return "utenti_page";
	}
	
	public String navigateTest() {
		System.out.println("Redirect to Test Page");

		return "test_page";
	}

	public String navigateGraphLocation() {
		System.out.println("Redirect to Graphic Location Page");

		return "graphloc";
	}

	public String action() {
		System.out.println("Action Fired");

		return null;
	}

	public void actionListener(ActionEvent event) {
		System.out.println("ActionListener Fired");
	}

	@Override
	protected boolean isDefaultAscending(String sortColumn) {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public void setEntity(Object value) {
		// TODO Auto-generated method stub
		
	}
	

	
	

}
