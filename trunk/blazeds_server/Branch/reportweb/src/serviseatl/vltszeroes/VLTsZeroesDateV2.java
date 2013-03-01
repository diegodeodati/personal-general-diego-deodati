package serviseatl.vltszeroes;

import java.io.Serializable;

public class VLTsZeroesDateV2 implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4433950158728551531L;

	private String DateZero;

	public VLTsZeroesDateV2()
    {
    }
    
    public VLTsZeroesDateV2(String DateZero)
    {
    	this.DateZero = DateZero;
    }

	public void setDateZero(String datezero) {
		DateZero = datezero;
	}
	public String getDateZero() {
		return DateZero;
	}

}
