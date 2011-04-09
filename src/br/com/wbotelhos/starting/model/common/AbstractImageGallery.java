package br.com.wbotelhos.starting.model.common;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractImageGallery extends AbstractEntity {

	private static final long serialVersionUID = 3910848666317936736L;

	private String imagem;
	private String titulo;
	private String descricao;

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
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