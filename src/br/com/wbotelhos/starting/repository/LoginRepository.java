package br.com.wbotelhos.starting.repository;

import br.com.wbotelhos.starting.model.Usuario;

public interface LoginRepository {

	Usuario autenticar(String email, String senha);

}