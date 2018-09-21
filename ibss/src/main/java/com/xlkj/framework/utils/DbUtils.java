package com.xlkj.framework.utils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;


public class DbUtils {
	
	private static final String IMPLICTTEMPLATE = "%1$s%2$s%3$s";

    //首部%
    public static String getPrefixImplict(String str) {
    	if(str != null) {
    		return String.format(IMPLICTTEMPLATE,"%",str,"");
    	} else {
    		return "%%";
    	}
    }
    
    //尾部%
    public static String getSuffixImplict(String str) {
    	if(str != null) {
    		return String.format(IMPLICTTEMPLATE, "", str, "%");
    	} else {
    		return "%%";
    	}
    }
    
    //首尾%
    public static String getFullImplict(String str) {
    	if(str != null) {
    		 return String.format(IMPLICTTEMPLATE, "%", str, "%");
    	} else {
    		return "%%";
    	}
    }
    
    /**
    * @Description:产生一个数据库的主键 
    * @return String
     */
    public static String createId() {
    	return UUID.randomUUID().toString();
    }
    
    /**
    * @Description: 得到当前日期（yyyy-MM-dd格式）
    * @return String
     */
    public static String getDate() {
		DateFormat date = new SimpleDateFormat("yyyy-MM-dd");
		return date.format(new Date());
	}
    
    /**
    * @Description:	得到当前时间（yyyy-MM-dd HH:mm:ss格式） 
    * @return String
     */
    public static String getTime() {
		DateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return date.format(new Date());
	}
    
    /**
    * @Description: 在指定的日期上加一天
    * @param sourceDate	指定的日期
    * @return String	加一天之后的日期
     */
    public static String addOneDay(String sourceDate) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

