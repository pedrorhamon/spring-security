package com.starking.crud.services;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.starking.crud.domain.model.Produto;
import com.starking.crud.repositories.ProdutoRepository;

/**
 * @author pedroRhamon
 */
@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	public Page<Produto> buscarTodosProduto(Pageable pageable) {
		return this.produtoRepository.findAll(pageable);
	}
	
    public ByteArrayOutputStream gerarRelatorioPDF(Page<Produto> produtos) throws DocumentException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        
        Document document = new Document();
        PdfWriter.getInstance(document, byteArrayOutputStream);
        
        document.open();
        document.add(new Paragraph("Relatório de Produtos:"));

        for (Produto produto : produtos) {
            document.add(new Paragraph("Nome: " + produto.getTitle()));
            document.add(new Paragraph("Preço: " + produto.getPreco()));
            document.add(new Paragraph("Descrição: " + produto.getDescricao()));
            document.add(new Paragraph("Foto: " + produto.getImagem()));
            document.add(new Paragraph("-----------------------------------"));
        }

        document.close();
        return byteArrayOutputStream;
    }

	
	public Produto buscarPorId(Long id) {
		Optional<Produto> produtoOptional = produtoRepository.findById(id);
		return produtoOptional.orElseThrow(() -> 
		new RuntimeException("Produto com ID " + id + " não encontrado"));
	}

	public void atualizarUsuario(Produto produto, Long id) {
	    Optional<Produto> produtoAtualizadoOptional = Optional.ofNullable(this.buscarPorId(id));
	    Produto produtoAtualizado = produtoAtualizadoOptional.orElseThrow(() ->
	            new RuntimeException("Usuário com ID " + id + " não encontrado"));

	    BeanUtils.copyProperties(produto, produtoAtualizado);

	    this.produtoRepository.save(produtoAtualizado);
	}

	public Optional<Produto> salvarProduto(Produto produto, MultipartFile imagem) throws IOException {
		Produto produtoSalvar = this.produtoRepository.save(produto); 
		produtoSalvar.setImagem(imagem.getBytes());
		return Optional.ofNullable(produtoSalvar);
	}

	public void deletarProduto(Long id) {
		this.produtoRepository.deleteById(id);
	}
}
