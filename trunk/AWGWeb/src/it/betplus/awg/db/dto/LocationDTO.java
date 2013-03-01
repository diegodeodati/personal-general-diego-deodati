package it.betplus.awg.db.dto;

import java.io.Serializable;

public class LocationDTO implements Serializable {
	private static final long serialVersionUID = -7653933875260255097L;
	
	private String aamslocationname;
	private String aamslocationid;
	private String aamsaddress;
	private String aamsprovince;
	private String aamscity;
	
	
	public LocationDTO() {
		
	}
	
	public LocationDTO(String aamslocationname, String aamslocationid
			, String aamsaddress, String aamsprovince, String aamscity) 
	{
		this.aamslocationname = aamslocationname;
		this.aamslocationid = aamslocationid;
		this.aamsaddress = aamsaddress;
		this.aamsprovince = aamsprovince;
		this.aamscity = aamscity;
	}

	public String getAamslocationname() {
		return aamslocationname;
	}

	public void setAamslocationname(String aamslocationname) {
		this.aamslocationname = aamslocationname;
	}

	public String getAamslocationid() {
		return aamslocationid;
	}

	public void setAamslocationid(String aamslocationid) {
		this.aamslocationid = aamslocationid;
	}

	public String getAamsaddress() {
		return aamsaddress;
	}

	public void setAamsaddress(String aamsaddress) {
		this.aamsaddress = aamsaddress;
	}

	public String getAamsprovince() {
		return aamsprovince;
	}

	public void setAamsprovince(String aamsprovince) {
		this.aamsprovince = aamsprovince;
	}

	public String getAamscity() {
		return aamscity;
	}

	public void setAamscity(String aamscity) {
		this.aamscity = aamscity;
	}
	
}
