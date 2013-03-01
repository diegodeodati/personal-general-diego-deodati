package it.bplus.bean;


import java.io.Serializable;
import java.util.Date;



public class Confronto extends BasicBean implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private String aams_vlt_id;
	private String gs_vlt_id;
	private String aams_location_id;
	private String location_name;
	private String gamesystem_name;
	private Date data;
	private MeterBean sogei;
	private MeterBean seicento;
	
	
	public MeterBean getSogei() {
		return sogei;
	}
	public void setSogei(MeterBean sogei) {
		this.sogei = sogei;
	}
	public MeterBean getSeicento() {
		return seicento;
	}
	public void setSeicento(MeterBean seicento) {
		this.seicento = seicento;
	}
	
	public Confronto() {
		super();
	}
	
	public Confronto(MeterBean sogei, MeterBean seicento) {
		super();
		this.sogei = sogei;
		this.seicento = seicento;
	}
	public void setAams_vlt_id(String aams_vlt_id) {
		this.aams_vlt_id = aams_vlt_id;
	}
	public String getAams_vlt_id() {
		return aams_vlt_id;
	}
	public void setGs_vlt_id(String gs_vlt_id) {
		this.gs_vlt_id = gs_vlt_id;
	}
	public String getGs_vlt_id() {
		return gs_vlt_id;
	}
	public void setAams_location_id(String aams_location_id) {
		this.aams_location_id = aams_location_id;
	}
	public String getAams_location_id() {
		return aams_location_id;
	}
	public void setLocation_name(String location_name) {
		this.location_name = location_name;
	}
	public String getLocation_name() {
		return location_name;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public Date getData() {
		return data;
	}
	public void setGamesystem_name(String gamesystem_name) {
		this.gamesystem_name = gamesystem_name;
	}
	public String getGamesystem_name() {
		return gamesystem_name;
	}

	
	

}
