package br.com.wbotelhos.starting.business;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.caelum.vraptor.ioc.Component;
import br.com.wbotelhos.starting.model.Usuario;
import br.com.wbotelhos.starting.repository.LoginRepository;

@Component
public class LoginBusiness implements LoginRepository {

	private EntityManager manager;

	public LoginBusiness(EntityManager manager) {
		this.manager = manager;
	}

	public Usuario autenticar(String email, String senha) {
		try {
			Query query = manager.createQuery("from Usuario where email = :email and senha = :senha");
			query.setParameter("email", email);
			query.setParameter("senha", senha);
			return (Usuario) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

}