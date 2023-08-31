package com.starking.crud.exception;

/**
 * @author pedroRhamon
 */
public class ErroAcessoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ErroAcessoException(String msg) {
		super(msg);
	}
}