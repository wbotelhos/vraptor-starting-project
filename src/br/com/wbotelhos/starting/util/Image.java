package br.com.wbotelhos.starting.util;

public class Image {

	private static final String[] ALLOWED_FILE = { ".jpg", ".jpeg", ".gif", ".bmp", ".png" };

	public static String getExtension(String fileName) {
		int last = fileName.lastIndexOf(".");
		String extension = (last > 0) ? fileName.substring(last) : "";
		return (extension.equalsIgnoreCase(".jpeg") ? ".jpg" : extension).toLowerCase();
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
