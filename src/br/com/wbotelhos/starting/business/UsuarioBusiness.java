package br.com.wbotelhos.starting.business;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.caelum.vraptor.ioc.Component;
import br.com.wbotelhos.starting.business.common.GenericImageBusiness;
import br.com.wbotelhos.starting.exception.CommonException;
import br.com.wbotelhos.starting.model.Usuario;
import br.com.wbotelhos.starting.repository.UsuarioRepository;

@Component
public class UsuarioBusiness extends GenericImageBusiness<Usuario> implements UsuarioRepository {

	public UsuarioBusiness(EntityManager manager) {
		super(manager);
	}

	public boolean isMailExist(Usuario entity) {
		Query query = manager.createQuery("select id from Usuario where email = :email and (:id is null or id != :id)");
		query.setParameter("email", entity.getEmail());
		query.setParameter("id", entity.getId());
		return (query.getSingleResult() == null) ? false : true;
	}

	@Override
	public Usuario save(Usuario entity) throws CommonException {
		if (this.isMailExist(entity)) {
			throw new CommonException("email.ja.cadastrado");
		}

		return super.save(entity);
	}
	
}