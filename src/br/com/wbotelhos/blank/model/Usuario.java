package br.com.wbotelhos.blank.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.hibernate.validator.Email;

import br.com.wbotelhos.blank.model.common.AbstractEntity;
import br.com.wbotelhos.blank.model.common.TipoPerfil;

@Entity
public class Usuario extends AbstractEntity {

	private static final long serialVersionUID = -2011617444465730587L;

	private String nome;

	@Email
	@Column(unique = true)
	private String email;

	@Column(length = 13)
	private String senha;

	@Column(length = 13)
	@Enumerated(EnumType.STRING)
	private TipoPerfil perfil;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public TipoPerfil getPerfil() {
		return perfil;
	}

	public void setPerfil(TipoPerfil perfil) {
		this.perfil = perfil;
	}

}