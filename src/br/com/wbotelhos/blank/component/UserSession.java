package br.com.wbotelhos.blank.component;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.SessionScoped;
import br.com.wbotelhos.blank.model.Usuario;

@Component
@SessionScoped
public class UserSession implements java.io.Serializable {

	private static final long serialVersionUID = -7840810249105080395L;

	private Usuario user;

	public boolean isLogged() {
		return user != null;
	}

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}

}