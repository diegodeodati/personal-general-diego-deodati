package it.betplus.awg.beans.fee;

import it.betplus.awg.beans.GeneralFilterBean;
import it.betplus.awg.beans.LoginBeanAwg;
import it.betplus.awg.db.dto.LocationDataDTO;
import it.betplus.awg.frontend.beans.FilterBean;
import it.betplus.web.framework.exceptions.DataLayerException;
import it.betplus.web.framework.managedbeans.BaseTableBean;
import it.betplus.web.framework.managedbeans.LoginBean;
import it.betplus.web.framework.utils.DateUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;


@ManagedBean(name = "locationDataTableBean")
@ViewScoped
public class LocationDataTableBean extends BaseTableBean {
	private static final long serialVersionUID = -2870074586481624270L;
	
	private List<LocationDataDTO> tableList;
	private LocationDataDTO selectedObj;
	
	private boolean isDistante;
	private boolean groupByDate;
	
	public String aamslocationidSel;
	public boolean btnDelDisabled;
	
	public String testoMail;
	public int mode = 1;
	
	public LocationDataTableBean() {			
		super();	

		GeneralFilterBean generalFilterBean = findBean("generalFilterBean");
		generalFilterBean.setTableBeanName("locationDataTableBean", "Location");

		btnDelDisabled = true;
	}

    //*** Business methods ***//

