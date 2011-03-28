package br.com.wbotelhos.blank.controller;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.wbotelhos.blank.business.LoginBusiness;
import br.com.wbotelhos.blank.component.UserSession;
import br.com.wbotelhos.blank.model.Usuario;

@Resource
public class LoginController {

	private final Result result;
	private final LoginBusiness loginBusiness;
	private final UserSession userSession;

	public LoginController(Result result, LoginBusiness loginBusiness, UserSession userSession) {
		this.result = result;
		this.loginBusiness = loginBusiness;
		this.userSession = userSession;
	}

	@Post("/login")
	public void login(Usuario usuario) {
		Usuario user = loginBusiness.autenticar(usuario.getEmail(), usuario.getSenha());

		if (user != null) {
			userSession.setUser(user);
	
			result.redirectTo(IndexController.class).index();
		} else {
			result.include("error", "Usu‡rio ou senha incorreta!").redirectTo(IndexController.class).index();
		}
	}

	@Get("/logout")
	public void logout() {
		userSession.logout();
		result.redirectTo(IndexController.class).index();
	}

}