package br.com.wbotelhos.starting.repository;

import br.com.wbotelhos.starting.model.Usuario;
import br.com.wbotelhos.starting.repository.common.GenericImageRepository;
import br.com.wbotelhos.starting.repository.common.GenericRepository;

public interface UsuarioRepository extends GenericRepository<Usuario>, GenericImageRepository<Usuario> {

}