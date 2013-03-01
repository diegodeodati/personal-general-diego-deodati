package it.betplus.awg.beans;

import java.util.Date;

import it.betplus.web.framework.export.ExcelExporter;
import it.betplus.web.framework.managedbeans.BaseTableBean;
import it.betplus.web.framework.managedbeans.GeneralBean;
import it.betplus.web.framework.utils.DateUtils;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "generalFilterBean")
@SessionScoped
public class GeneralFilterBean extends GeneralBean {  
	private String tableBeanName;
	private String xlsName;
	private ExcelExporter excelExporter;
	
    public GeneralFilterBean() {			
		super();
		
		setExcelExporter(new ExcelExporter());
	}

    //*** Business methods ***//

    public void updateFee()
    {
    	
    }
    
    public void filterTable()
    {
    	BaseTableBean tableBean = findBean(tableBeanName);
    	tableBean.populateTable(null);
    }

	public String getTableBeanName() {
		return tableBeanName;
	}

	public void setTableBeanName(String tableBeanName, String xmlName) {
		this.tableBeanName = tableBeanName;
		String xmlNameFin = "";
		if (!xmlName.isEmpty())
			xmlNameFin = xmlName;
		xmlNameFin += "_" + DateUtils.dateToString(new Date(), "yyyyMMdd_HHmmss");
		this.xlsName = xmlNameFin; 
	}

	public ExcelExporter getExcelExporter() {
		return excelExporter;
	}

	public void setExcelExporter(ExcelExporter excelExporter) {
		this.excelExporter = excelExporter;
	}

	public String getXlsName() {
		return xlsName;
	}

	public void setXlsName(String xlsName) {
		this.xlsName = xlsName;
	}


	 
}
