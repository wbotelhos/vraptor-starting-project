package br.com.wbotelhos.starting.repository.common;

import java.io.FileNotFoundException;

import br.com.caelum.vraptor.interceptor.multipart.UploadedFile;
import br.com.wbotelhos.starting.model.common.AbstractImage;

public interface GenericImageRepository<T extends AbstractImage> {

	void removeImage(T entity) throws FileNotFoundException;

	void updateImage(T entity);

	void uploadImage(UploadedFile file, T entity) throws Exception;

}