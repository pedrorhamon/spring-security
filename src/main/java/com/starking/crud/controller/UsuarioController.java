package com.starking.crud.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

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

/**
 * @author pedroRhamon
 */
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

private UsuarioService usuarioService;
	
	private JwtService jwtService;
	
	@Autowired
	public UsuarioController(UsuarioService usuarioService, JwtService jwtService) {
		this.usuarioService = usuarioService;
		this.jwtService = jwtService;
	}
	
	@PostMapping("/autenticar")
	@ResponseStatus(HttpStatus.OK)
	public void autenticar( @RequestBody @Validated CredenciaisRecord record ) {
		try {
			Usuario usuarioAutenticado = this.usuarioService.autenticar(record.email(), record.senha());
			String token = jwtService.gerarToken(usuarioAutenticado);
			TokenRecord tokenDTO = new TokenRecord( usuarioAutenticado.getNome(), token);
		}catch (ErroAcessoException e) {
			e.printStackTrace();
			return;
		}
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<Usuario> listarUsuario() {
		return this.usuarioService.listarUsuario();
	}

	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void buscarPorId(@PathVariable Long id) {
		this.usuarioService.buscarPorId(id);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Optional<Usuario> salvarUsuario(@RequestBody @Valid Usuario usuario) {
		return this.usuarioService.salvarUsuario(usuario);
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizarUsuario(@RequestBody Usuario usuario, @PathVariable Long id) {
		this.usuarioService.atualizarUsuario(usuario, id);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletarUsuario(@PathVariable Long id) {
		this.usuarioService.deletarUsuario(id);
	}

}
