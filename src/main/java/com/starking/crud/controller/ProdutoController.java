package com.starking.crud.controller;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void buscarPorId(@PathVariable Long id) {
		this.produtoService.buscarPorId(id);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Optional<Produto> salvarProduto(@RequestBody Produto produto) {
		return this.produtoService.salvarProduto(produto);
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizarProduto(@RequestBody Produto produto, @PathVariable Long id) {
		this.produtoService.atualizarUsuario(produto, id);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletarProduto(@PathVariable Long id) {
		this.produtoService.deletarProduto(id);
	}
}
