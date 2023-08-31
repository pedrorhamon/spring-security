package com.starking.crud.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.starking.crud.services.JwtService;
import com.starking.crud.services.UsuarioService;

import lombok.RequiredArgsConstructor;

/**
 * @author pedroRhamon
 */
@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioController {

	private final UsuarioService usuarioService;
	private final JwtService jwtService;
	
	@PostMapping("/autenticar")
	@ResponseStatus(HttpStatus.OK)
	public void autenticar( @RequestBody @Validated CredenciaisRecord dto ) {
		try {
			Usuario usuarioAutenticado = this.usuarioService.autenticar(dto.getEmail(), dto.getSenha());
			String token = jwtService.gerarToken(usuarioAutenticado);
			TokenRecord tokenDTO = new TokenRecord( usuarioAutenticado.getNome(), token);
			return ResponseEntity.ok(tokenDTO);
		}catch (ErroAcessoException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
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
	public Optional<Usuario> salvarUsuario(@RequestBody Usuario usuario) {
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
