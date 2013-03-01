package it.betplus.awg.beans.fee;

import java.util.ArrayList;
import java.util.List;

import it.betplus.awg.beans.GeneralFilterBean;
import it.betplus.awg.beans.LoginBeanAwg;
import it.betplus.awg.db.dto.LocationDataDTO;
import it.betplus.awg.db.dto.VltDTO;
import it.betplus.awg.frontend.beans.FilterBean;
import it.betplus.web.framework.exceptions.DataLayerException;
import it.betplus.web.framework.managedbeans.BaseTableBean;
import it.betplus.web.framework.utils.DateUtils;
import it.betplus.web.framework.utils.Utils;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


@ManagedBean(name = "vltMilionarieBean")
@ViewScoped
public class VltMilionarieBean extends BaseTableBean {
	private static final long serialVersionUID = -2053843375750944944L;
	
	private List<VltDTO> tableList;
	private LocationDataDTO selectedObj; 
	
	public VltMilionarieBean() {			
		super();	

		GeneralFilterBean generalFilterBean = findBean("generalFilterBean");
		generalFilterBean.setTableBeanName("vltMilionarieBean", "VltMilionarie");
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
		
		this.tableList = loginBean.getFacade().getVltMilionarie(startDate, endDate, filterBean.idManufacture);
        
    }
	
	
	public String getDecimalFormat(String pattern, double number) {
        return Utils.formatCurrency(number, pattern);
    }


	public List<VltDTO> getTableList() {
		return tableList;
	}

	public void setTableList(List<VltDTO> tableList) {
		this.tableList = tableList;
	}

	public LocationDataDTO getSelectedObj() {
		return selectedObj;
	}

	public void setSelectedObj(LocationDataDTO selectedObj) {
		this.selectedObj = selectedObj;
	}
	
}  
