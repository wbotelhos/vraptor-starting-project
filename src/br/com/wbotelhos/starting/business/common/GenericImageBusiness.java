package br.com.wbotelhos.starting.business.common;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.commons.io.IOUtils;

import br.com.caelum.vraptor.interceptor.multipart.UploadedFile;
import br.com.wbotelhos.starting.exception.UploadException;
import br.com.wbotelhos.starting.model.common.AbstractImage;
import br.com.wbotelhos.starting.repository.common.GenericImageRepository;
import br.com.wbotelhos.starting.util.Image;

public abstract class GenericImageBusiness<T extends AbstractImage> extends GenericBusiness<T> implements GenericImageRepository<T> {

	public GenericImageBusiness(EntityManager manager) {
		super(manager);
	}

	public void removeImage(T entity) throws FileNotFoundException {
		if (!entity.hasImageDefault()) {

			File image = new File(entity.getImagePath());

			if (image.exists() && !image.delete()) {
				throw new FileNotFoundException("Não foi possível apagar a imagem.");
			}

			entity.setImagem("default.jpg");

			this.updateImage(entity);
		}
	}

	public void updateImage(T entity) {
		Query query = manager.createQuery("update " + entity.getClass().getName() + " set imagem = :imagem where id = :id");
		query.setParameter("imagem", entity.getImagem());
		query.setParameter("id", entity.getId());
		query.executeUpdate();
	}

	public void uploadImage(T entity, UploadedFile uploadedFile) throws UploadException {
		String extension = Image.getExtension(uploadedFile.getFileName());

		entity.setImagem(entity.getId() + extension);

		File folder = new File(entity.getFolderPath());

		if (!folder.exists()) {
			folder.mkdirs();
		}

		File file = new File(folder, entity.getImagem());

		try {
			IOUtils.copy(uploadedFile.getFile(), new FileOutputStream(file));
		} catch (FileNotFoundException e) {
			throw new UploadException("caminho.destino.invalido");
		} catch (IOException e) {
			e.printStackTrace();
			throw new UploadException("erro.enviar.arquivo");
		}

		this.updateImage(entity);
	}

}