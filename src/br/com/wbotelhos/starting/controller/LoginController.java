package br.com.wbotelhos.starting.controller;

import static br.com.caelum.vraptor.view.Results.referer;
import static br.com.wbotelhos.starting.util.Utils.i18n;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.wbotelhos.starting.annotation.Public;
import br.com.wbotelhos.starting.component.UserSession;
import br.com.wbotelhos.starting.model.Usuario;
import br.com.wbotelhos.starting.repository.LoginRepository;

@Resource
public class LoginController {

	private final Result result;
	private final LoginRepository repository;
	private final UserSession userSession;

	public LoginController(Result result, LoginRepository repository, UserSession userSession) {
		this.result = result;
		this.repository = repository;
		this.userSession = userSession;
	}

	@Public
	@Post("/autenticar")
	public void autenticar(Usuario entity) {
		Usuario user = repository.autenticar(entity.getEmail(), entity.getSenha());

		if (user != null) {
			userSession.setUser(user);

			try {
				result.use(referer()).redirect();
			} catch (IllegalStateException e) {
				result.redirectTo(IndexController.class).index();
			}
		} else {
			result.include("error", i18n("email.senha.incorreta")).redirectTo(IndexController.class).index();
		}
	}

	@Get("/logout")
	public void logout() {
		userSession.logout();
		result.redirectTo(IndexController.class).index();
	}

}
