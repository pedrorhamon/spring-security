package com.starking.crud.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.starking.crud.domain.model.Usuario;
import com.starking.crud.repositories.UsuarioRepository;

import lombok.RequiredArgsConstructor;

/**
 * @author pedroRhamon
 */

@Service
@RequiredArgsConstructor
public class SecurityUserDetailsService implements UserDetailsService {
	
	private final UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		Usuario usuarioEncontrado = usuarioRepository
				.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("Email não cadastrado."));
		
		return null;
	}

}
