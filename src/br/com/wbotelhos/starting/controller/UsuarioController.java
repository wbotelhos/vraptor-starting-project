package br.com.wbotelhos.starting.controller;

import static br.com.caelum.vraptor.view.Results.referer;
import static br.com.wbotelhos.starting.util.Utils.i18n;

import java.util.Locale;

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
import br.com.wbotelhos.starting.annotation.Permission;
import br.com.wbotelhos.starting.annotation.Public;
import br.com.wbotelhos.starting.component.UserSession;
import br.com.wbotelhos.starting.exception.CommonException;
import br.com.wbotelhos.starting.exception.UploadException;
import br.com.wbotelhos.starting.model.Usuario;
import br.com.wbotelhos.starting.model.UsuarioImage;
import br.com.wbotelhos.starting.model.common.Perfil;
import br.com.wbotelhos.starting.repository.UsuarioRepository;
import br.com.wbotelhos.starting.util.Image;

@Resource
@Permission({ Perfil.MEMBRO, Perfil.MODERADOR, Perfil.ADMINISTRADOR })
public class UsuarioController {

	private final Result result;
	private final UsuarioRepository repository;
	private final UserSession userSession;
	private final Validator validator;
	private final Localization localization;

	public UsuarioController(Result result, UsuarioRepository repository, UserSession userSession, Validator validator, Localization localization) {
		this.result = result;
		this.repository = repository;
		this.userSession = userSession;
		this.validator = validator;
		this.localization = localization;
	}

	@Put("/usuario/{entity.id}")
	public void atualizar(Usuario entity) {
		validator.validate(entity);
		validator.onErrorRedirectTo(this).editar(entity);

		try {
			entity = repository.save(entity);
			result.include("notice", i18n("usuario.atualizado.sucesso")).redirectTo(this).exibir(entity);
		} catch (CommonException e) {
			result.include("error", i18n(e.getMessage())).redirectTo(this).editar(entity);
		}
	}

	@Get("/usuario/criar")
	public void criar(Usuario entity) {
		result
		.include("perfilList", Perfil.values())
		.include("entity", entity);
	}

	@Get("/usuario/{entity.id}/editar")
	public void editar(Usuario entity) {
		result.include("perfilList", Perfil.values());

		if (entity.getEmail() == null) {
			result.include("entity", repository.find(entity.getId()));
		} else {
			result.include("entity", entity);
		}
	}

	@Get("/usuario/{entity.id}")
	public void exibir(Usuario entity) {
		result.include("entity", repository.find(entity.getId()));
	}
	
	@Get("/usuario")
	public void listagem() {
		result.include("entityList", repository.all());
	}

	@Delete("/usuario/{entity.id}")
	public void remover(Usuario entity) {
		repository.remove(entity);

		result
		.include("notice", i18n("usuario.removido.sucesso"))
		.redirectTo(this).listagem();
	}

	@Post("/usuario")
	public void salvar(Usuario entity) {
		validator.validate(entity);
		validator.onErrorRedirectTo(this).criar(entity);

		try {
			entity.setImageName("default.jpg");

			entity = repository.save(entity);

			result.include("notice", i18n("usuario.salvo.sucesso")).redirectTo(this).exibir(entity);
		} catch (CommonException e) {
			result.include("error", i18n(e.getMessage())).redirectTo(this).criar(entity);
		}
	}

	@Public
	@Get("/translate/{language}/{country}")
	public void translateTo(String language, String country) {
		try {
			Locale.setDefault(new Locale(language, country));

			userSession.setLanguage(language + "_" + country.toUpperCase());

		    result.use(referer()).redirect();
		} catch (IllegalStateException e) {
		    result.redirectTo(IndexController.class).index();
		}
	}

	@Post("/usuario/{entity.id}/image")
	public void uploadImage(Usuario entity, UploadedFile file) {
		try {
			repository.uploadImage(entity, file);

			result.redirectTo(this).exibir(entity);
		} catch (UploadException e) {
			result.include("error", e.getMessage()).forwardTo(this).exibir(entity);
		}
	}

	@Get("/usuario/{entity.id}/thumb")
	public InputStreamDownload viewThumb(Usuario entity) {
		Usuario usuario = repository.find(entity.getId());

		return (usuario == null) ? entity.getNotFoundImage() : usuario.getThumb();
	}

	@Get("/usuario/{entity.id}/image")
	public InputStreamDownload viewImage(Usuario entity) {
		Usuario usuario = repository.find(entity.getId());

		return (usuario == null) ? entity.getNotFoundImage() : usuario.getImage();
	}

	@Get("/usuario/{entity.id}/gallery/{fileName}/thumb")
	public InputStreamDownload viewThumbGallery(Usuario entity, String fileName) {
		return entity.getThumbGallery(fileName);
	}

	@Get("/usuario/{entity.id}/gallery/{fileName}/image")
	public InputStreamDownload viewImageGallery(Usuario entity, String fileName) {
		return entity.getImageGallery(fileName);
	}

	@Post("/usuario/{entity.id}/gallery")
	public void uploadGallery(Usuario entity, UsuarioImage entityImage, final UploadedFile file) {
		validator.checking(new Validations(localization.getBundle()) {{
			that(Image.isValidFile(file.getFileName()), i18n("imagem"), "imagem.invalida");
	    }});

		validator.onErrorUsePageOf(this).exibir(entity);

		try {
			repository.uploadGallery(entity, entityImage, file);
		} catch (UploadException e) {
			result.include("error", e.getMessage()).forwardTo(this).exibir(entity);
		}

		result.redirectTo(this).exibir(entity);
	}

}
