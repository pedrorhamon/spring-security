package com.starking.crud.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.starking.crud.domain.model.Usuario;
import com.starking.crud.exception.ErroAutenticacao;
import com.starking.crud.repositories.UsuarioRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

/**
 * @author pedroRhamon
 */
@Service
@RequiredArgsConstructor
public class UsuarioService {

	private final UsuarioRepository usuarioRepository;
	private final PasswordEncoder encoder;

	public Usuario autenticar(String email, String senha) {
		Optional<Usuario> usuario = this.usuarioRepository.findByEmail(email);

		if (!usuario.isPresent()) {
			throw new ErroAutenticacao("Usuário não encontrado para o email informado.");
		}

		boolean senhasBatem = encoder.matches(senha, usuario.get().getSenha());

		if (!senhasBatem) {
			throw new ErroAutenticacao("Senha inválida.");
		}

		return usuario.get();
	}
	
	public List<Usuario> listarUsuario() {
		return this.usuarioRepository.findAll();
	}

	public Usuario buscarPorId(Long id) {
		Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
		return usuarioOptional.orElseThrow(() -> 
		new RuntimeException("Usuário com ID " + id + " não encontrado"));
	}

	public void atualizarUsuario(Usuario usuario, Long id) {
	    Optional<Usuario> usuarioAtualizadoOptional = Optional.ofNullable(this.buscarPorId(id));
	    Usuario usuarioAtualizado = usuarioAtualizadoOptional.orElseThrow(() ->
	            new RuntimeException("Usuário com ID " + id + " não encontrado"));

	    BeanUtils.copyProperties(usuario, usuarioAtualizado);

	    this.usuarioRepository.save(usuarioAtualizado);
	}

	@Transactional
	public Optional<Usuario> salvarUsuario(Usuario usuario) {
		Usuario usuarioSalvo = this.usuarioRepository.save(usuario);
		return Optional.ofNullable(usuarioSalvo);
	}
	
	public void validarEmail(String email) {
		boolean existe = this.usuarioRepository.existsByEmail(email);
		if(existe) {
			throw new RuntimeException("Já existe um usuário cadastrado com este email.");
		}
	}
}
