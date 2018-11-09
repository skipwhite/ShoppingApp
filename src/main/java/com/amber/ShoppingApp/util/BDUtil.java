package com.amber.ShoppingApp.util;


import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;

public class BDUtil {
	/*
	 * String cast to BigDecimal
	 */
	public static BigDecimal getBigDecimalFromString(String bdStr, String pattern) throws ParseException {
		DecimalFormatSymbols symbols = new DecimalFormatSymbols();
//		symbols.setGroupingSeparator(',');
//		symbols.setDecimalSeparator('.');
//		String pattern = "#,##0.0#";
		DecimalFormat decimalFormat = new DecimalFormat(pattern, symbols);
		decimalFormat.setParseBigDecimal(true);

		// parse the string
		BigDecimal bd = (BigDecimal) decimalFormat.parse(bdStr);
		return bd;
	}
	
}
