package br.com.wbotelhos.starting.controller;

import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.core.Localization;
import br.com.caelum.vraptor.interceptor.download.InputStreamDownload;
import br.com.caelum.vraptor.interceptor.multipart.UploadedFile;
import br.com.caelum.vraptor.validator.Validations;
import br.com.wbotelhos.starting.exception.UploadException;
import br.com.wbotelhos.starting.model.Usuario;
import br.com.wbotelhos.starting.model.common.TipoPerfil;
import br.com.wbotelhos.starting.repository.UsuarioRepository;
import br.com.wbotelhos.starting.util.Image;

@Resource
public class UsuarioController {

	private final Result result;
	private final UsuarioRepository repository;
	private final Validator validator;
	private final Localization localization;

	public UsuarioController(Result result, UsuarioRepository repository, Validator validator, Localization localization) {
		this.result = result;
		this.repository = repository;
		this.validator = validator;
		this.localization = localization;
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
		validator.validate(entity);

		validator.onErrorUsePageOf(this).novo();

		entity = repository.save(entity);

		result
		.include("message", "Usu‡rio salvo com sucesso!")
		.redirectTo(this).exibir(entity);
	}

	@Post("/usuario/{entity.id}/image")
	public void uploadImage(Usuario entity, final UploadedFile file) {
		validator.checking(new Validations(localization.getBundle()) {{
			that(Image.isValidFile(file.getFileName()), i18n("imagem"), "imagem.invalida");
	    }});

		validator.onErrorUsePageOf(this).exibir(entity);

		try {
			repository.uploadImage(entity, file);
		} catch (UploadException e) {
			result.include("error", e.getMessage());
			e.printStackTrace();
		}

		result.redirectTo(this).exibir(entity);
	}

	@Get("/usuario/{entity.id}/image/{width}/{height}")
	public InputStreamDownload viewThumb(Usuario entity, int width, int height) {
		entity = repository.loadById(entity.getId());
		return entity.getThumb(entity.getImagePath(), width, height);
	}

	@Get("/usuario/{entity.id}/image")
	public InputStreamDownload viewImage(Usuario entity) {
		entity = repository.loadById(entity.getId());
		return entity.getImage(entity.getImagePath());
	}

}