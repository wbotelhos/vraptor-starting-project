package br.com.wbotelhos.blank.model.common;

public enum TipoPerfil {

	VISITANTE("Visitante"),
	MEMBRO("Membro"),
	MODERADOR("Moderador"),
	ADMINISTRADOR("Administrador");

	private String label;

	private TipoPerfil(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

}