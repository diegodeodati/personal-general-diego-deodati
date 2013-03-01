package serviseatl_gis.estructura;

import java.io.Serializable;

public class Estructura  implements Serializable {
    static final long serialVersionUID = 104844614003000001L;
    
    private int Capa;
    private int Shp;
    private int Punto;
    private double X;
    private double Y;
    
    
    public Estructura() {
    	
    }
    
    public Estructura(int Capa, int Shp, int Punto, double X, double Y)    
     {
		this.Capa = Capa;
		this.Shp = Shp;
		this.Punto = Punto;
		this.X = X;
		this.Y = Y;
	}

	public void setCapa(int capa) {
		Capa = capa;
	}

	public int getCapa() {
		return Capa;
	}

	public void setShp(int shp) {
		Shp = shp;
	}

	public int getShp() {
		return Shp;
	}

	public void setPunto(int punto) {
		Punto = punto;
	}

	public int getPunto() {
		return Punto;
	}

	public void setX(double x) {
		X = x;
	}

	public double getX() {
		return X;
	}

	public void setY(double y) {
		Y = y;
	}

	public double getY() {
		return Y;
	}

}
