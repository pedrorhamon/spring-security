package com.starking.crud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.starking.crud.domain.model.Produto;

/**
 * @author pedroRhamon
 */
public interface ProdutoRepository extends JpaRepository<Produto, Long>{

}
