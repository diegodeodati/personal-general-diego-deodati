package it.betplus.awg.beans.fee;

import java.util.ArrayList;
import java.util.List;

import it.betplus.awg.beans.GeneralFilterBean;
import it.betplus.awg.beans.LoginBeanAwg;
import it.betplus.awg.db.dto.LocationDTO;
import it.betplus.awg.db.dto.LocationDataDTO;
import it.betplus.awg.frontend.beans.FilterBean;
import it.betplus.web.framework.exceptions.DataLayerException;
import it.betplus.web.framework.managedbeans.BaseTableBean;
import it.betplus.web.framework.utils.DateUtils;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;


@ManagedBean(name = "locationTableBean")
@ViewScoped
public class LocationTableBean extends BaseTableBean {
	private static final long serialVersionUID = 7724328620206233579L;
	
	private List<LocationDTO> tableList;
	private LocationDTO selectedObj;
	
	private boolean isDistante;
	private boolean groupByDate;
	
	public String aamslocationidSel;
	public boolean btnDelDisabled;
	
	public LocationTableBean() {			
		super();	

		GeneralFilterBean generalFilterBean = findBean("generalFilterBean");
		generalFilterBean.setTableBeanName("locationDataTableBean", "Location");

    	btnDelDisabled = true;
	}

    //*** Business methods ***//

	@Override
    public void setListDataFromSource(ArrayList<Object> params) throws DataLayerException {  
		LoginBeanAwg loginBean = findBean("loginBean");
			
		this.tableList = loginBean.getFacade().getLocation(true);
    }
	
	public void onRowSelect(SelectEvent event) {  
    	
    	try {
    		
    		selectedObj = (LocationDTO) event.getObject();
        	
        	LoginBeanAwg loginBean = findBean("loginBean");
    		
    		String IDSel = selectedObj.getAamslocationid();
    		loginBean.getFacade().AddDistanteLocation(IDSel);
    		
    		try {
    			LocationDataTableBean locationDataTableBean = findBean("locationDataTableBean");
    			locationDataTableBean.startLocationDistante();	
    			setListDataFromSource(null);
        	} catch(Exception e) {
        		log.error("Unhandled exception Loading Location Distante - LocationDataTableBean ", e); 
        	}
    		
    	} catch(Exception e) {
    		log.error("Unhandled exception onRowSelect - LocationDataTableBean ", e); 
    	}
    	
    }  
  
    public void onRowUnselect(UnselectEvent event) {  

    	// reset selected obj
    	selectedObj = null;
    	btnDelDisabled = true;
    	
    }  
	
	public List<LocationDTO> getTableList() {
		return tableList;
	}

	public void setTableList(List<LocationDTO> tableList) {
		this.tableList = tableList;
	}

	public LocationDTO getSelectedObj() {
		return selectedObj;
	}

	public void setSelectedObj(LocationDTO selectedObj) {
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

}  
