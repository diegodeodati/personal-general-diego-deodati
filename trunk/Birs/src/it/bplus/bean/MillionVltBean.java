package it.bplus.bean;

import java.io.Serializable;
import java.util.Date;


public class MillionVltBean extends BasicBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String aams_vlt_id_max_bet;
	private String gs_vlt_id;
	private String location_name;
	private Date data;
	private String aams_location_id;
	private Double max_bet_vlt;
	private Double media_bet_vlt;
	private Double total_bet_sala;
	private int num_vlt;
	private int percent_max_bet;
	private Double media_7gg;
	private Double media_30gg;
	
	
	
	public MillionVltBean() {
		super();
	}
	
	public String getAams_vlt_id_max_bet() {
		return aams_vlt_id_max_bet;
	}
	public void setAams_vlt_id_max_bet(String aams_vlt_id_max_bet) {
		this.aams_vlt_id_max_bet = aams_vlt_id_max_bet;
	}
	public String getLocation_name() {
		return location_name;
	}
	public void setLocation_name(String location_name) {
		this.location_name = location_name;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public String getAams_location_id() {
		return aams_location_id;
	}
	public void setAams_location_id(String aams_location_id) {
		this.aams_location_id = aams_location_id;
	}
	public Double getMax_bet_vlt() {
		return max_bet_vlt;
	}
	public void setMax_bet_vlt(Double max_bet_vlt) {
		this.max_bet_vlt = max_bet_vlt;
	}
	public Double getMedia_bet_vlt() {
		return media_bet_vlt;
	}
	public void setMedia_bet_vlt(Double media_bet_vlt) {
		this.media_bet_vlt = media_bet_vlt;
	}
	public Double getTotal_bet_sala() {
		return total_bet_sala;
	}
	public void setTotal_bet_sala(Double total_bet_sala) {
		this.total_bet_sala = total_bet_sala;
	}
	public int getNum_vlt() {
		return num_vlt;
	}
	public void setNum_vlt(int num_vlt) {
		this.num_vlt = num_vlt;
	}

	public void setPercent_max_bet(int percent_max_bet) {
		this.percent_max_bet = percent_max_bet;
	}

	public int getPercent_max_bet() {
		return percent_max_bet;
	}

	public void setMedia_7gg(Double media_7gg) {
		this.media_7gg = media_7gg;
	}

	public Double getMedia_7gg() {
		return media_7gg;
	}

	public void setMedia_30gg(Double media_30gg) {
		this.media_30gg = media_30gg;
	}

	public Double getMedia_30gg() {
		return media_30gg;
	}

	public void setGs_vlt_id(String gs_vlt_id) {
		this.gs_vlt_id = gs_vlt_id;
	}

	public String getGs_vlt_id() {
		return gs_vlt_id;
	}

	
}
