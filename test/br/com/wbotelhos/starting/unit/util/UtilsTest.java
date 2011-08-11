package br.com.wbotelhos.starting.unit.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.wbotelhos.starting.util.Utils;

public class UtilsTest {

	private String key;

	@Test
	public void deveriaNaoAcharI18N() {
		// given
		dadoQueTenhoAKey("key.inexistente");

		// when
		String value = Utils.i18n(key);

		// then
		assertEquals("???key.inexistente???", value);
	}

	private void dadoQueTenhoAKey(String key) {
		this.key = key;
	}

}