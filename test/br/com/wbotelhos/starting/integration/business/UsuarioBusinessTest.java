package br.com.wbotelhos.starting.integration.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import javax.persistence.EntityManager;

import org.jstryker.database.DBUnitHelper;
import org.jstryker.database.JPAHelper;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.wbotelhos.starting.business.UsuarioBusiness;
import br.com.wbotelhos.starting.exception.CommonException;
import br.com.wbotelhos.starting.helper.Given;
import br.com.wbotelhos.starting.model.Usuario;
import br.com.wbotelhos.starting.model.UsuarioImage;
import br.com.wbotelhos.starting.model.common.Perfil;
import br.com.wbotelhos.starting.repository.UsuarioRepository;

public class UsuarioBusinessTest {

	private static final String DATASET_USUARIO = "/br/com/wbotelhos/starting/integration/xml/Usuario.xml";
	private static final String DATASET_USUARIO_IMAGE = "/br/com/wbotelhos/starting/integration/xml/UsuarioImage.xml";
	private static final Long ID_VALIDO = 1L;

	private UsuarioRepository repository;
	private DBUnitHelper dbUnitHelper = new DBUnitHelper();

	@BeforeClass
	public static void beforeClass() {
		JPAHelper.entityManagerFactory("default");
	}

	@Before
	public void setup() {
		dbUnitHelper.cleanInsert(DATASET_USUARIO);
		dbUnitHelper.cleanInsert(DATASET_USUARIO_IMAGE);

		EntityManager manager = JPAHelper.currentEntityManager();

		repository = new UsuarioBusiness(manager);
	}

	@After
	public void tearDown() {
		JPAHelper.close();
		dbUnitHelper.deleteAll(DATASET_USUARIO_IMAGE);
		dbUnitHelper.deleteAll(DATASET_USUARIO);
	}

	@Test
	public void deveriaListarTodos() {
		// given

		// when
		List<Usuario> entityList = (List<Usuario>) repository.loadAll();

		// then
		assertEquals("numero de registros encontrados", 3, entityList.size());

		verificarEntity(1l, "email_1@gmail.com", "imagem-1.jpg", "nome-1", Perfil.MEMBRO, "senha-1", entityList.get(0));
		verificarEntity(2l, "email_2@gmail.com", "imagem-2.jpg", "nome-2", Perfil.MODERADOR, "senha-2", entityList.get(1));
		verificarEntity(3l, "email_3@gmail.com", "imagem-3.jpg", "nome-3", Perfil.ADMINISTRADOR, "senha-3", entityList.get(2));
	}

	@Test
	public void deveriaBuscarPorID() {
		// given
		Usuario found = repository.loadById(ID_VALIDO);

		// when
		assertNotNull("usuario encontrado", found);

		// then
		verificarEntity(1l, "email_1@gmail.com", "imagem-1.jpg", "nome-1", Perfil.MEMBRO, "senha-1", found);
	}

	@Test
	public void deveriaSalvar() throws CommonException {
		// given
		Usuario expected = Given.usuario(ID_VALIDO, "email_1@email.com", "imagem-1.jpg", "nome-1", Perfil.ADMINISTRADOR, "senha-1");

		// when
		repository.save(expected);

		// then
		Usuario found = repository.loadById(expected.getId());

		assertNotNull("usuario encontrado", found);

		verificarEntities(expected, found);
	}

	@Test
	public void deveriaAtualizar() throws CommonException {
		// given
		Usuario altered = repository.loadById(ID_VALIDO);

		// when
		altered.setEmail("novo_email@mail.com");
		altered.setImagem("nova-imagem.jpg");
		altered.setNome("novo-nome");
		altered.setPerfil(Perfil.MEMBRO);
		altered.setSenha("nova-senha");

		repository.save(altered);

		Usuario found = repository.loadById(ID_VALIDO);

		// then
		verificarEntities(altered, found);
	}

	@Test
	public void deveriaRemover() throws CommonException {
		// given
		Usuario found = repository.loadById(ID_VALIDO);

		// when
		repository.remove(found);

		// then
		found = repository.loadById(ID_VALIDO);

		assertNull("usuario nao encontrado", found);
	}


	@Test
	public void deveriaBuscarPorId() {
		Usuario entity = repository.loadById(ID_VALIDO);

		assertNotNull("usuario nao encontrado", entity);
	}

	private void verificarEntity(Long id, String email, String imagem, String nome, Perfil perfil, String senha, Usuario found) {
		verificarEntity(id, email, imagem, nome, perfil, senha, found.getImageList(), found);		
	}

	private void verificarEntity(Long id, String email, String imagem, String nome, Perfil perfil, String senha, List<UsuarioImage> imageList, Usuario found) {
		assertEquals("usuario.id", id, found.getId());
		assertEquals("usuario.email", email, found.getEmail());
		assertEquals("usuario.imagem", imagem, found.getImagem());
		assertEquals("usuario.nome", nome, found.getNome());
		assertEquals("usuario.perfil", perfil, found.getPerfil());
		assertEquals("usuario.senha", senha, found.getSenha());

		UsuarioImage expectedImage = imageList.get(0);
		UsuarioImage foundImage = found.getImageList().get(0);

		assertEquals("usuarioImage.id", expectedImage.getId(), foundImage.getId());
		assertEquals("usuarioImage.descricao", expectedImage.getDescricao(), foundImage.getDescricao());
		assertEquals("usuarioImage.imagemNome", expectedImage.getImagem(), foundImage.getImagem());
		assertEquals("usuarioImage.titulo", expectedImage.getTitulo(), foundImage.getTitulo());
		assertEquals("usuarioImage.usuario", expectedImage.getUsuario(), foundImage.getUsuario());
	}

	private void verificarEntities(Usuario expected, Usuario found) {
		assertEquals("usuario.id", expected.getId(), found.getId());
		assertEquals("usuario.email", expected.getEmail(), found.getEmail());
		assertEquals("usuario.imagem", expected.getImagem(), found.getImagem());
		assertEquals("usuario.nome", expected.getNome(), found.getNome());
		assertEquals("usuario.perfil", expected.getPerfil(), found.getPerfil());
		assertEquals("usuario.senha", expected.getSenha(), found.getSenha());

		UsuarioImage expectedImage = expected.getImageList().get(0);
		UsuarioImage foundImage = found.getImageList().get(0);

		assertEquals("usuarioImage.id", expectedImage.getId(), foundImage.getId());
		assertEquals("usuarioImage.descricao", expectedImage.getDescricao(), foundImage.getDescricao());
		assertEquals("usuarioImage.imagem", expectedImage.getImagem(), foundImage.getImagem());
		assertEquals("usuarioImage.titulo", expectedImage.getTitulo(), foundImage.getTitulo());
		assertEquals("usuarioImage.usuario.id", expectedImage.getUsuario().getId(), foundImage.getUsuario().getId());
	}

}