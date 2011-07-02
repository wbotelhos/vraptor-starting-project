package integration.br.com.wbotelhos.starting.business;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.wbotelhos.starting.business.UsuarioBusiness;
import br.com.wbotelhos.starting.model.Usuario;
import br.com.wbotelhos.starting.model.common.TipoPerfil;
import br.com.wbotelhos.starting.repository.UsuarioRepository;

public class UsuarioBusinessTest {

	private Usuario entity;

	@Mock private Query query;
	@Mock private EntityManager manager;
	@Mock private UsuarioRepository repository;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		repository = new UsuarioBusiness(manager);
	}

	@Test
	public void shouldLoadAll() throws Exception {
		// given
		givenUser();

		// when // then
		when(manager.createQuery("from " + Usuario.class.getName())).thenReturn(query);
		when(query.getResultList()).thenReturn(Collections.singletonList(entity));
	}

	@Test
	public void shouldLoadById() throws Exception {
		// given
		givenUser();

		// when
		repository.loadById(entity.getId());

		// then
		verify(manager).find(Usuario.class, entity.getId());
	}

	@Test
	public void shouldRemove() throws Exception {
		// given
		givenUser();

		// when
		repository.remove(entity);

		// then
		entity = verify(manager).getReference(Usuario.class, entity.getId());
		verify(manager).remove(entity);
	}

	@Test
	public void shouldSave() throws Exception {
		// given
		givenUser();

		// when
		when(manager.merge(entity)).thenReturn(entity);

		repository.save(entity);

		// then
		verify(manager).createQuery("select id from " + Usuario.class.getName() + " where email = :email and (:id is null or id != :id)");
		verify(query).setParameter("email", entity.getEmail());
		verify(query).setParameter("id", entity.getId());
	}

	private void givenUser() {
		entity = new Usuario();
		entity.setId(42l);
		entity.setNome("Washington Botelho");
		entity.setEmail("mail@gmail.com");
		entity.setSenha("password");
		entity.setPerfil(TipoPerfil.ADMINISTRADOR);
	}

}