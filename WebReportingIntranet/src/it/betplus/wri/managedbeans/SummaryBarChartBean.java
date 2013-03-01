package it.betplus.wri.managedbeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;


@ManagedBean(name = "summaryBarChartBean")
@SessionScoped
public class SummaryBarChartBean {
	public SummaryBarChartBean() {
		createCategoryModel();
	}

	public CartesianChartModel getCategoryModel() {
		return categoryModel;
	}
	
	public void createCategoryModel() {
		categoryModel = new CartesianChartModel();  
		  
        ChartSeries boys = new ChartSeries();  
        boys.setLabel("Boys");  
  
        boys.set("2004", 120);  
        boys.set("2005", 100);  
        boys.set("2006", 44);  
        boys.set("2007", 150);  
        boys.set("2008", 25);  
  
        ChartSeries girls = new ChartSeries();  
        girls.setLabel("Girls");  
  
        girls.set("2004", 52);  
        girls.set("2005", 60);  
        girls.set("2006", 110);  
        girls.set("2007", 135);  
        girls.set("2008", 120);  
  
        categoryModel.addSeries(boys);  
        categoryModel.addSeries(girls);
	}

	private CartesianChartModel categoryModel;
	
	
}
