package br.com.wbotelhos.starting.business.common;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.commons.io.IOUtils;

import br.com.caelum.vraptor.interceptor.multipart.UploadedFile;
import br.com.wbotelhos.starting.exception.CommonException;
import br.com.wbotelhos.starting.exception.UploadException;
import br.com.wbotelhos.starting.model.common.AbstractImage;
import br.com.wbotelhos.starting.model.common.AbstractImageGallery;
import br.com.wbotelhos.starting.repository.common.GenericImageRepository;
import br.com.wbotelhos.starting.util.Image;

public abstract class GenericImageBusiness<T extends AbstractImage, I extends AbstractImageGallery> extends GenericBusiness<T> implements GenericImageRepository<T, I> {

	public GenericImageBusiness(EntityManager manager) {
		super(manager);
	}

	public void removeImage(T entity) throws CommonException {
		if (!entity.hasDefaultImage()) {

			File image = new File(entity.getImagePath());

			if (image.exists() && !image.delete()) {
				throw new CommonException("erro.apagar.imagem");
			}

			entity.setImageName("default.jpg");

			this.updateImage(entity);
		}
	}

	public void save(I entityImage) {
		manager.merge(entityImage);
	}

	public void updateImage(T entity) {
		Query query = manager.createQuery("update " + entity.getClass().getName() + " set imageName = :imageName where id = :id");
		query.setParameter("imageName", entity.getImageName());
		query.setParameter("id", entity.getId());
		query.executeUpdate();
	}

	public void uploadImage(T entity, UploadedFile uploadedFile) throws UploadException {
		if (!Image.isValidFile(uploadedFile.getFileName())) {
			throw new UploadException("imagem.invalida");
		}

		String extension = Image.getExtension(uploadedFile.getFileName());

		entity.setImageName(entity.getId() + extension);

		File folder = new File(entity.getImageFolderPath());

		checkFolders(folder);

		File file = new File(folder, entity.getImageName());

		try {
			IOUtils.copy(uploadedFile.getFile(), new FileOutputStream(file));

			entity.resize(file.getPath(), 200, 200);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new UploadException("caminho.destino.invalido");
		} catch (IOException e) {
			e.printStackTrace();
			throw new UploadException("erro.enviar.arquivo");
		}

		this.updateImage(entity);
	}

	public void uploadGallery(T entity, I entityImage, UploadedFile uploadedFile) throws UploadException {
		String extension = Image.getExtension(uploadedFile.getFileName());

		entityImage.setImageName(System.currentTimeMillis() + extension);

		File folder = new File(entity.getImageFolderPath(), entity.getId().toString());

		checkFolders(folder);

		File file = new File(folder, entityImage.getImageName());

		try {
			IOUtils.copy(uploadedFile.getFile(), new FileOutputStream(file));

			entity.resize(file.getPath(), 200, 200);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new UploadException("caminho.destino.invalido");
		} catch (IOException e) {
			e.printStackTrace();
			throw new UploadException("erro.enviar.arquivo");
		}

		this.save(entityImage);
	}

	private void checkFolders(File folder) {
		if (!folder.exists()) {
			folder.mkdirs();
		}

		File thumbFolder = new File(folder, "thumb");

		if (!thumbFolder.exists()) {
			thumbFolder.mkdirs();
		}
	}
	
}
