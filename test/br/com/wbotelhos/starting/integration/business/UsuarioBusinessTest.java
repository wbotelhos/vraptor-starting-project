package br.com.wbotelhos.starting.integration.business;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import javax.persistence.EntityManager;

import org.jstryker.database.JPAHelper;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.wbotelhos.starting.business.UsuarioBusiness;
import br.com.wbotelhos.starting.exception.CommonException;
import br.com.wbotelhos.starting.helper.Given;
import br.com.wbotelhos.starting.helper.Helper;
import br.com.wbotelhos.starting.model.Usuario;
import br.com.wbotelhos.starting.repository.UsuarioRepository;

public class UsuarioBusinessTest {

	private final Helper helper = new Helper();

	private UsuarioRepository repository;

	@BeforeClass
	public static void beforeClass() {
		JPAHelper.entityManagerFactory("default");
	}

	@Before
	public void setup() {
		helper.cleanAndInsert();

		EntityManager manager = JPAHelper.currentEntityManager();

		repository = new UsuarioBusiness(manager);
	}

	@After
	public void tearDown() {
		JPAHelper.close();
		//helper.clean();
	}

	@Test
	public void deveriaListarTodos() {
		// given

		// when
		List<Usuario> entityList = (List<Usuario>) repository.loadAll();

		// then
		assertEquals("deve retornar a quantidade correta de registros", 3, entityList.size());

		verifyEntities(Given.usuario(1l), entityList.get(0));
		verifyEntities(Given.usuario(2l), entityList.get(1));
		verifyEntities(Given.usuario(3l), entityList.get(2));
	}

	@Test
	public void deveriaBuscarPorID() {
		// given
		Long expected = 1l;

		Usuario actual = repository.loadById(expected);

		// when
		assertNotNull("usuario encontrado", actual);

		// then
		verifyEntities(Given.usuario(expected), actual);
	}

	@Test
	public void deveriaSalvar() throws CommonException {
		// given
		Usuario entity = Given.usuario(null);
		entity.setImageList(Given.usuarioImageList(1l, 1l, 2l, 3l));

		Usuario expected = Given.usuario(4l);

		// when
		Usuario actual = repository.save(entity);

		List<Usuario> actualList = (List<Usuario>) repository.loadAll();

		// then
		assertEquals("deve ter um registro a mais", 4, actualList.size());
		assertEquals("deve salvar as imagens", 3, entity.getImageList().size());

		verifyEntities(expected, actual);
	}

	@Test
	public void deveriaLancarExceptionCustomizadaAoSalvarComEmailJaCadastrado() {
		// given
		Usuario entity = Given.usuario(null);
		entity.setEmail("email-1@mail.com");

		try {
			// when
			repository.save(entity);
			fail("exception esperada, mas nao lancada");
		} catch (CommonException e) {
			// then
			assertEquals("email.ja.cadastrado", e.getMessage());
		}
	}

	@Test
	public void deveriaAtualizar() throws CommonException {
		// given
		Usuario expected = Given.usuario(4l); // novos dados
		expected.setId(1l);

		// when
		repository.save(expected);

		Usuario actual = repository.loadById(expected.getId());

		// then
		verifyEntities(expected, actual);
	}

	@Test
	public void deveriaRemover() throws CommonException {
		// given
		Long id = 1l;
		Usuario entity = repository.loadById(id);

		// when
		repository.remove(entity);

		// then
		Usuario actual = repository.loadById(id);

		assertNull("o registro nao deve ser encontrado", actual);
	}

	@Test
	public void deveriaBuscarPorId() {
		// given
		Long id = 1l;

		// when
		Usuario actual = repository.loadById(id);

		// then
		assertNotNull("deve encontrar o registro", actual);
	}

	@Test
	public void deveriaAfirmarExistenciaDeUmEmailJaExistente() {
		// given
		Usuario entity = Given.usuario(1l); // email existente
		entity.setId(null); // novo cadastro

		// when
		Boolean actual = repository.isMailExist(entity);

		// then
		assertTrue("deve afirmar a existencia", actual);
	}

	@Test
	public void deveriaNaoAfirmarExistenciaDeUmEmailJaExistentePoremMeuProprio() {
		// given
		Usuario entity = Given.usuario(1l);

		// when
		Boolean actual = repository.isMailExist(entity);

		// then
		assertFalse("nao deve afirmar a existencia", actual);
	}

	private void verifyEntities(Usuario expected, Usuario found) {
		assertEquals("deve ter o mesmo email", expected.getEmail(), found.getEmail());
		assertEquals("deve ter a mesmo imagem", expected.getImagem(), found.getImagem());
		assertEquals("deve ter o mesmo nome", expected.getNome(), found.getNome());
		assertEquals("deve ter o mesmo perfil", expected.getPerfil(), found.getPerfil());
		assertEquals("deve ter a mesmo senha", expected.getSenha(), found.getSenha());
	}

}