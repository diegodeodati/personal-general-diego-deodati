package serviseatl.location;

import java.io.Serializable;

public class Location implements Serializable {
    static final long serialVersionUID = 103844514089700123L;
    private byte LocationID;
    private String LCountry;
    private String LCity;
    private String Location; 

    public Location() {
   	
    }
    
    public Location(byte LocationID, String LCountry,  String LCity, String Location)    
    {
		this.LocationID = LocationID;
		this.LCountry = LCountry;
		this.LCity = LCity;
		this.Location = Location;
    }

	public void setLocationID(byte locationID) {
		LocationID = locationID;
	}

	public byte getLocationID() {
		return LocationID;
	}

	public void setLCountry(String lCountry) {
		LCountry = lCountry;
	}

	public String getLCountry() {
		return LCountry;
	}

	public void setLocation(String location) {
		Location = location;
	}

	public String getLocation() {
		return Location;
	}

	public void setLCity(String lCity) {
		LCity = lCity;
	}

	public String getLCity() {
		return LCity;
	}
    
      
}
