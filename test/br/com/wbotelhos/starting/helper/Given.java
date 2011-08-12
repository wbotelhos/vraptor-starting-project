package br.com.wbotelhos.starting.helper;

import java.util.ArrayList;
import java.util.List;

import br.com.wbotelhos.starting.model.Usuario;
import br.com.wbotelhos.starting.model.UsuarioImage;
import br.com.wbotelhos.starting.model.common.TipoPerfil;

public class Given {

	/**
	 * Cria um usuário com uma lista de imagens contendo apenas um ítem que usa o ID passado.
	 * @param id
	 * @param email
	 * @param nome
	 * @param imagem
	 * @param perfil
	 * @param senha
	 * @param List[UsuarioImage] * Criada dinamicamente com o ID passado.
	 * @return Usuario
	 */
	public static Usuario usuario(Long id, String email, String nome, String imagem, TipoPerfil perfil, String senha) {
		List<UsuarioImage> imageList = Given.imageList(id, "descricao-1", "imagem-1.jpg", "titulo-1");

		return usuario(id, email, nome, imagem, perfil, senha, imageList);
	}

	public static Usuario usuario(Long id, String email, String nome, String imagem, TipoPerfil perfil, String senha, List<UsuarioImage> imageList) {
		Usuario usuario = new Usuario();

		usuario.setId(id);
		usuario.setEmail(email);
		usuario.setImagem(imagem);
		usuario.setNome(nome);
		usuario.setPerfil(perfil);
		usuario.setSenha(senha);

		usuario.setImageList(imageList);

		return usuario;
	}

	/**
	 * Cria uma lista de imagens do usuário já com o usuário relacionado usando o ID passado.
	 * @param id
	 * @param descricao
	 * @param imagem
	 * @param titulo
	 * @param [usuario] * Criado dinamicamente com o ID passado.
	 * @return List<UsuarioImage>
	 */
	public static List<UsuarioImage> imageList(Long id, String descricao, String imagem, String titulo) {
		Usuario usuario = new Usuario();
		usuario.setId(id);

		return imageList(id, descricao, imagem, titulo, usuario);
		
	}

	public static List<UsuarioImage> imageList(Long id, String descricao, String imagem, String titulo, Usuario usuario) {
		List<UsuarioImage> imageList = new ArrayList<UsuarioImage>();

		UsuarioImage usuarioImage = new UsuarioImage();
		usuarioImage.setId(id);
		usuarioImage.setDescricao(descricao);
		usuarioImage.setImagem(imagem);
		usuarioImage.setTitulo(titulo);

		usuarioImage.setUsuario(usuario);

		imageList.add(usuarioImage);

		return imageList;
	}

}