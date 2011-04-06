package br.com.wbotelhos.starting.exception;

public class UploadException extends Exception {

	private static final long serialVersionUID = 1692477659813333477L;

	public UploadException(String mensagem) {
		super(mensagem);
	}

	public UploadException(String mensagem, Throwable e) {
		super(mensagem, e);
	}

}