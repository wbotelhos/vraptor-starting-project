package br.com.wbotelhos.starting.util;

public class Image {

	private static final String[] ARQUIVOS_PERMITIDOS = { ".jpg", ".jpeg", ".gif", ".bmp", ".png" };

	public static String getExtension(String fileName) {
		String extensao = (fileName.lastIndexOf(".") > 0) ? fileName.substring(fileName.lastIndexOf(".")) : "";
		return (extensao.equalsIgnoreCase(".jpeg") ? ".jpg" : extensao).toLowerCase();
	}

	public static boolean isValidExtension(String extension) {
		int tam = extension.length();

		if (tam == 4 || (tam == 5 && extension.equalsIgnoreCase(".jpeg"))) {
			extension = extension.toLowerCase();

			for (int i = 0; i < ARQUIVOS_PERMITIDOS.length; i++) {
				if (extension.endsWith(ARQUIVOS_PERMITIDOS[i])) {
					return true;
				}
			}
		}

		return false;
	}

}
