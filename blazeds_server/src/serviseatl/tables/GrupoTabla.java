package serviseatl.tables;

import java.io.Serializable;

public class GrupoTabla implements Serializable {

    static final long serialVersionUID = 103111004401000002L;
    
    private int CodGrupoTabla;
    private String NGrupoTabla;
   
    public GrupoTabla() {
    	
    }

    public GrupoTabla( int CodGrupoTabla, String NGrupoTabla)    
    {
		this.CodGrupoTabla = CodGrupoTabla;
		this.NGrupoTabla = NGrupoTabla;
		
	}

	public void setCodGrupoTabla(int codGrupoTabla) {
		CodGrupoTabla = codGrupoTabla;
	}

	public int getCodGrupoTabla() {
		return CodGrupoTabla;
	}

	public void setNGrupoTabla(String nGrupoTabla) {
		NGrupoTabla = nGrupoTabla;
	}

	public String getNGrupoTabla() {
		return NGrupoTabla;
	}
}