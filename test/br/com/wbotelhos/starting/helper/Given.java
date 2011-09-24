package br.com.wbotelhos.starting.helper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.wbotelhos.starting.model.Usuario;
import br.com.wbotelhos.starting.model.UsuarioImage;
import br.com.wbotelhos.starting.model.common.EntityWrapper;
import br.com.wbotelhos.starting.model.common.Perfil;

public class Given {

	/** Usuario **/
	public static Usuario usuario(Long id, String email, String imagem, String nome, Perfil perfil, String senha) {
		Usuario entity = new Usuario();

		entity.setId(id);
		entity.setEmail(email);
		entity.setImagem(imagem);
		entity.setNome(nome);
		entity.setPerfil(perfil);
		entity.setSenha(senha);

		return entity;
	}

	public static Usuario usuario(Long id) {
		id = ((id == null) ? 4l : id); // null == nova entidade. no xml ha 3, o proximo eh 4. 

		String email	= "email-" + id + "@mail.com";
		String imagem	= "imagem-" + id + ".jpg";
		String nome		= "nome-" + id;

		Perfil[] perfilList = Perfil.values();
		int index = 0;

		if (id != null) {
			index = ((id.intValue() > perfilList.length) ? perfilList.length : id.intValue()) - 1;
		}

		Perfil perfil	= perfilList[index];
		String senha	= "senha-" + id;

		return Given.usuario(((id.intValue() == 4) ? null : id), email, imagem, nome, perfil, senha);
	}

	public static Collection<Usuario> usuarioList(Long... ids) {
		Collection<Usuario> entityList = new ArrayList<Usuario>();

		for (Long id : ids) {
			entityList.add(usuario(id));
		}

		return entityList;
	}

	public static String usuarioAsJSON(Long id) {
	    return Serializer.serialize(usuario(id));
	}

	public static String usuarioListAsJSON(Long...ids) {
	    return Serializer.serialize(usuarioList(ids));
	}

	public static String usuarioGridyListAsJSON(Long... ids) {
	    Collection<Usuario> entityList = usuarioList(ids);

	    EntityWrapper<Usuario> wrapper = new EntityWrapper<Usuario>();
		wrapper.setEntityList(entityList);
		wrapper.setTotal(entityList.size());

	    return Serializer.serialize(wrapper);
	}

	/** UsuarioImage **/
	public static UsuarioImage usuarioImage(Long id, String descricao, String imagem, String titulo) {
		UsuarioImage entity = new UsuarioImage();

		entity.setId(id);
		entity.setDescricao(descricao);
		entity.setImagem(imagem);
		entity.setTitulo(titulo);

		return entity;
	}

	public static UsuarioImage usuarioImage(Long usuarioId, Long id) {
		id = ((id == null) ? 4l : id); // null == nova entidade. no xml ha 3, o proximo eh 4.

		String descricao	= "descricao-" + id;
		String imagem		= "imagem-" + id + ".jpg";
		String titulo		= "titulo-" + id;

		UsuarioImage entity = Given.usuarioImage(((id.intValue() == 4) ? null : id), descricao, imagem, titulo);

		// usuario_id
		if (usuarioId != null) {
			entity.setUsuario(Given.usuario(usuarioId));
		}

		return entity;
	}

	public static List<UsuarioImage> usuarioImageList(Long usuarioId, Long... ids) {
		List<UsuarioImage> entityList = new ArrayList<UsuarioImage>();

		for (Long id : ids) {
			entityList.add(usuarioImage(usuarioId, id));
		}

		return entityList;
	}

	public static String usuarioImageAsJSON(Long usuarioId, Long id) {
	    return Serializer.serialize(usuarioImage(usuarioId, id));
	}

	public static String usuarioImageListAsJSON(Long usuarioId, Long...ids) {
	    return Serializer.serialize(usuarioImageList(usuarioId, ids));
	}

}