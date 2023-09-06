package com.starking.crud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.starking.crud.domain.model.Produto;

/**
 * @author pedroRhamon
 */
@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{}