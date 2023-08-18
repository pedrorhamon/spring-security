package com.starking.crud.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.starking.crud.domain.model.Usuario;
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

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<Usuario> listarUsuario() {
		return this.usuarioService.listarUsuario();
	}

	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void buscarPorId(@RequestParam Usuario usuario) {
		this.usuarioService.buscarPorId(usuario);
	}

}
