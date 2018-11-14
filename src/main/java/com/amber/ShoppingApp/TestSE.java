package com.amber.ShoppingApp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.amber.ShoppingApp.util.SerialUtil;

public class TestSE {

	public static void main(String[] args) throws ParseException {
//		String num = "0.96";
//		BigDecimal bd = BDUtil.getBigDecimalFromString(num, "0.##");
//		
////		// Create a DecimalFormat that fits your requirements
////		DecimalFormatSymbols symbols = new DecimalFormatSymbols();
//////		symbols.setGroupingSeparator(',');
////		symbols.setDecimalSeparator('.');
////		String pattern = "#.##";
////		DecimalFormat decimalFormat = new DecimalFormat(pattern, symbols);
////		decimalFormat.setParseBigDecimal(true);
////
////		// parse the string
////		BigDecimal bd = (BigDecimal) decimalFormat.parse("0.96");
//		System.out.println(bd);
//		System.out.println(bd.getClass().getName());
//		Date sysDate = new Date();
		
//		String yourString = "2018-11-14";
//		System.out.println("Hello");
//		try {
//		    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//		    Date parsedDate = dateFormat.parse(yourString);
//		    Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
//		    System.out.println(timestamp.getClass().getName());
//		} catch(Exception e) { 
//			e.printStackTrace();
//		}
//		
//		String numIn = "1";
//		Integer numOut = Integer.parseInt(numIn) + 1;
//		System.out.println(String.format("%05d", Integer.parseInt(numIn)));
//		System.out.println(String.format("%05d", Integer.parseInt(numOut.toString())));
//		Map<String, String> map = new HashMap<>();
//		map.put("00001", "1");
//		Integer in = new Integer(map.get("00001"));
//		Integer out = new Integer(in + 1);
//		map.put("00001",out.toString());
//		System.out.println(map.get("00001"));
		
//		Map<String, String> cart = new HashMap<>();
//		//判斷商品是否已存在購物車中
//		cart.put("001", "2");
//		String qtyInCartS = cart.get("001");
//		System.out.println(qtyInCartS);
//		if (qtyInCartS != null) {
//			//取得購物車中的商品數量
//			Integer qtyInCart = Integer.parseInt(qtyInCartS);
//			System.out.println(qtyInCart);
//			//更新購物車中的數量
//			Integer total = new Integer(qtyInCart + Integer.parseInt("3"));
//			System.out.println(total);
//			
//			cart.put("001", total.toString());
//		} else {
//			cart.put("001", "2");
//		}
//		System.out.println(cart);
//		
//
//		for (Map.Entry<String, String> entry : cart.entrySet())
//		{
//		    System.out.println(entry.getKey() + "/" + entry.getValue());
//		}
//		
//	}
//		System.out.println(SerialUtil.increment("2", "%014d"));
//		Integer maxItem = 1;
//		System.out.println(maxItem);
//			maxItem = Integer.parseInt("1") + 1;
//		System.out.println(maxItem);
//	
		//目前時間
//		Date date = new Date();
//		//設定日期格式-- 20181201xxxxxx 流水號
////		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//		//進行轉換
//		String dateString = sdf.format(date);
////		System.out.println(SerialUtil.increment("2", "%014d"));
//		
//		
//		System.out.println(dateString + SerialUtil.increment("0", "%06d"));
//		System.out.println(SerialUtil.increment("0", "%d"));
		String a = "20181114000001";
		System.out.println(a.substring(8));
		
		
	}
}
