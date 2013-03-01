package serviseatl.compiler;

public class Compiler {
	private int LocationID_Compiler;
	private int CodCasino_Compiler;	
	private static Compiler instance;

	private Compiler()
	{
		//*************definido solo para servidores tomcat primarios*****************
		//Atlantis
		//setLocationID_Compiler(0);
		//setCodCasino_Compiler(3);
		//San Geronimo
		//setLocationID_Compiler(5);
		//setCodCasino_Compiler(1);
		//Curacao
		//setLocationID_Compiler(6);
		//setCodCasino_Compiler(3);
		//italia
		setLocationID_Compiler(8);
		setCodCasino_Compiler(1);
		
	}

	public static Compiler getCompiler(){
		if (instance == null) {
			instance = new Compiler();
		}
		return instance;
	}

	public void setLocationID_Compiler(int locationID_Compiler) {
		LocationID_Compiler = locationID_Compiler;
	}

	public int getLocationID_Compiler() {
		return LocationID_Compiler;
	}

	public void setCodCasino_Compiler(int codCasino_Compiler) {
		CodCasino_Compiler = codCasino_Compiler;
	}

	public int getCodCasino_Compiler() {
		return CodCasino_Compiler;
	}
}