package it.betplus.awg.beans.fee;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import it.betplus.awg.beans.GeneralFilterBean;
import it.betplus.awg.beans.LoginBeanAwg;
import it.betplus.awg.db.dto.CompensiInspireDTO;
import it.betplus.awg.frontend.beans.FilterBean;
import it.betplus.web.framework.exceptions.DataLayerException;
import it.betplus.web.framework.managedbeans.BaseTableBean;
import it.betplus.web.framework.utils.DateUtils;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


@ManagedBean(name = "feeTableBean")
@ViewScoped
public class FeeTableBean extends BaseTableBean {
	private static final long serialVersionUID = 7885608476150226065L;

	private List<CompensiInspireDTO> tableList;
	private CompensiInspireDTO selectedObj; 
	
	private Double totBet = 0d; 
	private Double totWin = 0d;
	private Double totContJakpot = 0d;
	private Double totJakpotWins = 0d;
	private Double totNetWin = 0d;
	private Double totHouseWin = 0d;
	private Double totFeePerc = 0d;
	private Double totRicavoInspired = 0d;
	
	public FeeTableBean() {			
		super();	

		GeneralFilterBean generalFilterBean = findBean("generalFilterBean");
		generalFilterBean.setTableBeanName("feeTableBean", "Fee");
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
		
    	this.tableList = loginBean.getFacade().getCompensi(startDate, endDate, filterBean.idManufacture);
        
    	setTotals();
    	
    	CompensiInspireDTO campInspTot = new CompensiInspireDTO();
    	campInspTot = new CompensiInspireDTO("TOTALI", "", "", totBet, totWin, totContJakpot, totJakpotWins, totNetWin, totHouseWin, totFeePerc, totRicavoInspired);
    	
    	this.tableList.add(campInspTot);
    }  
	
	public void setTotals(){
		totBet = 0d;
		totWin = 0d;
		totContJakpot = 0d;
		totJakpotWins = 0d;
		totNetWin = 0d;
		totHouseWin = 0d;
		totFeePerc = 0d;
		totRicavoInspired = 0d;
		
		for (CompensiInspireDTO _element : tableList) {
	         totBet += _element.getBet(); 
	         totWin += _element.getWin();
	         totContJakpot += _element.getJackpotcontribution();
	         totJakpotWins += _element.getJackpotwins();
	         totNetWin += _element.getNetwin();
	         totHouseWin += _element.getTotalhousewin();
	         totRicavoInspired += _element.getRicavoinspired();
    	}
		totFeePerc += totRicavoInspired/totHouseWin * 100;
       
        BigDecimal bdTotBet = new BigDecimal(Double.toString(totBet));
        bdTotBet = bdTotBet.setScale(2, BigDecimal.ROUND_HALF_UP);        
        totBet = bdTotBet.doubleValue();
        
        
        BigDecimal bdTotWin = new BigDecimal(Double.toString(totWin));
        bdTotWin = bdTotWin.setScale(2, BigDecimal.ROUND_HALF_UP);        
        totWin = bdTotWin.doubleValue();

        
        BigDecimal bdTotContJakpot = new BigDecimal(Double.toString(totContJakpot));
        bdTotContJakpot = bdTotContJakpot.setScale(2, BigDecimal.ROUND_HALF_UP);        
        totContJakpot = bdTotContJakpot.doubleValue();
        
        BigDecimal bdTotJakpotWins = new BigDecimal(Double.toString(totJakpotWins));
        bdTotJakpotWins = bdTotJakpotWins.setScale(2, BigDecimal.ROUND_HALF_UP);        
        totJakpotWins = bdTotJakpotWins.doubleValue();
        
        BigDecimal bdTotNetWin = new BigDecimal(Double.toString(totNetWin));
        bdTotNetWin = bdTotNetWin.setScale(2, BigDecimal.ROUND_HALF_UP);        
        totNetWin = bdTotNetWin.doubleValue();
        
        
        BigDecimal bdTotHouseWin = new BigDecimal(Double.toString(totHouseWin));
        bdTotHouseWin = bdTotHouseWin.setScale(2, BigDecimal.ROUND_HALF_UP);        
        totHouseWin = bdTotHouseWin.doubleValue();
        
        BigDecimal bdTotRicavoInspired = new BigDecimal(Double.toString(totRicavoInspired));
        bdTotRicavoInspired = bdTotRicavoInspired.setScale(2, BigDecimal.ROUND_HALF_UP);        
        totRicavoInspired = bdTotRicavoInspired.doubleValue();
        
        BigDecimal bdTotFeePerc = new BigDecimal(Double.toString(totFeePerc));
        bdTotFeePerc = bdTotFeePerc.setScale(2, BigDecimal.ROUND_HALF_UP);        
        totFeePerc = bdTotFeePerc.doubleValue();
        
        
        
		
    }	
	
	public List<CompensiInspireDTO> getTableList() {
		return tableList;
	}

	public void setTableList(List<CompensiInspireDTO> tableList) {
		this.tableList = tableList;
	}

	public CompensiInspireDTO getSelectedObj() {
		return selectedObj;
	}

	public void setSelectedObj(CompensiInspireDTO selectedObj) {
		this.selectedObj = selectedObj;
	}

	public Double getTotBet() {
		return totBet;
	}

	public void setTotBet(Double totBet) {
		this.totBet = totBet;
	}

	public Double getTotWin() {
		return totWin;
	}

	public void setTotWin(Double totWin) {
		this.totWin = totWin;
	}

	public Double getTotContJakpot() {
		return totContJakpot;
	}

	public void setTotContJakpot(Double totContJakpot) {
		this.totContJakpot = totContJakpot;
	}

	public Double getTotNetWin() {
		return totNetWin;
	}

	public void setTotNetWin(Double totNetWin) {
		this.totNetWin = totNetWin;
	}

	public Double getTotHouseWin() {
		return totHouseWin;
	}

	public void setTotHouseWin(Double totHouseWin) {
		this.totHouseWin = totHouseWin;
	}

	public Double getTotFeePerc() {
		return totFeePerc;
	}

	public void setTotFeePerc(Double totFeePerc) {
		this.totFeePerc = totFeePerc;
	}

	public Double getTotRicavoInspired() {
		return totRicavoInspired;
	}

	public void setTotRicavoInspired(Double totRicavoInspired) {
		this.totRicavoInspired = totRicavoInspired;
	}

	public Double getTotJakpotWins() {
		return totJakpotWins;
	}

	public void setTotJakpotWins(Double totJakpotWins) {
		this.totJakpotWins = totJakpotWins;
	}
		  
}  
