package br.com.wbotelhos.starting.model.common;

public enum Perfil {

	MEMBRO("Membro"),
	MODERADOR("Moderador"),
	ADMINISTRADOR("Administrador");

	private String label;

	private Perfil(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

}