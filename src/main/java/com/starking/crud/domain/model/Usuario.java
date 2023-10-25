package com.starking.crud.domain.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author pedroRhamon
 */

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = "O nome é obrigatorio")
	@NotEmpty(message = "O nome não pode ser vázio")
	private String nome;
	
	@CPF
	@Column(unique = true)
	@NotNull(message = "O CPF é obrigatorio")
	private String cpf;
	
	@Email(message = "Digite o formato de e-mail válido")
	private String email;
	
	@NotNull(message = "A senha não pode ser nula")
	@NotEmpty(message = "A Senha não pode ser vazia")
	private String senha;
	
	private String telefone;
	
	@DateTimeFormat(iso = ISO.DATE, fallbackPatterns = {"dd/MM/yyyy"})
	@NotNull(message = "A data de nascimento não pode ser nula")
	private Date dataNascimento;
	
	private Boolean ativo = Boolean.TRUE;
}
