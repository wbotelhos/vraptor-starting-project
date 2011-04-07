package br.com.wbotelhos.starting.business;

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
import br.com.wbotelhos.starting.repository.UsuarioRepository;

public class UsuarioBusinessTest {

	private Usuario entity;

	@Mock
	private EntityManager manager;

	@Mock
	private Query query;

	@Mock
	private UsuarioRepository repository;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		repository = new UsuarioBusiness(manager);
	}

	@Test
	public void deveriaLoadAll() throws Exception {
		// given
		dadoQueTenhoUmUsuario();

		// when // then
		when(manager.createQuery("from Usuario")).thenReturn(query);
		when(query.getResultList()).thenReturn(Collections.singletonList(entity));
	}

	@Test
	public void deveriaLoadById() throws Exception {
		// given
		dadoQueTenhoUmUsuario();

		// when
		repository.loadById(entity.getId());

		// then
		verify(manager).find(Usuario.class, entity.getId());
	}

	@Test
	public void deveriaRemove() throws Exception {
		// given
		dadoQueTenhoUmUsuario();

		// when
		repository.remove(entity);

		// then
		entity = verify(manager).getReference(Usuario.class, entity.getId());
		verify(manager).remove(entity);
	}

	@Test
	public void deveriaSave() throws Exception {
		// given
		dadoQueTenhoUmUsuario();

		// when
		repository.save(entity);

		// then
		when(manager.merge(entity)).thenReturn(entity);
	}

	private void dadoQueTenhoUmUsuario() {
		entity = new Usuario();
		entity.setId(System.currentTimeMillis());
		entity.setNome("Washington Botelho");
		entity.setEmail("mail@gmail.com");
		entity.setSenha("password");
	}

}