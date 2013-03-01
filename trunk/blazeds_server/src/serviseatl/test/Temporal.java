package serviseatl.test;

import java.io.Serializable;

public class Temporal  implements Serializable {

    static final long serialVersionUID = 103844514001007702L;
    
    private int a;
    private String b;
    private String c;
    private String d;
    
    public Temporal() {
    	
    }
    
    public Temporal(int a, String b, String c, String d)    
    {
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
	
	}

    public int geta() {
		return a;
	}
	public void seta(int a) {
		this.a = a;
	}
	public String getb() {
		return b;
	}
	public void setb(String b) {
		this.b = b;
	}
	public String getc() {
		return c;
	}
	public void setc(String c) {
		this.c = c;
	}
	public String getd() {
		return d;
	}
	public void setd(String d) {
		this.d = d;
	}

}