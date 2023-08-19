package com.starking.crud.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.starking.crud.domain.model.Produto;
import com.starking.crud.repositories.ProdutoRepository;

import lombok.RequiredArgsConstructor;

/**
 * @author pedroRhamon
 */
@Service
@RequiredArgsConstructor
public class ProdutoService {
	
	private final ProdutoRepository produtoRepository;
	
	public List<Produto> buscarTodosProduto() {
		return this.produtoRepository.findAll();
	}

}
