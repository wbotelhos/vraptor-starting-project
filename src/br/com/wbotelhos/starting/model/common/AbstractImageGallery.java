package br.com.wbotelhos.starting.model.common;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractImageGallery extends AbstractEntity {

	private static final long serialVersionUID = 5035370118565309661L;

	private String imageName;
	private String titulo;
	private String descricao;

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
