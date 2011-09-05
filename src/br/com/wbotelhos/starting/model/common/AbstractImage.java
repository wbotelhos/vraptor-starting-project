package br.com.wbotelhos.starting.model.common;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.persistence.MappedSuperclass;

import br.com.caelum.vraptor.interceptor.download.InputStreamDownload;

@MappedSuperclass
public abstract class AbstractImage extends AbstractEntity {

	private static final long serialVersionUID = 7003348999944464969L;

	private static final String IMAGE_DEFAULT = "default.jpg"; 
	private static final String IMAGE_NOT_FOUND = "not-found.png";
	private static final String THUMB_FOLDER = "thumb";
	public static final String IMAGE_PATH = System.getProperty("user.home") + File.separator + "startig" + File.separator + "img";

	private String imagem;

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public abstract String getFolderName();

	/** IMAGE **/
	public String getImageFolderPath() {																	// filme
		return IMAGE_PATH + File.separator + this.getFolderName();
	}

	public String getImagePath() {																			// filme/1.jpg
		return this.getImageFolderPath() + File.separator + this.getImagem();
	}

	public InputStreamDownload getImage() {																	// File(filme/1.jpg)
		return this.getStream(this.getImagePath());
	}

	/* thumb */
	public String getThumbFolderPath() {																	// filme/thumb
		return this.getImageFolderPath() + File.separator + THUMB_FOLDER;
	}
	
	public String getThumbPath() {																			// filme/thumb/1.jpg
		return this.getThumbFolderPath() + File.separator + this.getImagem();
	}


	public InputStreamDownload getThumb() {																	// File(filme/thumb/1.jpg)
		return this.getStream(this.getThumbPath());
	}

	/** GALLERY **/
	public String getImageGalleryFolderPath() {																// filme/1
		return this.getImageFolderPath() + File.separator + this.getId();
	}

	public String getImageGalleryPath(String fileName) {													// filme/1/1.jpg
		return this.getImageGalleryFolderPath() + File.separator + fileName;
	}

	public InputStreamDownload getImageGallery(String fileName) {											// File(filme/1/1.jpg)
		return this.getStream(this.getImageGalleryFolderPath() + File.separator + fileName);
	}

	/* thumb */
	public String getThumbGalleryFolderPath() {																// filme/thumb/1
		return this.getImageFolderPath() + File.separator + THUMB_FOLDER + File.separator + this.getId();
	}

	public String getThumbGalleryPath(String fileName) {													// filme/thumb/1/1.jpg
		return this.getThumbGalleryFolderPath() + File.separator + fileName;
	}

	public InputStreamDownload getThumbGallery(String fileName) {											// File(filme/thumb/1/1.jpg)
		return this.getStream(this.getThumbGalleryFolderPath() + File.separator + fileName);
	}

	/* default image */
	public InputStreamDownload getDefaultImage() {
		return this.getStream(this.getImageFolderPath() + File.separator + IMAGE_DEFAULT);
	}

	public boolean hasDefaultImage() {
		return this.getImagem().equalsIgnoreCase(IMAGE_DEFAULT);
	}

	/* default image */
	protected String getDownloadFileName() {
		return (this.getImagem() == null) ? null : this.getImagem().substring(0, this.getImagem().indexOf("."));
	}

	private String getDownloadFileName(File file) {
		String fileName = this.getDownloadFileName();

		return (fileName == null) ? "default.jpg" : fileName;
	}

	private InputStreamDownload getStream(String path) {
		File file = new File(path);

		if (!file.exists()) {
			if (file.getName().endsWith(IMAGE_DEFAULT)) {
				return this.getNotFoundImage();
			}

			return this.getDefaultImage();
		}

		try {
			return new InputStreamDownload(new FileInputStream(file), "image/jpeg", this.getDownloadFileName(file), false, file.length());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return this.getNotFoundImage();
		}
	}

	public InputStreamDownload getNotFoundImage() {
		File file = new File(IMAGE_PATH, IMAGE_NOT_FOUND);

		try {
			return new InputStreamDownload(new FileInputStream(file), "image/jpeg", IMAGE_NOT_FOUND, false, file.length());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void resize(String path, int width, int height) {
		try {
			BufferedImage image = ImageIO.read(new File(path));

			int type = (image.getTransparency() == Transparency.OPAQUE) ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB;

			int imageWidth = image.getWidth();
			int imageHeight = image.getHeight();

			if (width < imageWidth || height < imageHeight) {
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
			}

			ImageIO.write(image, "jpeg", new File(this.getThumbPath()));
		} catch (IOException e) {
			System.out.println(new SimpleDateFormat("HH:mm:ss").format(new Date()).toString() + "[ERROR] " + e.getMessage());
		}
	}

}