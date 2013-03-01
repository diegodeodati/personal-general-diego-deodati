package it.betplus.awg.db.dto;

import java.io.Serializable;
import java.util.Date;

public class ManufactureDateDTO implements Serializable {
	private static final long serialVersionUID = 1737320865975070893L;
	
	private String manufacturerid;
	private Date maxdateimported;
		
	
	public ManufactureDateDTO() {
		
	}
	
	public ManufactureDateDTO(String manufacturerid, Date maxdateimported) 
	{
		this.manufacturerid = manufacturerid;
		this.maxdateimported = maxdateimported;
	}

	public Date getMaxdateimported() {
		return maxdateimported;
	}

	public void setMaxdateimported(Date maxdateimported) {
		this.maxdateimported = maxdateimported;
	}

	public String getManufacturerid() {
		return manufacturerid;
	}

	public void setManufacturerid(String manufacturerid) {
		this.manufacturerid = manufacturerid;
	}

		
}
