package br.com.wbotelhos.starting.repository.common;

import java.io.FileNotFoundException;

import br.com.caelum.vraptor.interceptor.multipart.UploadedFile;
import br.com.wbotelhos.starting.exception.UploadException;
import br.com.wbotelhos.starting.model.common.AbstractImage;
import br.com.wbotelhos.starting.model.common.AbstractImageGallery;

public interface GenericImageRepository<T extends AbstractImage, I extends AbstractImageGallery> {

	void removeImage(T entity) throws FileNotFoundException;

	void save(I entityImage);

	void updateImage(T entity);

	void uploadImage(T entity, UploadedFile uploadedFile) throws UploadException;

	void uploadGallery(T entity, I entityImage, UploadedFile uploadedFile) throws UploadException;

}