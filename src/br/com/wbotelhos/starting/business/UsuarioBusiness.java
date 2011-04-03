package br.com.wbotelhos.starting.business;

import javax.persistence.EntityManager;

import br.com.caelum.vraptor.ioc.Component;
import br.com.wbotelhos.starting.business.common.GenericBusiness;
import br.com.wbotelhos.starting.model.Usuario;
import br.com.wbotelhos.starting.repository.UsuarioRepository;

@Component
public class UsuarioBusiness extends GenericBusiness<Usuario> implements UsuarioRepository {

	public UsuarioBusiness(EntityManager manager) {
		super(manager);
	}

}