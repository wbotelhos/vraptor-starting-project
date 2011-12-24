package br.com.wbotelhos.starting.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import br.com.wbotelhos.starting.model.common.AbstractImageGallery;

@Entity
public class UsuarioImage extends AbstractImageGallery {

	private static final long serialVersionUID = -5545470204682629302L;

	@ManyToOne
	private Usuario usuario;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
