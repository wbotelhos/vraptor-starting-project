package br.com.wbotelhos.starting.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;

import org.hibernate.validator.Email;
import org.hibernate.validator.NotEmpty;
import org.hibernate.validator.NotNull;

import br.com.wbotelhos.starting.model.common.AbstractImage;
import br.com.wbotelhos.starting.model.common.TipoPerfil;

@Entity
public class Usuario extends AbstractImage {

	private static final long serialVersionUID = 2831700789078671451L;

	@NotNull
	@NotEmpty
	private String nome;

	@Email
	@NotNull
	@NotEmpty
	@Column(unique = true)
	private String email;

	@NotNull
	@NotEmpty
	@Column(length = 13)
	private String senha;

	@NotNull
	@Column(length = 13)
	@Enumerated(EnumType.STRING)
	private TipoPerfil perfil;

	@OneToMany(mappedBy = "usuario", cascade = CascadeType.REMOVE)
	private List<UsuarioImage> imageList;

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

	public List<UsuarioImage> getImageList() {
		return imageList;
	}

	public void setImageList(List<UsuarioImage> imageList) {
		this.imageList = imageList;
	}

	@Override
	public String getFolderName() {
		return "usuario";
	}

}