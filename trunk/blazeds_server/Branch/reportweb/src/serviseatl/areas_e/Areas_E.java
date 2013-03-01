package serviseatl.areas_e;

import java.io.Serializable;

public class Areas_E implements Serializable {

    static final long serialVersionUID = 103144111178521007L;
    
    private String AreaName;

   
    public Areas_E() {
    	
    }

    public Areas_E( String AreaName)    
    {
		this.AreaName = AreaName;
	}

	public void setAreaName(String areaName) {
		AreaName = areaName;
	}

	public String getAreaName() {
		return AreaName;
	}

	

}