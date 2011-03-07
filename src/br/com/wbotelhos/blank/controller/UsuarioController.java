package br.com.wbotelhos.blank.controller;

import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.wbotelhos.blank.model.Usuario;
import br.com.wbotelhos.blank.repository.UsuarioRepository;

@Resource
public class UsuarioController {

	private final Result result;
	private final UsuarioRepository repository;

	public UsuarioController(Result result, UsuarioRepository repository) {
		this.result = result;
		this.repository = repository;
	}

	@Get("/usuario/novo")
	public void novo() {

	}

	@Put("/usuario")
	public void editar(Usuario entity) {
		try {
			result
			.include("entity", repository.loadById(entity.getId()))
			.forwardTo(this).novo();
		} catch (Exception e) {
			result.include("error", e.getMessage()).forwardTo(this).exibir(entity);
		}
	}

	@Get("/usuario/{entity.id}")
	public void exibir(Usuario entity) {
		try {
			result.include("entity", repository.loadById(entity.getId()));
		} catch (Exception e) {
			result.include("error", e.getMessage());
		}
	}

	@Get("/usuario")
	public void listagem() {
		try {
			result.include("entityList", repository.loadAll());
		} catch (Exception e) {
			result.include("error", e.getMessage()).redirectTo(IndexController.class).index();
		}
	}

	@Delete("/usuario")
	public void remover(Usuario entity) {
		try {
			repository.remove(entity);

			result
			.include("message", "Usu‡rio removido com sucesso!")
			.redirectTo(this).listagem();
		} catch (Exception e) {
			result.include("error", e.getMessage()).forwardTo(this).exibir(entity);
		}
	}

	@Post("/usuario")
	public void salvar(Usuario entity) {
		try {
			entity = repository.save(entity);

			result
			.include("message", "Usu‡rio salvo com sucesso!")
			.redirectTo(this).exibir(entity);
		} catch (Exception e) {
			result.include("error", e.getMessage());
		}
	}

}