	@Override
    public void setListDataFromSource(ArrayList<Object> params) throws DataLayerException {  
		FilterBean filterBean = findBean("filterBean");
		LoginBeanAwg loginBean = findBean("loginBean");
		
		String startDate;
		String endDate;
		try {
			startDate = DateUtils.formatDateToString(filterBean.getStartDate(), "yyyyMMdd");
			endDate = DateUtils.formatDateToString(filterBean.getEndDate(), "yyyyMMdd");
		} catch (Exception e) {
			throw new DataLayerException(e.getMessage());
		}
		
		this.tableList = loginBean.getFacade().getLocationData(startDate, endDate, groupByDate, filterBean.idManufacture, isDistante);
		
	
	    try {
	    	refreshMailValue();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	public static void main(String[] args) {
	
		BigDecimal bBet = new BigDecimal("14.45");
		BigDecimal bWin = new BigDecimal("10.4658");
		
		System.out.println((bBet.add(bWin).doubleValue() / 100) * 100);
		
	}
	
	public void refreshMailValue() throws Exception
	{
		FilterBean filterBean = findBean("filterBean");
		LoginBeanAwg loginBean = findBean("loginBean");
		String startDate = DateUtils.formatDateToString(filterBean.getStartDate(), "dd/MM/yyyy");
		String endDate = DateUtils.formatDateToString(filterBean.getEndDate(), "dd/MM/yyyy");
		
		String startDateSql = DateUtils.formatDateToString(filterBean.getStartDate(), "yyyyMMdd");
		String endDateSql = DateUtils.formatDateToString(filterBean.getEndDate(), "yyyyMMdd");
		
		Long idMan = filterBean.getIdManufacture();
		
		
		String redirect = ((HttpServletRequest) FacesContext
				.getCurrentInstance().getExternalContext().getRequest())
				.getRequestURI();
		
	
		String mailValue = "";
		
		if(mode==2){
			mailValue = "Buongiorno, \ndi seguito l'elenco delle sale in Esercizio che non hanno prodotto bet \nnella giornata relativi ";	
		}
		else {		
		mailValue = "Buongiorno,\n" +
				"di seguito i valori di Bet & Win relativi ";
		}
				
		Double totBet = 0d;
		Double totWin = 0d;
				
		if(idMan==0)		
			mailValue = mailValue + "a tutti i sistemi di gioco\n";
		else if (idMan==1711000045)
		    mailValue = mailValue + "al sistema di gioco \nNOVOMATIC";
		else
			mailValue = mailValue + "al sistema di gioco \nINSPIRED";
		
		mailValue = mailValue + "\n\nBet & Win ";
		
		if(!redirect.equals("/WebReportingIntra/pages/presidio/bet_win.xhtml"))
		mailValue = mailValue + "(sale WIN TIME) ";
		
		if (startDate.equals(endDate))
			mailValue += "del " + startDate;
		else
			mailValue += "da " + startDate + " a " + endDate;
		
		mailValue += "\n\n";

		DecimalFormat dec = (DecimalFormat) NumberFormat.getInstance(new Locale("it","IT"));
		dec.applyLocalizedPattern("###.###.##0,00");
		dec.setRoundingMode(RoundingMode.DOWN); //SEMPRE apporssimazione in difetto
		
		
		
		

		for (LocationDataDTO _element : tableList) {
			
			
			
			if(mode==1)
			{			
				mailValue += "- " + _element.getAamslocationname() + " - " + _element.getAamscity() + "(" + _element.getAamsprovince() + ")\n";
				mailValue += "Bet: " + dec.format(_element.getBet()) + " Win: " + dec.format(_element.getWin()) + "\n";

				
			}
			else if (mode==2)
			{
				if(_element.getBet()==0)
				{
					if(_element.getLocationStatus().equals("ESERCIZIO")&&!_element.getVltStatus().equals("disabilitate"))
					
					mailValue += "- " + _element.getAamslocationname() + " - " + _element.getAamscity() + "(" + _element.getAamsprovince() + ") "+_element.getAamslocationid() +" \n";
					

				}
			} 
			else 
			{
				if(_element.getBet()>0){
					mailValue += "- " + _element.getAamslocationname() + " - " + _element.getAamscity() + "(" + _element.getAamsprovince() + ")\n";
					mailValue += "Bet: " + dec.format(_element.getBet()) + " Win: " + dec.format(_element.getWin()) + "\n";
					
	
				}
			}
			
		}
		
		
		if(mode!=2)
		mailValue+= "\n\nGRAN TOTALE \n"+loginBean.getFacade().getTotali(startDateSql, endDateSql, isDistante, idMan);
		
		testoMail = mailValue;
	}
	
	
	
	public void onRowSelect(SelectEvent event) {  
    	
    	try {
    		
    		selectedObj = (LocationDataDTO) event.getObject();
        	btnDelDisabled = false;
    		
    	} catch(Exception e) {
    		log.error("Unhandled exception onRowSelect - LocationDataTableBean ", e); 
    	}
    	
    }  
  
    public void onRowUnselect(UnselectEvent event) {  

    	// reset selected obj
    	selectedObj = null;
    	btnDelDisabled = true;
    	
    }  
	
	public void startLocationDistante() throws DataLayerException
	{
		LoginBeanAwg loginBean = findBean("loginBean");
		this.tableList = loginBean.getFacade().getLocationData("", "", false, new Long(0), true);
		
		//return "sale_distante";
	}
	
	public void deleteDistanteLocation() throws DataLayerException
	{
		LoginBeanAwg loginBean = findBean("loginBean");
		
		String IDSel = selectedObj.getAamslocationid();
		loginBean.getFacade().DelDistanteLocation(IDSel);
		
		try {
			startLocationDistante();	
    	} catch(Exception e) {
    		log.error("Unhandled exception Loading Location Distante - LocationDataTableBean ", e); 
    	}
		selectedObj = null;
    	btnDelDisabled = true;
	}

	public List<LocationDataDTO> getTableList() {
		return tableList;
	}

	public void setTableList(List<LocationDataDTO> tableList) {
		this.tableList = tableList;
	}

	public LocationDataDTO getSelectedObj() {
		return selectedObj;
	}

	public void setSelectedObj(LocationDataDTO selectedObj) {
		this.selectedObj = selectedObj;
	}

	public boolean getGroupByDate() {
		return groupByDate;
	}

	public void setGroupByDate(boolean groupByDate) {
		this.groupByDate = groupByDate;
	}

	public boolean isDistante() {
		return isDistante;
	}

	public void setDistante(boolean isDistante) {
		this.isDistante = isDistante;
	}

	public String getAamslocationidSel() {
		return aamslocationidSel;
	}

	public void setAamslocationidSel(String aamslocationidSel) {
		this.aamslocationidSel = aamslocationidSel;
	}

	public boolean isBtnDelDisabled() {
		return btnDelDisabled;
	}

	public void setBtnDelDisabled(boolean btnDelDisabled) {
		this.btnDelDisabled = btnDelDisabled;
	}

	public String getTestoMail() {
		return testoMail;
	}

	public void setTestoMail(String testoMail) {
		this.testoMail = testoMail;
	}

	public int getMode() {
		return mode;
	}

	public void setMode(int mode) {
		this.mode = mode;
	}

}  
