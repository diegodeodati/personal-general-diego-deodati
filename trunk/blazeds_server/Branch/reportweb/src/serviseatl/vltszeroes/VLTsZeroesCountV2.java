package serviseatl.vltszeroes;

import java.io.Serializable;

public class VLTsZeroesCountV2 implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6112007678534636045L;

	private String aamsVltId;
    private int TotalZeroes;
    
    public VLTsZeroesCountV2()
    {
    }
    
    public VLTsZeroesCountV2(String aamsVltId, int TotalZeroes)
    {
    	this.aamsVltId = aamsVltId;
    	this.TotalZeroes = TotalZeroes;
    }

    
	public String getAamsVltId() {
		return aamsVltId;
	}

	public void setAamsVltId(String aamsVltId) {
		this.aamsVltId = aamsVltId;
	}

	public void setTotalZeroes(int totalzeroes) {
		TotalZeroes = totalzeroes;
	}
	public int getTotalZeroes() {
		return TotalZeroes;
	}

}
