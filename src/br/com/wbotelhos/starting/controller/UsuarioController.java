package br.com.wbotelhos.starting.controller;

import static br.com.wbotelhos.starting.util.Utils.i18n;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.core.Localization;
import br.com.caelum.vraptor.interceptor.download.InputStreamDownload;
import br.com.caelum.vraptor.interceptor.multipart.UploadedFile;
import br.com.caelum.vraptor.validator.Validations;
import br.com.wbotelhos.starting.exception.CommonException;
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

	@Put("/usuario/{entity.id}")
	public void atualizar(Usuario entity) {
		validator.validate(entity);
		validator.onErrorRedirectTo(this).editar(entity);

		try {
			entity = repository.save(entity);
			result.include("message", i18n("usuario.atualizado.sucesso")).redirectTo(this).exibir(entity);
		} catch (CommonException e) {
			result.include("error", i18n(e.getMessage())).redirectTo(this).editar(entity);
		}
	}

	@Get("/usuario/criar")
	public void criar(Usuario entity) {
		result
		.include("perfilList", TipoPerfil.values())
		.include("entity", entity);
	}

	@Get("/usuario/{entity.id}/editar")
	public void editar(Usuario entity) {
		result.include("perfilList", TipoPerfil.values());

		if (entity.getEmail() == null) {
			result.include("entity", repository.loadById(entity.getId()));
		} else {
			result.include("entity", entity);
		}
	}

	@Get("/usuario/{entity.id}")
	public void exibir(Usuario entity) {
		result.include("entity", repository.loadById(entity.getId()));
	}
	
	@Get("/usuario")
	public void listagem() {
		result.include("entityList", repository.loadAll());
	}

	@Delete("/usuario/{entity.id}")
	public void remover(Usuario entity) {
		repository.remove(entity);

		result
		.include("message", i18n("usuario.removido.sucesso")) // TODO: i18n
		.redirectTo(this).listagem();
	}

	@Post("/usuario")
	public void salvar(Usuario entity) {
		validator.validate(entity);
		validator.onErrorRedirectTo(this).criar(entity);

		try {
			entity.setImagem("default.jpg");

			entity = repository.save(entity);

			result.include("message", i18n("usuario.salvo.sucesso")).redirectTo(this).exibir(entity);
		} catch (CommonException e) {
			result.include("error", i18n(e.getMessage())).redirectTo(this).criar(entity);
		}
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
			result.include("error", e.getMessage()).forwardTo(this).exibir(entity);
			e.printStackTrace(); // TODO: remover.
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