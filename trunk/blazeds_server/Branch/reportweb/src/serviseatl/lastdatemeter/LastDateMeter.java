package serviseatl.lastdatemeter;
import java.io.Serializable;
import java.util.Date;
public class LastDateMeter implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8785065722019741776L;
    private Date  LastDate; 

    public LastDateMeter() {
   	
    }
    
    public LastDateMeter(Date LastDate)    
    {
		this.LastDate = LastDate;
    }

	public void setLastDate(Date lastDate) {
		LastDate = lastDate;
	}

	public Date getLastDate() {
		return LastDate;
	}

}
