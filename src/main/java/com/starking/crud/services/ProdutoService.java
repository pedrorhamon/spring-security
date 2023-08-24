package com.starking.crud.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

	public Page<Produto> buscarTodosProduto(Pageable pageable) {
		return this.produtoRepository.findAll(pageable);
	}

	public Optional<Produto> salvarProduto(Produto produto) {
		Produto produtoSalvar = this.produtoRepository.save(produto);
		return Optional.ofNullable(produtoSalvar);
	}

}
