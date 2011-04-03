package br.com.wbotelhos.blank.business;

import javax.persistence.EntityManager;

import br.com.caelum.vraptor.ioc.Component;
import br.com.wbotelhos.blank.business.common.GenericBusiness;
import br.com.wbotelhos.blank.model.Usuario;
import br.com.wbotelhos.blank.repository.UsuarioRepository;

@Component
public class UsuarioBusiness extends GenericBusiness<Usuario> implements UsuarioRepository {

	public UsuarioBusiness(EntityManager manager) {
		super(manager);
	}

}