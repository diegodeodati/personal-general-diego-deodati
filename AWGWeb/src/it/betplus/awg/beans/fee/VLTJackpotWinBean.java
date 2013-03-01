package it.betplus.awg.beans.fee;

import java.util.ArrayList;
import java.util.List;

import it.betplus.awg.beans.GeneralFilterBean;
import it.betplus.awg.beans.LoginBeanAwg;
import it.betplus.awg.db.dto.LocationDTO;
import it.betplus.awg.db.dto.LocationDataDTO;
import it.betplus.awg.db.dto.VltDTO;
import it.betplus.awg.frontend.beans.FilterBean;
import it.betplus.web.framework.exceptions.DataLayerException;
import it.betplus.web.framework.managedbeans.BaseTableBean;
import it.betplus.web.framework.utils.DateUtils;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;


@ManagedBean(name = "vltJackpotWinBean")
@ViewScoped
public class VLTJackpotWinBean extends BaseTableBean {
	private static final long serialVersionUID = -2870074586481624270L;
	
	private List<VltDTO> tableList;
	private VltDTO selectedObj;
	
	public VLTJackpotWinBean() {			
		super();	

		GeneralFilterBean generalFilterBean = findBean("generalFilterBean");
		generalFilterBean.setTableBeanName("vltJackpotWinBean", "VinciteJackpot");

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
		
		this.tableList = loginBean.getFacade().getVltJackpotWin(startDate, endDate, filterBean.idManufacture);
    }

	public List<VltDTO> getTableList() {
		return tableList;
	}

	public void setTableList(List<VltDTO> tableList) {
		this.tableList = tableList;
	}

	public VltDTO getSelectedObj() {
		return selectedObj;
	}

	public void setSelectedObj(VltDTO selectedObj) {
		this.selectedObj = selectedObj;
	}
	
	
}  
