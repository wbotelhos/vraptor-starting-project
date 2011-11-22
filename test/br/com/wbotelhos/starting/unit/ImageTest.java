package br.com.wbotelhos.starting.unit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import br.com.wbotelhos.starting.util.Image;

public class ImageTest {

	private String fileName;

	@Test
	public void deveriaRetornarEstensao() throws Exception {
		// given
		dadoQueTenhoOArquivo("imagem.jpg");

		// when
		String extensao = Image.getExtension(fileName);

		// then
		assertEquals(".jpg", extensao);
	}

	@Test
	public void deveriaRetornarEstensaoMinuscula() throws Exception {
		// given
		dadoQueTenhoOArquivo("imagem.PNG");

		// when
		String extensao = Image.getExtension(fileName);

		// then
		assertEquals(".png", extensao);
	}

	@Test
	public void deveriaRetornarEstensaoMinusculaResumida() throws Exception {
		// given
		dadoQueTenhoOArquivo("imagem.JpEg");

		// when
		String extensao = Image.getExtension(fileName);

		// then
		assertEquals(".jpg", extensao);
	}

	@Test
	public void deveriaSerUmaEstensaoValida() throws Exception {
		// given
		dadoQueTenhoOArquivo("imagem.JpEg");

		// when
		boolean result = Image.isValidFile(fileName);

		// then
		assertTrue(result);
	}

	@Test
	public void deveriaSerUmaEstensaoInvalida() throws Exception {
		// given
		dadoQueTenhoOArquivo("imagem.txt");

		// when
		boolean result = Image.isValidFile(fileName);

		// then
		assertFalse(result);
	}

	private void dadoQueTenhoOArquivo(String fileName) {
		this.fileName = fileName;
	}

}