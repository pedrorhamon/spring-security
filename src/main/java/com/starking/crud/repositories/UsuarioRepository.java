package com.starking.crud.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.starking.crud.domain.model.Usuario;

/**
 * @author pedroRhamon
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	boolean existsByEmail(String email);

	Optional<Usuario> findByEmail(String email);
}
