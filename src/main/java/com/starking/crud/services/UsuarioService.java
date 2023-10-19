package com.starking.crud.services;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.starking.crud.domain.model.Usuario;
import com.starking.crud.exception.ErroAutenticacao;
import com.starking.crud.repositories.UsuarioRepository;

/**
 * @author pedroRhamon
 */
@Service
public class UsuarioService {

	private final UsuarioRepository usuarioRepository;
	private final PasswordEncoder encoder;
	
	@Autowired
	public UsuarioService(
			UsuarioRepository usuarioRepository, 
			PasswordEncoder encoder) {
		this.usuarioRepository = usuarioRepository;
		this.encoder = encoder;
	}
	
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

	@Transactional
	public void deletarUsuario(Long id) {
		this.usuarioRepository.deleteById(id);
	}
	
	// Método para exportar usuários para Excel
	public void exportarDadosExcel() throws Exception, IOException  {
		
		List<Usuario> listarUsuario = this.listarUsuario();
		
		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet("Lista de Usuários");
		
		 int rowNum = 0;
		
	}
}
