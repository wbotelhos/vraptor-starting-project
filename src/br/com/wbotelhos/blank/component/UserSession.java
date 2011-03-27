package br.com.wbotelhos.blank.component;

import java.io.Serializable;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.SessionScoped;
import br.com.wbotelhos.blank.model.Usuario;

@Component
@SessionScoped
public class UserSession implements Serializable {

	private static final long serialVersionUID = -2122338680139826090L;

	private Usuario user;

	public boolean isLogged() {
		return user != null;
	}

	public void logout() {
		user = null;
	}

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}

}