package com.lurencun.android.encrypt;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author : 桥下一粒砂
 * @email  : chenyoca@gmail.com
 * @date   : 2012-10-23
 * @desc   : HASH加密工具
 */
public class HashEncrypt {
	
	/**
	 * </br><b>name : </b>		CryptTyep
	 * </br><b>description :</b>加密类型
	 * </br>@author : 			桥下一粒砂
	 * </br><b>e-mail : </b>	chenyoca@gmail.com
	 * </br><b>weibo : </b>		@桥下一粒砂
	 * </br><b>date : </b>		2012-8-4 下午3:04:42
	 */
	public enum CryptType { MD5 ,SHA1,SHA256 };
	
	public static final String ALG_MD5 = "MD5";
	public static final String ALG_SHA1 = "SHA-1";
	public static final String ALG_SHA256 = "SHA-256";
	
	public static String encode(CryptType type, String content) {
		MessageDigest instance = null;
		byte[] encryptMsg = null;
		try {
			if (CryptType.MD5 == type) {
				instance = MessageDigest.getInstance(ALG_MD5);
			} else if (CryptType.SHA1 == type) {
				instance = MessageDigest.getInstance(ALG_SHA1);
			} else if (CryptType.SHA256 == type) {
				instance = MessageDigest.getInstance(ALG_SHA256);
			}
			encryptMsg = instance.digest(content.getBytes());
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("Unbelievabl! How can u passby the method ? No such algorithm !");
		}
		StringBuffer buffer = new StringBuffer();
		for(int i=0; i<encryptMsg.length; i++) {
			switch(Integer.toHexString(encryptMsg[i]).length()) {
			case 1:
				buffer.append("0");
				buffer.append(Integer.toHexString(encryptMsg[i]));
				break;
			case 2:		
				buffer.append(Integer.toHexString(encryptMsg[i]));
				break;
			case 8:
				buffer.append((Integer.toHexString(encryptMsg[i])).substring(6,8));
				break;
			}
		}
		return buffer.toString();
	}
}
