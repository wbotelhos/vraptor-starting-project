package unit.br.com.wbotelhos.starting.controller;

import static br.com.wbotelhos.starting.util.Utils.i18n;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.core.Localization;
import br.com.caelum.vraptor.util.test.MockLocalization;
import br.com.caelum.vraptor.util.test.MockResult;
import br.com.caelum.vraptor.util.test.MockValidator;
import br.com.wbotelhos.starting.controller.UsuarioController;
import br.com.wbotelhos.starting.model.Usuario;
import br.com.wbotelhos.starting.model.common.TipoPerfil;
import br.com.wbotelhos.starting.repository.UsuarioRepository;

public class UsuarioControllerTest {

	private Usuario entity;
	private UsuarioController controller;

	@Spy private Result result = new MockResult();
	@Spy private Validator validator = new MockValidator();
	@Spy private Localization localization = new MockLocalization();

	@Mock private UsuarioRepository repository;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		controller = new UsuarioController(result, repository, validator, localization);
	}

	@Test
	public void deveriaAtualizar() throws Exception {
		// given
		dadoQueTenhoUmUsuario();

		// when
		controller.atualizar(entity);

		// then
		verify(repository).save(entity);
		verify(result).include("message", i18n("usuario.atualizado.sucesso"));
	}

	@Test
	public void deveriaCriar() {
		// given
		dadoQueTenhoUmUsuario();

		// when
		controller.criar(entity);

		// then
		verify(result).include("perfilList", TipoPerfil.values());
		verify(result).include("entity", entity);
	}

	@Test
	public void deveriaChamarFormularioDeEdicaoSemBuscarNoBanco() {
		// given
		dadoQueTenhoUmUsuario();

		// when
		controller.editar(entity);

		// then
		verify(result).include("perfilList", TipoPerfil.values());
		verify(result).include("entity", entity);
	}

	@Test
	public void deveriaChamarFormularioDeEdicaoBuscandoNoaBanco() {
		// given
		dadoQueReceboUmUsuarioSomenteComIDParaEditar();

		// when
		controller.editar(entity);

		// then
		verify(result).include("perfilList", TipoPerfil.values());

		Usuario resulted = verify(repository).loadById(entity.getId());
		verify(result).include("entity", resulted);
	}

	@Test
	public void deveriaExibir() {
		// given
		dadoQueTenhoUmUsuario();

		// when
		controller.exibir(entity);

		// then
		entity = verify(repository).loadById(entity.getId());
		verify(result).include("entity", entity);
	}

	@Test
	public void deveriaListar() {
		// given

		// when
		controller.listagem();

		// then
		verify(repository).loadAll();
		verify(result).include("entityList", new ArrayList<Usuario>());
	}

	@Test
	public void deveriaRemover() {
		// given
		dadoQueTenhoUmUsuario();

		// when
		controller.remover(entity);

		// then
		verify(repository).remove(entity);
		verify(result).include("message", i18n("usuario.removido.sucesso"));
	}
	
	@Test
	public void deveriaSalvar() throws Exception {
		// given
		dadoQueTenhoUmUsuario();

		// when
		controller.salvar(entity);

		// then
		Assert.assertEquals("default.jpg", entity.getImagem());

		verify(repository).save(entity);
		verify(result).include("message", i18n("usuario.salvo.sucesso"));
	}

	private void dadoQueTenhoUmUsuario() {
		entity = new Usuario();
		entity.setId(42l);
		entity.setNome("Washington Botelho");
		entity.setEmail("mail@gmail.com");
		entity.setSenha("password");
		entity.setPerfil(TipoPerfil.ADMINISTRADOR);
	}
	
	private void dadoQueReceboUmUsuarioSomenteComIDParaEditar() {
		entity = new Usuario();
		entity.setId(42l);
	}

}