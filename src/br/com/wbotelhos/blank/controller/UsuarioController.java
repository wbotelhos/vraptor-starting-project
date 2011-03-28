package br.com.wbotelhos.blank.controller;

import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.wbotelhos.blank.model.Usuario;
import br.com.wbotelhos.blank.model.common.TipoPerfil;
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
		result.include("perfilList", TipoPerfil.values());
	}

	@Get("/usuario/{entity.id}/editar")
	public void editar(Usuario entity) {
		entity = repository.loadById(entity.getId());

		result.include("entity", entity).forwardTo(this).novo();
	}

	@Get({ "/usuario/{entity.id}", "/usuario/{entity.id}/exibir" })
	public void exibir(Usuario entity) {
		entity = repository.loadById(entity.getId());

		result.include("entity", entity);
	}

	@Get("/usuario")
	public void listagem() {
		result.include("entityList", repository.loadAll());
	}

	@Delete("/usuario/{entity.id}")
	public void remover(Usuario entity) {
		repository.remove(entity);

		result
		.include("message", "Usu‡rio removido com sucesso!")
		.redirectTo(this).listagem();
	}

	@Post("/usuario")
	public void salvar(Usuario entity) {
		entity = repository.save(entity);

		result
		.include("message", "Usu‡rio salvo com sucesso!")
		.redirectTo(this).exibir(entity);
	}

}