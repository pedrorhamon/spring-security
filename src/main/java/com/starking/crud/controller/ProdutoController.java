package com.starking.crud.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.apache.logging.log4j.*;

import com.itextpdf.text.DocumentException;
import com.starking.crud.domain.model.Produto;
import com.starking.crud.services.ProdutoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

/**
 * @author pedroRhamon
 */

@RestController
@RequestMapping("/produto")
@RequiredArgsConstructor
@Api("Api de produto")
public class ProdutoController {
	
	 private static final Logger log = LogManager.getLogger(UsuarioController.class);
	
	private final ProdutoService produtoService;
	
	@ApiOperation("Busca todos os produtos")
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public Page<Produto> buscarTodosProdutos(Pageable pageable) {
		return this.produtoService.buscarTodosProduto(pageable);
	}
	
	@ApiOperation("Exportação da listagem de todos os produtos")
	@GetMapping("/exportar-pdf")
	@ResponseStatus(HttpStatus.OK)
    public byte[] exportarProdutosParaPDF(Pageable pageable) throws DocumentException, IOException {
        Page<Produto> produtos = produtoService.buscarTodosProduto(pageable);
        ByteArrayOutputStream pdfStream = produtoService.gerarRelatorioPDF(produtos);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "relatorio_produtos.pdf");

        log.info("Exportando Produtos em PDF com sucesso.");
        return pdfStream.toByteArray();
    }
	
	@ApiOperation("Busca produtos por id")
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void buscarPorId(@PathVariable Long id) {
		this.produtoService.buscarPorId(id);
	}
	
	@ApiOperation("Cadastra novos produtos")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Optional<Produto> salvarProduto(@RequestBody Produto produto, @RequestParam MultipartFile imagem) throws IOException {
		return this.produtoService.salvarProduto(produto, imagem);
	}
	
	@ApiOperation("Altera produtos existente")
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizarProduto(@RequestBody Produto produto, @PathVariable Long id) {
		this.produtoService.atualizarUsuario(produto, id);
	}
	
	@ApiOperation("Remove produtos")
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletarProduto(@PathVariable Long id) {
		this.produtoService.deletarProduto(id);
	}
}
