package com.starking.crud.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

}
