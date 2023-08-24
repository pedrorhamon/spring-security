package com.starking.crud.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.starking.crud.domain.model.Produto;
import com.starking.crud.services.ProdutoService;

import lombok.RequiredArgsConstructor;

/**
 * @author pedroRhamon
 */

@RestController
@RequestMapping("/produto")
@RequiredArgsConstructor
public class ProdutoController {
	
	private final ProdutoService produtoService;
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public Page<Produto> buscarTodosProdutos(Pageable pageable) {
		return this.produtoService.buscarTodosProduto(pageable);
	}

}