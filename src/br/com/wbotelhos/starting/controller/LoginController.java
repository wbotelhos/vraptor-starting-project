package br.com.wbotelhos.starting.controller;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.wbotelhos.starting.business.LoginBusiness;
import br.com.wbotelhos.starting.component.UserSession;
import br.com.wbotelhos.starting.model.Usuario;

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

	@Post("/autenticar")
	public void autenticar(Usuario entity) {
		Usuario user = loginBusiness.autenticar(entity.getEmail(), entity.getSenha());

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