package it.bplus.controller;

import it.bplus.bean.MeterBean;
import it.bplus.bean.MillionVltBean;
import it.bplus.business.MeterBusinessDelegate;
import it.bplus.exception.BusinessLayerException;
import it.bplus.util.DateUtil;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.model.chart.PieChartModel;

import com.icesoft.faces.component.ext.RowSelectorEvent;

@ManagedBean(name="milVltTable")
@SessionScoped
public class MillionVltController extends BasicController implements Serializable {
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PieChartModel pieModel;  
	
	private Map<String,String> locationMap;
	private Map<String, List<MeterBean>> vltMeterMap;
	private String nome_selected_location;
	private String id_selected_location;
	private String selected_vlt;
	private String gd_selected_vlt;
	
	private Date data1 = DateUtil.oraDelleStreghe(DateUtil.calcolaGiornoPrecedente(new Date()));	
	
	private List<MeterBean> meterList_vlt;
	private List<MillionVltBean> meterList_vlt_table;

	private static GraphicController instance;
	
	public static synchronized GraphicController getInstance() {
		if (instance == null)
		{
			synchronized(GraphicController.class) {      //1
				GraphicController inst = instance;         //2
				if (inst == null)
				{
					synchronized(GraphicController.class) {  //3
						instance = new GraphicController();
					}
					//instance = inst;               //5
				}
			}
		}
		return instance;
	}
	
	
	
	public Date getData1() {
		return data1;
	}

	public void setData1(Date data1) {
		this.data1 = data1;
	}


	public Map<String, String> getLocationMap() {
		return locationMap;
	}
	

	public void setMeterList_vlt_table(List<MillionVltBean> meterList_vlt_table) {
		this.meterList_vlt_table = meterList_vlt_table;
	}


	public List<MillionVltBean> getMeterList_vlt_table() {
		if(meterList_vlt_table==null)
			query();
		return meterList_vlt_table;
	}



	public void setSelected_vlt(String selected_vlt) {
		this.selected_vlt = selected_vlt;
	}

	public String getSelected_vlt() {
		return selected_vlt;
	}
	
	public void setGd_selected_vlt(String gd_selected_vlt) {
		this.gd_selected_vlt = gd_selected_vlt;
	}

	public String getGd_selected_vlt() {
		return gd_selected_vlt;
	}
	





	public void setLocationMap(Map<String, String> locationMap) {
		this.locationMap = locationMap;
	}




	public void setVltMeterMap(Map<String, List<MeterBean>> vltMeterMap) {
		this.vltMeterMap = vltMeterMap;
	}



	public Map<String, List<MeterBean>> getVltMeterMap() {
		return vltMeterMap;
	}


	
	
	public List<MeterBean> query_locations_vlt() {
		logger.info("getMeter_Locations_vlt");
		
		try {		
			meterList_vlt = MeterBusinessDelegate.getInstance().retrieveMeterVltByLocationsId(
					null, null, null, null,
					null, null, data1, DateUtil.ventitreCinquantanove(data1), "vista1.DATA", true, false);
			
		} catch (BusinessLayerException e) {
			e.printStackTrace();
			logger.error(e.toString());
			addFacesMessageForUI("Exception detected. Message: " +e.toString());
		}
		return meterList_vlt;	
	}
	
	
	public List<MillionVltBean> query(){
		logger.info("query vlt million");
		
		try {		
			meterList_vlt_table = MeterBusinessDelegate.getInstance().retrieveMeter_vlt_milionarie(data1);
		} catch (BusinessLayerException e) {
			e.printStackTrace();
			logger.error(e.toString());
			addFacesMessageForUI("Exception detected. Message: " +e.toString());
		}
		return meterList_vlt_table;	
	}
		
	
	
	public List<MeterBean> getMeterList_vlt() {
		return meterList_vlt;
	}

	public void setMeterList_vlt(List<MeterBean> meterList_vlt) {
		this.meterList_vlt = meterList_vlt;
	}

  
	@Override
	protected boolean isDefaultAscending(String sortColumn) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setEntity(Object value) {
		// TODO Auto-generated method stub
		
	}
		
	   public void setId_selected_location(String id_selected_location) {
		this.id_selected_location = id_selected_location;
	}

	public String getId_selected_location() {
		return id_selected_location;
	}

	public void setNome_selected_location(String nome_selected_location) {
		this.nome_selected_location = nome_selected_location;
	}

	public String getNome_selected_location() {
		return nome_selected_location;
	}


	
	
    public PieChartModel getPieModel() {  
    	if (pieModel==null)
    		createPieModel();  
        return pieModel;  
    } 
    
    public PieChartModel getNewPieModel(){
    	createPieModel();
    	return pieModel;
    }

	
    private void createPieModel() {  
        pieModel = new PieChartModel();  
        
        meterList_vlt.clear();
        meterList_vlt = query_locations_vlt();
        
		if (meterList_vlt != null && meterList_vlt.size() > 0) {
			for (MeterBean mb : meterList_vlt) {
				pieModel.set(mb.getAams_vlt_id(), mb.getBet());
			}
		}
    }
    
    
    public void rowSelectionListener(RowSelectorEvent ev) {
    	logger.debug(ev.getRow());
    }
    
    
}