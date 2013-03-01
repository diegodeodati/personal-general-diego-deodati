package it.betplus.wri.managedbeans;

import it.betplus.wri.db.dto.SummaryCasinoV3;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.LineChartSeries;



@ManagedBean(name = "summaryLineChartBean")
@SessionScoped
public class SummaryLineChartBean {
	private List<SummaryCasinoV3> summaryCasinoV3List = new ArrayList<SummaryCasinoV3>();
	
	
	private CartesianChartModel linearModel;  
	
	
	private static final long serialVersionUID = 6267502660288290821L;

	public SummaryLineChartBean() {
		createLinearModel();  
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

	public CartesianChartModel getLinearModel() {  
        return linearModel;  
    }  

	private void createLinearModel() {  
        linearModel = new CartesianChartModel();  
  
        LineChartSeries series1 = new LineChartSeries();  
        series1.setLabel("Series 1");  
  
        series1.set(1, 2);  
        series1.set(2, 1);  
        series1.set(3, 3);  
        series1.set(4, 6);  
        series1.set(5, 8);  
  
        LineChartSeries series2 = new LineChartSeries();  
        series2.setLabel("Series 2");  
        series2.setMarkerStyle("diamond");  
  
        series2.set(1, 6);  
        series2.set(2, 3);  
        series2.set(3, 2);  
        series2.set(4, 7);  
        series2.set(5, 9);  
  
        linearModel.addSeries(series1);  
        linearModel.addSeries(series2);  
    } 
}
