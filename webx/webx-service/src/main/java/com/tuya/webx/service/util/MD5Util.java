
package com.tuya.webx.service.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;
import java.text.ParseException;

public class MD5Util {

	protected static Logger	logger	= LoggerFactory.getLogger(MD5Util.class);

	public static String getMD5(byte[] source) {
		String s = null;
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(source);
			byte tmp[] = md.digest(); // MD5 的计算结果是一个 128 位的长整数，
			// 用字节表示就是 16 个字节
			char str[] = new char[16 * 2]; // 每个字节用 16 进制表示的话，使用两个字符，
			// 所以表示成 16 进制需要 32 个字符
			int k = 0; // 表示转换结果中对应的字符位置
			for (int i = 0; i < 16; i++) { // 从第一个字节开始，对 MD5 的每一个字节
				// 转换成 16 进制字符的转换
				byte byte0 = tmp[i]; // 取第 i 个字节
				str[k++] = hexDigits[byte0 >>> 4 & 0xf]; // 取字节中高 4 位的数字转换,
				// >>> 为逻辑右移，将符号位一起右移
				str[k++] = hexDigits[byte0 & 0xf]; // 取字节中低 4 位的数字转换
			}
			s = new String(str); // 换后的结果转换为字符串

		} catch (Exception e) {
			logger.warn("MD5机密异常!", e);
		}
		return s;
	}

//	public static String getMD5(String inputStr) {
//		String md5Str = inputStr;
//		if (inputStr != null) {
//			MessageDigest md;
//			try {
//				md = MessageDigest.getInstance("MD5");
//				md.update(inputStr.getBytes());
//				BigInteger hash = new BigInteger(1, md.digest());
//				md5Str = hash.toString(16);
//				if ((md5Str.length() % 2) != 0) {
//					md5Str = "0" + md5Str;
//				}
//			} catch (NoSuchAlgorithmException e) {
//				logger.error("error message", e);
//			}
//		}
//		return md5Str;
//	}

	public static void main(String[] args) throws ParseException {
		String key = "51/base/eb/05/30/51dbeb0530f8ea29a0db4389de10326b/2014-01-10/1AB0A3E0-A377-4EC9-B0CD-F06D92EAF2C9.jpg";
		String md5value = MD5Util.getMD5(key.getBytes());
		System.out.println(md5value);

	}
}
