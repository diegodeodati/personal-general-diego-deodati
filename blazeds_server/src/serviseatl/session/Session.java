package serviseatl.session;

import java.io.Serializable;
import java.util.Date;

public class Session implements Serializable {

    static final long serialVersionUID = 103844519907800000L;
    
    private int EmployeeID;
    private String EmployeeName;    
    private Date Fecha;
    private int Year;
    private int Month;
    private int Day;    
    private int Hora;
    private int Minuto;
    
    public Session() {
    	
    }
    
    public Session(int EmployeeID, String EmployeeName, Date Fecha, int Year, int Month, int Day, int Hora, int Minuto)    
     {
		this.setEmployeeID(EmployeeID);
		this.setEmployeeName(EmployeeName);
		this.setFecha(Fecha);
		this.setYear(Year);
		this.setMonth(Month);
		this.setDay(Day);		
		this.setHora(Hora);
		this.setMinuto(Minuto);		
	}

	public void setEmployeeID(int employeeID) {
		EmployeeID = employeeID;
	}

	public int getEmployeeID() {
		return EmployeeID;
	}

	public void setEmployeeName(String employeeName) {
		EmployeeName = employeeName;
	}

	public String getEmployeeName() {
		return EmployeeName;
	}

	public void setFecha(Date fecha) {
		Fecha = fecha;
	}

	public Date getFecha() {
		return Fecha;
	}

	public void setHora(int hora) {
		Hora = hora;
	}

	public int getHora() {
		return Hora;
	}

	public void setMinuto(int minuto) {
		Minuto = minuto;
	}

	public int getMinuto() {
		return Minuto;
	}

	public void setYear(int year) {
		Year = year;
	}

	public int getYear() {
		return Year;
	}

	public void setMonth(int month) {
		Month = month;
	}

	public int getMonth() {
		return Month;
	}

	public void setDay(int day) {
		Day = day;
	}

	public int getDay() {
		return Day;
	}

}