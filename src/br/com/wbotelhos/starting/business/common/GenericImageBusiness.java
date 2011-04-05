package br.com.wbotelhos.starting.business.common;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.commons.io.IOUtils;

import br.com.caelum.vraptor.interceptor.multipart.UploadedFile;
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

	public void uploadImage(T entity, UploadedFile file) throws Exception {
		String extensao = Image.getExtension(file.getFileName());

		if (!Image.isValidExtension(extensao)) {
			throw new Exception("Tipo de arquivo não permitido!\nUse: JPG, JPEG, GIF, BMP ou PNG.");
		}

		entity.setImagem(entity.getId() + extensao);

		File diretorio = new File(entity.getFolderPath());
		
		if (!diretorio.exists()) {
			diretorio.mkdirs();
		}

		IOUtils.copy(file.getFile(), new FileOutputStream(new File(entity.getImagePath())));

		this.updateImage(entity);
	}

}