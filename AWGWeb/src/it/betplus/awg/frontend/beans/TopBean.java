package it.betplus.awg.frontend.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.DateSelectEvent;

import it.betplus.awg.beans.LoginBeanAwg;
import it.betplus.awg.db.dto.LocationDataDTO;
import it.betplus.awg.db.dto.ManufactureDateDTO;
import it.betplus.awg.db.facade.AwgFacade;
import it.betplus.web.framework.exceptions.DataLayerException;
import it.betplus.web.framework.frontend.beans.BeanBase;
import it.betplus.web.framework.managedbeans.BaseTableBean;
import it.betplus.web.framework.managedbeans.GeneralBean;
import it.betplus.web.framework.utils.DateUtils;

@ManagedBean(name="topBean")
@ViewScoped
public class TopBean extends GeneralBean {

	private List<ManufactureDateDTO> tableList;
	
	public TopBean() {
		super();
		
		try {
			getManufactureData();
		} catch (DataLayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void getManufactureData() throws DataLayerException {  
		LoginBeanAwg loginBean = findBean("loginBean");
		this.tableList = loginBean.getFacade().getDateByManufacture();
    }

	public String getDataManufacture(String manufacturerID)
	{
		for (ManufactureDateDTO element : this.tableList) {
			if (element.getManufacturerid().equals(manufacturerID))
				return DateUtils.dateToString(element.getMaxdateimported(), "dd/MM/yyyy");
		}
		return "";
	}
	
	public Date getMaxDataManufacture()
	{
		Date dNovomatic = new Date();
		Date dInspired = new Date();
		for (ManufactureDateDTO element : this.tableList) {
			if (element.getManufacturerid().equals("1711000045"))
				dInspired = element.getMaxdateimported();
			else
				dNovomatic = element.getMaxdateimported();
		}

		if (DateUtils.isDateAfter(dInspired, dNovomatic))
			return dInspired;
		else
			return dNovomatic;
	}
	
	public List<ManufactureDateDTO> getTableList() {
		return tableList;
	}

	public void setTableList(List<ManufactureDateDTO> tableList) {
		this.tableList = tableList;
	}

	
}
