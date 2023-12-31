package com.starking.crud.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.starking.crud.domain.CredenciaisRecord;
import com.starking.crud.domain.TokenRecord;
import com.starking.crud.domain.model.Usuario;
import com.starking.crud.exception.ErroAcessoException;
import com.starking.crud.services.JwtService;
import com.starking.crud.services.UsuarioService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author pedroRhamon
 */
@RestController
@RequestMapping("/usuarios")
@Api("Api de Usuário")
public class UsuarioController {

private UsuarioService usuarioService;
	
	private static final Logger log = LogManager.getLogger(UsuarioController.class);

	private JwtService jwtService;
	
	@Autowired
	public UsuarioController(UsuarioService usuarioService, JwtService jwtService) {
		this.usuarioService = usuarioService;
		this.jwtService = jwtService;
	}
	
	@ApiOperation("Autenticar")
	@PostMapping("/autenticar")
	@ResponseStatus(HttpStatus.OK)
	public void autenticar( @RequestBody @Validated CredenciaisRecord record ) {
		try {
			Usuario usuarioAutenticado = this.usuarioService.autenticar(record.email(), record.senha());
			String token = jwtService.gerarToken(usuarioAutenticado);
			@SuppressWarnings("unused")
			TokenRecord tokenDTO = new TokenRecord( usuarioAutenticado.getNome(), token);
		}catch (ErroAcessoException e) {
			e.printStackTrace();
			return;
		}
	}

	@ApiOperation("Busca todos os usuários")
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<Usuario> listarUsuario() {
		return this.usuarioService.listarUsuario();
	}

	@ApiOperation("Busca usuários por id")
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void buscarPorId(@PathVariable Long id) {
		this.usuarioService.buscarPorId(id);
	}

	@ApiOperation("Criar usuário")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Optional<Usuario> salvarUsuario(@RequestBody @Valid Usuario usuario) {
		return this.usuarioService.salvarUsuario(usuario);
	}
	
	@ApiOperation("Atualizar usuário")
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizarUsuario(@RequestBody Usuario usuario, @PathVariable Long id) {
		this.usuarioService.atualizarUsuario(usuario, id);
	}
	
	@ApiOperation("Deletar usuário")
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletarUsuario(@PathVariable Long id) {
		this.usuarioService.deletarUsuario(id);
	}
	
	@ApiOperation("Exportar Relatório para auditoria")
	@GetMapping("/exportar-excel")
	@ResponseStatus(HttpStatus.OK)
	public void exportarDadosExcel() throws Exception, IOException {
		this.usuarioService.exportarDadosExcel();
		log.info("Usuários exportados para Excel com sucesso.");
	}
}
