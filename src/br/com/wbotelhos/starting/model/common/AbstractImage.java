package br.com.wbotelhos.starting.model.common;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.persistence.MappedSuperclass;

import br.com.caelum.vraptor.interceptor.download.InputStreamDownload;

@MappedSuperclass
public abstract class AbstractImage extends AbstractEntity {

	private static final long serialVersionUID = 7003348999944464969L;

	private static final String IMAGE_DEFAULT = "default.jpg"; 
	private static final String IMAGE_NOT_FOUND = "not-found.jpg"; 
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

	public String getImagePath() {
		return this.getFolderPath() + File.separator + this.getImagem();
	}

	public String getDownloadFileName() {
		return this.getImagem().substring(0, this.getImagem().indexOf("."));
	}

	public boolean hasImageDefault() {
		return this.getImagem().equalsIgnoreCase(IMAGE_DEFAULT);
	}

	public InputStreamDownload getThumbGallery(String fileName, int width, int height) {
		return this.getThumb(this.getFolderPath() + File.separator + this.getId().toString() + File.separator + fileName, width, height);
	}

	public InputStreamDownload getImageGallery(String fileName) {
		return this.getImage(this.getFolderPath() + File.separator + this.getId().toString() + File.separator + fileName);
	}

	public InputStreamDownload getImageDefault() {
		File file = new File(IMAGE_PATH + File.separator + this.getFolderName(), IMAGE_DEFAULT);

		try {
			return new InputStreamDownload(new FileInputStream(file), "image/jpeg", IMAGE_DEFAULT, false, file.length());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return this.getImageNotFound();
		}
	}

	public InputStreamDownload getImageNotFound() {
		File file = new File(IMAGE_PATH + File.separator + this.getFolderName(), IMAGE_NOT_FOUND);

		try {
			return new InputStreamDownload(new FileInputStream(file), "image/jpeg", IMAGE_NOT_FOUND, false, file.length());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	public InputStreamDownload getImage(String path) {
		File file = new File(path);

		if (!file.exists() || file.getName().endsWith(IMAGE_DEFAULT)) {
			return this.getImageDefault();
		}

		try {
			return new InputStreamDownload(new FileInputStream(file), "image/jpeg", this.getDownloadFileName(), false, file.length());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return this.getImageNotFound();
		}
	}

	public InputStreamDownload getThumb(String path, int width, int height) {
		try {
			BufferedImage image = ImageIO.read(new File(path));

			int type = (image.getTransparency() == Transparency.OPAQUE) ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB;
	
			int imageWidth = image.getWidth();
			int imageHeight = image.getHeight();
	
			do {
				if (imageWidth > width) {
					imageWidth /= 2;

					if (imageWidth < width) {
						imageWidth= width;
					}
				}

				if (imageHeight > height) {
					imageHeight /= 2;

					if (imageHeight < height) {
						imageHeight= height;
					}
				}

				BufferedImage imageTemp = new BufferedImage(imageWidth, imageHeight, type);
				Graphics2D graph = imageTemp.createGraphics();
				graph.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
				graph.drawImage(image, 0, 0, imageWidth, imageHeight, null);
				graph.dispose();

				image = imageTemp;
			} while (imageWidth != width || imageHeight != height);

			ByteArrayOutputStream output = new ByteArrayOutputStream();
	
			ImageIO.write(image, "jpeg", output);

			return new InputStreamDownload(new ByteArrayInputStream(output.toByteArray()), "image/jpeg", this.getDownloadFileName(), false, output.size());
		} catch (IOException e) {
			e.printStackTrace();
			return this.getImageNotFound();
		}
	}

}