package br.com.wbotelhos.starting.controller;

import static br.com.caelum.vraptor.view.Results.referer;

import java.util.Locale;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.wbotelhos.starting.component.UserSession;

@Resource
public class IndexController {

	private final Result result;
	private final UserSession userSession;

	public IndexController(Result result, UserSession userSession) {
		this.result = result;
		this.userSession = userSession;
	}

	@Path("/")
	public void index() {

	}

	@Get("/404")
	public void erro404() {
		result.forwardTo("/404.jsp");
	}
	
	@Get("/500")
	public void erro500() {
		result.forwardTo("/500.jsp");
	}
	
	@Get("/translate/{language}/{country}")
	public void translateTo(String language, String country) {
		try {
			Locale.setDefault(new Locale(language, country));

			userSession.setLanguage(language + "_" + country);

		    result.use(referer()).redirect();
		} catch (IllegalStateException e) {
		    result.redirectTo(this).index();
		}
	}

}