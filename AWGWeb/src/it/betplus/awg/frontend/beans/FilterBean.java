package it.betplus.awg.frontend.beans;

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.DateSelectEvent;

import it.betplus.web.framework.exceptions.DataLayerException;
import it.betplus.web.framework.managedbeans.GeneralBean;
import it.betplus.web.framework.utils.DateUtils;

@ManagedBean(name="filterBean")
@SessionScoped
public class FilterBean extends GeneralBean {
	private static final long serialVersionUID = -4881011421656749740L;

	private Date startDate;	
	private Date endDate;
	private Date nowDate;// = new Date();
	public Long idManufacture;
	public Integer nrecordperpagina;
	
	private boolean dateChanged = true;
	private boolean errorDate = false;
	
	public FilterBean() throws DataLayerException {
		TopBean topBean = findBean("topBean");
		
		//startDate = new Date();
		//endDate = new Date();
		setNowDate(new Date(topBean.getMaxDataManufacture().getTime()));
		startDate = nowDate;//DateUtils.addDays(nowDate, 1);
		endDate = nowDate;//DateUtils.addDays(nowDate, 1);
	}

	public void changeStartDate(DateSelectEvent event) {
		dateChanged = true;
		if (DateUtils.isDateAfter(event.getDate(), endDate)
				&& !DateUtils.isDateEquals(event.getDate(), endDate)) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL,
							"ERRORE DATA", DateUtils.dateToString(
									event.getDate(), "dd/MM/yyyy")
									+ " successiva a "
									+ DateUtils.dateToString(endDate,
											"dd/MM/yyyy")));
			errorDate = true;
		} else {
			errorDate = false;
		}
	}

	public void changeEndDate(DateSelectEvent event) {
		dateChanged = true;
		if (DateUtils.isDateAfter(startDate, event.getDate())
				&& !DateUtils.isDateEquals(startDate, event.getDate())) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL,
							"ERRORE DATA", DateUtils.dateToString(startDate,
									"dd/MM/yyyy")
									+ " successiva a "
									+ DateUtils.dateToString(event.getDate(),
											"dd/MM/yyyy")));
			errorDate = true;
		} else {
			errorDate = false;
		}
	}
		
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getNowDate() {
		return nowDate;
	}

	public void setNowDate(Date nowDate) {
		this.nowDate = nowDate;
	}

	public Long getIdManufacture() {
		return idManufacture;
	}

	public void setIdManufacture(Long idManufacture) {
		this.idManufacture = idManufacture;
	}

	public Integer getNrecordperpagina() {
		return nrecordperpagina;
	}

	public void setNrecordperpagina(Integer nrecordperpagina) {
		this.nrecordperpagina = nrecordperpagina;
	}

	public boolean isDateChanged() {
		return dateChanged;
	}

	public void setDateChanged(boolean dateChanged) {
		this.dateChanged = dateChanged;
	}

	public boolean isErrorDate() {
		return errorDate;
	}

	public void setErrorDate(boolean errorDate) {
		this.errorDate = errorDate;
	}

	
}
