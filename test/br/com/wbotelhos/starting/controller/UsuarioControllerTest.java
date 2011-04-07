package br.com.wbotelhos.starting.controller;

import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.core.Localization;
import br.com.caelum.vraptor.util.test.MockResult;
import br.com.wbotelhos.starting.component.UserSession;
import br.com.wbotelhos.starting.model.Usuario;
import br.com.wbotelhos.starting.model.common.TipoPerfil;
import br.com.wbotelhos.starting.repository.UsuarioRepository;

public class UsuarioControllerTest {

	private Usuario entity;
	private UsuarioController controller;

	@Spy private Result result = new MockResult();

	@Mock private UserSession userSession;
	@Mock private UsuarioRepository repository;
	@Mock private Validator validator;
	@Mock private Localization localization;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		controller = new UsuarioController(result, repository, validator, localization);
	}

	@Test
	public void deveriaEditar() throws Exception {
		// given
		dadoQueTenhoUmUsuario();

		// when
		controller.editar(entity);

		// then
		verify(repository).loadById(entity.getId());
		
	}

	@Test
	public void deveriaExibir() throws Exception {
		// given
		dadoQueTenhoUmUsuario();

		// when
		controller.exibir(entity);

		// then
		entity = verify(repository).loadById(entity.getId());
		verify(result).include("entity", entity);
	}

	@Test
	public void deveriaRemover() throws Exception {
		// given
		dadoQueTenhoUmUsuario();

		// when
		controller.remover(entity);

		// then
		verify(repository).remove(entity);
		verify(result).include("message", "Usu‡rio removido com sucesso!");
	}
	
	@Test
	public void deveriaSalvar() throws Exception {
		// given
		dadoQueTenhoUmUsuario();

		// when
		controller.salvar(entity);

		// then
		verify(repository).save(entity);
		verify(result).include("message", "Usu‡rio salvo com sucesso!");
	}
	
	private void dadoQueTenhoUmUsuario() {
		entity = new Usuario();
		entity.setId(42l);
		entity.setNome("Washington Botelho");
		entity.setEmail("mail@gmail.com");
		entity.setSenha("password");
		entity.setPerfil(TipoPerfil.ADMINISTRADOR);
	}

}