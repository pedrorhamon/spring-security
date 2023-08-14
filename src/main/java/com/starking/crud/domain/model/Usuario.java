package com.starking.crud.domain.model;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author pedroRhamon
 */

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Usuario implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	
	@NotNull
	@NotEmpty
	private String nome;
	
	@CPF
	@Column(unique = true)
	private String cpf;
	
	private String telefone;
	
	@DateTimeFormat(iso = ISO.DATE, fallbackPatterns = {"dd/MM/yyyy"})
	private Date dataNascimento;
}
