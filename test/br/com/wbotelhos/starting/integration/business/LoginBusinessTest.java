package br.com.wbotelhos.starting.integration.business;

import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import javax.persistence.EntityManager;

import org.jstryker.database.DBUnitHelper;
import org.jstryker.database.JPAHelper;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.wbotelhos.starting.business.LoginBusiness;
import br.com.wbotelhos.starting.helper.Given;
import br.com.wbotelhos.starting.helper.Helper;
import br.com.wbotelhos.starting.model.Usuario;
import br.com.wbotelhos.starting.repository.LoginRepository;

public class LoginBusinessTest {

	private DBUnitHelper dbUnitHelper = new DBUnitHelper();
	private Helper helper = new Helper(dbUnitHelper);

	private LoginRepository repository;

	@BeforeClass
	public static void beforeClass() {
		JPAHelper.entityManagerFactory("default");
	}

	@Before
	public void setup() {
		helper.cleanAndInsert();

		EntityManager manager = JPAHelper.currentEntityManager();

		repository = new LoginBusiness(manager);
	}

	@After
	public void tearDown() {
		JPAHelper.close();
		helper.clean();
	}

	@Test
	public void deveriaAutenticar() {
		// given
		Usuario entity = Given.usuario(1l);

		// when
		Usuario actual = repository.autenticar(entity.getEmail(), entity.getSenha());

		// then
		assertNotNull("deveria encontrar o usuario", actual);
	}

	@Test
	public void deveriaNaoAutenticar() {
		// given
		Usuario entity = Given.usuario(4l);

		// when
		Usuario actual = repository.autenticar(entity.getEmail(), entity.getSenha());

		// then
		assertNull("nao deveria encontrar o usuario", actual);
	}

}