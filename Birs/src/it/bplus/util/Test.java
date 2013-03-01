package it.bplus.util;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {

	public static String puntoVirgola(BigDecimal x){
		String num = x.toString();
		String app = "";
		String ret = "";
		num = num.replace(".", ",");
		int s;

		if (num.contains(",")) {
			if (num.charAt(num.length() - 3) == ',' && num.length() > 6) {
				s = num.length() - 3;
				ret = ret.concat(num.substring(s));
				for (int i = 0; i <= s - 1; i++) {
					if (i > 0 && i % 3 == 0)
						ret = ".".concat(ret);
					app = num.substring(s - i - 1, s - i);
					ret = app.concat(ret);
				}
			}
			else if (num.charAt(num.length() - 2) == ',' && num.length() > 5) {
				s = num.length() - 2;
				ret = ret.concat(num.substring(s));
				for (int i = 0; i <= s - 1; i++) {
					if (i > 0 && i % 3 == 0)
						ret = ".".concat(ret);
					app = num.substring(s - i - 1, s - i);
					ret = app.concat(ret);
				}
			}
			else ret = num;
		} else if ((!num.contains(",")) && num.length() > 3) {
			s = num.length();
			for (int i = 0; i <= s - 1; i++) {
				if (i > 0 && i % 3 == 0)
					ret = ".".concat(ret);
				app = num.substring(s - i - 1, s - i);
				ret = app.concat(ret);

			}
			ret = ret.concat(",00");
		}

		else
			ret = num.concat(",00");

		return ret;
	}
	
	

	
	public static void main(String[] args) throws ParseException {
		 
		Date data = new Date();
		
		System.out.println(data);

		DateFormat df = new SimpleDateFormat(IConstants.DATE_FORMAT_4);
		
		System.out.println(df.format(DateUtil.ventitreCinquantanove(data)));
		
//		data = df_1.parse(st);
//		System.out.println(data);
//		
//		DateFormat df_2 = new SimpleDateFormat("dd-MM-yyyy");
//		System.out.println(df_2.format(data));		
		

//		
//		Double d = 444301.599999999999;
//		
//		System.out.println(d);
//		
		
		//System.out.println(Double.valueOf(d.toString()));
		
		
//		System.out.println(roundTwoDecimals(d));
		
		
		/*
		double amount = 4000.7+300.9;
		  NumberFormat formatter = new DecimalFormat("#0.000");
		  System.out.println("The Decimal Value is:"+formatter.format(amount));*/
		
		
		
	}
}