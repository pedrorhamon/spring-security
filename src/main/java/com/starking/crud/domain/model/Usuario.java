package com.starking.crud.domain.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Email;
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
	
	@NotNull(message = "O nome é obrigatorio")
	@NotEmpty(message = "O nome não pode ser vázio")
	private String nome;
	
	@CPF
	@Column(unique = true)
	@NotNull(message = "O CPF é obrigatorio")
	private String cpf;
	
	@Email(message = "Digite o formato de e-mail válido")
	private String email;
	
	private String telefone;
	
	@DateTimeFormat(iso = ISO.DATE, fallbackPatterns = {"dd/MM/yyyy"})
	@NotNull(message = "A data de nascimento é obrigatorio")
	private Date dataNascimento;
	
	@ManyToOne
	@JoinColumn(name = "produtos")
	private List<Produto> produtos;
}
