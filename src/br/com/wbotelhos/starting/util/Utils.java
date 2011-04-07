package br.com.wbotelhos.starting.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Utils {

	public static String encrypt(String text) {
		try {
			MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
			byte messageDigest[] = algorithm.digest(text.getBytes("UTF-8"));

			StringBuilder hexBuild = new StringBuilder();

			for (byte b : messageDigest) {
				hexBuild.append(String.format("%02X", 0xFF & b));
			}

			return hexBuild.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return text;
	}
	
}