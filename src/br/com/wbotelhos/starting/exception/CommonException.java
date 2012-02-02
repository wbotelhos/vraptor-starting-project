package br.com.wbotelhos.starting.exception;

public class CommonException extends Exception {

	private static final long serialVersionUID = 5746751520999684832L;

	public CommonException(String mensagem) {
		super(mensagem);
	}

	public CommonException(String mensagem, Throwable e) {
		super(mensagem, e);
	}

}
