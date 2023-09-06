package com.starking.crud.domain.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.validation.constraints.NegativeOrZero;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author pedroRhamon
 */
@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Produto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	
	@NotNull(message = "O nome é obrigatorio")
	@NotEmpty(message = "O nome não pode ser vázio")
	@Size(min = 3, max = 100)
	private String title;
	
	@NotNull(message = "O preço é obrigatorio")
	@NotEmpty(message = "O preço não pode ser vázio")
	@Positive
	@NegativeOrZero
	private BigDecimal preco;
	
	private String descricao;
	
	@Lob
	private byte[] imagem;
}
