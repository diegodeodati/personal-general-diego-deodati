package it.betplus.wri.managedbeans;

import it.betplus.web.framework.managedbeans.ChartsManagerBean;
import it.betplus.wri.db.dto.SummaryCasinoV3;
import it.betplus.wri.db.procedure.SPSummaryCasinoV3;
import it.betplus.wri.frontend.beans.UserBean;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.chart.PieChartModel;


@ManagedBean(name = "summaryPieChartBean")
@SessionScoped
public class SummaryPieChartBean {
	private List<SummaryCasinoV3> summaryCasinoV3List = new ArrayList<SummaryCasinoV3>();
	/**
	 * 
	 */
	
	private PieChartModel pieModel; 
	
	
	private static final long serialVersionUID = 6267502660288290821L;

	public SummaryPieChartBean() {
		createPieModel();
	}

/*
	public void createPieModel() {

		LinkedHashMap<String, Number> hLocation = new LinkedHashMap<String, Number>();

		try {
			for (SummaryCasinoV3 s : summaryCasinoV3List) {
				hLocation
						.put(s.getAamsLocationId(), s.getNetWinLose());
			}
			createOrUpdateChart("pieChartLocationNetwinLose", hLocation);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void loadSummaryCasinoV3List() {

		SPSummaryCasinoV3 spSummary = new SPSummaryCasinoV3();

		try {

			// today date
			Calendar cal = Calendar.getInstance();
			java.sql.Date dateToday = new java.sql.Date(cal.getTime().getTime());

			// get current user employee
			LoginWri loginBean = findBean("loginBean");
			UserBean user = loginBean.getLoggedUser();

			summaryCasinoV3List = spSummary.getSPSummaryCasinoV3(1, 1,
					dateToday.getTime(), dateToday.getTime(),
					user.getEmployeeID(), 1000);

			System.out.println("Executed query results n. "
					+ summaryCasinoV3List.size());

		} catch (Exception e) {
			e.printStackTrace();
		}

	}*/
    /*
	public List<SummaryCasinoV3> getSummaryCasinoV3List() {
		return summaryCasinoV3List;
	}

	public void setSummaryCasinoV3List(List<SummaryCasinoV3> summaryCasinoV3List) {
		this.summaryCasinoV3List = summaryCasinoV3List;
	}*/


	public PieChartModel getPieModel() {
	        return pieModel;
	}

    private void createPieModel() {
	        pieModel = new PieChartModel();

	        pieModel.set("Brand 1", 540);
	        pieModel.set("Brand 2", 325);
	        pieModel.set("Brand 3", 702);
	        pieModel.set("Brand 4", 421);
    }
	
}
