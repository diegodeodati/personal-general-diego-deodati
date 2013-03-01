package it.bplus.util;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {
	
	public static double roundTwoDecimals(double d) {
        DecimalFormat twoDForm = new DecimalFormat("####.##");
        String s = twoDForm.format(d).replace(",",".");
    return Double.valueOf(s);
   }
	
	public static Date calcolaGiornoPrecedente(Date data){				
		Calendar cal = new GregorianCalendar();
		cal.setTime(data);
		cal.add(Calendar.DAY_OF_YEAR, -1);		
		Date ieri = cal.getTime();	
		return ieri;
	}
	
	public static Date calcolaGiornoSuccessivo(Date data){				
		Calendar cal = new GregorianCalendar();
		cal.setTime(data);
		cal.add(Calendar.DAY_OF_YEAR, +1);		
		Date domani = cal.getTime();	
		return domani;
	}
	
	public static Date calcolaSettimanaPrecedente(Date data){				
		Calendar cal = new GregorianCalendar();
		cal.setTime(data);
		cal.add(Calendar.DAY_OF_YEAR, -7);		
		Date dat = cal.getTime();	
		return dat;
	}
	
	public static Date calcola8ggPrima(Date data){				
		Calendar cal = new GregorianCalendar();
		cal.setTime(data);
		cal.add(Calendar.DAY_OF_YEAR, -8);		
		Date dat = cal.getTime();	
		return dat;
	}
	
	public static Date calcolaMesePrecedente(Date data){				
		Calendar cal = new GregorianCalendar();
		cal.setTime(data);
		cal.add(Calendar.DAY_OF_YEAR, -31);		
		Date ieri = cal.getTime();	
		return ieri;
	}
	
	public static Date oraDelleStreghe(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		return cal.getTime();
	}
	
	public static Date ventitreCinquantanove(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		return cal.getTime();
	}
	
	public static Date creaData(int anno, int mese, int giorno, int ore,
			int minuti, int secondi, int millisecondi) {
		Calendar cal = Calendar.getInstance();
		cal.setLenient(false); // in caso di data non valida genera un'eccezione
		cal.set(Calendar.HOUR_OF_DAY, ore);
		cal.set(Calendar.MINUTE, minuti);
		cal.set(Calendar.SECOND, secondi);
		cal.set(Calendar.MILLISECOND, millisecondi);
		cal.set(Calendar.YEAR, anno);
		cal.set(Calendar.MONTH, mese - 1);
		cal.set(Calendar.DAY_OF_MONTH, giorno);
		return cal.getTime();
	}
	
	public static String formatExport(){
		String st = null;
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy_HH.mm.ss");
		st = df.format(new Date());
		
		return st;
	}

}
