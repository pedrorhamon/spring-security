package com.starking.crud.domain.model;

import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.NegativeOrZero;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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
