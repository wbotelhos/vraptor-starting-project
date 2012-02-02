package br.com.wbotelhos.starting.functional;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.util.test.MockResult;
import br.com.wbotelhos.starting.annotation.Public;
import br.com.wbotelhos.starting.controller.IndexController;

public class IndexControllerTest {

	private IndexController controller;

	@Spy private Result result = new MockResult();

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		controller = new IndexController(result);
	}

	@Test
	public void shouldIndex() {
		// given

		// when
		controller.index();

		// then

	}

	@Test
	public void deveriaEstarAnotadoComPublicaOController() throws SecurityException, NoSuchMethodException {
		// given
		Class<? extends IndexController> clazz = controller.getClass();

		// when
		Public publyc = clazz.getAnnotation(Public.class);

		// then
		assertNotNull(publyc);
		assertTrue(clazz.isAnnotationPresent(Public.class));
	}

}
