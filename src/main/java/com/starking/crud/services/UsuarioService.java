package com.starking.crud.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.starking.crud.domain.model.Usuario;
import com.starking.crud.repositories.UsuarioRepository;

import lombok.RequiredArgsConstructor;

/**
 * @author pedroRhamon
 */
@Service
@RequiredArgsConstructor
public class UsuarioService {
	
	private final UsuarioRepository usuarioRepository;
	
	public Optional<Usuario> salvarUsuario(Usuario usuario) {
		Usuario usuarioSalvo = this.usuarioRepository.save(usuario);
		return Optional.ofNullable(usuarioSalvo);
	}

}
