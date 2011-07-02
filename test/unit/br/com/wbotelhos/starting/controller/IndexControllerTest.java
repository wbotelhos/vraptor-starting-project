package unit.br.com.wbotelhos.starting.controller;

import java.util.Locale;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.util.test.MockResult;
import br.com.wbotelhos.starting.component.UserSession;
import br.com.wbotelhos.starting.controller.IndexController;

public class IndexControllerTest {

	private IndexController controller;

	@Mock private UserSession userSession;

	@Spy private Result result = new MockResult();

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		controller = new IndexController(result, userSession);
	}

	@Test
	public void deveriaIndex() throws Exception {
		// given

		// when
		controller.index();

		// then

	}

	@Test
	public void deveriaTranslateToEnglish() {
		// given
		String expectedLanguage = "en";
		String expectedCountry = "US";

		// when
		controller.translateTo(expectedLanguage, expectedCountry);

		// then
		Assert.assertEquals("Language is different", expectedLanguage, Locale.getDefault().getLanguage());
		Assert.assertEquals("Country is different", expectedCountry, Locale.getDefault().getCountry());
	}

	@Test
	public void deveriaTranslateToPortuguese() {
		// given
		String expectedLanguage = "pt";
		String expectedCountry = "BR";

		// when
		controller.translateTo(expectedLanguage, expectedCountry);

		// then
		Assert.assertEquals("Language is different", expectedLanguage, Locale.getDefault().getLanguage());
		Assert.assertEquals("Country is different", expectedCountry, Locale.getDefault().getCountry());
	}

}