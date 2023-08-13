package com.starking.crud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.starking.crud.domain.model.Usuario;

/**
 * @author pedroRhamon
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

}
