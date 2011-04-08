package br.com.wbotelhos.starting.business;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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

	public Usuario getUsuarioByEmail(String email) {
		try {
			Query query = manager.createQuery("from Usuario where email = :email");
			query.setParameter("email", email);
			return (Usuario) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public Usuario save(Usuario entity) throws CommonException {
		if (this.getUsuarioByEmail(entity.getEmail()) != null) {
			throw new CommonException("email.ja.cadastrado");
		}

		return super.save(entity);
	}
	
}