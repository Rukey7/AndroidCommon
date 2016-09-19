package com.dl7.commonlib.utils;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

public class ByteUtil {
	/**
	 * 二进制转换十进制
	 * @param value
	 * @return
	 */
	public static int transfer2to10(String value){
		BigInteger temp = new BigInteger(value,2);
		return temp.intValue();
	}
	
    /* Convert byte[] to hex string.这里我们可以将byte转换成int，然后利用Integer.toHexString(int)来转换成16进制字符串。
    * @param src byte[] data
    * @return hex string
    */   
	public static String bytesToHexString(byte[] src){
       StringBuilder stringBuilder = new StringBuilder("");
       if (src == null || src.length <= 0) {
           return null;
       }
       for (int i = 0; i < src.length; i++) {
           int v = src[i] & 0xFF;
           String hv = Integer.toHexString(v);
           if (hv.length() < 2) {
               stringBuilder.append(0);
           }
           stringBuilder.append(hv + " ");
       }
       return stringBuilder.toString();
   	}
    /**
    * Convert hex string to byte[]
    * @param hexString the hex string
    * @return byte[]
    */
    public static byte[] hexStringToBytes(String hexString) {
       if (hexString == null || hexString.equals("")) {
           return null;
       }
       hexString = hexString.toUpperCase();
       int length = hexString.length() / 2;
       char[] hexChars = hexString.toCharArray();
       byte[] d = new byte[length];
       for (int i = 0; i < length; i++) {
           int pos = i * 2;
           d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
       }
       return d;
   	}
   /**
    * Convert char to byte
    * @param c char
    * @return byte
    */
    public static byte charToByte(char c) {
       return (byte) "0123456789ABCDEF".indexOf(c);
    }
    /**
     * 二进制补全
     * @param binaryString
     * @return
     */
    public static String fill0(String binaryString, int length){
    	String tmp = "";
    	int left = length - binaryString.length();
    	if(left < 0){
    		return binaryString.substring(binaryString.length() - 8, binaryString.length());
    	}
    	for(int i = 0; i < left; ++i){
			tmp += "0";
		}
    	return tmp + binaryString;
    }
    
    /**
     * 二进制补全
     * 默认8
     * @param binaryString
     * @return
     */
    public static String fill0(String binaryString){
    	return fill0(binaryString,8);
    }
    
    public static char byte2Char(byte value){
    	Charset cs = Charset.forName ("UTF-8");
        ByteBuffer bb = ByteBuffer.allocate (1);
        bb.put (value);
        bb.flip ();
        CharBuffer cb = cs.decode (bb);
        return cb.charAt(0);
    }
    
    // 二进制转十进制
    public static int convertAlgorism(String value) {
    	char[] cars = value.toCharArray();
        int result = 0;
        int num = 0;
        for (int i = cars.length - 1; 0 <= i; i--) {
            int temp = 2;
            if (num == 0) {
                temp = 1;
            } else if (num == 1) {
                temp = 2;
            } else {
                for (int j = 1; j < num; j++) {
                    temp = temp * 2;
                }
            }
            int sum = Integer.parseInt(String.valueOf(cars[i]));
            result = result + (sum * temp);
            num++;
        }
        return result;
    }
    
    public static String transfer10to2(int value){
    	int i;
    	StringBuilder sb=new StringBuilder();
    	i = value;
    	int r;
    	do{
    		if(i%2==1){
    			r=1;
			}else{
				r=0;
			}
    		i/=2;
    		sb.insert(0,r);
    	}while(i>0);
    	return sb.toString();
    }
}
