package br.com.wbotelhos.blank.controller;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

@Resource
public class IndexController {

	private final Result result;

	public IndexController(Result result) {
		this.result = result;
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

}