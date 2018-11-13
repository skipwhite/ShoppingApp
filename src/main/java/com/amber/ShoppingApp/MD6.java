package com.amber.ShoppingApp;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import com.amber.ShoppingApp.util.SerialUtil;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;  
import java.security.NoSuchAlgorithmException;  
  
public class MD6 {  
  
   public byte[] eccrypt(String info) throws NoSuchAlgorithmException, UnsupportedEncodingException   {  
       byte[] bytesOfMessage = info.getBytes("UTF-8");
	   // 根據 MD5 演算法生成 MessageDigest 物件  
       MessageDigest md5 = MessageDigest.getInstance("MD5");  
//       byte[] srcBytes = info.getBytes();  
       // 使用 srcBytes 更新摘要  
       md5.update(bytesOfMessage);  
       // 完成哈希計算，得到 result  
       byte[] resultBytes = md5.digest(bytesOfMessage);  
       return resultBytes;  
   }  
  
   public static void main(String args[]) throws         NoSuchAlgorithmException, UnsupportedEncodingException  {  
//       String msg = "郭XX-精品相聲技術";  
//       MD5 md5 = new MD5();  
//       byte[] resultBytes = md5.eccrypt(msg);  
	   
       String msg = "1234qwer";  
       MD6 md5 = new MD6();  
       byte[] resultBytes = md5.eccrypt(msg);  
       System.out.println("密文是：" + new String(resultBytes));  
       System.out.println("明文是：" + msg);  
       System.out.println(resultBytes);  
       System.out.println(new String(resultBytes).equals(md5.eccrypt(msg)));  
   }  
}  

