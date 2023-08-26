package com.starking.crud.services;

import org.springframework.beans.factory.annotation.Value;

/**
 * @author pedroRhamon
 */
public class JwtService {
	
	@Value("${jwt.expiracao}")
	private String expiracao;
	
	@Value("${jwt.chave-assinatura}")
	private String chaveAssinatura;

}
