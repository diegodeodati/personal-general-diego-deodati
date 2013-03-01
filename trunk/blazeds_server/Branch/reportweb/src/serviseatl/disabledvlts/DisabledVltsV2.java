package serviseatl.disabledvlts;

import java.io.Serializable;

public class DisabledVltsV2 implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4104370059923512710L;

    private String aamsvltid;
    private String FechaStr;
    public DisabledVltsV2() {
    	
    }
    
    public DisabledVltsV2(
        String aamsvltid,
        String FechaStr){
    	this.aamsvltid = aamsvltid;
    	this.FechaStr = FechaStr;
    }


	public String getAamsvltid() {
		return aamsvltid;
	}

	public void setAamsvltid(String aamsvltid) {
		this.aamsvltid = aamsvltid;
	}

	public void setFechaStr(String fechaStr) {
		FechaStr = fechaStr;
	}
	public String getFechaStr() {
		return FechaStr;
	}

}
