package it.bplus.controller;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.component.chart.series.ChartSeries;
import org.primefaces.model.chart.CartesianChartModel;

@ManagedBean(name="chartBean")
@SessionScoped
public class ChartBean implements Serializable {  
	  
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private CartesianChartModel cartesianModel;  

  
    public ChartBean() {  
    }  
  
    public CartesianChartModel getCartesianModel() {  
    	if (cartesianModel==null)
    		createCartesianModel();
        return cartesianModel;  
    }  
    public CartesianChartModel getNewCartesianModel() {  
    	createCartesianModel();
        return cartesianModel;  
    }
  
    private void createCartesianModel() {  
        cartesianModel = new CartesianChartModel();  
  
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
  
        cartesianModel.addSeries(boys);  
        cartesianModel.addSeries(girls);  
    }  
  

}  
 