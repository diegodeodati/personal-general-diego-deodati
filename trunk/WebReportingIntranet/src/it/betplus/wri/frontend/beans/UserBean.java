package it.betplus.wri.frontend.beans;

import java.util.Date;

import it.betplus.web.framework.frontend.beans.BeanBase;
import it.betplus.wri.db.dto.Session;

public class UserBean extends BeanBase {
	
	private static final long serialVersionUID = 1765983525997673573L;
	
	private int EmployeeID;
    private String EmployeeName;    
    private Date Fecha;
    private int Year;
    private int Month;
    private int Day;    
    private int Hora;
    private int Minuto;
	
    private String username;
    private Date loginDate;
    
	public UserBean(){
		
	}
			
	public UserBean(Session dto, boolean useReflection) {
		
		try {
			
			if(useReflection) {
			
				beanReflectionFromDto(dto);		
		  
			} 
			    
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	public int getEmployeeID() {
		return EmployeeID;
	}

	public void setEmployeeID(int employeeID) {
		EmployeeID = employeeID;
	}

	public String getEmployeeName() {
		return EmployeeName;
	}

	public void setEmployeeName(String employeeName) {
		EmployeeName = employeeName;
	}

	public Date getFecha() {
		return Fecha;
	}

	public void setFecha(Date fecha) {
		Fecha = fecha;
	}

	public int getYear() {
		return Year;
	}

	public void setYear(int year) {
		Year = year;
	}

	public int getMonth() {
		return Month;
	}

	public void setMonth(int month) {
		Month = month;
	}

	public int getDay() {
		return Day;
	}

	public void setDay(int day) {
		Day = day;
	}

	public int getHora() {
		return Hora;
	}

	public void setHora(int hora) {
		Hora = hora;
	}

	public int getMinuto() {
		return Minuto;
	}

	public void setMinuto(int minuto) {
		Minuto = minuto;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}
	
}
