package br.com.wbotelhos.starting.util;

public class Image {

	private static final String[] ALLOWED_FILE = { ".jpg", ".jpeg", ".gif", ".bmp", ".png" };

	public static String getExtension(String fileName) {
		int last = fileName.lastIndexOf(".");
		String extensao = (last > 0) ? fileName.substring(last) : "";
		return (extensao.equalsIgnoreCase(".jpeg") ? ".jpg" : extensao).toLowerCase();
	}

	public static boolean isValidFile(String fileName) {
		String extension = getExtension(fileName);

		for (String item : ALLOWED_FILE) {
			if (extension.endsWith(item)) {
				return true;
			}
		}

		return false;
	}

}