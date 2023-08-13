package com.starking.crud.domain.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author pedroRhamon
 */
public class Usuario implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String nome;
	
	private String cpf;
	
	private String telefone;
	
	private Date dataNascimento;
}
