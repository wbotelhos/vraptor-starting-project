package br.com.wbotelhos.starting.model.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.persistence.MappedSuperclass;

import br.com.caelum.vraptor.interceptor.download.InputStreamDownload;

@MappedSuperclass
public abstract class AbstractImage extends AbstractEntity {

	private static final long serialVersionUID = 6961638080836404305L;

	private static final String IMAGE_POSTER_NAME = "poster-"; 
	private static final String IMAGE_DEFAULT = "default.jpg"; 
	public static final String IMAGE_PATH = "/Users/botelho/movy/img";

	private String imagem;

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public abstract String getFolderName();

	public String getFolderPath() {
		return IMAGE_PATH + File.separator + this.getFolderName();
	}

	public String getThumbPath() {
		return this.getFolderPath() + File.separator + this.getImagem();
	}

	public InputStreamDownload getThumb() throws FileNotFoundException {
		return this.getStream(this.getImagePath());
	}

	public String getImagePath() {
		return this.getFolderPath() + File.separator + this.getImagem();
	}

	public InputStreamDownload getImage() throws FileNotFoundException {
		return this.getStream(IMAGE_POSTER_NAME + this.getImagem());
	}

	public InputStreamDownload getImageDefault() throws FileNotFoundException {
		File file = new File(IMAGE_PATH + File.separator + this.getFolderName(), IMAGE_DEFAULT);
		return new InputStreamDownload(new FileInputStream(file), "image/jpeg", IMAGE_DEFAULT, false, file.length());
	}

	public String getDownloadFileName() {
		return this.getImagem().substring(0, this.getImagem().indexOf("."));
	}

	public boolean hasImageDefault() {
		return this.getImagem().equalsIgnoreCase(IMAGE_DEFAULT);
	}

	protected InputStreamDownload getStream(String path) throws FileNotFoundException {
		File file = new File(path);

		if (!file.exists() || file.getName().endsWith(IMAGE_DEFAULT)) {
			return this.getImageDefault();
		}

		String downloadFileName = this.getDownloadFileName();

		if (this.getDownloadFileName() != null) {
			downloadFileName = this.getDownloadFileName().replaceAll(" ", "-").replaceAll("---", "_");
		}

		return new InputStreamDownload(new FileInputStream(file), "image/jpeg", downloadFileName, false, file.length());
	}

}