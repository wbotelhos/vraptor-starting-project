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
	public static Usuario usuario(Long id, String email, String imageName, String nome, Perfil perfil, String senha) {
		Usuario entity = new Usuario();

		entity.setId(id);
		entity.setEmail(email);
		entity.setImageName(imageName);
		entity.setNome(nome);
		entity.setPerfil(perfil);
		entity.setSenha(senha);

		return entity;
	}

	public static Usuario usuario(Long id) {
		int token = getToken(id); 

		String email		= "email-" + token + "@mail.com";
		String imageName	= token + ".jpg";
		String nome			= "nome-" + token;

		Perfil[] perfilList = Perfil.values();
		int index = ((token > perfilList.length) ? perfilList.length : token) - 1;

		Perfil perfil	= perfilList[index];
		String senha	= "senha-" + token;

		return Given.usuario(id, email, imageName, nome, perfil, senha);
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
	public static UsuarioImage usuarioImage(Long id, String descricao, String imageName, String titulo) {
		UsuarioImage entity = new UsuarioImage();

		entity.setId(id);
		entity.setDescricao(descricao);
		entity.setImageName(imageName);
		entity.setTitulo(titulo);

		return entity;
	}

	public static UsuarioImage usuarioImage(Long usuarioId, Long id) {
		int token = getToken(id);

		String descricao	= "descricao-" + token;
		String imagem		= token + ".jpg";
		String titulo		= "titulo-" + token;

		UsuarioImage entity = Given.usuarioImage(id, descricao, imagem, titulo);

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

	/** Utils **/
	public static Long[] array(Long... ids) {
		Long[] array = new Long[ids.length];

		for (int i = 0; i < ids.length; i++) {
			array[i] = ids[i];
		}

		return array;
	}

	private static int getToken(Long id) {
		return (id == null) ? 4 : id.intValue();
	}

}
