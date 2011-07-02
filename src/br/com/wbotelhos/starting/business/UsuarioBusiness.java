package br.com.wbotelhos.starting.business;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.caelum.vraptor.ioc.Component;
import br.com.wbotelhos.starting.business.common.GenericImageBusiness;
import br.com.wbotelhos.starting.exception.CommonException;
import br.com.wbotelhos.starting.model.Usuario;
import br.com.wbotelhos.starting.model.UsuarioImage;
import br.com.wbotelhos.starting.repository.UsuarioRepository;

@Component
public class UsuarioBusiness extends GenericImageBusiness<Usuario, UsuarioImage> implements UsuarioRepository {

	public UsuarioBusiness(EntityManager manager) {
		super(manager);
	}

	public Boolean isMailExist(Usuario entity) {
		try {
			Query query = manager.createQuery("select id from " + Usuario.class.getName() + " where email = :email and (:id is null or id != :id)");
			query.setParameter("email", entity.getEmail());
			query.setParameter("id", entity.getId());
			return (query.getSingleResult() != null);
		} catch (NoResultException e) {
			return false;
		}
	}

	@Override
	public Usuario save(Usuario entity) throws CommonException {
		if (this.isMailExist(entity)) {
			throw new CommonException("email.ja.cadastrado");
		}

		return super.save(entity);
	}
	
}