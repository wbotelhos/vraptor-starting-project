package br.com.wbotelhos.starting.unit;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.wbotelhos.starting.util.Utils;

public class UtilsTest {

	@Test
	public void deveriaNaoAcharI18N() {
		// given
		String key = "Ã§Ã¢Ã§Ã­sÃ£Ã²";

		// when
		String actual = Utils.i18n(key);

		// then
		assertEquals("???key.inexistente???", actual);
	}

	@Test
	public void deveriaDecodificarCaracteres() {
		// given
		String text = "Ã§Ã¢Ã§Ã­sÃ£Ã²";

		// when
		text = Utils.decoderText(text);

		// then
		assertEquals("çâçísãò", text);
	}

}
