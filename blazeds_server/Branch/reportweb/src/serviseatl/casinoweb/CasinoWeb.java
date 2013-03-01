package serviseatl.casinoweb;
import java.io.Serializable;

public class CasinoWeb implements Serializable {
    static final long serialVersionUID = 103844514002000001L;
    private String label;
    private String data;

    public CasinoWeb() {
   	
    }
    
    public CasinoWeb(String label,  String data)    
    {
		this.label = label;
		this.data = data;
    }
    
    public String getlabel() {
		return label;
	}
	public void setlabel(String label) {
		this.label = label;
	}
	public String getdata() {
		return data;
	}
	public void setdata(String data) {
		this.data = data;
	}
    
}
