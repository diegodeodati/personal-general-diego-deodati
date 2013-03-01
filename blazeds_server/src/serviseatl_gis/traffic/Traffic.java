package serviseatl_gis.traffic;

import java.io.Serializable;

public class Traffic implements Serializable {
    static final long serialVersionUID = 104832642002002701L;
    
    private int TiempoY;
    private int TiempoM;
    private int TiempoD;
    private int TiempoH;
    private int TiempoMi;
    private int ConTarjeta;
    private int SinTarjeta;
    
    public Traffic() {
    	
    }

    public Traffic(int TiempoY, int TiempoM, int TiempoD, int TiempoH, int TiempoMi, int ConTarjeta, int SinTarjeta)    
    {
		this.TiempoY = TiempoY;
		this.TiempoM = TiempoM;
		this.TiempoD = TiempoD;
		this.TiempoH = TiempoH;
		this.TiempoMi = TiempoMi;
		this.ConTarjeta = ConTarjeta;
		this.SinTarjeta = SinTarjeta;
	}

	public void setTiempoY(int tiempoY) {
		TiempoY = tiempoY;
	}

	public int getTiempoY() {
		return TiempoY;
	}

	public void setTiempoM(int tiempoM) {
		TiempoM = tiempoM;
	}

	public int getTiempoM() {
		return TiempoM;
	}

	public void setTiempoD(int tiempoD) {
		TiempoD = tiempoD;
	}

	public int getTiempoD() {
		return TiempoD;
	}

	public void setTiempoH(int tiempoH) {
		TiempoH = tiempoH;
	}

	public int getTiempoH() {
		return TiempoH;
	}

	public void setTiempoMi(int tiempoMi) {
		TiempoMi = tiempoMi;
	}

	public int getTiempoMi() {
		return TiempoMi;
	}

	public void setConTarjeta(int conTarjeta) {
		ConTarjeta = conTarjeta;
	}

	public int getConTarjeta() {
		return ConTarjeta;
	}

	public void setSinTarjeta(int sinTarjeta) {
		SinTarjeta = sinTarjeta;
	}

	public int getSinTarjeta() {
		return SinTarjeta;
	}

}
