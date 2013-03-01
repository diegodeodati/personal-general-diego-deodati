package serviseatl.eventos;

import java.io.Serializable;

public class SASExceptions implements Serializable {

    static final long serialVersionUID = 109232314421602072L;
    
    private int	Code;
    private String DescriptionE;

    public SASExceptions() {
    	
    }

    public SASExceptions( int Code, String DescriptionE)    
    {
    	this.Code = Code;
    	this.DescriptionE =	DescriptionE;
	}

	public void setCode(int code) {
		Code = code;
	}

	public int getCode() {
		return Code;
	}

	public void setDescriptionE(String descriptionE) {
		DescriptionE = descriptionE;
	}

	public String getDescriptionE() {
		return DescriptionE;
	}

	
}