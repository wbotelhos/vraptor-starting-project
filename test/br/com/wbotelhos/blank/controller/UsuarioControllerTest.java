package br.com.wbotelhos.blank.controller;

import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.util.test.MockResult;
import br.com.wbotelhos.blank.component.UserSession;
import br.com.wbotelhos.blank.model.Usuario;
import br.com.wbotelhos.blank.repository.UsuarioRepository;

public class UsuarioControllerTest {

	private Usuario entity;
	private UsuarioController controller;

	@Spy
	private Result result = new MockResult();

	@Mock
	private UserSession userSession;

	@Mock
	private UsuarioRepository repository;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		controller = new UsuarioController(result, repository);
	}

	@Test
	public void deveriaEditarTest() throws Exception {
		// given
		dadoQueTenhoUmUsuario();

		// when
		controller.editar(entity);

		// then
		verify(repository).loadById(entity.getId());
		
	}

	@Test
	public void deveriaExibirTest() throws Exception {
		// given
		dadoQueTenhoUmUsuario();

		// when
		controller.exibir(entity);

		// then
		entity = verify(repository).loadById(entity.getId());
		verify(result).include("entity", entity);
	}

	@Test
	public void deveriaRemoverTest() throws Exception {
		// given
		dadoQueTenhoUmUsuario();

		// when
		controller.remover(entity);

		// then
		verify(repository).remove(entity);
		verify(result).include("message", "Usu‡rio removido com sucesso!");
	}
	
	@Test
	public void deveriaSalvarTest() throws Exception {
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
	}

}