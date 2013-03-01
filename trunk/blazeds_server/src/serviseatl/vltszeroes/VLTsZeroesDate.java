package serviseatl.vltszeroes;

import java.io.Serializable;

public class VLTsZeroesDate implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1634104018775336366L;
	private String DateZero;
    public VLTsZeroesDate()
    {
    }
    
    public VLTsZeroesDate(String DateZero)
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
