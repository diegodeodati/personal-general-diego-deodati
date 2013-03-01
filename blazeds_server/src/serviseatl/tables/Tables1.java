package serviseatl.tables;

import java.io.Serializable;

public class Tables1 implements Serializable {

    static final long serialVersionUID = 103110004401000002L;
    
    private int CodGameTable;
    private int CodGrupoTabla;
    private String CodigoTabla;
    private String Cash;
    private String Marker;
    private String Credit;
    private String Fill;
    private String Result;
   
    public Tables1() {
    	
    }

    public Tables1( int CodGameTable, int CodGrupoTabla, String CodigoTabla, String Cash,
    	    String Marker, String Credit, String Fill, String Result)    
 
    {
		this.CodGameTable = CodGameTable;
		this.CodGrupoTabla = CodGrupoTabla;
		this.CodigoTabla = CodigoTabla;
		this.Cash = Cash;
		this.Marker = Marker;
		this.Credit = Credit;
		this.Fill = Fill;
		this.Result = Result;
		
	}

	public void setCodGameTable(int codGameTable) {
		CodGameTable = codGameTable;
	}

	public int getCodGameTable() {
		return CodGameTable;
	}

	public void setCodGrupoTabla(int codGrupoTabla) {
		CodGrupoTabla = codGrupoTabla;
	}

	public int getCodGrupoTabla() {
		return CodGrupoTabla;
	}

	public void setCodigoTabla(String codigoTabla) {
		CodigoTabla = codigoTabla;
	}

	public String getCodigoTabla() {
		return CodigoTabla;
	}

	public void setCash(String cash) {
		Cash = cash;
	}

	public String getCash() {
		return Cash;
	}

	public void setMarker(String marker) {
		Marker = marker;
	}

	public String getMarker() {
		return Marker;
	}

	public void setCredit(String credit) {
		Credit = credit;
	}

	public String getCredit() {
		return Credit;
	}

	public void setFill(String fill) {
		Fill = fill;
	}

	public String getFill() {
		return Fill;
	}

	public void setResult(String result) {
		Result = result;
	}

	public String getResult() {
		return Result;
	}

}