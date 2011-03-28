package br.com.wbotelhos.blank.repository;

import br.com.wbotelhos.blank.model.Usuario;

public interface LoginRepository {

	Usuario autenticar(String email, String senha);

}