		try {
			Date dd = df.parse(sourceDate);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(dd);
			calendar.add(Calendar.DAY_OF_MONTH, 1);// 加一天
			return df.format(calendar.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
    
    /**
    * @Description:	 在指定的日期上加一天
    * @param sourceDate	指定的日期
    * @return String	加一天之后的日期
     */
    public static String minusOneDay(String sourceDate) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

		try {
			long dif = df.parse(sourceDate).getTime() - 86400 * 1000;// 减一天
			Date date = new Date();
			date.setTime(dif);
			return df.format(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
    
    /**
    * @Description:	将日期转换成yyyy-MM-dd格式 
    * @param dateSource
    * @return String
     */
    public static String dateFormat(String dateSource) {
    	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    	try {
			return df.format(df.parse(dateSource));
		} catch (ParseException e) {
			e.printStackTrace();
			return dateSource;
		}
    }
    
    /**
    * @Description:	十进制转换成二进制 ()
    * @param decimalSource
    * @return String
     */
    public static String decimalToBinary(int decimalSource) {
    	BigInteger bi = new BigInteger(String.valueOf(decimalSource));	//转换成BigInteger类型
    	return bi.toString(2);	//参数2指定的是转化成X进制，默认10进制
    }
    
    /**
    * @Description:	二进制转换成十进制 
    * @param binarySource
    * @return int
     */
    public static int binaryToDecimal(String binarySource) {
    	BigInteger bi = new BigInteger(binarySource, 2);	//转换为BigInteger类型
    	return Integer.parseInt(bi.toString());		//转换成十进制
    }
    
    /**
    * @Description:	将两个十进制数按位异或运算，返回运算结果
    * @param i1
    * @param i2
    * @return Integer
     */
    public static String bitOperation(Integer i1, Integer i2) {
    	Integer binary1 = Integer.parseInt(decimalToBinary(i1));
    	Integer binary2 = Integer.parseInt(decimalToBinary(i2));
    	return String.valueOf(binary1 ^ binary2);
    }
    
    /**
	* @Description: 将十进制转换成二进制然后获取标识位‘1’所在位置，从右往左排序
	* @param decimalSource
	* @return List<Integer>
	 */
	public static List<Integer> getTag1(int decimalSource) {
		List<Integer> list = new ArrayList<Integer>();

		if(decimalSource <= 0) {
			return list;
		} else {
			String binaryString = decimalToBinary(decimalSource);
			//将字符串倒序排列
			String inverteString = new StringBuffer(binaryString).reverse().toString();
			//第一个1所在位置
			int temp = inverteString.indexOf("1");
			while(temp >= 0) {
				list.add(++temp);
				temp = inverteString.indexOf("1", temp);
			}
			return list;
		}
	}
	
	/**
	* @Description:	将用，分隔的字符串转换成sql语句中in后面的参数样式 
	* @param keys
	* @return String
	 */
	public static String convertToParamIn(String keys) {
		String[] myKeys = keys.split(",");
		StringBuffer s = new StringBuffer();
		s.append("(");
		for(String temp : myKeys) {
			s.append("'" + temp + "',");
		}
		String result = s.substring(0, s.length()-1);
		result += ")";
		return result;
	}
    
    /**
    * @Description: 加密
    * @param src
    * @return String
     */
    public static String ibssEncrypt(String src)
	{
		return DESEncrypt(src, "shxtshxt").toUpperCase(); 	
	}
    
    /**
    * @Description: 解密
    * @param src
    * @return String
     */
	public static String ibssDecrypt(String src)
	{
		return DESDecrypt(src, "shxtshxt"); 	
	}
    
	public static String DESDecrypt(String message,String key)  {
		try {
	        byte[] bytesrc =convertHexString(message);
	        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
	        DESKeySpec desKeySpec = new DESKeySpec(key.getBytes("UTF-8"));
	        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
	        SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
	        IvParameterSpec iv = new IvParameterSpec(key.getBytes("UTF-8"));
	        cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
	        byte[] retByte = cipher.doFinal(bytesrc);
	        return new String(retByte);
		} catch (Exception e) {
			return "";
		}	

    }

	public static String DESEncrypt(String message, String key)	  {
		try {
			byte[] en = Encrypt(message, key);
			String result = toHexString(en);
			return result;
		} catch (Exception e) {
			return "";
		}

	}

    public static byte[] Encrypt(String message, String key) throws Exception {    	
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        DESKeySpec desKeySpec = new DESKeySpec(key.getBytes("UTF-8"));
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
        IvParameterSpec iv = new IvParameterSpec(key.getBytes("UTF-8"));
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
        return cipher.doFinal(message.getBytes("UTF-8"));
    }
        
    public static byte[] convertHexString(String ss)
    {
	    byte digest[] = new byte[ss.length() / 2];
	    for(int i = 0; i < digest.length; i++)
	    {
		    String byteString = ss.substring(2 * i, 2 * i + 2);
//		    String byteString = ss.substring(2 * i,  2);
		    int byteValue = Integer.parseInt(byteString, 16);
		    digest[i] = (byte)byteValue;
	    }
	    return digest;
    }  
    
	 public static String toHexString(byte b[]) 
	 { 
		 StringBuffer hexString = new StringBuffer(); 
		 for (int i = 0; i < b.length; i++) 
		 { 
			 String plainText = Integer.toHexString(0xff & b[i]); 
			 if (plainText.length() < 2) 
				 plainText = "0" + plainText; 
			 hexString.append(plainText); 			
		 } 
		 return hexString.toString(); 		 
	 } 
	 
	 /**
	 * @Description: 将数字转化成整型
	 * @param dataSource
	 * @return String
	  */
	 public static String formatToInt(double dataSource) {
		 DecimalFormat df = new DecimalFormat("#");
		 return df.format(dataSource);
	 }
	 
	 /**
	 * @Description:生成ID(new Date().getTime()) 
	 * @return String
	  */
	 public static String generateID() {
		 return String.valueOf(new Date().getTime());
	 }
	
	 /**
	  * @Description:	将String转换成Double，并且四舍五入保留小数点后两位
	  * @return String
	  */
	 public static Double StringToDouble(String str){
			if(str == null || str.equals("")){
				str="0";
			}
		 	BigDecimal b = new BigDecimal(Double.parseDouble(str));  
			return b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		}
	 
	 /**
	 * @Description:文本换行
	 * @return String
	  */
	 public static String ToBr(String str,int num) {
		if(str!=null&&!str.equals("")){
			 String newStr=null;
			 if(str.length()<num){
				 newStr=str.substring(0,str.length());
			 }else{
				 newStr=str.substring(0,num)+"<br>";
				 int time=str.length()-str.length()%num;
				 for(int i=num;i<time;i=i+num){  
					 newStr=newStr+str.substring(i, i+num)+"<br>";
				 }
				 if(str.length()%num!=0){
					 newStr=newStr+str.substring(time);
				 }else{
					 newStr=newStr.substring(0,newStr.length()-4);
				 }
			 }
			 str=newStr;
		 }
		 return str;
	 }
		/**
	     * 生成email信息字符串
	     * @param email:property文件中获取的字符串
	     * @param parameters:需要传入的参数，可多个，按占位符顺序传入
	     * @return
	     */
	    public static String getMessageContent(String sms,String ...parameters ) {

	    	for(int i=0;i<parameters.length;i++){
	    		sms = sms.replace("{"+i+"}",parameters[i]);
	    	}
	         return sms;
	    }
}